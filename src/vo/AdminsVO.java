package vo;

public class AdminsVO {
	private int admins_sq;
	private String id;
	private String pw;
	private String name;
	public int getAdmins_sq() {
		return admins_sq;
	}
	public void setAdmins_sq(int admins_sq) {
		this.admins_sq = admins_sq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AdminsVO [admins_sq=" + admins_sq + ", id=" + id + ", pw=" + pw + ", name=" + name + "]";
	}
	
}
