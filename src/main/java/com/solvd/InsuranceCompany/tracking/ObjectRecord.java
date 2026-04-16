package com.solvd.InsuranceCompany.tracking;

import com.solvd.InsuranceCompany.exceptions.IncompleteRecord;
import com.solvd.InsuranceCompany.interfaces.IBrakes;
import com.solvd.InsuranceCompany.interfaces.ITheft;

public class ObjectRecord extends Records implements IBrakes {

	private static final ITheft<ObjectRecord> THEFT_VALIDATION = ObjectRecord::isStolen;
	private int totalBrakes;
	private boolean isStolen;

	public ObjectRecord(String info, String details, ClientRequests request, int brokenObjectNumber)
		throws IncompleteRecord {
		this(info, details, request, brokenObjectNumber, false);
	}

	public ObjectRecord(String info, String details, ClientRequests request, int brokenObjectNumber, boolean isStolen)
		throws IncompleteRecord {
		super(info, details, request);
		if (request == null) {
			throw new IncompleteRecord("Object Records cannot be created without a valid ClientRequest.");
		}
		this.totalBrakes = brokenObjectNumber;
		this.isStolen = isStolen;
	}

	public int getBrokenObjectNumber() {
		return totalBrakes;
	}

	public void setBrokenObjectNumber(int brokenObjectNumber) {
		this.totalBrakes = brokenObjectNumber;
	}

	public boolean isStolen() {
		return isStolen;
	}

	public void setStolen(boolean stolen) {
		isStolen = stolen;
	}

	public boolean hasTheftReport() {
		return THEFT_VALIDATION.check(this);
	}

	@Override
	public void recordNewBrake() {
		this.totalBrakes++;
	}

	@Override
	public int totalBrakes() {
		return this.totalBrakes;
	}
}
