package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.AuthorVo;
import vo.BookVo;

public class BookDao {

	public Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "dev", "dev");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾을수 없습니다.");
		}

		return conn;

	}

	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into book values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, bookVo.getAuthor_no());
			pstmt.setString(2, bookVo.getName());
			pstmt.setLong(3, bookVo.getPrice());

			int count = pstmt.executeUpdate();
			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
			return false;
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public List<BookVo> getList() {

		List<BookVo> list = new ArrayList<BookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select no , autor_no , name , price from book";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong(1);
				Long author_no = rs.getLong(2);
				String name = rs.getString(3);
				Long price = rs.getLong(4);

				BookVo bookVo = new BookVo();
				bookVo.setNo(no);
				bookVo.setName(name);
				bookVo.setAuthor_no(author_no);
				bookVo.setPrice(price);

				list.add(bookVo);
			}

			return list;

		} catch (SQLException e) {
			System.out.println("error:" + e);
			return list;
		} finally {

			/* 자원정리 */
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public BookVo get(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BookVo bookVo = null;

		try {
			conn = getConnection();

			String sql = "select  no , autor_no , name , price from book where no = ? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);

			// 5. sql문 실행
			rs = pstmt.executeQuery();

			// 5. fetch row( row를 하나씩 가져오기)
			if (rs.next()) {
				bookVo = new BookVo();

				bookVo.setNo(rs.getLong(1));
				bookVo.setAuthor_no(rs.getLong(2));
				bookVo.setName(rs.getString(3));
				bookVo.setPrice(rs.getLong(4));
			}

			return bookVo;

		} catch (SQLException e) {
			System.out.println("error:" + e);
			return bookVo;
		} finally {
			/* 자원정리 */
			try {
				if (rs != null) {
					rs.close();
				}
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
	
	public BookVo get( String name ) {
		return null;
	}
	
	
	public boolean delete( Long no ) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =	"delete from book where no = ?";
			pstmt = conn.prepareStatement( sql );
			
			//4. 바인딩
			pstmt.setLong( 1, no );
			
			//5. sql문 실행
			int count = pstmt.executeUpdate();
			return (count == 1);
			
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
			return false;
		} finally {
			
			/* 자원정리 */
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean update( BookVo bookVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =	"update book set no =? , autor_no = ? , name = ? , price = ?";
			pstmt = conn.prepareStatement( sql );
			
			//4. 바인딩
			pstmt.setLong( 1, bookVo.getNo() );
			pstmt.setLong( 2, bookVo.getAuthor_no() );
			pstmt.setString( 3, bookVo.getName() );
			pstmt.setLong( 3, bookVo.getPrice());
			
			//5. sql문 실행
			int count = pstmt.executeUpdate();
			return (count == 1);
			
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
			return false;
		} finally {
			
			/* 자원정리 */
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
