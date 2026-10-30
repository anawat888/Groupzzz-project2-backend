package se331.zzz.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExamAttemptDTO {
    Long id;
    Integer score;
    Boolean passed;
    LocalDateTime attemptedAt;
    LevelSummaryDTO level;
}