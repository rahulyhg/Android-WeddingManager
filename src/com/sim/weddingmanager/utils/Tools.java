package com.sim.weddingmanager.utils;

import java.util.Calendar;
import java.util.Date;

import android.widget.DatePicker;

public class Tools {
	public static Date getDateFromDatePicket(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);

	    return calendar.getTime();
	}

}
