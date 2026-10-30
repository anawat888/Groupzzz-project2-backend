package se331.zzz.dao;
import se331.zzz.entity.Question;
import java.util.*;

public interface QuestionDao {
    List<Question> getQuestions();
    List<Question> getQuestionsByLevel(Long levelId);
    Optional<Question> findById(Long id);
    Question save(Question question);
    void delete(Question question);
}
