package org.sky.framework.common.utils;

import org.sky.framework.common.annotation.Service;


@Service("ser")
public class ServiceTest {
	public void sayHello(){
		System.out.println("ServiceTest说：this is first service test by ioc");
	}
}
