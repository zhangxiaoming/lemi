package com.sample.libs.image.core;

import com.sample.libs.image.cache.disc.naming.Md5FileNameGenerator;

public class HttpImage {
	public static final String JPEG =".jpeg";
	public static final String PNG =".png";
	
	public String url;
	public int width;
	public int height;
	public String filePath;
	public String type;
	
	public HttpImage (String url,int w,int h,String path){
		this.url =url;
		this.height =h;
		this.width =w;
		this.filePath =path;
	}
	
	public String getFileName(){
		if(filePath != null){
			return filePath;
		}		
		Md5FileNameGenerator md5 = new Md5FileNameGenerator();
		return md5.generate(url);
	}
	
}
