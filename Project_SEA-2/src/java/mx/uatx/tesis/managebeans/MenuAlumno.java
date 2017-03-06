/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class MenuAlumno {

    private String usuario;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    /**
     * Creates a new instance of CerrarSesion
     */
    public MenuAlumno() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionIDUsuario") != null) {
            usuario = httpServletRequest.getSession().getAttribute("sessionIDUsuario").toString();
        }

    }

    public void cerrarSession() throws IOException {
        httpServletRequest.getSession().removeAttribute("sessionIDUsuario");
        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, facesMessage);

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("index.xhtml");
        
    }
    
    
     public void redirectMisEjerciciosAlumno() throws IOException {       

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_Ejercicio.xhtml");
        
    }

      public void redirectToMisTemasAlumno() throws IOException {       

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_Temario.xhtml");
        
    }
      
      public void redirectToMisClasesAlumno() throws IOException {       

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_MiClases.xhtml");
        
    }
}

