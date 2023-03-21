package sfdc.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import sfdc.utilities.DataUtilities;

public class UserMenuPage {

	public UserMenuPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	@FindBy(id = "userNavLabel")
	public WebElement userMenu;

	@FindBy(xpath = "//*[@class='menuButtonMenu menuWidthExtended']")
	public WebElement userMenuDropDown;

	@FindBy(xpath = "//div[@id='userNav-menuItems']//a")
	public List<WebElement>listOfuserMenuOptions;
	
	@FindBy(xpath="//*[@id=\"chatterTab\"]/div[2]//img[@title='Edit Profile']")
	public WebElement editProfile ;


	@FindBy(xpath="//li[@id='aboutTab']//a[@role='tab']")
	public WebElement aboutTab ;
	
	
	@FindBy(xpath="//input[@id='lastName'][@name='lastName']")
	public WebElement editProfileLastName ;
	
	
	@FindBy(xpath="//input[@type='button'][@value='Save All']")
	public WebElement saveAllButton ;
	
	
	@FindBy(xpath="//span[@id='tailBreadcrumbNode']")
	public WebElement verifyLastName ;
	
	
	@FindBy(xpath="//span[@class='publisherattachtext '][text()='Post']")
	public WebElement postLink ;
	

	@FindBy(xpath="cke_publisherRichTextEditor")
	public WebElement postLinkTextArea;
	
	
	@FindBy(xpath="//input[@id='publishersharebutton']")
	public WebElement sharePostLink;
	
	@FindBy(xpath="//a[@id='publisherAttachContentPost']")
	public WebElement fileLink;
	
	@FindBy(xpath="//a[@id='chatterUploadFileAction']")
	public WebElement uploadFileLink;
	
	@FindBy(xpath="//input[@class='file'][@name='chatterFile']")
	public WebElement chooseFileButton;
	
	
	@FindBy(xpath="//input[@id='publishersharebutton']")
	public WebElement shareFileButton;
	
	
	@FindBy(id="PersonalInfo")
	public WebElement personalMenuLink;
	
	
	@FindBy(xpath="//a[@id='LoginHistory_font']")
	public WebElement loginHistoryLink;
	
	
	@FindBy(className="pShowMore")
	public WebElement optionLink;
	
	
	@FindBy(id="DisplayAndLayout_font")
	public WebElement displayAndLayoutLink;
	
	
	@FindBy(xpath="//span[@id='CustomizeTabs_font' and text()='Customize My Tabs']")
	public WebElement customMyTabsLink;
	
	
	
	@FindBy(xpath="//select[@id='p4'] /option[text()='Salesforce Chatter']")
	public WebElement salesForseChatter;
	
	
	@FindBy(xpath="//option[@value='report' and text()='Reports']")
	public WebElement reportOption;
	
	
	@FindBy(id="duel_select_0_right")
	public WebElement addLeftOption;
	
	
	@FindBy(xpath="//td[@id='bottomButtonRow']/input[@title='Save']")
	public WebElement saveButton;
	
	@FindBy(id="EmailSettings_font")
	public WebElement myEmailSettingTAb;
	
	
	@FindBy(id="sender_name")
	public WebElement emailNameSetting;
	
	
	@FindBy(xpath="//input[@id='sender_email']")
	public WebElement emailAddressField;
	
	@FindBy(id="auto_bcc1")
	public WebElement bccRadioButton;
	
	
	@FindBy(xpath="	//td[@id='bottomButtonRow']/input[@class='btn primary']")
	public WebElement saveEmailSetting;
	
	
	@FindBy(xpath="//h1[@class='noSecondHeader pageType']")
	public WebElement myEmailSettingHeader;
	
	
	@FindBy(id="CalendarAndReminders_font")
	public WebElement activityReminderLink;
	
	
	@FindBy(id="testbtn")
	public WebElement oprnATestReminderButton;
	
	
	
	
	
	
	
	
	public  boolean verifyUserMenuItems(WebDriver driver) {
		boolean isOptionVerified = false; 
		String[] userMenuOption = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};

		for(int i=0;i<userMenuOption.length;i++) {

			String optionValue= listOfuserMenuOptions.get(i).getText();
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

	public boolean verifyUserName() throws IOException {
		DataUtilities du = new DataUtilities();
		boolean isUserNameValid =false;
		String expectedUserName = du.readPageValidationsText("homepage.username");
		if(expectedUserName.equals(userMenu.getText())) {
			isUserNameValid =true;
		}
		return isUserNameValid;
	}


	public static void clickOnElement(WebElement element) {

		element.click();

	}

	
	public static boolean verifyMySettingUserMenu(WebDriver driver) {
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
	
	
	
}
