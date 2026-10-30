package se331.zzz.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.zzz.user.User;
import se331.zzz.user.UserRepository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class UserDaoDbImpl implements UserDao {
    final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
