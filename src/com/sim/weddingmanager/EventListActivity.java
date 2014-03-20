package com.sim.weddingmanager;

import java.util.ArrayList;
import java.util.HashMap;

import com.sim.weddingmanager.dao.EventDAO;
import com.sim.weddingmanager.entities.Event;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EventListActivity extends Activity {

	HashMap<String, String> map;
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

	String[] hotel_names;
	String[] hotel_prices;
	TypedArray hotel_images;

	String[] car_names;
	String[] car_prices;
	TypedArray car_images;

	String[] deco_names;
	String[] deco_prices;
	TypedArray deco_images;

	String[] cake_names;
	String[] cake_prices;
	TypedArray cake_images;

	ListView listView;

	SimpleAdapter simpleAdapter;
	
	String strCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** TRAP 2 **/
		setContentView(R.layout.activity_event_list);
		this.trapExtras();
		this.findViews();

		hotel_names = getResources().getStringArray(R.array.hotel_name);
		hotel_prices = getResources().getStringArray(R.array.hotel_price);
		hotel_images = getResources().obtainTypedArray(R.array.hotel_image);

		car_names = getResources().getStringArray(R.array.car_name);
		car_prices = getResources().getStringArray(R.array.car_price);
		car_images = getResources().obtainTypedArray(R.array.car_image);

		cake_names = getResources().getStringArray(R.array.cake_name);
		cake_prices = getResources().getStringArray(R.array.cake_price);
		cake_images = getResources().obtainTypedArray(R.array.cake_image);

		deco_names = getResources().getStringArray(R.array.deco_name);
		deco_prices = getResources().getStringArray(R.array.deco_price);
		deco_images = getResources().obtainTypedArray(R.array.deco_image);

		
		/**
		 * HINT GET IMAGE RESSOURCE FROM TYPEDARRAY :
		 * String.valueOf(deco_images.getResourceId(i, -1))
		 **/
		
		/** TODO 8 GET INTENT + FEEDING THE ArrayList **/
		data = new ArrayList<HashMap<String, String>>();
		
		if(this.strCategory.equals("Car")) {
			
			for(int i = 0; i<car_names.length; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("name", car_names[i]);
				item.put("budget", car_prices[i]);
				item.put("picture", String.valueOf(car_images.getResourceId(i, -1)));
				data.add(item);
				
			}
			
		} else if(this.strCategory.equals("Hotel")) {
			
			for(int i = 0; i<hotel_names.length; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("name", hotel_names[i]);
				item.put("budget", hotel_prices[i]);
				item.put("picture", String.valueOf(hotel_images.getResourceId(i, -1)));
				data.add(item);
				
			}
			
		} else if(this.strCategory.equals("Deco")) {
			
			for(int i = 0; i<deco_names.length; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("name", deco_names[i]);
				item.put("budget", deco_prices[i]);
				item.put("picture", String.valueOf(deco_images.getResourceId(i, -1)));
				data.add(item);
				
			}
		} else if(this.strCategory.equals("Cake")) {
			
			for(int i = 0; i<cake_names.length; i++) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("name", cake_names[i]);
				item.put("budget", cake_prices[i]);
				item.put("picture", String.valueOf(cake_images.getResourceId(i, -1)));
				data.add(item);
				
			}
		}
		
		
		
		
		/** TODO 9 SIMPLE ADAPTER **/
		
		this.simpleAdapter = new SimpleAdapter(EventListActivity.this, data,
				R.layout.one_event, new String[] { "picture", "name", "budget" },
				new int[] { R.id.iconNew, R.id.lblName, R.id.lblPrice });
		this.listView.setAdapter(simpleAdapter);

		
		/**
		 * TODO 10 OnItemClickListener + INSERT DATABASE + RESULT ->
		 * DASHBOARD_ACTIVITY
		 **/
		this.listView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(position);
				
				String itemNameValue = (String)map.get("name");
				String itemPriceValue = (String)map.get("budget");
				
				Event event = new Event();
				event.setName(itemNameValue);
				event.setBudget(Double.valueOf(itemPriceValue));
				event.setCategory(strCategory);
				if(insertObject(event)) {
					Bundle bundle = new Bundle();
					bundle.putString("extraItem", itemNameValue);
					Intent intent = new Intent();
					intent.putExtras(bundle);
					setResult(RESULT_OK, intent);
					finish();
				} else {
					Toast.makeText(
							getApplicationContext(),
							"Erreur d'ajout de l'objet à la base de donnée !", Toast.LENGTH_LONG).show();
				}
			}

		});
		
	}
	
	private void trapExtras() {
		Bundle bundle = this.getIntent().getExtras();
		if (bundle == null) {
			Log.i("==============", "INTENT EXTRA NULL");
			return;

		}
		String extraCategory = bundle.getString("extraCategory");
		if (extraCategory == null) {
			Log.i("==============", "BUNDLE EXTRA TEXT NULL");
			return;
		}
		
		this.setTitle(extraCategory);
		this.strCategory = extraCategory;
		
		
	}
	
	private void findViews() {
		this.listView = (ListView) findViewById(R.id.lista);
	}
	
	
	private boolean insertObject(Event event) {
		try {
			EventDAO eventDAO = new EventDAO(EventListActivity.this);
			eventDAO.create(event);
			return true;
		} catch (Exception e) {
			Log.i("==============", "Error Insert object in database !");
			return false;
		}
		
	}

}
