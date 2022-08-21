package model;

import java.util.ArrayList;

import javax.ws.rs.FormParam;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Farmer {

		public static final int ACTIVE_STATE=1;
		public static final int DE_ACTIVE_STATE=0;
		
		public int sqlIndex;
		public String farmerId;
		public String fullName;
		public String address;
		public String phNo;
			 
		public int length;
		public int breadth;
		public String unit_size;
		public String radius;
		
		@JsonIgnore
		public String strLocWithGPS;
			 
		public ArrayList<LocationData> locationDataList;
		
		
		
		
		public void generateLocationDataList() {
			System.out.println("Trying to Gen location Data List");
			System.out.println("strLocWithGPS: " + strLocWithGPS);
			locationDataList = LocationData.generateLocationDataList(strLocWithGPS);
		}
	
		
		
		/*public void setGPSCordinate(String inputData) {
			locWithGPS = new ArrayList<>();
			
			String inpData[] = inputData.split("&");
			for(int i =0; i<inpData.length; i++) {
				
				locWithGPS.add(inpData[i]);
				System.out.println(inpData[i]);
				
				String locationAndData[] = inpData[i].split(":");
				String location = locationAndData[0];
				String data = locationAndData[1];
				
				
			}
			
		}*/
		
		
		/*public void setStrGPS(ArrayList<String> locWithGPS) {
			String data="";
			for(int i=0;i<locWithGPS.size();i++) {
				if(i!=locWithGPS.size()-1) {
					data = data + locWithGPS.get(i)+"&"; 
				}else {
					data = data + locWithGPS.get(i); 
				}
				
			}
			strLocWithGPS = data;
		}*/
		
	
}
