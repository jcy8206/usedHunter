package com.jcy.usedhunter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL(); // 로그인을 안했으면 로그인 화면으로 이동
		}
		return "boardList";  // 로그인을 했으면 게시판 화면으로 이동
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
