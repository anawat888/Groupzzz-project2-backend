package se331.zzz.service;

import se331.zzz.dto.UserUpdateRequest;
import se331.zzz.entity.Level;
import se331.zzz.user.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserService {
    User getUser(Long id);
    User updateProfile(Long id, UserUpdateRequest request);
    List<Level> getBadges(Long id);
    Page<User> getUsers(String name, String province, Long levelId, Integer pageSize, Integer page);
    User updateStatus(Long id, Boolean active);

}
