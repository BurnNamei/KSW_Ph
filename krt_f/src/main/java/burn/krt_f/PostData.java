package burn.krt_f;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.Consumes;
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
import model.PostDataPh;

@Path("postData")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostData {

	@Path("/farmer")
	@POST
	public MyResponse enterFarmerData(Farmer farmer){
		
		//************************************************************************		
		MyResponse myResponse = new MyResponse();
		myResponse.status= MyResponse.StatusError;
		
		System.out.println("Farmer Name: " + farmer.farmerId);
		//Checking validation
		if(farmer.farmerId.equals("")) {	
		myResponse.message = "Farmer Name can not be blank ";
		return myResponse;
		}
		
		farmer.phNo= farmer.phNo.replace(" ", ""); // Removing blank space
		if(farmer.phNo.equals("")) {
		myResponse.message = "Phone No can not be blank ";
		return myResponse;
		}else if(farmer.phNo.length()!=10){
		myResponse.message = "Phone No should have 10 digit";
		return myResponse;
		}else {
		try {
		Integer no = Integer.parseInt(farmer.phNo);
		}catch (Exception e) {
		// TODO: handle exception
		myResponse.message = "Phone Number should contain only Number ";
		return myResponse;
		}
		
		}
				
		//----------------------------------------------
		//DataTable dt = new DataTable();
		//PhData phData = new PhData(farmerName,allPhData);
		//MyResponse myResponse = dt.insertData(phData);
		
		FarmerTable farmerTable = new FarmerTable();
		myResponse = farmerTable.insertData(farmer);
		
		//MyResponse myResponse = new MyResponse();
		//myResponse.message="TESTING TESTING";
		
		return myResponse;
		}
	
	
	
	@Path("/phData")
	@POST
	public MyResponse enterPhData(PostDataPh postDataPh){
		
		//************************************************************************		
		MyResponse myResponse = new MyResponse();
		myResponse.status= MyResponse.StatusError;
		
		
		System.out.println("Farmer ID: "+postDataPh.farmerId);
		System.out.println("Date: "+postDataPh.strDataDate);
		
		
		/*if(postDataPh.dataDate==null) {
			System.out.println("Data Send for Today ");
			
			Calendar c = Calendar.getInstance();
			postDataPh.dataDate =  new java.sql.Date(c.getTime().getTime());
		}*/
		
		
		
/*		ObjectMapper Obj = new ObjectMapper();
		String jsonStr="";
		try {
			jsonStr = Obj.writeValueAsString(postDataPh);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		//----------------------------------------------
		PhDataTable dt = new PhDataTable();
		myResponse = dt.insertData(postDataPh);
		
		//MyResponse myResponse = new MyResponse();
		//myResponse.message="TESTING TESTING";
		
		return myResponse;
		}
	
	
	
	
	//----------------------------------------------------------------
	
	@Path("/gika")
	@POST
	public MyResponse enterPhData(PhData phData){
	
		PhDataTable dt = new PhDataTable();
		MyResponse myResponse = dt.insertData(phData);
		return myResponse;
		
	}
	
	
	
	
	
}
