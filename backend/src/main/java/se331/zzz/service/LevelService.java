package se331.zzz.service;
import se331.zzz.entity.Level;
import java.util.*;

public interface LevelService {
    List<Level> getLevels();
    Level getLevel(Long id);
    Level update(Long id, Level level);
}
