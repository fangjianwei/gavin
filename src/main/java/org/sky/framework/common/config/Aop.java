package org.sky.framework.common.config;

import java.util.HashMap;
import java.util.Map;

public class Aop {

	private String id;
	
	private String ref;
	
	private Map<String,AOPPointCut> pointcut;
	
	private Map<String,AOPPointCutRequest> pointcutRequest;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public Map<String, AOPPointCut> getPointcut() {
		return pointcut;
	}
	
	public AOPPointCut getPointcut( String key ){
		if(this.pointcut==null) return null;
		return this.pointcut.get(key);
	}


	public void setPointcut(Map<String, AOPPointCut> pointcut) {
		this.pointcut = pointcut;
	}
	
	public void addPointcut( AOPPointCut pointCut ){
		if( pointCut==null ) return;
		
		if( this.pointcut==null ){
			this.pointcut = new HashMap<String, AOPPointCut>();
		}
		
		this.pointcut.put(pointCut.getId(), pointCut);
	}

	public Map<String, AOPPointCutRequest> getPointcutRequest() {
		return pointcutRequest;
	}
	
	public AOPPointCutRequest getPointcutRequest( String key ){
		if(this.pointcutRequest==null) return null;
		return this.pointcutRequest.get(key);
	}

	public void setPointcutRequest(Map<String, AOPPointCutRequest> pointcutRequest) {
		this.pointcutRequest = pointcutRequest;
	}

	public void addPointcutRequest(String key, AOPPointCutRequest pointCutReq ){
		if( pointCutReq==null ) return;
		
		if( this.pointcutRequest==null ){
			this.pointcutRequest = new HashMap<String, AOPPointCutRequest>();
		}
		
		this.pointcutRequest.put(key, pointCutReq);
	}

	public AOPPointCut createPointcut(){
		return new AOPPointCut();
	}
	
	public AOPPointCutRequest createAOPPointCutRequest(){
		return new AOPPointCutRequest();
	}

	public class AOPPointCut{
		private String id;
		private String expression;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getExpression() {
			return expression;
		}
		public void setExpression(String expression) {
			this.expression = expression;
		}
	}
	
	public class AOPPointCutRequest{
		private String request;
		
		private String method;
		
		private String pointcutref;

		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}
		
		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getPointcutref() {
			return pointcutref;
		}

		public void setPointcutref(String pointcutref) {
			this.pointcutref = pointcutref;
		}
		
		
	}
}
