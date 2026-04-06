package com.solvd.InsuranceCompany.Items;

import com.solvd.InsuranceCompany.Exceptions.InvalidValue;
import com.solvd.InsuranceCompany.People.Client;

import java.util.Objects;
public class Vehicle extends InsurancedItem {
    private String carBrand;
    private String model;
    private String fabricationYear;
    private String carPlate;
    private String engineNumber;
    public Vehicle(Client owner, double value, String carBrand, String model, String fabricationYear, String carPlate, String engineNumber) throws InvalidValue {
        super(owner, value);
        this.carBrand = carBrand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.carPlate = carPlate;
        this.engineNumber = engineNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
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
    public String getCarPlate() {
        return carPlate;
    }
    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
    public String getEngineNumber() {
        return engineNumber;
    }
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
    @Override
    public String toString() {
        return this.getCarBrand() + " " + this.getModel() + " " + getFabricationYear();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getCarBrand(), vehicle.getCarBrand()) && Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(getFabricationYear(), vehicle.getFabricationYear());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getCarBrand(), getModel(), getFabricationYear());
    }
}
