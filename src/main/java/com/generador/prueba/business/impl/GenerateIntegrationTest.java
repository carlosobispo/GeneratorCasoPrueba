package com.generador.prueba.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generador.prueba.bean.ParameterBean;
import com.generador.prueba.bean.ProcesoBean;
import com.generador.prueba.bean.ValidateBean;
import com.generador.prueba.bean.ValidateDetailBean;
import com.generador.prueba.business.IGenerateIntegrationTest;
import com.generador.prueba.util.bundle.ResourceBundleUtil;

public class GenerateIntegrationTest implements IGenerateIntegrationTest{

	private static final Logger LOG =LoggerFactory.getLogger(GenerateIntegrationTest.class);
	
	private String nameObjetoEntrada ="";
	
	public StringBuilder generateIntegrationTest(ProcesoBean procesoBean) {
		LOG.info(".. invoke GenerateIntegrationTest.generateIntegrationTest... ");
		ValidateBean validateBean = procesoBean.getValidateBean();
		List<ParameterBean> parameterBeans = validateBean.getParameters();
		
		List<ValidateDetailBean> validateDetailBeans = procesoBean.getValidateDetailBeans();
		
		StringBuilder stringBuilder = new StringBuilder();
		createEncabezado(stringBuilder);
		createFullTest(stringBuilder, procesoBean);
		
		List<String> metodos = new ArrayList<String>();
		for(ValidateDetailBean detailBean : validateDetailBeans){
			
		
			String[] atributos = detailBean.getAtributo().split("\\.");
			
			boolean isObligatorio = "SI".equals(detailBean.getObligatoriedad()) ? true :false;
			if(atributos.length >1){
				StringBuilder sbNombreAtributo = new StringBuilder();
				int numCampo = atributos.length ;
				for (int i = 0; i < atributos.length ; i++) {
					sbNombreAtributo.append( atributos[i]);
					
					if( numCampo < i ){
						String nombreMetodo= generateMethodName(procesoBean, sbNombreAtributo.toString(), false, false); 
						if( metodos.contains(nombreMetodo)){
							continue;
						}
						metodos.add(nombreMetodo);
				
						stringBuilder.append("@Test" + getSaltoLinea());
						stringBuilder.append("public void "+ nombreMetodo + "throws IOException {" + getSaltoLinea());
						
						validateObligatoriedad(stringBuilder, true , false);
						generateObjetoEntrada(stringBuilder, procesoBean.getValidateBean().getParameters());
						stringBuilder.append( getNameMethodService(procesoBean) + getSaltoLinea());
						generateresultMethod(stringBuilder, true);
					}else{

						String nombreMetodo= generateMethodName(procesoBean, sbNombreAtributo.toString(), false, isObligatorio); 
						if( metodos.contains(nombreMetodo)){
							continue;
						}
						metodos.add(nombreMetodo);
				
						stringBuilder.append("@Test" + getSaltoLinea());
						stringBuilder.append("public void "+ nombreMetodo + "throws IOException {" + getSaltoLinea());
						
						validateObligatoriedad(stringBuilder, isObligatorio , false);
						generateObjetoEntrada(stringBuilder, procesoBean.getValidateBean().getParameters());
						stringBuilder.append( getNameMethodService(procesoBean) + getSaltoLinea());
						generateresultMethod(stringBuilder, isObligatorio);
					}
					
				}	
			}
	
		}
		
		return stringBuilder;
	}
	
	private void createFullTest(StringBuilder stringBuilder, ProcesoBean procesoBean ){
		stringBuilder.append("@Test" + getSaltoLinea());
		stringBuilder.append("public void "+ generateMethodName(procesoBean, null, true , false) 
								+ "throws IOException {" + getSaltoLinea());
		validateObligatoriedad(stringBuilder, false, true);
		generateObjetoEntrada(stringBuilder, procesoBean.getValidateBean().getParameters());
		stringBuilder.append("ServiceResponse<"+ nameObjetoEntrada +"> result ="
							+ getNameMethodService(procesoBean) + getSaltoLinea());
		generateresultMethod(stringBuilder, false);
	}
	
