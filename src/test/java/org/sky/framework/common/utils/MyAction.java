package org.sky.framework.common.utils;

import java.util.List;
import java.util.Set;

import org.sky.framework.common.annotation.Controller;
import org.sky.framework.common.annotation.Inject;
import org.sky.framework.common.annotation.Scope;

@Controller("ddd")
@Scope("prototype")
public class MyAction {

	private List<Integer> list;
	private Set<String> set;
	private String a;
	private String[] ab;
	private int c;
	private int[] cc;
	private Integer[] cci;
	private float f;
	private float[] ff;
	private Float[] fff;
	private double d;
	private double[] dd;
	private Double[] ddd;
	private short ss;
	private Short sss;
	private short[] ssss;
	private Short[] sssss;
	
	@Inject(id="ser")
	private ServiceTest helloworld;
	
	@Inject(id="ServiceImple")
	private IService service;

	public void setHelloworld(ServiceTest helloworld) {
		this.helloworld = helloworld;
	}
	
	public void setService(IService service) {
		this.service = service;
	}



	public void hello(){
		helloworld.sayHello();
	}
	
	public void serviceH(){
		service.what();
	}
	
    public void test( int a,float b){
    	
    }
    
    public void test2( Integer a,Float b){
    	
    }
	
}
