package com.example.pk.selfgeography.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.pk.selfgeography.BuildConfig;

public class UserInformationTable implements Table {
    public static final String NAME = "user_information";

    public static final String CREATE = "CREATE TABLE "
            + NAME
            + "("
            + Columns._ID
            + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Columns.NAME
            + " TEXT, "
            + Columns.PASSWORD
            + " TEXT, "
            + Columns.COUNTRY
            + " TEXT "
            + ");";

    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;

    private static final String MIME_TYPE = "vnd." + BuildConfig.AUTHORITY + "_" + NAME;

    private static final String CONTENT_PATH = "content://" + BuildConfig.AUTHORITY + "/" + NAME;

    public static final Uri CONTENT_URI = Uri.parse(CONTENT_PATH);

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getColumnId() {
        return Columns._ID;
    }

    @Override
    public Uri getItemUri(long id) {
        return Uri.parse(CONTENT_PATH + "/" + id);
    }

    public String getContentItemType() {
        return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + MIME_TYPE;
    }

    public String getContentType() {
        return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + MIME_TYPE;
    }

    public interface Columns extends BaseColumns {
        String NAME = "name";
        String PASSWORD = "password";
        String COUNTRY = "country";
    }

    public interface ColumnIndices {
        int _ID = 0;
        int NAME = 1;
        int PASSWORD = 2;
        int COUNTRY = 3;
    }
}
