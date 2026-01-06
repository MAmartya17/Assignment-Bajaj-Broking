package com.bajajbroking.bajaj_trading_sdk.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MockAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // ✅ Accept X-USER-ID
        String userId = request.getHeader("X-USER-ID");

        if (userId == null || userId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(
                    "{\"statusCode\":1,\"message\":\"Missing Authorization header\"}"
            );
            return;
        }

        // attach user to request
        request.setAttribute("userId", userId);

        chain.doFilter(request, response);
    }
}
