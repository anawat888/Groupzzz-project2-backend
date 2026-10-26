package se331.zzz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity 
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 

public class Level {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String description;
    Integer passScore;


    @OneToMany(mappedBy = "level")
    @Builder.Default 
    List<Benefit> benefits = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<ServiceItem> services = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    List<Question> questions = new ArrayList<>();
}
