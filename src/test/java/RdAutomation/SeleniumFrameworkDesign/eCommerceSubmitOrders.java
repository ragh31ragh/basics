package RdAutomation.SeleniumFrameworkDesign;
//158. Creating page object classes for Login screen and migrate the test 

import java.io.File;
import java.io.IOException;

//158. Creating page object classes for Login screen and migrate the test
//159.Implementing Actions Methods for PageFactory WebElements to implement logic.
//160.Creating Abstract components to reuse the common methods /code in the framework.
//161. PageObject Class Implementation for product catalogue page and update test.
//162. Speed up your test execution - Fix for application slowness in the backend.
//163. Creating common methods to Abstract components extending it in page classes.
//164. Wrapping up the whole test with complete refractor into PageObjectModel

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RdAutomation.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.CartPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.CheckOut;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.LandingPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.OrderPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class eCommerceSubmitOrders extends BaseTest {

	//String productName = "ZARA COAT 3";

	// @Test
	@Test(dataProvider = "getData", groups = { "Purchase" })

	// public void SubmitOrder(String email, String password, String productName)
	// throws IOException {
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// ProductCatalogue productCatalogue = landingPage.LoginApplication(email,
		// password);
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductsList();
		// productCatalogue.addProductToCart(productName);
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		// Boolean match = cartPage.verifyProductDisplayed(productName);
		Boolean match = cartPage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckOut checkOut = cartPage.goToCheckout();
		checkOut.selectCountry("india");
		ConfirmationPage confirmationPage = checkOut.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Execution Completed");

	}

	//@Test(dependsOnMethods = { "SubmitOrder" })
	//public void OrderHistoryTest() {
		// "ZARA COAT 3";
	//	ProductCatalogue productCatalogue = landingPage.LoginApplication("qatestrd@qaauto.com",
	//			"Qatestrd@qaauto.com123");
	//	OrderPage ordersPage = productCatalogue.goToOrdersPageTest1();
	//	Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	//}
	//@Test(dataProvider = "getData", groups = { "Purchase" })
	@Test(dependsOnMethods = { "SubmitOrder" },dataProvider = "getData")
		public void OrderHistoryTest(HashMap<String, String> input) {
			// "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),
					input.get("password"));
			OrderPage ordersPage = productCatalogue.goToOrdersPageTest1();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));

		}

	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\RdAutomation\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		// return new Object[][] {{data.get(0)}, {data.get(1) } };

		System.out.println(data.get(0).toString());
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		// return new Object[][] {{data.get(1) } };
		// return new Object[][] {{data.get(0)}, {data.get(1) } };
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "qatestrd@qaauto.com", "Qatestrd@qaauto.com123", "ZARA COAT 3" },
//				{ "qatestrd2@qaauto.com", "Qatestrd@qaauto.com123", "ADIDAS ORIGINAL" } };

//	}

}

//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "anshika@gmail.com");
//map.put("password", "Iamking@000");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "shetty@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("product", "ADIDAS ORIGINAL");
