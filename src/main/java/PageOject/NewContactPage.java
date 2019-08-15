package PageOject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.BaseClass;
import resources.ReuseMethod;
import resources.TestUtils;

public class NewContactPage extends BaseClass {
	Select selectTitle;
	ArrayList<String> selectTitleList;
	TestUtils testUtils;
	HashMap<String , String> map;
	ReuseMethod reuseMethod;
	int len;
	String KeyVal;

	
	NewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(xpath="//select[@name='title']")
	WebElement titleDD;
	
	//public ArrayList<String> verifyTitleList()
	public void verifyTitleList()
	{
		selectTitle = new Select(titleDD);
		List<WebElement> s = selectTitle.getOptions();
		for (int i=0; i< s.size() ; i++)
		{
			System.out.println("list :"+ s.get(i).getText());
		}
	}	
	public void setTitleValue()
	{
		KeyVal = getDatafromMap("Title");
		selectTitle.selectByValue(KeyVal);
		//Title
		
	}
	public void getDataFromExcel() throws IOException
	{
		map = new HashMap<String, String>();
		map = ReuseMethod.getTestCaseData("TestData", "Add New Contact");
		//System.out.println(mp);
	}
	public String getDatafromMap(String keyValue)
	{
		String valueForKey = null;
		len = map.size();
		for(int i =0 ; i <len ; i++)
		{
			if(map.containsKey(keyValue))
			{
			 valueForKey = map.get(keyValue);
			}
		}
		return valueForKey;
	}
}
