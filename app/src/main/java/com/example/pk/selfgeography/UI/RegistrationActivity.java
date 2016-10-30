package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.models.UserInformationModel;
import com.example.pk.selfgeography.utils.DataKeeper;
import com.example.pk.selfgeography.utils.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.ar_name)
    TextView name;
    @BindView(R.id.ar_password)
    TextView password;
    @BindView(R.id.ar_country)
    TextView country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ar_registration)
    public void registration() {
        Validator validator = new Validator(this);
        DataKeeper dataKeeper = new DataKeeper(this);

        String putName = name.getText().toString();
        String putPassword = password.getText().toString();
        String putCountry = country.getText().toString();

        if (dataKeeper.isExistUser(putName)) {
            Toast.makeText(this, "User with put name is exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!putName.isEmpty() && validator.isValidatePassword(putPassword)
                && validator.isValidateCountry(putCountry)) {

            dataKeeper.saveUserInformation(putName
                    , new UserInformationModel(putName, putPassword, putCountry));
            startActivity(new Intent(this, AuthorizationActivity.class));
        } else {
            Toast.makeText(this, "Please, enter right fields!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ar_clear)
    public void clear() {
        name.setText("");
        password.setText("");
        country.setText("");
    }
}
