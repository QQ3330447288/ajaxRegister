package cn.thanlon.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "root";
	private static final String PWD = "123456";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@SuppressWarnings("unused")
	private void getConnection() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			// 1 ����������
			Class.forName(DRIVER);
			// 2 ��ȡ���ݿ�����
			conn = DriverManager.getConnection(URL, USER, PWD);
		}
	}
}
