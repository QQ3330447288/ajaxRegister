package cn.thanlon.www.entity;

public class User {
	private int id;
	private String userName;
	private String pwd;
	private String phone;

	/**
	 * 构造函数
	 */
	public User() {

	}

	/**
	 * 构造函数
	 * 
	 * @param id
	 * @param userName
	 * @param pwd
	 * @param phone
	 */
	public User(int id, String userName, String pwd, String phone) {
		super();
		this.id = id;
		this.userName = userName;
		this.pwd = pwd;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", pwd=" + pwd + ", phone=" + phone + "]";
	}
}
