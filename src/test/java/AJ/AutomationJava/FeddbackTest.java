package AJ.AutomationJava;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import Pages.Feedback;
import Pages.Home;
import Util.User;
import io.qameta.allure.Allure;

public class FeddbackTest extends BaseTestUI {
	
	
	@Test
	public void addFeedback() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		this.login(home, User.UserType.REGULAR);
		Feedback feedbackPage = home.clickAddFeedback();
		Allure.step("click add feedback");
		feedbackPage.fillFormAndSubmit("my feedback", "test add feedback", "");
		Allure.step("submit valid feedback without contact info");
		List<WebElement> alerts = feedbackPage.getAlert();
		Assert.assertNotEquals(alerts.size(), 0);
		String alert = home.getElementText(alerts.get(0));
		Assert.assertEquals(alert,"Feedback submitted successfully");
		Allure.step("Feedback submitted successfully");

	}
    @Test(dependsOnMethods = { "addFeedback"})
    public void deleteFeedback() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		this.login(home, User.UserType.LECTURER);
    }

	
}
