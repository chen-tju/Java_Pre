package exam1;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawWindow extends JFrame{
	
	DrawBoard drawBoard = new DrawBoard();
	public DrawWindow(){
		Container con = this.getContentPane();
		con.add(drawBoard);
		setTitle("»æÍ¼Èí¼þ");
		setSize(600,400);
		setVisible(true);
	}
	public static void main(String[] args){
		new DrawWindow();
	}

}
