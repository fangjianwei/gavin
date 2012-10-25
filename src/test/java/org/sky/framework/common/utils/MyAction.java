package org.sky.framework.common.utils;

import java.util.List;
import java.util.Set;

import org.sky.framework.common.annotation.Controller;
import org.sky.framework.common.annotation.Inject;
import org.sky.framework.common.annotation.Scope;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;
import org.sky.framework.web.core.dispatcher.DispatcherServlet;
import org.sky.framework.web.core.result.JSONResult;
import org.sky.framework.web.core.result.RedirectResult;
import org.sky.framework.web.core.result.Result;

@Controller("ddd")
@Scope("prototype")
public class MyAction {

	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	
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
    	System.out.println("================MyAction.test(int a,float b),a="+a+",b="+b);
    }
    
    public void test2( Integer a,Float b){
    	System.out.println("================MyAction.test(Integer a,Float b),a="+a+",b="+b);
    }
	
    public Result redirect(String a,int b){
    	System.out.println("================MyAction.redirect(String a,int b),a="+a+",b="+b);
    	return new RedirectResult("/test.jsp");
    }
    
    public Result forward(String a,int b){
    	System.out.println("================MyAction.forward(String a,int b),a="+a+",b="+b);
    	return new RedirectResult("/test.jsp");
    }
    
    public Result jsonOutprint(String a,int b, TestBean bean ){
    	log.info("================MyAction.jsonOutprint(String a,int b,TestBean bean),a="+a+",b="+b+","+bean.toString());
    	return new JSONResult(bean,"utf-8");
    }
}
