package com.istiabudi.favoritemovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "fave";


    public static final class FaveColumns implements BaseColumns {

        public static String ID = "id";
        public static String TMDBID = "tmdbid";
        public static String TITLE = "title";
        public static String RELEASEDATE = "release_date";
        public static String DESCRIPTION = "description";
        public static String POSTER = "poster";

    }

    public static final String AUTHORITY = "com.istiabudi.cataloguemoviedb";

    public static Uri contentUri() {
        return new Uri.Builder().scheme( "content" )
                .authority( AUTHORITY )
                .appendEncodedPath( TABLE_NAME )
                .build();
    }
    public static Uri contentUriWithId(int tmdbid) {
        return new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendEncodedPath(TABLE_NAME)
                .appendEncodedPath(String.valueOf(tmdbid))
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex( columnName ) );
    }

}

