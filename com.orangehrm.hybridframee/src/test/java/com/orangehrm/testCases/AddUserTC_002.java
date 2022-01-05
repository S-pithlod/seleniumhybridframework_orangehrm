package com.orangehrm.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.pageObject.AddUserPage;
import com.orangehrm.pageObject.HomePage;
import com.orangehrm.pageObject.LoginPage;
import com.orangehrm.testBase.TestBase;

public class AddUserTC_002 extends TestBase {

	@Test(dataProvider = "getExcelData")
	public void addNewUserTest(String user_role, String empname, String userName, String status, String password,
			String confirmPassword) {

		LoginPage login = new LoginPage(driver);
		// login.loginOrangeHrm(uname, upass);
		//HomePage homepage = (HomePage) login.loginOrangeHrm(xlsxData.getStringCellData("login", 0, 0),
				//xlsxData.getStringCellData("login", 0, 1));
		
		HomePage homepage = login.loginOrangeHrm(xlsxData.getStringCellData("login", 0, 0), xlsxData.getStringCellData("login", 0, 1));
		
		AddUserPage addUser = homepage.navigateToAddUserPage();

		addUser.addNewUser(user_role, empname, userName, status, password, confirmPassword);
	}

	@DataProvider

	public Object[][] getExcelData() {

		Object[][] data = xlsxData.getExcelTestData("adduser");
		return data;

	}

}
