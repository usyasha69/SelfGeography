package com.example.pk.selfgeography.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class QuestionModel implements Comparable<QuestionModel>{
    public String question;
    public String rightAnswer;
    public ArrayList<String> answers;

    public QuestionModel() {
    }

    public QuestionModel(String question, String rightAnswer, ArrayList<String> answers) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", answers=" + answers +
                '}';
    }

    @Override
    public int compareTo(@NonNull QuestionModel questionModel) {
        int result = questionModel.question.compareTo(question);

        if (result != 0) {
            return result;
        }

        result = questionModel.rightAnswer.compareTo(rightAnswer);

        if (result != 0) {
            return result;
        }


        for (int i = 0; i < questionModel.answers.size(); i++) {
            result += questionModel.answers.get(i).compareTo(answers.get(i));
        }

        return result;
    }
}
