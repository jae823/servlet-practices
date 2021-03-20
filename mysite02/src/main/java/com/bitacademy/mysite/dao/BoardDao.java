package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.PageVo;

public class BoardDao {
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

	public List<BoardVo> findAll(PageVo pageVo) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, title, name, userno, hit_cnt, reg_date, depth, del_gb from board order by group_no desc, order_no asc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setInt(1, (pageVo.getNowPage()-1)*pageVo.getCntPerPage());
			pstmt.setInt(2, pageVo.getCntPerPage());
			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Long userNo = rs.getLong(4);
				int hit_cnt = rs.getInt(5);
				String dt = rs.getString(6);
				int depth = rs.getInt(7); 
				String delgb = rs.getString(8);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setUserNo(userNo);
				vo.setHit_cnt(hit_cnt);
				vo.setReg_date(dt);
				vo.setDepth(depth);
				vo.setDel_gb(delgb);
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


	public BoardVo findOne(Long no) {
		BoardVo vo = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, title, content, group_no, order_no, depth from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setLong(1, no);
			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			if(rs.next()) {
				Long number = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int groupNo = rs.getInt(4);		
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				vo = new BoardVo();
				vo.setNo(number);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setGroup_no(groupNo);
				vo.setOrder_no(orderNo);
				vo.setDepth(depth);
				// 조회수 증가
				updateHit_cnt(no);
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
		return vo;
	}

	public List<BoardVo> search(String searchText, PageVo pageVo) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, title, name, userno, hit_cnt, reg_date, depth, del_gb from board where title like ? or content like ? and del_gb = 'N' order by group_no desc, order_no asc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1,"%"+searchText+"%");
			pstmt.setString(2,"%"+searchText+"%");
			pstmt.setInt(3, (pageVo.getNowPage()-1)*pageVo.getCntPerPage());
			pstmt.setInt(4, pageVo.getCntPerPage());
			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Long userNo = rs.getLong(4);
				int hit_cnt = rs.getInt(5);
				String dt = rs.getString(6);
				int depth = rs.getInt(7); 


				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setUserNo(userNo);
				vo.setHit_cnt(hit_cnt);
				vo.setReg_date(dt);
				vo.setDepth(depth);
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

	private boolean updateHit_cnt(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardVo vo = new BoardVo();

		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "update board set hit_cnt = hit_cnt+1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setLong(1, no);
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			//6. 결과
			result = count == 1;
		}  catch (SQLException ex) {
			//1. 사과
			//2. log
			System.out.println(ex);
		} finally {
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


	public boolean insert(BoardVo vo, String reply) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {			
			conn = getConnection();

			//3. SQL 준비			
			String sql = "";
			if(reply.equals("Y")) {
				sql = "insert into board values (null, ?, ?, ?, ?, 0, now(), ?, ?, 0, 'N')";
			} else {
				sql = "insert into board values (null, ?, ?, ?, ?, 0, now(), ifnull((select max(group_no) from board a), 0)+1, 1, 0, 'N')";	
			}
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getName());
			pstmt.setLong(4, vo.getUserNo());
			//계층 생성
			if(reply.equals("Y")) {
				pstmt.setInt(5, vo.getGroup_no());
				pstmt.setInt(6, vo.getOrder_no());
			}

			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			//6. 결과 
			result = count == 1;

			if(result && reply.equals("Y")) {
				setHierarchy(vo.getGroup_no(), vo.getOrder_no(), vo.getDepth());
			}

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

	// 계층 생성
	@SuppressWarnings("resource")
	private boolean setHierarchy(int group_no, int order_no, int depth) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {			
			conn = getConnection();

			//3. SQL 준비 (기존 답글의 order_no++)
			
			
			//계층 Update
			String sql = "update board set depth = depth + 1 where group_no = ? and depth > ?+1";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, depth);
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			
			
			//정렬 Update
			sql = "update board set order_no = order_no + 1 where group_no = ? and order_no > ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, order_no);
			//5. SQL문 실행
			count = pstmt.executeUpdate();

			//현재 답글의 order_no++
			sql = "update board set order_no = order_no + 1, depth = ? + 1 where  no = (select * from (select last_insert_id(no) from board order by no desc limit 1) as temp)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depth);
			count = pstmt.executeUpdate();

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

	public boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {			
			conn = getConnection();

			//3. SQL 준비
			String sql = "update board set del_gb = 'Y' where no = ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setLong(1, no);
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

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {			
			conn = getConnection();

			//3. SQL 준비
			String sql = "update board set title= ?, content= ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
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

	public int getTotalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			if(rs.next()) {
				count = rs.getInt(1);
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

		return count;
	}


	public int searchTotalCount(String searchText) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select count(*) from board where title like ? or content like ? and del_gb = 'N' order by group_no desc, order_no asc";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩(PreparedStatment가 아닐경우 값을 바인딩하는 처리가 필요하다)
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setString(2, "%"+searchText+"%");
			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터 가져오기
			if(rs.next()) {
				count = rs.getInt(1);
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

		return count;
	}


}
