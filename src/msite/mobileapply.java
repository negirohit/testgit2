package msite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class mobileapply extends baseClass {

	@BeforeClass
	public void starttest() throws IOException, InterruptedException {

		setup();

	}

	@Test
	public void matched_job_apply() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(baseurl + "/");
		baseClass.login();

		driver.get(baseurl + "/myshine/home/");

		Thread.sleep(5000);

		List<WebElement> apply2 = driver.findElements(By.cssSelector("a.apply"));

		apply2.get(3).click();

		assert_message();

	}

	@Test(priority = 1)
	public void similar_job_apply() throws Exception {

		Thread.sleep(3000);

		List<WebElement> apply2 = driver.findElements(By.cssSelector("a#apply_.apply.cls_call_dialogbox"));

		apply2.get(1).click();

		assert_message();

	}

	@Test(priority = 2)
	public static void search_apply() throws Exception {

		Thread.sleep(3000);

		mobilesearch.search1();

		// apply jobs
		List<WebElement> apply = driver.findElements(By.cssSelector("a.apply"));
		apply.get(5).click();
		// assert for successful job apply
		Thread.sleep(3000);

		assert_message();

		mobilesearch.search2();

		List<WebElement> apply1 = driver.findElements(By.cssSelector("a.apply"));
		apply1.get(6).click();

		assert_message();

	}

	public static void assert_message() throws Exception {
		Thread.sleep(3000);
		WebElement element1 = driver.findElement(By.cssSelector("ul.similarjobs> li> strong"));
		String message = element1.getText();
		Assert.assertEquals("Your Application has been submitted successfully.", message);
		// Jobs similar to the applied job.
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
