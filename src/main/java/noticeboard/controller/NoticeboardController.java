package noticeboard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import noticeboard.model.NoticeboardBean;
import noticeboard.model.NoticeboardDAO;
import utility.Paging;

@Controller
public class NoticeboardController {

	@Autowired
	NoticeboardDAO noticeboardDAO;
	
	//공지사항 목록
	@RequestMapping("list.not")
	public String listNotice(HttpServletRequest request, 
							@RequestParam(value="pageNumber", required=false) String pageNumber,
							Model model ) {
		
		int totalCount = noticeboardDAO.getNoticeTotalCount();
		
		String url = request.getContextPath() + "/list.not";
		Paging pageInfo = new Paging(pageNumber,"5",totalCount,url,null,null,null);
		
		List<NoticeboardBean> lists = new ArrayList<NoticeboardBean>();
		lists = noticeboardDAO.getAllNotice();
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("lists", lists);
		return "/listNotice";
	}
	//공지사항 등록폼으로
	@RequestMapping(value="insert.not", method=RequestMethod.GET)
	public String insertNotice() {
		return "/insertNotice";
	}
	//공지사항 등록
	@RequestMapping(value="insert.not", method=RequestMethod.POST)
	public String insertsNotice(@ModelAttribute("notice") @Valid NoticeboardBean notice) {
		
		System.out.println("notice.getWriter();"+notice.getWriter());
		System.out.println("notice.getSubject();"+notice.getSubject());
		System.out.println("notice.getImage();"+notice.getImage());
		System.out.println("notice.getOpen();"+notice.getOpen());
		System.out.println("notice.getReadcount();"+notice.getReadcount());
		System.out.println("notice.getReg_date();"+notice.getReg_date());
		System.out.println("notice.getContent();"+notice.getContent());
		
		noticeboardDAO.insertNotice(notice);
		
		return "redirect:/list.not";
	}
	//업데이트 수정폼으로
	@RequestMapping("update.not")
	public String updateNotice() {
		return "/updateNotice";
	}
	
}
