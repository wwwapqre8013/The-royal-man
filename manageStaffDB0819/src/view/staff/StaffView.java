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
	List<Staff> list = new ArrayList<Staff>();// 创建一个Student的集合
	StaffDao staDao = new StaffDao();
	BuMenDao bmDao = new BuMenDao();
	JFrame frame;
	List<BuMen> bmList;
	private  static StaffView instance;
	private StaffView(){//首先是构造方法变成私有	
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
		list = staDao.searchAll();// 加载stadent中的数据
		// 创建窗口
		// for(Student sta : list)
		// {
		// System.out.println(sta.getName());
		// }//逐元循环
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null);
		frame.setTitle("学生管理系统");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 建立上中下三个面板
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
		// 在面板1中 设置
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 35));
		JLabel label1 = new JLabel();
		label1.setFont(new Font("", 1, 20));
		label1.setText("姓名：");
		panel1.add(label1);

		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(70, 30));
		panel1.add(nameText);

		JLabel label2 = new JLabel();
		label2.setFont(new Font("", 1, 20));
		label2.setText("性别：");
		panel1.add(label2);

		sexText = new JTextField();
		sexText.setPreferredSize(new Dimension(70, 30));
		panel1.add(sexText);

		JLabel label3 = new JLabel();
 		label3.setFont(new Font("", 1, 20));
		label3.setText("年龄：");
		panel1.add(label3);

		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(70, 30));
		panel1.add(ageText);
		
		JLabel label4 = new JLabel();
 		label4.setFont(new Font("", 1, 20));
		label4.setText("部门：");
		panel1.add(label4);

		bmBox = new JComboBox();
		bmList = bmDao.searchAll();
		bmBox.addItem("请选择部门");
		for(BuMen bm:bmList){
			bmBox.addItem(bm.getName());
		}
		bmBox.addItem("未设置部门");
		panel1.add(bmBox);
		// 查询
		JButton btn1 = new JButton();
		btn1.setPreferredSize(new Dimension(80, 40));
		btn1.setFont(new Font("", 1, 20));
		btn1.setText("查询");
		panel1.add(btn1);
		 btn1.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 String serchName = nameText.getText();
			 String serchSex = sexText.getText();
			 int serchAge = -1;
			 if (!ageText.getText().equals("")) // 判断填写值后强制转换成数字
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
		// 在面板2中设置
		// 创建表格
		// 创建带滑轮的

		table = new JTable();
		model = new StaffTableModel(list);
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(880, 600));
		panel2.add(scrollPane);
		// 通过AbstracyTabelModel来进行数据放入

		// 在面板3中设置

		// 新增數據
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 180, 40));
		JButton addbtn = new JButton();
		addbtn.setPreferredSize(new Dimension(100, 40));
		addbtn.setFont(new Font("", 1, 20));
		addbtn.setText("新增");
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

		// 修改數據
		JButton modifybtn = new JButton();
		modifybtn.setPreferredSize(new Dimension(100, 40));
		modifybtn.setFont(new Font("", 1, 20));
		modifybtn.setText("修改");
		panel3.add(modifybtn);

		modifybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请选中数据后进行修改");
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

		// 删除數據
		JButton deletebtn = new JButton();
		deletebtn.setPreferredSize(new Dimension(100, 40));
		deletebtn.setFont(new Font("", 1, 20));
		deletebtn.setText("删除");
		panel3.add(deletebtn);

		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();// 定义一个row 为table中的选中行
				if (row == -1) // row不等于-1证明有选中行
				{
					JOptionPane.showConfirmDialog(frame, "請選擇要刪除的數據", "删除数据",
							JOptionPane.YES_NO_OPTION);
				} else {
					int type = JOptionPane.showConfirmDialog(null, "是否要刪除數據");
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
		model.fireTableStructureChanged();// 调用STmodel中的model的在执行一遍的方法
	}

	public void refreshTable(List<Staff> list) {
		
		model.setData(list);
		model.fireTableStructureChanged();// 调用STmodel中的model的在执行一遍的方法
	}
	
}
