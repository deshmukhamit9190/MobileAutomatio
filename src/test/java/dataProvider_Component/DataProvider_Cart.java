package dataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic_Component.ExcelReadWrite;

public class DataProvider_Cart {

	@DataProvider(name="dp_addCart")
	public static Iterator<Object[]> getAddCartData() throws IOException
	{
		List<Object[]> loadCartData = loadCartData("AddCart");
        return loadCartData.iterator();
	}

	@DataProvider(name="dp_deleteCart")
	public static Iterator<Object[]> getDeleteCartData() throws IOException
	{

		List<Object[]> loadCartData = loadCartData("DeleteCart");
	    return loadCartData.iterator();
	}
  
	public static List<Object[]> loadCartData(String scriptName) throws IOException
	{

		ExcelReadWrite x=new ExcelReadWrite("C:\\rohit\\localWorkspace\\March4_BB_project\\TestData\\TestData.xls");
		HSSFSheet scenarioCart = x.Setsheet("Scenario_Cart");

		//to get row and column of the sheet
		int rowCount = x.getrowcount(scenarioCart);
		int colCount=x.getcolcount(scenarioCart, 0);
		
		
		System.out.println("row and column count is "+rowCount+" ### "+colCount);
		
		List<Object[]> arrayList = new ArrayList<Object[]>();

		//loop for row
		for(int i=1;i<=rowCount;i++)
		{

			String execute_Flag = x.Readvalue(scenarioCart, i, "Execute_Flag");
			String script_Name = x.Readvalue(scenarioCart, i,"Script_name");

			if(execute_Flag.equals("Y") && script_Name.equals(scriptName))
			{

				Map<String,String> dMap=new HashMap<String, String>();

                //for iterating through the column
				for(int j=0;j<colCount;j++)
				{
				String key = x.Readvalue(scenarioCart, 0, j);
                String value = x.Readvalue(scenarioCart, i, j);

			    dMap.put(key, value);
				
				}
                System.out.println("value of map is "+dMap);
                Object[]x1=new Object[1];
                x1[0]=dMap;
                arrayList.add(x1);

			}
		}
		return arrayList;
	}






}
