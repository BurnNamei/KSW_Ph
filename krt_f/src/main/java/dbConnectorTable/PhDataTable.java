package dbConnectorTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import model.MyResponse;
import model.PhData;
import model.PostDataPh;
import utility.Constants;


public class PhDataTable {
	
	
	public MyResponse insertData(PostDataPh postDataPh){  // This method is used while pushing from application
		MyResponse mr = new MyResponse();
		int insertedId;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);
			
			String sqlGenerated;
			PreparedStatement myStmt;
			
			
			sqlGenerated = "INSERT INTO `farm_ph_value` "
					+ " (`farmerId`, `date`, `phData`) "
					+ "VALUES ( ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sqlGenerated);
	       
			
			myStmt.setString(1, postDataPh.farmerId);
			if(postDataPh.strDataDate==null) {
				myStmt.setDate(2,getTodaysIndianDate());
			}else {
				System.out.println("INPUT DATE: "+postDataPh.strDataDate);
				
				SimpleDateFormat sdf = new SimpleDateFormat(Constants.commonStrDateFormate);
		        java.util.Date date = sdf.parse(postDataPh.strDataDate); // Returns a Date format object with the pattern
		        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
				
		        myStmt.setDate(2,sqlStartDate);
				
				
			}
			myStmt.setString(3, postDataPh.generateDbPhValueStorageText());

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
	
	
	
	public MyResponse insertData(PhData phData){
		MyResponse mr = new MyResponse();
		int insertedId;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);
			
			String sqlGenerated;
			PreparedStatement myStmt;
			
			
			sqlGenerated = "INSERT INTO `farm_ph_value` "
					+ " (`farmerId`, `date`, `phData`) "
					+ "VALUES ( ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sqlGenerated);
			
			Date currentDT = new Date();
	        java.sql.Date dt = new java.sql.Date(currentDT.getTime());
			
			
			myStmt.setString(1, phData.farmerId);
			myStmt.setDate(2, dt);
			myStmt.setString(3, phData.strPhData);

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
	
	
	
	public ArrayList<PhData> getDataList(String farmerId) throws Exception {//java.sql.Date dt
		ResultSet myRs = null;
		ArrayList<PhData> dataList = new ArrayList<>();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConn = DriverManager.getConnection(Constants.url_v1, Constants.user_v1, Constants.myPassword_v1);
		String prepareQuery = "SELECT * FROM farm_ph_value WHERE farmerId = ?";
		
		System.out.println(prepareQuery);
		
		PreparedStatement myStmt = myConn.prepareStatement(prepareQuery);
		// ------
		//myStmt.setDate(1, farmerId);
		myStmt.setString(1, farmerId);
		myRs = myStmt.executeQuery();
		
		dataList = extractDataList(myRs);
		System.out.println("No of Data: "+ dataList.size());
		
		myConn.close();
		return dataList;
	}
	
	
	
	
	public boolean updateData() {
		return false;
	}
	
	
	
	
	//#################################  INNER METHODS   ##########################################################
	
	
	private java.sql.Date getTodaysIndianDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //Here you say to java the initial timezone. This is the secret
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //Will print in UTC
        String allTimAsText = sdf.format(calendar.getTime());
        System.out.println("allTimAsText: "+ allTimAsText);
        
        java.util.Date date = sdf.parse(allTimAsText); // Returns a Date format object with the pattern
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return sqlStartDate;
	}
	
	
	private PhData extractData(ResultSet myRs) throws SQLException {
		PhData phData = new PhData();
		//(`id`, `farmerId`, `time`, `phData`) "
		phData.id = myRs.getInt("id");
		phData.farmerId = myRs.getString("farmerId");
		phData.dataDate = myRs.getDate("date");
		phData.strPhData = myRs.getString("phData");
		return phData;
	}
	
	private ArrayList<PhData> extractDataList(ResultSet myRs) throws SQLException {
		ArrayList<PhData> dataList = new ArrayList<>();
		while (myRs.next()) {
			PhData phData = new PhData();
			//(`id`, `farmerId`, `time`, `phData`) "
			phData.id = myRs.getInt("id");
			phData.farmerId = myRs.getString("farmerId");
			phData.dataDate =  myRs.getDate("date");
			phData.strPhData = myRs.getString("phData");
			dataList.add(phData);
		}while (myRs.next());
		 
		return dataList;
	}

}
