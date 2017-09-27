package view.project;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;

public class ProjectTableModel extends AbstractTableModel 
{
    List<Project> list = null;
    String[] columnNames = {"id","����"};
    
	public ProjectTableModel(List<Project> list)//���췽�������ݲ���
	{
	this.list = list;
	}
	
//	public void setlist(List<Subject> list) //�Զ��巽��������
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
		else{
			return null;
		}
	}
	
//	public void setData(List<Subject> list){
//		this.list = list;//��AddSubject����Ҫ��������ݴ���list��
//		fireTableDataChanged();//������ִ��һ��SubjectTableModel
//		
//	}
	
	
	public void setData(List<Project> list){
		 
	     this.list = list;
	}
}
