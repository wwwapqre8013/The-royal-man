package view.jixiao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.ShowMessage;
import dao.BuMenDao;
import dao.JiXiaoDao;
import dao.ManageProject2BuMenDao;
import dao.ProjectDao;
import entity.BuMen;
import entity.JiXiao;
import entity.Staff;
import entity.Project;

public class JiXiaoView {
	JTextField nameText;
	JTextField jiXiaoText;
	JComboBox  proBox;
	JComboBox  bmBox;
	JiXiaoTableModel model;
	JTable table;
	JScrollPane jxrollPane;
	List<JiXiao> list = new ArrayList<JiXiao>();// ����һ��Jixiao�ļ���
	JiXiaoDao jxDao = new JiXiaoDao();
	BuMenDao bmDao = new BuMenDao();
	ProjectDao proDao = new ProjectDao();
	ManageProject2BuMenDao sbDao = new ManageProject2BuMenDao();
	JFrame frame;
	List<Project> proList = new ArrayList<Project>();
	List<BuMen> bmList= new ArrayList<BuMen>();
	//List<Jixiao> saveList = new ArrayList<Jixiao>();
    Set<JiXiao> saveSet = new HashSet<JiXiao>();
	private  static JiXiaoView instance;
	private JiXiaoView(){//�����ǹ��췽�����˽��	
	}
	public static JiXiaoView getInstance(){
		if(instance == null){
			instance = new JiXiaoView();
		}
		return instance;
	}
	public void creatFrame(){
		if(frame == null ){
			frame = new JFrame();
			init();
		}
		else{
			frame.setVisible(true);
		}
	}
	public void init() {
		list = jxDao.searchAll();// ����jxdent�е�����
		// ��������
		// for(Jixiao jx : list)
		// {
		// System.out.println(jx.getName());
		// }//��Ԫѭ��
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setTitle("��Ч����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// �����������������
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(900, 100));
		panel1.setBackground(Color.GRAY);
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(900, 600));
		panel2.setBackground(Color.WHITE);
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(900, 100));
		panel3.setBackground(Color.GRAY);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		frame.add(panel);
		// �����1�� ����
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 35));
		JLabel label1 = new JLabel();
		label1.setFont(new Font("", 1, 20));
		label1.setText("������");
		panel1.add(label1);

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(70, 30));
		panel1.add(nameText);

		
		JLabel label2 = new JLabel();
 		label2.setFont(new Font("", 1, 20));
		label2.setText("���ţ�");
		panel1.add(label2);
		bmBox = new JComboBox();
		bmList = bmDao.searchAll();
		bmBox.addItem("��ѡ����");
		for(BuMen bm:bmList){
			bmBox.addItem(bm.getName());
		}
		panel1.add(bmBox);
		bmBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = -1;
				if(bmBox.getSelectedIndex()>0){
					index = bmBox.getSelectedIndex()-1;
					proList = sbDao.searchSub(bmList.get(index).getId());
					
				}	else{
					proList=proDao.searchAll();
				}
				refreshSubBox(proList);
			}
		});
		
		
		JLabel label3 = new JLabel();
 		label3.setFont(new Font("", 1, 20));
		label3.setText("��Ŀ��");
		panel1.add(label3);
		proBox = new JComboBox();
		
		proList = proDao.searchAll();
		refreshSubBox(proList);
		panel1.add(proBox);
		
		
		JLabel label4 = new JLabel();
		label4.setFont(new Font("", 1, 20));
		label4.setText("��Ч��");
		panel1.add(label4);

		jiXiaoText = new JTextField();
		jiXiaoText.setPreferredSize(new Dimension(70, 30));
		panel1.add(jiXiaoText);

		
		// ��ѯ
		JButton btn1 = new JButton();
		btn1.setPreferredSize(new Dimension(80, 40));
		btn1.setFont(new Font("", 1, 20));
		btn1.setText("��ѯ");
		panel1.add(btn1);
		 btn1.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 JiXiao condition = new JiXiao();
		 JiXiao jx = new JiXiao();
		 int searchJixiao = -1;
		 if (!jiXiaoText.getText().equals("")) // �ж���дֵ��ǿ��ת��������
		 {
			 searchJixiao = Integer.parseInt(jiXiaoText.getText());
		 }
		 jx.setJixiao(searchJixiao);
		 Staff sta = new Staff();
		 sta.setName(nameText.getText());
		 BuMen bm = new BuMen();
		 int bmIndex = bmBox.getSelectedIndex();
		 int bmId = -1;
		 if(bmIndex > 0){
			 bmId = bmList.get(bmIndex-1).getId();
			 }
		 bm.setId(bmId);
		 sta.setBm(bm);
		 
		 Project pro = new Project();
		 int proIndex = proBox.getSelectedIndex();
		 int proId = -1;
		 if(proIndex > 0){
			 proId = proList.get(proIndex-1).getId();
			 }
		 pro.setId(proId);
		 condition.setSta(sta);
		 condition.setPro(pro);
		 condition.setJixiao(searchJixiao);
		 list =jxDao.searchByCondition(condition);
		 refreshTable(list);
		 }
		 });
		 
		
		model = new JiXiaoTableModel(list,saveSet);
		table = new JTable();
		table.setModel(model);
		jxrollPane = new JScrollPane(table);
		jxrollPane.setPreferredSize(new Dimension(880, 550));
		panel2.add(jxrollPane);
	
		
		// ��������
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
		JButton addbtn = new JButton();
		addbtn.setPreferredSize(new Dimension(300, 40));
		addbtn.setFont(new Font("", 1, 20));
		addbtn.setText("����");
		panel3.add(addbtn);
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = jxDao.save(saveSet);
				saveSet.clear();
				ShowMessage.show(flag, "����");
				refreshTable();
			}
		});
		frame.setVisible(true);
	}

	private void refreshSubBox(List<Project> proList) {
		proBox.removeAllItems();
		proBox.addItem("��ѡ����Ŀ");
		for(Project pro:proList){
			proBox.addItem(pro.getName()); 
		}
	}
	public void refreshTable() {
		list = jxDao.searchAll();
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}

	public void refreshTable(List<JiXiao> list) {
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}
}
