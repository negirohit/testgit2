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

public class inbox extends baseClass {

	@BeforeClass
	public void starttest() throws Exception {

		setup();

	}

	@Test(priority = 1)
	public void jobAlert_Apply() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.linkText("Sign In")));
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.name("email")).clear();
		// driver.findElement(By.name("email")).sendKeys(baseClass.emailid);
		driver.findElement(By.name("email")).sendKeys("rohitnegi2345@gmail.com");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@name='']")).click();

		Thread.sleep(5000);

		try {
			// Try block to handle code that may cause exception
			driver.findElement(By.cssSelector("a.cls_link.link")).click();
		} catch (Exception e) {
			// This block is to catch divide-by-zero error
			System.out.println("no app pop up");
		}

		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[@id='header']/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("id_inbox")).click();

		Thread.sleep(3000);

		List<WebElement> apply = driver.findElements(By.cssSelector("a > div.matchjobs > div.jobsinside1 > span.exp"));

		int i = apply.size();

		if (i > 0) {

			apply.get(1).click();

			Thread.sleep(2000);

			List<WebElement> apply1 = driver.findElements(By.cssSelector("input.apl_btn"));

			int j = apply1.size();
			if (j > 0) {
				// boolean applypresent=apply1.get(0).isDisplayed();

				// if(applypresent)

				apply1.get(0).click();
				Thread.sleep(5000);

				APP_LOGS.debug("job alert -> apply");

				driver.findElement(By.cssSelector(".applynew.cls_call_dialogbox.cls_applyFromJdes")).click();

			}

			else {

				Assert.assertTrue(true, "No apply button left");

				APP_LOGS.debug("alreday applied,No apply button left");
			}

		}

		else {

			List<WebElement> element6 = driver.findElements(By.cssSelector("span.mail_container"));

			String strng2 = element6.get(1).getText();

			// String strng2 = element2.getText();

			Assert.assertEquals("You have not received any job alert mails in the past 15 days." + "\n" + "\n"
					+ "Keep your profile updated to get latest Job Alerts.", strng2);
			APP_LOGS.debug("You have not received any job alert mails in the past 15 days.");
		}

	}

	@Test(priority = 2)
	public void recruitermail_Apply() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(CONFIG.getProperty("testSiteURL"));

		driver.findElement(By.xpath("//div[@id='header']/a")).click();
		// driver.findElement(By.xpath("//div[@id='navpanel']/div/ul/li[2]/a/strong")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("id_inbox")).click();

		List<WebElement> apply2 = driver.findElements(By.cssSelector("a.mail_tab"));

		apply2.get(1).click();

		List<WebElement> apply3 = driver.findElements(By.cssSelector("span.exp"));

		int j = apply3.size();

		if (j > 0) {
			apply3.get(0).click();

			Thread.sleep(5000);

			driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();

			// presence of apply button in second page
			// boolean Presence=
			// driver.findElement(By.xpath(".//*[@id='leftpanel']/div[2]/div/div/section/ul[1]/li[1]/a")).isDisplayed();

			/*
			 * WebElement first = driver.findElement(By.xpath(
			 * ".//*[@id='leftpanel']/div[2]/div/div/section/ul[1]/li[1]/a"));
			 * String first1 = first.getText();
			 * 
			 * 
			 * if(first1=="Applied") {
			 * 
			 * Assert.assertEquals("Applied", first1); }
			 * 
			 * 
			 * else
			 */
			{
				// first.click();

				WebElement element = driver.findElement(By.cssSelector("div.login_con_div.white >h1"));
				String strng = element.getText();

				Assert.assertEquals("Your application has been submitted successfully.", strng);
				APP_LOGS.debug("Your application has been submitted successfully.");
			}

		}

		else {

			List<WebElement> element1 = driver.findElements(By.cssSelector("span.mail_container"));

			String strng1 = element1.get(1).getText();
			// String strng2 = element2.getText();

			Assert.assertEquals("You have not received any job alert mails in the past 15 days." + "\n" + "\n"
					+ "Keep your profile updated to get noticed by recruiters.", strng1);
			APP_LOGS.debug("You have not received any job alert mails in the past 15 days.");
		}

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
