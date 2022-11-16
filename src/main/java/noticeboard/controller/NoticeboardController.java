package noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeboardController {

	@RequestMapping("list.not")
	public String listNotice() {
		return "/listNotice";
	}


}
