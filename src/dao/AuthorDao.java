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

public class AuthorDao {
	
	private Connection getConnection() throws SQLException {
	
		Connection conn = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection( url, "dev", "dev" );
		} catch( ClassNotFoundException e ) {
			System.out.println( "JDBC Driver 를 찾을 수 없습니다." );
		} 
		
		return conn;
	}
	
	public boolean insert( AuthorVo authorVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql =	"insert into author values( null, ?, ? )";
			pstmt = conn.prepareStatement( sql );
			
			//4. 바인딩
			pstmt.setString( 1, authorVo.getName() );
			pstmt.setString( 2, authorVo.getBio() );
			
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
	
	public List<AuthorVo> getList() {
		List<AuthorVo> list = new ArrayList<AuthorVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. sql문 실행
			String sql = "select  no, name, bio from author";
			rs = stmt.executeQuery( sql );
			
			//5. fetch row( row를 하나씩 가져오기)
			while( rs.next() ){
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String bio = rs.getString( 3 );
				
				AuthorVo authorVo = new AuthorVo();
				authorVo.setNo(no);
				authorVo.setName(name);
				authorVo.setBio(bio);
				
				list.add( authorVo );
			}

			return list;
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
			return list;
		} finally {
			/* 자원정리 */
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public AuthorVo get( Long no ) {
		return null;
	}
	
	public AuthorVo get( String name ) {
		return null;
	}
	
	public boolean delete( Long no ) {
		return false;
	}
	
	public boolean update( AuthorVo authorVo ) {
		return false;
	}
}
