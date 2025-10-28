package com.example.demo.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAttemptService {

    private final int MAX_ATTEMPTS = 5;
    private final long BLOCK_TIME_MS = TimeUnit.MINUTES.toMillis(10);

    private final ConcurrentHashMap<String, Attempt> attemptsCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String ip) {
        attemptsCache.remove(ip);
    }

    public void loginFailed(String ip) {
        Attempt attempt = attemptsCache.getOrDefault(ip, new Attempt());
        attempt.count++;
        attempt.lastAttempt = System.currentTimeMillis();
        attemptsCache.put(ip, attempt);
    }

    public boolean isBlocked(String ip) {
        Attempt attempt = attemptsCache.get(ip);
        if (attempt == null) return false;

        if (attempt.count >= MAX_ATTEMPTS) {
            long elapsed = System.currentTimeMillis() - attempt.lastAttempt;
            if (elapsed < BLOCK_TIME_MS) return true;
            else attemptsCache.remove(ip); // expira el bloqueo
        }
        return false;
    }

    private static class Attempt {
        int count = 0;
        long lastAttempt;
    }
}

