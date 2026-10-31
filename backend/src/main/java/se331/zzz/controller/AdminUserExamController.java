package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se331.zzz.service.ExamService;
import se331.zzz.util.AdminMapper;

@RestController
@RequiredArgsConstructor
public class AdminUserExamController {
    final ExamService examService;

    @GetMapping("/admin/users/{id}/exam-history")
    public ResponseEntity<?> getUserExamHistory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getExamAttemptDto(examService.getHistoryByUserId(id)));
    }
}