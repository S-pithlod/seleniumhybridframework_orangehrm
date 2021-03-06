package com.orangehrm.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.utilities.Helper;

public class AddUserPage {

	@FindBy(id = "btnAdd")
	@CacheLookup
	WebElement btnAdd;

	@FindBy(id = "systemUser_userType")
	@CacheLookup
	WebElement systemUserDD;

	@FindBy(id = "systemUser_employeeName_empName")
	@CacheLookup
	WebElement EmployeeName;

	@FindBy(id = "systemUser_userName")
	@CacheLookup
	WebElement username;

	@FindBy(id = "systemUser_status")
	@CacheLookup
	WebElement systemUser_statusDD;

	@FindBy(id = "systemUser_password")
	@CacheLookup
	WebElement userPassword;

	@FindBy(id = "systemUser_confirmPassword")
	@CacheLookup
	WebElement confirmpass;

	@FindBy(id = "btnSave")
	@CacheLookup
	WebElement btnSave;

	WebDriver driver;

	public AddUserPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void addNewUser(String selectRole, String employeeName, String userName, String status, String password,
			String confirmPassword) {
		try {
			btnAdd.click();
			Helper.selectDropDownValue(systemUserDD, selectRole);
			EmployeeName.sendKeys(employeeName);
			username.sendKeys(userName);

			Helper.selectDropDownValue(systemUser_statusDD, status);

			userPassword.sendKeys(password);
			confirmpass.sendKeys(confirmPassword);
			btnSave.click();

		} catch (Exception e) {

		}

	}

}
