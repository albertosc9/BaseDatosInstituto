package es.iestetuan.dam2.configProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GestorConfiguracion {

	
	static Properties prop;
	
	
	
	private static Properties cargarProperties() {
		
		
		
		
		try {
			
			FileInputStream input = new FileInputStream("recursos/conf.properties");
			
			
			prop.load(input);
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static String  gestionPropiedades(String clave) {
		
		prop = cargarProperties();
		
		String propiedades = prop.getProperty(clave);
		
		
		
		return propiedades;
	}
}
