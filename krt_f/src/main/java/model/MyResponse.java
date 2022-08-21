package model;

public class MyResponse {
	public static final String StatusSuccess = "SUCCESS", StatusError = "ERROR";

	public int statusCode;
	public int errorCode = 0;

	public String status = "";
	public String message = "";
}