package view.project;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;

public class ProjectTableModel extends AbstractTableModel 
{
    List<Project> list = null;
    String[] columnNames = {"id","名称"};
    
	public ProjectTableModel(List<Project> list)//构造方法来传递参数
	{
	this.list = list;
	}
	
//	public void setlist(List<Subject> list) //自定义方法传参数
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
		else{
			return null;
		}
	}
	
//	public void setData(List<Subject> list){
//		this.list = list;//把AddSubject中想要保存的数据传到list中
//		fireTableDataChanged();//返回再执行一遍SubjectTableModel
//		
//	}
	
	
	public void setData(List<Project> list){
		 
	     this.list = list;
	}
}
