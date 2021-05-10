package com.uiFramework.KTCTC.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.helper.logger.LoggerHelper;
import com.uiFramework.KTCTC.helper.select.DropDownHelper;
import com.uiFramwork.KTCTC.ObjectPages.PracticeFormObject;

/**
 * @author Sanket Patil
 *
 */
public class PracticeFormPage {
	private Logger log = LoggerHelper.getLogger(PracticeFormPage.class);
	DropDownHelper drpHelper = null;
	WebDriver driver=null;
	Actions actions = null;
	JavaScriptHelper jh=null;
	By firstNameOnPracticeForm = By.xpath("//input[@id='firstName']");
	By lastNameOnPracticeForm= By.xpath("//input[@id='lastName']");
	By emailOnPracticeForm = By.xpath("//input[@id='userEmail']");
	By mobileOnPracticeForm =By.xpath("//input[@id='userNumber']");
	By genderOnPracticeForm= By.xpath("//*[@id='genterWrapper']//div");
	By dateOnPracticeForm = By.xpath("//input[@id='dateOfBirthInput']");
	By hobbiesOnPracticeForm = By.xpath("//*[@id='hobbiesWrapper']//label");
	By subjectsOnPracticeForm = By.xpath("//input[@id='subjectsInput']");
	By pictureUploadOnPracticeForm = By.xpath("//input[@id='uploadPicture']");
	By addressOnPracticeForm = By.xpath("//*[@id='currentAddress']");
	By stateOnPracticeForm = By.xpath("//*[contains(@id,'react-select-3-input')]");
	By cityOnPracticeForm = By.xpath("//*[contains(@id,'react-select-4-input')]");
	By submitButtonOnPracticeForm = By.xpath("//*[@id='submit']");
	By responseTableRowsOnPracticeForm = By.xpath("//*[@class='table-responsive']//table//tbody//tr");
	By responseTableColsOnPracticeForm = By.xpath("//*[@class='table-responsive']//table//thead//th");
	By closeButton = By.xpath("//*[@id='closeLargeModal']");
	/**
	 * @param driver
	 */
	public PracticeFormPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void naviagteToPracticeFormPage()
	{
		log.info("Navigating to Practice form page...");
		driver.findElement(By.xpath("//*[@class='menu-list']//span[contains(text(),'Practice Form')]")).click();
		log.info("Navigated to practice form page");
	}
	
	public void selectGender(String gender)
	{
		log.info("Selcting gender");

		
		List<WebElement> allOptions = driver.findElements(genderOnPracticeForm);
		System.out.println(allOptions.size());
		for(WebElement option : allOptions)
		{
			if(option.getText().equals(gender))
			{
				driver.findElement(By.xpath("//*[@id='genterWrapper']//label[contains(text(),'"+gender+"')]")).click();
				break;
			}
		}
		log.info("gender selected");

	}
	
	public void selectFromDropDown(WebElement element,String optionToSelect)
	{
		Select select = new Select(element);
		select.selectByVisibleText(optionToSelect);
		
	}
	
