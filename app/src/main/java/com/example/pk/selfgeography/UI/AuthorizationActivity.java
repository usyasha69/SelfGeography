package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.models.CountryParsingModel;
import com.example.pk.selfgeography.utils.DataKeeper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorizationActivity extends AppCompatActivity {
    public static final String USER_INFORMATION = "user information";

    @BindView(R.id.aa_name)
    TextView name;
    @BindView(R.id.aa_password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.aa_registration)
    public void registration() {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    @OnClick(R.id.aa_enter)
    public void enter() {
        String putName = name.getText().toString();
        String putPassword = password.getText().toString();

        DataKeeper dataKeeper = new DataKeeper(this);

        if (!putName.isEmpty() && !putPassword.isEmpty()) {

            if (dataKeeper.isExistUser(putName)) {
                startActivity(new Intent(this, QuestionActivity.class)
                        .putExtra(USER_INFORMATION, dataKeeper.readUserInformation(putName)));
            } else {
                Toast.makeText(this, "User is not exists!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Fields is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.aa_clear)
    public void clear() {
        name.setText("");
        password.setText("");
    }
}
