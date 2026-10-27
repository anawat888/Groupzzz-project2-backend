package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.zzz.dao.LevelDao;
import se331.zzz.dao.QuestionDao;
import se331.zzz.entity.Level;
import se331.zzz.entity.Question;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    final QuestionDao questionDao;
    final LevelDao levelDao;
    @Override
    public List<Question> getQuestions(Long levelId) {
        if (levelId == null) {
            return questionDao.getQuestions();
        }
        return questionDao.getQuestionsByLevel(levelId);
    }
    @Override
    @Transactional
    public Question save(Question question) {
        Level level = levelDao.findById(question.getLevel().getId()).orElse(null);
        if (level == null) {
            return null;
        }
        question.setLevel(level);
        level.getQuestions().add(question);
        return questionDao.save(question);
    }
    @Override
    @Transactional
    public Question update(Long id, Question question) {
        Question output = questionDao.findById(id).orElse(null);
        Level level = levelDao.findById(question.getLevel().getId()).orElse(null);
        if (output == null || level == null) {
            return null;
        }
        output.getLevel().getQuestions().remove(output);
        output.setQuestion(question.getQuestion());
        output.setChoices(question.getChoices());
        output.setCorrectAnswer(question.getCorrectAnswer());
        output.setLevel(level);
        level.getQuestions().add(output);
        return questionDao.save(output);
    }
    @Override
    @Transactional
    public Question delete(Long id) {
        Question output = questionDao.findById(id).orElse(null);
        if (output == null) {
            return null;
        }
        output.getLevel().getQuestions().remove(output);
        questionDao.delete(output);
        return output;
    }
}

