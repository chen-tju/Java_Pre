package xsgl.model;
import java.sql.Date;
public class User {
	private Integer userId;
	private String userName;
	private String userPwd;
	private Date userDatetime;
	
	public Integer setUserId(){
		return userId;
	}
	public String setUserName(){
		return userName;
	}
	public String setUserPwd(){
		return userPwd;
	}
	public Date setUserDatetime(){
		return userDatetime;
	}

}
