package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.pojo.TbContentCategory;

/**
 *
 * @author Dxm
 *
 */
public interface ContentCatService {

	/**
	 * 查询内容列表的方法
	 * @author Dxm
	 * @param parentId
	 * @return
	 */
	public List<TreeNodes> getContentCatList(Long parentId);

	/**
	 * 新增内容分类的方法
	 * @author Dxm
	 * @param parentId
	 * @param name
	 * @return
	 */
	public E3Result createContentCat(Long parentId, String name);

	/**
	 * 重命名内容分类的方法
	 * @author Dxm
	 * @param id
	 * @param name
	 * @return
	 */
	public E3Result updateContentCat(TbContentCategory category);

	/**
	 * 删除节点的方法
	 * @author Dxm
	 * @param id
	 * @return
	 */
	public E3Result deleteContentCat(Long id);

}
