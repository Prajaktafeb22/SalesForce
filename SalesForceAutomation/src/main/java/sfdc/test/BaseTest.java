package sfdc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.AccountsPage;
import sfdc.pages.ContactsPage;
import sfdc.pages.CreateOptyPage;
import sfdc.pages.LeadsPage;
import sfdc.pages.LoginPage;
import sfdc.pages.UserMenuPage;
import sfdc.utilities.AppConstants;
import sfdc.utilities.DataUtilities;
import sfdc.utilities.ReusableUtils;

/**
 * this function will return browser configuration
 * @author sBrowserName eg: chrome,safari,firefox
 *@return driver
 */
public class BaseTest {
	
	public static long startTime = 0;
	public static long endTime = 0;
	public static WebDriver driver = null; 
	DataUtilities du = new DataUtilities();
	public static ExtentReports extent = null;
	public static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	public static LoginPage loginPage = null;
	public static ReusableUtils reusableUtils = null;
	public static UserMenuPage userMenuPage = null;
	public static String pageName;
	public static AccountsPage accountsPage = null;
	public static CreateOptyPage createOptyPage = null;
	public static LeadsPage leadsPage = null;
	public Logger logger;
	public static ContactsPage contactsPage = null;
	
	
	@BeforeSuite
	public void suiteLevelConfigs() throws IOException {
		 intializeLog4jLogging();
		logger.info("Logging is starting");
	}
	
	
	//@Parameters("browser name")
	@BeforeTest
	public void setup() throws IOException {
		initializeReport();
		logger.info("Extent report are configured");
		driver = getDriver("chrome");
		logger.info("Browser config is set");
		loginPage = new LoginPage(driver );
		reusableUtils = new ReusableUtils();
		
	
	}
	
	
	@AfterTest
	public void tearDown() {
		//driver.close();
		extent.flush();
		logger.info("Report is flushed");
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
		System.out.println(String.format("Finished Executing Method %s", methodName));

	}

	
	//should return a web driver based on argument passed
	public WebDriver getDriver(String sBrowserName) {
		String browserName = sBrowserName.toLowerCase();
		switch(browserName) {
		
		case "chrome":
		      WebDriverManager.chromedriver().setup();
		      driver = new ChromeDriver();
		   //   test.log(Status.INFO,"ChromeBrowser in initialized");
		      break;
		

		case "firefox":
		      WebDriverManager.firefoxdriver().setup();
		      driver = new FirefoxDriver();
		      break;
		

		case "safari":
		      WebDriverManager.safaridriver().setup();
		      driver = new SafariDriver();
		      break;
		
		  default:
			  driver = null;
			  break;
		 
		} 
		return  driver;
	
	}
	
	
	public ChromeOptions headLessMode() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless","--disable-gpu","--window-size=1920");
		logger.info("Headless mode configuration initialized");
		return null;
	}
	
	
	
	
	public String selectEnvironment(String environment) throws IOException {
		
		String appURL = null;
		switch (environment) {
		case "prod":
			appURL = du.readAppEnvironments("prod.url");
			logger.info("Tests will start in prod environment");
			break;
		case "qa":
			appURL = du.readAppEnvironments("qa.url");
			logger.info("Tests will start inqa.url environment");
			break;
		case "dev":
			appURL = du.readAppEnvironments("dev.url");
			logger.info("Tests will start in dev.url environment");
		break;
		
		default:
			appURL = null;
			logger.fatal("Environment selection failed");
	}
	return appURL;	
		
}
	
	public void initializeReport() {
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir")+"//target//NewTestCaseReports//"+dateFormat+"_"+pageName+"_SFDCReport.html";
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);
		
	}
	
	public static String takeScreenshot() throws IOException {


		String dateFormat = new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot screenshot =(TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"//target//Screenshots//_"+dateFormat+"_"+pageName+"_.PNG";
		File destination = new File(destinationPath);
		FileUtils.copyFile(srcFile, destination);
		return destinationPath;

	}
	
	protected static void threadSleep(int seconds) throws InterruptedException
	{
		Thread.sleep((long)seconds*1000);

	}

	protected static void Logout() {
		// TODO Auto-generated method stub		
		driver.findElement(By.id("userNavLabel")).click();
		driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click();

	}
	
	public static void VerifyPage(String pageName)
	{
		Assert.assertTrue(pageName.equalsIgnoreCase(driver.findElement(By.xpath("//h1[@class='pageType']")).getText()),String.format("Not landed in %s page",pageName));
		System.out.println(String.format("Reached %s page", pageName));
	}
	

public void intializeLog4jLogging() throws IOException {
	
	Properties log4jProp = new Properties();
	FileInputStream fis = new FileInputStream(AppConstants.LOG4J_PROPERTIES_PATH);
	log4jProp.load(fis);
	PropertyConfigurator.configure("C:\\Users\\DECOMM\\eclipse-workspace\\SalesForceAutomation\\src\\main\\java\\sfdc\\configs\\log4jConfig.properties");
	logger = Logger.getLogger(getClass().getSimpleName());
	
}

	
public static boolean isloadComplete(WebDriver driver)
{
	return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
			|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
}	
	
public static void clickOnElement(WebElement element) {

	element.click();

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
	
public void Click(WebElement element)
{                	  
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	executor.executeScript("arguments[0].click();", element);
}





}
