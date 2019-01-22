package semi.kh.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//컨텍스트 객체 생성 : resource를 JNDI API를 통해 찾음
			Context ctx = new InitialContext();
			DataSource pool = (DataSource)ctx.lookup("java:/comp/env/jdbc/myoracle");
			conn = pool.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed())
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void close(PreparedStatement pstmt) {
			try {
				if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
			}catch(SQLException e) {
				e.printStackTrace();
		}
	}
}
