package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browserImplementation.BrowserDriver;
import objectImplementation.ObjectReader;
import pages.StudyChairPage;
import utils.ExcelUtils;

public class StudyChairTest {
	
	WebDriver driver;
	BrowserDriver br;
	ObjectReader or;
	StudyChairPage sc;
	WebDriverWait wait;
	ExcelUtils ex;
	ExtentReports extent;
	
	//Opens the browser and the webpage is opened
	@BeforeTest
	public void setUp() throws InterruptedException, IOException {
		br = new BrowserDriver();
		driver = br.launch_Browser();
		driver.manage().window().maximize();
		Thread.sleep(3000);		
		or = new ObjectReader();
		driver.get(or.get_baseUrl());	
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/StudyChairReport.html");
		extent.attachReporter(spark);
		sc = new StudyChairPage(driver, wait);
	}
	
	//The verification of study chairs page is opened
	@Test
	public void testCase1() throws InterruptedException {
		ExtentTest test = extent.createTest("Test Case 1");
		test.assignAuthor("Bhuvaneshwari");
		String actualURL = sc.getStudyChairsURL();
		String expectedURL = "https://www.urbanladder.com/collection/study-chairs";
		System.out.println("The Study chair URL: " + actualURL);
		test.info("The url is : " + actualURL);
		Assert.assertEquals(actualURL, expectedURL);
		test.log(Status.PASS, "The actual URL matches the expected URL");
		
	}
	
	//Verifying the title of Study chair webpage
	@Test
	public void testCase2() {
		ExtentTest test = extent.createTest("Test Case 2");
		test.assignAuthor("Bhuvaneshwari");
		String title = sc.verifyTitleOfStudyChairs();
		String expectedTitle = "Buy Study Chairs Online at Upto 70% Off in India";
		System.out.println("Actual Title: " + title);
		test.info("The url is : " + title);
		Assert.assertEquals(title, expectedTitle);
		test.log(Status.PASS, "The actual title matches the expected title");
		
	}
	

	//Sorting the study chairs based on popularity
	@Test
	public void testCase3() throws InterruptedException {
		ExtentTest test = extent.createTest("Test Case 3");
		test.assignAuthor("Bhuvaneshwari");
		String actualURL = sc.sortByPopularity();
		String expectedURL = "https://www.urbanladder.com/collection/study-chairs?sortOn=popular";
		System.out.println("The Study chair URL: " + actualURL);
		test.info("The url is : " + actualURL);
		Assert.assertEquals(actualURL, expectedURL);
		test.log(Status.PASS, "The actual URL matches the expected URL");
	}
	
	//Returns the top 3 study chairs
	@Test
	public void testCase4() throws IOException {
		ExtentTest test = extent.createTest("Test Case 4");
		test.assignAuthor("Bhuvaneshwari");
		List<String> output = new ArrayList<>();
		output = sc.getTop3Products();
		ex = new ExcelUtils();
		String path = System.getProperty("user.dir") + "/Output-Data/StudyChair.xlsx";
		ex.writeData(output, path);
		test.info("The output is written into Excel");
		test.log(Status.PASS, "Excel file attached: " + "<a href='../Output-Data/StudyChair.xlsx'>Click to download</a>");

		
	}
	
	//The browser is closed
	@AfterTest
	public void tearDown() {
		extent.flush();
		br.close_Browser();
	}
}
