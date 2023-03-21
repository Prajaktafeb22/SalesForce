package sfdc.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sfdc.utilities.DataUtilities;

public class LoginPage {
	public  LoginPage(WebDriver driver){
		
		PageFactory.initElements( driver,this);// uses reflection
		
	}
	@FindBy(name="username")
	public WebElement username;
	
	
	@FindBy(name="pw")
	public WebElement password;
	
	@FindBy(name="Login")
	public WebElement loginButton;
	
	@FindBy(name="rem")
	public WebElement rememberMe;
	
	@FindBy
	public WebElement loginErrorMsg;
	
	
	//@FindBy(id="userNavLabel")
	//public WebElement usermenuoption;
	

	public void loginToApp(WebDriver driver, String URL) throws IOException {
		
		driver.get(URL);
		DataUtilities du = new DataUtilities();
		username.sendKeys(du.readAccountProperties("username"));
		password.sendKeys(du.readAccountProperties("password"));
		loginButton.click();
	
		
	}
	
	public boolean verifyErrorMessage() throws IOException {
        DataUtilities du = new DataUtilities();
        boolean isErrorMessage = false;
		String expectedErrorMessage = du.readPageValidationsText("loginpage.errorMessage");
		if(expectedErrorMessage.equals(loginErrorMsg.getText())) {
			isErrorMessage=true;
		
		}
		return isErrorMessage;
	}
	
	public void LauchPage(WebDriver driver,String URL) throws IOException
	{
		driver.get(URL);
	}
	
	
	
}
