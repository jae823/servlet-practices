package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.GuestbookVo;

public class GuestbookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException ex) {
			//1. 사과
			//2. log
			System.out.println("error" + ex);
			ex.printStackTrace();
			//3. 안전하게 종료
			// ex) return ...
		} 
		return conn;
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, name, contents, reg_date from guestbook order by reg_date";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)

			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String dt = rs.getString(4);
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setDt(dt);
				list.add(vo);
			}
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			conn = getConnection();
			
			//3. SQL 준비
			String sql = "insert into guestbook values (null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			//6. 결과
			result = count == 1;
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
			// 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}

	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {			
			conn = getConnection();

			//3. SQL 준비
			String sql = "delete from guestbook where no = ? and password=?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			//6. 결과
			result = count == 1;
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
			// 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		return result;
	}
}
