package se331.zzz.dao;
import se331.zzz.entity.ServiceItem;
import java.util.Optional;
public interface ServiceItemDao {
    Optional<ServiceItem> findById(Long id);
    ServiceItem save(ServiceItem serviceItem);
    void delete(ServiceItem serviceItem);
}
