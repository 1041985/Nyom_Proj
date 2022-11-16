package foodstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InsertFoodStoreController {

	@RequestMapping("adminInsert.fs")
	public String admin() {
		return "/adminInsertForm";
		
	}
}
