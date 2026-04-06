package com.solvd.InsuranceCompany.Tracking;

import com.solvd.InsuranceCompany.Exceptions.UnauthorizedRequest;
import com.solvd.InsuranceCompany.Interfaces.IStatus;
import com.solvd.InsuranceCompany.People.Client;

import java.util.Objects;


public class ClientRequests implements IStatus {

	private String requestType;
	private int requestValue;
	private int requestNumber;
	private boolean isSolved;
	private Client client;

	public ClientRequests(String requestType, int requestValue, int requestNumber, boolean isSolved, Client client) {
		this.requestType = requestType;
		this.requestValue = requestValue;
		this.requestNumber = requestNumber;
		this.isSolved = isSolved;
		this.client = client;
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

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean solved) {
		isSolved = solved;
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
		if (this.isSolved) {
			return "STATUS: Completed. Your liquidation of $" + payout + " has been aprobed.";
		} else {
			return "STATUS: Processing. Our employees are reviewing your request #" + this.getRequestNumber();
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
