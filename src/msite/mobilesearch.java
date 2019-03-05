package msite;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class mobilesearch extends baseClass {

	static String w1 = "";

	@BeforeClass
	public void start() throws IOException, InterruptedException {

		setup();
		driver.navigate().to(CONFIG.getProperty("testSiteURL"));

	}

	@Test
	public static void loggedoutSearch() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.navigate().to(CONFIG.getProperty("testSiteURL"));

		search0();

		APP_LOGS.debug("python search success");
		// added assertion
		String result = driver.findElement(By.cssSelector("div.signtab.jdmar")).getText().toString();
		Assert.assertNotEquals(result, "0 results found for python", "Result expected, but 0 candidates found!");

		Pattern p1 = Pattern.compile("[\\d,]+");
		Matcher m1 = p1.matcher(driver.findElement(By.cssSelector("div.signtab.jdmar")).getText());
		// String w = "";
		if (m1.find()) {
			w1 = m1.group();
		}

		APP_LOGS.debug("Match --->>> " + w1 + " Candidate(s) Found");

	}

	@Test(priority = 1)
	public void loggedinsearch() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get(baseurl + "/");
		driver.navigate().to(CONFIG.getProperty("testSiteURL"));

		baseClass.login();

		search1();

		String result1 = driver.findElement(By.cssSelector("div.signtab.jdmar")).getText().toString();
		Assert.assertNotEquals(result1, "0 results found for java", "Result expected, but 0 candidates found!");

		APP_LOGS.debug("java search success");
		// search again

		search2();

		Thread.sleep(3000);
		String result2 = driver.findElement(By.cssSelector("div.signtab.jdmar")).getText().toString();
		Assert.assertNotEquals(result2, "0 results found for sales", "Result expected, but 0 candidates found!");

		APP_LOGS.debug("sales search success");

		driver.close();

	}

	public static void search0() throws Exception {
		driver.findElement(By.id("id_q")).clear();
		driver.findElement(By.id("id_q")).sendKeys("python");
		driver.findElement(By.id("id_loc")).clear();
		driver.findElement(By.id("id_loc")).sendKeys("delhi");

		driver.findElement(By.id("id_experience_span")).click();
		driver.findElement(By.id("id_minexp_7")).click();

		driver.findElement(By.cssSelector("a.cls_more_search_option.viewmore")).click();

		driver.findElement(By.id("id_salary_span")).click();
		driver.findElement(By.id("id_minsal_8")).click();

		driver.findElement(By.id("id_area_span")).click();
		driver.findElement(By.id("id_area_10013")).click();

		driver.findElement(By.id("id_industry_span")).click();
		driver.findElement(By.id("id_ind_18")).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("input.ui-btn.search-btn")));

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// search
																				// button
	}

	public static void search1() throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("div.search > a")));
		driver.findElement(By.cssSelector("div.search > a")).click();

		driver.findElement(By.id("id_q")).clear();
		driver.findElement(By.id("id_q")).sendKeys("java");
		driver.findElement(By.id("id_loc")).clear();
		driver.findElement(By.id("id_loc")).sendKeys("delhi");

		driver.findElement(By.id("id_experience_span")).click();
		driver.findElement(By.id("id_minexp_6")).click();

		driver.findElement(By.cssSelector("a.cls_more_search_option.viewmore")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("id_salary_span")).click();
		driver.findElement(By.id("id_minsal_9")).click();

		driver.findElement(By.id("id_area_span")).click();
		driver.findElement(By.id("id_area_10013")).click();

		driver.findElement(By.id("id_industry_span")).click();
		driver.findElement(By.id("id_ind_18")).click();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("input.ui-btn.search-btn")));

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// search
																				// button

	}

	public static void search2() throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("div.search > a")));

		driver.findElement(By.cssSelector("div.search > a")).click();

		driver.findElement(By.id("id_q")).clear();
		driver.findElement(By.id("id_q")).sendKeys("sales");
		driver.findElement(By.id("id_loc")).clear();
		driver.findElement(By.id("id_loc")).sendKeys("delhi");

		driver.findElement(By.id("id_experience_span")).click();
		driver.findElement(By.id("id_minexp_10")).click();

		driver.findElement(By.cssSelector("a.cls_more_search_option.viewmore")).click();

		driver.findElement(By.id("id_salary_span")).click();
		driver.findElement(By.id("id_minsal_12")).click();

		// driver.findElement(By.id("id_area_span")).click();
		// driver.findElement(By.id("id_area_10013")).click();

		driver.findElement(By.id("id_industry_span")).click();
		driver.findElement(By.id("id_ind_15")).click();

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);",
		// driver.findElement(By.cssSelector("input.ui-btn.search-btn")));

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// search
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
