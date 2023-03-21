package sfdc.utilities;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class ReusableUtils {

	public boolean isElementDispalyed(WebElement element,WebDriver driver) {
		boolean isElementFound = false;
		if(element.isDisplayed()) {
			isElementFound = true;

		}
		return isElementFound;
	}

	public boolean waitForElementClikable(WebDriver driver,WebElement element) {
		boolean isElementClikable = false;
		WebDriverWait wait = new WebDriverWait( driver, WaitConstants.ELEMENT_WAIT_DURATION);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isElementClikable = true;
		}catch(Exception e) {

			System.out.println("Exception occured while waiting for the element " +e.getMessage());
		}

		return false;



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
	
	
	

	public static boolean verifyDropDown(WebElement element, String[] options,String ValidationMessage)
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
	
	public  void clickOnElement(WebElement element) {

		element.click();

	}
	
	public static boolean VerifyMenu(WebDriver driver) {
		boolean isOptionVerified = false; 
		String[] userMenuOption = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		List <WebElement> listOfUserMenuOption =driver.findElement(By.id("userNav-menuItems")).findElements(By.tagName("a"));

		//*[@id="userNav-menuItems"]/a[1]

		for(int i=0;i<userMenuOption.length;i++) {

			String optionValue= listOfUserMenuOption.get(i).getText();
			if(optionValue.equals(userMenuOption[i])) {

				System.out.println("Option "+userMenuOption[i]+" is verified");
				isOptionVerified=true;
			}else {

				System.out.println(" Options " +userMenuOption[i]+ "not selected");
				isOptionVerified=false;

			} 

		}
		return isOptionVerified;

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
	
	

public static void clearElement(WebElement element) {
	element.clear();	

}	
	
public static void insertText(WebElement element,String inputString) {
	
	element.sendKeys(inputString);
	
}	
	
	
	
	
}
