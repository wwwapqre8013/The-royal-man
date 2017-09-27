package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;
import dao.UsersDao1;
import entity.User;

public class UsersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		// 内置对象（九个）最重要的
		try {
			request.setCharacterEncoding("utf-8");
			// 表单post用这个办法
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			int time = Integer.parseInt(request.getParameter("time"));
			// 表单get用这个办法
			// account = new String(account.getBytes("ISO-8859-1"),"utf-8");
			User searchUser = new User();
			searchUser.setUsername(account);
			searchUser.setPassword(password);
			UsersDao1 ud = new UsersDao1();
			User users = ud.searchByUserNameAndPassword(searchUser);
			if (users!=null) {
//				 out.print("登陆成功!");
//				 response.sendRedirect("succes.jsp");

				Cookie cookie = new Cookie("usersName", users.getUsername());
				if (time == 1) {
					cookie.setMaxAge(0);
				} else if (time == 2) {
					cookie.setMaxAge(30);
				} else if (time == 3) {
					cookie.setMaxAge(60);
				}
				response.addCookie(cookie);
				request.getRequestDispatcher("succes.jsp").forward(request,
						response);
			} else {
				// out.print("登陆失败!");
				response.sendRedirect("fail.jsp");
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
