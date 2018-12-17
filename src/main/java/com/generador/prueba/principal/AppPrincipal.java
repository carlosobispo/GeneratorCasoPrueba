package com.generador.prueba.principal;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.util.bundle.ResourceBundleUtil;

public class AppPrincipal {
	
	private static final Logger LOG =LoggerFactory.getLogger(AppPrincipal.class);
	
	public static void main(String[] args) {
		
		try {
			LOG.info("========== Inicio generacion de casos de prueba ==========");
			String path_file = ResourceBundleUtil.getResourceValue("path_file");
			LOG.info("ruta archivo a procesar : "+ path_file);
			
			FileInputStream fileInputStream = new FileInputStream(path_file); 
		
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(fileInputStream);
			//obtener la hoja que se va leer
			XSSFSheet hojavalidacion = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = hojavalidacion.iterator();

			Row filaValidacion;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				System.out.println("FILAAAAA");
				filaValidacion = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = filaValidacion.cellIterator();
				Cell celdaFila;
				//se recorre cada celda
				int i = 0;
				while (cellIterator.hasNext()) {
					i++;
					// se obtiene la celda en específico y se la imprime
					celdaFila = cellIterator.next();
					System.out.println("valor :" +celdaFila.getStringCellValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
