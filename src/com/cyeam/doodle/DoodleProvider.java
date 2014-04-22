package com.cyeam.doodle;

import com.cyeam.doodle.ui.DoodleData;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DoodleProvider extends ContentProvider {
	public static final Uri CONTENT_URL = Uri.parse("content://com.cyeam.doodle.doodleprovider");

	DoodleData doodleData;
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		doodleData.update(values);
		return null;
	}

	@Override
	public boolean onCreate() {
		doodleData = new DoodleData(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		return doodleData.getDoodleCursor();
	}

	@Override
	public int update(Uri uri, ContentValues values, String arg2, String[] arg3) {
		doodleData.update(values);
		return 0;
	}

}
