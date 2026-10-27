package se331.zzz.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.Benefit;
import se331.zzz.repository.BenefitRepository;
import java.util.Optional;
@Repository 
@RequiredArgsConstructor 
@Profile("db")
public class BenefitDaoDbImpl implements BenefitDao{
    final BenefitRepository benefitRepository;

    @Override 
    public Optional<Benefit> findById(Long id){
        return benefitRepository.findById(id);
    }
    @Override 
    public Benefit save(Benefit benefit){
        return benefitRepository.save(benefit);
    }
    @Override 
    public void delete(Benefit benefit){
        benefitRepository.delete(benefit);
    }
}
