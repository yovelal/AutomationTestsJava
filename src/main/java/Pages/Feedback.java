package Pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Feedback extends BasePage {

	public Feedback(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void fillSubject(String subject) {
		WebElement input = this.findElementsByAttribute("placeholder", "Subject", null).get(0);
		this.sendKeys(input, subject);
	}
	
	public void fillFeedback(String feedback) {
		WebElement input = this.findElementsByAttribute("placeholder", "Enter your feedback", null).get(0);
		this.sendKeys(input, feedback);
	}
	
	public void fillContact(String contact) {
		WebElement input = this.findElementsByAttribute("placeholder", "Contact Info (Optional)", null).get(0);
		this.sendKeys(input, contact);
	}
	
	public void SubmitFeedback() {
		WebElement button = this.findElementsByAttribute("type", "submit", null).get(0);
		this.click(button);
	}
	
	public void fillFormAndSubmit(String subject, String feedback, String contact) {
		this.fillSubject(subject);
		this.fillFeedback(feedback);
		this.fillContact(contact);
		this.SubmitFeedback();
		this.sleep(2);
	}
	
	public List<WebElement> getAlert() {
		List<WebElement> alerts = this.findElementsByAttribute("role", "alert", null);
		return alerts;
	}
	
}
