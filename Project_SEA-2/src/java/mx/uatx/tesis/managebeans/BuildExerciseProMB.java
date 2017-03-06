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
import mx.uatx.tesis.beans.BuildExerciseProBeans;
import mx.uatx.tesis.beans.EjerciciosProListBeans;
import mx.uatx.tesis.daos.BuildExerciseProDAO;
import mx.uatx.tesis.daos.EjerciciosProListDAO;


/**
 *
 * @author tlp_0_000
 */
@ManagedBean

@ViewScoped
public class BuildExerciseProMB implements Serializable{

    //VARIABLES PARA LA SESSION
    private String sessionEjercicio;
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
    private String codigoFuenteSubMB = "public  TIPO DE DATO  validaEjercicio() {\n"
            + "\n"
            + "\n"
            + " return  ; \n"
            + "} ";
    private String tiempoTotalMB;
    private int intentosTotalMB;
    private String tipoMB;
    //Enable botones
    private boolean btn1 = true;
    private boolean btn2;
    // tabla ejercicioProfesor
    private int idEjercicioProfeMB_2;
    private int subtemaProfe_idSubtemaProfeMB_2;
    private String nombreSubMB_2;
    private String descripcionSubMB_2;
    private String sugerenciaSubMB_2;
    private String codigoFuenteSubMB_2;
    private EjerciciosProListBeans selectedCar2;

    private List<EjerciciosProListBeans> listEjercicioPro = new ArrayList<EjerciciosProListBeans>();
    private List<BuildExerciseProBeans> editarEjercicio = new ArrayList<BuildExerciseProBeans>();

