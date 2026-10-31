package se331.zzz.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemRequestDTO {
    String name;
    String description;
}