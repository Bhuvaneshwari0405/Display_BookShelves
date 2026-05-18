package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import browserImplementation.BrowserDriver;

public class BaseTest {
	WebDriver driver;
	BrowserDriver br;	
	//Launch Browser and Open the application
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {		
		br = new BrowserDriver();
		driver = br.launch_Browser();	
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
