package exam1;
import javax.swing.*;
public class Menu extends JFrame{
	public Menu(){
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("文件");
		JMenuItem item1 = new JMenuItem("打开");
		JMenuItem item2 = new JMenuItem("保存");
		fileMenu.add(item1);
		fileMenu.add(item2);
		menubar.add(fileMenu);
		setJMenuBar(menubar);
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String srgs[]){
		new Menu();
	}

}
