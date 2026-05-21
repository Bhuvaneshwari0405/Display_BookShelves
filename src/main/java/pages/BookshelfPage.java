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
	//WebDriver instance
	private WebDriver driver;
	//Explicit wait object with a 30-second timeout for all element conditions.
	private WebDriverWait wait;
	//Locator for the search input textbox (identified by ID )
	By search_textbox = By.id("searchInput");
	//Locator for the filter toggle button on the product listing page.
	By filter_icon = By.xpath("//*[@id=\"productsContainer\"]/div[2]");
	//Locator for the Price filter header inside the filter panel.
	By price_filter = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[2]/div[16]/div");
	//Locator for the Maximum Price input field inside the expanded price filter.
	By maxPrice = By.xpath("//*[@id=\"inputfields\"]/div[2]/div/div[2]/div[2]/input");
	//Locator for the Storage Type filter element header inside the filter panel.
	By storageType = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[2]/div[4]/div");
	//Locator for the "Open Storage" option inside the Storage Type filter.
	By openStorage = By.xpath("//*[@id=\"accordion-panel-storage-type\"]/div/div[2]");
	//Locator for the Availability header inside the filter panel.
	By availability = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[2]/div[18]/div[1]/span[1]");
	//Locator for the "Out of Stock" checkbox 
	By outOfStock = By.xpath("//*[@id=\"accordion-panel-availability\"]/div/div/p/div/div[1]");
	//Locator for the Apply Filters button at the bottom of the filter panel.
	By submitFilter = By.xpath("//*[@id=\"app\"]/div/div[5]/div/div[3]/div[1]/div[1]/div/div[3]/div/div/div[3]/button[2]");
	//Locator for product name elements on the filtered results page.
	By productName = By.className("XxwSy");
	//Locator for product price elements on the filtered results page.
	By productPrice = By.className("UYQNp");

	//Initializes the BookshelfPage with the given WebDriver Instance 
	public BookshelfPage(WebDriver driver){
		this.driver = driver;
		//Initialize explicit wait with a 30 second timeout for dynamic elements
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(50));
	}
	//Searches for BookShelves using the search bar 
	public void searchBookShelves() {
		try {
			//Find the search bar and wait until it is ready to get input 
			WebElement searchbox = driver.findElement(search_textbox);
			wait.until(ExpectedConditions.elementToBeClickable(searchbox));
			//Click the textbox, type the search term and press ENTER to submit.
			searchbox.click();
			searchbox.sendKeys("Bookshelves"+Keys.ENTER);
		}
		catch(Exception e ){
			//Log any error that prevents the search 
			System.out.println("Error in searchBookShelves: " + e.getMessage());
		}
	}
	//Clicks the filter icon to open the filter sidebar panel on the product page.
	public void clickFilter() {
		try {
			//Wait for the filter icon to be clickable, then open the filter panel 
			WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filter_icon));
			filter.click();
		}
		catch(Exception e){
			//Log any error that prevents the filter panel form opening
			System.out.println("Error in clickFilter: " + e.getMessage());
		}
	}
	//Expands the Price filter section and enters Rs.15000 as the maximum price 
	public void applyPriceFilter() throws InterruptedException {
		try {
			//Locate the price filter and wait unitl clickable 
			WebElement price = driver.findElement(price_filter);
			wait.until(ExpectedConditions.elementToBeClickable(price));
			//Scroll the price filter into view uisng JavaScriptExecutor 
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", price);
			Thread.sleep(500); // small pause after scroll
			price.click();
 
			//Wait for the scroll animation to complete before clicking 
			WebElement MaxPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(maxPrice));
			//Clear any existing value and enter the maximum price 
			MaxPrice.clear();
			MaxPrice.sendKeys("15000");
		}
		catch(Exception e){
			//Log any error that prevents the price filter from being applied 
			System.out.println("Error in applyPriceFilter: " + e.getMessage());
		}
	}
	//Expands the Storage Type filter and selects the "Open Storage" option.
	public void applyStorageTypeFilter() {
		try {
			// Click the Storage Type element header to expand the filter options
			WebElement storagetype = wait.until(ExpectedConditions.elementToBeClickable(storageType));
			storagetype.click();
			// Wait for the "Open Storage" option to appear, then select it
			WebElement storage = wait.until(ExpectedConditions.elementToBeClickable(openStorage));
			storage.click();
		}
		catch(Exception e){
			// Log any error that prevents the storage type filter from being applied
			System.out.println("Error in applyStorageTypeFilter: " + e.getMessage());
		}
 
		
	}
	public boolean checkOutOfStockExcluded() throws InterruptedException {
		WebElement availabilityEl = driver.findElement(availability);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", availabilityEl);
        Thread.sleep(500); // small pause to let scroll animation settle
        availabilityEl.click();
 
        // ── Step 6: Check whether Out of Stock checkbox is selected ──
        WebElement outOfStockBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(outOfStock));
        boolean isChecked = outOfStockBox.isSelected();
        System.out.println("Out of Stock checkbox selected: " + isChecked);
        return isChecked;
	}

	//Submits all selected filters by clicking the Apply  button.
	public void submitFilters() {
		try {
			// Wait for the submit button to be interactable, then click to apply filters
			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitFilter));
			submit.click();
		}
		catch(Exception e){
			// Log any error that prevents filter submission
			System.out.println("Error in submitFilters: " + e.getMessage());
		}
	}
	//Retrieves and returns the name and price of the first 3 products displayed on the filtered results page.
	public List<String> displayProductDetails() {
		// Initialize an empty list to store product detail strings
		List<String> output = new ArrayList<>();
		try {
			// Wait until all product name elements are visible on the results page
			List<WebElement> productNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productName));
			// Wait until all product price elements are visible on the results page
			List<WebElement> productPrices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrice));
			// Loop through only the first 3 products to collect name and price
			for(int i=0;i<3;i++) {
				String name = productNames.get(i).getText();
				String price = productPrices.get(i).getText();
				// Add the formatted product detail string to the output list
				output.add(name+"----"+price);
			}
		}
		catch(Exception e){
			// Log any error that prevents product details from being collected
			System.out.println("Error in displayProductDetails: " + e.getMessage());
		}
		// Return the list of collected product details 
		return output;
	}
}