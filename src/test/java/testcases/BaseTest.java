package testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import browserImplementation.BrowserDriver;
import objectImplementation.ObjectReader;

public class BaseTest {
	
	WebDriver driver;
	BrowserDriver br;
	ObjectReader or;	
	
	public BaseTest(WebDriver driver) {
		this.driver = driver;
	}
	//Launch Browser and Open the application
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {		
		br = new BrowserDriver();
		driver = br.launch_Browser();
		driver.manage().window().maximize();
		Thread.sleep(3000);		
		or = new ObjectReader();
		driver.get(or.get_baseUrl());
		Thread.sleep(3000);
		
	}
	
	@Test
	public void testCase() {
		System.out.println("Testss......");
	}
	
	@AfterTest
	public void quit_Browser() {
		br.close_Browser();
	}
}
