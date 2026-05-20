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
	
	//Opens the browser and the webpage is opened
	@BeforeTest
	public void setUp() throws InterruptedException, IOException {
		br = new BrowserDriver();
		driver = br.launch_Browser();
		driver.manage().window().maximize();
		Thread.sleep(3000);		
		or = new ObjectReader();
		driver.get(or.get_baseUrl());
		Thread.sleep(3000);		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		sc = new StudyChairPage(driver, wait);
	}
	
	//The verification of study chairs page is opened
	@Test
	public void testCase1() throws InterruptedException {
		String actualURL = sc.getStudyChairsURL();
		String expectedURL = "https://www.urbanladder.com/collection/study-chairs";
		System.out.println("The Study chair URL: " + actualURL);
		Assert.assertEquals(actualURL, expectedURL);
	}
	
	//Verifying the title of Study chair webpage
	@Test
	public void testCase2() {
		String title = sc.verifyTitleOfStudyChairs();
		String expectedTitle = "Buy Study Chairs Online at Upto 70% Off in India";
		System.out.println("Actual Title: " + title);
		Assert.assertEquals(title, expectedTitle);
	}

	//Sorting the study chairs based on popularity
	@Test
	public void testCase3() throws InterruptedException {
		String actualURL = sc.sortByPopularity();
		String expectedURL = "https://www.urbanladder.com/collection/study-chairs?sortOn=popular";
		System.out.println("The Study chair URL: " + actualURL);
		Assert.assertEquals(actualURL, expectedURL);
	}
	
	//Returns the top 3 study chairs
	@Test
	public void testCase4() throws IOException {
		List<String> output = new ArrayList<>();
		output = sc.getTop3Products();
		ex = new ExcelUtils();
		ex.writeData(output, "StudyChair.xlsx");
		
	}
	
	//The browser is closed
	@AfterTest
	public void tearDown() {
		br.close_Browser();
	}
}
