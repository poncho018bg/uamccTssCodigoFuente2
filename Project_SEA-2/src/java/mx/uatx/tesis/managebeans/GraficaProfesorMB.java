/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.GraficaProfesorBeans;

import mx.uatx.tesis.daos.GraficaProfesorDAO;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class GraficaProfesorMB {

    //VARIABLES PARA LA SESSION
    private String claseXX;
    private HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    
    private List<GraficaProfesorBeans> listInscritosGraficas = new ArrayList<GraficaProfesorBeans>();
    private List<GraficaProfesorBeans> listCalificarGraficas = new ArrayList<GraficaProfesorBeans>();
    private List<GraficaProfesorBeans> listAprobados = new ArrayList<GraficaProfesorBeans>();
    
    //VARIABLE PARA LA GRAFICA
    private PieChartModel pieModel1;
     // CONSULTA QUE CUENTA EL TOTAL DE INSCRITOS
    private int ca_usuarios_idusuarios_MBT;
    private int ca_claseprofesor_idclaseprofesor_MBT;
    private int ea_idejercicioAlumno_MBT;
    private int ea_usuarios_idUsuarios_MBT;
    private int cpa_ejercicioalumno_idejercicioalumno_MBT;
    
    // CONSULTA DEL TOTAL DE ALUMNOS APROBADOS
    private int ca_idclasealumno_MB;
    private int ca_usuarios_idusuarios_MB;
    private int ca_claseprofesor_idclaseprofesor_MB;

    private int ea_idejercicioAlumno_MB;
    private int ea_ejercicioprofesor_idejercicioprofesor_MB;
    private int ea_usuarios_idUsuarios_MB;
    private String ea_fechainicio_a_MB;
    private String ea_fechafinal_a_MB;
    private String ea_tiempototal_a_MB;
    private int ea_intentostotal_a_MB;

    private int ep_idejercicioProfesor_MB;
    private String ep_tiempototal_MB;
    private int ep_intentostotal_MB;

    private int cpa_idcasopruebaalumno_MB;
    private int cpa_ejercicioalumno_idejercicioalumno_MB;
    private int cpa_idcasoprueba_MB;
    private int cpa_usuarios_idusuarios_MB;
    private String cpa_estatus_MB;
    
    //SUBIR A SESSION CLASE Y USUARIO
    private String verClaseAlumno;
    private String verIdAlumno;
    
    private GraficaProfesorBeans selectedCar2;

    /**
     * Creates a new instance of GraficaProfesorMB
     */
    public GraficaProfesorMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        
        downSession1();
        
        GraficaProfesorDAO inscritos = new GraficaProfesorDAO();
        int idClase = Integer.parseInt(downSession1());
        setListInscritosGraficas(inscritos.retriveListAlumnosInscritos(idClase));
        
         createPieModels();
        
    }
    public void redirectToMisAvances() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        setVerClaseAlumno(params.get("idClaseAlumno"));

        //Subir a sesión credenciasles
       
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*********NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionIDClase");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            //logout();
            //sesion.setAttribute("sessionIDClase", "");

            sesion.setAttribute("sessionIDClase", getVerClaseAlumno());

        } else {
            System.out.println("*********NO session");
        }
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_GraficaAvances.xhtml");


        
    }
     public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private void createPieModels() {
        createPieModel1();
    }
    


    private void createPieModel1() {

        pieModel1 = new PieChartModel();
        // DATOS DEL TOTAL DE ALUMNOS
        GraficaProfesorDAO inscritos = new GraficaProfesorDAO ();
        int idClase = Integer.parseInt(downSession1());
        setListCalificarGraficas(inscritos.cuentaInstcritosGraficaProfesor(idClase) );

        for (int i = 0; i < getListCalificarGraficas().size(); i++) {
            GraficaProfesorBeans inscritosBeans = (GraficaProfesorBeans) getListCalificarGraficas().get(i);

            setCa_usuarios_idusuarios_MBT(inscritosBeans.getCa_usuarios_idusuariosT());            

        }
        //DATOS DEL TOTAL DE APROBADOS EN LA CLASE
        setListAprobados(inscritos.retriveListGraficaProfesor1(idClase));
        for (int i = 0; i < getListAprobados().size(); i++) {
            GraficaProfesorBeans inscritosBeans = (GraficaProfesorBeans) getListAprobados().get(i);
            setCa_idclasealumno_MB(inscritosBeans.getCa_idclasealumno());
        }
       // int alumnosReprobados = getCa_usuarios_idusuarios_MBT() - getCa_idclasealumno_MB();
        int alumnosReprobados = getCa_usuarios_idusuarios_MBT() - getCa_idclasealumno_MB();
        pieModel1.set("Ejercicios Aprobados", getCa_idclasealumno_MB());
        pieModel1.set("Ejercicios Reprobados", alumnosReprobados);

        pieModel1.setTitle("Avance General de Ejercicios");
        pieModel1.setLegendPosition("w");
    }
    

    public void itemSelect(ItemSelectEvent event) {
        int alumnosReprobados = getCa_usuarios_idusuarios_MBT() - getCa_usuarios_idusuarios_MB();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, " Ejercicios ",
                //"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        "Aprobados : " + getCa_usuarios_idusuarios_MB() + ", Reprobados :" + alumnosReprobados);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public String downSession1() {
        if (httpServletRequest.getSession().getAttribute("sessionIDClase") != null) {
            setClaseXX(this.httpServletRequest.getSession().getAttribute("sessionIDClase").toString());

        }
        String idClase = getClaseXX();

        return idClase;
    }
    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @return the facesMessage
     */
    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    /**
     * @param facesMessage the facesMessage to set
     */
    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }

    /**
     * @return the claseXX
     */
    public String getClaseXX() {
        return claseXX;
    }

    /**
     * @param claseXX the claseXX to set
     */
    public void setClaseXX(String claseXX) {
        this.claseXX = claseXX;
    }

    /**
     * @return the listInscritosGraficas
     */
    public List<GraficaProfesorBeans> getListInscritosGraficas() {
        return listInscritosGraficas;
    }

    /**
     * @param listInscritosGraficas the listInscritosGraficas to set
     */
    public void setListInscritosGraficas(List<GraficaProfesorBeans> listInscritosGraficas) {
        this.listInscritosGraficas = listInscritosGraficas;
    }

    /**
     * @return the listCalificarGraficas
     */
    public List<GraficaProfesorBeans> getListCalificarGraficas() {
        return listCalificarGraficas;
    }

    /**
     * @param listCalificarGraficas the listCalificarGraficas to set
     */
    public void setListCalificarGraficas(List<GraficaProfesorBeans> listCalificarGraficas) {
        this.listCalificarGraficas = listCalificarGraficas;
    }

    /**
     * @return the listAprobados
     */
    public List<GraficaProfesorBeans> getListAprobados() {
        return listAprobados;
    }

    /**
     * @param listAprobados the listAprobados to set
     */
    public void setListAprobados(List<GraficaProfesorBeans> listAprobados) {
        this.listAprobados = listAprobados;
    }

    /**
     * @return the ca_usuarios_idusuarios_MBT
     */
    public int getCa_usuarios_idusuarios_MBT() {
        return ca_usuarios_idusuarios_MBT;
    }

    /**
     * @param ca_usuarios_idusuarios_MBT the ca_usuarios_idusuarios_MBT to set
     */
    public void setCa_usuarios_idusuarios_MBT(int ca_usuarios_idusuarios_MBT) {
        this.ca_usuarios_idusuarios_MBT = ca_usuarios_idusuarios_MBT;
    }

    /**
     * @return the ca_claseprofesor_idclaseprofesor_MBT
     */
    public int getCa_claseprofesor_idclaseprofesor_MBT() {
        return ca_claseprofesor_idclaseprofesor_MBT;
    }

    /**
     * @param ca_claseprofesor_idclaseprofesor_MBT the ca_claseprofesor_idclaseprofesor_MBT to set
     */
    public void setCa_claseprofesor_idclaseprofesor_MBT(int ca_claseprofesor_idclaseprofesor_MBT) {
        this.ca_claseprofesor_idclaseprofesor_MBT = ca_claseprofesor_idclaseprofesor_MBT;
    }

    /**
     * @return the ea_idejercicioAlumno_MBT
     */
    public int getEa_idejercicioAlumno_MBT() {
        return ea_idejercicioAlumno_MBT;
    }

    /**
     * @param ea_idejercicioAlumno_MBT the ea_idejercicioAlumno_MBT to set
     */
    public void setEa_idejercicioAlumno_MBT(int ea_idejercicioAlumno_MBT) {
        this.ea_idejercicioAlumno_MBT = ea_idejercicioAlumno_MBT;
    }

    /**
     * @return the ea_usuarios_idUsuarios_MBT
     */
    public int getEa_usuarios_idUsuarios_MBT() {
        return ea_usuarios_idUsuarios_MBT;
    }

    /**
     * @param ea_usuarios_idUsuarios_MBT the ea_usuarios_idUsuarios_MBT to set
     */
    public void setEa_usuarios_idUsuarios_MBT(int ea_usuarios_idUsuarios_MBT) {
        this.ea_usuarios_idUsuarios_MBT = ea_usuarios_idUsuarios_MBT;
    }

    /**
     * @return the cpa_ejercicioalumno_idejercicioalumno_MBT
     */
    public int getCpa_ejercicioalumno_idejercicioalumno_MBT() {
        return cpa_ejercicioalumno_idejercicioalumno_MBT;
    }

    /**
     * @param cpa_ejercicioalumno_idejercicioalumno_MBT the cpa_ejercicioalumno_idejercicioalumno_MBT to set
     */
    public void setCpa_ejercicioalumno_idejercicioalumno_MBT(int cpa_ejercicioalumno_idejercicioalumno_MBT) {
        this.cpa_ejercicioalumno_idejercicioalumno_MBT = cpa_ejercicioalumno_idejercicioalumno_MBT;
    }

    /**
     * @return the ca_idclasealumno_MB
     */
    public int getCa_idclasealumno_MB() {
        return ca_idclasealumno_MB;
    }

    /**
     * @param ca_idclasealumno_MB the ca_idclasealumno_MB to set
     */
    public void setCa_idclasealumno_MB(int ca_idclasealumno_MB) {
        this.ca_idclasealumno_MB = ca_idclasealumno_MB;
    }

    /**
     * @return the ca_usuarios_idusuarios_MB
     */
    public int getCa_usuarios_idusuarios_MB() {
        return ca_usuarios_idusuarios_MB;
    }

    /**
     * @param ca_usuarios_idusuarios_MB the ca_usuarios_idusuarios_MB to set
     */
    public void setCa_usuarios_idusuarios_MB(int ca_usuarios_idusuarios_MB) {
        this.ca_usuarios_idusuarios_MB = ca_usuarios_idusuarios_MB;
    }

    /**
     * @return the ca_claseprofesor_idclaseprofesor_MB
     */
    public int getCa_claseprofesor_idclaseprofesor_MB() {
        return ca_claseprofesor_idclaseprofesor_MB;
    }

    /**
     * @param ca_claseprofesor_idclaseprofesor_MB the ca_claseprofesor_idclaseprofesor_MB to set
     */
    public void setCa_claseprofesor_idclaseprofesor_MB(int ca_claseprofesor_idclaseprofesor_MB) {
        this.ca_claseprofesor_idclaseprofesor_MB = ca_claseprofesor_idclaseprofesor_MB;
    }

    /**
     * @return the ea_idejercicioAlumno_MB
     */
    public int getEa_idejercicioAlumno_MB() {
        return ea_idejercicioAlumno_MB;
    }

    /**
     * @param ea_idejercicioAlumno_MB the ea_idejercicioAlumno_MB to set
     */
    public void setEa_idejercicioAlumno_MB(int ea_idejercicioAlumno_MB) {
        this.ea_idejercicioAlumno_MB = ea_idejercicioAlumno_MB;
    }

    /**
     * @return the ea_ejercicioprofesor_idejercicioprofesor_MB
     */
    public int getEa_ejercicioprofesor_idejercicioprofesor_MB() {
        return ea_ejercicioprofesor_idejercicioprofesor_MB;
    }

    /**
     * @param ea_ejercicioprofesor_idejercicioprofesor_MB the ea_ejercicioprofesor_idejercicioprofesor_MB to set
     */
    public void setEa_ejercicioprofesor_idejercicioprofesor_MB(int ea_ejercicioprofesor_idejercicioprofesor_MB) {
        this.ea_ejercicioprofesor_idejercicioprofesor_MB = ea_ejercicioprofesor_idejercicioprofesor_MB;
    }

    /**
     * @return the ea_usuarios_idUsuarios_MB
     */
    public int getEa_usuarios_idUsuarios_MB() {
        return ea_usuarios_idUsuarios_MB;
    }

    /**
     * @param ea_usuarios_idUsuarios_MB the ea_usuarios_idUsuarios_MB to set
     */
    public void setEa_usuarios_idUsuarios_MB(int ea_usuarios_idUsuarios_MB) {
        this.ea_usuarios_idUsuarios_MB = ea_usuarios_idUsuarios_MB;
    }

    /**
     * @return the ea_fechainicio_a_MB
     */
    public String getEa_fechainicio_a_MB() {
        return ea_fechainicio_a_MB;
    }

    /**
     * @param ea_fechainicio_a_MB the ea_fechainicio_a_MB to set
     */
    public void setEa_fechainicio_a_MB(String ea_fechainicio_a_MB) {
        this.ea_fechainicio_a_MB = ea_fechainicio_a_MB;
    }

    /**
     * @return the ea_fechafinal_a_MB
     */
    public String getEa_fechafinal_a_MB() {
        return ea_fechafinal_a_MB;
    }

    /**
     * @param ea_fechafinal_a_MB the ea_fechafinal_a_MB to set
     */
    public void setEa_fechafinal_a_MB(String ea_fechafinal_a_MB) {
        this.ea_fechafinal_a_MB = ea_fechafinal_a_MB;
    }

    /**
     * @return the ea_tiempototal_a_MB
     */
    public String getEa_tiempototal_a_MB() {
        return ea_tiempototal_a_MB;
    }

    /**
     * @param ea_tiempototal_a_MB the ea_tiempototal_a_MB to set
     */
    public void setEa_tiempototal_a_MB(String ea_tiempototal_a_MB) {
        this.ea_tiempototal_a_MB = ea_tiempototal_a_MB;
    }

    /**
     * @return the ea_intentostotal_a_MB
     */
    public int getEa_intentostotal_a_MB() {
        return ea_intentostotal_a_MB;
    }

    /**
     * @param ea_intentostotal_a_MB the ea_intentostotal_a_MB to set
     */
    public void setEa_intentostotal_a_MB(int ea_intentostotal_a_MB) {
        this.ea_intentostotal_a_MB = ea_intentostotal_a_MB;
    }

    /**
     * @return the ep_idejercicioProfesor_MB
     */
    public int getEp_idejercicioProfesor_MB() {
        return ep_idejercicioProfesor_MB;
    }

    /**
     * @param ep_idejercicioProfesor_MB the ep_idejercicioProfesor_MB to set
     */
    public void setEp_idejercicioProfesor_MB(int ep_idejercicioProfesor_MB) {
        this.ep_idejercicioProfesor_MB = ep_idejercicioProfesor_MB;
    }

    /**
     * @return the ep_tiempototal_MB
     */
    public String getEp_tiempototal_MB() {
        return ep_tiempototal_MB;
    }

    /**
     * @param ep_tiempototal_MB the ep_tiempototal_MB to set
     */
    public void setEp_tiempototal_MB(String ep_tiempototal_MB) {
        this.ep_tiempototal_MB = ep_tiempototal_MB;
    }

    /**
     * @return the ep_intentostotal_MB
     */
    public int getEp_intentostotal_MB() {
        return ep_intentostotal_MB;
    }

    /**
     * @param ep_intentostotal_MB the ep_intentostotal_MB to set
     */
    public void setEp_intentostotal_MB(int ep_intentostotal_MB) {
        this.ep_intentostotal_MB = ep_intentostotal_MB;
    }

    /**
     * @return the cpa_idcasopruebaalumno_MB
     */
    public int getCpa_idcasopruebaalumno_MB() {
        return cpa_idcasopruebaalumno_MB;
    }

    /**
     * @param cpa_idcasopruebaalumno_MB the cpa_idcasopruebaalumno_MB to set
     */
    public void setCpa_idcasopruebaalumno_MB(int cpa_idcasopruebaalumno_MB) {
        this.cpa_idcasopruebaalumno_MB = cpa_idcasopruebaalumno_MB;
    }

    /**
     * @return the cpa_ejercicioalumno_idejercicioalumno_MB
     */
    public int getCpa_ejercicioalumno_idejercicioalumno_MB() {
        return cpa_ejercicioalumno_idejercicioalumno_MB;
    }

    /**
     * @param cpa_ejercicioalumno_idejercicioalumno_MB the cpa_ejercicioalumno_idejercicioalumno_MB to set
     */
    public void setCpa_ejercicioalumno_idejercicioalumno_MB(int cpa_ejercicioalumno_idejercicioalumno_MB) {
        this.cpa_ejercicioalumno_idejercicioalumno_MB = cpa_ejercicioalumno_idejercicioalumno_MB;
    }

    /**
     * @return the cpa_idcasoprueba_MB
     */
    public int getCpa_idcasoprueba_MB() {
        return cpa_idcasoprueba_MB;
    }

    /**
     * @param cpa_idcasoprueba_MB the cpa_idcasoprueba_MB to set
     */
    public void setCpa_idcasoprueba_MB(int cpa_idcasoprueba_MB) {
        this.cpa_idcasoprueba_MB = cpa_idcasoprueba_MB;
    }

    /**
     * @return the cpa_usuarios_idusuarios_MB
     */
    public int getCpa_usuarios_idusuarios_MB() {
        return cpa_usuarios_idusuarios_MB;
    }

    /**
     * @param cpa_usuarios_idusuarios_MB the cpa_usuarios_idusuarios_MB to set
     */
    public void setCpa_usuarios_idusuarios_MB(int cpa_usuarios_idusuarios_MB) {
        this.cpa_usuarios_idusuarios_MB = cpa_usuarios_idusuarios_MB;
    }

    /**
     * @return the cpa_estatus_MB
     */
    public String getCpa_estatus_MB() {
        return cpa_estatus_MB;
    }

    /**
     * @param cpa_estatus_MB the cpa_estatus_MB to set
     */
    public void setCpa_estatus_MB(String cpa_estatus_MB) {
        this.cpa_estatus_MB = cpa_estatus_MB;
    }

    /**
     * @return the verClaseAlumno
     */
    public String getVerClaseAlumno() {
        return verClaseAlumno;
    }

    /**
     * @param verClaseAlumno the verClaseAlumno to set
     */
    public void setVerClaseAlumno(String verClaseAlumno) {
        this.verClaseAlumno = verClaseAlumno;
    }

    /**
     * @return the verIdAlumno
     */
    public String getVerIdAlumno() {
        return verIdAlumno;
    }

    /**
     * @param verIdAlumno the verIdAlumno to set
     */
    public void setVerIdAlumno(String verIdAlumno) {
        this.verIdAlumno = verIdAlumno;
    }

    /**
     * @return the selectedCar2
     */
    public GraficaProfesorBeans getSelectedCar2() {
        return selectedCar2;
    }

    /**
     * @param selectedCar2 the selectedCar2 to set
     */
    public void setSelectedCar2(GraficaProfesorBeans selectedCar2) {
        this.selectedCar2 = selectedCar2;
    }

}
