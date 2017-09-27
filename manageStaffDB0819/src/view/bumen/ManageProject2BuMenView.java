package view.bumen;

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

import util.CallBack;
import util.Constant;
import view.ShowMessage;
import view.project.ProjectTableModel;
import dao.ManageProject2BuMenDao;
import entity.BuMen;
import entity.Project;

public class ManageProject2BuMenView {
	JTable table;
	JScrollPane scrollPane;
	ManageProject2BuMenDao stbDao = new ManageProject2BuMenDao();
	Project pro = new Project();
	ManageProject2BuMenModel model;
	JFrame frame;
	private BuMen bm;
	JComboBox proBox;
	List<Project> notList;
	List<Project> list = new ArrayList<Project>();
	private static ManageProject2BuMenView instance;
	JLabel bmLable;
	CallBack callBack;

	public static ManageProject2BuMenView getInstance() {
		if (instance == null) {
			instance = new ManageProject2BuMenView();
		} 
		return instance;
	}

	public ManageProject2BuMenView() {

	}

	public void creatFrame(BuMen bm,CallBack callBack) {
		this.bm = bm;
		this.callBack = callBack;
		if (frame == null) {
			frame = new JFrame();
			init();
		} else {
			bmLable.setText(bm.getName());
			refreshTable();
			refreshSubBox();
			frame.setVisible(true);
		}
	}

	public void init() {

		frame = new JFrame();
		frame.setSize(500, 700);
		frame.setLocationRelativeTo(null);
		frame.setTitle("班级科目管理");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(400, 80));
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(400, 420));
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(400, 100));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		frame.add(panel);

		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		bmLable = new JLabel();
		bmLable.setPreferredSize(new Dimension(200, 70));
		bmLable.setFont(new Font(null, 0, 20));
		bmLable.setText(bm.getName());
		panel1.add(bmLable);

		list = stbDao.searchSub(bm.getId());
		table = new JTable();
		model = new ManageProject2BuMenModel(list);
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		panel2.add(scrollPane);

		JPanel addpanel = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
		proBox = new JComboBox();
		proBox.setPreferredSize(new Dimension(80, 40));
		refreshSubBox();
		addpanel.add(proBox);

		JButton addBtn = new JButton();
		addBtn.setPreferredSize(new Dimension(80, 40));
		addBtn.setFont(new Font("", 1, 20));
		addBtn.setText("新增");
		addpanel.add(addBtn);
		panel3.add(addpanel);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = proBox.getSelectedIndex();
				int proId = notList.get(index).getId();
				boolean flag = stbDao.add(bm.getId(), proId);
				ShowMessage.show(flag, "添加成功");
				refreshTable();
				refreshSubBox();
			}
		});

		JButton deleteBtn = new JButton();
		deleteBtn.setPreferredSize(new Dimension(80, 40));
		deleteBtn.setFont(new Font("", 1, 20));
		deleteBtn.setText("删除");
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "请选择科目");
				} else {
					int type = JOptionPane.showConfirmDialog(null, "是否选择删除",
							"删除", JOptionPane.OK_CANCEL_OPTION);

					if (type == 0) {
						boolean flag = stbDao.delete(bm.getId(), list
								.get(index).getId());
						ShowMessage.show(flag, "删除成功");
						refreshTable();
						refreshSubBox();
					}
				}
			}
		});
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = stbDao.searchSub(bm.getId());
		model.setData(list);
		model.fireTableStructureChanged();// 调用STmodel中的model的在执行一遍的方法
	}

	public void refreshSubBox() {
		notList = stbDao.notSearchSub(bm.getId());
		proBox.removeAllItems();
		for (int i = 0; i < notList.size(); i++) {
			proBox.addItem(notList.get(i).getName());
		}
	}
}
