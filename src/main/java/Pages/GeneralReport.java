package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralReport extends BasePage {

	public static enum Option {
	    GENDER,
	    LOGIN_REGISTER,
	    AGE;
	}
	
	
	public GeneralReport(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void clickOnDropDown() {
		WebElement dropdown = this.findElementsByAttribute("id", "headlessui-popover-button-:r8:", null).get(0);
		this.click(dropdown);
	}
	
	public void chooseReport(Option op) {
		this.clickOnDropDown();
		WebElement optionsDiv = this.findElementsByAttribute("id", "dropdown-options", null).get(0);
		String option_text;
		switch(op) {
			case GENDER:
				option_text = "Gender";
				break;
			case LOGIN_REGISTER:
				option_text = "Login & Registeration";
				break;
			case AGE:
				option_text = "Age";
				break;
			default:
				return;
		}
		WebElement option = this.findElementsByTagAndText("p", option_text, optionsDiv).get(0);
		this.click(option);
		this.sleep(1);
	}
	
	public boolean verifyGraph() {
		List<WebElement> graph = this.findElementsByTag("canvas", null);
		return graph.size() > 0;
	}
	
	void downloadReport() {
		WebElement button = this.findElementsByTagAndText("button", "Export data", null).get(0);
		this.click(button);
	}

}
