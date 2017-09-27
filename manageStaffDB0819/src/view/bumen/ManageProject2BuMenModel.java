package view.bumen;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.BuMen;
import entity.Project;

public class ManageProject2BuMenModel extends AbstractTableModel{
	    List<Project> list = null;
	    String[] columnNames = {"id","����"};
	    
		public ManageProject2BuMenModel(List<Project> list)//���췽�������ݲ���
		{
		this.list = list;
		}
		
//		public void setlist(List<BanJi> list) //�Զ��巽��������
//		{
//			this.list = list;
//		}

		@Override
		public int getRowCount() {// ��
			// TODO Auto-generated method stub
			return list.size();
		}
		
		 @Override
		 public String getColumnName(int columnIndex) {
		      
		        return columnNames[columnIndex];
		    }

		@Override
		public int getColumnCount() // ��
		{
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) // ÿ����Ԫ����ʾʲô row��   column��
		{
			if(columnIndex==0)
			{
			return list.get(rowIndex).getId();
			}
			else if(columnIndex==1)
			{
			return list.get(rowIndex).getName();
			}
			else{
				return null;
			}
		}
		
//		public void setData(List<BanJi> list){
//			this.list = list;//��AddBanJi����Ҫ��������ݴ���list��
//			fireTableDataChanged();//������ִ��һ��BanJiTableModel
//			
//		}	
		public void setData(List<Project> list){
			 
		     this.list = list;
}
}
