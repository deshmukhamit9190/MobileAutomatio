package scenerio_Component;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic_Component.BaseClass;
import pageObject_Component.PageObjectSearch;

public class Scenerio_Search extends BaseClass {
    

	public static Logger log=Logger.getLogger(Scenerio_Search.class);


	@Test(dataProvider="dp_InvalidSearch",dataProviderClass=dataProvider_Component.DataProvider_Search.class,groups="smoke")
	public void testInvalidSearch(Map search) throws MalformedURLException, InterruptedException
	{
		SoftAssert sAssert=new SoftAssert();
		String TC_ID=search.get("TC_ID").toString();
		String Order_Set=search.get("Order_Set").toString();
		String Search_Item=search.get("Search_Item").toString();
		String Exp_Result=search.get("Exp_Result").toString();


		start_Server();
		
		log.info("Executing the Testcase  "+TC_ID+" Order set is "+Order_Set);
		init_App();
		PageObjectSearch searchPage=new PageObjectSearch(driver);
		searchPage.clickOnSearch();
		explicitWait(searchPage.search, 10);
		searchPage.enterTextInSearch(Search_Item);
		explicitWait(searchPage.invalidSearchMessage, 10);
		String Actual_Result = searchPage.getInvalidSearchMessage();

		if(Actual_Result.equals(Exp_Result))
		{
		    log.info("Passed as Actual message is "+Actual_Result+" Expected message is "+Exp_Result);
		}
		else
		{
			log.info("Failed as Actual msg is "+Actual_Result+ " Expected msg is "+Exp_Result);;
			sAssert.fail("Failed as Actual msg is "+Actual_Result+ " Expected msg is "+Exp_Result);
		}

		stop_server();	
		sAssert.assertAll();
	}

	
	@Test(dataProvider="dp_ValidSearch",dataProviderClass=dataProvider_Component.DataProvider_Search.class,groups="regression")
	public void testValidSearch(Map search) throws MalformedURLException, InterruptedException
	{   //Need to create the instance in every method otherwise getting the old state
		SoftAssert sAssert=new SoftAssert();
		String TC_ID=search.get("TC_ID").toString();
		String Order_Set=search.get("Order_Set").toString();
		String Search_Item=search.get("Search_Item").toString();
		String Exp_Result=search.get("Exp_Result").toString().replace(".0", "");


		start_Server();
		init_App();
		PageObjectSearch searchPage=new PageObjectSearch(driver);
		searchPage.clickOnSearch();
		explicitWait(searchPage.search, 10);
		searchPage.enterTextInSearch(Search_Item);
		explicitWait(searchPage.validSearchMessage, 10);
		String Actual_Result = searchPage.getValidSearchMessage().replace(" products", "");

		if(Actual_Result.equals(Exp_Result))
		{
		    log.info("Passed as Actual message is "+Actual_Result+" Expected message is "+Exp_Result);
		}
		else
		{
			log.info("Failed as Actual msg is "+Actual_Result+ " Expected msg is "+Exp_Result);;
			sAssert.fail("Failed as Actual msg is "+Actual_Result+ " Expected msg is "+Exp_Result);
		}
		stop_server();	
		sAssert.assertAll();
	}


}
