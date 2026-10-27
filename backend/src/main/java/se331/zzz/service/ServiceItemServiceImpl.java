package se331.zzz.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.zzz.dao.ServiceItemDao;
import se331.zzz.dao.LevelDao;
import se331.zzz.entity.ServiceItem;
import se331.zzz.entity.Level;
@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {
    final ServiceItemDao serviceItemDao;
    final LevelDao levelDao;

    @Override
    @Transactional
    public ServiceItem save(Long levelId, ServiceItem serviceItem) {
        Level level = levelDao.findById(levelId).orElse(null);
        if (level == null) {
            return null;
        }
        serviceItem.setLevel(level);
        level.getServices().add(serviceItem);
        return serviceItemDao.save(serviceItem);
    }
    @Override
    @Transactional
    public ServiceItem update(Long id, ServiceItem serviceItem) {
        ServiceItem output = serviceItemDao.findById(id).orElse(null);
        if (output == null) {
            return null;
        }
        output.setName(serviceItem.getName());
        output.setDescription(serviceItem.getDescription());
        return serviceItemDao.save(output);
    }
    @Override
    @Transactional
    public ServiceItem delete(Long id) {
        ServiceItem output = serviceItemDao.findById(id).orElse(null);
        if (output == null) {
            return null;
        }
        output.getLevel().getServices().remove(output);
        serviceItemDao.delete(output);
        return output;
    }
}

