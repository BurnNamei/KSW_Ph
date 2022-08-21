package model;



public class PostDataPh {
	
	public String farmerId;
	public String strDataDate;
	public float spData1,spData2,spData3,spData4,spData5,spData6,spData7,spData8,spData9;
	
	
	public String generateDbPhValueStorageText() {
		String storageText ="1:"+String.valueOf(spData1) +","
							+"2:"+String.valueOf(spData2)+","
							+"3:"+String.valueOf(spData3)+","
							+"4:"+String.valueOf(spData4)+","
							+"5:"+String.valueOf(spData5)+","
							+"6:"+String.valueOf(spData6)+","
							+"7:"+String.valueOf(spData7)+","
							+"8:"+String.valueOf(spData8)+","
							+"9:"+String.valueOf(spData9);
							
		return storageText;
	}
	
}
