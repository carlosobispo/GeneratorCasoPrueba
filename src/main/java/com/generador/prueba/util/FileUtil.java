package com.generador.prueba.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.util.bundle.ResourceBundleUtil;

public class FileUtil {
	
	private static final Logger LOG =LoggerFactory.getLogger( FileUtil.class);
	
	private static final FileUtil INSTANCE = new FileUtil();
	
	private FileUtil(){
	}
	
	public static FileUtil getInstance(){
		return INSTANCE;
	}
	
	public void generateFileResult( String ruta, StringBuilder builder){
		
		LOG.info("... invoke FileUtil.generateFileResult ... ");
		LOG.info("..archivo resultado a crear : "+ ruta);
		
		try {
			File archivo = new File(ruta);
			
	        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(ResourceBundleUtil.getResourceValue("line.separator"));
            bw.append(builder.toString());
	        bw.close();	
		} catch (Exception e) {
			LOG.error(" Error al crear FileResult ", e);
		}
		
	}
	
	
}
