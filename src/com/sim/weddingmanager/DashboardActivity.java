package com.sim.weddingmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {

	private static final int REQUEST_CODE = 10;
	TextView tv_name;
	ImageButton img_hotel, img_cake, img_car, img_deco;

	public static final String PREF_NAME = "EspritMobilePrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		this.findViews();
		this.getSharedPreferences();

	}

	private void findViews() {
		img_cake = (ImageButton) findViewById(R.id.cake_image);
		img_car = (ImageButton) findViewById(R.id.car_image);
		img_deco = (ImageButton) findViewById(R.id.deco_image);
		img_hotel = (ImageButton) findViewById(R.id.hotel_image);

		tv_name = (TextView) findViewById(R.id.welcome_text);

	}

	private void getSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREF_NAME,
				MODE_PRIVATE);

		tv_name.setText("Welcome " + settings.getString("thename", ""));

	}

	
	public void load(View view) {
		Intent intent = new Intent(DashboardActivity.this,
				EventListActivity.class);

		switch (view.getId()) {
		case R.id.hotel_image:
			intent.putExtra("extraCategory", "Hotel");
			startActivityForResult(intent, REQUEST_CODE);
			break;
		case R.id.deco_image:
			intent.putExtra("extraCategory", "Deco");
			startActivityForResult(intent, REQUEST_CODE);

			break;
		case R.id.cake_image:
			intent.putExtra("extraCategory", "Cake");
			startActivityForResult(intent, REQUEST_CODE);

			break;
		case R.id.car_image:
			intent.putExtra("extraCategory", "Car");
			startActivityForResult(intent, REQUEST_CODE);

			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle bundle = data.getExtras();
		String extraItem = bundle.getString("extraItem");
		
		Toast.makeText(
				getApplicationContext(),
				"Vous avez choisi : "+extraItem, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_calculate:
			Intent intent = new Intent(DashboardActivity.this,
					BillingActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
