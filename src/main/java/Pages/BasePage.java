package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public abstract class BasePage {
	WebDriver driver;
	
	public BasePage(WebDriver driver){
		this.driver = driver;
	}
	
	
	
	public void navigateTo(String url) {
		this.driver.get(url);
	}
	public void closeDriver() {
		this.driver.close();;
	}
	
	public String getUrl() {
		return this.driver.getCurrentUrl();
	}
	
	/////////////// actions on elements
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void sendKeys(WebElement element, String keys) {
		element.sendKeys(keys);
	}
	
	
	///////////////find element methods
	
	public List<WebElement> findElementsByClass(String locatorValue, WebElement parentElement) {
		if (parentElement!=null)
			return parentElement.findElements(By.className(locatorValue));
		return driver.findElements(By.className(locatorValue));
	}
	
	public List<WebElement> findElementsByTag(String locatorValue, WebElement parentElement) {
		if (parentElement!=null)
			return parentElement.findElements(By.tagName(locatorValue));
		return driver.findElements(By.tagName(locatorValue));
	}
	
	public List<WebElement> findElementsByText(String locatorValue, WebElement parentElement) {
		String xpath = "//*[text()='"+ locatorValue + "']";
		if (parentElement!=null)
			return parentElement.findElements(By.xpath(xpath));
		return driver.findElements(By.xpath(xpath));
	}
	
	
	public List<WebElement> findElementsByTagAndText(String tag,String text, WebElement parentElement) {
		String xpath = "//"+ tag +"[text()='"+ text + "']";
		if (parentElement!=null)
			return parentElement.findElements(By.xpath(xpath));
		return driver.findElements(By.xpath(xpath));
	}
	
	public List<WebElement> findElementsByAttribute(String attributeName,String attributeValue, WebElement parentElement) {
		String xpath = "//*[@"+ attributeName+ "='"+ attributeValue + "']";
		if (parentElement!=null)
			return parentElement.findElements(By.xpath(xpath));
		return driver.findElements(By.xpath(xpath));
	}
	
	public List<WebElement> findElements(String locatorType, String locatorValue, WebElement parentElement) {
		switch (locatorType) {
		case "class": {
			return this.findElementsByClass(locatorValue,parentElement);
		}
		case "tag": {
			return this.findElementsByTag(locatorValue,parentElement);
		}
		case "text": {
			return this.findElementsByText(locatorValue,parentElement);
		}
		default:
			throw new IllegalArgumentException("Unexpected locator type: " + locatorType);
		}
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	
	public void sleep(float sec) {
		try {
			Thread.sleep((long) (1000 * sec));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	

	
	
	// TODO Auto-generated method stub
	public static void main(String[] args) {
		

		

		//settings the driver executable
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();


		//options.addArguments("--remote-allow-origins=*");  

		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver();
	
		//Applied wait time
		//maximize window
		driver.manage().window().maximize();
	
		//open browser with desried URL
		driver.get("https://ds-sce.sce-fpm.com/");
	
		//closing the browser
	}
}
