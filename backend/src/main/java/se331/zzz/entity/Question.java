package se331.zzz.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity 
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class Question {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String question;
    @ElementCollection 
    List<String> choices;
    String correctAnswer;
    @ManyToOne 
    Level level;
}
