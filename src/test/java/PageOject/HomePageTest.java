package PageOject;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.BaseClass;
import resources.TestUtils;

public class HomePageTest {	
	
	BaseClass baseClass;
	Properties prop;
	WebDriver driver;
	LandingPage landingPage;
	HomePage homePage;
	LandingPageTest landingPageTest;
	
	@BeforeClass
	public void init() throws IOException, InterruptedException {
		
		
    	//landingPageTest.init();
	    baseClass = new BaseClass();
		prop= baseClass.initProp();
		driver = baseClass.initBrowser(prop);
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
		homePage = landingPage.loginIntoCRMLandingPage(prop.getProperty("username"), prop.getProperty("pwd"));
		//driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String title = homePage.verifyHomePageTitle();
		System.out.println(title);
	}
	
	@Test(priority=2)
	public void verifyFrameInfo()
	{
		homePage.verifyFrameInfo();
	}
	
	@Test(priority=3)
	public void verifyNewContact()
	{
		homePage.verifyNewContact();
		driver.manage().timeouts().implicitlyWait(TestUtils.TIMETOLOAD  , TimeUnit.SECONDS);
	}
	@AfterClass
	public void endHomePAge()
	{
		driver.quit();
	}

}
