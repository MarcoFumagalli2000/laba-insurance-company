package com.solvd.InsuranceCompany.Items;

import com.solvd.InsuranceCompany.Enums.ObjectType;
import com.solvd.InsuranceCompany.Exceptions.InvalidValue;
import com.solvd.InsuranceCompany.People.Client;

import java.util.Objects;

public class OtherObjects extends InsurancedItem {

	private String name;
	private ObjectType type;
	private String brand;
	private int quantity;

	public OtherObjects(Client owner, double value, String name, ObjectType type, String brand, int quantity) throws InvalidValue {
		super(owner, value);
		this.name = name;
		this.type = type;
		this.brand = brand;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return this.getQuantity() + " " + getName() + " " + getType() + " " + getBrand();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		OtherObjects that = (OtherObjects) o;
		return (
			Objects.equals(getName(), that.getName()) &&
			Objects.equals(getType(), that.getType()) &&
			Objects.equals(getBrand(), that.getBrand())
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getType(), getBrand());
	}
}
