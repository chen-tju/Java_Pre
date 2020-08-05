package xsgl.model;

public class Score {
	private Student student;
	private Course course;
	private Float score;
	private static Float avgScore;
	
	public Student setStudent(){
		return student;
	}
	public Course setCourse(){
		return course;
	}
	public Float setScore(){
		return score;
	}
	public Float setAvgScore(){
		return avgScore;
	}

}
