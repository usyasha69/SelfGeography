package com.example.pk.selfgeography.database;

import android.net.Uri;

public interface Table {
    String getName();

    String getColumnId();

    Uri getItemUri(long id);
}
