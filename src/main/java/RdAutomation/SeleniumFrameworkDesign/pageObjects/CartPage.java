package RdAutomation.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts ;
	
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkoutButton ;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	public Boolean verifyProductDisplayed(String ProductName) {
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//waitElementToAppear(checkoutButton);
		//System.out.println("Inside ###verifyProductDisplayed");
		//int cartProductsLength = cartProducts.size();
		//System.out.println(cartProductsLength);
		//for ( int i = 0 ; i < cartProductsLength ; i++ ) {
			//System.out.println("Inside verify prodcut displayed printing elements.");
			//System.out.println(cartProducts.get(i).getText());
			
		//}
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		
		return match ;
	}
	
	public CheckOut goToCheckout() {
		//checkoutButton.click();
		checkoutEle.click();
		return new CheckOut(driver);
		
	}

}
