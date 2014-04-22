package com.cyeam.doodle.model;

import android.graphics.Bitmap;

public class Doodle {

	private String Title;
	private String Doodle;
	private Bitmap bitmap;
	
	public Doodle() {
		
	}
	
	public Doodle(String Title, String Doodle) {
		this.Title = Title;
		this.Doodle = Doodle;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDoodle() {
		return Doodle;
	}

	public void setDoodle(String doodle) {
		Doodle = doodle;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
}
