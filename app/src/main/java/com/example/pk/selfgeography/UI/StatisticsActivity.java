package com.example.pk.selfgeography.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.adapters.StatisticsRVAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity {
    @BindView(R.id.as_user)
    TextView userName;
    @BindView(R.id.as_statistics_rv)
    RecyclerView statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        ArrayList<String> answers = getIntent().getStringArrayListExtra(QuestionActivity.ANSWERS_KEY);
        String name = getIntent().getStringExtra(QuestionActivity.USER_NAME_KEY);

        StatisticsRVAdapter statisticsRVAdapter = new StatisticsRVAdapter(this, answers);

        statistics.setLayoutManager(new LinearLayoutManager(this));
        statistics.setAdapter(statisticsRVAdapter);

        userName.setText("User: " + name + " statistics");
    }
}
