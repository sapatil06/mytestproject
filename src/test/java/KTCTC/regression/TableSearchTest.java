package KTCTC.regression;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiFramework.KTCTC.Pages.TableSearchPage;
import com.uiFramework.KTCTC.common.CommonMethods;
import com.uiFramework.KTCTC.testbase.TestBase;
import com.uiFramwork.KTCTC.ObjectPages.TableSearchObjectClass;

public class TableSearchTest extends TestBase {

	CommonMethods commonMethods = new CommonMethods();
	TableSearchObjectClass newUser = new TableSearchObjectClass();
	TableSearchObjectClass editUser = new TableSearchObjectClass();

	TableSearchPage objectPage;

	@Test(priority = 0)
	public void verifyNavigateToWebTablepageAndInitializeData() {
		objectPage = new TableSearchPage(driver);
		commonMethods.navigateToReQuiredPage(driver, "Elements");
		newUser.setFirstName(commonMethods.getcharacterString(5));
		newUser.setLastName(commonMethods.getcharacterString(5));
		newUser.setEmail(commonMethods.getcharacterString(5) + commonMethods.getAlphaNumericString(3) + "@gamil.com");
		newUser.setAge(commonMethods.getNumericString(2));
		newUser.setSalary(commonMethods.getNumericString(5));
		newUser.setDepartment(commonMethods.getcharacterString(5));

		editUser.setFirstName(commonMethods.getcharacterString(5));
		editUser.setLastName(commonMethods.getcharacterString(5));
		editUser.setEmail(commonMethods.getcharacterString(5) + commonMethods.getAlphaNumericString(3) + "@gamil.com");
		editUser.setAge(commonMethods.getNumericString(2));
		editUser.setSalary(commonMethods.getNumericString(5));
		editUser.setDepartment(commonMethods.getcharacterString(5));
		objectPage.navigateToWebtablePage();

	}

	@Test (priority = 1)
	public void verifyNewUserCanBeAddedOnWebTablePage() {
		objectPage.addNewUserRecord(newUser);
		assertTrue(objectPage.isSerchedUserPresentOnWebTablePage(newUser.getEmail()));

	}

	@Test (priority = 2)
	public void verifyAllDetailsOfAddedUser() {
		HashMap<String, String> data = objectPage.getAllDetailsOfRequiredUserOnWebPage(newUser.getEmail());

		if (data.get("firstName").contains(newUser.getFirstName())
				&& data.get("lastName").contains(newUser.getLastName()) && data.get("age").contains(newUser.getAge())
				&& data.get("email").contains(newUser.getEmail()) && data.get("salary").contains(newUser.getSalary())
				&& data.get("department").contains(newUser.getDepartment())) {
			Assert.assertTrue(true);
		}
	}

	@Test (priority = 3)
	public void verifyExistingUserDetailsCanBeEditedOnWebTablePage() {
		objectPage.editDetailsOfExistingUser(newUser.getEmail(), editUser);
		Assert.assertTrue(objectPage.isSerchedUserPresentOnWebTablePage(editUser.getEmail()));
	}

	@Test (priority = 4)
	public void verifyAllDetailsOfEditedUser() {
		HashMap<String, String> data = objectPage.getAllDetailsOfRequiredUserOnWebPage(editUser.getEmail());

		if (data.get("firstName").contains(editUser.getFirstName())
				&& data.get("lastName").contains(editUser.getLastName()) && data.get("age").contains(editUser.getAge())
				&& data.get("email").contains(editUser.getEmail()) && data.get("salary").contains(editUser.getSalary())
				&& data.get("department").contains(editUser.getDepartment())) {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 5)
	public void verifyExistingUserCanBeDeletedOnWebTablePage()
	{
		objectPage.deleteExistingUserFromWebTable(editUser.getEmail());
		Assert.assertFalse(objectPage.isSerchedUserPresentOnWebTablePage(editUser.getEmail()));
	}

}
