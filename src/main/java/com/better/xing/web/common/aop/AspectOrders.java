package com.better.xing.web.common.aop;

public interface AspectOrders {

	int BASE_ORDER = -100;


	public int LOG_ORDER = BASE_ORDER + 1;

	public int EXCEPTION_HANDLE_ORDER = LOG_ORDER + 1;
	
	public int VALIDATE_ORDER = EXCEPTION_HANDLE_ORDER + 1;
	
	public int AUTHCHECK_ORDER = VALIDATE_ORDER + 1;


}
