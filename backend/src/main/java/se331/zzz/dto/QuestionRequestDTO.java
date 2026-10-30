package se331.zzz.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
    String question;
    List<String> choices;
    String correctAnswer;
    Long levelId;
}