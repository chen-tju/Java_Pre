package exam1;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;


public class Rect extends Shape{
	public Rect(){
		
	}
	public Rect(int x1,int y1,int x2,int y2,Color color,float thick,FillType fillType){
		super(x1,y1,x2,y2,color,thick,fillType);
		
	}
	@Override
	public void draw(Graphics2D g){
		g.setColor(getColor());
		g.setStroke(new BasicStroke(getThick(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND));
		if(getFillType() == FillType.FIll){
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}else{
			g.drawRect(getX(), getY(), getWidth(), getHeight());
		}
	}
	
}
