package com.uiFramework.KTCTC.Pages;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.helper.logger.LoggerHelper;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;
import com.uiFramwork.KTCTC.ObjectPages.TableSearchObjectClass;

public class TableSearchPage {
	
	private Logger log = LoggerHelper.getLogger(PracticeFormPage.class);
	WebDriver driver;
	JavaScriptHelper jsh ;
	WaitHelper wt;
	
	By webTablesPage = By.xpath("//*[@class='element-group']//*[contains(text(),'Web Tables')]");
	By addButtonOnWebTablePage = By.id("addNewRecordButton");
	By searchBoxOnWebTablePage = By.id("searchBox");
	By deleteButtonOnwebTablesPage = By.xpath("//*[@title='Delete']");
	By editOnWebTablePage = By.xpath("//*[@title='Edit']");
	
	By firstNameOnRegistrationForm = By.xpath("//*[@id='firstName']");
	By LastNameOnRegistrationForm = By.xpath("//*[@id='lastName']");
	By emailOnRegistrationForm = By.xpath("//*[@id='userEmail']");
	By ageOnRegistrationForm = By.xpath("//*[@id='age']");
	By salaryOnRegistrationForm = By.xpath("//*[@id='salary']");
	By departmentOnRegistrationForm = By.xpath("//*[@id='department']");
	By submitButtonOnRegistrationForm = By.xpath("//button[@id='submit']");
	
	By firstRowsOnOnwebTablesPage = By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]");
	By firstNameOnWebTable = By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[1]");

	
	public TableSearchPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	
	public void navigateToWebtablePage()
	{
		jsh = new JavaScriptHelper(driver);
		log.info("Navigating to WebTable page");
		jsh.scrollDownByPixel(200);
		driver.findElement(webTablesPage).click();
		log.info("Navigated to WebTable page");
		
	}
	
	public void addNewUserRecord(TableSearchObjectClass newUser)
	{
		wt= new WaitHelper(driver);
		jsh=new JavaScriptHelper(driver);
		log.info("Adding new user in table");
		wt.WaitForElementVisibleWithPollingTime(driver.findElement(addButtonOnWebTablePage), 10, 1);
		jsh.clickElement(driver.findElement(addButtonOnWebTablePage));
		//driver.findElement(addButtonOnWebTablePage).click();
		//wt.WaitForElementVisibleWithPollingTime(driver.findElement(firstNameOnRegistrationForm), 10, 1);
		wt.waitForElement(driver.findElement(firstNameOnRegistrationForm), 10);
		driver.findElement(firstNameOnRegistrationForm).sendKeys(newUser.getFirstName());
		driver.findElement(LastNameOnRegistrationForm).sendKeys(newUser.getLastName());
		driver.findElement(emailOnRegistrationForm).sendKeys(newUser.getEmail());
		driver.findElement(ageOnRegistrationForm).sendKeys(newUser.getAge());
		driver.findElement(salaryOnRegistrationForm).sendKeys(newUser.getSalary());
		driver.findElement(departmentOnRegistrationForm).sendKeys(newUser.getDepartment());
		//driver.findElement(submitButtonOnRegistrationForm).click();
		jsh.clickElement(driver.findElement(submitButtonOnRegistrationForm));
		log.info("New User added");
		
	}
	
	public void clearInputBox(By element)
	{
		driver.findElement(element).clear();
	}
	
	public void searchUserInWebtable(String str)
	{
		log.info("Seaching in web table");
		clearInputBox(searchBoxOnWebTablePage);
		driver.findElement(searchBoxOnWebTablePage).sendKeys(str);
		
	}
	
	public HashMap<String, String> getAllDetailsOfRequiredUserOnWebPage(String str)
	{
		searchUserInWebtable(str);
		log.info("Getting information of required user");
		HashMap <String,String> data = new HashMap<>();
		
		data.put("firstName", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[1]")).getText());
		data.put("lastName", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[2]")).getText());
		data.put("email", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[3]")).getText());
		data.put("age", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[4]")).getText());
		data.put("salary", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[5]")).getText());
		data.put("department", driver.findElement(By.xpath("//*[@class='rt-table']//*[contains(@class,'rt-tr -odd')]/div[6]")).getText());

		return data;
		
		
	}
	
	public void editDetailsOfExistingUser(String email,TableSearchObjectClass editUser)
	{
		wt= new WaitHelper(driver);
		jsh=new JavaScriptHelper(driver);
		searchUserInWebtable(email);
		log.info("Editing deatils of existing user");
		driver.findElement(editOnWebTablePage).click();

		wt.waitForElement(driver.findElement(firstNameOnRegistrationForm),10);
		clearInputBox(firstNameOnRegistrationForm);
		driver.findElement(firstNameOnRegistrationForm).sendKeys(editUser.getFirstName());
		clearInputBox(LastNameOnRegistrationForm);
		driver.findElement(LastNameOnRegistrationForm).sendKeys(editUser.getLastName());

		clearInputBox(emailOnRegistrationForm);
		driver.findElement(emailOnRegistrationForm).sendKeys(editUser.getEmail());

		clearInputBox(ageOnRegistrationForm);
		driver.findElement(ageOnRegistrationForm).sendKeys(editUser.getAge());

		clearInputBox(salaryOnRegistrationForm);
		driver.findElement(salaryOnRegistrationForm).sendKeys(editUser.getSalary());

		clearInputBox(departmentOnRegistrationForm);
		driver.findElement(departmentOnRegistrationForm).sendKeys(editUser.getDepartment());
		jsh.clickElement(driver.findElement(submitButtonOnRegistrationForm));
		//driver.findElement(submitButtonOnRegistrationForm).click();;
				
	}
	
	public boolean isSerchedUserPresentOnWebTablePage(String str)
	{
		searchUserInWebtable(str);
		
		boolean flag=false;
		  // WaitHelper wt = new WaitHelper(driver);
		  WebDriverWait wait = new WebDriverWait(driver, 2);
		  try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(firstRowsOnOnwebTablesPage).findElement(By.xpath("//div[contains(text(),'"+str+"')]"))));
			flag = true;
			log.info("given  records is present");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("given  records is not present");
		}
		
		 return flag;  
	}
	
	public void deleteExistingUserFromWebTable(String email) {
		jsh=new JavaScriptHelper(driver);
		log.info("Deleting existing user");
		searchUserInWebtable(email);
		jsh.clickElement(driver.findElement(deleteButtonOnwebTablesPage));
		//driver.findElement(deleteButtonOnwebTablesPage).click();
		log.info("deletd existing user");

	}
	 
	
}
