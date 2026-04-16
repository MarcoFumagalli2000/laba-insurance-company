package com.solvd.InsuranceCompany.interfaces;

@FunctionalInterface
public interface ITheft<T> {
	boolean check(T record);
}
