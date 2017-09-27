package view.project;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.ProjectDao;
import util.CallBack;
import util.Constant;
import view.ShowMessage;
import entity.Project;
public class ProjectView {
	JTextField nameText;
	ProjectTableModel model;
	JTable table;
	JScrollPane scrollPane;
	List<Project> list = new ArrayList<Project>();// ����һ��Subject�ļ���
	ProjectDao proDao = new ProjectDao();
	JFrame frame;
	
	private  static ProjectView instance;
	private ProjectView(){//�����ǹ��췽�����˽��	
	}
	public static ProjectView getInstance(){
		if(instance == null){
			instance = new ProjectView();
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
		list = proDao.searchAll();// ����prodent�е�����
		// ��������
		// for(Subject pro : list)
		// {
		// System.out.println(pro.getName());
		// }//��Ԫѭ��
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setTitle("��Ŀ����ϵͳ");
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
		label1.setText("��Ŀ���ƣ�");
		panel1.add(label1);

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);

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
		 Project condition = new Project();
		 condition.setName(serchName);
		 list =proDao.searchByCondition(condition);
		 refreshTable(list);
		 }
		 });
		// �����2������
		// �������
		// ���������ֵ�

		table = new JTable();
		model = new ProjectTableModel(list);
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
				AddProjectView as = AddProjectView.getInstance();
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
					ModifyProjectView ms = ModifyProjectView.getInstance();
					Project pro = list.get(row);
					ms.createFrame(pro.getId(), new CallBack() {
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
						int rs = proDao.delete(list.get(row));
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
		list = proDao.searchAll();
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}

	public void refreshTable(List<Project> list) {
		
		model.setData(list);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}
	
}
