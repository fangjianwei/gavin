package org.sky.framework.dao.data;

import org.sky.framework.dao.annotation.Column;
import org.sky.framework.dao.annotation.Id;
import org.sky.framework.dao.annotation.Table;

@Table
public class EduTest {
	
	@Column
	@Id
	private int id;
	
	@Column
	private int personId;
	
	@Column
	private String personName;
	
	@Column
	private String name;
	
	@Column
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
