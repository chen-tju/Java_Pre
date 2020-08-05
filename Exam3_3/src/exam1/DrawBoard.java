package exam1;

import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;

public class DrawBoard extends JPanel{
	public DrawBoard(){
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
	}
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g1 = (Graphics2D)g;
		Shape s = null;
		s = new Line(20,20,100,200,Color.blue,3);
		s.draw(g1);
		s = new Rect(160,100,100,150,Color.green,2,IShape.FillType.NO_FILL);
		s.draw(g1);
		s = new Oval(320,100,200,150,Color.red,1,IShape.FillType.FIll);
		s.draw(g1);
	}

}
