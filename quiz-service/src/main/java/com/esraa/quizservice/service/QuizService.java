package com.esraa.quizservice.service;



import com.esraa.quizservice.dao.QuizDao;
import com.esraa.quizservice.feign.QuizInterface;
import com.esraa.quizservice.model.QuestionWrapper;
import com.esraa.quizservice.model.Quiz;
import com.esraa.quizservice.model.Response;
import com.esraa.quizservice.model.QuizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> create(String category, int numQ, String title) {

        List<Integer> questionList = quizInterface.generateQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIDs(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionFromDB = quiz.get().getQuestionsIDs();
        List<QuestionWrapper> questionForUser = quizInterface.getQuestions(questionFromDB).getBody();

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {


        ResponseEntity<Integer> score = quizInterface.getScore(response);

        return score;


    }

  }
