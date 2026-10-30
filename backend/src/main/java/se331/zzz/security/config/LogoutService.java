package se331.zzz.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import se331.zzz.security.token.TokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(JwtAuthenticationFilter.BEARER_PREFIX)) {
            return;
        }
        tokenRepository.findByToken(authHeader.substring(JwtAuthenticationFilter.BEARER_PREFIX.length()))
                .ifPresent(token -> {
                    token.setRevoked(true);
                    tokenRepository.save(token);
                });
    }
}
