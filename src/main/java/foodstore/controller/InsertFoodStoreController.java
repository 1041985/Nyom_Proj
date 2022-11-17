package foodstore.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import foodstore.model.FoodstoreBean;
import foodstore.model.FoodstoreDAO;

@Controller
public class InsertFoodStoreController {

    private final String command ="adminInsert.fs";
    private String getPage="/adminInsertForm";
    private String gotoPage="redirect:/admin.fs";
    
	@Autowired
	private FoodstoreDAO fdao;

	@Autowired
	ServletContext servletContext;

	//업체등록
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String insert() {
		
		return "/adminInsertForm";
		
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView insert1(@ModelAttribute("foodstore") @Valid FoodstoreBean foodstore,
			BindingResult result,HttpServletRequest request) {
		
		
		ModelAndView mav = new ModelAndView();
		
		//이미지 파일 업로드 할 위치 설정
        String uploadPath = servletContext.getRealPath("/resources/fs_images");
        System.out.println("uploadPath:"+uploadPath);
        
		MultipartFile multi = foodstore.getUpload();
		System.out.println(multi.getOriginalFilename());
        
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;

	      }
		
		
		int cnt = fdao.insertfoodstore(foodstore);	
		System.out.println("insertController cnt:"+cnt);
		
         if(cnt>0) {
            
            File file = new File(uploadPath+"/"+multi.getOriginalFilename());//업로드위치에 이미파일 올림   

		try {
			//파일 업로드
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        mav.setViewName(gotoPage);
        
        }//if
         
         else {
             
             mav.setViewName(getPage);
          }

	      
	      return mav;

	}
}
