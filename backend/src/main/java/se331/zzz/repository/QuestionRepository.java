package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.Question;
import java.util.List;
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByOrderById();
    List<Question> findByLevel_IdOrderById(Long levelId);

}
