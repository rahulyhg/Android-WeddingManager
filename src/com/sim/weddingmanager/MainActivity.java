package com.sim.weddingmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	Button button_valider, button_finish;
	EditText ed_name,ed_date;
	//DatePicker ed_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.findViews();
		this.handleEvents();

	}

	private void findViews() {
		
		ed_name = (EditText) findViewById(R.id.edit_name);
		ed_date = (EditText) findViewById(R.id.edit_date);
		button_valider = (Button) findViewById(R.id.button_validate);
		button_finish = (Button) findViewById(R.id.button_finish);

	}

	private void handleEvents() {
		button_valider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ed_name.getText().length() > 0) {
					saveSharedPreferences();
					Intent i = new Intent(MainActivity.this,
							DashboardActivity.class);
					startActivity(i);
					finish();
				} else {
					Toast toast = Toast.makeText(MainActivity.this,
							"Indiquer une valeur !", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});

		button_finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Create Dialog Alert
				new AlertDialog.Builder(MainActivity.this)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setTitle("Confirmation")
						.setMessage(
								"Voulez vous vraiment quitter l'application ?")
						.setNegativeButton("Non",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}

								})
						.setPositiveButton("Oui",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										finish();
										// dialog.cancel();
									}
								}).show();

			}
		});

	}

	private void saveSharedPreferences() {
		SharedPreferences mypref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = mypref.edit();
		
		prefEditor.putString("thename", ed_name.getText().toString());
		prefEditor.putString("thedate", ed_date.getText().toString());
//String.valueOf(Tools.getDateFromDatePicket(ed_date))
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
