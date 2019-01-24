package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserDao {
	
	public int insert(UserVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = 
				" insert" + 
				"   into user" + 
				" values ( null, ?, ?, ?, ?, now() )";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. 연결하기
			String url="jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch( ClassNotFoundException e ) {
			System.out.println( "드러이버 로딩 실패:" + e );
		} 
		
		return conn;
	}	
}