// A model for permit vehicle combinations
package edu.umass.parking.pcomobile.models;

public class PermitVehicle {
	int _id;
	String 	_perNmbr;
	String 	_perStrt;
	String 	_perExpr;
	int 	_perStts;
	int 	_vehState;
	int		_vehType;
	int		_vehColor;
	int		_vehMake;
	String	_vehPlate;
	
	// Constructors
	public PermitVehicle() {
		
	}
	
	public PermitVehicle(String perNmbr, String perStrt, String perExpr, int perStts) {
		this._perNmbr = perNmbr;
		this._perStrt = perStrt;
		this._perExpr = perExpr;
		this._perStts = perStts;
	}

	public PermitVehicle(String perNmbr, String perStrt, String perExpr, int perStts, int vehState, int vehType, int vehColor, int vehMake, String vehPlate) {
		this._perNmbr = perNmbr;
		this._perStrt = perStrt;
		this._perExpr = perExpr;
		this._perStts = perStts;
		this._vehState = vehState;
		this._vehType = vehType;
		this._vehColor = vehColor;
		this._vehMake = vehMake;
		this._vehPlate = vehPlate;
	}

	// Getters
	public int getID() {
		return this._id;
	}
	
	public String getPerNumber() {
		return this._perNmbr;
	}

	public String getPerStartDate() {
		return this._perStrt;
	}
	
	public String getPerExpireDate() {
		return this._perExpr;
	}
	
	public int getPerStatus() {
		return this._perStts;
	}
	
	public int getVehState() {
		return this._vehState;
	}
	
	public int getVehType() {
		return this._vehType;
	}
	
	public int getVehColor() {
		return this._vehColor;
	}
	
	public int getVehMake() {
		return this._vehMake;
	}
	
	public String getVehPlate() {
		return this._vehPlate;
	}
	
	// Setters
	public void setPerNumber(String perNmbr) {
		this._perNmbr = perNmbr;
	}

	public void setPerStartDate(String perStrt) {
		this._perStrt = perStrt;
	}
	
	public void setPerExpireDate(String perExpr) {
		this._perExpr = perExpr;
	}
	
	public void setPerStatus(int perStts) {
		this._perStts = perStts;
	}
	
	public void setVehState(int vehState) {
		this._vehState = vehState;
	}
	
	public void setVehType(int vehType) {
		this._vehType = vehType;
	}
	
	public void setVehColor(int vehColor) {
		this._vehColor = vehColor;
	}
	
	public void setVehMake(int vehMake) {
		this._vehMake = vehMake;
	}
	
	public void setVehPlate(String vehPlate) {
		this._vehPlate = vehPlate;
	}
	
}
