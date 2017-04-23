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



public class DataProvider_Search {


	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object[]> getInvalidSerachData() throws IOException
	{
          List<Object[]> loadData = loadData("InvalidSearch");
          return loadData.iterator();
	}
	@DataProvider(name="dp_ValidSearch")
	public static Iterator<Object[]> getValidSerachData() throws IOException
	{
		 List<Object[]> loadData = loadData("ValidSearch");
         return loadData.iterator();
	}
	public static List<Object[]>  loadData(String scriptname) throws IOException
	{

		ExcelReadWrite x1=new ExcelReadWrite("C:\\rohit\\localWorkspace\\March4_BB_project\\TestData\\TestData.xls");

		HSSFSheet sheet1=x1.Setsheet("Scenario_Search");

		int rowCount=x1.getrowcount(sheet1);
		int colCount=x1.getcolcount(sheet1, 0);

		System.out.println("row and column are "+rowCount +"###"+colCount);

		List<Object[]> arrList=new ArrayList<Object[]>();
		for(int i=1;i<=rowCount;i++)
		{

			
			String execute_Flag=   x1.Readvalue(sheet1, i, "Execute_Flag")	;
			String script_Name=x1.Readvalue(sheet1, i, "Script_name");
			if(execute_Flag.equals("Y") && script_Name.equals(scriptname))
			{
				Map<String, String> mp=new HashMap<String, String>();
				
				for(int j=0;j<=colCount;j++)
				{
					
					String sKey=x1.Readvalue(sheet1, 0, j);
					String value=x1.Readvalue(sheet1, i, j);
					mp.put(sKey, value);
				}	
				
				Object[]x=new Object[1];
				
				x[0]=mp;
				System.out.println(x[0]);
				arrList.add(x);
			}

		}

       return arrList;
	}


}
