package exam1;

import java.awt.BasicStroke;
import java.awt.*;
import java.awt.Graphics2D;


public class Oval extends Shape{
	public Oval(){
		
	}
	public Oval(int x1,int y1,int x2,int y2,Color color,float thick,FillType fillType){
		super(x1,y1,x2,y2,color,thick,fillType);
	}
	@Override
	public void draw(Graphics2D g){
		g.setColor(getColor());
		g.setStroke(new BasicStroke(getThick(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND));
		if(getFillType() == FillType.FIll){
			g.fillOval(getX(), getY(), getWidth(), getHeight());
		}else{
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		}
	}

}
