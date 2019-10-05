package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver eventdriver;
	public static WebEventListener eventListner;
	
	public Properties initProp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Ojha\\CRMSite\\src\\test\\java\\resources\\teatData.properties");
		prop = new Properties();
		prop.load(fis);
		return prop;
	}

	public WebDriver initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "H:\\chromedriver.exe");
			driver = new ChromeDriver();
		}
			  eventdriver= new EventFiringWebDriver(driver);
			  eventListner = new WebEventListener();
			  eventdriver.register(eventListner);
			  driver = eventdriver;
			  driver.manage().window().maximize();
			  driver.manage().deleteAllCookies();
			  return driver;
	}

}
