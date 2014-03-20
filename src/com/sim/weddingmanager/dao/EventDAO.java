package com.sim.weddingmanager.dao;

import garin.artemiy.sqlitesimple.library.SQLiteSimpleDAO;
import android.content.Context;

import com.sim.weddingmanager.entities.Event;



/** TODO 10.1 CREATE THE DAO **/

public class EventDAO extends SQLiteSimpleDAO<Event> {

	public EventDAO(Context context) {
		super(Event.class, context);
		// TODO Auto-generated constructor stub
	}

}
