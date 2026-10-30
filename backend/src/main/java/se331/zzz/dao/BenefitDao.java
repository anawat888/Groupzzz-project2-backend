package se331.zzz.dao;
import se331.zzz.entity.Benefit;
import java.util.Optional;
public interface BenefitDao {
    Optional<Benefit> findById(Long id);
    Benefit save(Benefit benefit);
    void delete(Benefit benefit);
}
