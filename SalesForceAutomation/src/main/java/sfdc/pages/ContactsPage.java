package sfdc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	public ContactsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//a[@title='Home Tab']")
	public WebElement sfdcHomePage;
	
	@FindBy(xpath="//a[@title='Contacts Tab'][text()='Contacts']")
	public WebElement contactsHomePage;
	
	@FindBy(xpath="//div[@class='menuButtonButton']//span[@class='menuButtonLabel']")
	public WebElement createNewLink;
	

	@FindBy(xpath="//a[@class='contactMru menuButtonMenuLink']")
	public WebElement contactsLink;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	public WebElement newContactPage;
	
	@FindBy(xpath="//input[@class='btn'][@name='new']")
	public WebElement newCreateContact;
	
	@FindBy(xpath="//input[@id='name_lastcon2']")
	public WebElement lastName;
	
	@FindBy(xpath="//input[@id=\"con4\"]")
	public WebElement accountName;
	
	
	@FindBy(xpath="//*[@id=\"topButtonRow\"]/input[1]")
	public WebElement saveAccountName;
	
	@FindBy(xpath="//li[@id=\"Contact_Tab\"]/a")
	public WebElement contactPage;
	
	
	@FindBy(xpath="//a[text()='Create New View']")
	public WebElement createNewViewLink;
	
	@FindBy(id="fname")
	public WebElement createNewViewTextArea;
	
	@FindBy(xpath="//*[@id=\"devname\"]")
	public WebElement createUniqueNameTextArea;
	
	
	@FindBy(xpath="//input[@class=\"btn primary\"][@name=\"save\"]")
	public WebElement saveCreateNewView;
	
	
	@FindBy(xpath="//select[@id=\"hotlist_mode\"]//option[text()=\"Recently Modified\"]")
	public WebElement recentlyModifiedLink;
	
	
	@FindBy(xpath="//select[@id=\"hotlist_mode\"]//option[text()=\"Recently Created\"]")
	public WebElement recentlyCreatedLink;
	
	
	@FindBy(xpath="//div[@class=\"errorMsg\"]")
	public WebElement ErrorCreateViewName;
	
	
	@FindBy(xpath="//input[@name=\"cancel\"]")
	public WebElement cancelCreateViewName;
	
	
	@FindBy(xpath="//table[@class='list']/tbody/tr[2]/th")
	public WebElement afterSavedCurrentCreateViewName;
	
	
	@FindBy(xpath="//input[@id=\"name_lastcon2\"]")
	public WebElement editNewContactLastName;
	
	@FindBy(xpath="//input[@id=\"con4\"]")
	public WebElement editNewContactAccountName;
	
	
	@FindBy(xpath="//input[@id=\"con4\"]")
	public WebElement saveNewContactPage;
	
	
	@FindBy(xpath="//h2[@class=\"topName\"]")
	public WebElement afterSavedNewContactPage;
	
	
	
	
}
