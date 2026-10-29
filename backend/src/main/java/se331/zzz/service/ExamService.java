package se331.zzz.service;

import se331.zzz.dto.ExamResultDTO;
import se331.zzz.dto.ExamSubmitDTO;
import se331.zzz.entity.ElearningContent;
import se331.zzz.entity.ExamAttempt;
import se331.zzz.entity.Question;
import java.util.List;

public interface ExamService {
    List<Question> getExamQuestions(Long userId);
    ExamResultDTO submit(Long userId, ExamSubmitDTO submit);
    List<ExamAttempt> getHistory(Long userId);
    List<ElearningContent> getElearning(Long userId);
}