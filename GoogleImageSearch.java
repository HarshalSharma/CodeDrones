package com.ncr.cia.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**\
 * 
 * Simple java file to search google for images and collect data.
 * @author Harshal Sharma
 *
 */
public class GoogleImageSearch{
	
	/**
	 * 
	 * @param query Google search query for the images 
	 * @param count Number of images required
	 * @param height height of images
	 * @param width  widht of images
	 * @return List of BufferedImages in google search result. Images may be less than the count value. 
	 * @throws IOException
	 */
	public List<String> getGoogleSearchImages(String query,int count,int height,int width) throws IOException{

		query =  URLEncoder.encode(query,"UTF-8");
        String url = "https://www.google.com/search?tbm=isch&tbs=isz:ex,iszw:"+width+",iszh:"+height+"&q=" + query;
        System.out.println(url);
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        
        Elements elements = document.select("img");
    	
        List<String> list = new ArrayList<>();
        
    	for(int i=0;i<count && i<elements.size();i++){
    		Element e = elements.get(i);
    		list.add(e.attr("src"));
    	}
    	
    	return list;
	}
	
	
	/**
	 * 
	 * @param query Google search query for the images 
	 * @param count Number of images required
	 * @param height height of images
	 * @param width  widht of images
	 * @return List of BufferedImages in google search result. Images may be less than the count value. 
	 * @throws IOException
	 */
	public List<String> getGoogleSearchImages(String query,int count) throws IOException{

//		query =  URLEncoder.encode(query,"UTF-8");
        String url = "https://www.google.com/search?tbm=isch&tbs=isz:l&q=" + query;
        //System.out.println(url);
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        
        Elements elements = document.select("img");
    	
        List<String> list = new ArrayList<>();
        
    	for(int i=0;i<count && i<elements.size();i++){
    		Element e = elements.get(i);
    		//System.out.println(e.data());
    		list.add(e.attr("src"));
    		//System.out.println(e.attr("src"));
    	}
    	
    	return list;
	}
	
	
	/**
	 * 
	 * @param urls List of URLs for images.
	 * @return List of BufferedImages of passed URLs.
	 * @throws MalformedURLException one or many of the URLs is malformed.
	 * @throws IOException Cannot read Image from one or many URLs.
	 */
	public List<BufferedImage> getImagesForUrls(List<String> urls) throws MalformedURLException, IOException{
        List<BufferedImage> list = new ArrayList<>();
        
    	for(int i=0;i<list.size();i++){
    		list.add(ImageIO.read(new URL(urls.get(i))));
    	}
    	return list;
	}
	
	//JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));	

}
