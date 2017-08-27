package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodes;
import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;

/**
 *
 * @author Dxm
 *
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
	
	@Autowired(required=false)
	private TbContentCategoryMapper contentCategoryMapper;
	@Autowired(required=false)
	private TbContentMapper contentMapper;
	@Override
	public List<TreeNodes> getContentCatList(Long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		
		example.createCriteria().andParentIdEqualTo(parentId);
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		List<TreeNodes> treeNodes = new ArrayList<>();
		
		for (TbContentCategory tbContentCategory : list) {
			
			TreeNodes nodes = new TreeNodes();
			
			nodes.setId(tbContentCategory.getId());
			nodes.setText(tbContentCategory.getName());
			nodes.setState(tbContentCategory.getIsParent()?"closed":"open");
			treeNodes.add(nodes);
		}
		
		return treeNodes;
	}

	@Override
	public E3Result createContentCat(Long parentId, String name) {
		
		TbContentCategory category = new TbContentCategory();
		
		//补全分类中国的属性
		category.setParentId(parentId);
		category.setName(name);
		category.setIsParent(false);
		
		Date date = new Date();
		
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		category.setSortOrder(1);
		category.setCreated(date);
		category.setUpdated(date);
		category.setStatus(1);
		//将数据插入表中
		contentCategoryMapper.insertSelective(category);
		//根据父节点查询分类
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为父节点,如果不是,需要改为父节点
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return E3Result.ok(category);
	}

	@Override
	public E3Result updateContentCat(TbContentCategory category) {
		
		contentCategoryMapper.updateByPrimaryKeySelective(category);
		
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContentCat(Long id) {
		
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		TbContentCategoryExample example = new TbContentCategoryExample();
		//判断该节点是否为父节点
		if (!category.getIsParent()) {
			//不是父节点,直接删除
			contentCategoryMapper.deleteByPrimaryKey(id);
		}else {
			//是父节点,判断是否为顶级或一级节点
			if (category.getParentId() == 0 || category.getParentId()  == 30) {
				//是顶级或一级节点,不允许删除
				return E3Result.build(203, "该节点不允许删除!!");
			}else {
				//不是顶级或一级节点,递归删除
				example.createCriteria().andParentIdEqualTo(id);
				List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
				for (TbContentCategory tbContentCategory : list) {
					contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
				}
				contentCategoryMapper.deleteByPrimaryKey(id);
			}
		}
		//删除后判断是还有同级目录
		Long parentId = category.getParentId();
		
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbContentCategory> broList = contentCategoryMapper.selectByExample(example);
		if (null == broList || broList.size()==0) {
			//没有,证明原来的父节点已经不再是父节点,需要修改
			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
			parentCat.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return E3Result.ok();
	}

	
	
}
