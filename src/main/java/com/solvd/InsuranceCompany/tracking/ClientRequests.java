package com.solvd.InsuranceCompany.tracking;

import com.solvd.InsuranceCompany.enums.RequestStatus;
import com.solvd.InsuranceCompany.exceptions.UnauthorizedRequest;
import com.solvd.InsuranceCompany.interfaces.IStatus;
import com.solvd.InsuranceCompany.interfaces.IValidation;
import com.solvd.InsuranceCompany.people.Client;

import java.util.Objects;


public class ClientRequests implements IStatus {

	private static final IValidation REQUEST_VALIDATION =
		req -> req != null && req.getStatus() != null;
	private String requestType;
	private int requestValue;
	private int requestNumber;
	private RequestStatus status;
	private Client client;

	public ClientRequests(String requestType, int requestValue, int requestNumber, RequestStatus status, Client client) {
		this.requestType = requestType;
		this.requestValue = requestValue;
		this.requestNumber = requestNumber;
		this.client = client;
		setStatus(status);
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getRequestValue() {
		return requestValue;
	}

	public void setRequestValue(int requestValue) {
		this.requestValue = requestValue;
	}

	public int getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		RequestStatus previousStatus = this.status;
		this.status = status;
		if (!REQUEST_VALIDATION.isValid(this)) {
			this.status = previousStatus;
			throw new IllegalArgumentException("Request status cannot be null.");
		}
	}

	public boolean hasValidStatus() {
		return REQUEST_VALIDATION.isValid(this);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void updateRequestValue(Client requester, int newValue) {
		if (!this.client.equals(requester)) {
			throw new UnauthorizedRequest("Access Denied: Client " + requester.getName() + " does not have access to this request.");
		}
		this.requestValue = newValue;
	}

	@Override
	public String toString() {
		return "Your request " + this.getRequestType() + " #" + getRequestNumber();
	}

	@Override
	public String getStatusMessage(double payout) {
		if (!hasValidStatus()) {
			throw new IllegalStateException("Request status is invalid.");
		}
		if (this.status == RequestStatus.APPROVED) {
			return "STATUS: Completed. Your liquidation of $" + payout + " has been approved.";
		} else if (this.status == RequestStatus.PENDING) {
			return "STATUS: Processing. Our employees are reviewing your request #" + this.getRequestNumber();
		} else {
			return "STATUS: Rejected.";
		}
    }

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		ClientRequests requests = (ClientRequests) o;
		return getRequestNumber() == requests.getRequestNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getRequestNumber());
	}
}
