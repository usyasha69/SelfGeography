package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.models.QuestionModel;
import com.example.pk.selfgeography.models.UserInformationModel;
import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.utils.QuestionManager;

import java.util.ArrayList;
import java.util.PriorityQueue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {
    public static final String ANSWERS_KEY = "answers";
    public static final String USER_NAME_KEY = "user name";

    @BindView(R.id.aq_question)
    TextView question;
    @BindView(R.id.aq_variant_a)
    RadioButton variantA;
    @BindView(R.id.aq_variant_b)
    RadioButton variantB;
    @BindView(R.id.aq_variant_c)
    RadioButton variantC;
    @BindView(R.id.aq_variant_d)
    RadioButton variantD;

    private UserInformationModel userInformationModel;
    private QuestionManager questionManager;
    private DatabaseManager databaseManager;

    private PriorityQueue<QuestionModel> questions;
    private QuestionModel questionModel;
    private ArrayList<String> answers;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        userInformationModel = getIntent().getParcelableExtra(AuthorizationActivity.USER_INFORMATION);
        databaseManager = new DatabaseManager(this);
        questionManager = new QuestionManager(databaseManager);

        questions = questionManager.createQuestions();
        answers = new ArrayList<>();

        fillingFields();
    }

    private void fillingFields() {
        questionModel = questions.poll();

        question.setText(questionModel.question);
        variantA.setText(String.format("A: %s", questionModel.answers.get(0)));
        variantB.setText(String.format("B: %s", questionModel.answers.get(1)));
        variantC.setText(String.format("C: %s", questionModel.answers.get(2)));
        variantD.setText(String.format("D: %s", questionModel.answers.get(3)));
    }

    @OnClick({R.id.aq_variant_a, R.id.aq_variant_b, R.id.aq_variant_c, R.id.aq_variant_d})
    public void chooseVariantOfAnswer(View view) {
        switch (view.getId()) {
            case R.id.aq_variant_a:
                answer = questionModel.answers.get(0);
                break;
            case R.id.aq_variant_b:
                answer = questionModel.answers.get(1);
                break;
            case R.id.aq_variant_c:
                answer = questionModel.answers.get(2);
                break;
            case R.id.aq_variant_d:
                answer = questionModel.answers.get(3);
                break;
            default:
                answer = "";
                break;
        }
    }

    @OnClick(R.id.aq_answer_the_question)
    public void answerTheQuestion() {
        answers.add("Question: " + questionModel.question + "; your answer: " + answer
                + "; right answer: " + questionModel.rightAnswer);

        if (questions.size() != 0) {
            fillingFields();
        } else {
            startActivity(new Intent(this, StatisticsActivity.class)
                    .putExtra(ANSWERS_KEY, answers)
                    .putExtra(USER_NAME_KEY, userInformationModel.name));
        }
    }
}
