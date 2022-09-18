package com.jcy.usedhunter;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jcy.usedhunter.domain.User;

@Controller
public class RegisterController {
	
//	@RequestMapping("/register/add")
//	@RequestMapping(value="/register/save", method= {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save") // Spring 4.3 부터 추가
	public String save(User user, Model m) throws Exception {
		
		// 1. 유효성 검사
		if(!isValid(user)) {
			
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
			m.addAttribute("msg", msg);
			
//			return "redirect:/register/add?msg="+msg; // URL 재작성(rewriting)
			return "redirect:/register/add";
		}
		
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

private boolean isValid(User user) {
	// TODO Auto-generated method stub
	return true;
}
}
