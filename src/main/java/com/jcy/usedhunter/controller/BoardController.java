package com.jcy.usedhunter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jcy.usedhunter.domain.BoardDto;
import com.jcy.usedhunter.domain.PageHandler;
import com.jcy.usedhunter.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL(); // 로그인을 안했으면 로그인 화면으로 이동
		}
		
		if (page==null) {
			page=1;
		}
		if (pageSize==null) {
			pageSize=10;
		}
		
		try {
			int totalCnt = boardService.getCount();
			PageHandler ph = new PageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			List<BoardDto> list = boardService.getPage(map);
			m.addAttribute("list", list);
			m.addAttribute("ph", ph);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "boardList";  // 로그인을 했으면 게시판 화면으로 이동
	}
	
	@GetMapping("read")
	public String read(Integer bno, Integer page, Integer pageSize, Model m) {
		try {
			BoardDto boardDto = boardService.read(bno);
//			m.addAttribute("boardDto", boardDto); 아래와 같은 코드
			m.addAttribute(boardDto);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	@PostMapping("remove")
	public String remove(HttpSession session, Integer bno, Integer page, Integer pageSize, Model m, RedirectAttributes rttr) {
		String writer = (String)session.getAttribute("id");
		try {
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			int rowCnt = boardService.remove(bno, writer);
			
			if(rowCnt != 1)
				throw new Exception("board remove error");
			rttr.addFlashAttribute("msg", "DEL_OK");
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msg", "DEL_ERROR");

		}
		return "redirect:/board/list"; // 모델에 담으면 "redirect:/board/list?page=&pageSize" 으로 자동으로 생성
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻고
		HttpSession session = request.getSession();
		// 2. 세션에  id 가 있는 지 확인 있으면 true 를 반환
//		if(session.getAttribute("id")!=null) {
//			return true;
//		} else {
//			return false;
//		}
		return session.getAttribute("id")!=null;
	}
}
