package cn.e3mall.service;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;

/**
 *
 * @author Dxm
 *
 */


public interface ItemService {
	
	
	/**
	 * 添加商品的方法
	 * @author Dxm
	 * @param tbItem
	 * @return
	 */
	public E3Result saveTbItem(TbItem tbItem,String desc);
	
	/**
	 * 根据ID查询商品
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	public TbItem geTbItemById(Long itemId);

	/**
	 * 返回符合EasyUIDatagrid的数据格式的方法
	 * @author Dxm
	 * @param page
	 * @param rows
	 * @return
	 */
	public DatagridResult<TbItem> geTbItemList(Integer page, Integer rows);

	/**
	 * 根据商品id查询商品描述 的方法
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	public E3Result geTbItemDescById(Long itemId);

	/**
	 * 根据商品id查询商品规格
	 * @author Dxm
	 * @param itemId
	 * @return
	 */
	public E3Result geTbItemParamItemById(Long itemId);

	/**
	 * 修改商品的方法
	 * @author Dxm
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	public E3Result updateItem(TbItem tbItem, String desc);

	/**
	 * 商品下架
	 * @author Dxm
	 * @param ids
	 * @return
	 */
	public E3Result instockItems(String ids);

	/**
	 * 商品上架
	 * @author Dxm
	 * @param ids
	 * @return
	 */
	public E3Result reshelfItems(String ids);
}
