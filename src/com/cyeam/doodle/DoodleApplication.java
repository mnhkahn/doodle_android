package com.cyeam.doodle;

import com.cyeam.doodle.ui.DoodleData;

import android.app.Application;
import android.content.ContentValues;
import android.graphics.Bitmap;

public class DoodleApplication extends Application {

	private DoodleData doodleDate;
	private Doodle doodle;
	
	public DoodleData getDoodleData() {
		doodleDate = new DoodleData(getApplicationContext());
		return doodleDate;
	}
	
	public synchronized int fetchDoodleUpdates() {
		doodle = new Doodle();
		com.cyeam.doodle.model.Doodle doodle_model = doodle.GetDoodle();
		Bitmap bm = doodle.returnBitMap(doodle_model.getDoodle());
		doodle_model.setBitmap(bm);
		
		ContentValues values = new ContentValues();
		values.put(DoodleData.C_BITMAP, Doodle.Bitmap2Bytes(doodle_model.getBitmap()));
		values.put(DoodleData.C_DOODLE, doodle_model.getDoodle());
		values.put(DoodleData.C_TITLE, doodle_model.getTitle());
		
		this.getDoodleData().update(values);
		
		return 1;
	}
}
