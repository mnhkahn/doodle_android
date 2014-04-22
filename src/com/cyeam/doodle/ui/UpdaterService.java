package com.cyeam.doodle.ui;

import com.cyeam.doodle.DoodleApplication;
import com.cyeam.doodle.DoodleProvider;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;

public class UpdaterService extends Service {
	private Updater updater;
	public static boolean runFlag = false;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.updater = new Updater();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		runFlag = false;
		this.updater.interrupt();
		this.updater = null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		
		runFlag = true;
		this.updater.start();
		
		return START_STICKY;
	}

	private class Updater extends Thread {
		public Updater() {
			
		}
		
		@Override
		public void run() {
			System.out.println("111111111111111");
			UpdaterService updaterService = UpdaterService.this;
			
			DoodleApplication doodleApplication = (DoodleApplication)updaterService.getApplication();
			int updatesResult = doodleApplication.fetchDoodleUpdates();
		}
	}
}
