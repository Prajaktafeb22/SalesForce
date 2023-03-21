package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RandomScenarioPage  {
	
	public  RandomScenarioPage(WebDriver driver){
		
		PageFactory.initElements( driver,this);// uses reflection
		
	}
	
	
	
	

}
