package se331.zzz.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.zzz.dao.LevelDao;
import se331.zzz.entity.Level;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService{
    final LevelDao levelDao;

    @Override 
    public List<Level> getLevels(){
        return levelDao.getLevels();
    }
    @Override 
    public Level getLevel(Long id){
        return levelDao.findById(id).orElse(null);
    }
    @Override
    @Transactional 
    public Level update(Long id, Level level){
        Level output = levelDao.findById(id).orElse(null);
        if(output == null){
            return null;
        }
        output.setName(level.getName());
        output.setDescription(level.getDescription());
        output.setPassScore(level.getPassScore());
        return levelDao.save(output);
    }
}
