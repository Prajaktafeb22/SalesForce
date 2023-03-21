package com.mypractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSeleniumTestCase {

	public static WebDriver driver;
	public static Properties prop;
	public static String pageName;
	public static ExtentReports extent;
	public static  ExtentTest test;



	public static void init() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}

	protected static void InitiliseProp() throws IOException {

		prop=new Properties();
        FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\SalesForceAutomation\\src\\main\\java\\sfdc\\testdata\\user_accounts.properties");
		prop.load(ip);// load the properties  
		ip.close();
		
	}




	protected static void Login() {
		// TODO Auto-generated method stub
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();

	}

	protected static void InitReport()
	{
		extent =new ExtentReports();
		String dateFormat = new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		String reportPath = System.getProperty("user.dir")+"//target//TestCaseReport//_"+dateFormat+"_"+pageName+"_SFDCReport.html";
		System.out.println(reportPath);

		ExtentHtmlReporter report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);
	}



	protected static void threadSleep(int seconds) throws InterruptedException
	{
		Thread.sleep((long)seconds*1000);

		/*synchronized(driver)
        {
        	driver.wait();
        	        }*/
	}
	protected static void Logout() {
		// TODO Auto-generated method stub		
		driver.findElement(By.id("userNavLabel")).click();
		driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click();

	}

	public static boolean selectOptionInUserMenu(WebDriver driver,String optionName) {
		boolean isOptionSelected =false;
		WebElement userMenuOption=driver.findElement(By.xpath("//a[text()=\""+optionName+"\"]"));
		driver.manage().window().maximize();

		//a[text()=\"+optionName+\"]"

		if(userMenuOption.isDisplayed()) {

			userMenuOption.click();
			isOptionSelected =true;
			System.out.println("User menu option "+optionName+" is  visible");

		}else {

			System.out.println("User menu option "+optionName+" is not visible");
		}
		return isOptionSelected;
	}

	protected static boolean VerifyMenu(WebDriver driver) {
		boolean isOptionVerified = true; 
		String[] userMenuOption = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		List <WebElement> listOfUserMenuOption =driver.findElement(By.id("userNav-menuItems")).findElements(By.tagName("a"));

		//*[@id="userNav-menuItems"]/a[1]

		for(int i=0;i<userMenuOption.length;i++) {

			String optionValue= listOfUserMenuOption.get(i).getText();
			if(optionValue.equals(userMenuOption[i])) {

				System.out.println("Option "+userMenuOption[i]+" is verified");
			}else {

				System.out.println(" Options " +userMenuOption[i]+ "not selected");
				isOptionVerified=false;

			}

		}
		return isOptionVerified;

	}

	public static boolean isloadComplete(WebDriver driver)
	{
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}



	public static boolean mySettingAvailableOpt(WebDriver driver,String optionName) {
		boolean isOptionSelected =false;
		driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		List <WebElement> listOfMySettingOption =driver.findElement(By.id("AutoNumber5")).findElements(By.className("parent"));
		for(int i=1;i<listOfMySettingOption.size();i++) 
		{
			if(optionName.equalsIgnoreCase(listOfMySettingOption.get(i).getText()))
			{
				listOfMySettingOption.get(i).click();
				System.out.println(" Clicked  " + optionName);
				isOptionSelected=true;
				break;
			}
		}
		Assert.assertTrue(isOptionSelected, optionName + "Not found");
		return isOptionSelected;
	}


	protected static boolean verifyMySettingUserMenu(WebDriver driver) {
		boolean isOptionVerified = true; 
		String[] mySettingOption = {"Personal","Display & Layout","Email","Chatter","Calendar & Reminders","Desktop Add-Ons","Import"};
		List <WebElement> listOfMySettingOption =driver.findElement(By.id("AutoNumber5")).findElements(By.className("parent"));


		//*[@id="userNav-menuItems"]/a[1]

		for(int i=1;i<listOfMySettingOption.size();i++) {

			String optionValue=  listOfMySettingOption.get(i).getText();
			if(optionValue.equals(mySettingOption[i-1])) {

				System.out.println("Option "+mySettingOption[i-1]+" is verified");
			}else {

				System.out.println(" Verification of " +mySettingOption[i-1]+ " failed");
				isOptionVerified=false;

			}

		}
		return isOptionVerified;
	}

	protected static boolean verifyDropDown(WebElement element, String[] options,String ValidationMessage)
	{
		boolean isvalid= true;

		List<WebElement> optionelements = element.findElements(By.tagName("option"));

		for(int i=0;i<options.length;i++)
		{
			if(!options[i].equals(optionelements.get(i).getText()))
			{
				isvalid = false;
				break;
			}

			//System.out.println(options + " Verified");

		}

		Assert.assertTrue(isvalid, ValidationMessage);

		return isvalid;

	}



	protected static boolean verifyOpportunitiesMenu(WebDriver driver) {

		boolean verifyOption=true;
		String[] opportinitiesOption = {"All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week","New This Week","Opportunity Pipeline",
				"Private","Recently Viewed Opportunities","Won"};
		List <WebElement> listOfOpportunitiesOption =driver.findElement(By.id("fcf")).findElements(By.tagName("option"));

		for(int i=0;i<listOfOpportunitiesOption.size();i++) {

			String optionValue=  listOfOpportunitiesOption.get(i).getText();
			if(optionValue.equals(opportinitiesOption[i])) {

				System.out.println("Option "+opportinitiesOption[i]+" is verified" );

			}else {

				System.out.println(" Verification of " +opportinitiesOption[i]+ " failed against" + optionValue );
				verifyOption=false;

			}

		}

		Assert.assertTrue(verifyOption,"Failed to verify this Options");

		return verifyOption;

	}







	public static boolean opportunitiesAvailableOpt(WebDriver driver,String optionName) {


		boolean isOpportunitiesSelected =false;
		//driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		List <WebElement> listOfOpportunitiesOption =driver.findElement(By.id("fcf")).findElements(By.tagName("option"));		
		for(int i=0;i< listOfOpportunitiesOption.size();i++) 
		{	
			if(optionName.equalsIgnoreCase( listOfOpportunitiesOption.get(i).getText()))
			{
				listOfOpportunitiesOption.get(i).click();
				System.out.println( optionName+ " is selected");
				isOpportunitiesSelected=true;
				break;
			}
		}
		Assert.assertTrue(isOpportunitiesSelected, optionName + "Not found");
		return isOpportunitiesSelected;
	}



	protected static boolean verifyReportMenu(WebDriver driver) {
		boolean isOptionVerified = true; 
		String[] reportOption = {"Opportunity Pipeline","Stuck Opportunities","Closed Opportunities","Opportunity Field History Report"};
		List <WebElement> listOfReportOption =driver.findElements(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul"));
		//*[@id="userNav-menuItems"]/a[1]

		for(int i=1;i<listOfReportOption.size();i++) {

			String optionValue=  listOfReportOption.get(i).getText();
			if(optionValue.equals(reportOption[i-1])) {

				System.out.println("Option "+reportOption[i-1]+" is verified");
			}else {

				System.out.println(" Verification of " +reportOption[i-1]+ " failed");
				isOptionVerified=false;

			}

		}
		return isOptionVerified;
	}


	protected static void VerifyPage(String pageName)
	{
		Assert.assertTrue(pageName.equalsIgnoreCase(driver.findElement(By.xpath("//h1[@class='pageType']")).getText()),String.format("Not landed in %s page",pageName));
		System.out.println(String.format("Reached %s page", pageName));
	}
	protected static boolean verifyContactMenu(WebDriver driver) {
		boolean isOptionVerified = true; 
		String[] contactMenuOption = {"Event","Task","Account","Opportunity","Lead","Contact"};
		List <WebElement> listOfContactMenuOption = driver.findElement(By.id("createNewLabel")).findElements(By.className("menuButtonLabel"));


		//*[@id="userNav-menuItems"]/a[1]

		for(int i=0;i<listOfContactMenuOption.size();i++) {

			String optionValue=  listOfContactMenuOption.get(i).getText();
			if(optionValue.equals(contactMenuOption[i])) {

				System.out.println("Option "+contactMenuOption[i]+" is verified");
			}else {

				System.out.println(" Verification of " +contactMenuOption[i]+ " failed");
				isOptionVerified=false;

			}

		}
		return isOptionVerified;
	}

	public static boolean contactMenu(WebDriver driver,String optionName) {


		boolean isOpportunitiesSelected =false;
		//driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		List <WebElement> listOfContactMenuOption =driver.findElement(By.id("createNewLabel")).findElements(By.tagName("menuButtonLabel"));		
		for(int i=0;i< listOfContactMenuOption.size();i++) 
		{	
			if(optionName.equalsIgnoreCase( listOfContactMenuOption.get(i).getText()))
			{
				listOfContactMenuOption.get(i).click();
				System.out.println( optionName+ " is selected");
				isOpportunitiesSelected=true;
				break;
			}
		}
		Assert.assertTrue(isOpportunitiesSelected, optionName + "Not found");
		return isOpportunitiesSelected;
	}

	public void Click(WebElement element)
	{                	  
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}


	public void GetToLatestPopupWindow() throws InterruptedException
	{
		// Store the current window handle		
		String currentWinHandle = driver.getWindowHandle();

		Iterator<String> windowHandle = driver.getWindowHandles().iterator();

		while(windowHandle.hasNext())
		{
			String winHandle = windowHandle.next();
			if(!currentWinHandle.equals(winHandle))
			{
				driver.switchTo().window(winHandle);
				threadSleep(2);// Switch takes time hence wait here 
			}
		}
	}


	@BeforeMethod
	public  void  beforemethod(Method method, Object[] params){
		String classname = getClass().getSimpleName();
		String methodName = method.getName();
		String paramsList = Arrays.asList(params).toString();  

		System.out.println(String.format("Executing Method %s", methodName));
		test = extent.createTest(methodName);
	}

	@AfterMethod
	public  void  aftermethod(Method method, Object[] params){
		String classname = getClass().getSimpleName();
		String methodName = method.getName();
		String paramsList = Arrays.asList(params).toString();

		extent.flush();
		System.out.println(String.format("Finished Executing Method %s", methodName));

	}



	public static String takeScreenshot() throws IOException {


		String dateFormat = new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot screenshot =(TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"//target//TestCaseReport//_"+dateFormat+"_"+pageName+"_.PNG";
		File destination = new File(destinationPath);
		FileUtils.copyFile(srcFile, destination);
		return destinationPath;

	}


	public static void enterText(WebElement element,String text) {

		element.sendKeys(text);

	}

	public static void clickOnElement(WebElement element) {

		element.click();

	}


	public static boolean clickOnProfile(WebElement profileElement) {

		if (profileElement.isDisplayed()) {

			profileElement.click();
		}
		return false;

	}

	
	protected static String generateRandom() {
		String aToZ="ABCDEFGHIJKLMNOPQESTUVWXYZ1234567890"; // 36 letter.
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < 17; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}



}



