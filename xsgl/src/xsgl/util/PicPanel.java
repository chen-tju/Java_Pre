package xsgl.util;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
public class PicPanel extends JPanel{
	Image image;
	public PicPanel(){
		
	}
	public PicPanel(String filename){
		image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	public void show(Image image){
		this.image = image;
		repaint();
	}
	    public void show(String filename){
	    	image = Toolkit.getDefaultToolkit().getImage(filename);
	    	repaint();
	    }
	    @Override
	    protected void paintComponent(Graphics g){
	    	super.paintComponent(g);
	    	g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),this);
	    }
}

