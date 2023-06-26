package com.blog.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
			
			// file the file to folder and update the name to contact
			String name =file.getOriginalFilename();

			//File saveFile = new ClassPathResource("static/img").getFile();
             String filepath = path+ File.separator +name;
             File f=new File(path);
             
		//	Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
            if(!f.exists()) {
            f.mkdir();
            }
			Files.copy(file.getInputStream(), Paths.get(filepath));

			System.out.println("Image is uploaded");

	   return name;	
	}
		
	

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
