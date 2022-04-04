package vo;

public class StaffList {
	// Staff 객체가 있지만 StaffList는 firstName과 LastName이 합쳐있음
	// Staff를 써도될듯하지만 우선 만들었음
	private int staffId;
	private String name;
	private String address;
	private int zipCode;
	private String phone;
	private String city;
	private String country;
	private int storeId;

	@Override
	public String toString() {
		return "StaffList [staffId=" + staffId + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode
				+ ", phone=" + phone + ", city=" + city + ", country=" + country + ", storeId=" + storeId + "]";
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
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
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	
}
