package se331.zzz.dao;

import se331.zzz.entity.ElearningContent;
import java.util.List;

public interface ElearningContentDao {
    List<ElearningContent> findByLevelId(Long levelId);
}