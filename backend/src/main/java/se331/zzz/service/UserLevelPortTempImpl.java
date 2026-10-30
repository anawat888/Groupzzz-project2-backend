package se331.zzz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.zzz.entity.Level;
import se331.zzz.repository.LevelRepository;

// Marked: this is temp file for test the user
@Service
@RequiredArgsConstructor
public class UserLevelPortTempImpl implements UserLevelPort {
    final LevelRepository levelRepository;

    @Override
    public Level getCurrentLevel(Long userId) {
        // restore Level 1 every time (level at lowest id)
        return levelRepository.findAll().stream()
                .min((a, b) -> a.getId().compareTo(b.getId()))
                .orElseThrow();
    }

    @Override
    public void setLevel(Long userId, Level level) {
        // Marked TEMP: Don't have User to save
    }
}