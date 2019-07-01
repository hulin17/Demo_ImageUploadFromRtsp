package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Controller
@EnableAutoConfiguration
@ImportResource("classpath:beans.xml")

public class DemoApplication {
	@Autowired
	private RequestConfig requestConfig;

	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "hello world";
	}

	@RequestMapping(value = "/getImage", method = RequestMethod.POST)
	@ResponseBody
	public String home(@RequestBody String image) throws IOException {
		byte[] bi = JSONObject.parseObject(image,new TypeReference<byte[]>() {});
		ByteArrayInputStream in = new ByteArrayInputStream(bi);
		BufferedImage imageOutput = ImageIO.read(in);
		dumpImageToFileSelfDefFileName(imageOutput,
				"/Users/baidu/Pictures" + "/" +
						String.valueOf(System.currentTimeMillis()) + "." + ImageType.JPG.getName(),
				ImageType.JPG.getName());
		System.out.println("收到一张图片！");
		return "store";
	}

	public static String dumpImageToFileSelfDefFileName(BufferedImage image,
														String fileFullName,
														String outputFileSuffix) {
		try {
			String outputFilename = fileFullName;
			File file = new File(outputFilename);
			if (file.exists() && !file.isDirectory()) {
				file.delete();
			}
			ImageIO.write(image, outputFileSuffix, new File(outputFilename));
			return outputFilename;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
