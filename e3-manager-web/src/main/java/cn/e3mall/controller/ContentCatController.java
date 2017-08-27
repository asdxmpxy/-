package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.pojo.TbContentCategory;

/**
 *
 * @author Dxm
 *
 */
@Controller
public class ContentCatController {
	
	@Autowired(required=false)
	private ContentCatService contentCatService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeNodes> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		
		List<TreeNodes> list = contentCatService.getContentCatList(parentId);
		
		return list;
	}
	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3Result createContentCat(Long parentId,String name){
		
		E3Result result = contentCatService.createContentCat(parentId,name);
		
		return result;
	}
	
	/**
	 * 更新分类的方法
	 * @author Dxm
	 * @param category
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public E3Result updateContentCat(TbContentCategory category){
		
		E3Result result = contentCatService.updateContentCat(category);
		
		return result;
	}
	
	/**
	 * 删除节点的方法
	 * @author Dxm
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public E3Result deleteContentCat(Long id){
		
		E3Result result = contentCatService.deleteContentCat(id);
		
		return result;
	}
}
