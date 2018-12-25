package com.generador.prueba.dao.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.bean.ParameterBean;
import com.generador.prueba.bean.ProcesoBean;
import com.generador.prueba.bean.ValidateBean;
import com.generador.prueba.bean.ValidateDetailBean;
import com.generador.prueba.dao.IGeneradorPruebaDao;
import com.generador.prueba.util.ExcelUtil;

public class GeneradorPruebaDaoImpl implements IGeneradorPruebaDao{
	
	private static final Logger LOG =LoggerFactory.getLogger(GeneradorPruebaDaoImpl.class);
	
	public ProcesoBean getProcesoBean(FileInputStream archivoProcesar) {
		ProcesoBean procesoBean = null;
		try {
			
			ExcelUtil excelUtil = ExcelUtil.getInstance(0, archivoProcesar);
			procesoBean = new ProcesoBean();
			procesoBean.setValidateBean( getValidateBean(excelUtil) );
			procesoBean.setValidateDetailBeans( getValidateDetailBean(excelUtil) );

			System.out.println( procesoBean.getValidateDetailBeans() );
		} catch (Exception e) {
			LOG.error("Error al procesar archivo , ", e);
			return null;
		}
		return procesoBean;
	}
	
	private ValidateBean getValidateBean(ExcelUtil excelUtil){
		ValidateBean validateBean = new ValidateBean();
		validateBean.setTipo( excelUtil.getValueCell("C3") );
		validateBean.setMetodo( excelUtil.getValueCell("C4"));
		validateBean.setParameters( getParameters(excelUtil) );
		return validateBean;
	}
	
	private List<ParameterBean> getParameters(ExcelUtil excelUtil){

		List<ParameterBean> parameterBeans = new ArrayList<ParameterBean>();
		ParameterBean parameterBean;
		
		Iterator<Row> rowIterator = excelUtil.getFilasExcel();
		boolean iniciaProceso = false;
		while (rowIterator.hasNext()) {
			
			Row filaValidacion = rowIterator.next();
			int numFila =  filaValidacion.getRowNum();
			
			if( numFila == 7 ){
				iniciaProceso = true;
			}
			
			if( iniciaProceso ){
				parameterBean = new ParameterBean();
				
				Iterator<Cell> cellIterator = filaValidacion.cellIterator();
				int i = 0;
				while (cellIterator.hasNext()) {
					i++;
					Cell celdaFila = cellIterator.next();
					if( "DETALLE CONFIGURACION" .equals(celdaFila.getStringCellValue())){
						return parameterBeans;
					}
					
					if( i == 1) { parameterBean.setTipo( celdaFila.getStringCellValue() ); }
					else if ( i ==2 ){ parameterBean.setVariable( celdaFila.getStringCellValue() ); }
				}
				parameterBeans.add(parameterBean);
			}
		}
		return parameterBeans;
	}

	private List<ValidateDetailBean> getValidateDetailBean(ExcelUtil excelUtil){
		List<ValidateDetailBean> validateDetailBeans = new ArrayList<ValidateDetailBean>();
		ValidateDetailBean validateDetailBean;
		Iterator<Row> rowIterator = excelUtil.getFilasExcel();
		boolean inicia = false;
		boolean iniciaProceso = false;
		while (rowIterator.hasNext()) {
			
			System.out.println("FILA ===========================");
			validateDetailBean = new ValidateDetailBean();
			
			Row filaValidacion = rowIterator.next();
			Iterator<Cell> cellIterator = filaValidacion.cellIterator();
			int i = 0;
			while (cellIterator.hasNext()) {
				i++;
				Cell celdaFila = cellIterator.next();
				
				System.out.println("cel: " + celdaFila.getStringCellValue());
				
				if( "campo_backend".equals(celdaFila.getStringCellValue()) ){
					iniciaProceso = true;
					break;
				}
				
				if(iniciaProceso){
					inicia  = true;
					if( i== 1){validateDetailBean.setAtributo( celdaFila.getStringCellValue() );}
					else if( i== 2){validateDetailBean.setTipo( celdaFila.getStringCellValue() );}
					else if( i== 3){validateDetailBean.setLongitud( celdaFila.getStringCellValue() );}
					else if( i== 4){validateDetailBean.setObligatoriedad( celdaFila.getStringCellValue() );}
					else if( i== 5){validateDetailBean.setCampoBackend( celdaFila.getStringCellValue() );}
				}
			}
			if(inicia){
				validateDetailBeans.add(validateDetailBean);
			}
		}
		return validateDetailBeans;
	}
}
