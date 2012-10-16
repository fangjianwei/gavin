package org.sky.framework.common.utils;

import junit.framework.TestCase;

public class ClassScanUtilsTest extends TestCase{
	
	
	public void testScanByFile(){
		ClassScanUtils.getClass("org.sky.framework.common.utils.logging");
		ClassScanUtils.getClass("org.sky.framework.common.utils.logging.*");
	}
	
	public void testScanByJar(){
		ClassScanUtils.getClass("org.dom4j");
		ClassScanUtils.getClass("org.dom4j.*");
	}
	
	public void testScanWithEmpty(){
		ClassScanUtils.getClass("abc.kkk.*");
		ClassScanUtils.getClass("");
	}
}
