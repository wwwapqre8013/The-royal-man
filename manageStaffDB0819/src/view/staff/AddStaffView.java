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

public class AddStaffView {
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JComboBox bmBox;
	JFrame frame;
	CallBack callBack;
	BuMenDao bmDao = new BuMenDao();
	List<BuMen> bmList;
	private static  AddStaffView instance;
	
	public static AddStaffView getInstance(){
		
		if(instance == null)
		{
			instance = new AddStaffView();
		}
		return instance;
	}
	
	private AddStaffView()
	{
		
	}
	
	public void createFrame(CallBack callBack){
		this.callBack = callBack;
		if(frame == null)
		{
			frame = new JFrame();
			init1();		
			}
		else{
			frame.setVisible(true);
		}
	}
	public void init1() {
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("����Ա��");
		
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
		
		//��������
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label1 = new JLabel();
		label1.setText("����");
		panel1.add(label1);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);
		//�Ա𲿷�
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label2 = new JLabel();
		label2.setText("�Ա�");
		panel2.add(label2);
		sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(150, 40));
		panel2.add(sexText);
		//���䲿��
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label3 = new JLabel();
		label3.setText("����");
		panel3.add(label3);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(150, 40));
		panel3.add(ageText);
		//�༶
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label4 = new JLabel();
		label4.setText("����");
		panel4.add(label4);
		bmBox = new JComboBox();
		bmList = bmDao.searchAll();
		bmBox.addItem("��ѡ����");
		for(BuMen bm:bmList){
			bmBox.addItem(bm.getName());
		}
		
		bmBox.setPreferredSize(new Dimension(150, 40));
		panel4.add(bmBox);
		//��ť���沿��
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton savebtn = new JButton();
		savebtn.setPreferredSize(new DimensionUIResource(120, 40));
		savebtn.setText("����");
		panel5.add(savebtn);
		//�������
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StaffDao staDao  = new StaffDao();
				String name = nameText.getText();
				nameText.setText("");
				String sex = sexText.getText();
				sexText.setText("");
				int age = Integer.parseInt(ageText.getText());
				ageText.setText("");
				Staff sta = new Staff();
				sta.setName(name);
				sta.setSex(sex);
				sta.setAge(age);
				
				int index = bmBox.getSelectedIndex();
				BuMen bm = new BuMen();
				if(index>0){
					index = index-1;
					bm = bmList.get(index);
				}
                sta.setBm(bm);
				boolean rs = staDao.add(sta);
				ShowMessage.show(rs, Constant.MES_ADD);
				frame.dispose();
				callBack.call();
				//ְ�ܾ��ǻص�����Ϊֻ��addstadent֪��ʲôʱ��ˢ�£�addstadent���Կ���ʲôʱ�����
			}
		});
		    frame.setVisible(true);
        }
}
