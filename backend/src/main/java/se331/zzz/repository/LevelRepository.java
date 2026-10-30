package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.Level;
import java.util.List;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level,Long>{
    List<Level> findByOrderById();
    Optional<Level> findFirstByIdGreaterThanOrderByIdAsc(Long id);
}
