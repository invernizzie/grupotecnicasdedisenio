package ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton que permite acceso a los recursos globales de la aplicacion
 * @author santiago
 *
 */
public class RecursosAplicacion {
	public static final String PROPERTIES_PATH = "config.xml";

	/**
	 * Properties de la aplicacion
	 */
	private Properties properties = null;

	/**
	 * La unica instancia de este Singleton
	 */    
	private static RecursosAplicacion instance = new RecursosAplicacion();

	/** 
	 * Forzamos el Singleton ocultando el constructor 
	 */ 
	private RecursosAplicacion() {
		this.loadProperties();
	}

	public static RecursosAplicacion instance(){
		return instance;
	}
	
	/**
     * Carga las properties de configuración.
     *
     */
    protected void loadProperties() {
        
        try {
            
            InputStream is = this.getClass().getClassLoader()
                                        .getResourceAsStream(PROPERTIES_PATH);
            
            this.properties = new Properties();
            this.properties.loadFromXML(is);
            
            
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public Properties getProperties() {
        return this.properties;
    }
    
    public String getProperty(String key) {
        return this.getProperties().getProperty(key);
    }

    
    public int getIntProperty(String key) {
        
        return Integer.parseInt(this.getProperty(key));
    }
    
}
