package msite;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.openqa.selenium.support.ui.WebbaseClass.driverWait;
//import org.openqa.selenium.support.ui.WebbaseClass.baseClass.driverWait;
import org.testng.annotations.Test;

public class profilepageadd_edit extends baseClass {

	boolean present;

	@BeforeClass
	public void starttest() throws IOException, InterruptedException {

		setup();
		driver.navigate().to(baseurl);

	}

	@Test
	public void personal_detail() throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseClass.login();

		Thread.sleep(3000);

		driver.get(baseurl + "/");
		driver.findElement(By.xpath("//div[@id='header']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("span.pullLeft.pResTitle")).click();

		driver.findElement(By.id("id_test_pd")).click();

		// driver.findElement(By.id("id_eMail")).clear();
		// driver.findElement(By.id("id_eMail")).sendKeys(baseClass.emailid);
		driver.findElement(By.id("id_CellPhone")).clear();
		driver.findElement(By.id("id_CellPhone")).sendKeys("9876556789");
		driver.findElement(By.id("id_name")).clear();
		driver.findElement(By.id("id_name")).sendKeys("Rohit");
		driver.findElement(By.id("id_location_span")).click();
		driver.findElement(By.id("id_CandidateLocation_453")).click();
		driver.findElement(By.id("id_DateOfBirth")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("22")).click();
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("personal detail updated succesfully");

	}

	@Test(priority = 1)
	public void work_exp() throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.id("id_test_ed")));

		driver.findElement(By.id("id_test_ed")).click();
		driver.findElement(By.id("id_ResumeTitle")).clear();
		driver.findElement(By.id("id_ResumeTitle")).sendKeys("software engineer");

		driver.findElement(By.id("id_experience_year_span")).click();
		driver.findElement(By.id("id_Experience_9")).click();

		driver.findElement(By.id("id_experiene_month_span")).click();
		driver.findElement(By.id("id_Experience_Month_4")).click();

		driver.findElement(By.id("id_team_size_manage_span")).click();
		driver.findElement(By.id("id_TeamSizeManaged_4")).click();

		driver.findElement(By.id("id_salary_lakh_span")).click();
		driver.findElement(By.id("id_SalaryL_8")).click();

		driver.findElement(By.id("id_salary_thousand_span")).click();
		driver.findElement(By.id("id_SalaryT_45")).click();

		driver.findElement(By.id("id_notice_period_span")).click();
		driver.findElement(By.id("id_NoticePeriod_4")).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("work exp updated succesfully");

	}

	@Test(priority = 2)
	public void job_details() throws Exception {

		driver.findElement(By.id("id_test_jd")).click();
		driver.findElement(By.id("id_job_title")).clear();
		driver.findElement(By.id("id_job_title")).sendKeys("test engineer");
		driver.findElement(By.id("id_txt_company_names")).clear();
		driver.findElement(By.id("id_txt_company_names")).sendKeys("infosys");

		Thread.sleep(3000);
		driver.findElement(By.id("id_industry_span")).click();
		// driver.findElement(By.cssSelector("div#id_Industry-button.ui-btn.ui-icon-carat-d.ui-btn-icon-right.ui-corner-all.ui-shadow")).click();
		driver.findElement(By.id("id_Industry_19")).click();

		driver.findElement(By.id("id_area_span")).click();
		driver.findElement(By.id("id_SubField_1312")).click();

		driver.findElement(By.id("id_start_month_span")).click();
		driver.findElement(By.id("id_StartMonth_4")).click();

		driver.findElement(By.id("id_start_year_span")).click();
		driver.findElement(By.id("id_StartYear_2012")).click();

		driver.findElement(By.id("id_IsCurrent")).click();
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button

		APP_LOGS.debug("job detail added succesfully");

	}

	@Test(priority = 3)
	public void jobdetail_Edit() throws Exception {
		/*
		 * List <WebElement> edit =
		 * driver.findElements(By.cssSelector("a#id_test_jde.ui-link"));
		 * 
		 * edit.get(0);
		 */

		driver.findElement(By.id("id_test_jde")).click();
		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_job_title_sts")).clear();
		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_job_title_sts"))
				.sendKeys("test engineer new");

		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_cmp_name_sts")).clear();
		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_cmp_name_sts"))
				.sendKeys("infotech");

		driver.findElement(By.id("id_industry_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[5]/div[2]/ul/li[10]/label")).click();

		driver.findElement(By.id("id_area_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[6]/div[2]/ul/li[19]/label")).click();

		driver.findElement(By.id("id_start_month_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[1]/div[2]/ul/li[8]/label")).click();

		driver.findElement(By.id("id_start_year_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[2]/div[2]/ul/li[7]/label")).click();

		// driver.findElement(By.xpath("html/body/div[2]/div[1]/div[1]/div/div/form/ul/li[5]/div[3]/input")).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button

		APP_LOGS.debug("job detail updated succesfully");

	}

	@Test(priority = 4)
	public void Edu_details() throws Exception {

		driver.findElement(By.xpath("html/body/div[2]/div[5]/div/div[2]/div/div[6]/h2/span/a")).click();

		driver.findElement(By.id("id_qualification_level_span")).click();
		driver.findElement(By.id("id_EducationLevel_80")).click();

		Thread.sleep(2000);

		// driver.findElement(By.id("id_education_specialization_span")).click();
		driver.findElement(By.xpath("html/body/div[2]/div[3]/div/div/div/form/ul/li[2]/ul/li[1]/div/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("id_StudyField_96")).click();

		driver.findElement(By.id("id_institute_name")).clear();
		driver.findElement(By.id("id_institute_name")).sendKeys("iit");

		driver.findElement(By.id("id_year_of_passout_span")).click();
		driver.findElement(By.id("id_YearOfPassout_2010")).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button

		APP_LOGS.debug("Education added succesfully");

	}

	// @Test(priority=5)
	public void Edudetails_Edit() throws Exception {

		driver.findElement(By.id("id_test_eda")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("id_qualification_level_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[1]/div[2]/ul/li[4]/label/input")).click();

		driver.findElement(By.id("id_education_specialization_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[2]/div[2]/ul/li[47]/label")).click();

		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_initSt_sts.firstCharCheck"))
				.clear();
		driver.findElement(By.cssSelector("input.reg_text12.cls_single_autocomplete.cls_initSt_sts.firstCharCheck"))
				.sendKeys("iit");

		driver.findElement(By.id("id_year_of_passout_span")).click();
		driver.findElement(By.xpath("html/body/aside/div[4]/div[2]/ul/li[14]/label")).click();
		// driver.findElement(By.linkText("2007")).click();
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("Education detail updated succesfully");

	}

	@Test(priority = 6)
	public void certification() throws Exception {

		driver.findElement(By.id("id_test_cr")).click();
		driver.findElement(By.id("id_txt_certicification")).clear();
		driver.findElement(By.id("id_txt_certicification")).sendKeys("cer12");
		driver.findElement(By.id("id_certification_year_span")).click();
		driver.findElement(By.id("id_year_2012")).click();
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("certification added succesfully");
	}

	@Test(priority = 7)
	public void certification_edit() throws Exception {

		driver.findElement(By.id("id_test_cra")).click();
		driver.findElement(By.id("id_txt_certicification")).clear();
		driver.findElement(By.id("id_txt_certicification")).sendKeys("cer_edit12");
		driver.findElement(By.id("id_certification_year_span")).click();
		driver.findElement(By.id("id_year_2009")).click();
		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("certification detail updated succesfully");
	}

	@Test(priority = 8)
	public void skills() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("id_test_sk")).click();
		driver.findElement(By.id("id_txt_skills")).clear();
		driver.findElement(By.id("id_txt_skills")).sendKeys("python");
		driver.findElement(By.id("id_skill_year_span")).click();
		driver.findElement(By.id("id_LevelId_5")).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("skill added succesfully");
	}

	@Test(priority = 9)
	public void skills_edit() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.id("id_test_ska")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.reg_text5.cls_single_autocomplete.cls_skill_sts.cls_multi_tooltip"))
				.sendKeys("c++");
		driver.findElement(By.id("id_skill_year_span")).click();
		driver.findElement(By.xpath("html/body/aside/div/div[2]/ul/li[6]/label")).click();

		// driver.findElement(By.linkText("3 Yrs")).click();

		driver.findElement(By.cssSelector("input.ui-btn.search-btn")).click();// save
																				// button
		APP_LOGS.debug("skill detail updated succesfully");

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
