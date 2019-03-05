package msite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class registration_before_Apply extends baseClass{
	

	@BeforeClass
	public  void starttest() throws IOException, InterruptedException{
		
		setup();
	    driver.navigate().to(baseurl);
			
	
	}
	
	@Test
    public void registration_Before_Apply() throws Exception  {
		
		 driver.findElement(By.id("id_q")).clear();
		    driver.findElement(By.id("id_q")).sendKeys("java");
		    new Select(driver.findElement(By.name("minexp"))).selectByVisibleText("2 Yrs");
		    driver.findElement(By.cssSelector("option[value=\"7\"]")).click();
		    driver.findElement(By.name("loc")).clear();
		    driver.findElement(By.name("loc")).sendKeys("delhi");
		    new Select(driver.findElement(By.name("minsal"))).selectByVisibleText("Rs 4.0 - 4.5 Lakh / Yr");
		    new Select(driver.findElement(By.name("ffunc_area_text"))).selectByVisibleText("IT - Software");
		    driver.findElement(By.xpath("//input[@name='']")).click();
		    Thread.sleep(5000);
		   
		    
		    // apply jobs
		   // driver.findElement(By.xpath(".//*[@id='cls_jobs']/div[2]/div[2]/a")).click();
		    List <WebElement> apply = driver.findElements(By.cssSelector("a.apply.ui-link.ui-btn.ui-shadow.ui-corner-all"));
			apply.get(5).click();
		    // assert for successful job apply
			Thread.sleep(3000);
			
		//	assert_message();
		   
		   
	    mobile_Registration_new.registration();
		resume_upload.testResumeupload();

}

}