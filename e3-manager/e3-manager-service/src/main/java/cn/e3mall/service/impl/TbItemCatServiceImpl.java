package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.po.TbItemCat;
import cn.e3mall.po.TbItemCatExample;
import cn.e3mall.service.TbItemCatService;

/**
 *
 * @author Dxm
 *
 */
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	
	@Autowired(required=false)
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<TreeNodes> getTreeNodes(Long parentId) {
		
		//根据parentId查询对应的商品类目
		TbItemCatExample example = new TbItemCatExample();
		
		example.createCriteria().andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		List<TreeNodes> treeNodesList = new ArrayList<>();
		//将查询出的商品类目分装成为符合目标对象的样子
		
		for (TbItemCat tbItemCat : list) {
			
			TreeNodes node = new TreeNodes();
			
			node.setId(tbItemCat.getId());
			
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			
			treeNodesList.add(node);
		}
		return treeNodesList;
	}
}
