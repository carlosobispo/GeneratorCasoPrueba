package com.generador.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {
	
	public static void main(String[] args) {
		
		String nombreArchivo = "Inventario.xlsx";
		String rutaArchivo = "E:\\Ficheros-Excel\\" + nombreArchivo;
		try {
		
			FileInputStream file = new FileInputStream(new File(rutaArchivo)); 
		
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet hojavalidacion = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = hojavalidacion.iterator();

			Row filaValidacion;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
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
					if(i ==2) System.out.print(celdaFila.getStringCellValue()+" | ");
					if(i ==3) System.out.print((int)celdaFila.getNumericCellValue()+" | ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
