package edu.umass.parking.pcomobile.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import edu.umass.parking.pcomobile.R;
import edu.umass.parking.pcomobile.helpers.DatabaseHelper;
import edu.umass.parking.pcomobile.models.State;

public class UpdateDbActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_db);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_db, menu);
		return true;
	}

	public void updateStatesTable(View view) {
		DatabaseHelper dbh = new DatabaseHelper(this);
		
		// parsing the states.csv file under res/raw
		InputStream is = getResources().openRawResource(R.raw.states);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				String pieces[] = strLine.split(",");
				if (pieces.length == 3) {
					String code = pieces[1];
					String desc = pieces[2];
					int id = Integer.parseInt(pieces[0]);
					
					State s = new State(id, code, desc);
					dbh.addState(s);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EditText wsDisplay = (EditText) findViewById(R.id.show_ws_results);
		State s3 = dbh.getState("PR");
		wsDisplay.setText(s3.getID() + "\t" + s3.getCode() + "\t" + s3.getDescription());
		State s4 = dbh.getState("VI");

	}

}
