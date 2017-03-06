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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.CasoPruebaProBeans;
import mx.uatx.tesis.daos.CasoPruebaProDAO;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
//@RequestScoped
//@SessionScoped
@ViewScoped
public class CasoPruebaProMB {

    //VARIABLES PARA LA SESSION
    private String sessionEjercicio;
    private String sessionCasoPrueba;
    private String sessionSalida;
    private String sessionParametro;
    private int casoPruebaEntero;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    // Tabla Caso de Prueba
    private int idCasoPruebaPRO_MB;
    private int ejercicio_idEjercicioPRO_MB;
    private String resultadoPRO_MB;
    //Tabla Parametros
    private int idParametroPRO_MB;
    private int caso_idCasoPruebaPRO_MB;
    private String tipoPRO_MB;
    private String nombreVariablePRO_MB;
    private String valorPRO_MB;

    private List<CasoPruebaProBeans> listCasoPrueba = new ArrayList<CasoPruebaProBeans>();
    private List<CasoPruebaProBeans> listParametros = new ArrayList<CasoPruebaProBeans>();
    private List<CasoPruebaProBeans> editarSalida = new ArrayList<CasoPruebaProBeans>();
    private List<CasoPruebaProBeans> editarParametros = new ArrayList<CasoPruebaProBeans>();

    private CasoPruebaProBeans selectedCar2;
    private CasoPruebaProBeans selectedCar3;
    private boolean editarEnable = true;
    private boolean editarParametro = true;

