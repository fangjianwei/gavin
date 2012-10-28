package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;
import org.sky.framework.common.utils.GavinBeanToXml;

public class XMLResult implements Result{
	
	private static final String defaultEncoding = "utf-8";
	
	private Object bean;
	
	private String encoding;
	
	public XMLResult( Object bean,String encoding ){
		this.bean = bean;
		this.encoding = encoding;
	}

	public String getResultType() {
		return ResultType.outprint.getValue();
	}

	public String getResult() {
		return GavinBeanToXml.toXml(bean, encoding);
	}

	public String getCharacterEncoding() {
		String characterEncoding = defaultEncoding;
		if( encoding!=null&&!"".equals(encoding) ){
			characterEncoding = encoding;
		}
		return characterEncoding;
	}	

	public String getContentType() {
		return "text/xml";
	}
}
