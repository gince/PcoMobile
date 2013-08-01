package edu.umass.parking.pcomobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.umass.parking.pcomobile.models.State;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1; // may be deleted
	private static final String DATABASE_NAME = "pcoMobile";

	private static final String TABLE_STATES = "states";
	public static final String COLUMN_STATES_ID = "id";
	public static final String COLUMN_STATES_CODE = "code";
	public static final String COLUMN_STATES_DESCRIPTION = "description";

	private static final String TABLE_STAFF = "staff";
	public static final String COLUMN_STAFF_ID = "id";
	public static final String COLUMN_STAFF_CODE = "code";
	public static final String COLUMN_STAFF_DESCRIPTION = "description";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createStatesTable = "CREATE TABLE " + TABLE_STATES + "("
				+ COLUMN_STATES_ID + " INTEGER, "
				+ COLUMN_STATES_CODE + " TEXT, " + COLUMN_STATES_DESCRIPTION
				+ " TEXT);";
		String createStaffTable = "CREATE TABLE " + TABLE_STAFF + "("
				+ COLUMN_STAFF_ID + " INTEGER, "
				+ COLUMN_STAFF_CODE + " TEXT, " + COLUMN_STAFF_DESCRIPTION
				+ " TEXT);";

		db.execSQL(createStatesTable);
		db.execSQL(createStaffTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
		onCreate(db);
	}

	public void addState(State state) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_STATES_ID, state.getID());
		values.put(COLUMN_STATES_CODE, state.getCode());
		values.put(COLUMN_STATES_DESCRIPTION, state.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_STATES, null, values);
		db.close();
	}
	
	public State getState(String scode) {
		String query = "Select * FROM " + TABLE_STATES + " WHERE " + COLUMN_STATES_CODE + " =  \"" + scode + "\"";
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		State s = new State();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			s.setID(Integer.parseInt(cursor.getString(0)));
			s.setCode(cursor.getString(1));
			s.setDescription(cursor.getString(2));
			cursor.close();
		} else {
			s = null;
		}
	        db.close();
		return s;
	}
}
