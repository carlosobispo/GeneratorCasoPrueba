package com.generador.prueba.principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.fachada.IGeneradorPrueba;
import com.generador.prueba.fachada.impl.GeneradorPruebaExcel;

public class AppPrincipal {
	
	private static final Logger LOG =LoggerFactory.getLogger(AppPrincipal.class);
	
	public static void main(String[] args) {
		LOG.info("========== Inicio generacion de casos de prueba ==========");
		IGeneradorPrueba generadorPrueba = new GeneradorPruebaExcel();
		generadorPrueba.procesarArchivo();
	}
	
}
