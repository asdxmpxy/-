package cn.e3mall.service;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.po.TbItem;

/**
 *
 * @author Dxm
 *
 */


public interface ItemService {
	
	public TbItem geTbItemById(Long itemId);

	public DatagridResult<TbItem> geTbItemList(Integer page, Integer rows);
}
