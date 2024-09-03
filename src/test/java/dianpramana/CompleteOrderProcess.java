package dianpramana;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import dianpramana.pageobjects.CartPage;
import dianpramana.pageobjects.CheckOutPage;
import dianpramana.pageobjects.LandingPage;
import dianpramana.pageobjects.ProductCatalogue;
import dianpramana.pageobjects.ThankYouPage;


public class CompleteOrderProcess {
	
	public static void main(String args[]) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.visitUrl("https://rahulshettyacademy.com/client/");
		landingPage.loginApplication("testaccountlatest@gmail.com", "Test@123");
		
		// Wait for all products to display and get Adidas Original to cart
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart("Adidas Original");
		productCatalogue.goToCartPage();
				
		// Validate the Adidas Original is on the cart. If yes, then click Checkout button
		CartPage cartPage = new CartPage(driver);
		Assert.assertTrue(cartPage.isProductExist("Adidas Original"));
		cartPage.checkOut();

		// Complete the checkout
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.selectCountry("Ind");
		checkOutPage.placeOrder();
		
		// Validate Place Order Success
		ThankYouPage thankYouPage = new ThankYouPage(driver);
		Assert.assertTrue(thankYouPage.getThankYouMessage().equalsIgnoreCase("Thankyou for the order."));
		
		 
	}

}
