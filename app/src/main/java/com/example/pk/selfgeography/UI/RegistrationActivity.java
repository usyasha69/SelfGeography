package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.loaders.InsertUserInformationDataLoader;
import com.example.pk.selfgeography.loaders.UserInformationDataExistLoader;
import com.example.pk.selfgeography.loaders.ValidateUserInformationFieldsLoader;
import com.example.pk.selfgeography.models.UserInformationModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    public static final int USER_INFORMATION_DATA_IS_EXIST_LOADER = 0;
    public static final int INSERT_USER_INFORMATION_DATA_LOADER = 1;
    public static final int VALIDATE_USER_INFORMATION_FIELDS_LOADER = 2;

    @BindView(R.id.ar_name)
    TextView name;
    @BindView(R.id.ar_password)
    TextView password;
    @BindView(R.id.ar_country)
    TextView country;

    private String putName;
    private String putPassword;
    private String putCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ar_registration)
    public void registration() {

        putName = name.getText().toString();
        putPassword = password.getText().toString();
        putCountry = country.getText().toString();

        getSupportLoaderManager().initLoader(USER_INFORMATION_DATA_IS_EXIST_LOADER, null, this).forceLoad();
    }

    @OnClick(R.id.ar_clear)
    public void clear() {
        name.setText("");
        password.setText("");
        country.setText("");
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case USER_INFORMATION_DATA_IS_EXIST_LOADER:
                loader = new UserInformationDataExistLoader(this, putName);
                break;
            case INSERT_USER_INFORMATION_DATA_LOADER:
                loader = new InsertUserInformationDataLoader(this
                        , new UserInformationModel(putName, putPassword, putCountry));
                break;
            case VALIDATE_USER_INFORMATION_FIELDS_LOADER:
                loader = new ValidateUserInformationFieldsLoader(this, putName, putPassword, putCountry);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        switch (loader.getId()) {
            case USER_INFORMATION_DATA_IS_EXIST_LOADER:
                if ((boolean) data) {
                    Toast.makeText(this, "User with put name is exists!", Toast.LENGTH_SHORT).show();
                    break;
                }

                getSupportLoaderManager().initLoader(VALIDATE_USER_INFORMATION_FIELDS_LOADER, null, this).forceLoad();
                break;
            case INSERT_USER_INFORMATION_DATA_LOADER:
                startActivity(new Intent(this, AuthorizationActivity.class));
                break;
            case VALIDATE_USER_INFORMATION_FIELDS_LOADER:
                if ((boolean) data) {

                    getSupportLoaderManager().initLoader(INSERT_USER_INFORMATION_DATA_LOADER, null, this).forceLoad();
                } else {
                    Toast.makeText(this, "Please, enter right fields!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
