package org.virgil.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> upload(
			@RequestParam("uploadfile") MultipartFile uploadfile)
			throws Exception {
		String filename = uploadfile.getOriginalFilename();
		System.out.println(filename);
		String directory = env.getProperty("org.virgil.uploadFiles");
		System.out.println(directory);
		String filepath = Paths.get(directory, filename).toString();
		System.out.println(filepath);
		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(new File(
					filepath)));
			out.write(uploadfile.getBytes());
			out.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
