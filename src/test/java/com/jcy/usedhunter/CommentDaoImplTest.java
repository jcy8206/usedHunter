package com.jcy.usedhunter;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcy.usedhunter.dao.CommentDao;
import com.jcy.usedhunter.domain.CommentDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoImplTest {

	@Autowired
	CommentDao commentDao;
	
	@Test
	public void init() throws Exception {
		for(int i = 1; i<=220; i++) {
		CommentDto dto = new CommentDto(1066, 0, "comment"+i, "commenter"+i);
		commentDao.insert(dto);
		}
		
		
	}
	
	
	@Test
	@Ignore
	public void insert() throws Exception {
		commentDao.deleteAll(1);
		CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==1);
		
		commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==2);
	}
	
	@Test
	@Ignore
	public void select() throws Exception {
		commentDao.deleteAll(1);
		CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==1);
		
		List<CommentDto> list = commentDao.selectAll(1);
		String comment = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(comment.equals(commentDto.getComment()));
		assertTrue(commenter.equals(commentDto.getCommenter()));
	}
	
	@Test
	@Ignore
	public void selectAll() throws Exception {
		commentDao.deleteAll(1);
		CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==1);
		
		List<CommentDto> list = commentDao.selectAll(1);
		assertTrue(list.size()==1);
		
		commentDto = new CommentDto(1, 0, "comment", "asdf");
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==2);
		
		list = commentDao.selectAll(1);
		assertTrue(list.size()==2);
	}
	
	@Test
	@Ignore
	public void update() throws Exception {
		commentDao.deleteAll(1);
		CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==1);
		
		List<CommentDto> list = commentDao.selectAll(1);
		commentDto.setCno(list.get(0).getCno());
		commentDto.setComment("comment2");
		assertTrue(commentDao.update(commentDto)==1);
		
		list = commentDao.selectAll(1);
		String comment = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(comment.equals(commentDto.getComment()));
		assertTrue(commenter.equals(commentDto.getCommenter()));
	}
	
	@Test
	@Ignore
	public void count() throws Exception {
		commentDao.deleteAll(1);
		assertTrue(commentDao.count(1)==0);
	}
	
	@Test
	@Ignore
	public void delete() throws Exception {
		commentDao.deleteAll(1);
		CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf"); 
		assertTrue(commentDao.insert(commentDto)==1);
		assertTrue(commentDao.count(1)==1);
	}
	

}
