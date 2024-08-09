package com.mysite.board.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class PasswordResetTokenStore {

    private final Map<String, PasswordResetToken> tokenStore = new HashMap<>();

    public String createToken(SiteUser user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, user, LocalDateTime.now().plusHours(24));
        tokenStore.put(token, resetToken);
        return token;
    }

    public PasswordResetToken getToken(String token) {
        return tokenStore.get(token);
    }

    public void removeToken(String token) {
        tokenStore.remove(token);
    }

    public static class PasswordResetToken {
        private final String token;
        private final SiteUser user;
        private final LocalDateTime expiryDate;

        public PasswordResetToken(String token, SiteUser user, LocalDateTime expiryDate) {
            this.token = token;
            this.user = user;
            this.expiryDate = expiryDate;
        }

        public String getToken() {
            return token;
        }

        public SiteUser getUser() {
            return user;
        }

        public boolean isValid() {
            return LocalDateTime.now().isBefore(expiryDate);
        }
    }
}