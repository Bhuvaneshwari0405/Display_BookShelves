package testcases;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import browserImplementation.BrowserDriver;

public class BaseTest {
	WebDriver driver;
	BrowserDriver br;
	
	
	
	//Launch Browser and Open the application
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Choose the Browser Type:\nChrome - 1\nEdge - 2\nEnter the value: ");
		int browserType = sc.nextInt();
		sc.close();
		
		br = new BrowserDriver();
		driver = br.Launch_Browser(browserType);	
	}
	
	@AfterTest
	public void close_Browser() {
		br.quit_Browser();
	}
}
