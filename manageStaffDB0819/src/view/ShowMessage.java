package view;

import javax.swing.JOptionPane;

import util.Constant;

public class ShowMessage {
	public static void show(boolean flag,String type) {
		
		if(flag){
			JOptionPane.showMessageDialog(null, type+Constant.MES_SUCCESS);
		}
		else{
			JOptionPane.showMessageDialog(null, type+Constant.MES_FAILE);
		}
	}
}
