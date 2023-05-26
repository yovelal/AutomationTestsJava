package AJ.AutomationJava;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.Home;
import Pages.Login;
import Util.EmailManager;
import Util.Messages;
import Util.User;
import io.qameta.allure.Allure;

public class LoginTest extends BaseTestUI {
	
	
	
	@Test
	public void loginRegularUser() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		Login loginPage = this.login(home, User.UserType.REGULAR);
		Allure.step("fiil login form and click on the submit button");
		Assert.assertTrue(home.verifyLogin() , "The user was not logged in");
		Allure.step("The user is logged in");
	}
	
	@Test
	public void loginLecturer() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		Login loginPage = this.login(home, User.UserType.LECTURER);
		Allure.step("fiil login form and click on the submit button");
		Assert.assertTrue(home.verifyLogin() , "The user was not logged in");
		Allure.step("The user is logged in");
	}
	
	@Test
	public void loginLecturer2FA() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);

		
		try {
			// fetch 2 factor auth code from email:
			User user = User.getUser(User.UserType.LECTURER2FA);
			Login loginPage = home.clickSignIn();
			Allure.step("clicked sign in - redirect to login page");
			loginPage.login(user);
			Allure.step("fiil login form and click on the submit button");
			Assert.assertTrue(loginPage.verify2FactorPage());
			Allure.step("redirected to two-step verification page");
			
	        EmailManager emailManager = new EmailManager(user.email, user.password);
	        loginPage.sleep(10);
	        String code = emailManager.readAndDeleteLastEmail();
	        Assert.assertNotEquals(code, "No content found." , "The varification code was not found");
			Allure.step("fetch the verification code from email");
			loginPage.fiilAndSubmitCode(code);
			Assert.assertTrue(home.verifyLogin() , "The user was not logged in");
			Allure.step("The user is logged in");
		}
		catch(Exception e) {
		      Assert.fail(e.getMessage());
		}
	}

	
	
	@Test
	public void wrongEmail() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		
		try {
			// fetch 2 factor auth code from email:
			User user = User.getUser(User.UserType.REGULAR);
			Login loginPage = home.clickSignIn();
			Allure.step("clicked sign in - redirect to login page");
			loginPage.login("wrong"+user.email, user.password);
			Allure.step("try to login with wrong email");
			List<WebElement> errors = loginPage.getErrors();
			Assert.assertNotNull(errors, "could not find errors");
	        Assert.assertEquals(loginPage.getErrorsText(errors), Messages.wrongEmail , "missing error:" +Messages.wrongEmail);
			Allure.step("error message");

		}
		catch(Exception e) {
		      Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void wrongPassword() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		
		try {
			// fetch 2 factor auth code from email:
			User user = User.getUser(User.UserType.REGULAR);
			Login loginPage = home.clickSignIn();
			Allure.step("clicked sign in - redirect to login page");
			loginPage.login(user.email,"wrong"+ user.password);
			Allure.step("try to login with wrong password");
			List<WebElement> errors = loginPage.getErrors();
			Assert.assertNotNull(errors, "could not find errors");
	        Assert.assertEquals(loginPage.getErrorsText(errors), Messages.wrongPassword , "missing error" + Messages.wrongPassword);
			Allure.step("error message");

		}
		catch(Exception e) {
		      Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void wrong2FA() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);

		
		try {
			// fetch 2 factor auth code from email:
			User user = User.getUser(User.UserType.LECTURER2FA);
			Login loginPage = home.clickSignIn();
			Allure.step("clicked sign in - redirect to login page");
			loginPage.login(user);
			Allure.step("fiil login form and click on the submit button");
			Assert.assertTrue(loginPage.verify2FactorPage());
			Allure.step("redirected to two-step verification page");

			Allure.step("filling wrong code");
			loginPage.fiilAndSubmitCode("0000");
			Assert.assertFalse(home.verifyLogin() , "The user was logged in");
			List<WebElement> errors = loginPage.getErrors();
			Assert.assertNotNull(errors, "could not find errors");
			Allure.step("found error message");
	        Assert.assertEquals(loginPage.getErrorsText(errors), Messages.wrong2FACode, "missing error" + Messages.wrong2FACode);
		}
		catch(Exception e) {
		      Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void expired2FACode() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);

		
		try {
			// fetch 2 factor auth code from email:
			User user = User.getUser(User.UserType.LECTURER2FA);
			Login loginPage = home.clickSignIn();
			Allure.step("clicked sign in - redirect to login page");
			loginPage.login(user);
			Allure.step("fiil login form and click on the submit button");
			Assert.assertTrue(loginPage.verify2FactorPage());
			Allure.step("redirected to two-step verification page");
			
	        EmailManager emailManager = new EmailManager(user.email, user.password);
	        loginPage.sleep(10);
	        String code = emailManager.readAndDeleteLastEmail();
	        Assert.assertNotEquals(code, "No content found." , "The varification code was not found");
			Allure.step("fetch the verification code from email and wait 5 minutes");
			loginPage.sleep(360);
			loginPage.fiilAndSubmitCode(code);
			Assert.assertFalse(home.verifyLogin() , "The user was logged in");
			List<WebElement> errors = loginPage.getErrors();
			Assert.assertNotNull(errors, "could not find errors");
			Allure.step("found error message");
	        Assert.assertEquals(loginPage.getErrorsText(errors), Messages.expired2FACode, "missing error" + Messages.expired2FACode);
		}
		catch(Exception e) {
		      Assert.fail(e.getMessage());
		}
	}

	
}
