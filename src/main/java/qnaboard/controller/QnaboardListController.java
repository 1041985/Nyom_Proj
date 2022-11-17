package qnaboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaboardListController {
	@RequestMapping("qna.qna")
	public String main(HttpServletRequest request, Model model) {
			
	      return "/qna";
	}
	
	@RequestMapping("form.qna")
	public String form(HttpServletRequest request, Model model) {
			
	      return "/form";
	}
}
