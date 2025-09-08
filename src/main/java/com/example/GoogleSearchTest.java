package com.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {

	public static void main(String[] args) {
		

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to Google
		driver.get("https://www.google.com");

		// Accept cookies if present (Google usually shows consent popup)
		try {
			WebElement agreeButton = driver.findElement(By.xpath("//div[contains(text(),'Accept all')]"));
			agreeButton.click();
		} catch (Exception e) {
			// No popup, continue
		}

		// Search for "Selenium"
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium");
		searchBox.submit();

		// Wait for results and print title
		try {
			Thread.sleep(2000); // Simple wait (use WebDriverWait in real tests)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Page Title: " + driver.getTitle());

		driver.quit();
		System.out.println("Test Passed");
	}
}
