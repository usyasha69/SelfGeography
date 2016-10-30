package com.example.pk.selfgeography.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pk.selfgeography.R;
import com.example.pk.selfgeography.models.CountryParsingModel;
import com.example.pk.selfgeography.net.Retrofit;
import com.example.pk.selfgeography.utils.DataKeeper;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadingDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_data);

        final DataKeeper dataKeeper = new DataKeeper(this);

        if (!dataKeeper.isDataExists()) {
            Retrofit.getCountries(new Callback<ArrayList<CountryParsingModel>>() {
                @Override
                public void success(ArrayList<CountryParsingModel> countryParsingModels, Response response) {
                    dataKeeper.saveData(countryParsingModels);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

        startActivity(new Intent(LoadingDataActivity.this, AuthorizationActivity.class));
    }
}
