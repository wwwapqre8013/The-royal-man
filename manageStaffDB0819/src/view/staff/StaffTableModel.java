package view.staff;

import java.util.ArrayList;
import java.util.List;
import entity.Staff;

import javax.swing.table.AbstractTableModel;

public class StaffTableModel extends AbstractTableModel 
{
    List<Staff> list = null;
    String[] columnNames = {"id","姓名","性别","年龄","部门"};
    
	public StaffTableModel(List<Staff> list)//构造方法来传递参数
	{
	this.list = list;
	}
	
//	public void setlist(List<Student> list) //自定义方法传参数
//	{
//		this.list = list;
//	}

	@Override
	public int getRowCount() {// 行
		// TODO Auto-generated method stub
		return list.size();
	}
	
	 @Override
	 public String getColumnName(int columnIndex) {
	      
	        return columnNames[columnIndex];
	    }

	@Override
	public int getColumnCount() // 列
	{
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) // 每个单元格显示什么 row行   column列
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
