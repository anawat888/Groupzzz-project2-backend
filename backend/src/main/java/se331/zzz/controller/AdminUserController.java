package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.zzz.service.UserService;
import se331.zzz.user.User;
import se331.zzz.util.UserMapper;

@RestController
@RequiredArgsConstructor
public class AdminUserController {
    final UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<?> getUsers(@RequestParam(value = "_limit", required = false) Integer perPage,@RequestParam(value = "_page", required = false) Integer page,@RequestParam(value = "name", required = false) String name,@RequestParam(value = "province", required = false) String province,@RequestParam(value = "level", required = false) Long levelId) {
        perPage = perPage == null ? 5 : perPage;
        page = page == null ? 1 : page;
        name = name == null ? "" : name;
        province = province == null ? "" : province;
        Page<User> pageOutput = userService.getUsers(name, province, levelId, perPage, page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return ResponseEntity.ok().headers(responseHeaders).body(UserMapper.INSTANCE.getUserDto(pageOutput.getContent()));
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(UserMapper.INSTANCE.getUserDto(userService.getUser(id)));
    }
}
