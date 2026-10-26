package se331.zzz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 

public class ServiceItem {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String description;
    @ManyToOne 
    Level level;
}
