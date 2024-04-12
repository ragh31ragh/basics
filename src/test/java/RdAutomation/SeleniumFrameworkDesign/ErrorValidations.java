package RdAutomation.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RdAutomation.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.CartPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	//@Test
	//@Test(groups= {"ErrorHandling"})
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=RdAutomation.SeleniumFrameworkDesign.TestComponents.Retry.class)
	//@Test(retryAnalyzer=RdAutomation.SeleniumFrameworkDesign.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		
		//landingPage.LoginApplication("anshika@gmail.com", "Iamki000");
		landingPage.LoginApplication("reddafa@gmail.com", "afdafafafd");
		//Assert.assertEquals("000", landingPage.getErrorMessage());
		//Incorrect email or password.
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.LoginApplication("qatestrd@qaauto.com",
				"Qatestrd@qaauto.com123");
		productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplayed("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}
	

}
