package xsgl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xsgl.idao.IStudentDao;
import xsgl.model.*;
import xsgl.util.SqlHelper;

public class StudentDao implements IStudentDao{
	@Override
	public Student findStudent(String studNo){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		try{
			con = SqlHelper.connect();
			ps = con.prepareStatement("select a.major_name,b.* from major a,student b where a.major_id = b.major_id and b.stud_no = ?");
			ps.setString(1, studNo);
			rs = ps.executeQuery();
			if(rs.next()){
				student = new Student();
				Major major = new Major();
				student.setMajor(major);
				major.setMajorName(rs.getString("major_name"));
				student.setStuNo(rs.getString("stud_no"));
				major.setMajorId(rs.getInt("major_id"));
				student.setStudName(rs.getString("stud_name"));
				student.setStudSex(rs.getString("stud_sex"));
				student.setStudBirthDate(rs.getDate("stud_birthDate"));
				student.setStudIsMember(rs.getBoolean("stud_isMember"));
				student.setStudAddress(rs.getString("stud_address"));
				student.setStudResume(rs.getString("stud_resume"));
				student.setStudPic(rs.getBytes("stud_pic"));
			}
			return student;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			SqlHelper.closeResultSet(rs);
			SqlHelper.closePreparedStatement(ps);
			SqlHelper.closeConnection(con);
		}
	}
	public List<Student>findStudents(String studName,Integer pageNo,Integer pageSize){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student>list = new ArrayList<Student>();
		try{
			con = SqlHelper.connect();
			ps = con.prepareStatement("select a.major_name,b.*from xsgl_major a,xsgl_student b where a.major_id = b.major_id and b.stud_name like?limit"+(pageNo - 1)*pageSize + ","+pageSize);
			ps.setString(1, "%"+studName+"%");
			rs = ps.executeQuery();
			Student student;
			while(rs.next()){
				student = new Student();
				Major major = new Major();
				student.setMajor(major);
				major.setMajorName(rs.getString("major_name"));
				student.setStuNo(rs.getString("stud_no"));
				major.setMajorId(rs.getInt("major_id"));
				student.setStudName(rs.getString("stud_name"));
				student.setStudSex(rs.getString("stud_sex"));
				student.setStudBirthDate(rs.getDate("stud_birthDate"));
				student.setStudIsMember(rs.getBoolean("stud_isMember"));
				student.setStudResume(rs.getString("stud_resume"));
				student.setStudPic(rs.getBytes("stud_pic"));
				list.add(student);
			}
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			return list;
		}finally{
			SqlHelper.closeResultSet(rs);
			SqlHelper.closePreparedStatement(ps);
			SqlHelper.closeConnection(con);			
		}	
	}
	public int findCount(String stud_name){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			con = SqlHelper.connect();
			String sql = "select count(*) as count from xsgl_student where stud_name like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+stud_name+"%");
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
			}
			return count;
		}catch (Exception e){
			e.printStackTrace();
			return count;	
		}finally{
			SqlHelper.closeResultSet(rs);
			SqlHelper.closePreparedStatement(ps);
			SqlHelper.closeConnection(con);			
		}	
	}
	@Override
	public boolean addStudent(Student student){
		return false;
	}
	@Override
	public boolean deleteStudent(String studNo){
			return false;
		}
	@Override
	public boolean editStudent(Student student){
			return false;
		}


}
