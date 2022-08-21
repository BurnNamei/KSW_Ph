package burn.krt_f;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dbConnectorTable.PhDataTable;
import dbConnectorTable.FarmerTable;
import model.Farmer;
import model.LocationData;
import model.MyResponse;
import model.PhData;

//@Path("/farmer_entry")
@Produces(MediaType.APPLICATION_JSON)
public class FormEntryFarmer {
	
	@POST
	public MyResponse enterFarmerData
								(@FormParam("name") String name,
								 @FormParam("address") String address,
								 @FormParam("phNo") String phNo,
								 
								 
								 @FormParam("length") String length,
								 @FormParam("breadth") String breadth,
								 @FormParam("unit_size") String unit_size,
								 @FormParam("radius") String radius,
								 
								 @FormParam("1lat") String l1,
								 @FormParam("2lat") String l2,
								 @FormParam("3lat") String l3,
								 @FormParam("4lat") String l4,
								 @FormParam("5lat") String l5,
								 @FormParam("6lat") String l6,
								 @FormParam("7lat") String l7,
								 @FormParam("8lat") String l8,
								 @FormParam("9lat") String l9,
								 
								 @FormParam("1long") String lo1,
								 @FormParam("2long") String lo2,
								 @FormParam("3long") String lo3,
								 @FormParam("4long") String lo4,
								 @FormParam("5long") String lo5,
								 @FormParam("6long") String lo6,
								 @FormParam("7long") String lo7,
								 @FormParam("8long") String lo8,
								 @FormParam("9long") String lo9){
		
		
		
//************************************************************************		
		MyResponse myResponse = new MyResponse();
		myResponse.status= MyResponse.StatusError;
		
		System.out.println("Farmer Name: " + name);
		//Checking validation
		if(name.equals("")) {	
			myResponse.message = "Farmer Name can not be blank ";
			return myResponse;
		}
		
		phNo= phNo.replace(" ", ""); // Removing blank space
		if(phNo.equals("")) {
			myResponse.message = "Phone No can not be blank ";
			return myResponse;
		}else if(phNo.length()!=10){
				myResponse.message = "Phone No should have 10 digit";
				return myResponse;
		}else {
			try {
				Integer no = Integer.parseInt(phNo);
			}catch (Exception e) {
				// TODO: handle exception
				myResponse.message = "Phone Number should contain only Number ";
				return myResponse;
			}
			
		}
		
		
		if(length.equals("")) {	
			myResponse.message = "Length can not be blank ";
			return myResponse;
		}
		
		if(breadth.equals("")) {	
			myResponse.message = "Breadth can not be blank ";
			return myResponse;
		}
		
		
		
		
		int lengthValue=0,breadthValue=0;
		
		try {
			length= length.replace(" ", "");
			breadth= breadth.replace(" ", "");
			lengthValue= Integer.parseInt(length);
			breadthValue=Integer.parseInt(breadth) ;
		}catch (Exception e) {
			// TODO: handle exception
			myResponse.message = "Length and Breadth should contain only Number ";
			return myResponse;
		}
		
		Farmer farmer= new Farmer();

		farmer.farmerId = name;
		farmer.address=address;
		farmer.phNo= phNo;
		farmer.length= lengthValue;
		farmer.breadth=breadthValue;
		farmer.unit_size= unit_size;
		farmer.radius= radius;
		

		//----------------------------------------------
		
		String inputLat[] = {l1,l2,l3,l4,l5,l6,l7,l8,l9};
		String inputLong[] = {lo1,lo2,lo3,lo4,lo5,lo6,lo7,lo8,lo9};
		
		ArrayList<LocationData> locationDataList = new ArrayList<>();
		LocationData locationData;
		
		for(int i=0;i<inputLat.length;i++) {
			locationData = LocationData.checkValidationAndReturnLocationData(String.valueOf(i+1), inputLat[i], inputLong[i]);
			if(locationData==null) {
				myResponse.status= MyResponse.StatusError;
				myResponse.message = "Error in input location cordinate of spot No: "+String.valueOf(i+1);
				return myResponse;
			}
			locationDataList.add(locationData);
		}
		
		String allPhData="";
		for(int i=0;i<locationDataList.size();i++) {
			allPhData = allPhData + locationDataList.get(i).spotId + locationDataList.get(i).latitude + locationDataList.get(i).longitude;
			if(i!=locationDataList.size()-1) {
				allPhData = allPhData + ","; 
			}
		}
		System.out.println("All Cordinate Data: "+allPhData);
		farmer.strLocWithGPS = allPhData;
		
		//DataTable dt = new DataTable();
		//PhData phData = new PhData(farmerName,allPhData);
		//MyResponse myResponse = dt.insertData(phData);
		
		FarmerTable farmerTable = new FarmerTable();
		myResponse = farmerTable.insertData(farmer);
		
		//MyResponse myResponse = new MyResponse();
		//myResponse.message="TESTING TESTING";
		
		return myResponse;
	}

}
