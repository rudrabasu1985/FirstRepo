package com.test.common;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="testdata")
@XmlSeeAlso({Scenario.class})
@XmlAccessorType(XmlAccessType.FIELD)

public class TestData {
@XmlElement(name="scenario")  //
private List<Scenario> scenario;

public List<Scenario> getTestdata(){
	return scenario;
		
}

public void setTestdata(List <Scenario> scenario) {
	this.scenario=scenario;
}
	

}
