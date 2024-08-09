package Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.LandingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCataloguePage;
import TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest{
	

	@Test(dataProvider="getData",groups= {"LoginTests"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		ExtentTest test = extent.createTest("Submit Order");
		//test.addScreenCaptureFromBase64String(test);
		ProductCataloguePage productCatalogePage = landingpage.Login(input.get("email"), input.get("pwd"));
			
	    productCatalogePage.AddElementToCart(input.get("item"));
		CartPage cartpage = productCatalogePage.GoToCart();

	    Assert.assertTrue(cartpage.VerifyProductDisplay(),"Not added");   
	     
	    CheckoutPage checkoutpage = cartpage.Checkout();
	    checkoutpage.SelectCountry("India");
	    OrderConfirmationPage orderconfirmationpage = checkoutpage.PlaceOrder();
	    Assert.assertEquals(orderconfirmationpage.getOrderPlacedSuccessfullyMessage(),"THANKYOU FOR THE ORDER.");
	   
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistroryTest() throws IOException
	{
		ProductCataloguePage productCatalogePage = landingpage.Login("gita123@gmail.com", "Sangamner_1");
		
		OrdersPage orderspage = productCatalogePage.gotoOrders();
		Assert.assertTrue(orderspage.VerifyProductsInList("ZARA COAT 3"));
	}	
	
//	@DataProvider
//	public Object[][] getData(){
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email","gita123@gmail.com");
//		map.put("pwd","Sangamner_1");
//		map.put("item","ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map.put("email","abc156@gmail.com");
//		map.put("pwd","Sangamner_2");
//		map.put("item","ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//	}
//	
	@DataProvider
	public Object[][] getData() throws IOException{
	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
}
