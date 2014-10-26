package base.presentacion.utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {

	private FacesUtils() {
	}
	
    
	// mensajes
	
	public static void addSuccessMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, msg);
    }

    public static void addErrorMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, msg);
    }

    private static void addMessage(FacesMessage.Severity severity, String msg) {
        final FacesMessage facesMsg = new FacesMessage(severity, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
	// redirección

	public static void redirect(String url) {
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.getExternalContext().redirect(url);
            fctx.responseComplete();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
	
}
