package testcases;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import browserImplementation.BrowserDriver;
import objectImplementation.ObjectReader;
import pages.CollectionsPage;
import utils.ExcelUtils;
 
public class CollectionsTest {
 
    WebDriver driver;
    BrowserDriver br;
    ObjectReader or;
    CollectionsPage cp;
    List<WebElement> list;
    ExcelUtils ex;
    ExtentReports extent;
    
    
    //opening the website
    @BeforeTest
    public void setUp() throws IOException {
        br = new BrowserDriver();
        driver = br.launch_Browser();
        driver.manage().window().maximize();
        or = new ObjectReader();
        driver.get(or.get_baseUrl());
        cp = new CollectionsPage(driver);
        extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/collections.html");
		extent.attachReporter(spark);
        
        
    }
    
    //furnishing clicked
    @Test(priority = 1)
    public void verifyNavigation() {
        cp.clickFurnishingMenu();
        System.out.println("Furnishing clicked");
        ExtentTest test = extent.createTest("Testcase 1");
		test.assignAuthor("Akshitha");
		test.info("furnishing clicked");
		System.out.println("Test case");
    }
 
    //to check whether the submenu is visible or not
    @Test(priority = 2, dependsOnMethods = "verifyNavigation")
    public void verifySubmenuVisible() {
        list = cp.getSubMenuElements();
        ExtentTest test = extent.createTest("Testcase 2");
		test.assignAuthor("Akshitha");
        Assert.assertTrue(list.size() > 0,"list is empty");
        test.log(Status.PASS, "List is not empty");
        System.out.println("Submenu visible");
    }
 
   //taking list of elements from the submenu
    @Test(priority = 3, dependsOnMethods = "verifySubmenuVisible")
    public void extractItems() {
        cp.printSubMenuItems();
        ExtentTest test = extent.createTest("Testcase 3");
		test.assignAuthor("Akshitha");
		test.info("verified submenu elements");
        System.out.println("Items extracted");
    }
 
   //printing the items in the console and excel sheet
    @Test(priority = 4, dependsOnMethods = "extractItems")
    public void storeItems() throws IOException {
        List<String> storedList = cp.getStoredMenuList();
        ExtentTest test = extent.createTest("Testcase 4");
		test.assignAuthor("Akshitha");
        Assert.assertNotNull(storedList);
        Assert.assertTrue(list.size() > 0);
        ex = new ExcelUtils();//getting data into the excel using excelutils
        String path = System.getProperty("user.dir") + "/Output-Data/Collections.xlsx";
        ex.writeData(storedList, path);
        test.log(Status.INFO, "Excel file attached: " + "<a href='../Output-Data/Collections.xlsx'>Click to download</a>");
        test.log(Status.PASS, "items printed");
        System.out.println("Items stored successfully");
    }
    //quitting the browser
    @AfterTest
    public void tearDown() {
    	extent.flush();
        driver.quit();
    }
}