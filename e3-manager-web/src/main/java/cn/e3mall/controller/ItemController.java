package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.po.TbItem;
import cn.e3mall.service.ItemService;

/**
 *
 * @author Dxm
 *
 */
@Controller
//@RequestMapping("item")
public class ItemController {
	
	@Autowired(required=false)
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem geTbItemById(@PathVariable Long itemId) {
		
		TbItem tbItem = itemService.geTbItemById(itemId);
		
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public DatagridResult<TbItem> getItemList(Integer page ,Integer rows){
		
		DatagridResult<TbItem> result = itemService.geTbItemList(page,rows);
		
		return result;
	}
}
