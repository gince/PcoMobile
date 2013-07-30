package edu.umass.parking.pcomobile.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1; // may be deleted
	private static final String DATABASE_NAME = "pcomobile.db";

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
		String createStatesTable = "CREATE TABLE " + TABLE_STATES +
				"(" + COLUMN_STATES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				COLUMN_STATES_CODE + " TEXT, " + COLUMN_STATES_DESCRIPTION + 
				" TEXT);" ;
		String createStaffTable = "CREATE TABLE " + TABLE_STAFF +
				"(" + COLUMN_STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				COLUMN_STAFF_CODE + " TEXT, " + COLUMN_STAFF_DESCRIPTION + 
				" TEXT);" ;
		
		db.execSQL(createStatesTable);
		db.execSQL(createStaffTable);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
		onCreate(db);
	}
}
