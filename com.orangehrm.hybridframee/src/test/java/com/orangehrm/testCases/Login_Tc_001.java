package com.orangehrm.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pageObject.HomePage;
import com.orangehrm.pageObject.LoginPage;
import com.orangehrm.testBase.TestBase;
import com.orangehrm.utilities.Helper;

public class Login_Tc_001 extends TestBase {

	@Test
	public void loginOrangeHrmTest() throws IOException {
		LoginPage login = new LoginPage(driver);
		// login.loginOrangeHrm(configData.getUserName(), configData.getUserPassword());

		test = report.createTest("loginOrangeHrm");
		test.info("Navigated on login page ");

		login.loginOrangeHrm(xlsxData.getStringCellData("login", 0, 0), xlsxData.getStringCellData("login", 0, 1));
		test.info("Enter valid username and password and then click on login button");
		

		String actualTitle = driver.getTitle();
		String expectedTitle = "";
		 Helper.captureScreenshot(actualTitle, driver);

		if (actualTitle.equals(expectedTitle)) {

			Assert.assertTrue(true);
			test.pass("Login page title is verified...");
		} else {
			test.pass("Login page title not matched...");
			Assert.assertTrue(false);
		}

	}

}
