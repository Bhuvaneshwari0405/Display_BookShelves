package pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
public class CollectionsPage {
    WebDriver driver;

    public CollectionsPage(WebDriver driver) {
        this.driver = driver;
    }
    By furnishing = By.xpath("//*[@id='app']/div/div[4]/div/div[1]/div/div[2]/div[3]/div/div/div/div[11]/div/span");
    By submenuItems = By.xpath("//div[@class='ZDF6_']//a");
    List<WebElement> listElement;
    List<String> list = new ArrayList<>();
    //clicking on the furnishing menu
    public void clickFurnishingMenu() {
        driver.findElement(furnishing).click();
    }
   //checking whether the subelements are present or not
    public List<WebElement> getSubMenuElements() {
    	listElement = driver.findElements(submenuItems);
        return listElement;
    }
   //storing the elements in the list
    public void printSubMenuItems() {
        for (int i = 0; i < listElement.size(); i++) {
            System.out.println(listElement.get(i).getText());
        }
    }
   //returning the elements in the list
    public List<String> getStoredMenuList() {
    	for (int i = 0; i < listElement.size(); i++) {
    		String collection = listElement.get(i).getText();
 
			if (!collection.isEmpty()) {
			            list.add(collection);
			}
 
        }
        return list;
    }
}