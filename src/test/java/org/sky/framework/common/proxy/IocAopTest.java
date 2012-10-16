package org.sky.framework.common.proxy;

import junit.framework.TestCase;

import org.sky.framework.common.utils.IService;
import org.sky.framework.common.utils.MyAction;
import org.sky.framework.common.utils.ServiceTest;

public class IocAopTest extends TestCase{
	
	
	public void testIocAndAop(){
		IService service = (IService) BeanFactory.getBean("ServiceImple");
		service.what();
		IService service2 = (IService) BeanFactory.getBean("ServiceImple");
		service2.what();
		assertEquals(true,service.hashCode()==service2.hashCode());
		
		
		ServiceTest serviceTest = (ServiceTest)BeanFactory.getBean("ser");
		serviceTest.sayHello();
		
		MyAction myAction = (MyAction) BeanFactory.getControlledBean("ddd");
		
		MyAction myAction2 = (MyAction) BeanFactory.getControlledBean("ddd");
		
		assertEquals(false,myAction.hashCode()==myAction2.hashCode());

		System.out.println(myAction.hashCode()+","+myAction2.hashCode());
		
		myAction.hello();
	}
}
