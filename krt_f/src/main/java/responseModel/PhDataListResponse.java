package responseModel;

import java.util.ArrayList;

import model.PhData;

public class PhDataListResponse extends BaseResponseClass {
	
	
	public String farmerId;
	public int noOfData = 0;
	public String startingTimeStamp = null;
	public String endingTimeStamp = null;
	
	public ArrayList<PhData> dataList;
}
