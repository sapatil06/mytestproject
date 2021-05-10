package KTCTC.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiFramework.KTCTC.Pages.TextBoxPage;
import com.uiFramework.KTCTC.common.CommonMethods;
import com.uiFramework.KTCTC.testbase.TestBase;
import com.uiFramwork.KTCTC.ObjectPages.TextBoxObjectClass;

public class TextBoxTest extends TestBase {
	
	TextBoxPage textBoxPage = null;
	TextBoxObjectClass newData = new TextBoxObjectClass();
	CommonMethods commonMethods= new CommonMethods();
	

	@Test(priority=0)
	public void verifyNavigateToTextboxPageAndInitializeData() throws InterruptedException
	{
		commonMethods.navigateToReQuiredPage(driver, "Elements");
		textBoxPage=new TextBoxPage(driver);
		newData.setFullName(commonMethods.getcharacterString(10));
		newData.setEmail(commonMethods.getAlphaNumericString(5)+"@gmail.com");
		newData.setCurrentAddress(commonMethods.getcharacterString(20));
		newData.setPermanantAddress(commonMethods.getcharacterString(20));
		textBoxPage.navigateToTextBoxPage();
		Assert.assertTrue(driver.getCurrentUrl().contains("text-box"));
	}
	
	@Test(priority=1)
	public void verifyDataAddedAndSubmittedSuccessfully()
	{
		textBoxPage.addInformationToTextBoxes(newData);
		boolean flag = textBoxPage.isDataSubmitedSuccessfullyInTextboxes(newData);
		Assert.assertTrue(flag);
		
	}
}