    /**
     * Creates a new instance of BuildExerciseProMB
     */
    public BuildExerciseProMB() {
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

    public int sesionIdEjercicio() {

        //Bajar credenciales
        if (httpServletRequest.getSession().getAttribute("sessionEjercicioUpdate") != null) {
            setSessionEjercicio(this.httpServletRequest.getSession().getAttribute("sessionEjercicioUpdate").toString());

        }
        int a = Integer.parseInt(getSessionEjercicio());

        return a;
    }

    public void tipoCodigo() {
        if (getTipoMB().equals("1")) {
            codigoFuenteSubMB = "public int validaEjercicio() {\n"
                    + "\n"
                    + "\n"
                    + " return 0; \n"
                    + "} ";
        } else if (getTipoMB().equals("2")) {
            codigoFuenteSubMB = "public String validaEjercicio() {\n"
                    + "\n"
                    + "\n"
                    + " return null; \n"
                    + "} ";
        } else {
            codigoFuenteSubMB = "public Float validaEjercicio() {\n"
                    + "\n"
                    + "\n"
                    + " return 0; \n"
                    + "} ";
        }

        
    }

    public void saveExercisePro() {

        BuildExerciseProDAO excerciseDAO = new BuildExerciseProDAO();
        BuildExerciseProBeans exerciseBeans = new BuildExerciseProBeans();

        String replace1 = getNombreSubMB().replaceAll("'", "\\\\'");
        String replace2 = getDescripcionSubMB().replaceAll("'", "\\\\'");
        String replace3 = getSugerenciaSubMB().replaceAll("'", "\\\\'");
        String replace4 = getCodigoFuenteSubMB().replaceAll("'", "\\\\'");

        if (isBtn1()) {

            exerciseBeans.setNombreSub(replace1);
            exerciseBeans.setDescripcionSub(replace2);
            exerciseBeans.setSugerenciaSub(replace3);
            exerciseBeans.setCodigoFuenteSub(replace4);
            exerciseBeans.setTiempitotal(getTiempoTotalMB());
            exerciseBeans.setIntentosTotal(getIntentosTotalMB());
            exerciseBeans.setTipo(getTipoMB());

            int idSubtema = Integer.parseInt(getSubtemaXX());

            excerciseDAO.createExercisePro(exerciseBeans, idSubtema);

        } else {

            int id = sesionIdEjercicio();
            exerciseBeans.setIdEjercicioProfe(id);
            exerciseBeans.setNombreSub(replace1);
            exerciseBeans.setDescripcionSub(replace2);
            exerciseBeans.setSugerenciaSub(replace3);
            exerciseBeans.setCodigoFuenteSub(replace4);
            exerciseBeans.setTiempitotal(getTiempoTotalMB());
            exerciseBeans.setIntentosTotal(getIntentosTotalMB());
            exerciseBeans.setTipo(getTipoMB());

            excerciseDAO.UpdateEjercicioProf(exerciseBeans);

        }

        //Datos para llenar la tabla 
        EjerciciosProListDAO ejercicioDAO = new EjerciciosProListDAO();
        int idUser = Integer.parseInt(getSubtemaXX());
        setListEjercicioPro(ejercicioDAO.retriveListEjercicioPro(idUser));

        //reset
        setNombreSubMB(null);
        setDescripcionSubMB(null);
        setSugerenciaSubMB(null);
        setCodigoFuenteSubMB(null);
        setBtn1(true);
    }

    public void deleteEjercicio(int id) {

        BuildExerciseProDAO excerciseDAO = new BuildExerciseProDAO();
        BuildExerciseProBeans exerciseBeans = new BuildExerciseProBeans();

        EjerciciosProListDAO ejercicioDAO = new EjerciciosProListDAO();
        if (ejercicioDAO.deleteEjercicio(id)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito..!", "EJERCICIO ELIMINADO "));

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Alerta..!", "PRIMERO ELIMINA CASOS DE PRUEBA ASOCIADOS "));

        }

        //Datos para llenar la tabla 
        int idUser = Integer.parseInt(getSubtemaXX());
        setListEjercicioPro(ejercicioDAO.retriveListEjercicioPro(idUser));

    }

    public void editarEjercicio(int id) throws IOException {

        BuildExerciseProDAO excerciseDAO = new BuildExerciseProDAO();
        //BuildExerciseProBeans exerciseBeans = new BuildExerciseProBeans();

        setEditarEjercicio(excerciseDAO.retriveEjercicioPRO(id));

        for (int x = 0; x < getEditarEjercicio().size(); x++) {

            BuildExerciseProBeans clase = (BuildExerciseProBeans) getEditarEjercicio().get(x);

            setNombreSubMB(clase.getNombreSub());
            setDescripcionSubMB(clase.getDescripcionSub());
            setSugerenciaSubMB(clase.getSugerenciaSub());
            setCodigoFuenteSubMB(clase.getCodigoFuenteSub());
            setTiempoTotalMB(clase.getTiempitotal());
            setIntentosTotalMB(clase.getIntentosTotal());
            setTipoMB(clase.getTipo());
        }
        subirIdEjercicio(id);// subir el id del ejercicio Update a sesion 
        setBtn1(false);
    }

    public void redirectToCasoPrueba(int id) throws IOException {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("****NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionIDEjercicio");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            //logout();
            //sesion.setAttribute("sessionIDClase", "");

            sesion.setAttribute("sessionIDEjercicio", id);

        } else {
            System.out.println("*****NO session");
        }

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_AgregarCasoPrueba.xhtml");

    }

    public void subirIdEjercicio(int idEjercicio) throws IOException {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*********NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sessionEjercicioUpdate");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            //logout();
            //sesion.setAttribute("sessionIDClase", "");

            sesion.setAttribute("sessionEjercicioUpdate", idEjercicio);

        } else {
            System.out.println("********NO session");
        }

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

    /**
     * @return the btn1
     */
    public boolean isBtn1() {
        return btn1;
    }

    /**
     * @param btn1 the btn1 to set
     */
    public void setBtn1(boolean btn1) {
        this.btn1 = btn1;
    }

    /**
     * @return the btn2
     */
    public boolean isBtn2() {
        return btn2;
    }

    /**
     * @param btn2 the btn2 to set
     */
    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
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
     * @return the idEjercicioProfeMB_2
     */
    public int getIdEjercicioProfeMB_2() {
        return idEjercicioProfeMB_2;
    }

    /**
     * @param idEjercicioProfeMB_2 the idEjercicioProfeMB_2 to set
     */
    public void setIdEjercicioProfeMB_2(int idEjercicioProfeMB_2) {
        this.idEjercicioProfeMB_2 = idEjercicioProfeMB_2;
    }

