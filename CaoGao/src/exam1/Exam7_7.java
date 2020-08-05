package exam1;
package exam1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawWindow extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem m1,m2,m3,m4;
	DrawBoard drawBoard = new DrawBoard();
	public DrawWindow(){
		Container con = this.getContentPane();
		con.add(drawBoard);
		setTitle("绘图软件");
		setSize(600,400);
		setVisible(true);
		createMenu();
	}
	private void createMenu(){
		menuBar = new JMenuBar();
		menu = new JMenu("文件(F)");
		menu.setMnemonic('F');
		m1 = new JMenuItem("打开");
		m2 = new JMenuItem("保存");
		m3 = new JMenuItem("保存成JPG");
		m4 = new JMenuItem("退出(X)");
		m4.setAccelerator(KeyStroke.getKeyStroke('X',2));
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.addSeparator();
		menu.add(m4);
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		m4.addActionListener(this);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	private void saveToJpg(){
		try{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("Graphic Files(.dat)","jpg");
			chooser.addChoosableFileFilter(fnef);
			int returnVal = chooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				BufferedImage img = new BufferedImage(drawBoard.getWidth(),drawBoard.getHeight(),BufferedImage.TYPE_INT_RGB);
				drawBoard.printAll(img.getGraphics());
				ImageIO.write(img, "jpg", file);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private void save(){
		try{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("Graphic Files(.dat)",".dat");
			chooser.addChoosableFileFilter(fnef);
			int returnVal = chooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				FileOutputStream fo = new FileOutputStream(file);
				ObjectOutputStream so = new ObjectOutputStream(fo);
				so.writeObject(drawBoard.list);
				so.close();
				fo.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private void open(){
		try{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("Graphic File(.dat)",".dat");
			chooser.addChoosableFileFilter(fnef);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream si = new ObjectInputStream(fi);
				drawBoard.list = (List<Shape>)si.readObject();
				drawBoard.repaint();
				si.close();
				fi.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e){
		{
		
		}else if(e.getSource() == m1){
			open();
		}else if(e.getSource() == m2){
			save();
		}else if(e.getSource() == m3){
			saveToJpg();
		}else if(e.getSource() == m2){
			System.exit(0);
		}
		drawBoard.requestDefaultFocus();
	}
	public static void main(String[] args){
		new DrawWindow();
	}

}

