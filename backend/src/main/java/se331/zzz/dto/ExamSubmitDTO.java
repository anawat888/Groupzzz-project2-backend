package se331.zzz.dto;

import lombok.*;
import java.util.Map;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExamSubmitDTO {
    Map<Long, String> answers;   // Marked: questionId -> Selected Answer
}