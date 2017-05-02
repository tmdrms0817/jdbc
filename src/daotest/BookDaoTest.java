package daotest;

import java.util.List;

import dao.BookDao;
import vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		//insertTest();
		//updateTest();
		selectTest();
		//deleteTest( 1L );
		//selectTest( 1L );
	}

	public static void updateTest() {
		BookVo BookVo = new BookVo();
		
		BookVo.setNo( 2L );
		BookVo.setAuthor_no(2L);
		BookVo.setName( "robert2" );
		BookVo.setPrice(30000L);
		
		
		new BookDao().update(BookVo);
	}
	
	public static void insertTest() {
		BookVo BookVo = new BookVo();
		
		BookVo.setAuthor_no(1L);
		BookVo.setName( "robert2" );
		BookVo.setPrice(30000L);
		
		
		new BookDao().insert(BookVo);
	}
	
	public static void selectTest(){
		List<BookVo> list = new BookDao().getList();
		for( BookVo vo  : list ) {
			System.out.println( vo );
		}
	}
	
	public static void selectTest( Long no){
		BookVo vo = new BookDao().get( no );
		System.out.println( vo );
	}
	
	public static void deleteTest( Long no ) {
		new BookDao().delete( no );
	}
}
