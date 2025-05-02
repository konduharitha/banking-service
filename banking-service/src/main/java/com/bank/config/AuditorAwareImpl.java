package com.bank.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Use your authentication mechanism (e.g., Spring Security)
        // For now, returning a static user:
        return Optional.of("SYSTEM_USER");
    }
}
