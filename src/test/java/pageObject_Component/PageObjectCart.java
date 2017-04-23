package pageObject_Component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_Component.BaseClass;
import io.appium.java_client.AppiumDriver;

public class PageObjectCart extends BaseClass {
	
	//public AppiumDriver driver;
	
	//second section
	
	@FindBy(id="com.bigbasket.mobileapp:id/btnAddToBasket")
	public WebElement btn_Add;
		
	@FindBy(id="com.bigbasket.mobileapp:id/basketimageview")
	public WebElement Img_Cart;	
		
	@FindBy(id="com.bigbasket.mobileapp:id/txtProductDesc")
	public WebElement AddCart_msg;	
	
	@FindBy(id="com.bigbasket.mobileapp:id/imgRemove")
	public WebElement btn_Delete;	
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
	public WebElement Delete_msg;	
	
	
	public PageObjectCart(AppiumDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	
	//3rd section is creating methods
	
	public void Additem()
	{
		explicitWait(btn_Add,25);
		btn_Add.click();
		explicitWait(Img_Cart, 25);
		Img_Cart.click();
		explicitWait(AddCart_msg,25);
		
	}
	
	
	public String getAddcartmsg()
	{		
		return AddCart_msg.getText();
	}
	
	public String DeletCartmsg()
	{
		btn_Delete.click();
		explicitWait(Delete_msg, 20);
		return Delete_msg.getText();
	}

	
	

}
