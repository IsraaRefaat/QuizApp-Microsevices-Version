package com.esraa.questionservice.controller;


import com.esraa.questionservice.model.Question;
import com.esraa.questionservice.model.QuestionWrapper;
import com.esraa.questionservice.model.Response;
import com.esraa.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping("allQuestions/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.searchByCategory(category);
    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
       return questionService.save(question);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
         return questionService.save(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.delete(id);
    }


    // generate  -> generate questions for the quiz
    // getQuestions (questionId) -> give quiz service the questions it needs depends on the IDs of the questions
    // getScore

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (@RequestParam String category , @RequestParam Integer numberOfQuestions) {
        return questionService.generateQuestionsForQuiz(category , numberOfQuestions);
    }


    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsIDs) {
        return questionService.getQuestions(questionsIDs);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }



}
