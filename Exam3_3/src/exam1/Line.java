package exam1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape{
	public Line(){	
	}
	public Line(int x1,int y1,int x2,int y2,Color color,float thick){
		super(x1,y1,x2,y2,color,thick);
	}
	@Override
	public void draw(Graphics2D g){
		g.setColor(getColor());
		g.setStroke(new BasicStroke(getThick(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND));
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	}

}