    /**
     * @return the subtemaProfe_idSubtemaProfeMB_2
     */
    public int getSubtemaProfe_idSubtemaProfeMB_2() {
        return subtemaProfe_idSubtemaProfeMB_2;
    }

    /**
     * @param subtemaProfe_idSubtemaProfeMB_2 the
     * subtemaProfe_idSubtemaProfeMB_2 to set
     */
    public void setSubtemaProfe_idSubtemaProfeMB_2(int subtemaProfe_idSubtemaProfeMB_2) {
        this.subtemaProfe_idSubtemaProfeMB_2 = subtemaProfe_idSubtemaProfeMB_2;
    }

    /**
     * @return the nombreSubMB_2
     */
    public String getNombreSubMB_2() {
        return nombreSubMB_2;
    }

    /**
     * @param nombreSubMB_2 the nombreSubMB_2 to set
     */
    public void setNombreSubMB_2(String nombreSubMB_2) {
        this.nombreSubMB_2 = nombreSubMB_2;
    }

    /**
     * @return the descripcionSubMB_2
     */
    public String getDescripcionSubMB_2() {
        return descripcionSubMB_2;
    }

    /**
     * @param descripcionSubMB_2 the descripcionSubMB_2 to set
     */
    public void setDescripcionSubMB_2(String descripcionSubMB_2) {
        this.descripcionSubMB_2 = descripcionSubMB_2;
    }

    /**
     * @return the sugerenciaSubMB_2
     */
    public String getSugerenciaSubMB_2() {
        return sugerenciaSubMB_2;
    }

    /**
     * @param sugerenciaSubMB_2 the sugerenciaSubMB_2 to set
     */
    public void setSugerenciaSubMB_2(String sugerenciaSubMB_2) {
        this.sugerenciaSubMB_2 = sugerenciaSubMB_2;
    }

    /**
     * @return the codigoFuenteSubMB_2
     */
    public String getCodigoFuenteSubMB_2() {
        return codigoFuenteSubMB_2;
    }

    /**
     * @param codigoFuenteSubMB_2 the codigoFuenteSubMB_2 to set
     */
    public void setCodigoFuenteSubMB_2(String codigoFuenteSubMB_2) {
        this.codigoFuenteSubMB_2 = codigoFuenteSubMB_2;
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
     * @return the tiempoTotalMB
     */
    public String getTiempoTotalMB() {
        return tiempoTotalMB;
    }

    /**
     * @param tiempoTotalMB the tiempoTotalMB to set
     */
    public void setTiempoTotalMB(String tiempoTotalMB) {
        this.tiempoTotalMB = tiempoTotalMB;
    }

    /**
     * @return the intentosTotalMB
     */
    public int getIntentosTotalMB() {
        return intentosTotalMB;
    }

    /**
     * @param intentosTotalMB the intentosTotalMB to set
     */
    public void setIntentosTotalMB(int intentosTotalMB) {
        this.intentosTotalMB = intentosTotalMB;
    }

    /**
     * @return the tipoMB
     */
    public String getTipoMB() {

        return tipoMB;
    }

    /**
     * @param tipoMB the tipoMB to set
     */
    public void setTipoMB(String tipoMB) {

        this.tipoMB = tipoMB;
    }

    /**
     * @return the selectedCar2
     */
    public EjerciciosProListBeans getSelectedCar2() {
        return selectedCar2;
    }

    /**
     * @param selectedCar2 the selectedCar2 to set
     */
    public void setSelectedCar2(EjerciciosProListBeans selectedCar2) {
        this.selectedCar2 = selectedCar2;
    }

    /**
     * @return the editarEjercicio
     */
    public List<BuildExerciseProBeans> getEditarEjercicio() {
        return editarEjercicio;
    }

    /**
     * @param editarEjercicio the editarEjercicio to set
     */
    public void setEditarEjercicio(List<BuildExerciseProBeans> editarEjercicio) {
        this.editarEjercicio = editarEjercicio;
    }

}
