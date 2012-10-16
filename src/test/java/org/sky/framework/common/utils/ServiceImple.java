package org.sky.framework.common.utils;

import org.sky.framework.common.annotation.Service;

@Service
public class ServiceImple implements IService{
	
	public void what() {
		System.out.println("ServiceImple说：我实现了接口");
	}
	
}
