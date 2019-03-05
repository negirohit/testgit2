package msite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class registration_completion_page extends baseClass {

	@BeforeClass
	public void starttest() throws Exception, IOException, InterruptedException {

		setup();
		driver.navigate().to(baseurl);
		Thread.sleep(2000);
		baseClass.login();
	}

	@Test
	public void personal_detail_registration_completion() throws Exception {

		// baseClass.login();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_doItLater")));

		driver.findElement(By.id("id_doItLater")).click();// i ll do it later
															// button in resume
															// upload page

		try {
			// Try block to handle code that may cause exception
			driver.findElement(By.cssSelector("a.cls_link.link")).click();
		} catch (Exception e) {
			// This block is to catch divide-by-zero error
			System.out.println("no app pop up");
		}

		driver.findElement(By.id("id_profile_title")).clear();
		driver.findElement(By.id("id_profile_title")).sendKeys("shine test");
		driver.findElement(By.id("id_gender_0")).click();

		try {
			// job experience salary field
			// driver.findElement(By.id("id_is_fresher_1")).click();
			driver.findElement(By.id("id_salary_lakh_span")).click();
			driver.findElement(By.id("id_salary_in_lakh_5")).click();
			driver.findElement(By.id("id_salary_in_thousand_span")).click();
			driver.findElement(By.id("id_salary_in_thousand_20")).click();

		} catch (Exception e) {
			// This block is to catch divide-by-zero error
			System.out.println("fresher personal detail");
		}

	}

	@Test(priority = 1)
	public void job_detail_registration_completion() throws Exception {

		try {

			driver.findElement(By.id("id_job__job_title")).clear();
			driver.findElement(By.id("id_job__job_title")).sendKeys("Software Developer");
			// driver.findElement(By.id("ui-id-14")).click();

			driver.findElement(By.id("id_job__company_name")).clear();
			driver.findElement(By.id("id_job__company_name")).sendKeys("India Infoline Ltd");

			driver.findElement(By.id("id_industry_span")).click();
			driver.findElement(By.id("id_job__industry_id_19")).click();

			driver.findElement(By.id("id_area_span")).click();
			driver.findElement(By.id("id_job__sub_field_1301")).click();

			driver.findElement(By.id("id_start_month_span")).click();
			driver.findElement(By.id("id_job__start_month_5")).click();

			driver.findElement(By.id("id_start_year_span")).click();
			driver.findElement(By.id("id_job__start_year_2011")).click();

			driver.findElement(By.id("id_job__is_current")).click();

		} catch (Exception e) {

			System.out.println("frsher candidate");

		}
	}

	@Test(priority = 2)
	public void education_detail_registration_completion() throws Exception {

		driver.findElement(By.id("id_qualification_level_span")).click();
		driver.findElement(By.id("id_education__education_level_50")).click();

		driver.findElement(By.id("id_education_specialization_span")).click();
		driver.findElement(By.id("id_education__education_specialization_145")).click();

		driver.findElement(By.id("id_education__institute_name")).clear();
		driver.findElement(By.id("id_education__institute_name")).sendKeys("IIIT Hyderabad");

		driver.findElement(By.id("id_year_of_passout_span")).click();
		driver.findElement(By.id("id_education__year_of_passout_2012")).click();

	}

	@Test(priority = 3)
	public void skill_detail_registration_completion() throws Exception {

		driver.findElement(By.id("id_skills-0-value")).clear();
		driver.findElement(By.id("id_skills-0-value")).sendKeys("java");
		Thread.sleep(2000);
		driver.findElement(By.id("id_years_of_experience_0_span")).click();
		driver.findElement(By.id("id_skills-0-years_of_experience_6")).click();

		Thread.sleep(4000);
		// driver.findElement(By.cssSelector("a.add-row.ui-link")).click();

		/*
		 * driver.findElement(By.id("skills-0-id_skills-0-value")).clear();
		 * driver.findElement(By.id("skills-0-id_skills-0-value")).sendKeys("c")
		 * ;
		 * 
		 * driver.findElement(By.id(
		 * "skills-0-id_skills-0-years_of_experience-button")).click();
		 * 
		 * Thread.sleep(2000);
		 * driver.findElement(By.id("id_skills-0-years_of_experience_7")).click(
		 * );
		 */

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector("input.ui-btn.search-btn")));
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();

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
