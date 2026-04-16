package com.solvd.InsuranceCompany.tracking;

public abstract class Records {

	private String info;
	private String details;
	private ClientRequests request;

	public Records(String info, String details, ClientRequests request) {
		this.info = info;
		this.details = details;
		this.request = request;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ClientRequests getRequest() {
		return request;
	}

	public void setRequest(ClientRequests request) {
		this.request = request;
	}
}
