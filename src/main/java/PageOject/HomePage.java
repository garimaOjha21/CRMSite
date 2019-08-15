package PageOject;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import resources.BaseClass;
import resources.TestUtils;

public class HomePage extends BaseClass
{
	WebDriver driver;
	Actions a ;
	WebDriverWait wait;
	
	HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	@FindAll({@FindBy(tagName = "frame")})
	List<WebElement> frames ;

	@FindBy(xpath = "//a[@title='Contacts']")
	WebElement contact;

	@FindBy(xpath = "//a[@title='New Contact']")
	WebElement newContact;
	
	public NewContactPage verifyNewContact()
	{
		switchToFrame();
		a=new Actions(driver);
		wait = new WebDriverWait(driver , 20);
		a.moveToElement(contact).build().perform();
		//driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(newContact));
	 	a.moveToElement(newContact).build().perform();
		//driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
	 	newContact.click();
	 	return new NewContactPage(driver);
	}
	
	public void verifyNewContactTitle()
	{
		
	}
	public String verifyHomePageTitle()
	{
		return driver.getTitle();
	}
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
		
	}

	
	public void verifyFrameInfo()
	{
		int count = frames.size();
		System.out.println("Total frames in page is "+ count);
		
		for(int i =0 ; i<count ; i++)
		{
			System.out.println(frames.get(i).getText());
			String name = "name";
			System.out.println(frames.get(i).getAttribute(name));
			
		}		
	}
	

}
