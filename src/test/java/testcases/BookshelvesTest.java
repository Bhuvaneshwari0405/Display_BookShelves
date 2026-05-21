package testcases;
 
import java.io.IOException;
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.Status;
 
import browserImplementation.BrowserDriver;
import objectImplementation.ObjectReader;
import pages.BookshelfPage;
import utils.ExcelUtils;
 
public class BookshelvesTest {
 
	private BookshelfPage bookpage ;
	private ExcelUtils excel;
	private BrowserDriver browserDriver;
	private ObjectReader objectReader;
	private WebDriver driver;
	private ExtentReports extent;
	
	@BeforeTest
	public void setup() throws IOException {
		
		//Initializing browser driver and utilities  
		browserDriver = new BrowserDriver();
		objectReader = new ObjectReader();
		excel = new ExcelUtils();
		
		// Initialize ExtentReports and attach the Spark HTML reporter
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/BookshelfReport.html");
		spark.config().setReportName("Bookshelf Test Report");
        spark.config().setDocumentTitle("Bookshelf Automation Results");
		extent.attachReporter(spark);
		
		
		// Set system info visible in the report
		extent.setSystemInfo("Tester", "Ishaa");
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Application", "Urban Ladder");
 
		
		//launch the browser and navigate to the application url
		driver = browserDriver.launch_Browser();
		driver.get(objectReader.get_baseUrl());
		
		//Maximizing the browser window
		driver.manage().window().maximize();
		
		//Initializing the Bookshelf Page with the actual driver
		bookpage = new BookshelfPage(driver);
	
	}
 
	@Test(priority=1)
	public void verifyNavigationToBookshelves() {
		
		// Create a test node in the ExtentReport
		ExtentTest test = extent.createTest("TC01 - Verify Navigation To Bookshelves");
		test.assignAuthor("Ishaa");
		test.assignCategory("Navigation");
		
		try {
            // Search for bookshelves using the search bar
            bookpage.searchBookShelves();
 
            // Log success in the report
            test.log(Status.PASS, "Successfully searched for Bookshelves.");
 
        } catch (Exception e) {
            // Log failure with the error message in the report
            test.log(Status.FAIL, "Navigation to Bookshelves failed: " + e.getMessage());
        }
 
	}
 
	@Test(priority=2)
	public void verifyPriceFilterBelow15000() throws InterruptedException {
		
		// Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC02 - Verify Price Filter Below 15000");
        test.assignCategory("Filter");
        test.assignAuthor("Ishaa");
 
        try {
            // Open the filter panel
            bookpage.clickFilter();
            test.log(Status.INFO, "Filter panel opened successfully.");
 
            // Apply price filter below Rs.15000
            bookpage.applyPriceFilter();
            test.log(Status.PASS, "Price filter below Rs.15000 applied successfully.");
 
        } catch (Exception e) {
            // Log failure with the error message in the report
            test.log(Status.FAIL, "Price filter application failed: " + e.getMessage());
        }
 
	}
 
	@Test(priority=3)
	public void verifyStorageTypeFilter() {
 
		// Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC03 - Verify Storage Type Filter");
        test.assignAuthor("Ishaa");
        test.assignCategory("Filter");
 
        try {
            // Apply the open storage type filter
            bookpage.applyStorageTypeFilter();
            test.log(Status.PASS, "Storage Type filter (Open Storage) applied successfully.");
 
        } catch (Exception e) {
            // Log failure with the error message in the report
            test.log(Status.FAIL, "Storage Type filter failed: " + e.getMessage());
        }
 
	}
	
