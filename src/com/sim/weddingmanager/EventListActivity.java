package com.sim.weddingmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sim.weddingmanager.dao.EventDAO;
import com.sim.weddingmanager.entities.Event;
import com.sim.weddingmanager.entities.Item;
import com.sim.weddingmanager.utils.BaseFeedParser;

public class EventListActivity extends Activity {

	HashMap<String, String> map;
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
	ProgressBar progress_data;
	ListView listView;
	GridView grid_list;
	SimpleAdapter simpleAdapter;

	String strCategory;
	String strPersonFullName;
	List<Item> listItems;

	public static final String PREF_NAME = "EspritMobilePrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		this.trapExtras();

		this.findViews();

		this.getSharedPreferences();

		this.loadData();

	}

	private void loadData() {
		new HttpService().execute();
	}

	private ArrayList<HashMap<String, String>> fillListView() {

		data = new ArrayList<HashMap<String, String>>();
		for (Item item : listItems) {
			if (this.strCategory.equals("Hotel")
					&& item.getCategory().equals("Hotel")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture", String.valueOf(getResources().getIdentifier(
						item.getPicture(), "drawable", getPackageName())));
				data.add(x);

			}

			if (this.strCategory.equals("Car")
					&& item.getCategory().equals("Car")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture", String.valueOf(getResources().getIdentifier(
						item.getPicture(), "drawable", getPackageName())));
				data.add(x);

			}

			if (this.strCategory.equals("Deco")
					&& item.getCategory().equals("Deco")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture", String.valueOf(getResources().getIdentifier(
						item.getPicture(), "drawable", getPackageName())));
				data.add(x);
			}

			if (this.strCategory.equals("Cake")
					&& item.getCategory().equals("Cake")) {
				HashMap<String, String> x = new HashMap<String, String>();
				x.put("name", item.getName());
				x.put("budget", item.getPrice());
				x.put("picture", String.valueOf(getResources().getIdentifier(
						item.getPicture(), "drawable", getPackageName())));
				data.add(x);
			}
		}

		return data;

	}

	private void setAdapter(ArrayList<HashMap<String, String>> data) {
		this.simpleAdapter = new SimpleAdapter(EventListActivity.this, data,
				R.layout.one_event,
				new String[] { "picture", "name", "budget" }, new int[] {
						R.id.iconNew, R.id.lblName, R.id.lblPrice });
		this.listView.setAdapter(simpleAdapter);
		this.grid_list.setAdapter(simpleAdapter);
		this.listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				addItem(position);
			}

		});

		this.grid_list.setAdapter(simpleAdapter);

		this.grid_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				addItem(position);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void addItem(int position) {
		HashMap<String, String> map = (HashMap<String, String>) listView
				.getItemAtPosition(position);

		String itemNameValue = (String) map.get("name");
		String itemPriceValue = (String) map.get("budget");

		Event event = new Event();
		event.setName(itemNameValue);
		event.setBudget(Double.valueOf(itemPriceValue));
		event.setPerson(this.strPersonFullName);
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
		this.grid_list = (GridView) findViewById(R.id.grid_list);
		this.progress_data = (ProgressBar) findViewById(R.id.progress_data);

		onConfigurationChanged(this.getResources().getConfiguration());
	}

	private boolean insertObject(Event event) {
		try {
			EventDAO eventDAO = new EventDAO(EventListActivity.this);
			Event filtredEvent = eventDAO.readWhere(Event.COLUMN_PERSON,
					this.strPersonFullName, Event.COLUMN_CATEGORY,
					event.getCategory());

			if (filtredEvent != null) {
				eventDAO.update(filtredEvent.getId(), event);
			} else {
				eventDAO.create(event);
			}

			
			return true;
		} catch (Exception e) {
			Log.i("==============", "Error Insert object in database !");
			return false;
		}

	}

	private void getSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREF_NAME,
				MODE_PRIVATE);

		this.strPersonFullName = settings.getString("thename", "");

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.i("==============", "PORTRAIT !");
			listView.setVisibility(View.VISIBLE);
			grid_list.setVisibility(View.GONE);
		}

		else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.i("==============", "LANDSCAE !");
			listView.setVisibility(View.GONE);
			grid_list.setVisibility(View.VISIBLE);
		}
	}

	private class HttpService extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress_data.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			BaseFeedParser parser = new BaseFeedParser();
			try {
				parser.setInputStream(getAssets().open("data.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			listItems = parser.parse();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			setAdapter(fillListView());
			progress_data.setVisibility(View.GONE);

		}

	}

}
