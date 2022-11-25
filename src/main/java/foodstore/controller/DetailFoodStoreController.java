package foodstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;

@Controller
public class DetailFoodStoreController {
	
	private final String command="adminDetail.fs";
	private final String getPage="/adminDetailView";
	

	@Autowired
	private FoodstoreDAO fdao;

	@RequestMapping(command)
	public String Detail(@RequestParam("store_no") String store_no, Model model, HttpServletRequest request) {
		
		System.out.println("DetailFoodStoreController");
		
		FoodstoreBean fsbean = fdao.selectFoodstore(store_no);
		
		String fullpath = request.getContextPath()+"/resources/fs_images";

		model.addAttribute("fullpath",fullpath);
		model.addAttribute("foodstore", fsbean);
		
		return getPage;	
	}

}
