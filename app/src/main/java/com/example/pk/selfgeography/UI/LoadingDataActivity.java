package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.loaders.CountriesDataExistLoader;
import com.example.pk.selfgeography.loaders.InsertCountriesDataLoader;

public class LoadingDataActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    public static final int COUNTRIES_DATA_EXISTS_LOADER = 0;
    public static final int INSERT_COUNTRIES_DATA_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_data);

        getSupportLoaderManager().initLoader(COUNTRIES_DATA_EXISTS_LOADER, null, this);

        startActivity(new Intent(LoadingDataActivity.this, AuthorizationActivity.class));
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case COUNTRIES_DATA_EXISTS_LOADER:
                loader = new CountriesDataExistLoader(this);
                break;
            case INSERT_COUNTRIES_DATA_LOADER:
                loader = new InsertCountriesDataLoader(this);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        switch (loader.getId()) {
            case COUNTRIES_DATA_EXISTS_LOADER:
                if (!(boolean) data) {
                    getSupportLoaderManager().initLoader(INSERT_COUNTRIES_DATA_LOADER, null, this);
                }
                break;
            case INSERT_COUNTRIES_DATA_LOADER:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
