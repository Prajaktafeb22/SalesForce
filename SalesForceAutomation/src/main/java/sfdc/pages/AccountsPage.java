package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage  {

	public  AccountsPage(WebDriver driver){

		PageFactory.initElements( driver,this);// uses reflection
		
	}


         @FindBy(xpath="//*[@id='Account_Tab']")
         public WebElement Accounts;


         @FindBy(id="createNewLabel")
         public WebElement Create_New;

      
         @FindBy(xpath="//a[text()='Account']")
         public WebElement Account;

         @FindBy(id="acc2")
         public WebElement AccountName;
  
         @FindBy(xpath="//input[@title='Save']")
         public WebElement Save;
  
         @FindBy(xpath="//*[@id=\"filter_element\"]//a[text()='Create New View']")
         public WebElement CreateNewView;
         
         
         @FindBy(xpath="//a[text()='Edit']")
         public WebElement Edit;
   
         
}


