package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
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
	
	/**
	 * 根据id查询商品的方法
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	@RequestMapping("item/{itemId}")
	@ResponseBody
	public TbItem geTbItemById(@PathVariable Long itemId) {
		
		TbItem tbItem = itemService.geTbItemById(itemId);
		
		return tbItem;
	}
	
	/**
	 * 分页查询商品列表的方法
	 * @author Dxm
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public DatagridResult<TbItem> getItemList(Integer page ,Integer rows){
		
		DatagridResult<TbItem> result = itemService.geTbItemList(page,rows);
		
		return result;
	}
	
	/**
	 * 添加商品的方法
	 * @author Dxm
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result saveItem(TbItem tbItem , String desc) {
		
		E3Result e3Result = itemService.saveTbItem(tbItem, desc);
		
		return e3Result;
		
	}

	/**
	 * 根据商品ID查询商品描述
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public E3Result geTbItemDescById(@PathVariable Long itemId) {
		
		E3Result result = itemService.geTbItemDescById(itemId);
		
		return result;
	}
	
	/**
	 * 根据商品ID查询商品规格
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public E3Result geTbItemParamItemById(@PathVariable Long itemId) {
		
		E3Result result = itemService.geTbItemParamItemById(itemId);
		
		return result;
	}
	
	/**
	 * 修改商品信息的方法
	 * @author Dxm
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping(value ="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateItem(TbItem tbItem , String desc) {
		
		E3Result result = itemService.updateItem(tbItem,desc);
		
		return result;
	}
	
	
	/**
	 * 商品下架
	 * @author Dxm
	 * @param ids
	 * @return
	 */
	@RequestMapping(value ="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public E3Result instock(String ids) {
		
		E3Result result = itemService.instockItems(ids);
		
		return result;
	}
	/**
	 * 商品上架
	 * @author Dxm
	 * @param ids
	 * @return
	 */
	@RequestMapping(value ="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public E3Result reshelf(String ids) {
		
		E3Result result = itemService.reshelfItems(ids);
		
		return result;
	}
}
