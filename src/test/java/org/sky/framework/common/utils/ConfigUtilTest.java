package org.sky.framework.common.utils;

import org.sky.framework.common.config.ConfigUtil;
import org.sky.framework.common.config.Configuration;

import junit.framework.TestCase;

public class ConfigUtilTest extends TestCase{

	public void testCofig(){
		Configuration configuration = ConfigUtil.getInstance().getConfigurationDefault();
		
		assertEquals(true, configuration.isAnnotation());
		assertEquals(3, configuration.getBeans().size());
		assertEquals(1, configuration.getActions().size());
		
		
	}
}
