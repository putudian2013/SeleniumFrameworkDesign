package dianpramana.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement navigationCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;

	public void waitElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void goToCartPage() {
		navigationCart.click();
	}
	
	public void checkOut() {
		checkOut.click();
	}
	
	public void placeOrder() {
		placeOrder.click();
	}

}
