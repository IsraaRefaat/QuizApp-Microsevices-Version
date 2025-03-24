package com.esraa.quizservice.controller;


import com.esraa.quizservice.model.QuestionWrapper;
import com.esraa.quizservice.model.QuizDTO;
import com.esraa.quizservice.model.Response;
import com.esraa.quizservice.service.QuizService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.create(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id , @RequestBody List<Response> response) {
        return quizService.calculateResult(id,response);
    }

}
