package view.jixiao;

import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import entity.JiXiao;

public class JiXiaoTableModel extends AbstractTableModel 
{
    List<JiXiao> list = null;
    Set<JiXiao> saveSet;
    String[] columnNames = {"����","����","��Ŀ","��Ч","����"};
    
	public JiXiaoTableModel(List<JiXiao> list,Set<JiXiao> saveSet)//���췽�������ݲ���
	{
	this.list = list;
	this.saveSet = saveSet;
	}
	
//	public void setlist(List<Score> list) //�Զ��巽��������
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
	public boolean isCellEditable(int rowIndex, int columnIndex){
		if(columnIndex == 3){
			return true;
		}
		return false;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) // ÿ����Ԫ����ʾʲô row��   column��
	{
		
		if(columnIndex==0)
		{
		return list.get(rowIndex).getSta().getName();
		}
		else if(columnIndex==1)
		{
			return list.get(rowIndex).getSta().getBm().getName();
		}
		else if(columnIndex==2)
		{
			return list.get(rowIndex).getPro().getName();
		}
		else if(columnIndex==3)
		{
			if(list.get(rowIndex).getJixiao() != -1){
			return list.get(rowIndex).getJixiao();
			}else{
				return "";
			}
		}
		else if(columnIndex==4)
		{
		return list.get(rowIndex).getBonus();
		}
		else{
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue,int rowIndex,int columuIndex){
		
		list.get(rowIndex).setJixiao(Integer.parseInt((String) aValue));
		saveSet.add(list.get(rowIndex));
	}
	
	public void setData(List<JiXiao> list){	 
	     this.list = list;
	}
}
