package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import util.Constant;
import view.bumen.BuMenView;
import view.jixiao.JiXiaoView;
import view.project.ProjectView;
import view.staff.StaffView;

public class MainView extends JFrame {

	public void init() {

		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel mainPanel = (JPanel) this.getContentPane();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JButton stuButton = new JButton("Ա������");
		stuButton.setFont(new Font(null, 1, 25));

		stuButton.setPreferredSize(new DimensionUIResource(150, 80));
		JButton bjButton = new JButton("���Ź���");
		bjButton.setFont(new Font(null, 1, 25));

		bjButton.setPreferredSize(new DimensionUIResource(150, 80));
		JButton subButton = new JButton("��Ŀ����");
		subButton.setFont(new Font(null, 1, 25));

		subButton.setPreferredSize(new DimensionUIResource(150, 80));
		JButton scButton = new JButton("��Ч����");
		scButton.setPreferredSize(new DimensionUIResource(150, 80));
		scButton.setFont(new Font(null, 1, 25));

		stuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StaffView sv = StaffView.getInstance();
				sv.creatFrame();// ����Frame�е� init����
			}
		});

		bjButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BuMenView bv = BuMenView.getInstance();
				bv.creatFrame();// ����Frame�е� init����
			}
		});
		
		subButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProjectView sv = ProjectView.getInstance();
				sv.creatFrame();// ����Frame�е� init����
			}
		});
		
		scButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JiXiaoView sv = JiXiaoView.getInstance();
				sv.creatFrame();
			}
		});

		mainPanel.add(stuButton);
		mainPanel.add(bjButton);
		mainPanel.add(subButton);
		mainPanel.add(scButton);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new MainView().init();
	}
}
