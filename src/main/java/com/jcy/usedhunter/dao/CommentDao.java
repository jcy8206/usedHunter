package com.jcy.usedhunter.dao;

import java.util.List;

import com.jcy.usedhunter.domain.CommentDto;

public interface CommentDao {

	int insert(CommentDto commentDto) throws Exception;
	
	CommentDto select(Integer cno) throws Exception;
	
	List<CommentDto> selectAll(Integer bno) throws Exception;
	
	int count(Integer bno) throws Exception;
	
	int update(CommentDto commentDto) throws Exception;
	
	int delete(Integer cno, String commenter) throws Exception;
	
	int deleteAll(Integer bno) throws Exception;
	
	
}
