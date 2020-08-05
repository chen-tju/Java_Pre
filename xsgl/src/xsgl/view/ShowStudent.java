package xsgl.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import xsgl.view.AddStudent;
import xsgl.dao.StudentDao;
import xsgl.model.Student;
import xsgl.util.PicPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import xsgl.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShowStudent extends JDialog{
	String tiltle[] = {"学号","专业","姓名","性别","出生日期","团员否","家庭住址"};
	JLabel lab[] = new JLabel[7];
	public ShowStudent(String studNo){
		Container con = getContentPane();
		txtResume.setEditable(false);
		panelPic.setBorder(BorderFactory.createLoweredBevelBorder());
		panelPic.setPreferredSize(new Dimension(100,120));
		JPanel top = new JPanel(new BorderLayout());
		JPanel topLeft = new JPanel(new GridLayout(7,1));
		JPanel topRight = new JPanel();
		topRight.setPreferredSize(new Dimension(120,1));
		topRight.add(panelPic);
		JPanel p[] = new JPanel[7];
		for(int i = 0;i<7;i++){
			p[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			lab[i] = new JLabel();
			p[i].add(new JLabel(title[i]+":"));
			p[i].add(lab[i]);
			topLeft.add(p[i]);
		}
		top.add(topLeft,"Center");
		top.add(topRight,"East");
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.add(new JLabel("简历"), "West");
		center.add(new JScrollPane(txtResume), "Center");
		con.add(top, "North");
		con.add(center,"Center");
		loadData(studNo);
		setTitle("查看学生窗口");
		setModal(true);
		setSize(450,300);
		setVisible(true);
	}
	void loadData(String studNo){
		try{
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.findStudent(studNo);
			lab[0].setText(student.getStudNo());
			lab[1].setText(student.getMajor().getMajorName());
			lab[2].setText(student.getStudName());
			lab[3].setText(student.getStudSex());
			lab[4].setText(student.getStudBirthDate().toString());
			lab[5].setText(student.getStudIsMember()?"是":"不是");
			lab[6].setText(student.getStudAddress());
			txtResume.setText(student.getStudResume());
			if(student.getStudPic()!=null){
				ByteArrayInputStream in = new ByteArrayInputStream(student.getStudPic());
				BufferedImage image = ImageIO.read(in);
				panelPic.show(image);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
