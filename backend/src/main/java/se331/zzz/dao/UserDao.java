package se331.zzz.dao;

import se331.zzz.user.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);
    User save(User user);
    boolean existsByEmail(String email);
}
