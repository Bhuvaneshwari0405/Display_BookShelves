package browserImplementation;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserDriver {
	WebDriver driver;
	
	//Launch the browser based on user input
	public WebDriver launch_Browser() {
		/*
		 * Scanner sc = new Scanner(System.in); System.out.println("Select Browser:");
		 * System.out.println("1. Chrome"); System.out.println("2. Edge");
		 * System.out.println("Enter your choice (1 or 2) : "); int choice =
		 * sc.nextInt(); sc.close(); switch(choice) { case 1: driver = launchChrome();
		 * break; case 2: driver = launchEdge(); break; default:
		 * System.out.println("Invalid choice.Launching Chrome as default"); driver =
		 * launchChrome(); break; }
		 */
		driver = new ChromeDriver();
		return driver;
	}
	
	//Open chrome
	public WebDriver launchChrome() {
		driver = new ChromeDriver();
		return driver;
	}
	
	//Open edge
	public WebDriver launchEdge() {
		driver = new EdgeDriver();
		return driver;
	}
	
	//Close the browser
	public void close_Browser() {
		driver.quit();
	}

}
