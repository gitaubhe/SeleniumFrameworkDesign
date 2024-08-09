package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
  WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css=".ta-item")
	WebElement IndiaListItem;
	
	@FindBy(css=".action__submit")
			WebElement placeOrderButton;
	

	By countryList = By.cssSelector(".ta-results");
	
	
	public void SelectCountry(String country)
	{
		Country.sendKeys(country);
		WaitForElementToAppear(countryList);
		IndiaListItem.click();
	}
	
	public OrderConfirmationPage PlaceOrder()
	{
		placeOrderButton.click();
		return new OrderConfirmationPage(driver);
	}

}
