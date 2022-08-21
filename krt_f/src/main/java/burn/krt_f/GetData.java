package burn.krt_f;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dbConnectorTable.PhDataTable;
import dbConnectorTable.FarmerTable;
import model.Farmer;
import model.MyResponse;
import model.PhData;
import old_test.TestEp.Cat;
import responseModel.PhDataListResponse;

@Path("/getData")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetData {
	
	/*@Path("/PhValue")
	@GET
    public ArrayList<PhData> getParkingList(@QueryParam("farmerId") String farmerId) {
		System.out.println("GET FUNCTION CALLED ");
		ArrayList<PhData> dataList = new ArrayList<>();
		
		try {
			//SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
	        //java.util.Date jvDate = simpleDateFormat.parse(strDate);
	        //java.sql.Date dt = new java.sql.Date(jvDate.getTime());
	        
	        DataTable dataTable = new DataTable();
	        dataList = dataTable.getDataList(farmerId);
		}catch(Exception e) {
			System.out.println("ERROR ");
			System.out.println(e.toString());
		}
		return dataList;
    }*/
	
	@Path("/PhValue")
	@GET
    public PhDataListResponse getParkingList(@QueryParam("farmerId") String farmerId) {
		System.out.println("GET FUNCTION CALLED ");
		ArrayList<PhData> dataList = new ArrayList<>();
		PhDataListResponse phDataListResponse = new PhDataListResponse();
		
		Farmer theFarmer=null;
		try {
			//SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
	        //java.util.Date jvDate = simpleDateFormat.parse(strDate);
	        //java.sql.Date dt = new java.sql.Date(jvDate.getTime());
	        
	        PhDataTable dataTable = new PhDataTable();
	        dataList = dataTable.getDataList(farmerId);
	        
	        FarmerTable farmerTable = new FarmerTable();
	        theFarmer  = farmerTable.getFarmerData(farmerId);
		}catch(Exception e) {
			System.out.println("ERROR ");
			System.out.println(e.toString());
		}
		if(theFarmer!=null) {
			//--- Assigning the MOdel
			for(int i=0;i<dataList.size();i++){
				//dataList.get(i).generateLocDataList();
				dataList.get(i).generateLocDataList(theFarmer.strLocWithGPS);
			}
		}
		
		phDataListResponse.farmerId = farmerId;
		phDataListResponse.dataList = dataList;
		
		return phDataListResponse;
    }
	
	
	
	@Path("/activeFarmerList")
	@GET
    public ArrayList<Farmer> getActiveFarmers() {
		System.out.println("GET ACTIVE FARMERS CALLED ");
		ArrayList<Farmer> farmerList = new ArrayList<>();
		
		try {
			//SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
	        //java.util.Date jvDate = simpleDateFormat.parse(strDate);
	        //java.sql.Date dt = new java.sql.Date(jvDate.getTime());
	        
	        FarmerTable farmerTable = new FarmerTable();
	        farmerList = farmerTable.getFarmerList();
	        
	        //GENERATE LOCATION_DATA LIST
	        for(int i=0;i<farmerList.size();i++) {
	        	farmerList.get(i).generateLocationDataList();
	        }
	        
	        
		}catch(Exception e) {
			System.out.println("ERROR ");
			System.out.println(e.toString());
		}
		return farmerList;
    }

}
