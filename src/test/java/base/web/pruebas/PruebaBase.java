package base.web.pruebas;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import reservas.modelo.pruebas.DatosPrueba;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PruebaBase {

	/**
	 * Despliega el proyecto para las pruebas
	 * @return
	 */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
        	// crea una aplicación web
        	.create( WebArchive.class, "Test.war" )
        	
        	// incluyendo los paquetes del programa
        	.addPackages( true,
        			"base.accesoDatos.utils",
        			"base.modelo.pruebas"
        			)

        	// add persistence.xml
            .addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
            // add web.xml to WEB-INF
            .addAsWebInfResource("web.xml")
            // add glassfish-resources to WEB-INF
            .addAsWebInfResource("glassfish-resources.xml")  
            // including empty beans.xml to WEB-INF
            .addAsWebInfResource( EmptyAsset.INSTANCE, "beans.xml")

            // add log4j.properties
            .addAsResource( "log4j.properties", "log4j.properties")
            .addAsWebInfResource( "log4j.properties" );
        
    }	
	
    // Inyección de EJBs y Persistencia
    // ----------------------------------------
    
    @EJB 
    DatosPrueba datosPrueba;
    
    @PersistenceContext 
    EntityManager em;
    
    
    // Preparación de la prueba
    // ----------------------------------------
    
    @Before
    public void preparePersistenceTest() throws Exception {
        datosPrueba.borrarDatosPrueba();
        datosPrueba.crearDatosPrueba();
    }    
    

    // Pruebas
    // ----------------------------------------

	
	@Test
	public void devolverCopia(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Saco error " + e.getMessage() );
		}
	}


}
