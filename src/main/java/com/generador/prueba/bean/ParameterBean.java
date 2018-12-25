package com.generador.prueba.bean;

import java.io.Serializable;

public class ParameterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String variable;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return "ParameterBean [tipo=" + tipo + ", variable=" + variable + "]";
	}
}
