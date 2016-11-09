package com.example.pk.selfgeography.utils;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.CountryModel;
import com.example.pk.selfgeography.models.QuestionModel;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class QuestionManager {
    private DatabaseManager databaseManager;

    public QuestionManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public PriorityQueue<QuestionModel> createQuestions() {
        PriorityQueue<QuestionModel> questions = new PriorityQueue<>();
        ArrayList<CountryModel> countries = databaseManager.readCountriesData();

        final int QUESTIONS_COUNTRY_CAPITAL = 4;
        final int QUESTIONS_COUNTRY_POPULATION = 3;
        final int QUESTION_COUNTRY_AREA = 3;
        final int QUESTION_COUNTRY_REGION = 3;

        //create question about country capital
        for (int i = 0; i < QUESTIONS_COUNTRY_CAPITAL; i++) {
            questions.add(createCapitalQuestion(countries));
        }

        //create question about country population
        for (int i = 0; i < QUESTIONS_COUNTRY_POPULATION; i++) {
            questions.add(createPopulationQuestion(countries));
        }

        //create question about country area
        for (int i = 0; i < QUESTION_COUNTRY_AREA; i++) {
            questions.add(createAreaQuestion(countries));
        }

        //create question about country region
        for (int i = 0; i < QUESTION_COUNTRY_REGION; i++) {
            questions.add(createRegionQuestion(countries));
        }

        return questions;
    }

    private QuestionModel createCapitalQuestion(ArrayList<CountryModel> countries) {
        final int RANDOM_COUNTRY = (int) (Math.random() * countries.size());
        final int ANSWER_VARIANTS = 3;

        QuestionModel questionModel = new QuestionModel();
        questionModel.question = "Choose the capital of " + countries.get(RANDOM_COUNTRY).name + ":";
        questionModel.rightAnswer = countries.get(RANDOM_COUNTRY).capital;

        ArrayList<String> answers = new ArrayList<>();
        answers.add(questionModel.rightAnswer);

        for (int i = 0; i < ANSWER_VARIANTS; i++) {
            final int RANDOM = (int) (Math.random() * countries.size());

            if (RANDOM != RANDOM_COUNTRY) {
                answers.add(countries.get(RANDOM).capital);
            } else {
                i--;
            }
        }

        questionModel.answers = answers;

        return questionModel;
    }

    private QuestionModel createPopulationQuestion(ArrayList<CountryModel> countries) {
        final int RANDOM_COUNTRY = (int) (Math.random() * countries.size());
        final int ANSWER_VARIANTS = 3;

        QuestionModel questionModel = new QuestionModel();
        questionModel.question = "Choose the population of " + countries.get(RANDOM_COUNTRY).name + ":";
        questionModel.rightAnswer = String.valueOf(countries.get(RANDOM_COUNTRY).population);

        ArrayList<String> answers = new ArrayList<>();
        answers.add(questionModel.rightAnswer);

        for (int i = 0; i < ANSWER_VARIANTS; i++) {
            final int RANDOM = (int) (Math.random() * countries.size());

            if (RANDOM != RANDOM_COUNTRY) {
                answers.add(String.valueOf(countries.get(RANDOM).population));
            } else {
                i--;
            }
        }

        questionModel.answers = answers;

        return questionModel;
    }

    private QuestionModel createAreaQuestion(ArrayList<CountryModel> countries) {
        final int RANDOM_COUNTRY = (int) (Math.random() * countries.size());
        final int ANSWER_VARIANTS = 3;

        QuestionModel questionModel = new QuestionModel();
        questionModel.question = "Choose the area of " + countries.get(RANDOM_COUNTRY).name + ":";
        questionModel.rightAnswer = String.valueOf(countries.get(RANDOM_COUNTRY).area);

        ArrayList<String> answers = new ArrayList<>();
        answers.add(questionModel.rightAnswer);

        for (int i = 0; i < ANSWER_VARIANTS; i++) {
            final int RANDOM = (int) (Math.random() * countries.size());

            if (RANDOM != RANDOM_COUNTRY) {
                answers.add(String.valueOf(countries.get(RANDOM).area));
            } else {
                i--;
            }
        }

        questionModel.answers = answers;

        return questionModel;
    }

    private QuestionModel createRegionQuestion(ArrayList<CountryModel> countries) {
        final int RANDOM_COUNTRY = (int) (Math.random() * countries.size());
        final int ANSWER_VARIANTS = 3;

        QuestionModel questionModel = new QuestionModel();
        questionModel.question = "Choose the region of " + countries.get(RANDOM_COUNTRY).name + ":";
        questionModel.rightAnswer = countries.get(RANDOM_COUNTRY).region;

        ArrayList<String> answers = new ArrayList<>();
        answers.add(questionModel.rightAnswer);

        for (int i = 0; i < ANSWER_VARIANTS; i++) {
            final int RANDOM = (int) (Math.random() * countries.size());

            if (RANDOM != RANDOM_COUNTRY) {
                answers.add(countries.get(RANDOM).region);
            } else {
                i--;
            }
        }

        questionModel.answers = answers;

        return questionModel;
    }
}
