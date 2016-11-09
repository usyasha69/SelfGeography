package com.example.pk.selfgeography.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.pk.selfgeography.BuildConfig;

public class SelfGeographyContentProvider extends ContentProvider {
    private static final String AUTHORITY = BuildConfig.AUTHORITY;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final CountryTable countryTable = new CountryTable();
    private static final UserInformationTable userInformationTable = new UserInformationTable();

    static {
        URI_MATCHER.addURI(AUTHORITY, CountryTable.NAME, MappedUri.COUNTRIES);
        URI_MATCHER.addURI(AUTHORITY, CountryTable.NAME + "/#", MappedUri.COUNTRIES_ITEM);
        URI_MATCHER.addURI(AUTHORITY, UserInformationTable.NAME, MappedUri.USER_INFORMATION);
        URI_MATCHER.addURI(AUTHORITY, UserInformationTable.NAME + "/#", MappedUri.USER_INFORMATION_ITEM);
    }

    private SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        sqLiteDatabase = new DatabaseHelper(getContext()).getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Table table = getTable(uri);

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(table.getName());

        if (isItemUri(uri)) {
            builder.appendWhere(table.getColumnId() + "=" + uri.getLastPathSegment());
        }

        Cursor cursor = builder.query(sqLiteDatabase, strings, s, strings1, null, null, s1);
        setNotificationUri(cursor, uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case MappedUri.COUNTRIES:
                return countryTable.getContentType();
            case MappedUri.USER_INFORMATION:
                return userInformationTable.getContentType();

            default:
                throw new IllegalArgumentException("Uri: " + uri + " is not supported");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        Table table = getTable(uri);

        long rowId = sqLiteDatabase.insert(table.getName(), null, contentValues);

        Uri resultUri = table.getItemUri(rowId);
        notifyChange(uri);

        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        Table table = getTable(uri);

        if (isItemUri(uri) && TextUtils.isEmpty(s)) {
            s = table.getColumnId() + "=" + uri.getLastPathSegment();
        }

        int deletedRowNum = sqLiteDatabase.delete(table.getName(), s, strings);
        notifyChange(uri);

        return deletedRowNum;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        Table table = getTable(uri);

        if (isItemUri(uri) && TextUtils.isEmpty(s)) {
            s = table.getColumnId() + "=" + uri.getLastPathSegment();
        }

        int updatedRowsNum = sqLiteDatabase.update(table.getName(), contentValues, s, strings);
        //insert if not update

        if (updatedRowsNum == 0) {
            sqLiteDatabase.insert(table.getName(), null, contentValues);
            updatedRowsNum = 1;
        }

        notifyChange(uri);
        return updatedRowsNum;
    }

    private Table getTable(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case MappedUri.COUNTRIES:
            case MappedUri.COUNTRIES_ITEM:
                return countryTable;
            case MappedUri.USER_INFORMATION:
            case MappedUri.USER_INFORMATION_ITEM:
                return userInformationTable;

            default:
                throw new IllegalArgumentException("Uri: " + uri + " is not supported");
        }
    }

    private boolean isItemUri(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case MappedUri.COUNTRIES_ITEM:
                return true;
            case MappedUri.USER_INFORMATION_ITEM:
                return true;
        }
        return false;
    }


    private void setNotificationUri(Cursor cursor, Uri uri) {
        Context context = getContext();
        if (context != null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }
    }

    private void notifyChange(Uri uri) {
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
    }

    public interface MappedUri {
        int COUNTRIES = 0;
        int COUNTRIES_ITEM = 1;
        int USER_INFORMATION = 2;
        int USER_INFORMATION_ITEM = 3;
    }
}
