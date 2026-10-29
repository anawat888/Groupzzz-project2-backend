package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.zzz.dto.ExamSubmitDTO;
import se331.zzz.service.ExamService;
import se331.zzz.util.AdminMapper;

@RestController
@RequiredArgsConstructor
public class UserExamController {
    final ExamService examService;

    // Marked: change usedId to pull from JWT security when have it naja
    @GetMapping("/user/{userId}/exam")
    public ResponseEntity<?> getExam(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getExamQuestionDto(examService.getExamQuestions(userId)));
    }

    @PostMapping("/user/{userId}/exam")
    public ResponseEntity<?> submitExam(@PathVariable("userId") Long userId, @RequestBody ExamSubmitDTO submit) {
        return ResponseEntity.ok(examService.submit(userId, submit));
    }

    @GetMapping("/user/{userId}/exam-history")
    public ResponseEntity<?> getHistory(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getExamAttemptDto(examService.getHistory(userId)));
    }

    @GetMapping("/user/{userId}/elearning")
    public ResponseEntity<?> getElearning(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getElearningContentDto(examService.getElearning(userId)));
    }
}