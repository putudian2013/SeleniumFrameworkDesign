package dianpramana.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dianpramana.abstractcomponents.AbstractComponent;

public class ThankYouPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement thankYouMessage;

	
	public String getThankYouMessage() {
		return thankYouMessage.getText();
	}
	
}
