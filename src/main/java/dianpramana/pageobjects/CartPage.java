package dianpramana.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dianpramana.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productNames;

	
	public List<WebElement> getAllProductNames() {
		return productNames;
	}
	
	public boolean isProductExist(String productName) {
		List<WebElement> productNames = getAllProductNames();
		return productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}
}
