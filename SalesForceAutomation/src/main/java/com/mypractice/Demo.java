package com.mypractice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	public static void main(String[] args) {

       WebDriver driver;
       WebDriverManager.chromedriver().setup();
       driver=new ChromeDriver();
       
       
		ExtentReports extent =new ExtentReports();
		String dateFormat = new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
        String reportPath = System.getProperty("user.dir")+"//target//TestCaseReport//"+dateFormat+"_SFDCReport.html";
		System.out.println(reportPath);

		ExtentHtmlReporter report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);

		ExtentTest test = extent.createTest("login page error");
		driver.get("https://login.salesforce.com");
		test.info("login page launched");
		test.info("Enter usere name"+"prajakta");
		test.info("enter password");
		extent.flush();



	}

}
