package foodstore.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;

@Controller
public class DeleteFoodStoreController {

	private final String command="adminDelete.fs";
	private final String getPage="redirect:/admin.fs";
	
	@Autowired
	private FoodstoreDAO fdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(command)
	public String delete(@RequestParam("store_no") String store_no,  Model model) {
		
		FoodstoreBean fsbean = fdao.selectFoodstore(store_no);
		
		String deletePath = servletContext.getRealPath("/resources/fs_images");
		System.out.println("deletePath: "+deletePath);
		
		File delFile = new File(deletePath+"/"+fsbean.getStore_img());
		delFile.delete();
		
		int cnt = fdao.deleteFoodstore(store_no);
		System.out.println("삭제됨");
		
		return getPage;	
	}
}
