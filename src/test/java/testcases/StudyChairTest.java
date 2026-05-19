package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	@Test
	public void testCase1() throws InterruptedException {
		sc.getStudyChairsURL();
		//System.out.println(studyChairPageURL);
	}

	@Test
	public void testCase2() throws InterruptedException {
		sc.sortByPopularity();	
	}
	
	@Test
	public void testCase3() throws IOException {
		List<String> output = new ArrayList<>();
		output = sc.getTop3Products();
		ex = new ExcelUtils();
		ex.writeData(output, "StudyChair.xlsx");
		
	}
	
	@AfterTest
	public void tearDown() {
		br.close_Browser();
	}
}
