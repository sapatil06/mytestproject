package KTCTC.regression;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mongodb.diagnostics.logging.Logger;
import com.uiFramework.KTCTC.Pages.PracticeFormPage;
import com.uiFramework.KTCTC.common.CommonMethods;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.testbase.TestBase;
import com.uiFramwork.KTCTC.ObjectPages.PracticeFormObject;



public class PracticeFormTest extends TestBase{
	CommonMethods cmObj = new CommonMethods();
	PracticeFormObject pfObj = new PracticeFormObject();
	PracticeFormPage pfp= null;
	

	
	
	@Test(priority=0)
	public void navigateToPracticeFormPageAndInitializeData()
	{
		pfp=new PracticeFormPage(driver);
		cmObj.navigateToReQuiredPage(driver, "Forms");
		pfObj.setFirstName("Sangram");
		pfObj.setLastName("Patil");
		pfObj.setEmail("abc@gmail.com");
		pfObj.setGender("Male");
		pfObj.setMobile("1234567890");
		pfObj.setDay("10");
		pfObj.setMonth("June");
		pfObj.setYear("2023");
		pfObj.setSubject("maths");
		pfObj.setHobbies("Sports,Reading,Music");
		pfObj.setCurrentAddress("Pune");
		pfObj.setSelectState("Rajasthan");
		pfObj.setSelectCity("Jaipur");
		pfObj.setPicture("D:\\Capture.png");
		pfp.naviagteToPracticeFormPage();
		
		
		Assert.assertTrue(driver.getCurrentUrl().contains("practice-form"));
		
		
	}
	
	@Test(priority=1)
	public void verifyFillDataAndSubmitOperation() throws InterruptedException
	{
		pfp.FillPracticeFormAndSubmit(pfObj);
		boolean flag = pfp.isStudentInformationSubmitted(pfObj) ;
		Assert.assertTrue(flag);
		
	}
	

}
