package cn.thanlon.www.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.thanlon.www.utils.SDKDemo;



public class SendCodeAction extends ActionSupport {
	public void sendCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String phone = request.getParameter("phone");
//		System.out.println(code);
//		System.out.println(phone);
		SDKDemo.sendCodeWithTel(phone, code);
	}
}
