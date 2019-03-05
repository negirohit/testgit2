package msite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class forgot_password extends baseClass {

	@BeforeClass
	public static void starttest1() throws IOException, InterruptedException {

		setup();
	}

	@Test
	public static void forgotpassword() throws IOException, InterruptedException {

		// click sign in button
		driver.findElement(By.linkText("Sign In")).click();

		// click forgot password link
		driver.findElement(By.linkText("Forgot Password?")).click();

		// enter emailid

		Thread.sleep(2000);
		driver.findElement(By.id("candidateemailid")).sendKeys("rohitnegi2345@gmail.com");

		// click submit button

		Thread.sleep(2000);
		driver.findElement(By.id("id_fpSubmit")).click();

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
