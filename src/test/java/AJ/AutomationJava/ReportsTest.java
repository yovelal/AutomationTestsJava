package AJ.AutomationJava;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.GeneralReport;
import Pages.GeneralReport.Option;
import Pages.Home;
import Util.User;
import io.qameta.allure.Allure;

public class ReportsTest extends BaseTestUI {
	@Test
	public void generalReports() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		this.login(home, User.UserType.LECTURER);
		GeneralReport page = home.goToGeneralReports();
		Allure.step("navigate to general report page");
		
		page.chooseReport(Option.GENDER);
		Assert.assertTrue(page.verifyGraph(),"Could not find graph for gender");
		page.chooseReport(Option.LOGIN_REGISTER);
		Assert.assertTrue(page.verifyGraph(),"Could not find graph for login & register");
		page.chooseReport(Option.AGE);
		Assert.assertTrue(page.verifyGraph(),"Could not find graph for AGE");
		Allure.step("Verify graph for each option");
		
		//page.downloadReport();
		
		page.sleep(20);

	}
}
