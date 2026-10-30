package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.ExamAttempt;
import java.util.List;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {
    List<ExamAttempt> findByUserIdOrderByAttemptedAtDesc(Long userId);
}