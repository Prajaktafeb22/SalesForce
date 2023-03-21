package sfdc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateOptyPage  {

	public CreateOptyPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="createNewLabel")
	public WebElement createNewdropdown;
	
	
	@FindBy(xpath="//a[@class='opportunityMru menuButtonMenuLink']")
	public WebElement opportunityTab;
	
	@FindBy(xpath="//input[@id='opp3']")
	public WebElement opportunityNameField;
	
	
	@FindBy(xpath="//input[@id='opp4'][@name='opp4']")
	public WebElement accountNameField;
	
	
	@FindBy(id="opp9")
	public WebElement closeDateField;
	
	@FindBy(xpath="//*[@id=\"opp11\"]")
	public WebElement stageField;
	
	
	@FindBy(xpath="//*[@id=\"opp11\"]")
	public WebElement leadSourceField;
	
	@FindBy(xpath="//*[@id=\"opp12\"]")
	public WebElement probabilityField;
	
	
	
	
	
	
	
	
	
	public static boolean verifyOpportunitiesMenu(WebDriver driver) {

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

	

	
}
