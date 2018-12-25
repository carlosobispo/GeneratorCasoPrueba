package com.generador.prueba.bean;

import java.io.Serializable;
import java.util.List;

public class ValidateBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private String metodo;
	private List<ParameterBean> parameters;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public List<ParameterBean> getParameters() {
		return parameters;
	}
	public void setParameters(List<ParameterBean> parameters) {
		this.parameters = parameters;
	}
	@Override
	public String toString() {
		return "ValidateBean [tipo=" + tipo + ", metodo=" + metodo + ", parameters=" + parameters + "]";
	}
	
	
}
