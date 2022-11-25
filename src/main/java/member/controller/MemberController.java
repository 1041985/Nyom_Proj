package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDAO;

@Controller
public class MemberController {

	@Autowired
	MemberDAO memberDao;
	
	//로그인페이지
	@RequestMapping("login.mem")
	public String loginPage() {
		return "/login";
	}
	
	//로그인
	@RequestMapping(value="login.mem", method=RequestMethod.POST)
	public String login(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean loginInfo=memberDao.selectMemberById(memberBean.getMember_id());
		
		if(loginInfo==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('아이디를 확인해주세요.');");
			writer.println("</script>");
			writer.flush();
			return "/login";		
		}
		else {
			if(memberBean.getMember_pw().equals(loginInfo.getMember_pw())) {
				session.setAttribute("loginInfo", loginInfo);
				String destination=(String)session.getAttribute("destination");
				return "redirect:/main.fs";
			}
			else {
				writer.println("<script type='text/javascript'>");
				writer.println("alert('비밀번호를 확인해주세요.');");
				writer.println("</script>");
				writer.flush();
				return "/login";
			}   
		}
	}
	
	//로그아웃
	@RequestMapping("logout.mem")
	public String logout(HttpSession session, 
			HttpServletResponse response) throws IOException {
		session.removeAttribute("loginInfo");
		/*
		Object destination=(String)session.getAttribute("destination");
		response.sendRedirect(destination != null ? (String) destination : "/myboard");
		 */
		return "redirect:/main.fs";
	}
	
	//아이디찾기페이지
	@RequestMapping("findid.mem")
	public String findid() {
		return "/findid";
	}
	
	//아이디찾기
	@RequestMapping(value="findid.mem", method=RequestMethod.POST)
	public String findid(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean findid=memberDao.selectIdByNameEmail(memberBean);
		
		if(findid!=null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('"+memberBean.getMember_name()+"님의 아이디는 "+findid.getMember_id()+" 입니다.');");
			writer.println("</script>");
			writer.flush();
			return "/login";
		}
		else if(findid==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('입력정보를 확인해주세요.');");
			writer.println("</script>");
			writer.flush();
			return "/findid";		
		}
		return null;
	}

	//비밀번호찾기페이지
	@RequestMapping("findpw.mem")
	public String findpw() {
		return "/findpw";
	}	
	
	//비밀번호찾기
	@RequestMapping(value = "findpw.mem", method=RequestMethod.POST)
	public String findpw2(
			@ModelAttribute("member") @Valid MemberBean member,
			BindingResult  result,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		
		
		System.out.println("member.getId() : " + member.getMember_id());
		System.out.println("member.getEmail() : " + member.getMember_email());
		
		MemberBean mb = memberDao.selectPwByIdEmail(member.getMember_id());
		PrintWriter out = response.getWriter();
		
		if(memberDao.selectPwByIdEmail(member.getMember_id()) == null) {
			out.print("<script>alert('아이디를 확인해주세요.'); history.back(-1);</script>");
			out.close();
		}
		
		else if(!member.getMember_email().equals(mb.getMember_email())) {
			out.print("<script>alert('이메일을 확인해주세요.'); history.back(-1);</script>");
			out.close();
			
		}
		else {
			//비밀번호 갖고오기
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			member.setMember_pw(pw);
			// 비밀번호 변경
			memberDao.updatePw(member);
			
			// 비밀번호 변경 메일 발송
			sendEmail(member, "findpw");
			
			out.print("<script>alert('이메일로 임시 비밀번호를 발송하였습니다.'); location.href='login.mem'</script>");
			out.close();
			
		}
		
		return "/login";
	}

	//비밀번호찾기에서 임시비밀번호 이메일 발송
	private void sendEmail(MemberBean member, String div) {
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "sihyuntest@naver.com";
		String hostSMTPpwd = "sihyunjava";
		
		String fromEmail = "sihyuntest@naver.com";
		String fromName = "Nyom";
		String subject = "";
		String msg = "";
		
		if(div.equals("findpw")) {
			subject = "Nyom 회원님 임시 비밀번호 발송";
			msg += "<div align='center' font-family:verdana'>";
			msg += "<h2><p> 안녕하세요. <br>";
			msg += member.getMember_id() + "님의 임시 비밀번호 입니다.</h2>";
			msg += "<p> 임시 비밀번호 : ";
			msg += member.getMember_pw() + "</p>";
			msg += "<p>※임시 비밀번호는 시일 내 변경하여 안전하게 계정을 관리하세요.</p></div>";
		}
		
		String mail = member.getMember_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSLOnConnect(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);
			
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.addTo(mail, member.getMember_email());
			email.setFrom(fromEmail, fromName);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		
	}
	
	//마이페이지
	@RequestMapping("mypage.mem")
	public String mypage() {
		return "/mypage";
	}
	
	//마이페이지 비밀번호 입력
	@RequestMapping(value="mypage.mem", method=RequestMethod.POST)
	public ModelAndView mypage(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		ModelAndView mav = new ModelAndView();
		MemberBean mypage=memberDao.selectMemberByIdPw(memberBean);
		
		//비밀번호 일치하면 내정보수정페이지로
		if(mypage!=null) {
			mav.addObject("mypage", mypage);
			mav.setViewName("/updatemem");
			return mav;
		}
		else if(mypage==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호를 확인해주세요.');");
			writer.println("</script>");
			writer.flush();
			
			mav.setViewName("/mypage");
			return mav;		
		}
		return null;
	}
	
	//내정보수정
	@RequestMapping(value="updatemem.mem", method=RequestMethod.POST)
	public ModelAndView updatemem(@ModelAttribute("memberBean") @Valid MemberBean memberBean, BindingResult result) {
		
		ModelAndView mav=new ModelAndView();

		MemberBean mypage=memberDao.selectMemberByIdPw(memberBean);
		
		if(result.hasErrors()) {
			mav.addObject("mypage", mypage);
			mav.setViewName("/updatemem");
			return mav;
		}
		memberDao.updateMemberById(memberBean);
		mav.setViewName("/mypage");
		return mav;
	}
	
	//회원가입페이지
	@RequestMapping("join.mem")
	public String joinPage() {
		return "/join";
	}
	
	//회원가입
	@RequestMapping(value="join.mem", method=RequestMethod.POST)
	public ModelAndView join(@ModelAttribute("memberBean") @Valid MemberBean memberBean, BindingResult result) {
		
		ModelAndView mav=new ModelAndView();

		if(result.hasErrors()) {
			mav.setViewName("/join");
			return mav;
		}
		memberDao.insertMember(memberBean);
		mav.setViewName("/login");
		return mav;
	}
	
	// 아이디 중복체크
	@RequestMapping("/checkId.mem")
	public @ResponseBody int checkId(String id) {
		int result = memberDao.checkId(id);
		return result;
	}	
	
	//회원탈퇴
	@RequestMapping("unjoin.mem")
	public String unjoin(String member_id,
			HttpServletResponse response,
			HttpSession session) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		int deletemem=memberDao.deleteMember(member_id);

		if(deletemem==1) {
			session.removeAttribute("loginInfo");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('탈퇴가 완료되었습니다.');");
			writer.println("location.href='main.fs'");
			writer.println("</script>");
			writer.flush();
		}
		return null;
	}
}
