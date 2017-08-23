package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.po.TbItem;
import cn.e3mall.po.TbItemExample;
import cn.e3mall.service.ItemService;

/**
 *
 * @author Dxm
 *
 */
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired(required=false)
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem geTbItemById(Long itemId) {
		
		return tbItemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public DatagridResult<TbItem> geTbItemList(Integer page, Integer rows) {
		
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		DatagridResult<TbItem> result = new DatagridResult<>();
		
		result.setRows(list);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		result.setTotal((int) pageInfo.getTotal());
		
		return result;
	}

}
