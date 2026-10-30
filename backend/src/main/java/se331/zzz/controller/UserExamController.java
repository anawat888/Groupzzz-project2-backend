package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se331.zzz.dto.ExamSubmitDTO;
import se331.zzz.service.ExamService;
import se331.zzz.user.User;
import se331.zzz.util.AdminMapper;

@RestController
@RequiredArgsConstructor
public class UserExamController {
    final ExamService examService;

    @GetMapping("/user/exam")
    public ResponseEntity<?> getExam(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getExamQuestionDto(examService.getExamQuestions(user)));
    }

    @PostMapping("/user/exam")
    public ResponseEntity<?> submitExam(@AuthenticationPrincipal User user, @RequestBody ExamSubmitDTO submit) {
        return ResponseEntity.ok(examService.submit(user, submit));
    }

    @GetMapping("/user/exam-history")
    public ResponseEntity<?> getHistory(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getExamAttemptDto(examService.getHistory(user)));
    }

    @GetMapping("/user/elearning")
    public ResponseEntity<?> getElearning(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getElearningContentDto(examService.getElearning(user)));
    }
}