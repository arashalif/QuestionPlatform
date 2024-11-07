package com.arshalif.questionplatform;

import com.arshalif.questionplatform.dto.QuestionRequest;
import com.arshalif.questionplatform.dto.QuestionResponse;
import com.arshalif.questionplatform.dto.VoteRequest;
import com.arshalif.questionplatform.dto.VoteResponse;
import com.arshalif.questionplatform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/insert")
    public Map<String, Long> insertQuestion(@RequestBody QuestionRequest request) {
        Long questionId = questionService.insertQuestion(request);
        return Map.of("questionId", questionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {
        QuestionResponse question = questionService.getQuestion(id);
        if (question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
        return ResponseEntity.ok(question);

    }

    @PostMapping("/vote")
    public VoteResponse vote(@RequestBody VoteRequest request) {
        return questionService.vote(request);
    }

}
