package noticeboard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import noticeboard.model.NoticeboardBean;
import noticeboard.model.NoticeboardDAO;
import utility.Paging;

@Controller
public class NoticeboardController {

	@Autowired
	NoticeboardDAO noticeboardDAO;
	
	@Autowired
	ServletContext servletContext;
	
	//공지사항 목록
	@RequestMapping("list.not")
	public String listNotice(HttpServletRequest request, 
							@RequestParam(value="whatColumn",required = false) String whatColumn,
							@RequestParam(value="keyword",required = false) String keyword,
							@RequestParam(value="pageNumber", required=false) String pageNumber,
							Model model) {

		System.out.println("NoticeListController : "+whatColumn+", "+keyword+", "+pageNumber);

		Map<String,String> map = new HashMap<String,String>(); 
		map.put("whatColumn", whatColumn); 
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = noticeboardDAO.getNoticeTotalCount(map);
		
		String url = request.getContextPath() + "/list.not";
		Paging pageInfo = new Paging(pageNumber,"5",totalCount,url,whatColumn,keyword,null);
		
		List<NoticeboardBean> lists = new ArrayList<NoticeboardBean>();
		lists = noticeboardDAO.getAllNotice(pageInfo, map);
		
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
	public String insertNotice(NoticeboardBean notice
							//@RequestParam("reg_date") @DateTimeFormat(pattern="yyyy-MM-dd") Date reg_date
							) {
		
		System.out.println("notice.getWriter();"+notice.getWriter());
		System.out.println("notice.getSubject();"+notice.getSubject());
		System.out.println("notice.getImage();"+notice.getImage()); //null값임.이름을 upload로 넘김.
		System.out.println("notice.getOpen();"+notice.getOpen());
		System.out.println("notice.getReadcount();"+notice.getReadcount());
		System.out.println("notice.getReg_date();"+notice.getReg_date());
		System.out.println("notice.getContent();"+notice.getContent());
	
		System.out.println("notice.getUpload()"+notice.getUpload());
		//jsp에서 이미지name을 upload로 했으니까 여기로 넘어옴
		MultipartFile multi = notice.getUpload();
		System.out.println("multi.getName()"+multi.getName());
		System.out.println("multi.getOriginalFilename()"+multi.getOriginalFilename());
		System.out.println("notice.getImage()"+notice.getImage());
		
		//notice.getReg_date();11/15/2022 5:38 PM
		notice.setReg_date(new Timestamp(System.currentTimeMillis()));
		System.out.println("notice.getReg_date();"+notice.getReg_date());	
		
		noticeboardDAO.insertNotice(notice);
		
		String uploadPath = servletContext.getRealPath("resources");
		System.out.println("uplaodPath:"+uploadPath);
		
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
		try {
			multi.transferTo(file); //이미지 파일없으면 걍 에러뜨고 지나가게~
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/list.not";
	}
	//상세보기
	@RequestMapping("detail.not")
	public String detail(@RequestParam("pageNumber") String pageNumber,
							@RequestParam("no") int no,
							Model model) {

		noticeboardDAO.readCountNotice(no);
		NoticeboardBean notice = noticeboardDAO.getNoticeDetail(no);
		
		if(notice.getContent() != null) {
			notice.setContent(notice.getContent().replace("\r\n", "<br>"));

			model.addAttribute("notice", notice);
			model.addAttribute("pageNumber", pageNumber);
		}else {
		model.addAttribute("notice", notice);
		model.addAttribute("pageNumber", pageNumber);
		}
		
		return "/detailNotice";
	}
	
	//공지사항 수정폼으로
	@RequestMapping(value="update.not", method = RequestMethod.GET)
	public String updateNotice(@RequestParam("no") int no,
								@RequestParam(value="pageNumber", required=false) String pageNumber,
								Model model) {
		NoticeboardBean notice = noticeboardDAO.getNoticeDetail(no);
		
		model.addAttribute("notice", notice);
		model.addAttribute("pageNumber", pageNumber);
		
		return "/updateNotice";
	}
	//공지사항 수정
	@RequestMapping(value="update.not", method = RequestMethod.POST)
	public String updateNotice(NoticeboardBean notice,
								@RequestParam("no") int no,
								@RequestParam("originalImg") String originalImg,
								@RequestParam(value="pageNumber", required = false
								) String pageNumber) {
				
		System.out.println("notice.getImage()"+notice.getImage());
		System.out.println("originalImg:"+originalImg);
		//원 이미지 삭제
		String originalpath = servletContext.getRealPath("/resources/")+originalImg;
		File file1 = new File(originalpath);
		file1.delete();
		//새 이미지 등록
		MultipartFile multi = notice.getUpload();
		String newPath = servletContext.getRealPath("/resources/")+notice.getImage();
		File file2 = new File(newPath);
		try {
			multi.transferTo(file2); //파일 업로드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		noticeboardDAO.updateNotice(notice);
		
		return "redirect:/detail.not?pageNumber="+pageNumber+"&no="+no;
	}
	//공지사항 삭제
	@RequestMapping("delete.not")
	public String deleteNotice(@RequestParam("no") int no,
								@RequestParam(value="pageNumber", required = false) String pageNumber) {
		
		NoticeboardBean notice = noticeboardDAO.getNoticeDetail(no);
		
		String deletePath = servletContext.getRealPath("/resources/");
		
		File delFile = new File(deletePath+notice.getImage());
		delFile.delete();
		
		noticeboardDAO.deleteNotice(no);
		
		return "redirect:/list.not?pageNumber="+pageNumber;
	}
	
}
