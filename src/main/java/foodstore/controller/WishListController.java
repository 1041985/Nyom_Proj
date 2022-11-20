package foodstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;
import foodstore.model.WishBean;
import member.model.MemberBean;

@Controller
public class WishListController {

	@Autowired
	FoodstoreDAO foodstoreDAO;
	
	//위시리스트
	@RequestMapping("wishList.fs")
	public String wishlist(HttpSession session,
						  Model model,
						  HttpServletRequest request) {
			
			MemberBean mb = (MemberBean)session.getAttribute("loginInfo");
			ArrayList<WishBean> wishList = (ArrayList<WishBean>) foodstoreDAO.selectWishByMember_no(mb.getMember_no());
			ArrayList<FoodstoreBean> storeList = new ArrayList<FoodstoreBean>();
			for(WishBean wish : wishList) {
				FoodstoreBean store = foodstoreDAO.getStoreByStore_no(wish.getStore_no());
				storeList.add(store);
			}

			model.addAttribute("storeList", storeList);
			String fullpath = request.getContextPath()+"/resources/fs_images/";  
			model.addAttribute("fullpath", fullpath);
			System.out.println("wishList의 fullpath:"+fullpath);
		
		return "/wishList";
	}
	
	//하트 삭제 "deleteHeart.wi"
	@RequestMapping("deleteHeart.fs")
	public void insert(@RequestParam("storeNo") int no,
						HttpSession session,
						HttpServletResponse response) throws IOException {
		
		System.out.println("WishDeleteController");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		MemberBean mb = (MemberBean) session.getAttribute("loginInfo");
		WishBean wish = new WishBean();
		wish.setMember_no(mb.getMember_no());
		wish.setStore_no(no);
		
		writer.println("<script> alert('선택하신 위시리스트가 삭제 되었습니다.')</script>");
		writer.flush();
		
		foodstoreDAO.deleteWish(wish);
		System.out.println("deleteWish");
		
	}

	
	
	
}
