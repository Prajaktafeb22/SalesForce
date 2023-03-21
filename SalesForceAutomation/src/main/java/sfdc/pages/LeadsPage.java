package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	
	public LeadsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	@FindBy(id="Lead_Tab")
	public WebElement leadTab;
	
	@FindBy(xpath="//h1[@class='pageType']")
	public WebElement leadsHomePage;
	

	@FindBy(xpath="//h1[@class='pageType']")
	public WebElement leadsUpdatedHomePage;
	
	@FindBy(xpath="//div[@id='userNavButton']")
	public WebElement userMenuOption;
	
	
	@FindBy(xpath="//a[@class='menuButtonMenuLink'][@title='Logout']")
	public WebElement logOut;
	
	
	@FindBy(xpath="//input[@id='Login']")
	public WebElement logIn;
	
	
	
	
	
}
