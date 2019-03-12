package cn.thanlon.www.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BaseDao {
	
	/**驱动*/
	private static final String DRIVER="com.mysql.jdbc.Driver";
	
	/**连接*/
	private static final String URL="jdbc:mysql://localhost:3306/mydb";
	
	/**账户名字*/
	private static final String USER="root";
	
	
	/**密码*/
	private static final String PWD="nxl123";
	
	
	/**
	 * @throws Exception 
	 * 获取数据库连接
	 */
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	private void getConnection() throws Exception{
		
		if(conn==null){
			 //1 加载驱动类
			Class.forName(DRIVER);
			// 2 获取数据库连接
			conn = DriverManager.getConnection(
					URL, USER,
					PWD);
		}
		
	}
	
	
	/***
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql,List<Object> params)throws Exception{
		getConnection();
		//初始化句柄
		 pstmt= conn.prepareStatement(sql);
		//绑定参数
	    if(params!=null){
	    		for(int i=0;i<params.size();i++){
	    			pstmt.setObject(i+1, params.get(i));
	    		}
	    }
	    //执行sql
	    int count=pstmt.executeUpdate();
	    //关闭资源
	    pstmt.close();
	    pstmt=null;
	    conn.close();
	    conn=null;
	    
	   return count;
	}
	
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	 public ResultSet excuteQuery(String sql,List<Object> params)throws Exception{
			getConnection();
			 pstmt= conn.prepareStatement(sql);
			//绑定参数
		    if(params!=null){
		    		for(int i=0;i<params.size();i++){
		    			pstmt.setObject(i+1, params.get(i));
		    		}
		    }
		    //执行查询
		    rs=pstmt.executeQuery();
		    return rs;
	 }
	 
	 
	 /***
	  * 
	  * @param sql
	  * @param param
	  * @param t
	  * @return
	 * @throws Exception 
	  */
	 public <T> List<T> getModelsWidthSqlAndParam(String sql,List<Object> param,T t) throws Exception{
		 
		 //定义一个集合用来存放所有的数据
		 List<T>  list=new ArrayList<T>();
		 //获取t对应的类
		 Class<?> c= t.getClass();
		 
		 //查询数据
		 ResultSet rs=excuteQuery(sql, param);
		 
		 //遍历结果集，将数据封装成对象
		 while(rs.next()){
			 
			 //根据传递的类型初始化一个该对象
			 T obj=(T)c.newInstance();
			 //获取当前类里面的所有属性
			 Field[] fields=c.getDeclaredFields();
			 //username age pwd
			 for(Field f:fields){
				 //取出来当前属性对应的类型
				 //int   String 
				 String type=f.getType().getSimpleName();
				 //获取属性的名字
				 String fname=f.getName();
				 //判断当前列是否存在
				 if(!isExists(rs, fname))continue;
				 
				 Object value=null;
				 //取出当前属性对应的数据库里的值
				 if("string".equalsIgnoreCase(type)){
					 value=rs.getString(fname);
				 }else if("int".equalsIgnoreCase(type)){
					 value=rs.getInt(fname);
				 }else if("float".equalsIgnoreCase(type)){
					 value=rs.getFloat(fname);
				 }else if("double".equalsIgnoreCase(type)){
					 value=rs.getDouble(fname);
				 }else if("boolean".equalsIgnoreCase(type)){
					 value=rs.getBoolean(fname);
				 }else if("date".equalsIgnoreCase(type)){
					 value=rs.getDate(fname);
				 }
				 
				 //获取方法
				 fname=fname.substring(0,1).toUpperCase()+fname.substring(1);
				 String mName="set"+fname;
				 Method m= c.getDeclaredMethod(mName, f.getType());
				 m.invoke(obj, value);
				 
				  
			 }
			 //将obj加入集合
			 list.add(obj);
		 }
		 
		 //关掉
		 close();
		 return list;
	 }
	 
	 
	 
	 /**
	  * 
	  * @param rs
	  * @param clumn
	  * @return
	  */
	 public boolean isExists(ResultSet rs,String colum){
	   try {
			rs.findColumn(colum);
		} catch (SQLException e) {
			return false;
		}
		 return true;
	 }
	 
 
	 
	 
	 /**
	  * 关闭
	  */
	 public void close(){
		 		try {
		 			if(rs!=null){
						rs.close();
						rs=null;
				 	}
		 			if(pstmt!=null){
		 				pstmt.close();
		 				pstmt=null;
				 	}
		 			if(conn!=null){
		 				conn.close();
		 				conn=null;
				 	}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
	 
	 /**
	  * 获取索引
	  * @param seq
	  * @return
	  * @throws Exception
	  */
		public int getSequenceIndex(String seq) throws Exception{
			String sql="select "+seq+".nextval num from dual ";
			getConnection();
			
			ResultSet rs= excuteQuery(sql, null);
			try {
				if(rs.next()){
					return rs.getInt("num");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

	 
}
