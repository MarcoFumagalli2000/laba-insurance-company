package com.solvd.InsuranceCompany.tracking;

import com.solvd.InsuranceCompany.exceptions.IncompleteRecord;
import com.solvd.InsuranceCompany.interfaces.ICrashes;

import java.util.Objects;

public class VehicleRecords extends Records implements ICrashes {

	private int crashNumbers;

	public VehicleRecords(String info, String details, ClientRequests request, int crashNumbers) throws IncompleteRecord{
		super(info, details, request);
		if (request == null) {
			throw new IncompleteRecord("Vehicle records cannot be created without a valid ClientRequest.");
		}
		this.crashNumbers = crashNumbers;
	}

	public int getCrashNumbers() {
		return crashNumbers;
	}

	public void setCrashNumbers(int crashNumbers) {
		this.crashNumbers = crashNumbers;
	}

	@Override
	public void recordNewCrash() {
		this.crashNumbers++;
	}

	@Override
	public int crashNumbers() {
		return this.crashNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		VehicleRecords that = (VehicleRecords) o;
		return crashNumbers == that.crashNumbers;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(crashNumbers);
	}
}
