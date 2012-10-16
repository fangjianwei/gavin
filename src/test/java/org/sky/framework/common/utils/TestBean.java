package org.sky.framework.common.utils;

public class TestBean {
	private String name;
	
	private int age;
	
	private String add;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
	
	public String toString(){
		return "name="+name+",age="+age+",add="+add;
	}
	
	
}
