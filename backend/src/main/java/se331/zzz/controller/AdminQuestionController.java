package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.dto.QuestionRequestDTO;
import se331.zzz.entity.Level;
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
    public ResponseEntity<?> addQuestion(@RequestBody QuestionRequestDTO request) {
        checkQuestion(request);
        Question output = questionService.save(toEntity(request));
        if (output != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AdminMapper.INSTANCE.getQuestionDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given level id is not found");
        }
    }

    @PutMapping("/admin/questions/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, @RequestBody QuestionRequestDTO request) {
        checkQuestion(request);
        Question output = questionService.update(id, toEntity(request));
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

    private void checkQuestion(QuestionRequestDTO request) {
        if (request.getLevelId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The level is required");
        }
        if (request.getChoices() == null || !request.getChoices().contains(request.getCorrectAnswer())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The correct answer must be one of the choices");
        }
    }

    // Marked: Convert the request DTO to an entity, the service only needs level.id
    private Question toEntity(QuestionRequestDTO request) {
        return Question.builder()
                .question(request.getQuestion())
                .choices(request.getChoices())
                .correctAnswer(request.getCorrectAnswer())
                .level(Level.builder().id(request.getLevelId()).build())
                .build();
    }
}