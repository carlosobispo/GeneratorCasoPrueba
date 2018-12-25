package com.generador.prueba.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelUtil {
	
	private static final Logger LOG =LoggerFactory.getLogger(ExcelUtil.class);
	
	private static ExcelUtil INSTANCE;
			
	private Integer numeroHojaProcesar;
	private FileInputStream archivoProcesar;
	private XSSFWorkbook worbook;
	private XSSFSheet hojaExcel;
	
	private ExcelUtil(int numeroHojaProcesar, FileInputStream archivoProcesar){
		this.numeroHojaProcesar = numeroHojaProcesar;
		this.archivoProcesar =archivoProcesar;
		initializeExcel();
	}

	public synchronized static ExcelUtil getInstance(Integer numeroHojaProcesar,
			FileInputStream archivoProcesar){
		if(INSTANCE == null) {
			INSTANCE =  new ExcelUtil(numeroHojaProcesar, archivoProcesar);
		}
		return INSTANCE;
	}
	
	private void initializeExcel(){
		try {
			LOG.info("...invoke ExcelUtil.initializeExcel ...");
			worbook = new XSSFWorkbook(archivoProcesar);
			hojaExcel = worbook.getSheetAt( numeroHojaProcesar);
		} catch (IOException e) {
			LOG.error("Error al inicializar excel ", e);
		}
	}
	
	public Iterator<Row> getFilasExcel(){
		return hojaExcel.iterator();
	}
	
	
	public String getValueCell( String coordenada ){
		CellReference ref = new CellReference(coordenada); 
		Row filaExcel = hojaExcel.getRow(ref.getRow()); 
		if (filaExcel != null) { 
		    Cell celda = filaExcel.getCell( ref.getCol() );
		    return celda.getStringCellValue();
		} 
		return "";
	}
	
	public Integer getNumeroHojaProcesar() {
		return numeroHojaProcesar;
	}

	public void setNumeroHojaProcesar(Integer numeroHojaProcesar) {
		this.numeroHojaProcesar = numeroHojaProcesar;
	}

	public FileInputStream getArchivoProcesar() {
		return archivoProcesar;
	}

	public void setArchivoProcesar(FileInputStream archivoProcesar) {
		this.archivoProcesar = archivoProcesar;
	}
	
	
	
}
