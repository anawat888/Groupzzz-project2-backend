package se331.zzz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Long userId; // Marked: Change in the future
    Integer score;
    Boolean passed;
    LocalDateTime attemptedAt;
    @ManyToOne
    Level level;
}