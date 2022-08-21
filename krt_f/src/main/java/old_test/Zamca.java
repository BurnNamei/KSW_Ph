package old_test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Cat;



public class Zamca {
	
	
/*	@GET
    //@Produces("application/json")
	@Produces(MediaType.APPLICATION_JSON)
    public MyResponse getIt() {
		String cat = "This should work very fast";
		MyResponse mr = new MyResponse();
		mr.status = MyResponse.StatusSuccess;
		mr.statusCode = 21;
		mr.message= "Data Entered Successfully";
        return mr;
    }*/
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)//@Produces("application/json")
    public Cat getNow() {
		Cat cat = new Cat();
        return cat;
    }
	
	


}
