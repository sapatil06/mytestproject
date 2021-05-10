package com.uiFramework.KTCTC.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.helper.logger.LoggerHelper;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;
import com.uiFramwork.KTCTC.ObjectPages.TextBoxObjectClass;

public class TextBoxPage {
	private Logger log = LoggerHelper.getLogger(TextBoxPage.class);
	WebDriver driver =null;
	JavaScriptHelper js = null;
	WaitHelper wt = null;
	

	// locate elements 
	By fullNameOnTextboxPage = By.id("userName");
	By emailOnTextboxPage = By.xpath("//*[@id='userEmail']");
	By currentAddressOnTextboxPage = By.xpath("//*[@id='currentAddress']");
	By permanantAddressOnTextboxPage = By.xpath("//*[@id='permanentAddress']");
	By submitButtonOnTextboxPage = By.xpath("//*[@id='submit']");
	
	
	
	
	public TextBoxPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void navigateToTextBoxPage()
	{
		log.info("Navigating to Textbox page....");
		driver.findElement(By.xpath("//ul[@class='menu-list']//span[contains(text(),'Text Box')]")).click();
		log.info("Navigated to TextBox page");
		
	}
	
	
	public void addInformationToTextBoxes(TextBoxObjectClass newData)
	{
		wt=new WaitHelper(driver);
		js = new JavaScriptHelper(driver);

		
		log.info("Filleing information in textboxes of Textbox page....");
		driver.findElement(fullNameOnTextboxPage).sendKeys(newData.getFullName());
		driver.findElement(emailOnTextboxPage).sendKeys(newData.getEmail());
		driver.findElement(currentAddressOnTextboxPage).sendKeys(newData.getCurrentAddress());
		driver.findElement(permanantAddressOnTextboxPage).sendKeys(newData.getPermanantAddress());
		//js.scrollUpByPixel(500);
		js.scrollDownByPixel(300);
		//js.scrollDownVertically();
		//wt.waitForElementNotPresent(driver.findElement(submitButtonOnTextboxPage), 10);
		driver.findElement(submitButtonOnTextboxPage).click();
		log.info("Filled information in textboxes and click on submit of Textbox page....");

	}
	
	public boolean isDataSubmitedSuccessfullyInTextboxes(TextBoxObjectClass newData)
	{
		WebElement output = driver.findElement(By.xpath("//*[@id='output']//div"));
		String outputText = output.getAttribute("innerText").replace("\n","");
		System.out.println(outputText);
		
		if(outputText.contains(newData.getFullName()) && outputText.contains(newData.getEmail()) && outputText.contains(newData.getCurrentAddress()))
		{
			log.info("Data submitted sucessfully");
			return true;
		}
		else
		{
			log.info("Data not submitted");

			return false;
		}
		//List<WebElement> output = driver.fin dElements(By.xpath("//*[@id='output']//p"));
		
	}
	
	

}
