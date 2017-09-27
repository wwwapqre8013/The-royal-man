package view.bumen;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import dao.BuMenDao;
import entity.BuMen;

public class AddBuMenView {
	JTextField nameText;
	JTextField staNumsText;
	JFrame frame;
	CallBack callBack;
	private static  AddBuMenView instance;
	
	public static AddBuMenView getInstance(){
		
		if(instance == null)
		{
			instance = new AddBuMenView();
		}
		return instance;
	}
	
	private AddBuMenView()
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
		frame.setSize(300, 480);
		frame.setLocationRelativeTo(null);
		frame.setTitle("�����༶");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(300, 120));	
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 120));
		panel.add(panel1);
		panel.add(panel2);
		frame.add(panel);
		
		//��������
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel label1 = new JLabel();
		label1.setText("�༶����");
		panel1.add(label1);
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 40));
		panel1.add(nameText);

		//��ť���沿��
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton savebtn = new JButton();
		savebtn.setPreferredSize(new DimensionUIResource(120, 40));
		savebtn.setText("����");
		panel2.add(savebtn);
		//�������
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BuMenDao bmDao  = new BuMenDao();
				String name = nameText.getText();
				nameText.setText("");
				BuMen bm = new BuMen();
				bm.setName(name);
				boolean rs = bmDao.add(bm);
				ShowMessage.show(rs, Constant.MES_ADD);
				frame.dispose();
				callBack.call();
				//ְ�ܾ��ǻص�����Ϊֻ��addbm֪��ʲôʱ��ˢ�£�addbm���Կ���ʲôʱ�����
			}
		});
		    frame.setVisible(true);
        }
}
