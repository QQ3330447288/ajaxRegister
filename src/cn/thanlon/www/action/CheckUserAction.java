package cn.thanlon.www.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.thanlon.www.dao.BaseDao;
import cn.thanlon.www.entity.User;

public class CheckUserAction extends ActionSupport {
	/**
	 * 检查用户是否已经存在
	 * 
	 * @throws IOException
	 */
	public void checkUserName() throws IOException {
//		ServletActionContext类来自strut2-core-2.1.6.jar
//		获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName = request.getParameter("userName");
//		System.out.println(userName);
//		获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
//		设置response返回数据内容的编码
		response.setContentType("text/html,charset=utf-8");
		PrintWriter out = response.getWriter();
//		out.print(111);
		if (isExists(userName)) {
			out.print("1");
		} else {
			out.print("0");
		}
		out.flush();
		out.close();
	}

	public Boolean isExists(String userName) {
		String sql = "SELECT *FROM user WHERE username=?";
		BaseDao baseDao = new BaseDao();
		List<Object> param = new ArrayList<Object>();
		param.add(userName);
		try {
			List<User> list = baseDao.getModelsWidthSqlAndParam(sql, param, new User());
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		return false;
	}
}
