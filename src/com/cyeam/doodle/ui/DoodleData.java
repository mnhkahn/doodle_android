package com.cyeam.doodle.ui;

import com.cyeam.doodle.model.Doodle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DoodleData {

	static final int VERSION = 1;
	static final String DATABASE = "doodle.db";
	static final String TABLE = "doodle";
	
	public static final String C_ID = "_id";
	public static final String C_CREATED_AT = "created_at";
	public static final String C_BITMAP = "bitmap";
	public static final String C_DOODLE = "doodle";
	public static final String C_TITLE = "title";
	
	class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE + " (" + C_ID + " int primary key, " + C_CREATED_AT + " int, " + C_BITMAP + " BLOB, " + C_DOODLE + " text, " + C_TITLE + " text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table " + TABLE);
		}
		
	}
	
	final DbHelper dbHelper;
	
	public DoodleData(Context context) {
		this.dbHelper = new DbHelper(context);
	}
	
	public void close() {
		this.dbHelper.close();
	}
	
	public void update(ContentValues values) {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		db.execSQL("delete from " + TABLE);
		
		db.insert(TABLE, null, values);
		db.close();
	}
	
	public Cursor getDoodleCursor() {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE, new String[]{C_BITMAP, C_DOODLE, C_TITLE}, null, null, null, null, null);
		return cursor;
	}
	
	public Doodle getDoodle() {
		Doodle doodle = new Doodle();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE, new String[]{C_BITMAP, C_DOODLE, C_TITLE}, null, null, null, null, null);
		
		if (cursor.moveToNext()) {
			doodle.setBitmap(com.cyeam.doodle.Doodle.getPicFromBytes(cursor.getBlob(0)));
			doodle.setDoodle(cursor.getString(1));
			doodle.setTitle(cursor.getString(2));
		}
		db.close();
		return doodle;
	}
}
