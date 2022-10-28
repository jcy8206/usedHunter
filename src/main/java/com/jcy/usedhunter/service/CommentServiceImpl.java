package com.jcy.usedhunter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcy.usedhunter.dao.BoardDao;
import com.jcy.usedhunter.dao.CommentDao;
import com.jcy.usedhunter.domain.CommentDto;
import com.jcy.usedhunter.domain.CommentPageHandler;
import com.jcy.usedhunter.domain.SearchCondition;

@Service
public class CommentServiceImpl implements CommentService{
	
//	@Autowired
	BoardDao boardDao;
//	
//	@Autowired
	CommentDao commentDao;
	
//  @Autowired
	  public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
	      this.commentDao = commentDao;
	      this.boardDao = boardDao;
	  }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int write(CommentDto commentDto) throws Exception {
		boardDao.updateCommnetCnt(commentDto.getBno(), 1);
//		 throw new Exception("test");
		return commentDao.insert(commentDto);
	}

	@Override
	public CommentDto read(Integer cno) throws Exception {
		return commentDao.select(cno);
	}

	

	@Override
	public List<CommentDto> getList(Integer bno) throws Exception {
//		 throw new Exception("test");
		return commentDao.selectAll(bno);
	}
	@Override
	public List<CommentDto> getPage(Map map) throws Exception {
		return commentDao.selectPage(map);
	}

	@Override
	public int getCount(Integer bno) throws Exception {
		return commentDao.count(bno);
	}

	@Override
	public int modify(CommentDto commentDto) throws Exception {
		return commentDao.update(commentDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer cno, Integer bno, String commneter) throws Exception {
		int rowCnt = boardDao.updateCommnetCnt(bno, -1);
		System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//		 throw new Exception("test");
		rowCnt = commentDao.delete(cno, commneter);
		System.out.println("rowCnt = " + rowCnt);
		return rowCnt;
	}

	
}
