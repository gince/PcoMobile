// The Lookup model, which is an abstract class for lookup tables
package edu.umass.parking.pcomobile.models;

public class Lookup {

	int _id;
	String _code;
	String _description;

	// empty constructor
	public Lookup() {

	}

	// constructor that takes all three attributes
	public Lookup(int id, String code, String description) {
		this._id = id;
		this._code = code;
		this._description = description;
	}

	public Lookup(String code, String description) {
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
