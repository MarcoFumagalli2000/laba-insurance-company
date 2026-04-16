package com.solvd.InsuranceCompany.items;

import com.solvd.InsuranceCompany.enums.VehicleType;
import com.solvd.InsuranceCompany.exceptions.InvalidValue;
import com.solvd.InsuranceCompany.people.Client;

import java.util.Objects;

public class Vehicle extends InsurancedItem {
    private VehicleType type;
    private String brand;
    private String model;
    private String fabricationYear;
    private String plate;
    private String engineNumber;
    public Vehicle(VehicleType type,Client owner, double value, String brand, String model, String fabricationYear, String plate, String engineNumber) throws InvalidValue {
        super(owner, value);
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.plate = plate;
        this.engineNumber = engineNumber;
    }
    public VehicleType getType() {
        return type;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getFabricationYear() {
        return fabricationYear;
    }
    public void setFabricationYear(String fabricationYear) {
        this.fabricationYear = fabricationYear;
    }
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getEngineNumber() {
        return engineNumber;
    }
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
    @Override
    public String toString() {
        return this.getBrand() + " " + this.getModel() + " " + getFabricationYear();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getBrand(), vehicle.getBrand()) && Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(getFabricationYear(), vehicle.getFabricationYear());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getFabricationYear());
    }
}
