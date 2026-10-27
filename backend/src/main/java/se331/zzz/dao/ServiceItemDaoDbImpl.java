package se331.zzz.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.entity.ServiceItem;
import se331.zzz.repository.ServiceItemRepository;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Profile("db")
public class ServiceItemDaoDbImpl implements ServiceItemDao{
    final ServiceItemRepository serviceItemRepository;

    @Override 
     public Optional<ServiceItem> findById(Long id){
        return serviceItemRepository.findById(id);
    }
    @Override 
    public ServiceItem save(ServiceItem serviceItem){
        return serviceItemRepository.save(serviceItem);
    }
    @Override 
    public void delete(ServiceItem serviceItem){
        serviceItemRepository.delete(serviceItem);
    }
}
