package PageOject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	int currentMonthInt;
	
	NewContactPage(WebDriver driver)  
	{
		this.driver = driver;
		PageFactory.initElements(driver , this);
		
	}
	
	@FindBy(xpath="//select[@name='title']")
	WebElement titleDD;
	
	@FindBy(xpath="//*[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath="//*[@id='f_trigger_c_birthday']")
	WebElement dob;
	
	@FindBy(xpath="//div[@class='calendar']/table/thead/tr/td[@class='title']")
	WebElement monthYearVAlue;
	
	@FindBy(xpath="//div[@class='calendar']/table/thead/tr[2]/td[5]")
	WebElement nextYear;

	@FindBy(xpath="//div[@class='calendar']/table/thead/tr[2]/td")
	WebElement prevYear;
	
	
	@FindBy(xpath="//div[@class='calendar']/table/thead/tr[2]/td[4]")
	WebElement nextMonth;

	@FindBy(xpath="//div[@class='calendar']/table/thead/tr[2]/td[2]")
	WebElement prevMonth;
	/*
	 * @FindAll({@FindBy(tagName = "frame")})
	List<WebElement> frames ;
	 * *
	 */
	@FindAll({@FindBy(xpath="//div[@class='calendar']/table/tbody/tr")})
	List<WebElement> totalRow;
	
	@FindBy(xpath="//div[@class='calendar']/table/tbody/tr[1]/td")
	List<WebElement> totalCol;
	
	String xpath1="//div[@class='calendar']/table/tbody/tr[";
	String xpath2="]/td[";
	String xpath3 = "]";
			
	
	public void verifydob()
	{		 
		KeyVal = getDatafromMap("DOB");                 //get data from excel
		dob.click();
		LocalDate varDate = LocalDate.parse(KeyVal , dtf) ;   //fetch day , month and year from the fetched data from excel
		int excelDate = varDate.getDayOfMonth();
		int excelMonth = varDate.getMonthValue();
		int excelYear = varDate.getYear();
		
		String[] splitIt = monthYearVAlue.getText().split(","); //read the header of the calender in the web page
		String calenderMonth = splitIt[0].trim();
		String calenderYear = splitIt[1].trim();
		int currentYearInt = Integer.parseInt(calenderYear);   //convert the string year to integer year.
		switch(calenderMonth)
		{                                                  //convert the string month into integer month value.
			case "January" :
							currentMonthInt = 1;
							break;
			case "February" : 
							currentMonthInt = 2;
							break;
			case "March" : 
							currentMonthInt = 3;
							break;
			case "April" : 
							currentMonthInt = 4;
							break;
			case "May" : 
							currentMonthInt = 5;
							break;
			case "June" : 
							currentMonthInt = 6;
							break;
			case "July" : 
							currentMonthInt = 7;
							break;
			case "August" : 
							currentMonthInt = 8;
							break;
			case "September" :
							currentMonthInt = 9;
							break;
			case "October" : 
							currentMonthInt = 10;
							break;
			case "November" : 
							currentMonthInt = 11;
							break;
			case "December" : 
							currentMonthInt = 12;
							break;
		}
		while (excelYear>currentYearInt)   // if input year is > than present year click next year
		{
			nextYear.click();
			currentYearInt++;
		}
		while (excelYear<currentYearInt)  // if input year is > than present year click prev year
		{
			prevYear.click();
			currentYearInt--;
		}
		while(excelMonth>currentMonthInt)   // if input month is > than present month click next month
		{
			nextMonth.click();
			currentMonthInt++;
		}
		while(excelMonth<currentMonthInt)   // if input month is > than present month click next month
		{
			prevMonth.click();
			currentMonthInt--;
		}
		
		int calenderRowCount = totalRow.size();
		int calenderColCount = totalCol.size();
		String excelDateString = Integer.toString(excelDate);
		for(int i=1 ; i<=calenderRowCount ; i++)
		{
			for(int j=2;j<=calenderColCount ; j++)
			{
				String dateString = xpath1 + i + xpath2 + j + xpath3 ;
				String dateDataString = driver.findElement(By.xpath(dateString)).getText();
//				int dateCell = Integer.parseInt(dateDataString);
//				if(dateCell ==excelDate)
				
				if(excelDateString.equalsIgnoreCase(dateDataString))
				{
					driver.findElement(By.xpath(dateString)).click();
				}
			}
		}
		KeyVal=null;
				
	}
	
	public void verifyTitleList()
	{
		selectTitle = new Select(titleDD);
		List<WebElement> s = selectTitle.getOptions();
		for (int i=0; i< s.size() ; i++)
		{
			System.out.println("list :"+ s.get(i).getText());
		}
	}	
	public void setFirstName()
	{
		KeyVal = getDatafromMap("First Name");
		firstName.sendKeys(KeyVal);
		KeyVal=null;
	}
	public void setTitleValue()
	{
		KeyVal = getDatafromMap("Title");
		selectTitle.selectByValue(KeyVal);
		KeyVal=null;
		
	}
	public void getDataFromExcel() throws IOException
	{
		map = new HashMap<String, String>();
		map = ReuseMethod.getTestCaseData("TestData", "Add New Contact");
		System.out.println(map);
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