	private String getNameMethodService(ProcesoBean procesoBean){
		List<ParameterBean> parameterBeans =procesoBean.getValidateBean().getParameters(); 
		StringBuilder builder = new StringBuilder();
		builder.append("srvServicio."+ procesoBean.getValidateBean().getMetodo()+"(");
		if(CollectionUtils.isEmpty(parameterBeans)){
			return builder.append(");").toString();
		}
		for(ParameterBean parameterBean : parameterBeans){
			builder.append( parameterBean.getVariable() + " ," );
		}
		String metodo = builder.toString();
		return metodo.substring(0, metodo.length()-1 ) + " );";
	}
	
	private void generateresultMethod(StringBuilder stringBuilder, boolean isObligatorio){
		if(!isObligatorio){
			stringBuilder.append("assertNotNull(result.getData());" + getSaltoLinea());
			stringBuilder.append("assertNotNull(result.getMessages());" + getSaltoLinea());
			stringBuilder.append("assertNull(result.getPagination());" + getSaltoLinea());
		}
		stringBuilder.append("}" + getSaltoLinea());
	}
	
	private void generateObjetoEntrada(StringBuilder stringBuilder, List<ParameterBean> parameterBeans){
		if(CollectionUtils.isNotEmpty( parameterBeans)){
			for(ParameterBean parameterBean:parameterBeans){
				if("Objeto".equals(parameterBean.getTipo())){
					String variable=parameterBean.getVariable();
					nameObjetoEntrada= variable.substring(0, 1).toUpperCase()
							+ variable.substring(1); 
					stringBuilder.append(nameObjetoEntrada+ " "+ variable+ " = "
							+ "EntityMock.getInstance().get"+nameObjetoEntrada+"(); "+ getSaltoLinea());		
				}
			}
		}
	}
	
	private void validateObligatoriedad(StringBuilder stringBuilder, 
			boolean isObligatorio, boolean isfullTest){
		if(isfullTest){
			stringBuilder.append(getSaltoLinea());
		}
		else if(isObligatorio){
			stringBuilder.append("exception.expect(BusinessServiceException.class);" + getSaltoLinea());
			stringBuilder.append("exception.expect(BusinessServiceExceptionMatcher."
					+ "hasErrorCode(Errors.MANDATORY_PARAMETERS_MISSING));" + getSaltoLinea());
			stringBuilder.append(getSaltoLinea());
		}else{
			stringBuilder.append("exception.expect(BusinessServiceException.class);" + getSaltoLinea());
			stringBuilder.append("exception.expect(BusinessServiceExceptionMatcher."
					+ "hasErrorCode(Errors.WRONG_PARAMETERS));" + getSaltoLinea());
			stringBuilder.append(getSaltoLinea());
		}
	}
	
	
	private String generateMethodName(ProcesoBean procesoBean, 
			String atributo , boolean isFullTest , boolean isObligatorio){
		String nombre_metodo = procesoBean.getValidateBean().getMetodo(); 
		if(isFullTest){
			return nombre_metodo + "FullTest() ";
		}
		
		String prefijo = isObligatorio == true ? "Without" : "Wrong";
		StringBuilder nombreMetodoGenerado = new StringBuilder();
		nombreMetodoGenerado.append(  nombre_metodo + prefijo);
		String nombreParametro = atributo.substring(0, 1).toUpperCase() 
				+ atributo.substring(1, atributo.length() );
		nombreMetodoGenerado.append( nombreParametro );

		return nombreMetodoGenerado.toString()+ "() ";
	}
	
	private void createEncabezado(StringBuilder stringBuilder){
		stringBuilder.append("@Rule" + getSaltoLinea());
		stringBuilder.append("public ExpectedException exception = ExpectedException.none(); "+ getSaltoLinea());
		stringBuilder.append(getSaltoLinea());
		
		stringBuilder.append("@Autowired" + getSaltoLinea());
		stringBuilder.append("private ISrvAccountsV0 srvServicio; "+ getSaltoLinea());
		stringBuilder.append(getSaltoLinea());		
	}

	private String getSaltoLinea(){
		return  ResourceBundleUtil.getResourceValue("line.separator");
	}
	
}
