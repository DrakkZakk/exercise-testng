package exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
	
	AddToCart add = new AddToCart();
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test(priority = 0)
	public void testCase1() {
		add.searchDevice(driver, "Samsung Galaxy Note 20");
		add.addToCart(driver);
	}
	
	@Test(priority = 1)
	public void testCase2() {
		add.searchDevice(driver, "Galaxy S20 FE 5G");
		add.addToCart(driver);
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
	
}
