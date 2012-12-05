package org.sky.framework.dao.data;

import java.util.Date;
import java.util.List;

import org.sky.framework.dao.annotation.Column;
import org.sky.framework.dao.annotation.Id;
import org.sky.framework.dao.annotation.Ref;
import org.sky.framework.dao.annotation.Table;
import org.sky.framework.dao.annotation.Transient;

@Table("person")
public class PersonTest {

	@Transient
	private String trans = "trans";
	
	@Id
	private int id;
	
	@Column
	private String name;
	
	@Column("add")
	private String address;
	
	@Column
	private String email;
	
	@Column
	private Date birthdary;
	
	@Column
	@Transient("0")
	private int status;

	@Ref(refName="oneToMany",refKey={"id","name"},targetKey={"personId","personName"},otherConditions="cur.status!=0 and ref.status!=0")
	private List<EduTest> edu;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdary() {
		return birthdary;
	}

	public void setBirthdary(Date birthdary) {
		this.birthdary = birthdary;
	}

	public List<EduTest> getEdu() {
		return edu;
	}

	public void setEdu(List<EduTest> edu) {
		this.edu = edu;
	}
	
	
	
}
