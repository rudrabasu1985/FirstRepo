
package com.test.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "framework")
public class FrameworkData {
	private String name;

	private String chromedriver;
	private String firefoxdriver;
	
	private String iedriver;
	private int maxwaittime;
	private int highwaittime;
	private int medwaittime;
	private int lowwaittime;
	private String optexcel;
	private String appurl;
	private String testurl;
	
	private String appurl2;
	
	


	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@XmlElement
	public String getChromedriver() {
		return chromedriver;
	}
	public void setChromedriver(String chromedriver) {
		this.chromedriver = chromedriver;
	}
	
	@XmlElement
	public String getFirefoxdriver() {
		return firefoxdriver;
	}
	public void setFirefoxdriver(String firefoxdriver) {
		this.firefoxdriver = firefoxdriver;
	}
	
	
	@XmlElement
	public String getIedriver() {
		return iedriver;
	}
	public void setIedriver(String iedriver) {
		this.iedriver = iedriver;
	}
	
	@XmlElement
	public int getMaxwaittime() {
		return maxwaittime;
	}
	public void setMaxwaittime(int maxwaittime) {
		this.maxwaittime = maxwaittime;
	}
	
	@XmlElement
	public int getHighwaittime() {
		return highwaittime;
	}
	public void setHighwaittime(int highwaittime) {
		this.highwaittime = highwaittime;
	}
	
	@XmlElement
	public int getMedwaittime() {
		return medwaittime;
	}
	public void setMedwaittime(int medwaittime) {
		this.medwaittime = medwaittime;
	}
	
	@XmlElement
	public int getLowwaittime() {
		return lowwaittime;
	}
	public void setLowwaittime(int lowwaittime) {
		this.lowwaittime = lowwaittime;
	}
	
	@XmlElement
	public String getOptexcel() {
		return optexcel;
	}
	public void setOptexcel(String optexcel) {
		this.optexcel = optexcel;
	}
	
	@XmlElement
	public String getAppurl() {
		return appurl;
	}
	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}	
	
	@XmlElement
	public String getTesturl() {
		return testurl;
	}
	public void setTesturl(String testurl) {
		this.testurl = testurl;
	}
	
	
	@XmlElement
	public String getAppurl2() {
		return appurl2;
	}
	public void setAppurl2(String appurl2) {
		this.appurl2 = appurl2;
	}	
}
