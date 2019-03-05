package msite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class mail_check extends baseClass  {
	
	@BeforeClass
	public void start() throws Exception
	{
		setup();
	}

	
	@Test 
	public void testMailinatorInbox() throws Exception {
		
	
		baseurl = "http://www.mailinator.com";
	  driver.get(baseurl+"/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	  driver.findElement(By.id("inboxfield")).sendKeys(emailid);
	  driver.findElement(By.cssSelector(".btn.btn-dark")).click(); 
	   
	  
	 
		//assertion for 4 mails. Verify email, verify email from update flow,forgot password, alert creation.
	    List <WebElement> correctlen = driver.findElements(By.cssSelector("div.from.ng-binding"));
	    int len= correctlen.size();		
	    
		Assert.assertEquals(len,3);
		
								
							
						
										}
	@AfterClass
	public void closedriver(){
		driver.quit();
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
	    if (testResult.getStatus() == ITestResult.FAILURE) { 
	            
	           File scrFile = ((TakesScreenshot) driver)
	                 .getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("scrfolder")+ testResult.getName()+".jpg"));
	              
	           }
	 
	}
										}
										