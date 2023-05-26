package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Util.Url;


public class Home extends BasePage {
	
	
	public Home(WebDriver driver) {
		super(driver);
		driver.get(Url.base);
	}
	
	public Login clickSignIn() {
		WebElement button = this.findElements("text", "Sign in", null).get(0);
		this.click(button);
		this.sleep(2);
		return new Login(this.driver);

	}
	
	public boolean verifyLogin() {
		List<WebElement> button = this.findElementsByTagAndText("div", "Log Out", null);
		return button.size() != 0;
	}
	
	
	public Register clickSignUp() {
		WebElement button = this.findElements("text", "Sign up", null).get(0);
		this.click(button);
		return new Register(this.driver);
	}
	
	public Feedback clickAddFeedback() {
		WebElement button = this.findElementsByTagAndText("a", "Add Feedback", null).get(0);
		this.click(button);
		return new Feedback(this.driver);
	}
	
	public Feedback clickFeedback() {
		WebElement button = this.findElementsByTagAndText("a", "Feedback", null).get(0);
		this.click(button);
		this.sleep(5);
		return new Feedback(this.driver);
	}
	
	
	

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		
		//Applied wait time
		Timeouts implicitlyWait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();
	
		//open browser with desried URL
		driver.get("https://ds-sce.sce-fpm.com/");
		Home home = new Home(driver);
		Login login = home.clickSignIn();
		//login.login("tovel","pass");

	}
}


