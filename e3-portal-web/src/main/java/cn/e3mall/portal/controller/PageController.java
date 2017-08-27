package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dxm
 *
 */
@Controller
public class PageController {
	
	@RequestMapping("/index")
	public String toIndex() {
		
		return "index";
	}
}
