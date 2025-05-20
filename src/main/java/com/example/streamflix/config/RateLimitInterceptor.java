package com.example.streamflix.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.concurrent.atomic.AtomicInteger;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int LIMIT = 100;               // peticiones
    private static final long WINDOW_MS = 60_000L;      // 1 min

    /**  IP → (contador, timestamp de ventana) */
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();

    /** Limpieza periódica de la tabla una vez por minuto */
    private final ScheduledExecutorService cleaner =
            Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "rate-limit-cleaner");
                t.setDaemon(true);
                return t;
            });

    {
        // al crear el interceptor programamos limpieza cada ventana
        cleaner.scheduleAtFixedRate(counters::clear, WINDOW_MS, WINDOW_MS, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest req,
                             @NonNull HttpServletResponse res,
                             @NonNull Object handler) {

        String ip = req.getRemoteAddr();

        Counter counter = counters.computeIfAbsent(ip, k -> new Counter());

        if (counter.incrementAndGet() > LIMIT) {
            log.warn("IP {} superó límite de {} req/min", ip, LIMIT);
            res.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // 429
            return false;
        }
        return true;
    }

    /** Contador thread-safe */
    private static final class Counter {
        private final AtomicInteger value = new AtomicInteger(0);
        int incrementAndGet() { return value.incrementAndGet(); }
    }
}