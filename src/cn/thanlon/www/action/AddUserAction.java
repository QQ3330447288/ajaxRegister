package cn.thanlon.www.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.thanlon.www.dao.BaseDao;
import cn.thanlon.www.utils.Md5;

public class AddUserAction extends ActionSupport {
	public void addUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName = request.getParameter("userName");
		String pwd = Md5.md5(request.getParameter("pwd"), "Thanlon");
//		System.out.println(pwd);
		String phone = Md5.md5(request.getParameter("phone"), "Thanlon");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println(userName+phone+pwd);
		if (iSaddUser(userName, pwd, phone)) {
			out.print(1);
		} else {
			out.print(0);
		}
		out.flush();
		out.close();
	}

	private Boolean iSaddUser(String userName, String pwd, String phone) {
		String sql = "INSERT user(username,pwd,phone) VALUES(?,?,?)";
		List<Object> parames = new ArrayList<Object>();
		parames.add(userName);
		parames.add(pwd);
		parames.add(phone);
		BaseDao dao = new BaseDao();
		try {
			int count = dao.executeUpdate(sql, parames);
			return count > 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
