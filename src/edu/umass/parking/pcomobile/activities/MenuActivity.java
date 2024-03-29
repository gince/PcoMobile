package edu.umass.parking.pcomobile.activities;

import edu.umass.parking.pcomobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void startCitationActivity(View view) {
		Intent intent = new Intent(this, CitationActivity.class);
		startActivity(intent);
	}
	
	public void startUpdateDbActivity(View view) {
		Intent intent = new Intent(this, UpdateDbActivity.class);
		startActivity(intent);
	}

}
