package com.generador.prueba.bean;

import java.io.Serializable;

public class ValidateDetailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String atributo;
	private String tipo;
	private String longitud;
	private String obligatoriedad;
	private String campoBackend;
	
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getObligatoriedad() {
		return obligatoriedad;
	}
	public void setObligatoriedad(String obligatoriedad) {
		this.obligatoriedad = obligatoriedad;
	}
	public String getCampoBackend() {
		return campoBackend;
	}
	public void setCampoBackend(String campoBackend) {
		this.campoBackend = campoBackend;
	}
	@Override
	public String toString() {
		return "ValidateDetailBean [atributo=" + atributo + ", tipo=" + tipo + ", longitud=" + longitud
				+ ", obligatoriedad=" + obligatoriedad + ", campoBackend=" + campoBackend + "]";
	}
	
	
}
