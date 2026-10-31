package se331.zzz.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequestDTO {
    String name;
    String description;
    Integer passScore;
}