	@Test(priority=4)
	public void verifyOutOfStockProductsExcluded() throws InterruptedException {
		
        // Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC04 - Verify Out Of Stock Products Excluded");
        test.assignCategory("Filter");
        test.assignAuthor("Ishaa");
 
        try {
            // Check whether the "Out of Stock" checkbox is selected (true) or not (false)
            boolean result = bookpage.checkOutOfStockExcluded();
 
            // Assert that the "Out of Stock" checkbox is NOT selected
            // false = out-of-stock excluded (PASS), true = included (FAIL)
            Assert.assertFalse(result,
                "Out-of-stock products are visible in the listing. They should be excluded.");
 
            test.log(Status.PASS,
                "Out-of-stock products are correctly excluded. Checkbox is NOT selected.");
 
        } catch (AssertionError e) {
            // Log assertion failure — out-of-stock checkbox was selected
            test.log(Status.FAIL,
                "Out-of-stock products are visible in listing: " + e.getMessage());
            throw e; // rethrow so TestNG marks the test as failed
 
        } catch (Exception e) {
            // Log unexpected error
            test.log(Status.FAIL,
                "Unexpected error in out-of-stock check: " + e.getMessage());
        }
 
	
	}
 
	@Test(priority=5)
	public void verifysubmitFilters() {
		
		// Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC05 - Verify Submit Filters");
        test.assignCategory("Filter");
        test.assignAuthor("Ishaa");
 
        try {
            // Submit all the selected filters
            bookpage.submitFilters();
            test.log(Status.PASS, "All filters submitted successfully.");
 
        } catch (Exception e) {
            // Log failure with the error message in the report
            test.log(Status.FAIL, "Filter submission failed: " + e.getMessage());
        }
	}
 
	@Test(priority=6)
	public void verifyDisplayedProducts(){
		
        // Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC06 - Verify Displayed Products");
        test.assignCategory("Product Listing");
        test.assignAuthor("Ishaa");
 
        try {
            // Fetch the filtered product details from the results page
            List<String> result = bookpage.displayProductDetails();
 
            // Assert that the result list is not empty after applying filters
            Assert.assertTrue(result.size() > 0,
                "No products displayed after applying filters.");
            test.log(Status.INFO, "Products found after filtering: " + result.size());
 
            // Assert that at least 3 products are returned
            Assert.assertTrue(result.size() >= 3,
                "Less than 3 products displayed. Expected at least 3.");
 
            // Print and log each product detail
            System.out.println("──── Top 3 Filtered Products ────");
            for (int i = 0; i < 3; i++) {
                System.out.println("[" + (i + 1) + "] " + result.get(i));
                test.log(Status.INFO, "Product [" + (i + 1) + "]: " + result.get(i));
            }
 
            test.log(Status.PASS, "Filtered products displayed and verified successfully.");
 
        } catch (AssertionError e) {
            // Log assertion failure in the report
            test.log(Status.FAIL, "Product display verification failed: " + e.getMessage());
            throw e; // rethrow so TestNG marks the test as failed
 
        } catch (Exception e) {
            // Log unexpected error
            test.log(Status.FAIL, "Unexpected error in product display: " + e.getMessage());
        }
	    	
 
	}
	
	@Test(priority=7)
	public void getTop3Products() throws IOException {
		
		// Create a test node in the ExtentReport
        ExtentTest test = extent.createTest("TC07 - Get Top 3 Products and Write to Excel");
        test.assignCategory("Reporting");
        test.assignAuthor("Ishaa");
 
        try {
            // Fetch the filtered product details from the results page
            List<String> result = bookpage.displayProductDetails();
 
            // Write the filtered product details into the Excel report file
            String path = System.getProperty("user.dir") + "/Output-Data/BookShelves.xlsx";
            excel.writeData(result, path);
     
    		test.log(Status.INFO, "Excel file attached: " + "<a href='../Output-Data/BookShelves.xlsx'>Click to download</a>");
 
        } catch (Exception e) {
            // Log failure with the error message in the report
            test.log(Status.FAIL, "Failed to write product details to Excel: " + e.getMessage());
        }
        
	}
 
	@AfterTest
	public void tear_down() {
		
		extent.flush();
		//Quit the browser and terminate the webdriver session
		driver.quit();
 
	}
 
}