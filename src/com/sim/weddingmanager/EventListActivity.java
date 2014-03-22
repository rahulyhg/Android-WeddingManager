package com.sim.weddingmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sim.weddingmanager.dao.EventDAO;
import com.sim.weddingmanager.entities.Event;
import com.sim.weddingmanager.entities.Item;
import com.sim.weddingmanager.utils.BaseFeedParser;

public class EventListActivity extends Activity {

	HashMap<String, String> map;
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

	ListView listView;

	SimpleAdapter simpleAdapter;

	String strCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_event_list);
		this.trapExtras();
		this.findViews();

		data = new ArrayList<HashMap<String, String>>();
		
		
		BaseFeedParser parser = new BaseFeedParser();
		try {
			parser.setInputStream(this.getAssets().open("data.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Item> listItems = parser.parse();
		
		for (Item item : listItems) {
			if(this.strCategory.equals("Hotel") && item.getCategory().equals("Hotel")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture",
						String.valueOf(
								getResources().getIdentifier(item.getPicture(), "drawable", getPackageName()))
								);
				data.add(x);
				
				
			}
			
			if(this.strCategory.equals("Car") && item.getCategory().equals("Car")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture",
				String.valueOf(
						getResources().getIdentifier(item.getPicture(), "drawable", getPackageName()))
						);
				data.add(x);
				
			}
			
			if(this.strCategory.equals("Deco") && item.getCategory().equals("Deco")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture",
						String.valueOf(
								getResources().getIdentifier(item.getPicture(), "drawable", getPackageName()))
								);
				data.add(x);
			}
			
			if(this.strCategory.equals("Cake") && item.getCategory().equals("Cake")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture",
						String.valueOf(
								getResources().getIdentifier(item.getPicture(), "drawable", getPackageName()))
								);
				data.add(x);
			}
		}
		
		this.simpleAdapter = new SimpleAdapter(EventListActivity.this, data,
				R.layout.one_event,
				new String[] { "picture", "name", "budget" }, new int[] {
						R.id.iconNew, R.id.lblName, R.id.lblPrice });
		this.listView.setAdapter(simpleAdapter);

		this.listView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(position);

				String itemNameValue = (String) map.get("name");
				String itemPriceValue = (String) map.get("budget");

				Event event = new Event();
				event.setName(itemNameValue);
				event.setBudget(Double.valueOf(itemPriceValue));
				event.setCategory(strCategory);
				if (insertObject(event)) {
					Bundle bundle = new Bundle();
					bundle.putString("extraItem", itemNameValue);
					Intent intent = new Intent();
					intent.putExtras(bundle);
					setResult(RESULT_OK, intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Erreur d'ajout de l'objet à la base de donnée !",
							Toast.LENGTH_LONG).show();
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
