package model;

import java.util.ArrayList;

public class LocationData {
	
	public String spotId;
	public String latitude;
	public String longitude;
	public String phData;
	
	
	public LocationData() {
		
	}
	
	
	public LocationData(String spotId,String lat,String lon) {
		this.spotId = spotId;
		this.latitude =lat;
		this.longitude =lon;
	}
	
	public void insertPhData(String locationIdWithPh) {
		
		String aka[] = locationIdWithPh.split(":");
		String spotId=aka[0];
		String phValue=aka[1];
		this.phData = phValue;
	}
	
	
	//===================  STATIC METHOD
	
	public static LocationData checkValidationAndReturnLocationData(String spotNo,String lat,String lon) {
		LocationData locationData = null;
		
		if(lat.equals("")||lon.equals("")) { //Checking for blank Input
			return locationData;
		}
		
		
		try {
			// Removing Blank Space
			spotNo = spotNo.replace(" ", "");
			lat = lat.replace(" ", "");
			lon = lon.replace(" ", "");
			
			Integer.parseInt(spotNo);
			Float.parseFloat(lat);
			Float.parseFloat(lon);
			
			
			locationData = new LocationData();
			locationData.spotId=spotNo;
			locationData.latitude=lat;
			locationData.longitude=lon;
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in entering location Data for Spot No: "+ spotNo);
		}
		
		return locationData;
	}
	
	
	public static ArrayList<LocationData> generateLocationDataList(String cordinateInputs) {
		String cordinateArray[] = cordinateInputs.split(",");
		ArrayList<LocationData> locDataList = new ArrayList<>();
		
		for(int i=0;i<cordinateArray.length;i++) {
			LocationData locationData= new LocationData();
			String aka[]= cordinateArray[i].split("&");
			locationData.spotId = aka[0];
			locationData.latitude=aka[1];
			locationData.longitude=aka[2];
			locDataList.add(locationData);
		}
		
		return locDataList;
	}
	

}
