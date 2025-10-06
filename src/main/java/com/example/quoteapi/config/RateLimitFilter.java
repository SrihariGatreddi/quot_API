package com.example.quoteapi.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {
    private static final int LIMIT = 5;
    private static final long WINDOW = 60_000; // 1 minute
    private final Map<String, List<Long>> requests = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String ip = request.getRemoteAddr();

        if (request.getRequestURI().startsWith("/api/quote")) {
            long now = System.currentTimeMillis();
            requests.putIfAbsent(ip, new ArrayList<>());
            List<Long> timestamps = requests.get(ip);

            synchronized (timestamps) {
                timestamps.removeIf(time -> time + WINDOW < now);
                if (timestamps.size() >= LIMIT) {
                    response.setStatus(429);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Rate limit exceeded. Try again after a minute.\"}");
                    return;
                }
                timestamps.add(now);
            }
        }
        chain.doFilter(req, res);
    }
}

