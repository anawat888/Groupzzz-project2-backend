package se331.zzz.service;
import se331.zzz.entity.Question;
import java.util.List;
public interface QuestionService {
    List<Question> getQuestions(Long levelId);
    Question save(Question question);
    Question update(Long id, Question question);
    Question delete(Long id);
}
