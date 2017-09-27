package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MD5;
import dao.UsersDao;
import dao.UsersDao1;
import entity.User;

public class UsersServlet1 extends HttpServlet {

	
	
	public String makeMD5(String password) { 
		 String psw = null;
		MessageDigest md; 
		password +="*%$15^";
		 try {
			md = MessageDigest.getInstance("MD5");
			//����һ��md5���ܼ���ժҪ
			 md.update(password.getBytes());   
			 // ����md5����   
			 psw = new BigInteger(1, md.digest()).toString(16).substring(10, 26);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return psw;
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// ���ö��󣨾Ÿ�������Ҫ��
		try {
			request.setCharacterEncoding("utf-8");
			// ��post������취
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			MD5 md5 = new MD5();
			String psw = makeMD5(password);
			// ��get������취
			// account = new String(account.getBytes("ISO-8859-1"),"utf-8");
			User searchUser = new User();
			searchUser.setUsername(account);
			searchUser.setPassword(psw);
			UsersDao1 ud = new UsersDao1();
			boolean flag = ud.insert(searchUser);
			if (flag==true) {
				// out.print("��½�ɹ�!");
				// response.sendRedirect("succes.jsp");
//				response.addCookie(cookie);
				request.getRequestDispatcher("succes.jsp").forward(request,
						response);
			} else {
				// out.print("��½ʧ��!");
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
