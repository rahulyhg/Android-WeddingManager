package com.sim.weddingmanager;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String PREF_NAME = "EspritMobilePrefs";

	Button button_valider;
	EditText ed_name, ed_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		this.findViews();

		button_valider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/** TODO1 SharedPrefs **/
				if (ed_name.getText().length() > 0
						&& ed_date.getText().length() > 0) {
					saveSharedPreferences();

					/** TODO2 Intent **/
					Intent i = new Intent(MainActivity.this,
							DashboardActivity.class);
					
					startActivity(i);
					finish();
				} else {
					Toast toast = Toast.makeText(MainActivity.this, "Indiquer une valeur !",
							Toast.LENGTH_LONG);
					toast.show();
				}

			}
		});

	}

	private void findViews() {
		ed_name = (EditText) findViewById(R.id.edit_name);
		ed_date = (EditText) findViewById(R.id.edit_date);
		button_valider = (Button) findViewById(R.id.button_validate);

	}

	private void saveSharedPreferences() {
		SharedPreferences mypref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = mypref.edit();
		prefEditor.putString("thename", ed_name.getText().toString());
		prefEditor.putString("thedate", ed_date.getText().toString());

		prefEditor.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast toast = Toast.makeText(MainActivity.this, "Search handled",
				Toast.LENGTH_LONG);
		toast.show();
		return true;
		// return super.onOptionsItemSelected(item);
	}

}
