package com.jcy.usedhunter;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcy.usedhunter.dao.BoardDao;
import com.jcy.usedhunter.dao.CommentDao;
import com.jcy.usedhunter.domain.BoardDto;
import com.jcy.usedhunter.domain.CommentDto;
import com.jcy.usedhunter.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentServiceImplTest {

	@Autowired
	BoardDao boardDao;
	@Autowired
	CommentDao commentDao;
	@Autowired
	CommentService commentService;
	
	
	@Test
	public void write() throws Exception{
		boardDao.deleteAll();
		
		BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
		assertTrue(boardDao.insert(boardDto)==1);
		Integer bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		
		commentDao.deleteAll(bno);
		CommentDto commentDto = new CommentDto(bno, 0, "hi", "qwer");
		
		assertTrue(boardDao.select(bno).getComment_cnt()==0);
		assertTrue(commentService.write(commentDto)==1);
		
		Integer cno = commentDao.selectAll(bno).get(0).getCno();
		assertTrue(boardDao.select(bno).getComment_cnt()==1);
	}
	
	
	@Test
	public void remove() throws Exception{
		boardDao.deleteAll();
		
		BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
		assertTrue(boardDao.insert(boardDto)==1);
		Integer bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		
		commentDao.deleteAll(bno);
		CommentDto commentDto = new CommentDto(bno, 0, "hi", "qwer");
		
		assertTrue(boardDao.select(bno).getComment_cnt()==0);
		assertTrue(commentService.write(commentDto)==1);
		assertTrue(boardDao.select(bno).getComment_cnt()==1);
		
		Integer cno = commentDao.selectAll(bno).get(0).getCno();
		
		// ????????? ????????? ??????????????? Tx??? ??????????????? ??????
		int rowCnt = commentService.remove(cno, bno, commentDto.getCommenter());
		assertTrue(rowCnt==1);
		assertTrue(boardDao.select(bno).getComment_cnt()==0);
	}

}
