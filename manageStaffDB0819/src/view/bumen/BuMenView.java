package view.bumen;

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

import util.CallBack;
import util.Constant;
import view.ShowMessage;
import view.staff.StaffView;
import dao.BuMenDao;
import dao.StaffDao;
import entity.BuMen;

public class BuMenView {
	JTextField nameText;
	JTextField staNumsText;
	BuMenTableModelView model;
	JTable table;
	JScrollPane scrollPane;
	List<BuMen> bmList = new ArrayList<BuMen>();// ����һ��BanJi�ļ���
	BuMenDao bmDao = new BuMenDao();
	JFrame frame;

	private static BuMenView instance;

	private BuMenView() {// �����ǹ��췽�����˽��
	}

	public static BuMenView getInstance() {
		if (instance == null) {
			instance = new BuMenView();
		}
		return instance;
	}

	public void creatFrame() {
		if (frame == null) {
			frame = new JFrame();
			init();
		} else {
			frame.setVisible(true);
		}
	}

	public void init() {
		bmList = bmDao.searchAll();// ����bmdent�е�����
		// ��������
		// for(BanJi bm : bmList)
		// {
		// System.out.println(bm.getName());
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
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);

		JLabel label3 = new JLabel();
		label3.setFont(new Font("", 1, 20));
		label3.setText("����������");
		panel1.add(label3);

		staNumsText = new JTextField();
		staNumsText.setPreferredSize(new Dimension(150, 40));
		panel1.add(staNumsText);
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
				int serchStaNums = -1;
				if (!staNumsText.getText().equals("")) // �ж���дֵ��ǿ��ת��������
				{
					serchStaNums = Integer.parseInt(staNumsText.getText());
				}
				BuMen condition = new BuMen();
				condition.setName(serchName);
				condition.setStaNums(serchStaNums);
				bmList = bmDao.searchByCondition(condition);
				refreshTable(bmList);
			}
		});
		// �����2������
		// �������
		// ���������ֵ�

		JButton subBtn = new JButton();
		subBtn.setPreferredSize(new Dimension(120, 40));
		subBtn.setFont(new Font("", 1, 20));
		subBtn.setText("������Ŀ");
		panel1.add(subBtn);
		subBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ�а༶����й�����Ŀ");
				} else {
					ManageProject2BuMenView stb =ManageProject2BuMenView.getInstance();
					stb.creatFrame(bmList.get(row), new CallBack() {
						
						@Override
						public void call() {
							refreshTable();
						}
					});
				}
			}	
			});
		

		
		
		
		table = new JTable();
		model = new BuMenTableModelView(bmList);
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
				AddBuMenView as = AddBuMenView.getInstance();
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
					ModifyBuMen ms = ModifyBuMen.getInstance();
					BuMen bm = bmList.get(row);
					ms.createFrame(bm.getId(), new CallBack() {
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
						int rs = bmDao.delete(bmList.get(row));
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
		bmList = bmDao.searchAll();
		model.setData(bmList);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}

	public void refreshTable(List<BuMen> bmList) {

		model.setData(bmList);
		model.fireTableStructureChanged();// ����STmodel�е�model����ִ��һ��ķ���
	}

}
