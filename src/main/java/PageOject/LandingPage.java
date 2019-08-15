package PageOject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import resources.BaseClass;

public class LandingPage extends BaseClass {
	
	WebDriver driver;
	
	LandingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='username']")
	WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passWord;

	@FindBy(xpath = "//input[@class='btn btn-small']")
	WebElement loginButton;
	
//	@FindBy(linkText = "Home")
//	@FindBy(partialLinkText = "index.html")
	@FindBy(xpath = "//div[@id='navbar-collapse']/ul/li/a")
	WebElement homeLink;
	
//	@FindBy(linkText = "Sign Up")
//	@FindBy(partialLinkText = "index.html")
	@FindBy(xpath = "//*[@id='navbar-collapse']/ul/li[2]/a")
	WebElement SingUpClick;
	
	public WebElement verfySignUpClick()
	{
		SingUpClick.click();
		return SingUpClick;
	}
	public WebElement homeLink()
	{
		return homeLink;
	}
	public String landingPageTitle()
	{
		return driver.getTitle();
	}
	public HomePage loginIntoCRMLandingPage(String user1 , String pass1) throws InterruptedException
	{
		userName.sendKeys(user1);
		passWord.sendKeys(pass1);
	//	WebDriverWait waitDriver = new WebDriverWait(driver , 5);
	//	waitDriver.until(ExpectedConditions.elementToBeClickable(loginButton));
		Thread.sleep(2000);
		loginButton.click();
		
		return new HomePage(driver);	
	}
}
