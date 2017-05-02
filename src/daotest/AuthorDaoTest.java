package daotest;

import java.util.List;

import dao.AuthorDao;
import vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		//insertTest();
		updateTest();
		selectTest();
		deleteTest( 1L );
		selectTest( 1L );
	}

	public static void updateTest() {
		AuthorVo authorVo = new AuthorVo();
		
		authorVo.setNo( 2L );
		authorVo.setName( "robert2" );
		authorVo.setBio( "blah2~ blah2~" );
		
		new AuthorDao().update(authorVo);
	}
	
	public static void insertTest() {
		AuthorVo authorVo = new AuthorVo();
		authorVo.setName( "robert1" );
		authorVo.setBio( "blah~ blah~" );
		
		new AuthorDao().insert(authorVo);
	}
	
	public static void selectTest(){
		List<AuthorVo> list = new AuthorDao().getList();
		for( AuthorVo vo  : list ) {
			System.out.println( vo );
		}
	}
	
	public static void selectTest( Long no){
		AuthorVo vo = new AuthorDao().get( no );
		System.out.println( vo );
	}
	
	public static void deleteTest( Long no ) {
		new AuthorDao().delete( no );
	}
}
