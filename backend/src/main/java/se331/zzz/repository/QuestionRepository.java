package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.Question;
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
