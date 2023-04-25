package ch12;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import ch11.RegisterBean;

public class RegisterMgr2 {

	private final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
	private final String JDBC_URL ="jdbc:mysql://localhost:3306/mydb"; 
//	private final String JDBC_URL ="jdbc:mysql://192.168.0.46:3306/mydb";
	private final String USER ="root";
	private final String PASS ="root";
	
	private DBConnectionMgr pool;
		
	
	public RegisterMgr2() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("Error: 커낵션 연결 실패");
		}
	}
	
	public boolean loginRegister(String id, String pwd){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginCon = false;
			try {
			
				con = pool.getConnection();			
				String query="select count(*) from tblRegister where id=? and pwd =?";
				pstmt= con.prepareStatement(query);
				pstmt.setString(1,id);
				pstmt.setString(2,pwd);
				System.out.println("아이디"+id);
				System.out.println("비번"+pwd);
				rs= pstmt.executeQuery();
				System.out.println("안녕5");
				System.out.println("1번"+rs.next());
				System.out.println("2번"+(rs.getInt(1)>0));
				System.out.println("3번"+(rs.next()||rs.getInt(1)>0));
				if(rs.next()||rs.getInt(1)>0) {
					loginCon=true;
				}
			}catch(Exception ex) {
				System.out.println("Exception"+ex);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
		return loginCon;
	}
	
	
	public RegisterBean2 getRegisterList(String id){ 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RegisterBean2 regBean = new RegisterBean2();
			try {
				con = pool.getConnection();
				String query="select * from tblRegister where id=?";
				pstmt= con.prepareStatement(query);
				pstmt.setString(1,id);
				rs= pstmt.executeQuery();
				if(rs.next()&&rs.getInt(1)>0) {
					regBean.setId(rs.getString("id"));
					regBean.setPwd(rs.getString("pwd"));
					regBean.setName(rs.getString("name"));
					regBean.setNum1(rs.getString("num1"));
					regBean.setNum2(rs.getString("num2"));
					regBean.setEmail(rs.getString("email"));
					regBean.setPhone(rs.getString("phone"));
					regBean.setZipcode(rs.getString("zipcode"));
					regBean.setNickname(rs.getString("nickname"));
					regBean.setAddress(rs.getString("address"));
					regBean.setJob(rs.getString("job"));
				
				}
			}catch(Exception ex) {
				System.out.println("Exception"+ex);
			}finally {
				if(rs!=null) try {rs.close();} catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
				if(con!=null) try {con.close();} catch(SQLException e) {}
				
				pool.freeConnection(con);
				
			}
		return regBean;
	}
	
}
