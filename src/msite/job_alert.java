package msite;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class job_alert extends baseClass {

	@BeforeClass
	public void starttest() throws IOException, InterruptedException {

		setup();

	}

	@Test(priority = 0)
	public void nonloggedin_Jobalert() throws Exception {

		driver.get(baseurl + "/");

		Thread.sleep(2000);

		driver.findElement(By.id("id_menu")).click();

		driver.findElement(By.xpath(".//*[@id='id_jobalerts']/strong")).click();
		driver.findElement(By.id("id_name")).clear();
		driver.findElement(By.id("id_name")).sendKeys("java");
		driver.findElement(By.id("id_mail")).sendKeys(baseClass.emailid);
		// driver.findElement(By.id("id_mail")).sendKeys("shinejj@mailinator.com");

		driver.findElement(By.id("id_keywords")).clear();
		driver.findElement(By.id("id_keywords")).sendKeys("java");

		jobalert();

		Thread.sleep(3000);
		Assert.assertEquals("Shine.com", driver.getTitle());

		WebElement element1 = driver.findElement(By.cssSelector("div.matchjobs.aler.cls_aler"));
		String message = element1.getText();
		Assert.assertEquals(
				"Job Alert \"java\" created." + '\n' + '\n' + "Your email \"" + baseClass.emailid
						+ "\" is already registered with Shine. Login Now to see your job alerts in your account.",
				message);

		APP_LOGS.debug("logged out->job alert created");

	}

	@Test(priority = 1)
	public void loggedin_Jobalert() throws Exception {

		driver.get(baseurl + "/");

		Thread.sleep(5000);

		login();
		driver.findElement(By.id("id_menu")).click();
		// driver.findElement(By.cssSelector("a#id_jobalerts")).click();
		driver.findElement(By.xpath(".//*[@id='id_jobalerts']/strong")).click();
		driver.findElement(By.cssSelector("span.plus")).click();
		driver.findElement(By.id("id_name")).clear();
		driver.findElement(By.id("id_name")).sendKeys("python");
		driver.findElement(By.id("id_keywords")).clear();
		driver.findElement(By.id("id_keywords")).sendKeys("python");

		jobalert();

		APP_LOGS.debug("logged in->job alert created");

	}

	@Test(priority = 2)
	public void edit_Jobalert() throws Exception {

	}

	public void jobalert() throws Exception {

		driver.findElement(By.id("id_salary_span")).click();
		driver.findElement(By.id("id_sal_10")).click();

		driver.findElement(By.id("id_experience_span")).click();
		driver.findElement(By.id("id_exp_9")).click();

		driver.findElement(By.id("id_industry_span")).click();

		driver.findElement(By.id("id_ind_19")).click();
		driver.findElement(By.id("id_ind_69")).click();
		driver.findElement(By.id("id_ind_4")).click();

		Thread.sleep(2000);
		List<WebElement> save0 = driver.findElements(By.cssSelector("a.cls_mSelect_save.pullRight"));
		save0.get(0).click();

		driver.findElement(By.id("id_area_span")).click();
		driver.findElement(By.id("id_area_4559")).click();
		/*
		 * driver.findElement(By.id("id_area_1312")).click();
		 * driver.findElement(By.id("id_area_1301")).click();
		 * driver.findElement(By.id("id_area_1313")).click();
		 * driver.findElement(By.id("id_area_4530")).click();
		 */
		Thread.sleep(2000);
		List<WebElement> save1 = driver.findElements(By.cssSelector("a.cls_mSelect_save.pullRight"));
		save1.get(1).click();
		Thread.sleep(2000);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_location_span")));
		driver.findElement(By.id("id_location_span")).click();
		driver.findElement(By.id("id_locid_246")).click();
		driver.findElement(By.id("id_locid_404")).click();
		driver.findElement(By.id("id_locid_406")).click();
		driver.findElement(By.id("id_locid_423")).click();

		Thread.sleep(2000);
		List<WebElement> save2 = driver.findElements(By.cssSelector("a.cls_mSelect_save.pullRight"));
		save2.get(2).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button

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
