package RdAutomation.SeleniumFrameworkDesign.pageObjects;
//159.Implementing Actions Methods for PageFactory WebElements to implement logic.
//161. PageObject Class Implementation for product catalogue page and update test
//.161. PageObject Class Implementation for product catalogue page and update test.
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import RdAutomation.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css = ".mb-3")
	List<WebElement> products ;
	
	@FindBy(css = ".ng-animating")
	WebElement  spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()  {
		//System.out.println("inside getProductsList");
		
		waitElementToAppear(productsBy);
		//System.out.println(products);
		return products ;
		
	}
	
	public WebElement getProductByName(String ProductName) {
		
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String ProductName) {
		//System.out.println("Printing Product Name ");
		//System.out.println(ProductName);
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitElementToAppear(toastMessage);
		waitElementToDisappear(spinner);
		
	}

	public OrderPage goToOrdersPage() {
		// TODO Auto-generated method stub
		return null;
	} 
	


}
