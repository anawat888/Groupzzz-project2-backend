package se331.zzz.dto;

import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExamQuestionDTO {
    Long id;
    String question;
    List<String> choices; // Marked: Don't have correct answer rn na ja
}