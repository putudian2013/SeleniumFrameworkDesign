package dianpramana;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {
	
	public static void main(String args[]) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		// Declare Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Login
		driver.findElement(By.id("userEmail")).sendKeys("testaccountlatest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		
		// Wait for all products to display and get Adidas Original to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".row .card")));
		List<WebElement> products = driver.findElements(By.cssSelector(".row .card"));
		
		WebElement product = products.stream()
								.filter(s->s.findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase("Adidas Original"))
								.findFirst()
								.orElse(null);
	
		product.findElement(By.cssSelector(".card-body button:last-child")).click();
	
		// Wait success toast to display to click Cart menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		// Validate the Adidas Original is on the cart. If yes, then click Checkout button
		List<WebElement> productNames = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean isProductExistOnCart = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase("Adidas Original"));
		
		Assert.assertTrue(isProductExistOnCart);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		// Complete the checkout
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//section[contains(@class,'ta-results')]//button[3]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String thankYouMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(thankYouMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
		
		 
	}

}
