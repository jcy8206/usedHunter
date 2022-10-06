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
import com.jcy.usedhunter.domain.SearchCondition;
import com.jcy.usedhunter.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(SearchCondition sc, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL(); // 로그인을 안했으면 로그인 화면으로 이동
		}
		
		
		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			PageHandler ph = new PageHandler(totalCnt, sc); 
			
			
			List<BoardDto> list = boardService.getSearchResultPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", ph);

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
	
	@GetMapping("write")
	public String write(Model m) {
		m.addAttribute("mode", "new");
		return "board";
	}
	
	@PostMapping("write")
	public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rttr) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int rowCnt = boardService.write(boardDto);
			
			if(rowCnt!=1) {
				throw new Exception("Write failed");
			}
			rttr.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute(boardDto);
			m.addAttribute("msg", "WRT_ERROR");
			return "board";
		}
	}
	
	@PostMapping("modify")
	public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rttr) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int rowCnt = boardService.modify(boardDto);
			
			if(rowCnt!=1) {
				throw new Exception("Modify failed");
			}
			rttr.addFlashAttribute("msg", "MOD_OK");
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute(boardDto);
			m.addAttribute("msg", "MOD_ERROR");
			return "board";
		}
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
