package browserImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserDriver {
WebDriver driver;
	
	//Multiple Browsers
	public WebDriver Launch_Browser(int DriverType) {
		
		switch(DriverType) {
		case 1:
			driver = new ChromeDriver();
			break;
		case 2:
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Wrong BrowserType");
			break;
		}
		return driver;
		
	}
	
	//Quit Browser
	public void quit_Browser() {
		driver.quit();
	}

}
