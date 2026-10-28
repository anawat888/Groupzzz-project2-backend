package se331.zzz.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExamResultDTO {
    Integer score;
    Boolean passed;
    Integer passScore;
    Boolean levelUpgraded;
    Long currentLevelId;
}