package com.orangehrm.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	// WebElement repository at page level
	
	WebDriver driver;
	
	@FindBy(name="txtUsername")
	WebElement username;
	
	@FindBy(name = "txtPassword")
	WebElement userpassword;
	
	@FindBy(id = "btnLogin")
	WebElement loginbtn;
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public HomePage loginOrangeHrm(String uname, String upass )
	{
		
		//username.sendKeys("Admin");
		//userpassword.sendKeys("admin123");
		
		//loginbtn.click();
		
		username.sendKeys(uname);
		userpassword.sendKeys(upass);
		loginbtn.click();
		
		return new HomePage(driver);
		
		
		
	}

}
