package se331.zzz.dto;
import lombok.*;
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class ServiceItemDTO {
    Long id;
    String name;
    String description;
}
