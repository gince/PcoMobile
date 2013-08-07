package edu.umass.parking.pcomobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.umass.parking.pcomobile.models.Lookup;
import edu.umass.parking.pcomobile.models.PermitVehicle;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2; // may be deleted
	private static final String DATABASE_NAME = "pcoMobile";

	// attributes of staff table
	private static final String TABLE_STAFF = "staff";
	public static final String COLUMN_STAFF_ID = "id";
	public static final String COLUMN_STAFF_CODE = "code";
	public static final String COLUMN_STAFF_DESC = "description";

	// lookup table names
	private static final String TABLE_STATES = "states";
	private static final String TABLE_VEH_COLORS = "vehicle_colors";
	private static final String TABLE_VEH_MAKES = "vehicle_makes";
	private static final String TABLE_PLATE_TYPES = "plate_types";
	private static final String TABLE_PERMIT_STATUS = "permit_status";
	private static final String TABLE_LOCATIONS = "locations";
	private static final String TABLE_VIOLATIONS = "violations";
	private static final String TABLE_COMMENTS = "comments";

	// attributes of lookup table
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_DESC = "description";

	// attributes of PermitVehicle table
	private static final String TABLE_PERMIT_VEHICLE = "permit_vehicle";
	public static final String COLUMN_PERVEH_ID = "id";
	public static final String COLUMN_PERVEH_PER_NMBR = "per_number";
	public static final String COLUMN_PERVEH_PER_STRT = "per_start_date";
	public static final String COLUMN_PERVEH_PER_EXPR = "per_expire_date";
	public static final String COLUMN_PERVEH_PER_STTS = "per_status";
	public static final String COLUMN_PERVEH_VEH_STATE = "veh_state";
	public static final String COLUMN_PERVEH_VEH_TYPE = "plate_type";
	public static final String COLUMN_PERVEH_VEH_COLOR = "veh_color";
	public static final String COLUMN_PERVEH_VEH_MAKE = "veh_make";
	public static final String COLUMN_PERVEH_VEH_PLATE = "veh_plate";

	// constructor
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// query that creates STAFF table
		String createStaffTable = "CREATE TABLE " + TABLE_STAFF + "("
				+ COLUMN_STAFF_ID + " INTEGER, " + COLUMN_STAFF_CODE
				+ " TEXT, " + COLUMN_STAFF_DESC + " TEXT);";

		String createStatesTable = getCreateLookupTableQuery(TABLE_STATES);
		String createVehicleColorsTable = getCreateLookupTableQuery(TABLE_VEH_COLORS);
		String createVehicleMakesTable = getCreateLookupTableQuery(TABLE_VEH_MAKES);
		String createPlateTypesTable = getCreateLookupTableQuery(TABLE_PLATE_TYPES);
		String createPermitStatusTable = getCreateLookupTableQuery(TABLE_PERMIT_STATUS);
		String createLocationsTable = getCreateLookupTableQuery(TABLE_LOCATIONS);
		String createViolationsTable = getCreateLookupTableQuery(TABLE_VIOLATIONS);
		String createCommentsTable = getCreateLookupTableQuery(TABLE_COMMENTS);

		// query that creates TABLE_PERMIT_VEHICLE table
		String createPermitVehicleTable = "CREATE TABLE "
				+ TABLE_PERMIT_VEHICLE + "(" + COLUMN_PERVEH_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT , "
				+ COLUMN_PERVEH_PER_NMBR + " TEXT, " + COLUMN_PERVEH_PER_STRT
				+ " TEXT, " + COLUMN_PERVEH_PER_EXPR + " TEXT, "
				+ COLUMN_PERVEH_PER_STTS + " INTEGER, "
				+ COLUMN_PERVEH_VEH_STATE + " INTEGER, "
				+ COLUMN_PERVEH_VEH_TYPE + " INTEGER, "
				+ COLUMN_PERVEH_VEH_COLOR + " INTEGER, "
				+ COLUMN_PERVEH_VEH_MAKE + " INTEGER, "
				+ COLUMN_PERVEH_VEH_PLATE + " TEXT);";

		// tables are created
		db.execSQL(createStatesTable);
		db.execSQL(createStaffTable);
		db.execSQL(createVehicleColorsTable);
		db.execSQL(createVehicleMakesTable);
		db.execSQL(createPlateTypesTable);
		db.execSQL(createPermitStatusTable);
		db.execSQL(createPermitVehicleTable);
		db.execSQL(createLocationsTable);
		db.execSQL(createViolationsTable);
		db.execSQL(createCommentsTable);

	}

	public String getCreateLookupTableQuery(String tableName) {
		String query = "CREATE TABLE " + tableName + "(" + COLUMN_ID
				+ " INTEGER, " + COLUMN_CODE + " TEXT, " + COLUMN_DESC
				+ " TEXT);";
		return query;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEH_COLORS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEH_MAKES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLATE_TYPES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERMIT_STATUS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERMIT_VEHICLE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIOLATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(db);
	}

	// ADD functions for Lookup tables
	public void addToLookupTable(Lookup l, String tableName) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, l.getID());
		values.put(COLUMN_CODE, l.getCode());
		values.put(COLUMN_DESC, l.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(tableName, null, values);
		db.close();
	}

	public void addToPermitVehicleTable(PermitVehicle perveh) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_PERVEH_PER_NMBR, perveh.getPerNumber());
		values.put(COLUMN_PERVEH_PER_STRT, perveh.getPerStartDate());
		values.put(COLUMN_PERVEH_PER_EXPR, perveh.getPerExpireDate());
		values.put(COLUMN_PERVEH_PER_STTS, perveh.getPerStatus());
		values.put(COLUMN_PERVEH_VEH_STATE, perveh.getVehState());
		values.put(COLUMN_PERVEH_VEH_TYPE, perveh.getVehType());
		values.put(COLUMN_PERVEH_VEH_COLOR, perveh.getVehColor());
		values.put(COLUMN_PERVEH_VEH_MAKE, perveh.getVehMake());
		values.put(COLUMN_PERVEH_VEH_PLATE, perveh.getVehPlate());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_PERMIT_VEHICLE, null, values);
		db.close();
	}

	public Lookup getLookupEntry(String code, String tableName) {
		String query = "Select * FROM " + tableName + " WHERE " + COLUMN_CODE
				+ " =  \"" + code + "\"";

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);

		Lookup l = new Lookup();

		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			l.setID(Integer.parseInt(cursor.getString(0)));
			l.setCode(cursor.getString(1));
			l.setDescription(cursor.getString(2));
			cursor.close();
		} else {
			l = null;
		}
		db.close();
		return l;
	}

	public String[] getCodesDescsFromLookupTables(String tableName, String column) {
		String query = "SELECT " + column + " FROM " + tableName;
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);
	
		int i = 0;
		if (cursor.getCount() > 0) {
			String[] codes = new String[cursor.getCount()];
			while (cursor.moveToNext()) {
				codes[i] = cursor.getString(cursor.getColumnIndex(column));
				i++;
			}
			return codes;
		}
		db.close();
		return null;
	}

	public String[] getVehiclesByPermit(String perNum) {
		String[] vehicles = null;

		String query = "SELECT veh_plate FROM PERMIT_VEHICLE WHERE PER_NUMBER = \"" + perNum + "\"";
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);
	
		int i = 0;
		if (cursor.getCount() > 0) {
			String[] codes = new String[cursor.getCount()];
			while (cursor.moveToNext()) {
				codes[i] = cursor.getString(cursor.getColumnIndex("veh_plate"));
				i++;
			}
			return codes;
		}
		db.close();
		return vehicles;
	}	
}
