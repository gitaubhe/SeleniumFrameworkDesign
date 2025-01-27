package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{
     WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(css=".hero-primary")
	WebElement orderSuccessMessage;

	By orderSuccessMessageBy = By.cssSelector(".hero-primary");
	
	public String getOrderPlacedSuccessfullyMessage()
	{
		WaitForElementToAppear(orderSuccessMessageBy);
		return orderSuccessMessage.getText();
		
	}
	
}
