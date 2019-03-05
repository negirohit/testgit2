package msite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class resume_upload extends baseClass {

	public static Properties CONFIG;
	public static WebDriver driver1;
	public static String baseUrl;
	public static Logger APP_LOGS = Logger.getLogger("devpinoyLogger");

	@BeforeClass
	public void setUp() throws Exception {
		driver1 = new FirefoxDriver();

		CONFIG = new Properties();
		FileInputStream cn = new FileInputStream(System.getProperty("user.dir") + "/config/Config.properties");
		CONFIG.load(cn);

		// APPLICATION_LOGS.debug("Properties loaded.");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/config/log4j.properties");

		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public static void testResumeupload() throws Exception {

		driver1.get(CONFIG.getProperty("testSiteUrl2"));

		Thread.sleep(5000);

		driver1.manage().window().maximize();

		// shineweb.Base.midoutloginnew(baseClass.emailid);
		// =============================================
		driver1.findElement(By.linkText("Login")).click();

		Thread.sleep(2000);

		driver1.findElement(By.id("id_email_login")).clear();
		driver1.findElement(By.id("id_email_login")).sendKeys(baseClass.emailid);
		driver1.findElement(By.id("id_password")).clear();
		driver1.findElement(By.id("id_password")).sendKeys("password");
		Thread.sleep(2000);

		driver1.findElement(By.name("login_popup")).click();
		try {
			Assert.assertNotEquals(driver1.getTitle(), "Shine.com - My Shine | Login");
		} catch (Exception e) {
			driver1.findElement(By.id("id_email_login")).clear();
			driver1.findElement(By.id("id_email_login")).sendKeys(baseClass.emailid);
			driver1.findElement(By.id("id_password")).clear();
			driver1.findElement(By.id("id_password")).sendKeys("password");
			Thread.sleep(2000);

			driver1.findElement(By.name("login_popup")).click();

		}

		// ===================================

		APP_LOGS.debug("login successfully");
		// driver.findElement(By.cssSelector("span.ui-icon.ui-icon-closethick")).click();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver1.findElement(By.id("id_file")).sendKeys(CONFIG.getProperty("resume"));
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver1.findElement(By.cssSelector("input#button.submitred.resumeupload")).click();
		driver1.close();
		APP_LOGS.debug("resumeuploaded");

	}

	@Override
	@AfterClass
	public void CloseDriver() throws Exception {
		driver1.quit();

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {

			File scrFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("scrfolder") + testResult.getName() + ".jpg"));

		}
	}
}
