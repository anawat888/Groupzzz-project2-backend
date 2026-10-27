package se331.zzz.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.Question;
import se331.zzz.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Profile("db")
public class QuestionDaoDbImpl implements QuestionDao{
    final QuestionRepository questionRepository;

    @Override 
    public List<Question> getQuestions(){
        return questionRepository.findByOrderById();
    }
    @Override 
    public List<Question> getQuestionsByLevel(Long levelId){
        return questionRepository.findByLevel_IdOrderById(levelId);
    }
    @Override 
    public Optional<Question> findById(Long id){
        return questionRepository.findById(id);
    }
    @Override 
    public Question save(Question question){
        return questionRepository.save(question);
    }
    @Override 
    public void delete(Question question){
        questionRepository.delete(question);
    }
    
}
