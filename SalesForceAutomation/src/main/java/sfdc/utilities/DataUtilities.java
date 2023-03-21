package sfdc.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataUtilities {
	
	
	/**
	 * @param keyName
	 * @return
	 * @throws IOException
	 */
	public String readAccountProperties(String keyName) throws IOException {
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USER_ACCOUNTS_FILE_PATH);
		prop.load(fis);
		prop.getProperty(keyName);
		return prop.getProperty(keyName);
		
	}
	

    /**
     * @param keyName pass the url key name eg.prod.url 
     * @return URL of particular environment
     * @throws IOException
     */
    public String readAppEnvironments(String keyName) throws IOException {
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.APP_ENV_ACCOUNTS_FILE_PATH);
		prop.load(fis);
		prop.getProperty(keyName);
		return prop.getProperty(keyName);
		
		
		
	}
    public String readPageValidationsText(String keyName) throws IOException {
		
  		Properties prop =new Properties();
  		FileInputStream fis = new FileInputStream(AppConstants.PAGE_VALIDATION_FILE_PATH);
  		prop.load(fis);
  		prop.getProperty(keyName);
  		return prop.getProperty(keyName);
  		
  	}
	
public String readFileUploadPath(String keyName) throws IOException {
		
  		Properties prop =new Properties();
  		FileInputStream fis = new FileInputStream(AppConstants.PERSONAL_FILE_PATH);
  		prop.load(fis);
  		prop.getProperty(keyName);
  		return prop.getProperty(keyName);
  		
  	}
	
	
    
    
	
}
