package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.dao.LevelDao;
import se331.zzz.dao.UserDao;
import se331.zzz.dto.UserUpdateRequest;
import se331.zzz.entity.Level;
import se331.zzz.user.User;
import java.util.List;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserDao userDao;
    final LevelDao levelDao;

    @Override
    public User getUser(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    @Transactional
    public User updateProfile(Long id, UserUpdateRequest request) {
        User user = getUser(id);
        if (!user.getEmail().equals(request.getEmail()) && userDao.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already registered");
        }
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setProvince(request.getProvince());
        user.setImage(request.getImage());
        return userDao.save(user);
    }

    @Override
    public List<Level> getBadges(Long id) {
        Long currentLevelId = getUser(id).getLevel().getId();
        return levelDao.getLevels().stream()
                .filter(level -> level.getId() <= currentLevelId)
                .toList();
    }
        @Override
    public Page<User> getUsers(String name, String province, Long levelId, Integer pageSize, Integer page) {
        if (levelId == null) {
            return userDao.getUsers(name, province, pageSize, page);
        }
        return userDao.getUsersByLevel(name, province, levelId, pageSize, page);
    }

}
