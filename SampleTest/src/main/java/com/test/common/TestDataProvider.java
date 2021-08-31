package com.test.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import net.bytebuddy.asm.Advice.Return;

public class TestDataProvider {
	
	
	@DataProvider(name="AM")
	public static Iterator<Object[]> provideData19(){
		List<Object[]> data= dataExtractor("AM");
		return data.iterator();
				}

	/*
	 * @DataProvider(name="CHANGE PASSWORD") public static Iterator<Object[]>
	 * provideData20(){ List<Object[]> data= dataExtractor("CHANGE PASSWORD");
	 * return data.iterator(); }
	 */
	private static List<Object[]> dataExtractor(String myTest)
	{
		List <Object[]> data=new ArrayList<Object[]>();
		JAXBToObject<TestData> jaxbobj=new JAXBToObject<TestData>(TestData.class);
		//TestData tdobj=(TestData) jaxbobj.unmarshal(System.getProperty("user.dir")+"\\Automation");
		TestData tdobj=(TestData) jaxbobj.unmarshal(System.getProperty("user.dir") + "\\resources\\TestData\\Test\\Test");
		
		for(Scenario td : tdobj.getTestdata())
			if(td.getName().equals(myTest))
				for(Iteration it:td.getIterations())
					data.add(new Object[] {it});
		return data;
	}

}


