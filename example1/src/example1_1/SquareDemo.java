package example1_1;

class Square {
  private float a;
  public Square(){  
  }
  public Square(float a){
	  this.a = a;
  }
  public float getArea(){
	  return a*a;
  }
  public float getA(){
	  return a;
  }
  public void setA(float a){
	  this.a=a;
  }
}
public class SquareDemo{
	public static void main(String args[]){
		Square s = new Square(12.0f);
		System.out.println(s.getArea());
	}
}