/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.ClassProBeans;
import mx.uatx.tesis.daos.ClassProDAO;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean

@ViewScoped
public class ClassProMB  implements Serializable{

    //VARIABLES PARA LA SESSION
    private String usuarioXX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    // tabla  ClaseProfesor
    private int id;
    private int idClaseProfesor;
    private int usuario_idUsuario;
    private String nombreClass;
    private List<ClassProBeans> listClasePro = new ArrayList<ClassProBeans>();
    private List<ClassProBeans> listclaseEditar = new ArrayList<ClassProBeans>();
    private String claseEditar;
    private String claseEliminar;
    private String verTemario;
    private boolean statusNuevaClase;
    private boolean stauseditarClase;
    private int volorX;
    private ClassProBeans selectedCar2;

    /**
     * Creates a new instance of ClassProMB
     */
    public ClassProMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        sessionLogin();

        ClassProDAO claseDao = new ClassProDAO();

        setListClasePro(claseDao.retriveListClasePro(sessionLogin()));
    }

    public void redirectToClass() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_Clases.xhtml");
        
    }

    public int sessionLogin() {

        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionIDUsuario") != null) {
            setUsuarioXX(this.httpServletRequest.getSession().getAttribute("sessionIDUsuario").toString());

        }
        int a = Integer.parseInt(getUsuarioXX());

        return a;
    }

    public int sesionIdEjercicio() {

        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionIDEjercicioX") != null) {
            setUsuarioXX(this.httpServletRequest.getSession().getAttribute("sessionIDEjercicioX").toString());

        }
        int a = Integer.parseInt(getUsuarioXX());

        return a;
    }

    public void saveClasePro() {

        int idUsuarioX = Integer.parseInt(getUsuarioXX());
        ClassProDAO claseDAO = new ClassProDAO();
        ClassProBeans clase = new ClassProBeans();

        if (!isStatusNuevaClase()) {

            clase.setNombreClass(this.getNombreClass());
            claseDAO.createClasePro(clase, idUsuarioX);
        } else {

            clase.setIdClaseProfesor(sesionIdEjercicio());
            clase.setUsuario_idUsuario(sessionLogin());
            clase.setNombreClass(this.nombreClass);

            claseDAO.UpdateclaseProf(clase);

            setStatusNuevaClase(false);

        }

        setListClasePro(claseDAO.retriveListClasePro(idUsuarioX));

        setNombreClass(null);

        
    }

    public void updateClaseMetodo() {

        ClassProBeans campos = new ClassProBeans();
        ClassProDAO camposDao = new ClassProDAO();

        campos.setIdClaseProfesor(sesionIdEjercicio());
        campos.setUsuario_idUsuario(sessionLogin());
        campos.setNombreClass(this.nombreClass);

        camposDao.UpdateclaseProf(campos);

        setStatusNuevaClase(true);
        setStauseditarClase(false);

    }

    public void passParamEliminar(int id) {

        ClassProDAO clasDAO = new ClassProDAO();

        if (clasDAO.retriveListClaseEliminar(id)) {
            //clasDAO.retriveListClaseEliminar(idActual);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito..!", "CLASE ELIMINADA"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Alerta..!", "ELIMINA LOS TEMAS ASOCIADOS CON ESTA CLASE "));
        }

        clasDAO.retriveListClasePro(sessionLogin());
        setListClasePro(clasDAO.retriveListClasePro(sessionLogin()));

        
    }

    public void editarClase(int id) throws IOException {

        subirIdEjercicio(id);
        ClassProDAO editarClase = new ClassProDAO();

        setListclaseEditar(editarClase.retriveClaseProEditar(sessionLogin(), id));
        for (int x = 0; x < getListclaseEditar().size(); x++) {
            ClassProBeans clase = (ClassProBeans) getListclaseEditar().get(x);
            setIdClaseProfesor(clase.getIdClaseProfesor());
            setUsuario_idUsuario(clase.getUsuario_idUsuario());
            setNombreClass(clase.getNombreClass());

        }
        setStatusNuevaClase(true);
        
    }

    public void redirectToTemario(int id) throws IOException {

        ClassProDAO clasDAO = new ClassProDAO();

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*******NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionIDClase");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionIDClase", id);

        } else {
            System.out.println("********NO session");
        }
        TemarioProMB tem = new TemarioProMB();
        tem.listarTemario();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_Temario.xhtml");

        
    }

    public void subirIdEjercicio(int idEjercicio) throws IOException {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*******NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionIDEjercicioX");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            //logout();
            //sesion.setAttribute("sessionIDClase", "");

            sesion.setAttribute("sessionIDEjercicioX", idEjercicio);

        } else {
            System.out.println("******NO session");
        }

    }

    public String logout() {
        //Subir a sesión credenciasles        
        HttpSession sesion = null;
        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            sesion.invalidate();
        }
        return "sessionIDClase";
    }

    /**
     * @return the idClaseProfesor
     */
    public int getIdClaseProfesor() {
        return idClaseProfesor;
    }

    /**
     * @param idClaseProfesor the idClaseProfesor to set
     */
    public void setIdClaseProfesor(int idClaseProfesor) {
        this.idClaseProfesor = idClaseProfesor;
    }

    /**
     * @return the usuario_idUsuario
     */
    public int getUsuario_idUsuario() {
        return usuario_idUsuario;
    }

    /**
     * @param usuario_idUsuario the usuario_idUsuario to set
     */
    public void setUsuario_idUsuario(int usuario_idUsuario) {
        this.usuario_idUsuario = usuario_idUsuario;
    }

    /**
     * @return the nombreClass
     */
    public String getNombreClass() {
        return nombreClass;
    }

    /**
     * @param nombreClass the nombreClass to set
     */
    public void setNombreClass(String nombreClass) {
        this.nombreClass = nombreClass;
    }

    /**
     * @return the listClasePro
     */
    public List<ClassProBeans> getListClasePro() {
        return listClasePro;
    }

    /**
     * @param listClasePro the listClasePro to set
     */
    public void setListClasePro(List<ClassProBeans> listClasePro) {
        this.listClasePro = listClasePro;
    }

    /**
     * @return the claseEditar
     */
    public String getClaseEditar() {
        return claseEditar;
    }

    /**
     * @param claseEditar the claseEditar to set
     */
    public void setClaseEditar(String claseEditar) {
        this.claseEditar = claseEditar;
    }

    /**
     * @return the claseEliminar
     */
    public String getClaseEliminar() {
        return claseEliminar;
    }

    /**
     * @param claseEliminar the claseEliminar to set
     */
    public void setClaseEliminar(String claseEliminar) {
        this.claseEliminar = claseEliminar;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuarioXX
     */
    public String getUsuarioXX() {
        return usuarioXX;
    }

    /**
     * @param usuarioXX the usuarioXX to set
     */
    public void setUsuarioXX(String usuarioXX) {
        this.usuarioXX = usuarioXX;
    }

    /**
     * @return the verTemario
     */
    public String getVerTemario() {
        return verTemario;
    }

    /**
     * @param verTemario the verTemario to set
     */
    public void setVerTemario(String verTemario) {
        this.verTemario = verTemario;
    }

    /**
     * @return the listclaseEditar
     */
    public List<ClassProBeans> getListclaseEditar() {
        return listclaseEditar;
    }

    /**
     * @param listclaseEditar the listclaseEditar to set
     */
    public void setListclaseEditar(List<ClassProBeans> listclaseEditar) {
        this.listclaseEditar = listclaseEditar;
    }

    /**
     * @return the statusNuevaClase
     */
    public boolean isStatusNuevaClase() {
        return statusNuevaClase;
    }

    /**
     * @param statusNuevaClase the statusNuevaClase to set
     */
    public void setStatusNuevaClase(boolean statusNuevaClase) {
        this.statusNuevaClase = statusNuevaClase;
    }

    /**
     * @return the stauseditarClase
     */
    public boolean isStauseditarClase() {
        return stauseditarClase;
    }

    /**
     * @param stauseditarClase the stauseditarClase to set
     */
    public void setStauseditarClase(boolean stauseditarClase) {
        this.stauseditarClase = stauseditarClase;
    }

    /**
     * @return the volorX
     */
    public int getVolorX() {
        return volorX;
    }

    /**
     * @param volorX the volorX to set
     */
    public void setVolorX(int volorX) {
        this.volorX = volorX;
    }

    /**
     * @return the selectedCar2
     */
    public ClassProBeans getSelectedCar2() {
        return selectedCar2;
    }

    /**
     * @param selectedCar2 the selectedCar2 to set
     */
    public void setSelectedCar2(ClassProBeans selectedCar2) {
        this.selectedCar2 = selectedCar2;
    }

}
