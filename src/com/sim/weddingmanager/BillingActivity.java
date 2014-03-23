package com.sim.weddingmanager;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.sim.weddingmanager.dao.EventDAO;
import com.sim.weddingmanager.entities.Event;

public class BillingActivity extends Activity {

	HashMap<String, String> map;
	TextView txtCarSelection, txtDecoSelection, txtCakeSelection,
			txtHotelSelection, txtCarPrice, txtDecoPrice, txtCakePrice,
			txtHotelPrice, txtTotal;

	String strPersonFullName;
	
	public static final String PREF_NAME = "EspritMobilePrefs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_billing);

		this.getSharedPreferences();
		
		this.findViews();

		this.computeResult();
		

	}
	
	private void computeResult() {
		EventDAO eventDAO = new EventDAO(this);
		final List<Event> list = eventDAO.readAllWhere(Event.COLUMN_PERSON, this.strPersonFullName);
		
		double total = 0;
		for (Event event : list) {
			map = new HashMap<String, String>();
			String name = event.getName();
			String budget = String.valueOf(event.getBudget());
			map.put("name", name);
			map.put("budget", budget);
			total += event.getBudget();
			if (event.getCategory().equals("Hotel")) {
				this.txtHotelSelection.setText(name);
				this.txtHotelPrice.setText(budget);
			} else if (event.getCategory().equals("Car")) {
				this.txtCarSelection.setText(name);
				this.txtCarPrice.setText(budget);

			} else if (event.getCategory().equals("Cake")) {
				this.txtCakeSelection.setText(name);
				this.txtCakePrice.setText(budget);

			} else if (event.getCategory().equals("Deco")) {
				this.txtDecoSelection.setText(name);
				this.txtDecoPrice.setText(budget);
			}

		}

		txtTotal.setText(String.valueOf(total) + " DT");
		
	}

	private void findViews() {
		txtCarSelection = (TextView) findViewById(R.id.car_selection);
		txtCarPrice = (TextView) findViewById(R.id.car_price);

		txtDecoSelection = (TextView) findViewById(R.id.deco_selection);
		txtDecoPrice = (TextView) findViewById(R.id.deco_price);

		txtCakeSelection = (TextView) findViewById(R.id.cake_selection);
		txtCakePrice = (TextView) findViewById(R.id.cake_price);

		txtHotelSelection = (TextView) findViewById(R.id.hotel_selection);
		txtHotelPrice = (TextView) findViewById(R.id.hotel_price);

		txtTotal = (TextView) findViewById(R.id.total);

	}
	
	private void getSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREF_NAME,
				MODE_PRIVATE);

		this.strPersonFullName = settings.getString("thename", "");
		
	}

}
