package scenerio_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic_Component.BaseClass;
import pageObject_Component.PageObjectCart;
import pageObject_Component.PageObjectSearch;




public class Scenerio_Cart extends BaseClass {

	public static Logger log=Logger.getLogger(Scenerio_Cart.class);
	
	@Test(dataProvider="dp_addCart",dataProviderClass=dataProvider_Component.DataProvider_Cart.class,enabled=false)
	public void testAddCart(Map cart) throws InterruptedException, IOException
	{
		SoftAssert sAssert=new SoftAssert();
		String TC_ID=cart.get("TC_ID").toString();
		String Order_Set=cart.get("Order_Set").toString();
		String Search_Item=cart.get("Search_Item").toString();
		String Exp_Result=cart.get("Exp_Result").toString();
		
		
		start_Server();
		init_App();
		
		
		PageObjectSearch searchPage=new PageObjectSearch(driver);
		explicitWait(searchPage.searchProduct, 20);
		searchPage.clickOnSearch();
		explicitWait(searchPage.search, 20);
		searchPage.enterTextInSearch(Search_Item);
		
		
		PageObjectCart cartPage=new PageObjectCart(driver);
		cartPage.Additem();
		
		String Actual_Result = cartPage.getAddcartmsg();
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);
			screenshot_Capture(TC_ID,Order_Set);
		}
		else
		{
			log.info("Failed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);	
			screenshot_Capture(TC_ID,Order_Set);
			sAssert.fail("Failed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);	
		}
		
		stop_server();
		sAssert.assertAll();
	}
	
	
    @Test(dataProvider="dp_deleteCart",dataProviderClass=dataProvider_Component.DataProvider_Cart.class)	
    public void testDeleteCart(Map cart) throws InterruptedException, IOException
    {
    	SoftAssert sAssert=new SoftAssert();
		String TC_ID=cart.get("TC_ID").toString();
		String Order_Set=cart.get("Order_Set").toString();
		String Search_Item=cart.get("Search_Item").toString();
		String Exp_Result=cart.get("Exp_Result").toString();
		
		
		start_Server();
		init_App();
		
		
		PageObjectSearch searchPage=new PageObjectSearch(driver);
		explicitWait(searchPage.searchProduct, 20);
		searchPage.clickOnSearch();
		explicitWait(searchPage.search, 20);
		searchPage.enterTextInSearch(Search_Item);
		
		
		PageObjectCart cartPage=new PageObjectCart(driver);
		cartPage.Additem();
		
		String Actual_Result = cartPage.DeletCartmsg();
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);
			screenshot_Capture(TC_ID,Order_Set);
		}
		else
		{
			log.info("Failed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);	
			screenshot_Capture(TC_ID,Order_Set);
			sAssert.fail("Failed as Actual Result is "+Actual_Result + "Ecpected Result is "+Exp_Result);	
		}
		
		stop_server();
		sAssert.assertAll();
    	
    	
    }
	
	
}
