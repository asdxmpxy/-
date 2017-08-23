package cn.e3mall.controller;
/**
 *
 * @author Dxm
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	/**
	 * 跳转页面的方法
	 * @author Dxm
	 * @return
	 */
	@RequestMapping("/{page}")
	public String index(@PathVariable String page) {
		
		return page;
	}
}
