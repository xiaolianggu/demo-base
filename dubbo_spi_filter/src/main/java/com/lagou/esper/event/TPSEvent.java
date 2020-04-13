package com.lagou.esper.event;

public class TPSEvent {
	private Long currentTime;
	private String methodName;
	private Integer invokeTime;

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Integer getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(Integer invokeTime) {
		this.invokeTime = invokeTime;
	}

	@Override
	public String toString() {
		return "TPSEvent [currentTime=" + currentTime + ", methodName=" + methodName + ", invokeTime=" + invokeTime
				+ "]";
	}

}
