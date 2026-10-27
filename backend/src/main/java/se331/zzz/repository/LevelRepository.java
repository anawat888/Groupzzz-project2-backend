package se331.zzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.Level;
import java.util.List;

public interface LevelRepository extends JpaRepository<Level,Long>{
    List<Level> findByOrderById();
}
