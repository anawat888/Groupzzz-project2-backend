package se331.zzz.dao;

import se331.zzz.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface UserDao {
    Optional<User> findById(Long id);
    User save(User user);
    boolean existsByEmail(String email);
    Page<User> getUsers(String name, String province, Integer pageSize, Integer page);
    Page<User> getUsersByLevel(String name, String province, Long levelId, Integer pageSize, Integer page);
}
