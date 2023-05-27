package AJ.AutomationJava;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import Pages.Feedback;
import Pages.Home;
import Util.Messages;
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
		feedbackPage.fillFormAndSubmit("test add feedback", "test add feedback", "");
		Allure.step("submit valid feedback with contact info");
		List<WebElement> alerts = feedbackPage.getAlert();
		Assert.assertNotEquals(alerts.size(), 0);
		String alert = home.getElementText(alerts.get(0));
		Assert.assertEquals(alert,Messages.submitFeedback);
		Allure.step(Messages.submitFeedback);

	}
	
	
    @Test(dependsOnMethods = { "addFeedback"})
	public void deleteFeedback() {
		WebDriver driver = this.driverThreadLocal.get();
		Home home = new Home(driver);
		this.login(home, User.UserType.LECTURER);
		Feedback feedbackPage = home.clickFeedback();
		Allure.step("click feedback");
		int numOfFeedbacks =feedbackPage.getNumberOfFeedbacks();
		boolean isSelected = feedbackPage.selectFeedbackInPageByText("test add feedback");
		Assert.assertTrue(isSelected, "Feedback with content:'test add feedback' was not found");
		Allure.step("select feedback");
		feedbackPage.clickDelete();
		Assert.assertEquals(numOfFeedbacks - 1, feedbackPage.getNumberOfFeedbacks());
		Allure.step("delete feedback");
    }

	
}
