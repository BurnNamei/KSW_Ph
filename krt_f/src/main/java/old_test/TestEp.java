package old_test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import model.MyResponse;


public class TestEp {
	
	
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
    //@Produces("application/json")
	@Produces(MediaType.APPLICATION_JSON)
    public Cat getNow() {
		Cat cat = new Cat();
        return cat;
    }
	
	

	public class Cat {
		public String name = "mew";
		public String address ="sumang mamang";
		public int no=21;
		
		public Cat() {
			this.name = "miko";
			this.address="luka dara";
			this.no=51;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
	}

}
