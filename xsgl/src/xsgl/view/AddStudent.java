package xsgl.view;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import xsgl.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class AddStudent extends JFrame implements ActionListener{
	String title[] = {"学号","专业","姓名","性别","出生日期","团员否","家庭地址"};
	JTextField txtNo = new JTextField(5);
	JComboBox comMajor = new JComboBox();
	JTextField txtName = new JTextField(10);
	JRadioButton radSexM = new JRadioButton("男");
	JRadioButton radSexF = new JRadioButton("女");
	JTextField txtBirthDate = new JTextField(10);
	JCheckBox chIsMember = new JCheckBox("");
	JTextField txtAddress = new JTextField(35);
	JTextArea txtResume = new JTextArea(10,45);
	PicPanel panelPic = new PicPanel();
	JButton btnOK = new JButton("保存");
	JButton btnCancel = new JButton("取消");
	String filename;
	public AddStudent(){
		Container con = getContentPane();
		ButtonGroup group = new ButtonGroup();
		group.add(radSexM);
		group.add(radSexF);
		panelPic.setBorder(BorderFactory.createLoweredBevelBorder());
		panelPic.setPreferredSize(new Dimension(120,150));
		JPanel top = new JPanel(new BorderLayout());
		JPanel topLeft = new JPanel(new GridLayout(7,1));
		JPanel topRight = new JPanel();
		topRight.setPreferredSize(new Dimension(140,1));
		topRight.add(panelPic);
		JPanel p[] = new JPanel[7];
		for(int i=0;i<7;i++){
			p[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p[i].add(new JLabel(title[i] + ":"));
			topLeft.add(p[i]);
		}
		p[0].add(txtNo);
		p[1].add(comMajor);
		p[2].add(txtName);
		p[3].add(radSexM);
		p[3].add(radSexF);
		p[4].add(txtBirthDate);
		p[5].add(chIsMember);
		p[6].add(txtAddress);
		top.add(topLeft, "Center");
		top.add(topRight, "East");
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.add(new JLabel("简历："), "West");
		center.add(new JScrollPane(txtResume), "Center");
		JPanel bottom = new JPanel();
		bottom.add(btnOK);
		bottom.add(btnCancel);
		con.add(top, "North");
		con.add(center,"Center");
		con.add(bottom, "South");
		panelPic.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG图片文件","jpg"));
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = chooser.getSelectedFile();
					filename = file.getAbsolutePath();
					panelPic.show(filename);
				}
			}
		});
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		setTitle("添加学生数据窗口");
		setSize(640,500);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnOK){
			;
		}else{
			dispose();
		}
	}
	public static void main(String args[]){
		new AddStudent();
	}


}
