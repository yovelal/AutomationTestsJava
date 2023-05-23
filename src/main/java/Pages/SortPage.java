package Pages;

import java.sql.Array;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SortPage extends BasePage {

	public SortPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickRandom() {
		WebElement buttonElement = this.findElementsByTagAndText("button", "Random", null).get(0);
		this.click(buttonElement);
	}
	
	public void clickSort() {
		WebElement buttonElement = this.findElementsByTagAndText("button", "Sort", null).get(0);
		this.click(buttonElement);
	}
	
	public void clickSetInput() {
		WebElement buttonElement = this.findElementsByTagAndText("button", "Set", null).get(0);
		this.click(buttonElement);
	}
	
	public void enterInput(String input){
		WebElement inputElement = this.findElementsByTag("input", null).get(0);
		this.sendKeys(inputElement, input);
	}
	
	public boolean isErrorMessage() {
		List<WebElement> errorMsg = this.findElementsByClass("swal-text", null);
		return errorMsg.size()!=0;
	}
	
	public String getErrorMessage(WebElement error) {
		return this.getElementText(error);
	}
	
	public List<WebElement> getArrays(){
		WebElement divElement = this.findElements("class", "basis-9/12", null).get(0);
		return this.findElements("tag", "ul", divElement);
	}
	
	public int[] convertWebArrayToArray(WebElement ulArr){
		List<WebElement> liElements = this.findElements("tag", "li", ulArr);
		int size = liElements.size();
		int[] arr = new int[size];
		for (int i =0; i<size; i++) {
			arr[i] = Integer.parseInt(this.getElementText(liElements.get(i)));
		}
		return arr;
	}
	
	

}
