package se331.zzz.dao;
import lombok.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.Level;
import se331.zzz.repository.LevelRepository;
import java.util.List;
import java.util.Optional;

@Repository 
@RequiredArgsConstructor 
@Profile("db")

public class LevelDaoDbImpl implements LevelDao {
    final LevelRepository levelRepository;

    @Override 
    public List<Level> getLevels(){
        return levelRepository.findByOrderById();
    }

    @Override 
    public Optional<Level> findById(Long id){
        return levelRepository.findById(id);
    }
    @Override 
    public Level save(Level level){
        return levelRepository.save(level);
    }

    @Override
    public Level findNext(Long id) {
        return levelRepository.findFirstByIdGreaterThanOrderByIdAsc(id).orElse(null);
    }
}
