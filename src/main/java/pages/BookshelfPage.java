package pages;
 
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class BookshelfPage {

	private WebDriver driver;
	private WebDriverWait wait;

	By search_textbox = By.id("searchInput");
	
	By filter_icon = By.xpath("//*[@id=\"productsContainer\"]/div[2]");
	
	By price_filter = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[2]/div[16]/div");
	
	By maxPrice = By.xpath("//*[@id=\"inputfields\"]/div[2]/div/div[2]/div[2]/input");
	
	By storageType = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[2]/div[4]/div");
	
	By openStorage = By.xpath("//*[@id=\"accordion-panel-storage-type\"]/div/div[2]");
	
	By submitFilter = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[3]/button[2]");
	
	By productName = By.className("XxwSy");
	
	By productPrice = By.className("UYQNp");
 
	

	public BookshelfPage(WebDriver driver){
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
	}
	public void searchBookShelves() {
		
		try {
			WebElement searchbox = driver.findElement(search_textbox);
			wait.until(ExpectedConditions.elementToBeClickable(searchbox));
			searchbox.click();
			searchbox.sendKeys("Bookshelves"+Keys.ENTER);
		}
		catch(Exception e ){
			System.out.println("Error in searchBookShelves: " + e.getMessage());
		}
		
	}
	public void clickFilter() {
		
		try {
			WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filter_icon));
			filter.click();
		}
		catch(Exception e){
			System.out.println("Error in clickFilter: " + e.getMessage());
		}
		
	}
	public void applyPriceFilter() throws InterruptedException {
		
		try {
			WebElement price = driver.findElement(price_filter);
			wait.until(ExpectedConditions.elementToBeClickable(price));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", price);
			Thread.sleep(500); // small pause after scroll
			price.click();

			WebElement MaxPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(maxPrice));
			MaxPrice.clear();
			MaxPrice.sendKeys("15000");
		}
		catch(Exception e){
			System.out.println("Error in applyPriceFilter: " + e.getMessage());
		}
		
	}
	public void applyStorageTypeFilter() {
		
		try {
			WebElement storagetype = wait.until(ExpectedConditions.elementToBeClickable(storageType));
			storagetype.click();
			WebElement storage = wait.until(ExpectedConditions.elementToBeClickable(openStorage));
			storage.click();
		}
		catch(Exception e){
			System.out.println("Error in applyStorageTypeFilter: " + e.getMessage());
		}

		
	}
	public void submitFilters() {
		
		try {
			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitFilter));
			submit.click();
		}
		catch(Exception e){
			System.out.println("Error in submitFilters: " + e.getMessage());
		}
		
	}
	public List<String> displayProductDetails() {
		
		List<String> output = new ArrayList<>();
		try {
			List<WebElement> productNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productName));
			List<WebElement> productPrices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrice));
			
			for(int i=0;i<3;i++) {
				String name = productNames.get(i).getText();
				String price = productPrices.get(i).getText();
				System.out.println(name+"----"+price);
				output.add(name+"----"+price);
			}
		}
		catch(Exception e){
			System.out.println("Error in displayProductDetails: " + e.getMessage());
		}
		
		return output;
	}
 
}