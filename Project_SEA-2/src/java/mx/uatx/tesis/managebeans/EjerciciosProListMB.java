/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import mx.uatx.tesis.beans.EjerciciosProListBeans;
import mx.uatx.tesis.daos.EjerciciosProListDAO;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class EjerciciosProListMB {

    //VARIABLES PARA LA SESSION
    private String subtemaXX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    // tabla ejercicioProfesor
    private int idEjercicioProfeMB;
    private int subtemaProfe_idSubtemaProfeMB;
    private String nombreSubMB;
    private String descripcionSubMB;
    private String sugerenciaSubMB;
    private String codigoFuenteSubMB;

    private List<EjerciciosProListBeans> listEjercicioPro = new ArrayList<EjerciciosProListBeans>();

    /**
     * Creates a new instance of EjerciciosProListMB
     */
    public EjerciciosProListMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionIDSubtema") != null) {
            setSubtemaXX(this.httpServletRequest.getSession().getAttribute("sessionIDSubtema").toString());
           
        }
          EjerciciosProListDAO ejercicioDAO = new EjerciciosProListDAO();

        int idUser = Integer.parseInt(getSubtemaXX());
        setListEjercicioPro(ejercicioDAO.retriveListEjercicioPro(idUser));
        
    }

    public void redirectToBuildexErcise() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_AgregarEjercicio.xhtml");
        
    }

    /**
     * @return the subtemaXX
     */
    public String getSubtemaXX() {
        return subtemaXX;
    }

    /**
     * @param subtemaXX the subtemaXX to set
     */
    public void setSubtemaXX(String subtemaXX) {
        this.subtemaXX = subtemaXX;
    }

    /**
     * @return the idEjercicioProfeMB
     */
    public int getIdEjercicioProfeMB() {
        return idEjercicioProfeMB;
    }

    /**
     * @param idEjercicioProfeMB the idEjercicioProfeMB to set
     */
    public void setIdEjercicioProfeMB(int idEjercicioProfeMB) {
        this.idEjercicioProfeMB = idEjercicioProfeMB;
    }

    /**
     * @return the subtemaProfe_idSubtemaProfeMB
     */
    public int getSubtemaProfe_idSubtemaProfeMB() {
        return subtemaProfe_idSubtemaProfeMB;
    }

    /**
     * @param subtemaProfe_idSubtemaProfeMB the subtemaProfe_idSubtemaProfeMB to
     * set
     */
    public void setSubtemaProfe_idSubtemaProfeMB(int subtemaProfe_idSubtemaProfeMB) {
        this.subtemaProfe_idSubtemaProfeMB = subtemaProfe_idSubtemaProfeMB;
    }

    /**
     * @return the descripcionSubMB
     */
    public String getDescripcionSubMB() {
        return descripcionSubMB;
    }

    /**
     * @param descripcionSubMB the descripcionSubMB to set
     */
    public void setDescripcionSubMB(String descripcionSubMB) {
        this.descripcionSubMB = descripcionSubMB;
    }

    /**
     * @return the sugerenciaSubMB
     */
    public String getSugerenciaSubMB() {
        return sugerenciaSubMB;
    }

    /**
     * @param sugerenciaSubMB the sugerenciaSubMB to set
     */
    public void setSugerenciaSubMB(String sugerenciaSubMB) {
        this.sugerenciaSubMB = sugerenciaSubMB;
    }

    /**
     * @return the codigoFuenteSubMB
     */
    public String getCodigoFuenteSubMB() {
        return codigoFuenteSubMB;
    }

    /**
     * @param codigoFuenteSubMB the codigoFuenteSubMB to set
     */
    public void setCodigoFuenteSubMB(String codigoFuenteSubMB) {
        this.codigoFuenteSubMB = codigoFuenteSubMB;
    }

    /**
     * @return the listEjercicioPro
     */
    public List<EjerciciosProListBeans> getListEjercicioPro() {
        return listEjercicioPro;
    }

    /**
     * @param listEjercicioPro the listEjercicioPro to set
     */
    public void setListEjercicioPro(List<EjerciciosProListBeans> listEjercicioPro) {
        this.listEjercicioPro = listEjercicioPro;
    }

    /**
     * @return the nombreSubMB
     */
    public String getNombreSubMB() {
        return nombreSubMB;
    }

    /**
     * @param nombreSubMB the nombreSubMB to set
     */
    public void setNombreSubMB(String nombreSubMB) {
        this.nombreSubMB = nombreSubMB;
    }

}
