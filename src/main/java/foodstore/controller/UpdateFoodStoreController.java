package foodstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;

@Controller
public class UpdateFoodStoreController {

	private final String command="adminUpdate.fs";
	private final String getPage="/adminUpdateForm";
	private String gotoPage="redirect:/admin.fs"; //List요청하는 요청명
	
	@Autowired
	private FoodstoreDAO fdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String updateForm(@RequestParam("store_no") String store_no, Model model,
								HttpServletRequest request
							) {
		
		FoodstoreBean fsbean = fdao.selectFoodstore(store_no); //수정폼을 띄우기위해서 레코드 하나 가져오기
		
		String fullpath = request.getContextPath()+"/resources/fs_images";

		model.addAttribute("fullpath",fullpath);
		model.addAttribute("foodstore",fsbean);
		
		return getPage;
		
	}
	
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String update(@ModelAttribute("foodstore") @Valid FoodstoreBean foodstore, BindingResult result,
						@RequestParam("originalImg") String originalImg, Model model
						) {
		
		
		
		String originalPath = servletContext.getRealPath("/resources/fs_images")+"/"+originalImg;
		File file1 = new File(originalPath);
		file1.delete();
		
		MultipartFile multi = foodstore.getUpload();
		String newPath = servletContext.getRealPath("/resources/fs_images")+"/"+multi.getOriginalFilename();
		
		foodstore.setStore_addr(foodstore.getAddr_num()+"|"+foodstore.getAddr_first()+"|"+foodstore.getAddr_last());
		
		int cnt = fdao.updateFoodstore(foodstore);
		System.out.println("updateFoodstoreController:"+cnt);
		
		if(cnt>0) {
			
			File file2 = new File(newPath);
			System.out.println("cnt>0 들어옴");
			try {
				multi.transferTo(file2);
			} catch (IllegalStateException e) {
				System.out.println("FoodstoreUpdate 에러1");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("FoodstoreUpdate 에러2");
				e.printStackTrace();
			}
			System.out.println("FoodstoreUpdate 성공");
			return gotoPage;//목록으로 이동	
		}
		else {
			return getPage;
		}
		
//대용량 업로드		
//		MultipartFile upimage = foodstore.getUpload();
//		
//		UUID uuid = UUID.randomUUID();
//		String imageName = uuid +"-" + upimage.getOriginalFilename();
//		
//		foodstore.setStore_img(imageName);
//		
	
	}
	
	
}
