package org.sky.framework.common.proxy;

import org.sky.framework.common.utils.IService;
import org.sky.framework.common.utils.ServiceTest;

import junit.framework.TestCase;

public class BeanFactoryTest extends TestCase{

	public void testGetBean(){
		
		IService service = (IService) BeanFactory.getBean("ServiceImple");
		service.what();
		
		ServiceTest serviceTest = (ServiceTest)BeanFactory.getBean("ser");
		serviceTest.sayHello();
	}
}
