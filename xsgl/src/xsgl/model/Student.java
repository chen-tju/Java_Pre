package xsgl.model;
import java.sql.Date;

public class Student {
	private String studNo;
	private Major major;
	private String studName;
	private String studSex;
	private Date studBirthDate;
	private Boolean studIsMember;
	private String studAddress;
	private String studResume;
	private byte[] studPic;
	
	public String setStuNo(String string){
		return studNo;
	}
	public Major setMajor(Major major2){
		return major;
	}
	public String setStudName(String string){
		return studName;
	}
	public String setStudSex(String string){
		return studSex;
	}
	public Date setStudBirthDate(Date date){
		return studBirthDate;
	}
	public Boolean setStudIsMember(boolean b){
		return studIsMember;
	}
	public String setStudAddress(String string){
		return studAddress;
	}
	public String setStudResume(String string){
		return studResume;
	}
	public byte[] setStudPic(byte[] bs){
		return studPic;
	}
	
	
	public String getStudNo() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStudName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStudSex() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getStudBirthDate() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getStudIsMember() {
		// TODO Auto-generated method stub
		return studIsMember;
	}
	public Object getMajor() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStudAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStudResume() {
		// TODO Auto-generated method stub
		return null;
	}
	public byte[] getStudPic() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
