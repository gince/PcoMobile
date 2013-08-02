// The Vehicle Color model
package edu.umass.parking.pcomobile.models;

public class VehicleColor {

	int _id;
	String _code;
	String _description;

	// empty constructor
	public VehicleColor() {

	}

	// constructor that takes all three attributes
	public VehicleColor(int id, String code, String description) {
		this._id = id;
		this._code = code;
		this._description = description;
	}

	public VehicleColor(String code, String description) {
		this._code = code;
		this._description = description;
	}
	
	// getters
	public int getID() {
		return this._id;
	}
	
	public String getCode() {
		return this._code;
	}
	
	public String getDescription() {
		return this._description;
	}
	
	// setters
	public void setID(int id) {
		this._id = id;
	}
	
	public void setCode(String code) {
		this._code = code;
	}
	
	public void setDescription(String desc) {
		this._description = desc;
	}

}
