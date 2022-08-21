package dbConnectorTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Farmer;
import model.MyResponse;
import model.PhData;
import utility.Constants;

public class FarmerTable {
	
	public MyResponse insertData(Farmer farmer){
		MyResponse mr = new MyResponse();
		int insertedId;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);

			
			String sqlGenerated = "INSERT INTO `farmer` "
					+ " (`farmerId`, `full_name`, `address`, `phNo`, `length`, `breadth`, `unit_size`, `radius`, `location`) "
					+ "VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?)";
			
			PreparedStatement myStmt = myConn.prepareStatement(sqlGenerated);
			
			myStmt.setString(1, farmer.farmerId);
			myStmt.setString(2, farmer.fullName);
			myStmt.setString(3, farmer.address);
			myStmt.setString(4, farmer.phNo);
			myStmt.setInt(5, farmer.length);
			myStmt.setInt(6, farmer.breadth);
			myStmt.setString(7, farmer.unit_size);
			myStmt.setString(8, farmer.radius);
			myStmt.setString(9, farmer.strLocWithGPS);
			

			//java.sql.Timestamp timestamp = sdf.parse("apple");
			//myStmt.setTimestamp(3, parkingData.in_time);
			/*SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date jvDate = simpleDateFormat.parse(strDate);*/
			
			/*Date currentDT = new Date();
            java.sql.Date dt = new java.sql.Date(currentDT.getTime());
			myStmt.setDate(3,dt);
			myStmt.setString(4, parkingData.parking_spot);
			myStmt.setString(5, parkingData.user_name);
			myStmt.setString(6, parkingData.device_name);
			myStmt.setString(7, parkingData.extra_info);
			myStmt.setInt(8, parkingData.paid);*/
			insertedId =myStmt.executeUpdate();
			myConn.close();
		}catch(Exception e){
			System.out.println("Error in trying to insert Data");
			System.out.println(e.toString());
			mr.status = MyResponse.StatusError;
			mr.errorCode=1;
			
			mr.message= "Error in trying to insert Data";
			
		return mr;	
		}
		mr.status = MyResponse.StatusSuccess;
		mr.statusCode = insertedId;
		mr.message= "Data Entered Successfully";
		System.out.println("Data Entered Successfully at: "+String.valueOf(insertedId));
		return mr;
	}
	
	
	
	public ArrayList<Farmer> getFarmerList() throws Exception {//java.sql.Date dt
		ResultSet myRs = null;
		ArrayList<Farmer> farmerList = new ArrayList<>();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);
		String prepareQuery = "SELECT * FROM farmer WHERE active_state = ?";
		
		System.out.println(prepareQuery);
		
		PreparedStatement myStmt = myConn.prepareStatement(prepareQuery);
		// ------
		//myStmt.setDate(1, farmerId);
		myStmt.setInt(1, Farmer.ACTIVE_STATE);
		myRs = myStmt.executeQuery();
		
		farmerList = extractDataList(myRs);
		System.out.println("No of Data: "+ farmerList.size());
		
		myConn.close();
		return farmerList;
	}
	
	
	public Farmer getFarmerData(String farmerId) throws Exception {//java.sql.Date dt
		ResultSet myRs = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);
		String prepareQuery = "SELECT * FROM farmer WHERE farmerId = ?";
		
		System.out.println(prepareQuery);
		
		PreparedStatement myStmt = myConn.prepareStatement(prepareQuery);
		// ------
		//myStmt.setDate(1, farmerId);
		myStmt.setString(1, farmerId);
		myRs = myStmt.executeQuery();
		
		Farmer theFarmer = extractData(myRs);
		
		
		System.out.println("SUCCES, Farmer SQL INDEX: "+theFarmer.sqlIndex);
		myConn.close();
		return theFarmer;
	}
	
	
	
	
	public boolean updateData() {
		return false;
	}
	
	
	
	
	//###########################################################################################
	
	private Farmer extractData(ResultSet myRs) throws SQLException {
		myRs.first();
		
		Farmer farmer = new Farmer();
		//(`id`, `farmerId`, `time`, `phData`) "
		
		farmer.sqlIndex =  myRs.getInt("id");
		farmer.farmerId= myRs.getString("farmerId");
		farmer.fullName= myRs.getString("full_name");
		farmer.address= myRs.getString("address");
		farmer.phNo= myRs.getString("phNo");
			 
		farmer.length= myRs.getInt("length");
		farmer.breadth= myRs.getInt("breadth");
		farmer.unit_size= myRs.getString("unit_size");
		farmer.radius= myRs.getString("radius");
		farmer.strLocWithGPS= myRs.getString("location");
		
		
		return farmer;
	}
	
	private ArrayList<Farmer> extractDataList(ResultSet myRs) throws SQLException {
		ArrayList<Farmer> farmerList = new ArrayList<>();
		while (myRs.next()) {
			Farmer farmer = new Farmer();
			//(`id`, `farmerId`, `time`, `phData`) "
			farmer.sqlIndex =  myRs.getInt("id");
			farmer.farmerId= myRs.getString("farmerId");
			farmer.fullName= myRs.getString("full_name");
			farmer.address= myRs.getString("address");
			farmer.phNo= myRs.getString("phNo");
				 
			farmer.length= myRs.getInt("length");
			farmer.breadth= myRs.getInt("breadth");
			farmer.unit_size= myRs.getString("unit_size");
			farmer.radius= myRs.getString("radius");
			farmer.strLocWithGPS= myRs.getString("location");
			farmerList.add(farmer);
		}while (myRs.next());
		 
		return farmerList;
	}

}


