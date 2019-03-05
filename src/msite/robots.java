package  msite;

import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class robots extends baseClass{


	
	@BeforeClass
	public static void setup() throws IOException, InterruptedException{
   setup();
		
	}
	
	@Test (dataProvider="robotdata")
	public void testRobots (String url,String robotinstruction){
		driver.navigate().to(url);
		if (url=="http://www.shine.com/robots.txt")
		{
			driver.navigate().to(url);

		}
		String mapirobot=driver.findElement(By.cssSelector("html>body>pre")).getText();
		//APP_LOGS.debug("URL is.."+url);
		//APP_LOGS.debug("robots text is  "+mapirobot);
		Assert.assertEquals(mapirobot,robotinstruction);
		
		
	}
	
	 @DataProvider
     public Object [][] robotdata(){
			Object [][] data= new Object [3][2];
			
			data[0][0]="https://mapi.shine.com/robots.txt";
			data[0][1]="User-Agent: *"+'\n'+ "Allow: /api/"+'\n'+ "Disallow: /";		
     	
			data[1][0]="http://www.shine.com/robots.txt";
			data[1][1]="User-agent: *"+'\n'+"Allow: /"+'\n'+'\n'+"Disallow: /myshine/*";
			
			data[2][0]="http://sumopc.shine.com/robots.txt";
			data[2][1]="User-agent: *"+'\n'+"Disallow: /";
     	
     	return data;
     	
     }
	 @AfterClass
	 public void CloseDriver () throws Exception {
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
