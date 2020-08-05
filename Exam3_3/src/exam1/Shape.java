package exam1;

import java.awt.Color;

public abstract class Shape implements IShape{
	private int x1,y1;
	private int x2,y2;
	private Color color;
	private float thick;
	private FillType fillType;
	public Shape(){
		
	}
	public Shape(int x1,int y1,int x2,int y2,Color color,float thick){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.thick = thick;
	}
	public Shape(int x1,int y1,int x2,int y2,Color color,float thick,FillType fillType){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.thick = thick;
		this.fillType = fillType;
	}
	public int getX1(){
		return x1;
	}
	public int getY1(){
		return y1;
	}
	public int getX2(){
		return x2;
	}
	public int getY2(){
		return y2;
	}
	public Color getColor(){
		return color;
	}
	public float getThick(){
		return thick;
	}
	public int getX(){
		return x1;
	}
	public int getY(){
		return y1;
	}
	public int getWidth(){
		return x2;
	}
	public int getHeight(){
		return y2;
	}
	public FillType getFillType() {

		return fillType;
	}


}
