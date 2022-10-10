package com.jcy.usedhunter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jcy.usedhunter.domain.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao{

	@Autowired
	SqlSession session;
	private static String namespace = "com.jcy.usedhunter.dao.CommentMapper."; // 마지막에 '.' 잊지 말기
	
	
	@Override
	public int insert(CommentDto commentDto) throws Exception {
		return session.insert(namespace + "insert", commentDto);
	}
	@Override
	public CommentDto select(Integer cno) throws Exception {
		return session.selectOne(namespace + "select", cno);
	}
	@Override
	public List<CommentDto> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace + "selectAll", bno);
	}
	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + "count", bno);
	}
	@Override
	public int update(CommentDto commentDto) throws Exception {
		return session.update(namespace + "update", commentDto);
	}
	@Override
	public int delete(Integer cno, String commenter) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("commenter", commenter);
		return session.delete(namespace + "delete", map);
	}
	@Override
	public int deleteAll(Integer bno) throws Exception {
		return session.delete(namespace + "deleteAll", bno);
	}
	
	
}
