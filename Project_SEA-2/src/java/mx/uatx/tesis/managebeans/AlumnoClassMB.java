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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.AlumnoClassBeans;
import mx.uatx.tesis.daos.AlumnoClassDAO;
//import mx.uatx.tesis.daos.ClassProDAO;
//import mx.uatx.tesis.daos.ClassProDAO;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
//@ R equestScoped
@SessionScoped
public class AlumnoClassMB {

    //VARIABLES PARA LA SESSION
    private String idUsuarioXX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    private String verClase;
    private List<SelectItem> opcsProf;
    //private int opcionActual_Prof = -1;
    private List<SelectItem> opcsClase;
    //private int opcionActual_Clase = -1;
    private String opcionActual_Clase2 = "-1";
    private String opcionActual_Prof2 = "-1";

    private List<AlumnoClassBeans> listClaseAlumno = new ArrayList<AlumnoClassBeans>();
    private List<AlumnoClassBeans> totalClaseList = new ArrayList<AlumnoClassBeans>();

    // tabla claseAlumno;
    private int idClaseAlumnoMB;
    private int usuario_idUsuClaseAluMB;
    private int clasePro_idClaseProClaseAlumMB;

    // tabla Usuarios
    private int idUsuarioMB;
    private String nombreUsuMB;
    private int rol_idRolMB;
    // tabla  ClaseProfesor
    private int idClaseProfesorMB;
    private int usuario_idUsuarioMB;
    private String nombreClassMB;
    //Eliminar clase
    private String claseAlumnoEliminar;
    private String redirectClass;
    private AlumnoClassBeans selectedCar2;

