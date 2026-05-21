package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import utils.ScreenshotUtils;
 
public class GiftCardPage {
	WebDriver driver;
	WebDriverWait wait;
	
	By gitCard = By.className("ETrkE");
	By amount = By.id("denomination");
	By quantity = By.id("quantity");
	By firstName=By.xpath("//*[@id='firstname'][1]");
	By lastName=By.xpath("//*[@id='lastname'][1]");
	By email=By.xpath("//*[@id='email'][1]");
	By mobile=By.xpath("//*[@id='telephone']");
	By firstName2=By.xpath("(//*[@id='firstname'])[2]");
	By lastName2=By.xpath("(//*[@id='lastname'])[2]");
	By email2=By.xpath("(//*[@id=\"email\"])[2]");
	By message=By.id("giftMessage");
	By error=By.cssSelector("div.invalid-address.text-danger");
	
	public GiftCardPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	//Clicking on the Giftcard
	public void clickingGiftCards() {
		WebElement gc = driver.findElement(gitCard);
		gc.click();
 
	}
	//Entering into Giftcard Page
	public void enteringintoGiftCardsPage() {
		driver.manage().window().maximize();
		String mainWindow = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
 
	}
	
	//Entering the Details
	public void fillingFormDetails() throws InterruptedException {
		WebElement amountElement = wait.until(ExpectedConditions.elementToBeClickable(amount));
		amountElement.click();
 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", amountElement);
		Thread.sleep(2000);
		amountElement.sendKeys("1000");
		
		WebElement quantityElement = wait.until(ExpectedConditions.elementToBeClickable(quantity));
		quantityElement.click();
		quantityElement.sendKeys("10");
		Thread.sleep(3000);
		
		WebElement firstNameElement = wait.until(ExpectedConditions.elementToBeClickable(firstName));
		js.executeScript("arguments[0].scrollIntoView(true);", firstNameElement);
		firstNameElement.click();
		firstNameElement.sendKeys("Divyadarshini");
		Thread.sleep(3000);
 
		WebElement lastNameElement = wait.until(ExpectedConditions.elementToBeClickable(lastName));
		lastNameElement.click();
		lastNameElement.sendKeys("A");
		Thread.sleep(3000);
 
		WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(email));
		emailElement.click();
		emailElement.sendKeys("divyadarshinigmail.com");
		emailElement.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
 
		WebElement mobileElement = wait.until(ExpectedConditions.elementToBeClickable(mobile));
		js.executeScript("arguments[0].scrollIntoView(true);", mobileElement);
		mobileElement.click();
		mobileElement.sendKeys("9444487180");
		Thread.sleep(3000);
		
		WebElement firstName2Element = wait.until(ExpectedConditions.elementToBeClickable(firstName2));
		js.executeScript("arguments[0].scrollIntoView(true);", firstName2Element);
		firstName2Element.click();
		firstName2Element.sendKeys("Akshitha");
		Thread.sleep(3000);
 
		WebElement lastName2Element = wait.until(ExpectedConditions.elementToBeClickable(lastName2));
		lastName2Element.click();
		lastName2Element.sendKeys("Thallapalli");
		Thread.sleep(3000);
 
		WebElement email2Element = wait.until(ExpectedConditions.elementToBeClickable(email2));
		email2Element.click();
		email2Element.sendKeys("akshitha21@gmail.com");
		Thread.sleep(3000);
 
		WebElement messageElement = wait.until(ExpectedConditions.elementToBeClickable(message));
		messageElement.click();
		messageElement.sendKeys("Happy Birthday ! Enjoy your Gift");
		Thread.sleep(3000);
		
	}
	
	//Printing the Error Message
	public String errorMessage() {
		try {
			WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(error));
			return errorElement.getText().trim();
 
		} catch (Exception e) {
 
			return "No error message displayed";
 
		}
 
	}
	
	//Taking Screenshot of Error Message
	public String CapturingErrorMessage() {
		try {
			ScreenshotUtils ss = new ScreenshotUtils();
			ss.captureScreenshot(driver, "Error Message");
			return "Screenshot is taken";
 
		} catch (Exception e) {
 
			return "No Screenshot is taken";
 
		}
 
	}
 
}