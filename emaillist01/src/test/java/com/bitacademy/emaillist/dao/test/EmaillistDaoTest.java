package com.bitacademy.emaillist.dao.test;

import java.util.List;

import com.bitacademy.emaillist.dao.EmaillistDao;
import com.bitacademy.emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {
	//원래는 mock data를 가지고 해야한다
	
	public static void main(String[] args) {
		// insert test
		//testinsert();
		// findAll test
		testfindAll();
	}
	
	public static void testinsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirst_name("마");
		vo.setLast_name("이콜");
		vo.setEmail("michol@gmail.com");
		
		new EmaillistDao().insert(vo);
	}


	public static void testfindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		//assertEqual(list); 실제 Spring junit test에서 사용하는 메소드
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}
}
