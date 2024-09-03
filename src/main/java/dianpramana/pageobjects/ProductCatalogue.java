 package dianpramana.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dianpramana.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".row .card")
	List<WebElement> products;
	
	By productList = By.cssSelector(".row .card");
	By addToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.id("toast-container");
	By animation = By.className("ng-animating");
	
	public List<WebElement> getProductList() {
		waitElementToAppear(productList);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		List<WebElement> products = getProductList();
		
		WebElement product = products.stream()
				.filter(s->s.findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase(productName ))
				.findFirst()
				.orElse(null);
		
		return product;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();  
		waitElementToAppear(toastMessage);
		waitElementToDisappear(animation);
		Thread.sleep(2000);
		
	}
	
}
