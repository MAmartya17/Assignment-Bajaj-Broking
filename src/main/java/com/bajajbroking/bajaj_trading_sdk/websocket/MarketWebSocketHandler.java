  package com.bajajbroking.bajaj_trading_sdk.websocket;

  import org.springframework.web.socket.CloseStatus;
  import org.springframework.web.socket.TextMessage;
  import org.springframework.web.socket.WebSocketSession;
  import org.springframework.web.socket.handler.TextWebSocketHandler;
  import org.springframework.stereotype.Component;

  import java.util.Set;
  import java.util.concurrent.CopyOnWriteArraySet;
  import java.util.concurrent.Executors;
  import java.util.concurrent.ScheduledExecutorService;
  import java.util.concurrent.TimeUnit;
  import java.util.Random;

  @Component
  public class MarketWebSocketHandler extends TextWebSocketHandler {
      private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
      private final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
      private final Random rnd = new Random();

      public MarketWebSocketHandler() {
          exec.scheduleAtFixedRate(this::broadcast, 2, 2, TimeUnit.SECONDS);
      }

      @Override
      public void afterConnectionEstablished(WebSocketSession session) {
          sessions.add(session);
      }

      @Override
      public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
          sessions.remove(session);
      }

      private void broadcast() {
          double price = 1000 + rnd.nextGaussian()*10;
          String payload = String.format("{\"symbol\":\"RELIANCE\",\"ltp\":%.2f}", price);
          sessions.forEach(s -> {
              try { s.sendMessage(new TextMessage(payload)); } catch (Exception ignored) {}
          });
      }
  }
