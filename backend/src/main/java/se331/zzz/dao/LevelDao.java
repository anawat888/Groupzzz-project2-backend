package se331.zzz.dao;
import se331.zzz.entity.Level;
import java.util.*;
public interface LevelDao {
    List<Level> getLevels();
    Optional<Level> findById(Long id);
    Level save(Level level);

    Level findNext(Long id);
}
