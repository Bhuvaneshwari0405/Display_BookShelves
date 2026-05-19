package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudyChairPage {
	WebDriver driver;	
	WebDriverWait wait;
	
	By category = By.xpath("//*[@id='app']/div/div[4]/div/div[1]/div/div[2]/div[3]/div/div/div/div[8]/div/span");
	By studyChair = By.xpath("//*[@id='category-menu-7']/div[1]/div/div[1]/div[2]/a[1]");

	By sortDropdown = By.xpath("//div[@aria-label='Sort By filter']");
	By popularityOption = By.xpath("//*[@id='dropdown-menu-sort-by']//*[contains(text(),'Popularity')]");
	
	By productCards = By.xpath("//*[@role = 'link' and contains(@aria-label, 'by Urban Ladder')]");
	By nameOfProduct = By.xpath(".//h3[@class = 'XxwSy']");
	By priceOfProduct = By.xpath(".//div[@class = 'UYQNp']");
	
	public StudyChairPage(WebDriver driver, WebDriverWait wait) {
		this.driver= driver;
		this.wait = wait;
	}
	
	//Returns the URL of the study chair page
	public void getStudyChairsURL() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(category)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(studyChair)).click();
	}
	
	//Sorting the study chairs using popularity
	public void sortByPopularity() throws InterruptedException {
		
		//located and click all filters
		wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
		Thread.sleep(2000);
		
		//Wait until the dropdown is clicked
		wait.until(ExpectedConditions.attributeToBe(sortDropdown, "aria-expanded", "true"));
		Thread.sleep(2000);
		
		//locate and click sort
		wait.until(ExpectedConditions.elementToBeClickable(popularityOption)).click();
		Thread.sleep(2000);
		
	
	}
	
	//Display top 3 study chairs
	public List<String> getTop3Products() {
		List<WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCards));
		int count = 0;
		List<String> result = new ArrayList<>();
		
		for(WebElement card : cards) {
			String name = card.findElement(nameOfProduct).getText();
			String price = card.findElement(priceOfProduct).getText();
			System.out.println(name + " | " + price);
			result.add(name + " | " + price);
			count++;
			if(count == 3) {
				break;
			}
		}
		return result;
	}
	
	
}
