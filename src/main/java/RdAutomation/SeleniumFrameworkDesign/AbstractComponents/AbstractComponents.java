
package RdAutomation.SeleniumFrameworkDesign.AbstractComponents;
//160.Creating Abstract components to reuse the common methods /code in the framework.

//161. PageObject Class Implementation for product catalogue page and update test.

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RdAutomation.SeleniumFrameworkDesign.pageObjects.CartPage;
import RdAutomation.SeleniumFrameworkDesign.pageObjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitElementToAppear(By findBy) {
		// System.out.println("find by in wait element to appear");
		// System.out.println(findBy);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		//w.until(ExpectedConditions.visibilityOf(cartHeader))

	}

	public void waitForWebElementToAppear(WebElement errorMessage) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));

	}

	public void waitElementToDisappear(WebElement ele) {
		// Thread.sleep(1000);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		// w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));-Reference
		// code only
		w.until(ExpectedConditions.invisibilityOf(ele));
		// System.out.println("Waited for element spinner to disappear"); - For Debug
		// purpose only.
	}

	// As it is common in Header and does not depends on page.

	public CartPage goToCart() {
		// System.out.println("Executing goToCart() Method");
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPageTest1() {
		//System.out.println("Inside goTOOrdersMethod");
		orderHeader.click();
		//System.out.println("Clicked on order Header");
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
