package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.loaders.GetUserInformationDataLoader;
import com.example.pk.selfgeography.loaders.UserInformationDataExistLoader;
import com.example.pk.selfgeography.models.UserInformationModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorizationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    public static final String USER_INFORMATION = "user information";

    public static final int USER_INFORMATION_DATA_IS_EXIST_LOADER = 0;
    public static final int GET_USER_INFORMATION_DATA_LOADER = 1;

    private String putName;

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
        putName = name.getText().toString();
        String putPassword = password.getText().toString();

        if (!putName.isEmpty() && !putPassword.isEmpty()) {

            getSupportLoaderManager().initLoader(USER_INFORMATION_DATA_IS_EXIST_LOADER, null, this).forceLoad();

        } else {
            Toast.makeText(this, "Fields is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.aa_clear)
    public void clear() {
        name.setText("");
        password.setText("");
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case USER_INFORMATION_DATA_IS_EXIST_LOADER:
                loader = new UserInformationDataExistLoader(this, putName);
                break;
            case GET_USER_INFORMATION_DATA_LOADER:
                loader = new GetUserInformationDataLoader(this, putName);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        switch (loader.getId()) {
            case USER_INFORMATION_DATA_IS_EXIST_LOADER:
                if ((boolean) data) {
                    getSupportLoaderManager().initLoader(GET_USER_INFORMATION_DATA_LOADER, null, this).forceLoad();
                } else {
                    Toast.makeText(this, "User is not exists!", Toast.LENGTH_SHORT).show();
                }
                break;
            case GET_USER_INFORMATION_DATA_LOADER:
                startActivity(new Intent(this, QuestionActivity.class)
                        .putExtra(USER_INFORMATION, (UserInformationModel) data));
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
