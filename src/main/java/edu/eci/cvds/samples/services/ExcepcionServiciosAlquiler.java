package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{
	
	public ExcepcionServiciosAlquiler(String message) {
		super(message);
	}
	public ExcepcionServiciosAlquiler(String message, Throwable e) {
		super(message);
	}
}
