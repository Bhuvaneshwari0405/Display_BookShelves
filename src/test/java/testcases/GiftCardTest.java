package testcases;
 
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browserImplementation.BrowserDriver;
import objectImplementation.ObjectReader;
import pages.GiftCardPage;
 
public class GiftCardTest {
    WebDriver driver;
    BrowserDriver br;
    ObjectReader or;
    GiftCardPage giftCardPage;
    WebDriverWait wait;
    ExtentReports extent;
 
    
    @BeforeTest
    public void setup() throws IOException {
        br = new BrowserDriver();
        driver = br.launch_Browser();
        or = new ObjectReader();
        driver.get(or.get_baseUrl());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/GiftCardPage.html");
		extent.attachReporter(spark);
        giftCardPage = new GiftCardPage(driver, wait);
    }
 
    // Test 1: Navigating to the Gift Cards section
    @Test(priority=1)
    public void testcase1() throws InterruptedException {
        giftCardPage.clickingGiftCards();
        Thread.sleep(3000);
		ExtentTest test = extent.createTest("TestCase 1");
        test.assignAuthor("Divyadarshini");
        test.info("Clicked on GiftCards section");
        test.log(Status.PASS, "Test Passed");
    }
 
    // Test 2: Entering into the Gift Cards page
    @Test(priority=2)
    public void testcase2() throws InterruptedException {
        giftCardPage.enteringintoGiftCardsPage();
        Thread.sleep(3000);
        ExtentTest test = extent.createTest("TestCase 2");
        test.assignAuthor("Divyadarshini");
        test.info("Entered into GiftCard Page");
        test.log(Status.PASS, "Test Passed");
    }
    
    // Test 3: Entering the Form Details
    @Test(priority=3)
    	public void testcase3() throws InterruptedException {        
            giftCardPage.fillingFormDetails();        
            Thread.sleep(3000);    
            ExtentTest test = extent.createTest("TestCase 3");
            test.assignAuthor("Divyadarshini");
            test.info("Form Details are filled");
            test.log(Status.PASS, "Test Passed");
        }
 
    // Test 4: Printing the Error Message    
    @Test(priority=4)
    public void testcase4() throws InterruptedException {
        String msg = giftCardPage.errorMessage();
        System.out.println(msg);
        Thread.sleep(3000);
        ExtentTest test = extent.createTest("TestCase 4");
        test.assignAuthor("Divyadarshini");
        test.info("Error Message: " + msg);
        test.log(Status.PASS, "Test Passed");
    }
    
    // Test 5: Capturing the Error Message
    @Test(priority=5)
    public void testcase5() throws InterruptedException {
        String msg=giftCardPage.CapturingErrorMessage();
        System.out.println(msg);
        Thread.sleep(3000);
        ExtentTest test = extent.createTest("TestCase 5");
        test.assignAuthor("Divyadarshini");
        test.info("Captured Error Message");
        test.addScreenCaptureFromPath("C:\\Users\\2487361\\eclipse-workspace\\myworkspace\\com.finalproject.bookshelves\\Screenshots\\Error Message.png");
        test.log(Status.PASS, "Test Passed");
        
    }
    
    @AfterTest
    public void teardown() {
    	extent.flush();
        driver.quit();
    }
}