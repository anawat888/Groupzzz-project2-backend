package se331.zzz.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.dao.LevelDao;
import se331.zzz.security.config.JwtService;
import se331.zzz.security.token.Token;
import se331.zzz.security.token.TokenRepository;
import se331.zzz.user.Role;
import se331.zzz.user.User;
import se331.zzz.user.UserRepository;
import se331.zzz.util.UserMapper;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    final UserRepository userRepository;
    final TokenRepository tokenRepository;
    final LevelDao levelDao;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already registered");
        }
        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .province(request.getProvince())
                .image(request.getImage())
                .active(true)
                .roles(List.of(Role.ROLE_USER))
                .level(levelDao.getLevels().getFirst())
                .build());
        return createSession(user);
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        checkCredentials(request);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return createSession(user);
    }

    private void checkCredentials(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This account has been deactivated");
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    private AuthenticationResponse createSession(User user) {
        String jwt = jwtService.generateToken(user);
        tokenRepository.save(Token.builder().token(jwt).user(user).build());
        return AuthenticationResponse.builder()
                .token(jwt)
                .user(UserMapper.INSTANCE.getUserDto(user))
                .build();
    }
}
