package com.jcy.usedhunter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcy.usedhunter.domain.CommentDto;
import com.jcy.usedhunter.domain.CommentPageHandler;
import com.jcy.usedhunter.domain.SearchCondition;
import com.jcy.usedhunter.service.CommentService;

//@Controller
@RestController
public class CommentController {
	@Autowired
	CommentService service;

//	{
//	    "pcno":0,
//	    "comment":"modifyTest",
//	    "commenter":"asdf2"
//	}
	
	@PatchMapping("/comments/{cno}") // /comments/cno
//	@ResponseBody
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session){
	
		String commenter = (String)session.getAttribute("id");
//		String commenter = "asdf2";
		
		commentDto.setCommenter(commenter);
		commentDto.setCno(cno);
		System.out.println("dto = " + commentDto);
		
		try {
			if(service.modify(commentDto)!=1) {
				throw new Exception("Modify Failed");
			}
			return new ResponseEntity<>("MOD_OK",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MOD_ERROR",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
//	{
//	    "pcno":0,
//	    "comment":"hi"
//	}
	@PostMapping("/comments") // /comments?bno=1081
//	@ResponseBody
	public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session){
	
		String commenter = (String)session.getAttribute("id");
//		String commenter = "asdf2";
		
		commentDto.setCommenter(commenter);
		commentDto.setBno(bno);
		System.out.println("dto = " + commentDto);
		
		try {
			if(service.write(commentDto)!=1) {
				throw new Exception("Write Failed");
			}
			return new ResponseEntity<>("WRT_OK",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("WRT_ERROR",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/comments/{cno}") // /comments/1?bno=1080
//	@ResponseBody
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
		String commenter = (String)session.getAttribute("id");
//		String commenter = "asdf2";
		
		try {
			int rowCnt = service.remove(cno, bno, commenter);
			
			if (rowCnt!=1) {
				throw new Exception("Delete Failed");
			}
			return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("DEL_ERROR", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
//	@GetMapping("/comments")
////	@ResponseBody
//	public ResponseEntity<List<CommentDto>> list(Integer bno){
//
//    	
//		
//		List<CommentDto> list = null;
//		try {
//			list = service.getList(bno);
//		
//			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
//		}
//	}
	

	@GetMapping(value="/comments", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}) // /comments?bno=1066&page=2&pageSize=10
//	@ResponseBody
	public ResponseEntity<Map<String, Object>> getList(Integer page, Integer pageSize, Integer bno){

		
		 
		try {
			int totalCnt = service.getCount(bno);
			CommentPageHandler ph = new CommentPageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", ph.getOffset());
			map.put("pageSize", ph.getPageSize());
			map.put("bno", bno);
			List<CommentDto> list = service.getPage(map);
			
			Map map2 = new HashMap();
			map2.put("ph", ph);
			map2.put("list", list);
			
			return new ResponseEntity<Map<String, Object>>(map2, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}

}
