package exercise;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class SearchProduct implements IActions {
	
	WebElement element;
	ArrayList<String> list;
	String firstPrice = null;

	public void searchDevice(WebDriver driver, String device) {
		list = new ArrayList<String>();
		String price = null;
		String detailedPrice = null;
		element = driver.findElement(By.id("twotabsearchtextbox"));
		element.sendKeys(device);
		element = driver.findElement(By.cssSelector("div.nav-search-submit.nav-sprite"));
		element.click();
		element = driver.findElement(By.xpath("//div[starts-with(@data-asin, 'B0')]//div[@class='a-section a-spacing-none']//a[starts-with(@href, '/Samsung')]"));
		Assert.assertTrue(element.isDisplayed());
		element = driver.findElement(By.xpath("//div[starts-with(@data-asin, 'B0')]//div[@class='a-section a-spacing-none']//a[starts-with(@href, '/Samsung')]//following::div[@class='sg-row']//span[@class='a-price-whole']"));
		price = element.getText();
		list.add(price);
		element = driver.findElement(By.xpath("//div[starts-with(@data-asin, 'B0')]//div[@class='a-section a-spacing-none']//a[starts-with(@href, '/Samsung')]"));
		element.click();
		element = driver.findElement(By.id("priceblock_ourprice"));
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
		detailedPrice = element.getText();
		String[] temp = detailedPrice.split("\\$|\\.");
		Assert.assertEquals(temp[1], list.get(0));
		
		setFirstPrice(list.get(0));
	}
	
	public void setFirstPrice(String price) {
		this.firstPrice = price;
		
	}

	public String getFirstPrice() {
		return this.firstPrice;
	}

	public abstract void addToCart(WebDriver driver);
}