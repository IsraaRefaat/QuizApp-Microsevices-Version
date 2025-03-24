package com.esraa.questionservice.service;

import com.esraa.questionservice.dao.QuestionDao;
import com.esraa.questionservice.model.Question;
import com.esraa.questionservice.model.QuestionWrapper;
import com.esraa.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            List<Question> questions = questionDao.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Question>> searchByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategoryContaining(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> save(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question Saved Successfully", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (String category, Integer numberOfQuestions) {

        List<Integer> questionList = questionDao.findRandomQuestionsByCategory(category,numberOfQuestions);


        return new ResponseEntity<>(questionList, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionsIDs) {

        List<Question> questionFromDB = questionDao.findAllById(questionsIDs);
        List<QuestionWrapper> questionForQuiz = new ArrayList<>();
        for (Question q : questionFromDB) {

            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());

            questionForQuiz.add(qw);
        }


        return new ResponseEntity<>(questionForQuiz, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;

        for (Response r : responses) {
            Question question= questionDao.findById(r.getId()).get();
            if(r.getUserResponse().equals(question.getRightAnswer()))
                score++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);

    }
}
