package com.jcy.usedhunter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jcy.usedhunter.domain.CommentPageHandler;

public class CommentPageHandlerTest {

	
	
	
	@Test
	public void test() {
		CommentPageHandler cp = new CommentPageHandler(250, 1);
		cp.print();
		assertTrue(cp.getBeginPage()==1);
		assertTrue(cp.getEndPage()==10);
	}
	
	@Test
	public void test2() {
		CommentPageHandler cp = new CommentPageHandler(250, 11);
		cp.print();
		assertTrue(cp.getBeginPage()==11);
		assertTrue(cp.getEndPage()==20);
	}

	@Test
	public void test3() {
		CommentPageHandler cp = new CommentPageHandler(255, 25);
		cp.print();
		assertTrue(cp.getBeginPage()==21);
		assertTrue(cp.getEndPage()==26);
	}
}
 