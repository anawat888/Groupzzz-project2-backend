package se331.zzz.service;

import se331.zzz.dto.ExamResultDTO;
import se331.zzz.dto.ExamSubmitDTO;
import se331.zzz.entity.ElearningContent;
import se331.zzz.entity.ExamAttempt;
import se331.zzz.entity.Question;
import se331.zzz.user.User;
import java.util.List;

public interface ExamService {
    List<Question> getExamQuestions(User user);
    ExamResultDTO submit(User user, ExamSubmitDTO submit);
    List<ExamAttempt> getHistory(User user);
    List<ElearningContent> getElearning(User user);
}