package com.example.pk.selfgeography.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pk.selfgeography.models.CountryParsingModel;
import com.example.pk.selfgeography.models.UserInformationModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataKeeper {
    public static final String DATA_FILE_PATH = "countries data";
    public static final String USER_INFORMATION_SHARED_PREFERENCES_KEY = "user information shared preferences";
    public static final String USER_INFORMATION_SHARED_PREFERENCES_DEFAULT_VALUE = "Unknown";

    private Context context;

    public DataKeeper(Context context) {
        this.context = context;
    }

    /**
     * This method save countries data from API query to file in internal storage.
     *
     * @param countryParsingModels - array list with country parsing models
     */
    public void saveData(ArrayList<CountryParsingModel> countryParsingModels) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(DATA_FILE_PATH, Context.MODE_PRIVATE);
            fileOutputStream.write(new Gson().toJson(countryParsingModels
                    , new TypeToken<ArrayList<CountryParsingModel>>() {
                    }.getType()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method read data from file in internal storage.
     *
     * @return - array list with country parsing model
     */
    public ArrayList<CountryParsingModel> readData() {
        ArrayList<CountryParsingModel> countryParsingModels = null;

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = context.openFileInput("countries data");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuilder text = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
            }

            countryParsingModels = new Gson().fromJson(text.toString()
                    , new TypeToken<ArrayList<CountryParsingModel>>() {
                    }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return countryParsingModels;
    }

    /**
     * This method checked if file with data exist in internal storage.
     *
     * @return - is exists
     */
    public boolean isDataExists() {
        String path = context.getFilesDir().getAbsolutePath() + "/" + DATA_FILE_PATH;
        File data = new File(path);

        return data.exists();
    }

    /**
     * This method save user information to shared preferences.
     *
     * @param name                 - key of shared preferences item
     * @param userInformationModel - value of shared preferences item
     */
    public void saveUserInformation(String name, UserInformationModel userInformationModel) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(USER_INFORMATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, new Gson().toJson(userInformationModel, UserInformationModel.class));

        editor.apply();
    }

    /**
     * This method read information from shared preferences.
     *
     * @param name - user name
     * @return - user information model
     */
    public UserInformationModel readUserInformation(String name) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(USER_INFORMATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        return new Gson().fromJson(sharedPreferences.getString(name
                , USER_INFORMATION_SHARED_PREFERENCES_DEFAULT_VALUE), UserInformationModel.class);
    }

    /**
     * This method return array list with all users.
     *
     * @return - array list with all users
     */
    public ArrayList<UserInformationModel> getAllUsers() {
        ArrayList<UserInformationModel> users = new ArrayList<>();

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(USER_INFORMATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        for (String s : sharedPreferences.getAll().keySet()) {
            users.add(new Gson().fromJson(sharedPreferences.getString(s
                    , USER_INFORMATION_SHARED_PREFERENCES_DEFAULT_VALUE), UserInformationModel.class));
        }

        return users;
    }

    /**
     * This method checked if user is registration.
     *
     * @param name - name of user
     * @return - is exists
     */
    public boolean isExistUser(String name) {
        boolean isExistName = false;

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(USER_INFORMATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        for (String s : sharedPreferences.getAll().keySet()) {
            if (s.equals(name)) {
                isExistName = true;
            }
        }

        return isExistName;
    }
}
