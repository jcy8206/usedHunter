package com.jcy.usedhunter.dao;

import java.util.List;
import java.util.Map;

import com.jcy.usedhunter.domain.BoardDto;
import com.jcy.usedhunter.domain.SearchCondition;

public interface BoardDao {
	int insert(BoardDto dto) throws Exception;

	BoardDto select(Integer bno) throws Exception;
	
	List<BoardDto> selectAll() throws Exception;
	List<BoardDto> selectPage(Map map) throws Exception;
	
	int update(BoardDto dto) throws Exception;
	int count() throws Exception;

	int updateCommnetCnt(Integer bno, int cnt);
	int increaseViewCnt(Integer bno) throws Exception;
	int delete(Integer bno, String writer) throws Exception;
	int deleteAll() throws Exception;

	
	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
	int searchResultCnt(SearchCondition sc) throws Exception;








}