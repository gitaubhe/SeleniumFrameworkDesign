package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{

	WebDriver driver;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".card-body button:last-of-type")
	WebElement add_to_cart_Button;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement go_to_cart;
	
	
	
	By productBy = By.cssSelector(".mb-3");
	By toastMessageBy = By.cssSelector("#toast-container");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By progressbarBy = By.cssSelector(".ng-animating");
	By cartListBy = By.cssSelector(".cartSection h3");
	
	public List<WebElement> GetProductList()
	{
		WaitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement GetProductByName(String productName)
	{
		
		WebElement product = GetProductList().stream().filter(element->
		 element.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		return product;
	}
	
	public void AddElementToCart(String productName) throws InterruptedException
	{
		WebElement product = GetProductByName(productName);
		product.findElement(addToCartBy).click();
		WaitForElementToAppear(toastMessageBy);
		WaitForElementToDisAppear(progressbarBy);
	}
	
	public CartPage GoToCart() throws InterruptedException
	{
		go_to_cart.click();
		WaitForElementToAppear(cartListBy);
		return new CartPage(driver);
	}
	

}
