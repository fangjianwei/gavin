package org.sky.framework.common.utils;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

public class GavinBeanUtilsTest {
	
	@Test
	public void getMethodByName(){
		Method method= GavinBeanUtils.getMethodByName("getMethodByName", GavinBeanUtils.class);
		Assert.assertNotNull(method);
	}
	
}
