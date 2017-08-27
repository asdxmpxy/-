package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.service.TbItemCatService;

/**
 *
 * @author Dxm
 *
 */
@Controller
public class TbItemCatController {
	
	@Autowired(required=false)
	private TbItemCatService tbItemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<TreeNodes>  geTreeNodes(@RequestParam(name="id",defaultValue="0") Long parentId){
		
		List<TreeNodes> list = tbItemCatService.getTreeNodes(parentId);
		
		return list;
	}
	
	@RequestMapping("/item/param/query/itemcatid/{catId}")
	@ResponseBody
	public E3Result geTbItemCatById(@PathVariable Long catId) {
		
		E3Result result = tbItemCatService.geTbItemCatById(catId);
		
		return result;
	}
	

}
