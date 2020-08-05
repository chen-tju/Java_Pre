package xsgl.control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import xsgl.util.*;
import xsgl.view.*;
import javax.swing.*;


public class MainWindow extends JFrame implements ActionListener {
	JMenuBar menuBar;
	JMenu menu1,menu2,menu3,menu4;
	JMenuItem m11,m12,m13;
	JMenuItem m21,m22,m23;
	JMenuItem m31,m32,m33;
	JMenuItem m41,m42;
	JToolBar toolBar;
	JButton b1,b2,b3;
	public MainWindow(){
		add(new PicPanel("back.jpg"),BorderLayout.CENTER);
		createMenu();
		createToolBar();
		setTitle("学生管理系统");
		setIconImage((new ImageIcon("0.jpg")).getImage());
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	private void createMenu(){
		menuBar = new JMenuBar();
		menu1 = new JMenu("系统管理(S)");
		menu1.setMnemonic('S');
		m11 = new JMenuItem("密码设置");
		m12 = new JMenuItem("用户登录");
		m13 = new JMenuItem("退出(X)");
		m13.setAccelerator(KeyStroke.getKeyStroke('X',2));
		menu1.add(m11);
		menu1.add(m12);
		menu1.addSeparator();
		menu1.add(m13);
		menu2 = new JMenu("数据管理(D)");
		menu2.setMnemonic('D');
		m21 = new JMenuItem("学生管理");
		m22 = new JMenuItem("课程管理");
		m23 = new JMenuItem("成绩管理");
		menu2.add(m21);
		menu2.add(m22);
		menu2.add(m23);
		menu3 = new JMenu("数据查询(F)");
		menu3.setMnemonic('F');
		m31 = new JMenuItem("学生查询");
		m32 = new JMenuItem("课程查询");
		m33 = new JMenuItem("成绩查询");
		menu3.add(m31);
		menu3.add(m32);
		menu3.add(m33);
		menu4 = new JMenu("帮助(H)");
		menu4.setMnemonic('H');
		m41 = new JMenuItem("使用帮助");
		m42 = new JMenuItem("关于");
		menu4.add(m41);
		menu4.addSeparator();
		menu4.add(m42);
		m11.addActionListener(this);
		m12.addActionListener(this);
		m13.addActionListener(this);
		m21.addActionListener(this);
		m22.addActionListener(this);
		m23.addActionListener(this);
		m31.addActionListener(this);
		m32.addActionListener(this);
		m41.addActionListener(this);
		m42.addActionListener(this);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		this.setJMenuBar(menuBar);
	}
	private void createToolBar(){
		toolBar = new JToolBar();
		b1 = new JButton();
		b1.setToolTipText("学生管理");
		b1.addActionListener(this);
		b1.setIcon(new ImageIcon("1.jpg"));
		b2 = new JButton();
		b2.setToolTipText("课程管理");
		b2.addActionListener(this);
		b2.setIcon(new ImageIcon("2.jpg"));
		b3 = new JButton();
		b3.setToolTipText("成绩管理");
		b3.addActionListener(this);
		b3.setIcon(new ImageIcon("3.jpg"));
		toolBar.add(b1);
		toolBar.add(b2);
		toolBar.add(b3);
		toolBar.setRollover(true);
		add(toolBar,BorderLayout.PAGE_START);
	}
	public static void main(String args[]){
		new MainWindow();
		new LoginWindow();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == m31){
			new FindStudent();
		}
	}
	
	

}
