package se331.zzz.service;

import se331.zzz.entity.Level;

public interface UserLevelPort {
    Level getCurrentLevel(Long userId);
    void setLevel(Long userId, Level level);
}