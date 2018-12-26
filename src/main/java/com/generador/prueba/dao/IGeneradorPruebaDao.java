package com.generador.prueba.dao;

import java.io.FileInputStream;

import com.generador.prueba.bean.ProcesoBean;

public interface IGeneradorPruebaDao {
	
	ProcesoBean getProcesoBean(FileInputStream archivoProcesar);
	
	void generateIntegrationTest(ProcesoBean procesoBean);
}
