package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

public class RedirectResult implements Result{

	public String getResultType() {
		return ResultType.redirect.getValue();
	}

	public String getjumpURL() {
		// TODO Auto-generated method stub
		return null;
	}

}
