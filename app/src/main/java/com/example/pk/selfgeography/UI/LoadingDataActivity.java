package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.loaders.CountriesDataExistLoader;
import com.example.pk.selfgeography.loaders.InsertCountriesDataLoader;
import com.example.pk.selfgeography.models.CountryModel;
import com.example.pk.selfgeography.net.Retrofit;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadingDataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    public static final int COUNTRIES_DATA_EXISTS_LOADER = 0;
    public static final int INSERT_COUNTRIES_DATA_LOADER = 1;

    private ArrayList<CountryModel> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_data);

        getSupportLoaderManager().initLoader(COUNTRIES_DATA_EXISTS_LOADER, null, this).forceLoad();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case COUNTRIES_DATA_EXISTS_LOADER:
                loader = new CountriesDataExistLoader(this);
                break;
            case INSERT_COUNTRIES_DATA_LOADER:
                loader = new InsertCountriesDataLoader(this, countries);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        switch (loader.getId()) {
            case COUNTRIES_DATA_EXISTS_LOADER:
                if (!((boolean) data)) {
                    Retrofit.getCountries(new Callback<ArrayList<CountryModel>>() {
                        @Override
                        public void success(ArrayList<CountryModel> countryModels, Response response) {
                            countries = countryModels;

                            getSupportLoaderManager().initLoader(INSERT_COUNTRIES_DATA_LOADER, null
                                    , LoadingDataActivity.this).forceLoad();
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                } else {
                    startActivity(new Intent(LoadingDataActivity.this, AuthorizationActivity.class));
                }
                break;
            case INSERT_COUNTRIES_DATA_LOADER:
                startActivity(new Intent(LoadingDataActivity.this, AuthorizationActivity.class));
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
