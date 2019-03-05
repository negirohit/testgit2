package msite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class mobile_Registration_new extends baseClass {

	public static String EmailID1;

	@BeforeClass
	public void starttest() throws IOException, InterruptedException {

		Thread.sleep(3000);
		setup();

	}

	@Test
	public static void registration() throws InterruptedException {
		// hello
		Thread.sleep(3000);
		registration_Method();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_totalExperience")));
		driver.findElement(By.id("id_totalExperience")).sendKeys("5");

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Upload resume to complete registration",
				driver.findElement(By.cssSelector("div.topheading > strong")).getText());
		APP_LOGS.debug("registration completed");

	}

	public static void registration_Method() throws InterruptedException

	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// get current date time with Date()
		Date date = new Date();

		EmailID1 = "shinetest" + dateFormat.format(date) + "@mailinator.com";
		APP_LOGS.debug("registration email id is ...." + EmailID1);
		baseClass.emailid = EmailID1;

		driver.navigate().to(baseurl);

		System.out.println(EmailID1);
		// click on register link on homepage
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_register")));
		driver.findElement(By.id("id_register")).click();

		driver.findElement(By.id("id_name")).clear();
		driver.findElement(By.id("id_name")).sendKeys("Shine Test Profile");
		driver.findElement(By.id("id_email")).clear();
		driver.findElement(By.id("id_email")).sendKeys(EmailID1);
		driver.findElement(By.id("id_raw_password")).clear();
		driver.findElement(By.id("id_raw_password")).sendKeys("password");
		driver.findElement(By.id("id_cell_phone")).clear();
		driver.findElement(By.id("id_cell_phone")).sendKeys("9876556789");

		driver.findElement(By.id("id_location_span")).click();
		driver.findElement(By.id("id_candidate_location_406")).click();

		Thread.sleep(2000);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_area_span")));
		driver.findElement(By.id("id_area_span")).click();

		driver.findElement(By.id("id_functional_area_4559")).click();
		driver.findElement(By.id("id_functional_area_1301")).click();
		driver.findElement(By.id("id_functional_area_4530")).click();

		List<WebElement> save = driver.findElements(By.cssSelector("a.cls_mSelect_save.pullRight"));
		save.get(0).click();

		driver.findElement(By.id("id_industry_span")).click();
		driver.findElement(By.id("id_industry_18")).click();
		driver.findElement(By.id("id_industry_19")).click();
		driver.findElement(By.id("id_industry_27")).click();
		List<WebElement> save1 = driver.findElements(By.cssSelector("a.cls_mSelect_save.pullRight"));
		save1.get(1).click();
	}

	@Override
	@AfterClass
	public void CloseDriver() throws Exception {
		driver.quit();

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("scrfolder") + testResult.getName() + ".jpg"));

		}
	}

}
