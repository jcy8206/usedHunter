package com.jcy.usedhunter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jcy.usedhunter.domain.CommentDto;
import com.jcy.usedhunter.domain.CommentPageHandler;
import com.jcy.usedhunter.domain.SearchCondition;

public interface CommentDao {

	int insert(CommentDto commentDto) throws Exception;
	
	CommentDto select(Integer cno) throws Exception;
	
	List<CommentDto> selectAll(Integer bno) throws Exception;
	
	List<CommentDto> selectPage(Map map) throws Exception;
	
	int count(Integer bno) throws Exception;
	
	int update(CommentDto commentDto) throws Exception;
	
	int delete(Integer cno, String commenter) throws Exception;
	
	int deleteAll(Integer bno) throws Exception;
	
	
}
