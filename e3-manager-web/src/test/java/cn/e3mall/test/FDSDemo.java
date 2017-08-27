package cn.e3mall.test;

import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

/**
 *
 * @author Dxm
 *
 */
public class FDSDemo {
		
	
	@Test
	public void FDSClientTest() throws Exception {
		
		FastDFSClient fastDFSClient = new FastDFSClient("C:\\eclipse-workspace\\e3-manager-web\\src\\main\\resources\\config\\client.conf");
		
		String uploadPath = fastDFSClient.uploadFile("G:\\宜立方\\01.参考资料\\广告图片\\1946ceef1ea90c932e1f7c8bb631a3fa.jpg");
		
		System.out.println(uploadPath);
	}
}
