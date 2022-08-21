package responseModel;

public class BaseResponseClass {
	
	public static final String STATUS_SUCCESS="SUCCESS", STATUS_ERROR="ERROR";
	public static final int CODE_SUCCESS=0, ERROR_1=1;
	
	
	public String status = STATUS_SUCCESS;
	public int errorCode = CODE_SUCCESS;
	public String message= "";

}
