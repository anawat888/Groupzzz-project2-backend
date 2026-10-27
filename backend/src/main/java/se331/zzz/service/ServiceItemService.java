package se331.zzz.service;
import se331.zzz.entity.ServiceItem;

public interface ServiceItemService {
    ServiceItem save(Long levelId, ServiceItem serviceItem);
    ServiceItem update(Long id, ServiceItem serviceItem);
    ServiceItem delete(Long id);
}

