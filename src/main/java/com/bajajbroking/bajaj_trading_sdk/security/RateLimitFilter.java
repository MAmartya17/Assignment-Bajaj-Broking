package com.bajajbroking.bajaj_trading_sdk.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends HttpFilter {
    private final Map<String, Instant> lastCall = new ConcurrentHashMap<>();
    private final int seconds;

    public RateLimitFilter(@Value("${app.rateLimitSeconds}") int seconds) {
        this.seconds = seconds;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String auth = req.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }
        String user = auth.substring(7).trim();
        Instant now = Instant.now();
        Instant prev = lastCall.get(user);
        if (prev != null && now.isBefore(prev.plusSeconds(seconds))) {
            res.setStatus(429);
            res.getWriter().write("{\"statusCode\":1,\"message\":\"Too Many Requests - per-user rate limit of " + seconds + " seconds\"}");
            return;
        }
        lastCall.put(user, now);
        chain.doFilter(req, res);
    }
}
