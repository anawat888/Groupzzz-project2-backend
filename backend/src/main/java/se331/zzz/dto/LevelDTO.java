package se331.zzz.dto;
import lombok.*;
import java.util.*;
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class LevelDTO {
    Long id;
    String name;
    String description;
    Integer passScore;
    List<BenefitDTO> benefits;
    List<ServiceItemDTO> services;
}
