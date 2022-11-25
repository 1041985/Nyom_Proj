package foodstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;

@Controller
public class ListFoodStoreController {

	@Autowired
	private FoodstoreDAO fdao;

	//업체 리스트
	@RequestMapping("admin.fs")
	public String list(Model model,HttpServletRequest request) {

		List<FoodstoreBean> lists = fdao.getAllFoodStore();

		String fullpath = request.getContextPath()+"/resources/fs_images";

		model.addAttribute("lists",lists);
		model.addAttribute("fullpath",fullpath);

		return "/adminList";

	}
}
