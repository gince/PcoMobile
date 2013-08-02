package edu.umass.parking.pcomobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.umass.parking.pcomobile.models.PermitStatus;
import edu.umass.parking.pcomobile.models.PermitVehicle;
import edu.umass.parking.pcomobile.models.State;
import edu.umass.parking.pcomobile.models.VehicleColor;
import edu.umass.parking.pcomobile.models.VehicleMake;
import edu.umass.parking.pcomobile.models.VehiclePlateType;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1; // may be deleted
	private static final String DATABASE_NAME = "pcoMobile";

	// attributes of staff table
	private static final String TABLE_STAFF = "staff";
	public static final String COLUMN_STAFF_ID = "id";
	public static final String COLUMN_STAFF_CODE = "code";
	public static final String COLUMN_STAFF_DESC = "description";

	// attributes of states table
	private static final String TABLE_STATES = "states";
	public static final String COLUMN_STATE_ID = "id";
	public static final String COLUMN_STATE_CODE = "code";
	public static final String COLUMN_STATE_DESC = "description";

	// attributes of vehicle color table
	private static final String TABLE_VEH_COLORS = "vehicle_colors";
	public static final String COLUMN_VEH_COLOR_ID = "id";
	public static final String COLUMN_VEH_COLOR_CODE = "code";
	public static final String COLUMN_VEH_COLOR_DESC = "description";

	// attributes of vehicle make table
	private static final String TABLE_VEH_MAKES = "vehicle_makes";
	public static final String COLUMN_VEH_MAKE_ID = "id";
	public static final String COLUMN_VEH_MAKE_CODE = "code";
	public static final String COLUMN_VEH_MAKE_DESC = "description";

	// attributes of vehicle plate type table
	private static final String TABLE_PLATE_TYPES = "plate_types";
	public static final String COLUMN_PLATE_TYPE_ID = "id";
	public static final String COLUMN_PLATE_TYPE_CODE = "code";
	public static final String COLUMN_PLATE_TYPE_DESC = "description";

	// attributes of permit status table
	private static final String TABLE_PERMIT_STATUS = "permit_status";
	public static final String COLUMN_PERMIT_STATUS_ID = "id";
	public static final String COLUMN_PERMIT_STATUS_CODE = "code";
	public static final String COLUMN_PERMIT_STATUS_DESC = "description";

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

		// query that creates STATES table
		String createStatesTable = "CREATE TABLE " + TABLE_STATES + "("
				+ COLUMN_STATE_ID + " INTEGER, " + COLUMN_STATE_CODE
				+ " TEXT, " + COLUMN_STATE_DESC + " TEXT);";

		// query that creates TABLE_VEH_COLORS table
		String createVehicleColorsTable = "CREATE TABLE " + TABLE_VEH_COLORS
				+ "(" + COLUMN_VEH_COLOR_ID + " INTEGER, "
				+ COLUMN_VEH_COLOR_CODE + " TEXT, " + COLUMN_VEH_COLOR_DESC
				+ " TEXT);";

		// query that creates TABLE_VEH_MAKES table
		String createVehicleMakesTable = "CREATE TABLE " + TABLE_VEH_MAKES
				+ "(" + COLUMN_VEH_MAKE_ID + " INTEGER, "
				+ COLUMN_VEH_MAKE_CODE + " TEXT, " + COLUMN_VEH_MAKE_DESC
				+ " TEXT);";

		// query that creates TABLE_PLATE_TYPES table
		String createPlateTypesTable = "CREATE TABLE " + TABLE_PLATE_TYPES
				+ "(" + COLUMN_PLATE_TYPE_ID + " INTEGER, "
				+ COLUMN_PLATE_TYPE_CODE + " TEXT, " + COLUMN_PLATE_TYPE_DESC
				+ " TEXT);";

		// query that creates TABLE_PERMIT_STATUS table
		String createPermitStatusTable = "CREATE TABLE " + TABLE_PERMIT_STATUS
				+ "(" + COLUMN_PERMIT_STATUS_ID + " INTEGER, "
				+ COLUMN_PERMIT_STATUS_CODE + " TEXT, "
				+ COLUMN_PERMIT_STATUS_DESC + " TEXT);";

		// query that creates TABLE_PERMIT_VEHICLE table
		String createPermitVehicleTable = "CREATE TABLE "
				+ TABLE_PERMIT_VEHICLE + "(" + COLUMN_PERVEH_ID + " INTEGER, "
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
		onCreate(db);
	}

	// ADD functions
	public void addState(State state) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_STATE_ID, state.getID());
		values.put(COLUMN_STATE_CODE, state.getCode());
		values.put(COLUMN_STATE_DESC, state.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_STATES, null, values);
		db.close();
	}

	public void addVehColor(VehicleColor color) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_VEH_COLOR_ID, color.getID());
		values.put(COLUMN_VEH_COLOR_CODE, color.getCode());
		values.put(COLUMN_VEH_COLOR_DESC, color.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_VEH_COLORS, null, values);
		db.close();
	}

	public void addVehMake(VehicleMake make) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_VEH_MAKE_ID, make.getID());
		values.put(COLUMN_VEH_MAKE_CODE, make.getCode());
		values.put(COLUMN_VEH_MAKE_DESC, make.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_VEH_MAKES, null, values);
		db.close();
	}

	public void addState(VehiclePlateType type) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_PLATE_TYPE_ID, type.getID());
		values.put(COLUMN_PLATE_TYPE_CODE, type.getCode());
		values.put(COLUMN_PLATE_TYPE_DESC, type.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_PLATE_TYPES, null, values);
		db.close();
	}

	public void addPermitStatus(PermitStatus status) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_PERMIT_STATUS_ID, status.getID());
		values.put(COLUMN_PERMIT_STATUS_CODE, status.getCode());
		values.put(COLUMN_PERMIT_STATUS_DESC, status.getDescription());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_PERMIT_STATUS, null, values);
		db.close();
	}

	public void addState(PermitVehicle perveh) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_PERVEH_ID, perveh.getID());
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

	public State getState(String scode) {
		String query = "Select * FROM " + TABLE_STATES + " WHERE "
				+ COLUMN_STATE_CODE + " =  \"" + scode + "\"";

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
