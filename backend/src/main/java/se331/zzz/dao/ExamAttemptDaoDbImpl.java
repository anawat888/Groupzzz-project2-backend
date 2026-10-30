package se331.zzz.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.ExamAttempt;
import se331.zzz.repository.ExamAttemptRepository;
import java.util.List;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class ExamAttemptDaoDbImpl implements ExamAttemptDao {
    final ExamAttemptRepository examAttemptRepository;

    @Override
    public ExamAttempt save(ExamAttempt attempt) {
        return examAttemptRepository.save(attempt);
    }

    @Override
    public List<ExamAttempt> findByUserId(Long userId) {
        return examAttemptRepository.findByUserIdOrderByAttemptedAtDesc(userId);
    }
}