package testcases;
 
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.BookshelfPage;
import utils.ExcelUtils;
 
public class BookshelvesTest {

	private BookshelfPage pg ;
	private ExcelUtils excel;

	WebDriver driver;

	@Test(priority=1)

	public void verifyNavigationToBookshelves() {

		driver = new ChromeDriver();

		driver.get("https://www.urbanladder.com/");
		
		excel = new ExcelUtils();

		driver.manage().window().maximize();

		pg = new BookshelfPage(driver);

		pg.searchBookShelves();

	}

	@Test(priority=2)

	public void verifyPriceFilterBelow15000() throws InterruptedException {

		pg.clickFilter();

		pg.applyPriceFilter();

	}

	@Test(priority=3)

	public void verifyStorageTypeFilter() {

		pg.applyStorageTypeFilter();

	}

	@Test(priority=4)

	public void verifysubmitFilters() {

		pg.submitFilters();

	}

	@Test(priority=5)

	public void verifyDisplayedProducts() throws IOException {

		List<String> result = pg.displayProductDetails();
		excel.writeData(result, "BookShelves.xlsx");
		

	}

	@AfterTest

	public void tear_down() {

		driver.quit();

	}

}

 