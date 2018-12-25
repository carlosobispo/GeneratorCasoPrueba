package com.generador.prueba.fachada.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.bean.ProcesoBean;
import com.generador.prueba.dao.IGeneradorPruebaDao;
import com.generador.prueba.dao.impl.GeneradorPruebaDaoImpl;
import com.generador.prueba.fachada.IGeneradorPrueba;
import com.generador.prueba.util.bundle.ResourceBundleUtil;

public class GeneradorPruebaExcel implements IGeneradorPrueba{

	private static final Logger LOG =LoggerFactory.getLogger(GeneradorPruebaExcel.class);
	
	public void procesarArchivo() {

		try {
			LOG.info("... invoke GeneradorPruebaExcel.procesarArchivo ...");
			String path_file = ResourceBundleUtil.getResourceValue("path_file");
			LOG.info("ruta archivo a procesar : "+ path_file);
			
			FileInputStream fileInputStream = new FileInputStream(path_file); 
			IGeneradorPruebaDao generadorPruebaDao= new GeneradorPruebaDaoImpl();
			ProcesoBean procesoBean= generadorPruebaDao.getProcesoBean(fileInputStream);
			
		} catch (FileNotFoundException e1) {
			LOG.error("No se encontro archivo, " ,e1);
		}catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
