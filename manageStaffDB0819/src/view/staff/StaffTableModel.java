package view.staff;

import java.util.ArrayList;
import java.util.List;
import entity.Staff;

import javax.swing.table.AbstractTableModel;

public class StaffTableModel extends AbstractTableModel 
{
    List<Staff> list = null;
    String[] columnNames = {"id","����","�Ա�","����","����"};
    
	public StaffTableModel(List<Staff> list)//���췽�������ݲ���
	{
	this.list = list;
	}
	
//	public void setlist(List<Student> list) //�Զ��巽��������
//	{
//		this.list = list;
//	}

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
		else if(columnIndex==2)
		{
			return list.get(rowIndex).getSex();
		}
		else if(columnIndex==3)
		{
			return list.get(rowIndex).getAge();
		}
		else if(columnIndex==4)
		{
			return list.get(rowIndex).getBm().getName();
		}
		else{
			return null;
		}
	}
	
	
	public void setData(List<Staff> list){
		 
	     this.list = list;
	}
}
