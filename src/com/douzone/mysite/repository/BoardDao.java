package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardDao {
	
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

	public boolean delete(long no) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			
			String sql="delete from board where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);

	        int count = pstmt.executeUpdate();
	         
	        result = count == 1;

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public BoardVo getVo(Long board_no) {
		// TODO Auto-generated method stub
		BoardVo vo=new BoardVo();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="select * from board where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				long no=rs.getLong(1);
				String title=rs.getString(2);
				String contents=rs.getString(3);
				String write_date=rs.getString(4);
				int hit=rs.getInt(5);
				int g_no=rs.getInt(6);
				int o_no=rs.getInt(7);
				int dept=rs.getInt(8);
				long user_no=rs.getLong(9);
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(contents);
				vo.setWrite_date(write_date);
				vo.setHit(hit);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDept(dept);
				vo.setUser_no(user_no);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public void upHit(long no) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = getConnection();
	         String sql = "update board "
	                    + "set hit = hit + 1 "
	                  + "where no=" + no;
	         pstmt = conn.prepareStatement(sql);

	         pstmt.executeUpdate();
	      } catch (SQLException e) {
	         System.out.println("error :" + e);
	      } finally {
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
	   }

	public boolean modify(BoardVo vo) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = getConnection();
	         String sql = "update board "
	         		+ "set title=?, contents=? "
	         		+ "where no=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContent());
	         pstmt.setLong(3, vo.getNo());
	         
	         int count=pstmt.executeUpdate();
	         
	         result=count ==1;
	      } catch (SQLException e) {
	         System.out.println("error :" + e);
	      } finally {
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
		return result;
	}

	public boolean insert(BoardVo vo) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = getConnection();
	         String sql = "insert into board values "
	         		+ "(null, ?, ? , now(), 0, ifnull((select max(g_no)+1 from board a),1), 1, 0, ?);";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContent());
	         pstmt.setLong(3, vo.getUser_no());
	         
	         int count=pstmt.executeUpdate();
	         
	         result=count ==1;
	         
	      } catch (SQLException e) {
	         System.out.println("error :" + e);
	      } finally {
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
		
		return result;
	}

	public List<BoardVo> getList(String kwd, int currentPage) {
		// TODO Auto-generated method stub
		List<BoardVo> list=new ArrayList<BoardVo>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql=" select a.no, title, a.contents, write_date, hit, g_no, o_no, dept, user_no, b.name \r\n" + 
					"from (select * from board where title like ? or contents like ? ) as a , user b\r\n" + 
					"where a.user_no=b.no order by g_no desc, o_no asc limit 10 offset ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.setString(2, "%"+kwd+"%");
			pstmt.setInt(3, 10*(currentPage-1));
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				long no=rs.getLong(1);
				String title=rs.getString(2);
				String contents=rs.getString(3);
				String write_date=rs.getString(4);
				int hit=rs.getInt(5);
				int g_no=rs.getInt(6);
				int o_no=rs.getInt(7);
				int dept=rs.getInt(8);
				long user_no=rs.getLong(9);
				String user_name=rs.getString(10);
				
				BoardVo vo=new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(contents);
				vo.setWrite_date(write_date);
				vo.setHit(hit);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDept(dept);
				vo.setUser_no(user_no);
				vo.setUser_name(user_name);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		
		int count=1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="select count(*) from board";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
				conn.close();
				
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}

	public boolean insert_board(BoardVo vo) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = getConnection();
	         String sql ="update board set o_no = o_no+1 where g_no=? and o_no>=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, vo.getG_no());
	         pstmt.setInt(2, vo.getO_no()+1);
	         pstmt.executeUpdate();
	         
	         
	         sql = "insert into board values "
	         		+ "(null, ?, ? , now(), 0, ?, ?, ?, ?);";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContent());
	         pstmt.setInt(3, vo.getG_no());
	         pstmt.setInt(4, vo.getO_no()+1);
	         pstmt.setInt(5, vo.getDept()+1);
	         pstmt.setLong(6, vo.getUser_no());
	         
	         int count=pstmt.executeUpdate();
	         
	         result=count ==1;
	         
	      } catch (SQLException e) {
	         System.out.println("error :" + e);
	      } finally {
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
		
		return result;
	}

}
