package com.test.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="scenario")
@XmlSeeAlso({Iteration.class})
@XmlAccessorType(XmlAccessType.FIELD)

public class Scenario {
	
@XmlAttribute	

private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@XmlElement(name="iteration")
private List<Iteration> iterations;

public List<Iteration> getIterations() {
	return iterations;
}

public void setIterations(List<Iteration> iterations) {
	this.iterations = iterations;
}

@Override
public String toString() {
	String txtOut="";
	for(Iteration it : this.getIterations())
		txtOut +=this.getMethods(it);
	
	return txtOut;
}

public String getMethods(Object o) {
	String txtOut= "";
	for (Method m:o.getClass().getMethods()) {
		if(m.getName().startsWith("get") && m.getParameterTypes().length == 0 && !m.getName().equals("getClass")) {
			try {
				final Object r=m.invoke(o);
				txtOut=txtOut+ m.getName().substring(3,m.getName().length()).toLowerCase() + "." +r+",";
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch(InvocationTargetException e) {
				e.printStackTrace();
			}
			catch(IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	txtOut= txtOut + "]\n";
	return "TestCase [name: " + this.name +", "+ txtOut;
		
}

public String getTestData(String iterationData, String dataName) {
	
	String txtOut="";
	for (Iteration it : this.getIterations()) {
		if(it.getId().equals(iterationData))
			for(Method m : it.getClass().getMethods()) {
				if(m.getName().toLowerCase().equals("get" + dataName) && m.getParameterTypes().length==0) {
					try {
						Object r=m.invoke(it);
						txtOut=(String) r;
						}
					catch(IllegalArgumentException e) {
						e.printStackTrace();
					}
					catch(InvocationTargetException e) {
						e.printStackTrace();
					}
					catch(IllegalAccessException e) {
						e.printStackTrace();
					}
				}
					
			}
	}
	return txtOut;
			
}


}