    /**
     * Creates a new instance of AlumnoClassMB
     */
    public AlumnoClassMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        downSession1();
        listProf();

    }

    public String downSession1() {
        if (httpServletRequest.getSession().getAttribute("sessionIDUsuario") != null) {
            setIdUsuarioXX(this.httpServletRequest.getSession().getAttribute("sessionIDUsuario").toString());

        }
        String idClase = getIdUsuarioXX();

        return idClase;
    }

    public void listProf() {

        AlumnoClassDAO profeDAO = new AlumnoClassDAO();

        List<AlumnoClassBeans> profesor = profeDAO.retriveListProfesores();
        SelectItem si3 = new SelectItem();
        opcsProf = new ArrayList<SelectItem>();

        int a2 = 0;
        String b2 = "";

        for (int x = 0; x < profesor.size(); x++) {

            a2 = profesor.get(x).getIdUsuario();
            b2 = profesor.get(x).getNombreUsu();

            si3 = new SelectItem(a2, b2);
            this.opcsProf.add(si3);

        }
        //Carga los valores de para llenar la tabla 
        int idUser = Integer.parseInt(getIdUsuarioXX());
        setListClaseAlumno(profeDAO.retriveListClasesID(idUser));
        
    }

    public void listClass() {

        int idActual = 0;
        AlumnoClassDAO claseDAO = new AlumnoClassDAO();
        idActual = Integer.parseInt(getOpcionActual_Prof2());

        List<AlumnoClassBeans> clase = claseDAO.retriveListClases(idActual);
        SelectItem si33 = new SelectItem();
        opcsClase = new ArrayList<SelectItem>();

        int a2 = 0;
        int c2 = 0;
        String b2 = "";

        for (int x = 0; x < clase.size(); x++) {

            a2 = clase.get(x).getIdClaseProfesor();
            c2 = clase.get(x).getUsuario_idUsuario();
            b2 = clase.get(x).getNombreClass();

            si33 = new SelectItem(a2, b2, c2 + "");
            this.opcsClase.add(si33);

        }

        
    }

    public void saveClaseAlumno() {

        int idActualP = Integer.parseInt(getOpcionActual_Prof2());
        int idActualC = Integer.parseInt(getOpcionActual_Clase2());
        int usuario = Integer.parseInt(getIdUsuarioXX());
        int total = 0;
        FacesContext context = FacesContext.getCurrentInstance();

        AlumnoClassDAO alumClassDAO = new AlumnoClassDAO();
        AlumnoClassBeans alumClassBeans = new AlumnoClassBeans();

        // alumClassBeans.setUsuario_idUsuClaseAlu(idActualP);
        alumClassBeans.setClasePro_idClaseProClaseAlum(idActualC);
        setTotalClaseList(alumClassDAO.retriveCalseExist(alumClassBeans, usuario));

        for (int x = 0; x < getTotalClaseList().size(); x++) {
            AlumnoClassBeans casoBeans = (AlumnoClassBeans) getTotalClaseList().get(x);
            total = casoBeans.getTotalClases();
        }

        if (total != 0) {
            context.addMessage(null, new FacesMessage("Alerta..!", "Ya Estas Inscrito A Esta Clase"));

        } else {
            alumClassDAO.createClasePro(alumClassBeans, usuario);
            context.addMessage(null, new FacesMessage("Felicidades..!", "Inscrito Correctamente"));

        }

        //Carga los valores de para llenar la tabla 
        AlumnoClassDAO profeDAO = new AlumnoClassDAO();
        int idUser = Integer.parseInt(getIdUsuarioXX());
        setListClaseAlumno(profeDAO.retriveListClasesID(idUser));

    
    }

    public void passParamEliminar(int id) {

        FacesContext context = FacesContext.getCurrentInstance();
        AlumnoClassDAO claseAlumnoDAO = new AlumnoClassDAO();

        if (claseAlumnoDAO.retriveListClaseAlumnoEliminar(id)) {
            context.addMessage(null, new FacesMessage("Exito.!", "Clase Eliminada"));
        } else {
            context.addMessage(null, new FacesMessage("Alerta..!", "No se elimino la clase"));
        }

        //Carga los valores de para llenar la tabla 
        AlumnoClassDAO profeDAO = new AlumnoClassDAO();
        int idUser = Integer.parseInt(getIdUsuarioXX());
        setListClaseAlumno(profeDAO.retriveListClasesID(idUser));

        
    }

    public void claseAlumnoUp(int id) throws IOException {

        int idClaseAlumno = 0;
        int idUsuario = 0;
        int idClaseProfesor = 0;
        AlumnoClassDAO claseAlumnoDAO = new AlumnoClassDAO();
        List<AlumnoClassBeans> listClaseAlum = claseAlumnoDAO.listarClases(id);

        for (int x = 0; x < listClaseAlum.size(); x++) {
            AlumnoClassBeans casoBeans = (AlumnoClassBeans) listClaseAlum.get(x);
            idClaseAlumno = casoBeans.getIdclaseUp();
            idUsuario = casoBeans.getIdusuarioUp();
            idClaseProfesor = casoBeans.getIdClaseProfUp();
        }
        upClaseAlumno(idClaseAlumno);
        redirectToTemarioAlumno(idClaseProfesor);

    }

    public void redirectToTemarioAlumno(int id) throws IOException {
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

            sesion.setAttribute("sessionIDRerirectClase", id);

            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
            ctx.redirect("Alumnos_Temario.xhtml");

        } else {
            System.out.println("NO session");
        }

        
    }

    public void upClaseAlumno(int id) throws IOException {

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

            //sesion.setAttribute("sessionIDClase", "");
            sesion.setAttribute("sessionUPclaseAlumno", id);

        } else {
            System.out.println("***NO session");
        }

        
    }

    /**
     * @return the idClaseAlumnoMB
     */
    public int getIdClaseAlumnoMB() {
        return idClaseAlumnoMB;
    }

    /**
     * @param idClaseAlumnoMB the idClaseAlumnoMB to set
     */
    public void setIdClaseAlumnoMB(int idClaseAlumnoMB) {
        this.idClaseAlumnoMB = idClaseAlumnoMB;
    }

    /**
     * @return the usuario_idUsuClaseAluMB
     */
    public int getUsuario_idUsuClaseAluMB() {
        return usuario_idUsuClaseAluMB;
    }

    /**
     * @param usuario_idUsuClaseAluMB the usuario_idUsuClaseAluMB to set
     */
    public void setUsuario_idUsuClaseAluMB(int usuario_idUsuClaseAluMB) {
        this.usuario_idUsuClaseAluMB = usuario_idUsuClaseAluMB;
    }

    /**
     * @return the clasePro_idClaseProClaseAlumMB
     */
    public int getClasePro_idClaseProClaseAlumMB() {
        return clasePro_idClaseProClaseAlumMB;
    }

    /**
     * @param clasePro_idClaseProClaseAlumMB the clasePro_idClaseProClaseAlumMB
     * to set
     */
    public void setClasePro_idClaseProClaseAlumMB(int clasePro_idClaseProClaseAlumMB) {
        this.clasePro_idClaseProClaseAlumMB = clasePro_idClaseProClaseAlumMB;
    }

    /**
     * @return the opcsProf
     */
    public List<SelectItem> getOpcsProf() {

        return opcsProf;
    }

    /**
     * @param opcsProf the opcsProf to set
     */
    public void setOpcsProf(List<SelectItem> opcsProf) {
        this.opcsProf = opcsProf;
    }

    /**
     * @return the idUsuarioMB
     */
    public int getIdUsuarioMB() {
        return idUsuarioMB;
    }

    /**
     * @param idUsuarioMB the idUsuarioMB to set
     */
    public void setIdUsuarioMB(int idUsuarioMB) {
        this.idUsuarioMB = idUsuarioMB;
    }

    /**
     * @return the nombreUsuMB
     */
    public String getNombreUsuMB() {
        return nombreUsuMB;
    }

    /**
     * @param nombreUsuMB the nombreUsuMB to set
     */
    public void setNombreUsuMB(String nombreUsuMB) {
        this.nombreUsuMB = nombreUsuMB;
    }

    /**
     * @return the rol_idRolMB
     */
    public int getRol_idRolMB() {
        return rol_idRolMB;
    }

    /**
     * @param rol_idRolMB the rol_idRolMB to set
     */
    public void setRol_idRolMB(int rol_idRolMB) {
        this.rol_idRolMB = rol_idRolMB;
    }

    /**
     * @return the idClaseProfesorMB
     */
    public int getIdClaseProfesorMB() {
        return idClaseProfesorMB;
    }

    /**
     * @param idClaseProfesorMB the idClaseProfesorMB to set
     */
    public void setIdClaseProfesorMB(int idClaseProfesorMB) {
        this.idClaseProfesorMB = idClaseProfesorMB;
    }

    /**
     * @return the usuario_idUsuarioMB
     */
    public int getUsuario_idUsuarioMB() {
        return usuario_idUsuarioMB;
    }

    /**
     * @param usuario_idUsuarioMB the usuario_idUsuarioMB to set
     */
    public void setUsuario_idUsuarioMB(int usuario_idUsuarioMB) {
        this.usuario_idUsuarioMB = usuario_idUsuarioMB;
    }

    /**
     * @return the nombreClassMB
     */
    public String getNombreClassMB() {
        return nombreClassMB;
    }

    /**
     * @param nombreClassMB the nombreClassMB to set
     */
    public void setNombreClassMB(String nombreClassMB) {
        this.nombreClassMB = nombreClassMB;
    }

    /**
     * @return the verClase
     */
    public String getVerClase() {
        return verClase;
    }

    /**
     * @param verClase the verClase to set
     */
    public void setVerClase(String verClase) {
        this.verClase = verClase;
    }

    /**
     * @return the opcsClase
     */
    public List<SelectItem> getOpcsClase() {
        listClass();
        return opcsClase;
    }

    /**
     * @param opcsClase the opcsClase to set
     */
    public void setOpcsClase(List<SelectItem> opcsClase) {
        this.opcsClase = opcsClase;
    }

    /**
     * @return the opcionActual_Clase2
     */
    public String getOpcionActual_Clase2() {

        return opcionActual_Clase2;
    }

    /**
     * @param opcionActual_Clase2 the opcionActual_Clase2 to set
     */
    public void setOpcionActual_Clase2(String opcionActual_Clase2) {
        this.opcionActual_Clase2 = opcionActual_Clase2;
    }

    /**
     * @return the opcionActual_Prof2
     */
    public String getOpcionActual_Prof2() {

        return opcionActual_Prof2;
    }

    /**
     * @param opcionActual_Prof2 the opcionActual_Prof2 to set
     */
    public void setOpcionActual_Prof2(String opcionActual_Prof2) {

        this.opcionActual_Prof2 = opcionActual_Prof2;
    }

    /**
     * @return the listClaseAlumno
     */
    public List<AlumnoClassBeans> getListClaseAlumno() {
        return listClaseAlumno;
    }

    /**
     * @param listClaseAlumno the listClaseAlumno to set
     */
    public void setListClaseAlumno(List<AlumnoClassBeans> listClaseAlumno) {
        this.listClaseAlumno = listClaseAlumno;
    }

    /**
     * @return the idUsuarioXX
     */
    public String getIdUsuarioXX() {
        return idUsuarioXX;
    }

    /**
     * @param idUsuarioXX the idUsuarioXX to set
     */
    public void setIdUsuarioXX(String idUsuarioXX) {
        this.idUsuarioXX = idUsuarioXX;
    }

    /**
     * @return the claseAlumnoEliminar
     */
    public String getClaseAlumnoEliminar() {
        return claseAlumnoEliminar;
    }

    /**
     * @param claseAlumnoEliminar the claseAlumnoEliminar to set
     */
    public void setClaseAlumnoEliminar(String claseAlumnoEliminar) {
        this.claseAlumnoEliminar = claseAlumnoEliminar;
    }

    /**
     * @return the redirectClass
     */
    public String getRedirectClass() {
        return redirectClass;
    }

    /**
     * @param redirectClass the redirectClass to set
     */
    public void setRedirectClass(String redirectClass) {
        this.redirectClass = redirectClass;
    }

    /**
     * @return the selectedCar2
     */
    public AlumnoClassBeans getSelectedCar2() {
        return selectedCar2;
    }

    /**
     * @param selectedCar2 the selectedCar2 to set
     */
    public void setSelectedCar2(AlumnoClassBeans selectedCar2) {
        this.selectedCar2 = selectedCar2;
    }

    /**
     * @return the totalClaseList
     */
    public List<AlumnoClassBeans> getTotalClaseList() {
        return totalClaseList;
    }

    /**
     * @param totalClaseList the totalClaseList to set
     */
    public void setTotalClaseList(List<AlumnoClassBeans> totalClaseList) {
        this.totalClaseList = totalClaseList;
    }

}
