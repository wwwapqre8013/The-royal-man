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

public class CityServlet1 extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			
			CityDao cityDao = new CityDao();
			List<Sheng> shengs = cityDao.searchSheng();
			int sId = shengs.get(0).getId();
			if(request.getParameter("sId")==null){
				
				List<Shi> shis = cityDao.searchShiBySheng(sId);
				Sheng selectSheng = new Sheng();
				selectSheng.setId(sId);
				selectSheng.setShis(shis);
				request.setAttribute("shengs", shengs);
				request.setAttribute("selectSheng", selectSheng);
				request.getRequestDispatcher("city1.jsp").forward(request, response);
			}else{
				
				sId=Integer.parseInt(request.getParameter("sId"));
				List<Shi> shis = cityDao.searchShiBySheng(sId);
				PrintWriter out = response.getWriter();
				String str = "";
				for(int i=0;i<shis.size();i++){
					str+= shis.get(i).getId()+","+shis.get(i).getName()+";";
					
				}
				out.print(str.substring(0,str.length()-1));
			}
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public void doPost(HttpServletRequest request,HttpServletResponse response){
		
		doGet(request, response);
		
	}

}
