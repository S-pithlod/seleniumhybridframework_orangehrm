package com.orangehrm.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.pageObject.LoginPage;
import com.orangehrm.testBase.TestBase;

public class LoginTC02 extends TestBase {
	
	@Test(dataProvider="getTestData")
	public void validateLoginFunctionality(Object uname,Object pwd) {
		
		String un = uname.toString();
		String pw = pwd.toString();
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(un);
		lp.setPassword(pw);
		
	}
	
	@DataProvider
	public Object[][] getTestData() {
		
		 Object[][] data = excelDataProvider.getExcelData("Sheet2");
		 return data;
	}

}
