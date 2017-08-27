package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DatagridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.mapper.TbItemParamItemMapper;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemDescExample;
import cn.e3mall.pojo.TbItemDescExample.Criteria;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamItem;
import cn.e3mall.pojo.TbItemParamItemExample;
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
	@Autowired(required=false)
	private TbItemDescMapper itemDescMapper;
	@Autowired(required=false)	
	private TbItemParamItemMapper itemParamItemMapper;
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

	@Override
	public E3Result saveTbItem(TbItem tbItem,String desc) {
		
		//补全商品信息
		//补全ID
		long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		//补全商品状态
		tbItem.setStatus((byte) 1);
		//添加时间和更新时间
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		
		tbItemMapper.insertSelective(tbItem);
		//添加商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		
		itemDesc.setItemId(itemId);
		
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		
		itemDescMapper.insert(itemDesc);
		
		return E3Result.ok();
	}

	@Override
	public E3Result geTbItemDescById(Long itemId) {
		
		TbItemDesc desc = itemDescMapper.selectByPrimaryKey(itemId);
		
		return E3Result.ok(desc);
	}

	@Override
	public E3Result geTbItemParamItemById(Long itemId) {
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		
		example.createCriteria().andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> list = itemParamItemMapper.selectByExample(example);
		
		if (null != list && list.size()>0) {
			
			return E3Result.ok(list.get(0));
		}
		
		return null;
	}

	@Override
	public E3Result updateItem(TbItem tbItem, String desc) {
		
		Date date = new Date();
		
		tbItem.setUpdated(date);
		
		tbItemMapper.updateByPrimaryKeySelective(tbItem);
		
		TbItemDesc itemDesc = new TbItemDesc();
		
		itemDesc.setItemId(tbItem.getId());
		
		itemDesc.setUpdated(date);
		
		itemDesc.setItemDesc(desc);
		
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		return E3Result.ok();
	}

	@Override
	public E3Result instockItems(String ids) {
		
		if (StringUtils.isNotBlank(ids)) {
			
			String[] itemIds = ids.split(",");
			
			TbItem record = new TbItem();
			record.setStatus((byte) 2);
			for (String itemId : itemIds) {
				record.setId(Long.valueOf(itemId));
				tbItemMapper.updateByPrimaryKeySelective(record);
			}
		}
		return E3Result.ok();
	}

	@Override
	public E3Result reshelfItems(String ids) {
		
		if (StringUtils.isNotBlank(ids)) {

			String[] itemIds = ids.split(",");

			TbItem record = new TbItem();
			record.setStatus((byte) 1);
			for (String itemId : itemIds) {
				record.setId(Long.valueOf(itemId));
				int i = tbItemMapper.updateByPrimaryKeySelective(record);
				System.out.println(i);
			}
		}
		return E3Result.ok();
	}

}
