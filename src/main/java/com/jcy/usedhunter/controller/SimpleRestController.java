package com.jcy.usedhunter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jcy.usedhunter.domain.CommentDto;
import com.jcy.usedhunter.domain.CommentPageHandler;
import com.jcy.usedhunter.service.CommentService;

@Controller
public class SimpleRestController {
	
	@Autowired
	CommentService commentService;
    @GetMapping("/test")
    public String test(Integer page, Integer pageSize, Integer bno, Model m, HttpServletRequest request) {
    	
    	if(page==null) page=1;
    	if(pageSize==null) pageSize=1;
    	
    	try {
    		int totalCnt = commentService.getCount(bno);
    		CommentPageHandler cp = new CommentPageHandler(totalCnt, page, pageSize);
    		
    		Map map = new HashMap();
    		map.put("offset", (page-1)*pageSize);
    		map.put("pageSize", pageSize);
    		map.put("bno", bno);
    		
    		List<CommentDto> list = commentService.getPage(map);
    		m.addAttribute("list", list);
    		m.addAttribute("cp", cp);
    		
    	}catch(Exception e){
    		
    	}
        return "test";
    }
    @GetMapping("/comment")
    public String comment() {
        return "comment";
    }

}
