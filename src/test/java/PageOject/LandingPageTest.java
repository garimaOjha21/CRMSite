package PageOject;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import resources.BaseClass;
import resources.TestUtils;

public class LandingPageTest extends BaseClass
{
	BaseClass bassClass;
	LandingPage landingPage;
	WebDriver driver;
	Properties prop;
	TestUtils testUtils;
	WebDriverWait wait;
	String title ;
	
	@BeforeClass
	public void init() throws IOException
	{
		bassClass = new BaseClass();
		prop = bassClass.initProp();
		driver = bassClass.initBrowser(prop);
		String url = prop.getProperty("url");
		driver.get(url);
		landingPage = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void validateTheTitle()
	{
	    title = landingPage.landingPageTitle();
		System.out.println("Title of landing page " + title);
		Assert.assertEquals(title , testUtils.homePageTitle);
	}
	
  /*	@Test(priority=2)
	public void validatehomeLink()
	{
		wait = new WebDriverWait(driver , 10);
		System.out.println(landingPage.homeLink().getText());
		wait.until(ExpectedConditions.elementToBeClickable(landingPage.homeLink));
		landingPage.homeLink.click();
		
	}*/
	
/*	@Test(priority=3)
	public void validatehomeLinkTitle()
	{
		title = driver.getTitle();
		Assert.assertEquals(title , testUtils.homePageTitle);
		
	}*/
/* 	@Test(priority=4)
	public void verfySignUpClickTest()
	{
		landingPage.verfySignUpClick();
		
	}
	
	@Test(priority=5)
	public void validateSignUpLinkTitle()
	{
		title = driver.getTitle();
		Assert.assertEquals(title , testUtils.homePageTitle);
		
	}
		*/
	@AfterClass
	public void afterClasssetup()
	{
		System.out.println("All test methods of this test class executed");
		driver.quit();
	}
}
