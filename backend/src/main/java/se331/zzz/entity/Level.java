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

    // Marked: Exclude the lists from toString/equals to avoid infinite recursion (Level <-> children)
    @OneToMany(mappedBy = "level")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Benefit> benefits = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<ServiceItem> services = new ArrayList<>();

    @OneToMany(mappedBy = "level")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Question> questions = new ArrayList<>();
}
