package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.entity.Question;
import se331.zzz.service.QuestionService;
import se331.zzz.util.AdminMapper;

@RestController
@RequiredArgsConstructor
public class AdminQuestionController {
    final QuestionService questionService;
    @GetMapping("/admin/questions")
    public ResponseEntity<?> getQuestions(@RequestParam(value = "level", required = false) Long levelId) {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getQuestionDto(questionService.getQuestions(levelId)));
    }
    @PostMapping("/admin/questions")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        checkQuestion(question);
        Question output = questionService.save(question);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AdminMapper.INSTANCE.getQuestionDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given level id is not found");
        }
    }
    @PutMapping("/admin/questions/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
        checkQuestion(question);
        Question output = questionService.update(id, question);
        if (output != null) {
            return ResponseEntity.ok(AdminMapper.INSTANCE.getQuestionDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given question id or level id is not found");
        }
    }
    @DeleteMapping("/admin/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id) {
        Question output = questionService.delete(id);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    private void checkQuestion(Question question) {
        if (question.getLevel() == null || question.getLevel().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The level is required");
        }
        if (question.getChoices() == null || !question.getChoices().contains(question.getCorrectAnswer())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The correct answer must be one of the choices");
        }
    }
}
