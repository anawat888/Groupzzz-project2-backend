package se331.zzz.security.auth;

import lombok.*;
import se331.zzz.dto.UserDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    String token;
    UserDTO user;
}
