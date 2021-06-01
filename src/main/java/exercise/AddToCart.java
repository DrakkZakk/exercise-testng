package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddToCart extends SearchProduct {
	
	WebElement element;
	WebDriverWait wait;

	@Override
	public void addToCart(WebDriver driver) {
		String last = null;
		String[] lastPrice = null;
		element = driver.findElement(By.id("add-to-cart-button")); //Add to Cart button
		element.click();
		element = driver.findElement(By.xpath("//div[@id='hlb-subcart']//*[@class='a-color-price hlb-price a-inline-block a-text-bold']")); //Last Price
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
		if(element.isDisplayed()) {
			last = element.getText();
			lastPrice = last.split("\\$|\\."); //Split Last price
			Assert.assertEquals(getFirstPrice(), lastPrice[1]); //Compare First and Last price
			
			element = driver.findElement(By.id("hlb-view-cart-announce"));
			element.click(); //Click on Cart button
			
			element = driver.findElement(By.xpath("//input[starts-with(@name, 'submit.delete')]"));
			element.click(); //Click on Delete button
		}else {
			wait = new WebDriverWait(driver, 15);
			element = driver.findElement(By.xpath("//div[@id='attach-accessory-pane']//child::*[@id='attach-view-cart-button-form']")); //Cart button in pane
			wait.until(ExpectedConditions.visibilityOf(element));
			if(element.isDisplayed()) {
				element.click();
				
				last = driver.findElement(By.xpath("//div[@data-name='Active Items']//p//child::span")).getText(); //Obtain last price
				lastPrice = last.split("\\$|\\."); //Split Last price
				Assert.assertEquals(getFirstPrice(), lastPrice[1]); //Compare First and Last price
				
				element = driver.findElement(By.xpath("//input[starts-with(@name, 'submit.delete')]"));
				element.click();
			}
		}
	}

}
