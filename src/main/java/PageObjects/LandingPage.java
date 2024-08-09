package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
    WebElement userEmail;
	
	@FindBy(id="userPassword")
    WebElement userPassword;
	
	@FindBy(id="login")
    WebElement login_Button;
	
	@FindBy(css=".toast-message")
	WebElement errorMessage;
	
	By errorMessageBy = By.cssSelector(".toast-message");
	
  public ProductCataloguePage Login(String email, String pwd)
  {
	  userEmail.sendKeys(email);
	  userPassword.sendKeys(pwd);
	  login_Button.click();
	  return new ProductCataloguePage(driver);	  
  }
  
  public String getErrorMessage()
  {
	  WaitForElementToAppear(errorMessageBy);
	  return errorMessage.getText();
  }
  
  
  
  public void GoToApplication(){
	  {
		  driver.get("https://rahulshettyacademy.com/client/");	  
	  }
  }
}
