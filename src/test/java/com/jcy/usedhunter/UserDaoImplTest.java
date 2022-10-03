package com.jcy.usedhunter;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcy.usedhunter.dao.UserDao;
import com.jcy.usedhunter.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
	
	@Autowired
	UserDao userDao;
	
	@Ignore
	@Test
	public void deleteUser() throws Exception  {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2022, 1, 1);
		
		userDao.deleteAll();
		
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "facebook", new Date());
		userDao.insertUser(user);
		
		int rowCnt = userDao.deleteUser("asdf");
		assertTrue(rowCnt==1);
		assertTrue(userDao.selectUser(user.getId())==null);
	}
	
	@Ignore
	@Test
	public void selectUser()throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2022, 1, 1);
		
		userDao.deleteAll();
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "facebook", new Date());
		userDao.insertUser(user);
		User user2 = userDao.selectUser("asdf");
		
		assertTrue(user2.getId().equals("asdf"));
	}
	
	
	@Test
	public void insertUser() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		userDao.deleteAll();
		User user = new User("asdf2", "1234", "adb", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "facebook", new Date());
		int rowCnt = userDao.insertUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Ignore
	@Test
	public void updateUser() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		userDao.deleteAll();
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "facebook", new Date());
		int rowCnt = userDao.insertUser(user);
		assertTrue(rowCnt==1);
		
		user.setPwd("bbb");
		user.setEmail("bbb@bbb.com");
		rowCnt = userDao.updateUser(user);
		
		assertTrue(rowCnt==1);
		
		User user2 = userDao.selectUser(user.getId());
		System.out.println("user = " + user);
		System.out.println("user2 = " + user2);
		assertTrue(user.equals(user2));
		
		
	}

}
