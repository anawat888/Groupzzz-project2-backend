package se331.zzz.entity;

import jakarta.persistence.*;
import lombok.*;
import se331.zzz.user.User;
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
    Integer score;
    Boolean passed;
    LocalDateTime attemptedAt;
    @ManyToOne
    Level level;
    @ManyToOne
    @ToString.Exclude
    User user;
}