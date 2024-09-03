package dianpramana.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dianpramana.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryDropdown;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]//button[3]")
	WebElement countrySelected;
	
	By countryResult = By.cssSelector(".ta-results");

	
	public void selectCountry(String countryKeys) {
		countryDropdown.sendKeys(countryKeys);
		waitElementToAppear(countryResult);
		countrySelected.click();
	}
}
