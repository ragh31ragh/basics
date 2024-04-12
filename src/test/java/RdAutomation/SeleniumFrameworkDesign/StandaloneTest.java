package RdAutomation.SeleniumFrameworkDesign;
//152.Selenium Program on WebDriver Manager -Login - Get Products List 
//153.Selenium Program to retrieve product and Add to cart based on Java Streams.
//154. Implementation of Explicit wait to handle application synchronously on loading.
//155. Logic to verify items in the cart with Streams and checkout 
//156.Wrapping up end to end automation Script on Purchasing Order in ECommerce app


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(" https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("qatestrd@qaauto.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qatestrd@qaauto.com123");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//154. Implementation of Explicit wait to handle application synchronously on loading.
		//Introducing Explicit Wait 
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(15));
		//toast-container
		//#toast-container
		//Waiting till Product added to cart
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		//Waiting till animation dissapears
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//[routerlink*='cart']
		//Clicking on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//155. Logic to verify items in the cart with Streams and checkout 
		//ZARA COAT 3
		String productName = "ZARA COAT 3";
		//Get all the elements in checkout page
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		int cartProductsSize = cartProducts.size();
		System.out.println(cartProductsSize );
		System.out.println(cartProductsSize );
		//Check if it matches "ZARA COAT 3";
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//.totalRow button
		//Click on Checkout button and Navigate to payment page.s
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//156.Wrapping up end to end automation Script on Purchasing Order in ECommerce app
		//placeholder="Select Country"
		//[placeholder='Select Country']
		//Select India country from dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		////button[contains(@class,'ta-item')][2]
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		//.action__submit
		//Click on checkout
		driver.findElement(By.cssSelector(".action__submit")).click();
		//.hero-primary
		//Verify the order confirmation message.
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		

	}

}
