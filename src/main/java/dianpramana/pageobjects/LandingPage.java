package dianpramana.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dianpramana.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement inputEmail;
	
	@FindBy(id="userPassword")
	WebElement inputPassword;
	
	@FindBy(id="login")
	WebElement btnLogin;
	
	public void visitUrl(String url) {
		driver.get(url);
	}
	
	public void loginApplication(String email, String password) {
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(password);
		btnLogin.click();  
	}

}
