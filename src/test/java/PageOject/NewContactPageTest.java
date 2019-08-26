package PageOject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resources.BaseClass;
import resources.ReuseMethod;
import resources.TestUtils;

public class NewContactPageTest  
{
	
	BaseClass baseClass;
	Properties prop;
	WebDriver driver;
	LandingPage landingPage;
	HomePage homePage;
	ReuseMethod reuseMethod;
	LandingPageTest landingPageTest;
	NewContactPage newContactPage;
	Select selectTitle;
	ArrayList<String> s;
	TestUtils testUtils;
	
	@BeforeClass
	public void init() throws IOException, InterruptedException {
			
	    baseClass = new BaseClass();
		prop= baseClass.initProp();
		driver = baseClass.initBrowser(prop);
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
		homePage = landingPage.loginIntoCRMLandingPage(prop.getProperty("username"), prop.getProperty("pwd"));
		//driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
		newContactPage = homePage.verifyNewContact();
		newContactPage.getDataFromExcel();
	}
	
	@Test(priority=1)
	public void verifyTitle()
	{
		System.out.println(" title of new contact page is :"+driver.getTitle());
	}
	
	@Test(priority=2)
	public void verifyTitleList() throws IOException  
	{		
		newContactPage.verifyTitleList();
		newContactPage.setTitleValue();
	}
	
	@Test(priority=3)
	public void verifyFirstName() throws IOException  
	{		
		newContactPage.setFirstName();
		newContactPage.verifydob();
	}
	
	/*@AfterClass
	public void closeTab()
	{
		driver.quit();
	}*/
	
}
