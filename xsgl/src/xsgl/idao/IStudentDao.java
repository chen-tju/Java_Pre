package xsgl.idao;
import java.util.List;
import xsgl.model.Student;
public interface IStudentDao {
	boolean addStudent(Student student);
	boolean deleteStudent(String studNo);
	boolean editStudent(Student student);
	int findCount(String stud_name);
	Student findStudent(String studNo);
	List<Student>findStudents(String studName,Integer pageNo,Integer pageSize);
	
	//public boolean addStudent(Student student){
		//return false;
	//}
	
}
