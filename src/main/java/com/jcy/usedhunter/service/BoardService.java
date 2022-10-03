package com.jcy.usedhunter.service;

import java.util.List;
import java.util.Map;

import com.jcy.usedhunter.domain.BoardDto;



public interface BoardService {

	int getCount() throws Exception;

	int remove(Integer bno, String writer) throws Exception;

	int write(BoardDto boardDto) throws Exception;

	List<BoardDto> getList() throws Exception;

	BoardDto read(Integer bno) throws Exception;

	List<BoardDto> getPage(Map map) throws Exception;

	int modify(BoardDto boardDto) throws Exception;

	

}