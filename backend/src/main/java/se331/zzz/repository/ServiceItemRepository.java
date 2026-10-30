package se331.zzz.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.zzz.entity.ServiceItem;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
    
}
