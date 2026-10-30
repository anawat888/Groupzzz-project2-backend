package se331.zzz.dao;

import se331.zzz.entity.ExamAttempt;
import java.util.List;

public interface ExamAttemptDao {
    ExamAttempt save(ExamAttempt attempt);
    List<ExamAttempt> findByUserId(Long userId);
}