package com.jcy.usedhunter;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcy.usedhunter.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test{
	
	@Autowired
	DataSource ds;
	
	
	@Test
	public void insertUserTest() throws Exception{
		deleteAll();
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt==1);
		
	}
	
	@Test
	public void selectUserTest() throws Exception {
		deleteAll();
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(), "facebook", new Date());
		insertUser(user);
		User user2 = selectUser("asdf");
		
		assertTrue(user2.getId().equals("asdf"));
	}
	
	@Test
	public void deleteUserTest() throws Exception{
		deleteAll();
		int rowCnt = deleteUser("asdf");
		assertTrue(rowCnt==0);
		
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(), "facebook", new Date());
		insertUser(user);
		
		int rowCnt2 = deleteUser("asdf");
		assertTrue(rowCnt2==1);
		assertTrue(selectUser(user.getId())==null);
	}
	
	@Test
	public void updateUserTest() throws Exception{
		deleteAll();
		User user = new User("asdf", "1234", "adb", "aaa@aaa.com", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		assertTrue(rowCnt==1);
		
		User user2 = new User("asdf", "4567", "zzz", "bbb@bbb.com", new Date(), "kakao", new Date());
		rowCnt = updateUser(user2);
		assertTrue(rowCnt==1);
		
		assertTrue(selectUser("asdf").getName().equals("zzz"));
	}
	
	// 사용자 정보를 user_info 테이블에 저장하는 메서드
	public int insertUser(User user) throws Exception{
		Connection conn = ds.getConnection();
		
//		INSERT INTO `usedhunter`.`user_info`(`id`,`pwd`,`name`,`email`,`birth`,`sns`,`reg_date`)
//		VALUES('asdf2','1234','smith','aaa@aaa.com','2021-01-01','facebook',now());
		
		String sql = "INSERT INTO `usedhunter`.`user_info` VALUES(?, ?, ?, ?, ?, ?, now())";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL injection 방어에 유리, 성능 향상
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime())); // util.Date 인 user.getBirth() 를 sql.Date 로 변환
		pstmt.setString(6, user.getSns());
		
		int rowCnt = pstmt.executeUpdate(); // 영향 받은 로우행 개수 반환, insert, delete, update 에 사용
		return rowCnt;
	}
	
	public User selectUser(String id) throws Exception{
		Connection conn = ds.getConnection();
		
		String sql = "SELECT * FROM usedhunter.user_info where id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL injection 방어에 유리, 성능 향상
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery(); // select에 사용
		if(rs.next()) {
			User user = new User();
			user.setId(rs.getString(1));
			user.setPwd(rs.getString(2));
			user.setName(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setBirth(new Date(rs.getDate(5).getTime())); // util.date 를 sql.date 로 변환
			user.setSns(rs.getString(6));
			user.setReg_date(new Date(rs.getTimestamp(7).getTime())); // util.date 를 sql.date 로 변환
			
			return user;
		}
		return null;
	}
	
	public int deleteUser(String id) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "delete from user_info where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL injection 방어에 유리, 성능 향상
		pstmt.setString(1, id);
		int rowCnt = pstmt.executeUpdate(); // 영향 받은 로우행 개수 반환, insert, delete, update 에 사용
		return rowCnt;
	}
	
	
	private void deleteAll() throws Exception{
		Connection conn = ds.getConnection();


		String sql = "delete from `usedhunter`.`user_info`";

		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL injection 방어에 유리, 성능 향상

		pstmt.executeUpdate(); // 영향 받은 로우행 개수 반환, insert, delete, update 에 사용
	}
	
	
	public int updateUser(User user) throws Exception{
		Connection conn = ds.getConnection();
		
		String sql = "update user_info set pwd=?, name=?, email=?, birth=?, sns=? where id=? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(5, user.getSns());
		pstmt.setString(6, user.getId());
		
		int rowCnt = pstmt.executeUpdate();
		
		return rowCnt;
	}

	@Test
	public void main() throws Exception{
//		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//	      DataSource ds = ac.getBean(DataSource.class);

	      Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

	      System.out.println("conn = " + conn);
	      assertTrue(conn!=null); // 괄호 안의 조건식이 true면 테스트 성공, 아니면 실패
	}
}
