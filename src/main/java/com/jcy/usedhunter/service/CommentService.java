package com.jcy.usedhunter.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jcy.usedhunter.domain.CommentDto;

public interface CommentService {
	@Transactional(rollbackFor = Exception.class)
	int write(CommentDto commentDto) throws Exception;
	
	CommentDto read(Integer cno) throws Exception;
	List<CommentDto> getList(Integer bno) throws Exception;
	int getCount(Integer bno) throws Exception;
	int modify(CommentDto commentDto) throws Exception;
	
	@Transactional(rollbackFor = Exception.class)
	int remove(Integer cno, Integer bno, String commneter) throws Exception;
}
