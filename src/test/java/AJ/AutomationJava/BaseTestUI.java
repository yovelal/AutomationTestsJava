package AJ.AutomationJava;

import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import Pages.Home;
import Pages.Login;
import Util.Messages;
import Util.Url;
import Util.User;
import Util.User.UserType;
import io.qameta.allure.Allure;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestUI {
	
    protected ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	
	@BeforeMethod(alwaysRun=true)
	public void setup(ITestContext context, ITestResult result) {
		 System.out.println("setup");
		
		// Configure ChromeOptions with desired download directory
        ChromeOptions options = new ChromeOptions();
        String baseDirectory = System.getProperty("user.dir");
        String downloadDir = baseDirectory + "\\src\\main\\reasorces" ;
        
        Map<String, Object> prefs = new HashMap<String, Object>(); 
        prefs.put("download.default_directory", downloadDir);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver=new ChromeDriver(options);

		 driverThreadLocal.set(driver);
		 driver.manage().window().maximize();
		 Test testAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
	        // Extract any relevant information from the test annotation
        String testName = testAnnotation.testName();
        int invocationCount = testAnnotation.invocationCount();
        //System.out.println(testName+" "+invocationCount);
        Allure.step("open driver");

	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		 System.out.println("teardown");
		 WebDriver driver = driverThreadLocal.get();
        driver.close();
        driverThreadLocal.remove();
        Allure.step("close driver");
	}
	
	public Login login(Home page, UserType type) {
		Login loginPage = page.clickSignIn();
		User user;
		try {
			user = User.getUser(type);
			loginPage.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		return loginPage;
	}
	
	
}
