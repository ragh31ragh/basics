
package RdAutomation.SeleniumFrameworkDesign.StepDefnitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RdAutomation.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.CartPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.CheckOut;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.LandingPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefnitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	//Logged in with username <name> and password <password>
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.LoginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.verifyProductDisplayed(productName);
		Assert.assertTrue(match);
		CheckOut checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		 confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }
    
}
