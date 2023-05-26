package Pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Feedback extends BasePage {

	public Feedback(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	////// USER SIDE => ADD FEEDBACK ///////
	
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
	
	
	////// LECTURER SIDE => MANAGE FEEDBACK ///////

	public List<WebElement> getFeedbacksInPage(){
		List<WebElement> feedbacks = this.findElementsByAttribute("role", "row", null);
		feedbacks.remove(0);
		return feedbacks;
	}
	
	public String openFeedback(WebElement feedback) {
		WebElement div = this.findElementsByTag("div", feedback).get(1);
		WebElement button = this.findElementsByTag("button", div).get(0);
		this.click(button);
		this.sleep(2);
		WebElement content = this.findElementsByTag("p", null).get(0);
		String text = this.getElementText(content);
		this.click(button);
		return text;
	}
	
	public void selectFeedback(WebElement feedback) {
		WebElement button = this.findElementsByAttribute("type", "checkbox", feedback).get(0);
		this.click(button);
	}
	
	public void clickDelete() {
		WebElement button = this.findElementsByTagAndText("button", "Delete", null).get(0);
		this.click(button);
		this.sleep(3);
	}
	
	public int getNumberOfFeedbacks() {
		WebElement selectAll = this.findElementsByAttribute("name", "select-all-rows", null).get(0);
		this.click(selectAll);
		int num = this.getNumberOfFeedbacksSelected();
		this.click(selectAll);
		return num;
	}
	
	public int getNumberOfFeedbacksSelected() {
		WebElement header = this.findElementsByAttribute("role", "heading", null).get(0);
		String text = this.getElementText(this.findElementsByTag("div", header).get(1));
		if (text == null) {
			return 0;
		}
		String[] words = text.split(" "); 
		return Integer.valueOf(words[0]);
	}
	
	public boolean selectFeedbackInPageByText(String text) {
		List<WebElement> feedbacks =  this.getFeedbacksInPage();
		for(WebElement feedback: feedbacks) {
			if (text.equals(this.openFeedback(feedback))) {
				this.selectFeedback(feedback);
				return true;
			}
		}
		return false;
	}
}
