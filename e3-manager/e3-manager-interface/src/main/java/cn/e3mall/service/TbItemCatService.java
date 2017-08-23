package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.TreeNodes;

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

}
