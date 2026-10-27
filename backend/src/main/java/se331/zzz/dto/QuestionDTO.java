package se331.zzz.dto;
import lombok.*;
import java.util.*;
@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class QuestionDTO {
    Long id;
    String question;
    List<String> choices;
    String correctAnswer;
    LevelSummaryDTO level;
}
