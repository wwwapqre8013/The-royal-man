package view.jixiao;

import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import entity.JiXiao;

public class JiXiaoTableModel extends AbstractTableModel 
{
    List<JiXiao> list = null;
    Set<JiXiao> saveSet;
    String[] columnNames = {"姓名","部门","项目","绩效","奖金"};
    
	public JiXiaoTableModel(List<JiXiao> list,Set<JiXiao> saveSet)//构造方法来传递参数
	{
	this.list = list;
	this.saveSet = saveSet;
	}
	
//	public void setlist(List<Score> list) //自定义方法传参数
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
	public boolean isCellEditable(int rowIndex, int columnIndex){
		if(columnIndex == 3){
			return true;
		}
		return false;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) // 每个单元格显示什么 row行   column列
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
