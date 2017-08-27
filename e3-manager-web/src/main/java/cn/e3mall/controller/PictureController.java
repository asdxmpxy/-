package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

/**
 *
 * @author Dxm
 *
 */
@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER}")
	private String IMAGE_SERVER;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile){
		
		try {
			//创建一个fastDFS客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/client.conf");
			//拿到文件的原始名字
			String originalFilename = uploadFile.getOriginalFilename();
			//拿到文件的后缀名
			String extName = originalFilename.substring(originalFilename.lastIndexOf("\\.")+1);
			//获取文件上传后的路径
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//拼接成为返回的要求的格式
			url = IMAGE_SERVER + url;
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("error", 0);
			map.put("url", url);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("error", 1);
			map.put("message", "图片上传错误");
			return JsonUtils.objectToJson(map);
		}
		
	}
}
