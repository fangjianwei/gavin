package org.sky.framework.common.utils;

public class GavinBeanToXmlTest {
	
	public void toXML(){
		TestBean bean = new TestBean();
		bean.setName("fangjianwei");
		bean.setAge(30);
		bean.setAdd("oak");
		
		GavinBeanToXml.toXml(bean, "utf-8");
	}
}
