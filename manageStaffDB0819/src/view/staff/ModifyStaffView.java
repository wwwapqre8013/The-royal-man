package view.staff;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import util.CallBack;
import util.Constant;
import view.ShowMessage;
import dao.BuMenDao;
import dao.StaffDao;
import entity.BuMen;
import entity.Staff;

public class ModifyStaffView {
	
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JComboBox  bmBox;
	JFrame frame;
	Staff sta;
	StaffDao staDao = new StaffDao();
	BuMenDao bmDao = new BuMenDao();
	List<Staff> list;
	CallBack callBack;
	List<BuMen> bmList;
//	public ModifyStudent(List<Student> list) {
//		this.list = list;
//	}
	private static  ModifyStaffView instance;
	
	public static ModifyStaffView getInstance(){
		if(instance == null)
		{
			instance = new ModifyStaffView();
		}
		return instance;
	}
	private ModifyStaffView()
	{
		
	}
	
	public void createFrame(int id,CallBack callBack){
		this.callBack = callBack;
		this.sta = staDao.searchById(id);
		if(frame == null)
		{
			frame = new JFrame();
			init1();
		}
		else{
			nameText.setText(sta.getName());//要把text的值初始化			
			sexText.setText(sta.getSex());
			ageText.setText(String.valueOf(sta.getAge()));
			setBox();
			frame.setVisible(true);
		}
	}
	
	public void init1() {
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("修改员工信息");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(300, 100));	
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 100));
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(300, 100));
		JPanel panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(300, 100));
		JPanel panel5 = new JPanel();
		panel5.setPreferredSize(new Dimension(300, 100));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		frame.add(panel);
	
		//姓名部分
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label1 = new JLabel();
		label1.setText("姓名");
		panel1.add(label1);
		nameText = new JTextField(sta.getName());
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);
		//性别部分
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label2 = new JLabel();
		label2.setText("性别");
		panel2.add(label2);
		sexText = new JTextField(sta.getSex());
		sexText.setPreferredSize(new Dimension(150, 40));
		panel2.add(sexText);
		//年龄部分
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label3 = new JLabel();
		label3.setText("年龄");
		panel3.add(label3);
		ageText = new JTextField(""+sta.getAge());
		//还可以用String.valueOf((list.get(row).getAge()))强制转换
		ageText.setPreferredSize(new Dimension(150, 40));
		panel3.add(ageText);
		
		
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label4 = new JLabel();
		label4.setText("班级");
		panel4.add(label4);
		bmBox = new JComboBox();
		bmList = bmDao.searchAll();
		bmBox.addItem("请选择班级");
		for(BuMen bm:bmList){
			bmBox.addItem(bm.getName());
		}
		setBox();
		
		bmBox.setPreferredSize(new Dimension(150, 40));
		panel4.add(bmBox);
		
		
		//按钮保存部分
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton savebtn = new JButton();
		savebtn.setPreferredSize(new DimensionUIResource(120, 40));
		savebtn.setText("修改");
		panel5.add(savebtn);
		//插入监听
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String sex = sexText.getText();
				int age = Integer.parseInt(ageText.getText());
				sta.setName(name);
				sta.setSex(sex);
				sta.setAge(age);
				int index = bmBox.getSelectedIndex();
				BuMen bm = new BuMen();
				if(index > 0){
				 bm = bmList.get(index-1);
				}
			    sta.setBm(bm);
				boolean rs = staDao.updata(sta);
				ShowMessage.show(rs, Constant.MES_MODIFY);
				frame.dispose();
				callBack.call();
				//利用回调代替调用model。
				//model.fireTableStructureChanged();//调用STmodel中的model的在执行一遍的方法
			}
		});
		    frame.setVisible(true);
        }
	private void setBox(){
		bmBox.setSelectedIndex(0);
		for(int i = 0;i<bmList.size();i++)
		{
			if(bmList.get(i).getId()==sta.getBm().getId()){
				bmBox.setSelectedIndex(i+1);
			}
		}
	}

}
