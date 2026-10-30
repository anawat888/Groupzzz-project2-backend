package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.ElearningContent;
import java.util.List;

public interface ElearningContentRepository extends JpaRepository<ElearningContent, Long> {
    List<ElearningContent> findByLevelId(Long levelId);
}