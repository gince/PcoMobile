package edu.umass.parking.pcomobile.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.umass.parking.pcomobile.R;
import edu.umass.parking.pcomobile.helpers.DatabaseHelper;
import edu.umass.parking.pcomobile.models.Lookup;
import edu.umass.parking.pcomobile.models.PermitVehicle;

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

	public void updateLookupTables(View view) {
		updateLookupTable("states");
		updateLookupTable("vehicle_colors");
		updateLookupTable("vehicle_makes");
		updateLookupTable("permit_status");
		updateLookupTable("plate_types");
		updatePermitVehicleTable();
	}

	// updates a given table getting data from the csv file
	// which must have the same name as the table to be updated
	public void updateLookupTable(String tableName) {
		DatabaseHelper dbh = new DatabaseHelper(this);

		// gets the id of the csv file under res/raw
		int resID = getResources().getIdentifier(tableName, "raw",
				getPackageName());

		// parses the .csv and add entries into the corresponding table
		InputStream is = getResources().openRawResource(resID);
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

					Lookup l = new Lookup(id, code, desc);
					dbh.addToLookupTable(l, tableName);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updatePermitVehicleTable() {
		DatabaseHelper dbh = new DatabaseHelper(this);
		InputStream is = getResources().openRawResource(R.raw.permitvehicle);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String strLine;
		PermitVehicle pv;
		try {
			while ((strLine = br.readLine()) != null) {
				try {
					String perNmbr = ((String) strLine.subSequence(0, 9)).replace("#", "");
					String perStrt = (String) strLine.subSequence(9, 15);
					String perExpr = (String) strLine.subSequence(15, 21);
					int perStts = Integer.parseInt((String) strLine
							.subSequence(21, 25));
					int vehState = Integer.parseInt((String) strLine
							.subSequence(25, 29));
					int vehType = Integer.parseInt((String) strLine
							.subSequence(29, 33));
					int vehColor = Integer.parseInt((String) strLine
							.subSequence(33, 37));
					int vehMake = Integer.parseInt((String) strLine
							.subSequence(37, 41));
					String vehPlate = (String) strLine.subSequence(41,
							strLine.length());
					
					pv = new PermitVehicle(perNmbr, perStrt, perExpr, perStts, vehState, vehType, vehColor, vehMake, vehPlate);
				} catch (Exception e) {
					String perNmbr = ((String) strLine.subSequence(0, 9)).replace("#", "");
					String perStrt = (String) strLine.subSequence(9, 15);
					String perExpr = (String) strLine.subSequence(15, 21);
					int perStts = Integer.parseInt((String) strLine
							.subSequence(21, 25));
					pv = new PermitVehicle(perNmbr, perStrt, perExpr, perStts);
				}
				
				dbh.addToPermitVehicleTable(pv);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * EditText wsDisplay = (EditText) findViewById(R.id.show_ws_results); State
	 * s3 = dbh.getState("PR"); wsDisplay.setText(s3.getID() + "\t" +
	 * s3.getCode() + "\t" + s3.getDescription()); }
	 */
}
