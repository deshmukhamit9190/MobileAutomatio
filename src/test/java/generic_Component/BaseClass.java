package generic_Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {


	static String appium_node_path="C:\\Appium\\node.exe";
	static String appium_js_path="C:\\Appium\\node_modules\\appium\\bin\\appium.js";
	static AppiumDriverLocalService service;
	public static AndroidDriver driver;


	public static void start_Server() throws InterruptedException
	{

		service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
				.usingDriverExecutable(new File(appium_node_path)).withAppiumJS(new File(appium_js_path)));


		service.start();
		if(service.isRunning())
		{
			System.out.println("started the appium server");
		}
		else
		{
			System.out.println("not able to start the appium server");
		}
		
		Thread.sleep(8000);
	}

	public static void init_App() throws InterruptedException, MalformedURLException
	{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//device details 
		capabilities.setCapability("deviceName", "Lenovo A7020a48");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0");

		//app details 
		capabilities.setCapability("appPackage", "com.bigbasket.mobileapp");
		capabilities.setCapability("appActivity", "com.bigbasket.mobileapp.activity.SplashActivity");

		//appium server details

		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(10000);	
	}
	public static void stop_server() throws InterruptedException
	{
		if(service.isRunning())
		{
			service.stop();
			Thread.sleep(4000);
			System.out.println("stopped the appium server");
		}
	}


	public void explicitWait(WebElement ele, long t1)
	{
		WebDriverWait wait=new WebDriverWait(driver,t1);

		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
	}
    public void screenshot_Capture(String TC_ID,String Order_Set) throws IOException
    {  
    	Date date=new Date();
    	SimpleDateFormat df=new SimpleDateFormat("yyy-mm-dd hh-mm-ss");
    	String str=df.format(date)+".png";
   
    	
    	TakesScreenshot takeScreen=(TakesScreenshot)driver;
    	
    	File screenshotAs = takeScreen.getScreenshotAs(OutputType.FILE);
    	
    	FileUtils.copyFile(screenshotAs, new File("C:\\rohit\\localWorkspace\\March4_BB_project\\Screenshot\\"+TC_ID+"-"+Order_Set+str));
    }

}
