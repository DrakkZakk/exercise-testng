package exercise;

import org.openqa.selenium.WebDriver;

public interface IActions {
	
	public void searchDevice(WebDriver driver, String device);
	
	public void addToCart(WebDriver driver);
	
	public void setFirstPrice(String price);
	
	public String getFirstPrice();
	
}
