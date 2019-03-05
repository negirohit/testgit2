package msite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class profile_Views extends baseClass {

	@BeforeClass
	public void starttest() throws Exception {

		setup();
		driver.navigate().to(baseurl);

	}

	@Test(priority = 1)
	public void profile_views() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get(baseUrl + "/");

		// baseClass.login();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.linkText("Sign In")));
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.name("email")).clear();
		// driver.findElement(By.name("email")).sendKeys(baseClass.emailid);
		driver.findElement(By.name("email")).sendKeys("rohitnegi2345@gmail.com");
		// driver.findElement(By.name("email")).sendKeys("shinetest20160531110650@mailinator.com");

		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@name='']")).click();

		Thread.sleep(3000);

		try {
			// Try block to handle code that may cause exception
			driver.findElement(By.cssSelector("a.cls_link.link")).click();
		} catch (Exception e) {
			// This block is to catch divide-by-zero error
			System.out.println("no app pop up");
		}

		// driver.findElement(By.xpath("//div[@id='header']/a")).click();
		driver.findElement(By.id("id_menu")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("id_profileviews")).click();

		Thread.sleep(3000);

		List<WebElement> element2 = driver.findElements(By.cssSelector("div.whoviewed"));
		element2.get(0).getText();
		// String strng2 = element2.getText();

		// WebElement element3 =driver.findElement(By.cssSelector("a.ui-link"));

		// String strng3= element3.getText();
		try {
			Assert.assertNotEquals(
					"There has been no recruiter activity on your Profile. Please keep your Profile updated to ensure that you are visible to Recruiters.",
					element2);
			APP_LOGS.debug("profile viewd");
			List<WebElement> apply = driver.findElements(By.cssSelector("a.can_inbox.ui-btn.search-btn"));
			apply.get(0).click();
			driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();
			Assert.assertEquals(driver.findElement(By.cssSelector("div>h1")).getText(),
					"Your application has been submitted successfully.");

		} catch (Exception e) {
			Assert.assertEquals(
					"There has been no recruiter activity on your Profile. Please keep your Profile updated to ensure that you are visible to Recruiters.",
					element2);
		}

		// Thread.sleep(5000);

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