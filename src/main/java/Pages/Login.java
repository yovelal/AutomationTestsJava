package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Util.User;

public class Login extends BasePage {

	public Login(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickSignIn() {
		WebElement button = this.findElementsByAttribute("type", "submit", null).get(0);
		this.click(button);
	}
	
	public void login(User user) {
		List<WebElement> loginFormElements = this.findElements("tag","input", null);
		this.sendKeys(loginFormElements.get(1), user.email);
		this.sendKeys(loginFormElements.get(2), user.password);
		clickSignIn();
		this.sleep(2);	
	}

}
