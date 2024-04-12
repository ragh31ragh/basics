package RdAutomation.SeleniumFrameworkDesign.pageObjects;
//158. Creating page object classes for Login screen and migrate the test 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RdAutomation.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver ;
	//comment 1
	//comment 2
	

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
		
	}
	//driver.findElement(By.id("userEmail")).sendKeys("qatestrd@qaauto.com");
	//WebElement userEmail = driver.findElement(By.id(null));
	
	@FindBy(id = "userEmail") 
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginApplication(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue ;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}


}
