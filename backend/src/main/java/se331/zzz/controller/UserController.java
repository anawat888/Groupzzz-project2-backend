package se331.zzz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se331.zzz.dto.LevelDTO;
import se331.zzz.dto.LevelSummaryDTO;
import se331.zzz.dto.UserDTO;
import se331.zzz.dto.UserUpdateRequest;
import se331.zzz.service.UserService;
import se331.zzz.user.User;
import se331.zzz.util.AdminMapper;
import se331.zzz.util.UserMapper;
import java.util.List;

@RestController
@RequestMapping("/user/me")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserMapper.INSTANCE.getUserDto(userService.getUser(user.getId())));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateProfile(@AuthenticationPrincipal User user,
                                                 @Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(UserMapper.INSTANCE.getUserDto(userService.updateProfile(user.getId(), request)));
    }

    @GetMapping("/level")
    public ResponseEntity<LevelDTO> getLevel(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getLevelDto(userService.getUser(user.getId()).getLevel()));
    }

    @GetMapping("/badges")
    public ResponseEntity<List<LevelSummaryDTO>> getBadges(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserMapper.INSTANCE.getBadgeDto(userService.getBadges(user.getId())));
    }
}
