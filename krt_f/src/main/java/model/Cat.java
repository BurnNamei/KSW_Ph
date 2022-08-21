package model;

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
