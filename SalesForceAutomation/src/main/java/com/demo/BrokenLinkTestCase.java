package com.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkTestCase {
	
	
	
	
static WebDriver  driver;
	
	static {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		
	       }

	public static void main(String[] args) {
		String url = "";
		String starturl = "https://www.google.com";
		HttpURLConnection huc = null;
		int respCode = 200;
		
		driver.get(starturl);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		ListIterator<WebElement> it = links.listIterator();
		
		while(it.hasNext())
		{
			url = it.next().getAttribute("href");
			
			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
				}
			
			if(!url.startsWith(starturl)){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
				}
			
			
			try {
				huc = (HttpURLConnection)(new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if(respCode >= 400){
				System.out.println(url+" is a broken link");
				}
				else{
				System.out.println(url+" is a valid link");
				}

				} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				
		}
		
	}

}
