package member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberLoginController {

	//로그인페이지로 이동
	@RequestMapping("login.mem")
	public String loginPage(HttpServletRequest request, Model model) {
		return "/login";
	}
	
}
