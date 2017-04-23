package pageObject_Component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class PageObjectSearch {
	
	
	public AndroidDriver driver;
	
	@FindBy(id="com.bigbasket.mobileapp:id/homePageSearchBox")
	public WebElement searchProduct;
	
	@FindBy(id="com.bigbasket.mobileapp:id/searchView")

	public WebElement search;
	
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
	public WebElement invalidSearchMessage;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtProductCount")
	public WebElement validSearchMessage;
	
	public PageObjectSearch(AndroidDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnSearch()
	{
		searchProduct.click();
	}
	
	
	public void enterTextInSearch(String str) throws InterruptedException
	{
		search.sendKeys(str+"\n");
		
	
	}
	
	public String getInvalidSearchMessage()
	{
		
		return invalidSearchMessage.getText();
	}
	
	public String getValidSearchMessage()
	{
		
		return validSearchMessage.getText();
	}
	
	

}
