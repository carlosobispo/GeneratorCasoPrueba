package com.generador.prueba.util.bundle;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.constante.AppConstante;

public class ResourceBundleUtil implements Serializable{

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleUtil.class);
	
	private static final long serialVersionUID = 1L;

	public static String getResourceValue(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(AppConstante.CONFIG_RESOURCE_BUNDLE);
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			LOGGER.error("MissingResourceException " + e);
			return "";
		}
	}
	
	
}
