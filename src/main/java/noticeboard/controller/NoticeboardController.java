package noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeboardController {

	@RequestMapping("list.not")
	public String listNotice() {
		return "/listNotice";
	}

	@RequestMapping("insert.not")
	public String insertNotice() {
		return "/insertNotice";
	}
	
	@RequestMapping("update.not")
	public String updateNotice() {
		return "/updateNotice";
	}
	
}
