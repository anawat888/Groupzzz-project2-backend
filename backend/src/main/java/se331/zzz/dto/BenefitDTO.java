package se331.zzz.dto;
import lombok.*;
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class BenefitDTO {
    Long id;
    String title;
    String description;
}
