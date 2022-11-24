package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			writer.println("alert('아이디를 확인해 주세요.');");
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
				writer.println("alert('비밀번호를 확인해 주세요.');");
				writer.println("</script>");
				writer.flush();
				return "/login";
			}   
		}
	}
	
	//로그아웃
	@RequestMapping("logout.mem")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
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
			writer.println("alert('입력정보를 확인해 주세요.');");
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
	@RequestMapping(value="findpw.mem", method=RequestMethod.POST)
	public String findpw(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean findpw=memberDao.selectPwByIdEmail(memberBean);
		
		if(findpw!=null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('"+memberBean.getMember_id()+"님의 비밀번호는 "+findpw.getMember_pw()+" 입니다.');");
			writer.println("</script>");
			writer.flush();
			return "/login";
		}
		else if(findpw==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('입력정보를 확인해 주세요.');");
			writer.println("</script>");
			writer.flush();
			return "/findpw";		
		}
		return null;
	}
	
	//마이페이지
	@RequestMapping("mypage.mem")
	public String mypage() {
		return "/mypage";
	}
	
	//마이페이지 비밀번호 입력
	@RequestMapping(value="mypage.mem", method=RequestMethod.POST)
	public String mypage(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean mypage=memberDao.selectMemberByIdPw(memberBean);
		
		//비밀번호 일치하면 내정보수정페이지로
		if(mypage!=null) {
			return "/updatemem";
		}
		else if(mypage==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호를 확인해 주세요.');");
			writer.println("</script>");
			writer.flush();
			return "/mypage";		
		}
		return null;
	}
	
	//내정보수정
	@RequestMapping(value="updatemem.mem", method=RequestMethod.POST)
	public String updatemem(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		int updatemem=memberDao.updateMemberById(memberBean);
		System.out.println(updatemem);
		/*
		if(updatemem!=null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('"+memberBean.getMember_id()+"님의 비밀번호는 "+updatemem.getMember_pw()+" 입니다.');");
			writer.println("</script>");
			writer.flush();
			return "/login";
		}
		else if(updatemem==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('입력정보를 확인해 주세요.');");
			writer.println("</script>");
			writer.flush();
			return "/findpw";		
		}
		*/
		return "/mypage.";
	}	
}
