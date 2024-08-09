package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;
import dev.failsafe.internal.util.Assert;

public class CartPage  extends AbstractComponent {

	WebDriver driver;
	CheckoutPage checkoutpage;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	  List<WebElement> products;
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkout;
	


	public Boolean VerifyProductDisplay() {
		 Boolean flag = products.stream().anyMatch(element->element.getText().equalsIgnoreCase("ZARA COAT 3"));
		    System.out.println(flag);
		return flag;
		
	}
	  
	public CheckoutPage Checkout()
	{
		checkout.click();
		return new CheckoutPage(driver);
		
	}
	
}
