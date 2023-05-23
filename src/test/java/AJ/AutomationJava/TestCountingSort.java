package AJ.AutomationJava;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Pages.Home;
import Pages.Login;
import Util.User;
import io.qameta.allure.Allure;


public class TestCountingSort extends BaseTestUI {
	
	@Test(invocationCount = 1)
	public void myTestMethod2() {
		System.out.println("myTestMethod2");
		WebDriver driver = this.driverThreadLocal.get();
		driver.get("https://ds-sce.sce-fpm.com/");
//		Home home = new Home(driver);
//		Login loginPage = home.clickSignIn();
//		User user;
//		try {
//			user = User.getUser("lecturer");
//			loginPage.login(user);
//		} catch (Exception e) {
//			fail();
//		}
//        try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        assertEquals(true, true);
    }
	
	@Test
	public void myTestMethod() {
		WebDriver driver = driverThreadLocal.get();
		driver.get("https://google.com/");
		 System.out.println("myTestMethod");

        assertEquals(true, true);
    }
	
}
