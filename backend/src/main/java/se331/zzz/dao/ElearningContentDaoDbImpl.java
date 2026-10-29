package se331.zzz.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.ElearningContent;
import se331.zzz.repository.ElearningContentRepository;
import java.util.List;

@Repository
@Profile("db")
@RequiredArgsConstructor
public class ElearningContentDaoDbImpl implements ElearningContentDao {
    final ElearningContentRepository elearningContentRepository;

    @Override
    public List<ElearningContent> findByLevelId(Long levelId) {
        return elearningContentRepository.findByLevelId(levelId);
    }
}