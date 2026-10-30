package se331.zzz.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Size(min = 4, max = 30)
    String username;
    @NotBlank
    @Size(min = 6)
    String password;
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotBlank
    @Email
    String email;
    @NotBlank
    String province;
    @NotBlank
    String image;
}
