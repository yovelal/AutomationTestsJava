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
		this.login(user.email,user.password);
	}
	
	public void login(String email, String password) {
		List<WebElement> loginFormElements = this.findElements("tag","input", null);
		this.sendKeys(loginFormElements.get(1), email);
		this.sendKeys(loginFormElements.get(2), password);
		clickSignIn();
		this.sleep(4);	
	}
	
	public List<WebElement> getErrors(){
		return this.findElementsByTag("li", null);
	}
	
	public String getErrorsText(List<WebElement> errors) {
		if (errors == null) {
			errors = this.getErrors();
		}
		String text = "";
		for (WebElement e: errors) {
			text += this.getElementText(e);
		}
		return text;
	}
	
	/////// 2FA /////////
	
	public boolean verify2FactorPage() {
		return this.getUrl().contains("2fa"); 
	}
	
	public void fiilAndSubmitCode(String code) {
		WebElement input = this.findElementsByAttribute("id", "code", null).get(0);
		this.sendKeys(input, code);
		WebElement button = this.findElementsByAttribute("type", "submit", null).get(0);
		this.click(button);
		this.sleep(4);	
		
	}

}
