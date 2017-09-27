package view.staff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import util.CallBack;
import util.Constant;
import view.ShowMessage;
import dao.BuMenDao;
import dao.StaffDao;
import entity.BuMen;
import entity.Staff;

public class StaffView {
	JTextField nameText;
	JTextField sexText;
	JTextField ageText;
	JComboBox  bmBox;
	StaffTableModel model;
	JTable table;
	JScrollPane scrollPane;
	List<Staff> list = new ArrayList<Staff>();// ����һ��Student�ļ���
	StaffDao staDao = new StaffDao();
	BuMenDao bmDao = new BuMenDao();
	JFrame frame;
	List<BuMen> bmList;
	private  static StaffView instance;
	private StaffView(){//�����ǹ��췽�����˽��	
	}
	public static StaffView getInstance(){
		if(instance == null){
			instance = new StaffView();
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
		list = staDao.searchAll();// ����stadent�е�����
		// ��������
		// for(Student sta : list)
		// {
		// System.out.println(sta.getName());
		// }//��Ԫѭ��
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setTitle("ѧ������ϵͳ");
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
		label2.setText("�Ա�");
		panel1.add(label2);

		sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(70, 30));
		panel1.add(sexText);

		JLabel label3 = new JLabel();
 		label3.setFont(new Font("", 1, 20));
		label3.setText("���䣺");
		panel1.add(label3);

		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(70, 30));
		panel1.add(ageText);
		
		JLabel label4 = new JLabel();
 		label4.setFont(new Font("", 1, 20));
		label4.setText("���ţ�");
		panel1.add(label4);

		bmBox = new JComboBox();
		bmList = bmDao.searchAll();
		bmBox.addItem("��ѡ����");
		for(BuMen bm:bmList){
			bmBox.addItem(bm.getName());
		}
		bmBox.addItem("δ���ò���");
		panel1.add(bmBox);
		// ��ѯ
		JButton btn1 = new JButton();
		btn1.setPreferredSize(new Dimension(80, 40));
		btn1.setFont(new Font("", 1, 20));
		btn1.setText("��ѯ");
		panel1.add(btn1);
		 btn1.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 String serchName = nameText.getText();
			 String serchSex = sexText.getText();
			 int serchAge = -1;
			 if (!ageText.getText().equals("")) // �ж���дֵ��ǿ��ת��������
			 {
			 serchAge = Integer.parseInt(ageText.getText());
			 }
		 Staff condition = new Staff();
		 condition.setName(serchName);
		 condition.setSex(serchSex);
		 condition.setAge(serchAge);
		 
		 int index = bmBox.getSelectedIndex();
		 BuMen bm = new BuMen();
		 if(index > 0){
			 if(index<=bmList.size()){
			 bm = bmList.get(index-1);}
		 }else{
			 bm.setId(-1);                                     
		 }
		 condition.setBm(bm);
		 list =staDao.searchByCondition(condition);
		 refreshTable(list);
		 }
		 });
		// �����2������
		// �������
		// ���������ֵ�

		table = new JTable();
		model = new StaffTableModel(list);
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(880, 600));
		panel2.add(scrollPane);
		// ͨ��AbstracyTabelModel���������ݷ���

		// �����3������

		// ��������
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 180, 40));
		JButton addbtn = new JButton();
		addbtn.setPreferredSize(new Dimension(100, 40));
		addbtn.setFont(new Font("", 1, 20));
		addbtn.setText("����");
		panel3.add(addbtn);
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddStaffView as = AddStaffView.getInstance();
				as.createFrame(new CallBack() {
					@Override
					public void call() {
						refreshTable();
					}
				});
			}
		});

		// �޸Ĕ���
		JButton modifybtn = new JButton();
		modifybtn.setPreferredSize(new Dimension(100, 40));
		modifybtn.setFont(new Font("", 1, 20));
		modifybtn.setText("�޸�");
		panel3.add(modifybtn);

		modifybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ�����ݺ�����޸�");
				} else {
					ModifyStaffView ms = ModifyStaffView.getInstance();
					Staff sta = list.get(row);
					ms.createFrame(sta.getId(), new CallBack() {
						@Override
						public void call() {
							refreshTable();
						}
					});
				}
			}
		});

		// ɾ������
		JButton deletebtn = new JButton();
		deletebtn.setPreferredSize(new Dimension(100, 40));
		deletebtn.setFont(new Font("", 1, 20));
		deletebtn.setText("ɾ��");
		panel3.add(deletebtn);

		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();// ����һ��row Ϊtable�е�ѡ����
				if (row == -1) // row������-1֤����ѡ����
				{
					JOptionPane.showConfirmDialog(frame, "Ո�x��Ҫ�h���Ĕ���", "ɾ������",
							JOptionPane.YES_NO_OPTION);
				} else {
					int type = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�h������");
					if (type == 0) {
						int rs = staDao.delete(list.get(row));
						boolean flag = false;
						if (rs > 0) {
							flag = true;
						}
						ShowMessage.show(flag, Constant.MES_DELETE);
					}
				}
				refreshTable();
			}
		});

		frame.setVisible(true);
	}

	public void refreshTable() {
		list = staDao.searchAll();
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}

	public void refreshTable(List<Staff> list) {
		
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}
	
}
