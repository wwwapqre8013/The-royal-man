package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PromptDao;

public class PromptServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String mes = request.getParameter("mes");
			PromptDao dao = new PromptDao();
			List<String> list = dao.search(mes);
			
			PrintWriter out = response.getWriter();
			String str = "";
			for(int i=0;i<list.size();i++){
				str+= list.get(i) + ",";
			}
			if(str.length()>0){
			str=str.substring(0,str.length()-1);
			}
			out.print(str);
			
		
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
