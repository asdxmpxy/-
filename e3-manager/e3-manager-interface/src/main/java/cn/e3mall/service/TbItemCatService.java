package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.pojo.TbItemCat;

/**
 *
 * @author Dxm
 *
 */
public interface TbItemCatService {

	/**
	 * 查询商品类目的业务层方法
	 * @author Dxm
	 * @param parentId
	 * @return
	 */
	public List<TreeNodes> getTreeNodes(Long parentId);

	/**
	 * 根据id查询商品类目的方法
	 * @author Dxm
	 * @param catId
	 * @return
	 */
	public E3Result geTbItemCatById(Long catId);

}
