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
	
	//�α���������
	@RequestMapping("login.mem")
	public String loginPage() {
		return "/login";
	}
	
	//�α���
	@RequestMapping(value="login.mem", method=RequestMethod.POST)
	public String login(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean loginInfo=memberDao.selectMemberById(memberBean.getMember_id());
		
		if(loginInfo==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('���̵� Ȯ�����ּ���.');");
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
				writer.println("alert('��й�ȣ�� Ȯ�����ּ���.');");
				writer.println("</script>");
				writer.flush();
				return "/login";
			}   
		}
	}
	
	//�α׾ƿ�
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
	
	//���̵�ã��������
	@RequestMapping("findid.mem")
	public String findid() {
		return "/findid";
	}
	
	//���̵�ã��
	@RequestMapping(value="findid.mem", method=RequestMethod.POST)
	public String findid(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberBean findid=memberDao.selectIdByNameEmail(memberBean);
		
		if(findid!=null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('"+memberBean.getMember_name()+"���� ���̵�� "+findid.getMember_id()+" �Դϴ�.');");
			writer.println("</script>");
			writer.flush();
			return "/login";
		}
		else if(findid==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('�Է������� Ȯ�����ּ���.');");
			writer.println("</script>");
			writer.flush();
			return "/findid";		
		}
		return null;
	}

	//��й�ȣã��������
	@RequestMapping("findpw.mem")
	public String findpw() {
		return "/findpw";
	}	
	
	//��й�ȣã�⿡�� �ӽù߱�
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
			out.print("<script>alert('���̵� Ȯ�����ּ���.'); history.back(-1);</script>");
			out.close();
		}
		
		else if(!member.getMember_email().equals(mb.getMember_email())) {
			out.print("<script>alert('�̸����� Ȯ�����ּ���.'); history.back(-1);</script>");
			out.close();
			
		}
		else {
			//��й�ȣ �������
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			member.setMember_pw(pw);
			// ��й�ȣ ����
			memberDao.updatePw(member);
			
			// ��й�ȣ ���� ���� �߼�
			sendEmail(member, "findpw");
			
			out.print("<script>alert('�̸��Ϸ� �ӽ� ��й�ȣ�� �߼��Ͽ����ϴ�.'); location.href='login.mem'</script>");
			out.close();
			
		}
		
		return "/login";
	}

	//��й�ȣã�⿡�� �ӽú�й�ȣ �̸��� �߼�
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
			subject = "Nyom ȸ���� �ӽ� ��й�ȣ �߼�";
			msg += "<div align='center' font-family:verdana'>";
			msg += "<h2><p> �ȳ��ϼ���. <br>";
			msg += member.getMember_id() + "���� �ӽ� ��й�ȣ �Դϴ�.</h2>";
			msg += "<p> �ӽ� ��й�ȣ : ";
			msg += member.getMember_pw() + "</p>";
			msg += "<p>���ӽ� ��й�ȣ�� ���� �� �����Ͽ� �����ϰ� ������ �����ϼ���.</p></div>";
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
			System.out.println("���Ϲ߼� ���� : " + e);
		}
		
	}
	
	//����������
	@RequestMapping("mypage.mem")
	public String mypage() {
		return "/mypage";
	}
	
	//���������� ��й�ȣ �Է�
	@RequestMapping(value="mypage.mem", method=RequestMethod.POST)
	public ModelAndView mypage(MemberBean memberBean,
			HttpServletResponse response,
			HttpSession session) throws IOException{

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		ModelAndView mav = new ModelAndView();
		MemberBean mypage=memberDao.selectMemberByIdPw(memberBean);
		
		//��й�ȣ ��ġ�ϸ� ������������������
		if(mypage!=null) {
			mav.addObject("mypage", mypage);
			mav.setViewName("/updatemem");
			return mav;
		}
		else if(mypage==null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('��й�ȣ�� Ȯ�����ּ���.');");
			writer.println("</script>");
			writer.flush();
			
			mav.setViewName("/mypage");
			return mav;		
		}
		return null;
	}
	
	//����������
	@RequestMapping(value="updatemem.mem", method=RequestMethod.POST)
	public ModelAndView updatemem(@ModelAttribute("memberBean") @Valid MemberBean memberBean, BindingResult result) {
		
		ModelAndView mav=new ModelAndView();

		memberBean=memberDao.selectMemberByIdPw(memberBean);
		
		if(result.hasErrors()) {
			mav.addObject("memberBean", memberBean);
			mav.setViewName("/updatemem");
			return mav;
		}
		memberDao.updateMemberById(memberBean);
		mav.setViewName("/mypage");
		return mav;
	}
	
	//ȸ������������
	@RequestMapping("join.mem")
	public String joinPage() {
		return "/join";
	}
	
	//ȸ������
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
	
	// ���̵� �ߺ�üũ
	@RequestMapping("/checkId.mem")
	public @ResponseBody int checkId(String id) {
		int result = memberDao.checkId(id);
		return result;
	}	
	
	//ȸ��Ż��
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
			writer.println("alert('Ż�� �Ϸ�Ǿ����ϴ�.');");
			writer.println("location.href='main.fs'");
			writer.println("</script>");
			writer.flush();
		}
		return null;
	}
}
