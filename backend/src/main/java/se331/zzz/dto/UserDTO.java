package se331.zzz.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String name;
    String surname;
    String email;
    String province;
    String image;
    String status;
    Long level;
    String levelName;
    List<String> roles;
}
