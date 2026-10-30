package se331.zzz.service;

import se331.zzz.dto.UserUpdateRequest;
import se331.zzz.entity.Level;
import se331.zzz.user.User;
import java.util.List;

public interface UserService {
    User getUser(Long id);
    User updateProfile(Long id, UserUpdateRequest request);
    List<Level> getBadges(Long id);
}
