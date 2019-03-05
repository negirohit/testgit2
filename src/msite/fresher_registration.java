package msite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class fresher_registration extends baseClass {

	public static String EmailID1;

	@BeforeClass
	public static void starttest() throws IOException, InterruptedException {

		setup();

	}

	@Test
	public static void fresher_Registration() throws InterruptedException {

		mobile_Registration_new.registration_Method();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_totalExperience")));
		driver.findElement(By.id("id_totalExperience")).sendKeys("0");

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Upload resume to complete registration",
				driver.findElement(By.cssSelector("div.topheading > strong")).getText());
		APP_LOGS.debug("registration completed");

	}

	@Override
	@AfterClass
	public void CloseDriver() throws Exception {
		driver.quit();

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("scrfolder") + testResult.getName() + ".jpg"));

		}
	}

}
