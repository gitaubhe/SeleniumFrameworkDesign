package Tests;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.ProductCataloguePage;
import TestComponents.BaseTest;
import TestComponents.Retry;


public class VerifyErrorMessage extends BaseTest{
		@Test(groups= {"ErrorHandling", "LoginTests"}, retryAnalyzer = Retry.class)
		public void VerifyIncorrectEmailTest() throws InterruptedException, IOException {
		
			landingpage.Login("gita123@gmail.com", "Sangamne");
			Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");
		}

}
