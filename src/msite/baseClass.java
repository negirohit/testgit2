package msite;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class baseClass {

	public static WebDriver driver;
	public static Date date;
	public static DateFormat dateFormat;
	public static String dynamicUser;
	public static Properties OR;
	public static Properties CONFIG;
	public static String baseurl;
	public static Logger APP_LOGS = Logger.getLogger("devpinoyLogger");

	public static String emailid = "";
	public static String emailid1 = "";

	// @BeforeClass
	public static void setup() throws IOException, InterruptedException {

		CONFIG = new Properties();
		FileInputStream cn = new FileInputStream(System.getProperty("user.dir") + "/config/Config.properties");
		CONFIG.load(cn);

		// APPLICATION_LOGS.debug("Properties loaded.");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/config/log4j.properties");

		baseurl = CONFIG.getProperty("testSiteURL");

		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Google Nexus 5");

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		String chromedriver = CONFIG.getProperty("chromedriverpath");

		System.setProperty("webdriver.chrome.driver", chromedriver);
		driver = new ChromeDriver(capabilities);

		/*
		 * FirefoxProfile ffProfile = new FirefoxProfile();
		 * ffProfile.addExtension(new
		 * File(CONFIG.getProperty("agentswitcher"))); String samsung3 =
		 * "Mozilla/5.0 (Linux; U; Android 4.0.3; de-de; Galaxy S II Build/GRJ22) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
		 * ; ffProfile.setPreference("general.useragent.override", samsung3);
		 */

		// driver = new FirefoxDriver(ffProfile);

		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(366, 640));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		APP_LOGS.debug("Started the mobiletest");
		APP_LOGS.debug(CONFIG.getProperty("testSiteURL"));
		driver.navigate().to(CONFIG.getProperty("testSiteURL"));

	}

	public static void login() throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.linkText("Sign In")));
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(baseClass.emailid);

		// driver.findElement(By.name("email")).sendKeys("rohitnegi2345@gmail.com");
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
			//git push check
		}
	}

	// @AfterClass
	public void CloseDriver() throws Exception {
		driver.quit();

	}

}
