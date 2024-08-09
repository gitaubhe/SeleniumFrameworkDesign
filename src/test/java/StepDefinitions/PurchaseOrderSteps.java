package StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.LandingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.ProductCataloguePage;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseOrderSteps extends BaseTest {

	public LandingPage landingPage;
	public ProductCataloguePage productCataloguePage;
	CheckoutPage checkoutpage;
	CartPage cartpage;
	OrderConfirmationPage orderconfirmationpage;

	@Given("I landed on ecommerse page")
	public void i_landed_on_ecommerse_page() throws IOException {
		landingpage = LaunchApplication();
	}

	@Given("logged in with username {string} and password {string}")
	public void logged_in_with_username_and_password(String email, String pwd) {
		productCataloguePage = landingpage.Login(email, pwd);
	}

	@When("I add product {string} to cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		productCataloguePage.AddElementToCart(productName);
		cartpage = productCataloguePage.GoToCart();

	}

	@When("Checkout {string} and submit the order")
	public void checkout_and_submit_the_order(String string) {

		checkoutpage = cartpage.Checkout();
		checkoutpage.SelectCountry("India");
		orderconfirmationpage = checkoutpage.PlaceOrder();

	}

	@Then("message {string} is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String msg) {
		Assert.assertEquals(orderconfirmationpage.getOrderPlacedSuccessfullyMessage(), msg);
		driver.close();
	}

	@Then("{string} error message shall be displayed")
	public void error_message_shall_be_displayed(String msg) {
		Assert.assertEquals(landingpage.getErrorMessage(), msg);
		driver.close();
	}

}
