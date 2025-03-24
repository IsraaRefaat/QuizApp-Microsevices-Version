package com.esraa.quizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDTO {

    private String title;
    private int numQ;
    private String category;

}
