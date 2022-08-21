package burn.krt_f;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dbConnectorTable.PhDataTable;
import model.MyResponse;
import model.PhData;

//@Path("/ph_entry")
@Produces(MediaType.APPLICATION_JSON)
public class FormEntryPh {
	
	@POST
	public MyResponse enterPhData(@FormParam("FarmerId") String farmerName,
								 @FormParam("L1") String l1,
								 @FormParam("L2") String l2,
								 @FormParam("L3") String l3,
								 @FormParam("L4") String l4,
								 @FormParam("L5") String l5,
								 @FormParam("L6") String l6,
								 @FormParam("L7") String l7,
								 @FormParam("L8") String l8,
								 @FormParam("L9") String l9) {
		
		String allPhData=
				"[1:"+l1+","
				+"2:"+l2+","
				+"3:"+l3+","
				+"4:"+l4+","
				+"5:"+l5+","
				+"6:"+l6+","
				+"7:"+l7+","
				+"8:"+l8+","
				+"9:"+l9+"]";
		
		System.out.println("FarmerId: " + farmerName);
		System.out.println("All Ph Data: " + allPhData);
		
		//1 Convert into App Class Structure JSON formate
		/*
		 * String jsonProgramsData = InputConverter.convertToJSON(programs);
		 * RadioProgram rp = new RadioProgram();
		 * 
		 * Helper hp= new Helper(); rp.date = hp.extractFromWord(date); rp.details =
		 * jsonProgramsData; //2 Enter to DB RadioProgramConnector radioProgramConnector
		 * = new RadioProgramConnector(); boolean result=false; switch(channelName) {
		 * case Constants.channelNameSangai: result =
		 * radioProgramConnector.insertRadioProgram(rp,
		 * MyProjectConstants.channelCodeSangai); break;
		 * 
		 * case Constants.channelNameKangla: result =
		 * radioProgramConnector.insertRadioProgram(rp,
		 * MyProjectConstants.channelCodeKangla); break; }
		 *  
		*/
		
		PhDataTable dt = new PhDataTable();
		PhData phData = new PhData(farmerName,allPhData);
		MyResponse myResponse = dt.insertData(phData);
		
		return myResponse;
	}

}