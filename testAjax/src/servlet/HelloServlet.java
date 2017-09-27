package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			System.out.println(name + sex);
			PrintWriter out = response.getWriter();
			out.print("ÄãºÃ");
			
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
