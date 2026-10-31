package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.dao.ExamAttemptDao;
import se331.zzz.dao.ElearningContentDao;
import se331.zzz.dao.LevelDao;
import se331.zzz.dao.QuestionDao;
import se331.zzz.dto.ExamResultDTO;
import se331.zzz.dto.ExamSubmitDTO;
import se331.zzz.entity.ElearningContent;
import se331.zzz.entity.ExamAttempt;
import se331.zzz.entity.Level;
import se331.zzz.entity.Question;
import se331.zzz.user.User;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    final QuestionDao questionDao;
    final LevelDao levelDao;
    final ExamAttemptDao examAttemptDao;
    final ElearningContentDao elearningContentDao;
    final UserLevelPort userLevelPort;

    @Override
    public List<Question> getExamQuestions(User user) {
        Level level = userLevelPort.getCurrentLevel(user.getId());
        return questionDao.getQuestionsByLevel(level.getId());
    }

    @Override
    @Transactional
    public ExamResultDTO submit(User user, ExamSubmitDTO submit) {
        Level level = userLevelPort.getCurrentLevel(user.getId());
        List<Question> questions = questionDao.getQuestionsByLevel(level.getId());

        if (questions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This level has no questions");
        }
        // Marked: Must answer all of the questions
        if (submit.getAnswers() == null || submit.getAnswers().size() != questions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All questions must be answered");
        }

        int correct = 0;
        for (Question q : questions) {
            String answer = submit.getAnswers().get(q.getId());
            if (answer == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All questions must be answered");
            }
            if (answer.equals(q.getCorrectAnswer())) {
                correct++;
            }
        }
        int score = correct * 100 / questions.size();
        boolean passed = score >= level.getPassScore();

        examAttemptDao.save(ExamAttempt.builder()
                .user(user)
                .score(score)
                .passed(passed)
                .attemptedAt(LocalDateTime.now())
                .level(level)
                .build());

        boolean upgraded = false;
        Long currentLevelId = level.getId();
        if (passed) {
            Level next = levelDao.findNext(level.getId());
            if (next != null) {
                userLevelPort.setLevel(user.getId(), next);
                upgraded = true;
                currentLevelId = next.getId();
            }
        }
        return ExamResultDTO.builder()
                .score(score).passed(passed).passScore(level.getPassScore())
                .levelUpgraded(upgraded).currentLevelId(currentLevelId)
                .build();
    }

    @Override
    public List<ExamAttempt> getHistory(User user) {
        return examAttemptDao.findByUserId(user.getId());
    }

    @Override
    public List<ExamAttempt> getHistoryByUserId(Long userId) {
        return examAttemptDao.findByUserId(userId);
    }

    @Override
    public List<ElearningContent> getElearning(User user) {
        Level level = userLevelPort.getCurrentLevel(user.getId());
        return elearningContentDao.findByLevelId(level.getId());
    }
}