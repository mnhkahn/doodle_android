package com.cyeam.doodle.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.cyeam.doodle.DoodleProvider;
import com.cyeam.doodle.R;
import com.cyeam.doodle.model.Doodle;

public class DoodleWidget extends AppWidgetProvider {

	private DoodleData doodleData;

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		if (intent.getAction().equals("com.cyeam.doodle.click")) {
			if (UpdaterService.runFlag == true) {
				UpdaterService.runFlag = false;
				context.stopService(new Intent(context, UpdaterService.class));
			}
			UpdaterService.runFlag = true;
			context.startService(new Intent(context, UpdaterService.class));
			System.out.println("000000000000000");
		}
		else if (intent.getAction().equals("com.cyeam.doodle.update")) {
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(context);
			this.onUpdate(context, appWidgetManager, appWidgetManager
					.getAppWidgetIds(new ComponentName(context,
							DoodleWidget.class)));
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Doodle doodle = new Doodle();
		Cursor cursor = context.getContentResolver().query(
				DoodleProvider.CONTENT_URL, null, null, null, null);
		System.out.println(cursor.getCount());
		if (cursor != null && cursor.moveToNext()) {
			System.out.println("****************");
			doodle.setBitmap(com.cyeam.doodle.Doodle.getPicFromBytes(cursor
					.getBlob(0)));
			doodle.setDoodle(cursor.getString(1));
			doodle.setTitle(cursor.getString(2));
		}

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.doodle_widget);

		views.setImageViewBitmap(R.id.doodle_widget, doodle.getBitmap());

		Intent intent = new Intent("com.cyeam.doodle.click");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		views.setOnClickPendingIntent(R.id.doodle_widget, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, views);

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
