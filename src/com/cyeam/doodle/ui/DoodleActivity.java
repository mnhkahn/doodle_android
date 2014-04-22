package com.cyeam.doodle.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.widget.ImageView;

import com.cyeam.doodle.R;

public class DoodleActivity extends Activity {
	ImageView imView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doodle);
		imView = (ImageView) findViewById(R.id.doodle_preview);

		Intent intent = new Intent(this, UpdaterReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);

//		// 开始时间
//		long firstime = SystemClock.elapsedRealtime();
//
//		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
//				60 * 1000, pendingIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doodle, menu);
		return true;
	}

}