	public void selectDate(String day,String month,String year)
	{
		log.info("Selcting date from calender");

		drpHelper = new DropDownHelper(driver);
		

		driver.findElement(dateOnPracticeForm).click();
		drpHelper.selectUsingVisibleText( driver.findElement(By.xpath("//*[contains(@class,'month-select')]")), month);
		drpHelper.selectUsingVisibleText( driver.findElement(By.xpath("//*[contains(@class,'year-select')]")), year);
		//WebElement months = driver.findElement(By.xpath("//*[contains(@class,'month-select')]"));
		//selectFromDropDown(months,month);
		//WebElement years = driver.findElement(By.xpath("//*[contains(@class,'year-select')]"));
		//selectFromDropDown(years,year);
		WebDriverWait wt =new WebDriverWait(driver, 5);
		WebElement selectDay;
		if(Integer.parseInt(day)>=10)
		{
			 selectDay= wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'0"+day+"') and contains(@aria-label,'"+month+"')]"))));

		}
		else
		{
			 selectDay= wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'00"+day+"') and contains(@aria-label,'"+month+"')]"))));
		}
		selectDay.click();
		log.info("Date selected");

	}
	
	
	public void selectFromDropDownNoSelectTag(By location,String optionToSelect)
	{
		log.info("Selcting value from drop down");

		driver.findElement(location).sendKeys(optionToSelect);
		driver.findElement(location).sendKeys(Keys.ENTER);
		log.info("Selcted value from drop down");

		
	}
	
	public void selectHobbiesFromCheckbox(String hobbies)
	{
		log.info("Selcting hobbies");
		String[] hobbyarray = hobbies.split(",");
		List<WebElement> allHobbies = driver.findElements(hobbiesOnPracticeForm);
		System.out.println(allHobbies.size());
		for(WebElement each : allHobbies)
		{
			String text = each.getText();
			for(int i=0;i<hobbyarray.length;i++)
			{
				
				if(each.getAttribute("innerText").equalsIgnoreCase(hobbyarray[i]))
				{
					driver.findElement(By.xpath("//*[contains(@id,'hobbiesWrapper')]//label[contains(text(),'"+hobbyarray[i]+"')]")).click();
				}
			}
		}
		log.info(" hobbies selected");

	}
	
	
	public void FillPracticeFormAndSubmit(PracticeFormObject studentInfo) throws InterruptedException
	{
		jh=new JavaScriptHelper(driver);
		actions=new Actions(driver);
		log.info("Filling practice form");
		driver.findElement(firstNameOnPracticeForm).sendKeys(studentInfo.getFirstName());
		driver.findElement(lastNameOnPracticeForm).sendKeys(studentInfo.getLastName());
		driver.findElement(emailOnPracticeForm).sendKeys(studentInfo.getEmail());
		//Gender select from radio button
		selectGender(studentInfo.getGender());
		driver.findElement(mobileOnPracticeForm).sendKeys(studentInfo.getMobile());
		// date selection
		selectDate(studentInfo.getDay(),studentInfo.getMonth(),studentInfo.getYear());
		selectFromDropDownNoSelectTag(subjectsOnPracticeForm, studentInfo.getSubject());
		//driver.findElement(subjectsOnPracticeForm).sendKeys(studentInfo.getSubject());
		//hobbies selection
		selectHobbiesFromCheckbox(studentInfo.getHobbies());
		driver.findElement(addressOnPracticeForm).sendKeys(studentInfo.getCurrentAddress());
		driver.findElement(pictureUploadOnPracticeForm).sendKeys(studentInfo.getPicture());
		//select state
		selectFromDropDownNoSelectTag(stateOnPracticeForm,studentInfo.getSelectState());
		//driver.findElement(stateOnPracticeForm).sendKeys(studentInfo.getSelectState());
		//select city
		selectFromDropDownNoSelectTag(cityOnPracticeForm,studentInfo.getSelectCity());

		//driver.findElement(cityOnPracticeForm).sendKeys(studentInfo.getSelectState());
		jh.scrollDownByPixel(50);
		jh.clickElement(driver.findElement(submitButtonOnPracticeForm));
		//actions.moveToElement(driver.findElement(submitButtonOnPracticeForm)).click().build().perform();
		log.info("Filled practice form");
		

		
		
	}
	
	
	public boolean isStudentInformationSubmitted(PracticeFormObject studentInfo)
	{
		log.info("checking information is submitted or not");
		boolean flag=false;
		 List<WebElement> rows = driver.findElements(By.xpath("//div[@role='dialog']//*[@class='modal-content']//*[@class='table-responsive']//table//tbody//tr"));
		//int cols=driver.findElements(responseTableColsOnPracticeForm).size();
		System.out.println(rows.size());
		for(int i=1;i<=rows.size();i++)
		{
			String key= driver.findElement(By.xpath("//*[@class='table-responsive']//table//tbody//tr["+i+"]//td[1]")).getText();
			String value= driver.findElement(By.xpath("//*[@class='table-responsive']//table//tbody//tr["+i+"]//td[2]")).getText();
			System.out.println(key+" : "+value);
			if(key.equals("Student Email") && value.equals(studentInfo.getEmail()))
			{
				log.info("student information submitted successfully...");
				flag= true;
				driver.findElement(closeButton).click();
				break;
			}
			
		}
		return flag;
		
		
	}
	
	
	

}
