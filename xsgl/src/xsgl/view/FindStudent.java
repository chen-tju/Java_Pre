package xsgl.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.*;

import javax.swing.*;

import xsgl.dao.StudentDao;
import xsgl.model.Student;
import xsgl.util.XsglTableModel;

public class FindStudent extends JFrame implements ActionListener {
	final static Integer PAGE_SIZE = 4;
	StudentDao studentDao = new StudentDao();
	JTable table = new JTable();
	String[] head = {"ѧ��","����","�Ա�","��������","��Ա��"};
	JTextField txtName = new JTextField(10);
	JButton btnFind = new JButton("����");
	JButton btnFirst = new JButton("��ҳ");
	JButton btnNext = new JButton("��һҳ");
	JButton btnPrev = new JButton("��һҳ");
	JButton btnLast = new JButton("βҳ");
	JLabel labPageNo = new JLabel("ssss");
	String userName = "";
	int pageCount,pageNo = 1;
	public FindStudent(){
		Container con = this.getContentPane();
		JPanel top = new JPanel();
		top.add(new JLabel("����"));top.add(txtName);top.add(btnFind);
		con.add(top,"North");
		con.add(new JScrollPane(table), "Center");
		JPanel bottom1 = new JPanel();
		bottom1.add(btnFirst);bottom1.add(btnPrev);
		bottom1.add(btnNext);bottom1.add(btnLast);bottom1.add(labPageNo);
		con.add(bottom1,"South");
		int count = studentDao.findCount("");
		if(count>0){
			pageNo = 1;
			pageCount = count/PAGE_SIZE+(count%PAGE_SIZE>0?1:0);
		}else{
			pageNo = 0;
			pageCount = 0;
		}
		setState();
		loadData(userName);
		btnFind.addActionListener(this);btnNext.addActionListener(this);
		btnPrev.addActionListener(this);btnFirst.addActionListener(this);
		btnLast.addActionListener(this);
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				int row = table.getSelectedRow();
				if(row>=0){
					String studNo = table.getValueAt(row, 0).toString();
					new ShowStudent(studNo);
				}
			}
		});
		setTitle("��ѯѧ������");
		setSize(450,270);
		setVisible(true);
	}
	public void loadData(String userName){
		List<Student>list = studentDao.findStudents(userName, pageNo, PAGE_SIZE);
		table.setModel(new XsglTableModel(head,convert(list)));
	}
	private Object[][] convert(List<Student>list){
		Object[][]data = new Object[list.size()][];
		for(int i = 0;i<list.size();i++){
			Student s = list.get(i);
			data[i] = new Object[]{s.getStudNo(),s.getStudName(),
					s.getStudSex(),s.getStudBirthDate(),
					s.getStudIsMember(),};
			}
		return data;
		}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnFind){
			userName = txtName.getText();
			int count = studentDao.findCount(userName);
			if(count>0){
				pageNo = 1;
				pageCount = count/PAGE_SIZE+(count%PAGE_SIZE>0?1:0);
			}else{
				pageNo = 0;
				pageCount = 0;
			}
		}else if(e.getSource() == btnNext&&pageNo<pageCount){
			pageNo++;
		}else if(e.getSource() == btnPrev&&pageNo>1){
			pageNo--;
		}else if(e.getSource() == btnFirst){
			pageNo = 1;
		}else if(e.getSource() == btnLast){
			pageNo = pageCount;
		}
		setState();
		loadData(userName);
	}
	private void setState(){
		if(pageCount>0){
			labPageNo.setText(pageNo+"/"+pageCount);
			if(pageNo<=1){
				btnPrev.setEnabled(false);btnFirst.setEnabled(false);
			}else{
				btnPrev.setEnabled(true);btnFirst.setEnabled(true);
			}
			if(pageNo == pageCount){
				btnNext.setEnabled(false);btnLast.setEnabled(false);
			}else{
				btnNext.setEnabled(true);btnLast.setEnabled(true);
			}
		}else{
			btnNext.setEnabled(false);btnPrev.setEnabled(false);
			btnFirst.setEnabled(false);btnLast.setEnabled(false);
			labPageNo.setText("�޼�¼");
		}
	}



}
