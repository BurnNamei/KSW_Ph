package model;



import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PhData {

	@JsonIgnore
	public int id;
	@JsonIgnore
	public String farmerId;
	public Date dataDate;
	public ArrayList<LocationData> locDataList;
	
	@JsonIgnore
	public String strPhData;
	
	//----------------------------------------------------------
	
	public PhData(){
		locDataList = new ArrayList<>();
	}
	
	
	public PhData(String farmerId, String strPhData) {
		this.farmerId = farmerId;
		this.strPhData = strPhData;
		}
	
	//This is used for testing
	public boolean generateLocDataList() {
		locDataList= new ArrayList<>();
		String phDataArray[] = strPhData.split(",");		

		for(int i=0;i<phDataArray.length;i++) {
			LocationData locationData = new LocationData();
			locationData.spotId=String.valueOf(i+1);
			locationData.insertPhData(phDataArray[i]);
			locDataList.add(locationData);
		}
		return true;
	}
	
	public boolean generateLocDataList(String cordinateInputs) {
		String cordinateArray[] = cordinateInputs.split(",");
		String phDataArray[] = strPhData.split(",");
		
		if(cordinateArray.length!=phDataArray.length) {
			System.out.println("Data miss match...");
			return false;
		}
		
		//==================
		
		locDataList = LocationData.generateLocationDataList(cordinateInputs);
		for(int i=0;i<phDataArray.length;i++) {
			locDataList.get(i).insertPhData(phDataArray[i]);
		}
		return true;
	}
}
