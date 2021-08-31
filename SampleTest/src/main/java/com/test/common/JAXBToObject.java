package com.test.common;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBToObject<E> {
	private Class<E> type;
	
	public JAXBToObject(Class<E> type) {
		this.type=type;
	}
	
	@SuppressWarnings("unchecked")
	public Object unmarshal(String file) {
		Object obj=null;
		try {
			JAXBContext context=JAXBContext.newInstance(type);
			Unmarshaller unmarshaller=context.createUnmarshaller();
			obj =(E) unmarshaller.unmarshal(new File(file + ".xml"));
		}
		catch(JAXBException e) {
			e.printStackTrace();
			
		}
		return obj;
	}
	
	

	

}
