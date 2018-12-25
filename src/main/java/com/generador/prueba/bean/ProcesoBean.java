package com.generador.prueba.bean;

import java.io.Serializable;
import java.util.List;

public class ProcesoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ValidateBean validateBean;
	private List<ValidateDetailBean> validateDetailBeans;
	
	public ValidateBean getValidateBean() {
		return validateBean;
	}
	public void setValidateBean(ValidateBean validateBean) {
		this.validateBean = validateBean;
	}
	public List<ValidateDetailBean> getValidateDetailBeans() {
		return validateDetailBeans;
	}
	public void setValidateDetailBeans(List<ValidateDetailBean> validateDetailBeans) {
		this.validateDetailBeans = validateDetailBeans;
	}
	@Override
	public String toString() {
		return "ProcesoBean [validateBean=" + validateBean + ", validateDetailBeans=" + validateDetailBeans + "]";
	}
	
	
}
