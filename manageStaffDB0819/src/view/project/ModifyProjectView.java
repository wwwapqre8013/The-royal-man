package view.project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import util.CallBack;
import util.Constant;
import view.ShowMessage;
import dao.ProjectDao;
import entity.Project;

public class ModifyProjectView {
	
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JFrame frame;
	Project pro;
	ProjectDao proDao = new ProjectDao();
	List<Project> list;
	CallBack callBack;
//	public ModifySubject(List<Subject> list) {
//		this.list = list;
//	}
	private static  ModifyProjectView instance;
	
	public static ModifyProjectView getInstance(){
		if(instance == null)
		{
			instance = new ModifyProjectView();
		}
		return instance;
	}
	private ModifyProjectView()
	{
		
	}
	
	public void createFrame(int id,CallBack callBack){
		this.callBack = callBack;
		this.pro = proDao.searchById(id);
		if(frame == null)
		{
			frame = new JFrame();
			init1();
		}
		else{
			nameText.setText(pro.getName());//要把text的值初始化			
			frame.setVisible(true);
		}
	}
	
	public void init1() {
		frame.setSize(280, 280);
		frame.setLocationRelativeTo(null);
		frame.setTitle("修改项目");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(280, 140));	
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(280, 140));
		panel.add(panel1);
		panel.add(panel2);
		frame.add(panel);
	
		//姓名部分
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label1 = new JLabel();
		label1.setText("名称");
		panel1.add(label1);
		nameText = new JTextField(pro.getName());
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);
		
		//按钮保存部分
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton savebtn = new JButton();
		savebtn.setPreferredSize(new DimensionUIResource(120, 40));
		savebtn.setText("修改");
		panel2.add(savebtn);
		//插入监听
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				pro.setName(name);
				boolean rs = proDao.updata(pro);
				ShowMessage.show(rs, Constant.MES_MODIFY);
				frame.dispose();
				callBack.call();
				//利用回调代替调用model。
				//model.fireTableStructureChanged();//调用STmodel中的model的在执行一遍的方法
			}
		});
		    frame.setVisible(true);
        }

}
