package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityDao;
import entity.Sheng;
import entity.Shi;

public class CityServlet2 extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String type = request.getParameter("type");
			if(type==null){
				init(request, response);
			}else if(type.equals("sheng")){
				
				sheng(request, response);
			}else if(type.equals("shi")){
				
				shi(request, response);
			}		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private void shi(HttpServletRequest request, HttpServletResponse response) {
		try {
		CityDao cityDao = new CityDao();
		int sId = Integer.parseInt(request.getParameter("sId"));
		List<Shi> shis = cityDao.searchShiBySheng(sId);
		PrintWriter out;
		
			out = response.getWriter();
			String str = "";
			for(int i=0;i<shis.size();i++){
				str+= shis.get(i).getId()+","+shis.get(i).getName()+";";
				
			}
			out.print(str.substring(0,str.length()-1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.getRequestDispatcher("city2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void sheng(HttpServletRequest request,HttpServletResponse response){
		
		try {
			CityDao cityDao = new CityDao();
			List<Sheng> shengs = cityDao.searchSheng();
			int sId = shengs.get(0).getId();
				PrintWriter out = response.getWriter();
				String str = "";
				for(int i=0;i<shengs.size();i++){
					str+= shengs.get(i).getId()+","+shengs.get(i).getName()+";";
					
				}
				out.print(str.substring(0,str.length()-1));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
    public void doPost(HttpServletRequest request,HttpServletResponse response){
		
		doGet(request, response);
		
	}

}
