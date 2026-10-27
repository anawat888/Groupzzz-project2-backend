package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.zzz.dao.BenefitDao;
import se331.zzz.dao.LevelDao;
import se331.zzz.entity.Benefit;
import se331.zzz.entity.Level;
@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {
    final BenefitDao benefitDao;
    final LevelDao levelDao;

    @Override
    @Transactional
    public Benefit save(Long levelId, Benefit benefit) {
        Level level = levelDao.findById(levelId).orElse(null);
        if (level == null) {
            return null;
        }
        benefit.setLevel(level);
        level.getBenefits().add(benefit);
        return benefitDao.save(benefit);
    }
    @Override
    @Transactional
    public Benefit update(Long id, Benefit benefit) {
        Benefit output = benefitDao.findById(id).orElse(null);
        if (output == null) {
            return null;
        }
        output.setTitle(benefit.getTitle());
        output.setDescription(benefit.getDescription());
        return benefitDao.save(output);
    }
    @Override
    @Transactional
    public Benefit delete(Long id) {
        Benefit output = benefitDao.findById(id).orElse(null);
        if (output == null) {
            return null;
        }
        output.getLevel().getBenefits().remove(output);
        benefitDao.delete(output);
        return output;
    }
}
