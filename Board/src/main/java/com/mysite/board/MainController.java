package com.mysite.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/board")
	@ResponseBody
	public String index() {
		return "안녕하세요 박규현 게시판에 오신걸 환영 합니다.";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}