    /**
     * Creates a new instance of CasoPruebaProBeansMB
     */
    public CasoPruebaProMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        //Bajar credenciales
        downSession1();
        //Valores para Llenar la Tabla casoPrueba
        CasoPruebaProDAO caso = new CasoPruebaProDAO();
        int idEjercicio = downSession1();
        setListCasoPrueba(caso.retriveListEjercicioPro(idEjercicio));

    }

    public void listarParametros(int id) {

        //Valores para Llenar la Tabla Parametros
        CasoPruebaProDAO parametroDAO = new CasoPruebaProDAO();

        setListParametros(parametroDAO.retriveListParametros(id));
        
    }

    public int downSession1() {

        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionIDEjercicio") != null) {
            setSessionEjercicio(this.httpServletRequest.getSession().getAttribute("sessionIDEjercicio").toString());

        }
        int idEjercicio = Integer.parseInt(getSessionEjercicio());

        return idEjercicio;
    }

    public int downSalida() {
        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionIDSalida") != null) {
            setSessionSalida(this.httpServletRequest.getSession().getAttribute("sessionIDSalida").toString());

        }
        int idEjercicio = Integer.parseInt(getSessionSalida());

        return idEjercicio;
    }

    public void saveExercisePro() {

        CasoPruebaProDAO casoDAO = new CasoPruebaProDAO();
        CasoPruebaProBeans casoBeans = new CasoPruebaProBeans();

        //int idEjercicio2 = downSession1();  setSessionEjercicio
        int idEjercicio2 = Integer.parseInt(getSessionEjercicio());

        if (isEditarEnable()) {

            casoBeans.setResultadoPRO(getResultadoPRO_MB());
            casoDAO.createCasoPruebaPro(casoBeans, idEjercicio2);
        } else {

            int idsalida = downSalida();
            casoBeans.setResultadoPRO(getResultadoPRO_MB());
            casoBeans.setIdCasoPruebaPRO(idsalida);

            casoDAO.upDateSalida(casoBeans);
        }

        //Valores para Llenar la Tabla
        setListCasoPrueba(casoDAO.retriveListEjercicioPro(idEjercicio2));
        //reset
        setResultadoPRO_MB(null);
        setEditarEnable(true);

    }

    public void deleteCasoPrueba(int id) {

        CasoPruebaProDAO casoPrueba = new CasoPruebaProDAO();
        if (casoPrueba.deleteSalida(id)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito..!", " ELIMINADO "));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Alerta..!", "ELIMINA PARAMETROS ASOCIADOS "));
        }

        //Valores para Llenar la Tabla
        CasoPruebaProDAO casoDAO = new CasoPruebaProDAO();
        int idEjercicio2 = downSession1();
        setListCasoPrueba(casoDAO.retriveListEjercicioPro(idEjercicio2));

        
    }

    public void editarSalida(int id) throws IOException {

        upIDSalida(id);
        CasoPruebaProDAO casoDAO = new CasoPruebaProDAO();
        //CasoPruebaProBeans casoBeans = new CasoPruebaProBeans();

        setEditarSalida(casoDAO.retriveEditarSalida(id));
        for (int x = 0; x < getEditarSalida().size(); x++) {
            CasoPruebaProBeans casoBeans = (CasoPruebaProBeans) getEditarSalida().get(x);
            setResultadoPRO_MB(casoBeans.getResultadoPRO());

        }
        setEditarEnable(false);
    }

    public void editarParametros(int id) throws IOException {

        CasoPruebaProDAO casoDAO = new CasoPruebaProDAO();
        //CasoPruebaProBeans casoBeans = new CasoPruebaProBeans();

        setEditarParametros(casoDAO.parametrosEditar(id));
        for (int x = 0; x < getEditarParametros().size(); x++) {
            CasoPruebaProBeans casoBeans = (CasoPruebaProBeans) getEditarParametros().get(x);
            setIdParametroPRO_MB(casoBeans.getIdParametroPRO());
            setCaso_idCasoPruebaPRO_MB(casoBeans.getIdCasoPruebaPRO());
            setTipoPRO_MB(casoBeans.getTipoPRO());
            setNombreVariablePRO_MB(casoBeans.getNombreVariablePRO());
            setValorPRO_MB(casoBeans.getValorPRO());

        }
        setEditarParametro(false);
    }

    public void deleteParametro(int id) {

        CasoPruebaProDAO casoDAO = new CasoPruebaProDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        if (casoDAO.deleteParametros(id)) {

            context.addMessage(null, new FacesMessage("Exito..!", " ELIMINADO "));
        } else {

            context.addMessage(null, new FacesMessage("Alerta..!", "Parametro No Eliminado "));
        }

    }

    public void sesionIdCasoPrueba(int id) {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("******NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            sesion.setAttribute("sessionVarCasoPrueba", id);
        } else {
            System.out.println("*****NO session");
        }
        nombreVariablePRO_MB = "";
        valorPRO_MB = null;

    }

    public void redirectBackToBuildEjercicio() throws IOException {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*****NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            sesion.setAttribute("sessionIDEjercicio", null);
        } else {
            System.out.println("****NO session");
        }

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_AgregarEjercicio.xhtml");
        
    }

    //***************************************
    public String downSession2() {
        if (httpServletRequest.getSession().getAttribute("sessionVarCasoPrueba") != null) {
            setSessionEjercicio(this.httpServletRequest.getSession().getAttribute("sessionVarCasoPrueba").toString());

        }
        String idCasoPrueba = getSessionEjercicio();

        return idCasoPrueba;
    }

    //***************************************
    public void saveParametrosPro() {

        CasoPruebaProDAO parametroDAO = new CasoPruebaProDAO();
        CasoPruebaProBeans parametroBeans = new CasoPruebaProBeans();

        parametroBeans.setIdParametroPRO(getIdParametroPRO_MB());
        parametroBeans.setTipoPRO(getTipoPRO_MB());
        parametroBeans.setNombreVariablePRO(getNombreVariablePRO_MB());
        parametroBeans.setValorPRO(getValorPRO_MB());

        //*********
        int idCasoo = Integer.parseInt(downSession2());
        if (isEditarParametro()) {
            //Almacena los valores en la BD
            parametroDAO.createParametrosPro(parametroBeans, idCasoo);
        } else {
            parametroDAO.upDateParametro(parametroBeans);
        }

        //Valores para Llenar la Tabla
        setListParametros(parametroDAO.retriveListParametros(idCasoo));
        setEditarParametro(true);

    }

    public void upIDSalida(int idSalida) throws IOException {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*********NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionIDSalida");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionIDSalida", idSalida);

        } else {
            System.out.println("*********NO session");
        }

    }

    /**
     * @return the idCasoPruebaPRO_MB
     */
    public int getIdCasoPruebaPRO_MB() {
        return idCasoPruebaPRO_MB;
    }

    /**
     * @param idCasoPruebaPRO_MB the idCasoPruebaPRO_MB to set
     */
    public void setIdCasoPruebaPRO_MB(int idCasoPruebaPRO_MB) {
        this.idCasoPruebaPRO_MB = idCasoPruebaPRO_MB;
    }

    /**
     * @return the ejercicio_idEjercicioPRO_MB
     */
    public int getEjercicio_idEjercicioPRO_MB() {
        return ejercicio_idEjercicioPRO_MB;
    }

    /**
     * @param ejercicio_idEjercicioPRO_MB the ejercicio_idEjercicioPRO_MB to set
     */
    public void setEjercicio_idEjercicioPRO_MB(int ejercicio_idEjercicioPRO_MB) {
        this.ejercicio_idEjercicioPRO_MB = ejercicio_idEjercicioPRO_MB;
    }

    /**
     * @return the resultadoPRO_MB
     */
    public String getResultadoPRO_MB() {
        return resultadoPRO_MB;
    }

    /**
     * @param resultadoPRO_MB the resultadoPRO_MB to set
     */
    public void setResultadoPRO_MB(String resultadoPRO_MB) {
        this.resultadoPRO_MB = resultadoPRO_MB;
    }

    /**
     * @return the sessionEjercicio
     */
    public String getSessionEjercicio() {
        return sessionEjercicio;
    }

    /**
     * @param sessionEjercicio the sessionEjercicio to set
     */
    public void setSessionEjercicio(String sessionEjercicio) {
        this.sessionEjercicio = sessionEjercicio;
    }

    /**
     * @return the listCasoPrueba
     */
    public List<CasoPruebaProBeans> getListCasoPrueba() {
        return listCasoPrueba;
    }

    /**
     * @param listCasoPrueba the listCasoPrueba to set
     */
    public void setListCasoPrueba(List<CasoPruebaProBeans> listCasoPrueba) {
        this.listCasoPrueba = listCasoPrueba;
    }

    /**
     * @return the idParametroPRO_MB
     */
    public int getIdParametroPRO_MB() {
        return idParametroPRO_MB;
    }

    /**
     * @param idParametroPRO_MB the idParametroPRO_MB to set
     */
    public void setIdParametroPRO_MB(int idParametroPRO_MB) {
        this.idParametroPRO_MB = idParametroPRO_MB;
    }

    /**
     * @return the caso_idCasoPruebaPRO_MB
     */
    public int getCaso_idCasoPruebaPRO_MB() {
        return caso_idCasoPruebaPRO_MB;
    }

    /**
     * @param caso_idCasoPruebaPRO_MB the caso_idCasoPruebaPRO_MB to set
     */
    public void setCaso_idCasoPruebaPRO_MB(int caso_idCasoPruebaPRO_MB) {
        this.caso_idCasoPruebaPRO_MB = caso_idCasoPruebaPRO_MB;
    }

    /**
     * @return the tipoPRO_MB
     */
    public String getTipoPRO_MB() {
        return tipoPRO_MB;
    }

    /**
     * @param tipoPRO_MB the tipoPRO_MB to set
     */
    public void setTipoPRO_MB(String tipoPRO_MB) {
        this.tipoPRO_MB = tipoPRO_MB;
    }

    /**
     * @return the nombreVariablePRO_MB
     */
    public String getNombreVariablePRO_MB() {
        return nombreVariablePRO_MB;
    }

    /**
     * @param nombreVariablePRO_MB the nombreVariablePRO_MB to set
     */
    public void setNombreVariablePRO_MB(String nombreVariablePRO_MB) {
        this.nombreVariablePRO_MB = nombreVariablePRO_MB;
    }

    /**
     * @return the valorPRO_MB
     */
    public String getValorPRO_MB() {
        return valorPRO_MB;
    }

    /**
     * @param valorPRO_MB the valorPRO_MB to set
     */
    public void setValorPRO_MB(String valorPRO_MB) {
        this.valorPRO_MB = valorPRO_MB;
    }

    /**
     * @return the sessionCasoPrueba
     */
    public String getSessionCasoPrueba() {
        return sessionCasoPrueba;
    }

    /**
     * @param sessionCasoPrueba the sessionCasoPrueba to set
     */
    public void setSessionCasoPrueba(String sessionCasoPrueba) {
        this.sessionCasoPrueba = sessionCasoPrueba;
    }

    /**
     * @return the listParametros
     */
    public List<CasoPruebaProBeans> getListParametros() {
        return listParametros;
    }

    /**
     * @param listParametros the listParametros to set
     */
    public void setListParametros(List<CasoPruebaProBeans> listParametros) {
        this.listParametros = listParametros;
    }

    /**
     * @return the casoPruebaEntero
     */
    public int getCasoPruebaEntero() {
        return casoPruebaEntero;
    }

    /**
     * @param casoPruebaEntero the casoPruebaEntero to set
     */
    public void setCasoPruebaEntero(int casoPruebaEntero) {
        this.casoPruebaEntero = casoPruebaEntero;
    }

    /**
     * @return the selectedCar2
     */
    public CasoPruebaProBeans getSelectedCar2() {
        return selectedCar2;
    }

    /**
     * @param selectedCar2 the selectedCar2 to set
     */
    public void setSelectedCar2(CasoPruebaProBeans selectedCar2) {
        this.selectedCar2 = selectedCar2;
    }

    /**
     * @return the selectedCar3
     */
    public CasoPruebaProBeans getSelectedCar3() {
        return selectedCar3;
    }

    /**
     * @param selectedCar3 the selectedCar3 to set
     */
    public void setSelectedCar3(CasoPruebaProBeans selectedCar3) {
        this.selectedCar3 = selectedCar3;
    }

    /**
     * @return the editarSalida
     */
    public List<CasoPruebaProBeans> getEditarSalida() {
        return editarSalida;
    }

    /**
     * @param editarSalida the editarSalida to set
     */
    public void setEditarSalida(List<CasoPruebaProBeans> editarSalida) {
        this.editarSalida = editarSalida;
    }

    /**
     * @return the editarEnable
     */
    public boolean isEditarEnable() {
        return editarEnable;
    }

    /**
     * @param editarEnable the editarEnable to set
     */
    public void setEditarEnable(boolean editarEnable) {
        this.editarEnable = editarEnable;
    }

    /**
     * @return the sessionSalida
     */
    public String getSessionSalida() {
        return sessionSalida;
    }

    /**
     * @param sessionSalida the sessionSalida to set
     */
    public void setSessionSalida(String sessionSalida) {
        this.sessionSalida = sessionSalida;
    }

    /**
     * @return the sessionParametro
     */
    public String getSessionParametro() {
        return sessionParametro;
    }

    /**
     * @param sessionParametro the sessionParametro to set
     */
    public void setSessionParametro(String sessionParametro) {
        this.sessionParametro = sessionParametro;
    }

    /**
     * @return the editarParametros
     */
    public List<CasoPruebaProBeans> getEditarParametros() {
        return editarParametros;
    }

    /**
     * @param editarParametros the editarParametros to set
     */
    public void setEditarParametros(List<CasoPruebaProBeans> editarParametros) {
        this.editarParametros = editarParametros;
    }

    /**
     * @return the editarParametro
     */
    public boolean isEditarParametro() {
        return editarParametro;
    }

    /**
     * @param editarParametro the editarParametro to set
     */
    public void setEditarParametro(boolean editarParametro) {
        this.editarParametro = editarParametro;
    }

}
