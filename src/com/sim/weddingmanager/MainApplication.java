package com.sim.weddingmanager;

import garin.artemiy.sqlitesimple.library.SQLiteSimple;
import garin.artemiy.sqlitesimple.library.util.SimpleDatabaseUtil;
import android.app.Application;
import android.util.Log;

import com.sim.weddingmanager.entities.Event;

public class MainApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("=================","START APP...");
		// also may use isFirstStartOnAppVersion with your version
        if (SimpleDatabaseUtil.isFirstApplicationStart(this)) {
        	Log.d("=================","FIRST START APP...");
            SQLiteSimple databaseSimple = new SQLiteSimple(this);
            databaseSimple.create(Event.class);
        }
        
	}
	
	
}
