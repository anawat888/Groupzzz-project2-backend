package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.zzz.dao.UserDao;
import se331.zzz.entity.Level;
import se331.zzz.user.User;

@Service
@RequiredArgsConstructor
public class UserLevelPortImpl implements UserLevelPort {
    final UserDao userDao;

    @Override
    public Level getCurrentLevel(Long userId) {
        return getUser(userId).getLevel();
    }

    @Override
    public void setLevel(Long userId, Level level) {
        User user = getUser(userId);
        user.setLevel(level);
        userDao.save(user);
    }

    private User getUser(Long userId) {
        return userDao.findById(userId).orElseThrow();
    }
}
