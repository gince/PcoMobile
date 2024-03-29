// The state model
package edu.umass.parking.pcomobile.models;

public class State {

	int _id;
	String _code;
	String _description;

	// empty constructor
	public State() {

	}

	// constructor that takes all three attributes
	public State(int id, String code, String description) {
		this._id = id;
		this._code = code;
		this._description = description;
	}

	public State(String code, String description) {
		this._code = code;
		this._description = description;
	}
	
	public int getID() {
		return this._id;
	}
	
	public void setID(int id) {
		this._id = id;
	}
	
	public String getDescription() {
		return this._description;
	}
	
	public void setDescription(String desc) {
		this._description = desc;
	}
	
	public String getCode() {
		return this._code;
	}

	public void setCode(String code) {
		this._code = code;
	}
	
}
