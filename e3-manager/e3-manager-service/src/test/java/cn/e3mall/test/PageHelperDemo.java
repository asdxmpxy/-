package cn.e3mall.test;
/**
 *
 * @author Dxm
 *
 */

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.po.TbItem;
import cn.e3mall.po.TbItemExample;

public class PageHelperDemo {
	
	@Test
	public void pageHelperTest() {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
		
		TbItemMapper mapper = ac.getBean(TbItemMapper.class);
		
		PageHelper.startPage(1, 10);
		
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = mapper.selectByExample(example);
		
		System.out.println(list.size());
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		System.out.println(list.size());
	}
}	
