package foodstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InsertFoodStoreController {

	//업체등록
	@RequestMapping("adminInsert.fs")
	public String admin() {
		return "/adminInsertForm";
		
	}
}
