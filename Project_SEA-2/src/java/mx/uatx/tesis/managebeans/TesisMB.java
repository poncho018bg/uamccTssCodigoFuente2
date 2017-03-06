/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URI;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import mx.uatx.tesis.beans.TesisBEANS;
import mx.uatx.tesis.daos.TesisDAO;

/**
 *
 * @author poikjasd
 */
@ManagedBean
@SessionScoped
public class TesisMB {

    private List<TesisBEANS> validarUsuarioExistente = new ArrayList<TesisBEANS>();
    private List<SelectItem> opcs7;
    private List<SelectItem> opcs8;
    private String opcionActual_11 = "-1";
    private int opcionActual_123 = -1;

    private int idRolesMB;
    private String descripcionRolMB;
    // tabla Usuarios
    private int idUsuarioMB;
    private String nombreUsuMB;
    private String apellidoPaternoMB;
    private String apellidoMaternoMB;
    private String correoMB;
    private String passwordMB;
    private String password2MB;
    private int rol_idRolMB;
    // tabla  ClaseProfesor
    private int idClaseProfesorMB;
    private int usuario_idUsuarioMB;
    private String nombreClassMB;
    // tabla Temaprofesor
    private int idTemaProfeMB;
    private String nombreTemaMB;
    private int claseProfe_idClaseProfeMB;
    // tabla subtemaProfesor
    private int idSubtemaProfeMB;
    private String nombreSubtemaMB;
    private int temaProfe_idTemaProfeMB;
    // tabla ejercicioProfesor
    private int idEjercicioProfeMB;
    private int subtemaProfe_idSubtemaProfeMB;
    private String descripcionSubMB;
    private String sugerenciaSubMB;
    private String codigoFuenteSubMB;
    // tabla casoPrueba
    private int idCasoPruebaMB;
    private int ejercicioPro_idEjercicioProMB;
    private String pruebaMB;
    private String resultadoMB;
    // tabla claseAlumno;
    private int idClaseAlumnoMB;
    private int usuario_idUsuClaseAluMB;
    private int clasePro_idClaseProClaseAlumMB;
    // tabla ejercicioAlumno
    private int idEjercicioAlumnoMB;
    private int ejePro_idEjeProEjAlumnoMB;
    private String codigoFuenteAlumnoMB;
    private int casoPrueba_idEjeAluMB;
    private int statusMB;

    /**
     * Creates a new instance of TesisMB
     */
    public TesisMB() {

        listRoles();

    }

    public void loginUsuario() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        TesisBEANS tesisBean = new TesisBEANS();
        TesisDAO conection = new TesisDAO();

        List<TesisBEANS> usuario = conection.retriveLogin(correoMB, passwordMB);

        setValidarUsuarioExistente(conection.retriveLogin(getCorreoMB(), getPasswordMB()));

        tesisBean.setIdUsuario(this.idUsuarioMB);

        if (getValidarUsuarioExistente().size() != 0) {

            SelectItem si3 = new SelectItem();
            setOpcs7(new ArrayList<SelectItem>());

            int a2 = 0;

            for (int x = 0; x < usuario.size(); x++) {
                a2 = usuario.get(x).getRol_idRol();
            }
            if (a2 == 1) {
                ctx.redirect("Alumnos_MiClases.xhtml");

            } else {
                ctx.redirect("Profesor_Bienvenido.xhtml");
            }

        } else {

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Datos no validos.!"));
            

        }
   
    }

    public void listRoles() {

        TesisDAO usuarioDAO = new TesisDAO();

        List<TesisBEANS> rol = usuarioDAO.retriveListRoles();
        SelectItem si3 = new SelectItem();
        opcs7 = new ArrayList<SelectItem>();

        int a2 = 0;
        String b2 = "";

        for (int x = 0; x < rol.size(); x++) {

            a2 = rol.get(x).getIdRoles();
            b2 = rol.get(x).getDescripcionRol();

            si3 = new SelectItem(a2, b2);
            this.opcs7.add(si3);

        }

    }

    public void saveRegistro() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        TesisDAO usuarioDAO = new TesisDAO();
        TesisBEANS usuario = new TesisBEANS();

        usuario.setNombreUsu(this.getNombreUsuMB());
        usuario.setApellidoPaterno(this.getApellidoPaternoMB());
        usuario.setApellidoMaterno(this.getApellidoMaternoMB());
        usuario.setCorreo(this.getCorreoMB());
        usuario.setPassword(this.getPasswordMB());
        usuario.setRol_idRol(this.getOpcionActual_123());

        usuarioDAO.createUsuario(usuario);

        ctx.redirect("index.xhtml");

    }

    public void redirectToNewAccount() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("NewAccount.xhtml");
        
    }

    public void redirectToClass() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_Clases.xhtml");
       
    }

    public void redirectToTemario() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_Temario.xhtml");
        
    }

    /**
     * @return the validarUsuarioExistente
     */
    public List<TesisBEANS> getValidarUsuarioExistente() {
        return validarUsuarioExistente;
    }

    /**
     * @param validarUsuarioExistente the validarUsuarioExistente to set
     */
    public void setValidarUsuarioExistente(List<TesisBEANS> validarUsuarioExistente) {
        this.validarUsuarioExistente = validarUsuarioExistente;
    }

    /**
     * @return the idRolesMB
     */
    public int getIdRolesMB() {
        return idRolesMB;
    }

    /**
     * @param idRolesMB the idRolesMB to set
     */
    public void setIdRolesMB(int idRolesMB) {
        this.idRolesMB = idRolesMB;
    }

    /**
     * @return the descripcionRolMB
     */
    public String getDescripcionRolMB() {
        return descripcionRolMB;
    }

    /**
     * @param descripcionRolMB the descripcionRolMB to set
     */
    public void setDescripcionRolMB(String descripcionRolMB) {
        this.descripcionRolMB = descripcionRolMB;
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
     * @return the apellidoPaternoMB
     */
    public String getApellidoPaternoMB() {
        return apellidoPaternoMB;
    }

    /**
     * @param apellidoPaternoMB the apellidoPaternoMB to set
     */
    public void setApellidoPaternoMB(String apellidoPaternoMB) {
        this.apellidoPaternoMB = apellidoPaternoMB;
    }

    /**
     * @return the apellidoMaternoMB
     */
    public String getApellidoMaternoMB() {
        return apellidoMaternoMB;
    }

    /**
     * @param apellidoMaternoMB the apellidoMaternoMB to set
     */
    public void setApellidoMaternoMB(String apellidoMaternoMB) {
        this.apellidoMaternoMB = apellidoMaternoMB;
    }

    /**
     * @return the correoMB
     */
    public String getCorreoMB() {
        return correoMB;
    }

    /**
     * @param correoMB the correoMB to set
     */
    public void setCorreoMB(String correoMB) {
        this.correoMB = correoMB;
    }

    /**
     * @return the passwordMB
     */
    public String getPasswordMB() {
        return passwordMB;
    }

    /**
     * @param passwordMB the passwordMB to set
     */
    public void setPasswordMB(String passwordMB) {
        this.passwordMB = passwordMB;
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
     * @return the idTemaProfeMB
     */
    public int getIdTemaProfeMB() {
        return idTemaProfeMB;
    }

    /**
     * @param idTemaProfeMB the idTemaProfeMB to set
     */
    public void setIdTemaProfeMB(int idTemaProfeMB) {
        this.idTemaProfeMB = idTemaProfeMB;
    }

    /**
     * @return the nombreTemaMB
     */
    public String getNombreTemaMB() {
        return nombreTemaMB;
    }

    /**
     * @param nombreTemaMB the nombreTemaMB to set
     */
    public void setNombreTemaMB(String nombreTemaMB) {
        this.nombreTemaMB = nombreTemaMB;
    }

    /**
     * @return the claseProfe_idClaseProfeMB
     */
    public int getClaseProfe_idClaseProfeMB() {
        return claseProfe_idClaseProfeMB;
    }

    /**
     * @param claseProfe_idClaseProfeMB the claseProfe_idClaseProfeMB to set
     */
    public void setClaseProfe_idClaseProfeMB(int claseProfe_idClaseProfeMB) {
        this.claseProfe_idClaseProfeMB = claseProfe_idClaseProfeMB;
    }

    /**
     * @return the idSubtemaProfeMB
     */
    public int getIdSubtemaProfeMB() {
        return idSubtemaProfeMB;
    }

    /**
     * @param idSubtemaProfeMB the idSubtemaProfeMB to set
     */
    public void setIdSubtemaProfeMB(int idSubtemaProfeMB) {
        this.idSubtemaProfeMB = idSubtemaProfeMB;
    }

    /**
     * @return the nombreSubtemaMB
     */
    public String getNombreSubtemaMB() {
        return nombreSubtemaMB;
    }

    /**
     * @param nombreSubtemaMB the nombreSubtemaMB to set
     */
    public void setNombreSubtemaMB(String nombreSubtemaMB) {
        this.nombreSubtemaMB = nombreSubtemaMB;
    }

    /**
     * @return the temaProfe_idTemaProfeMB
     */
    public int getTemaProfe_idTemaProfeMB() {
        return temaProfe_idTemaProfeMB;
    }

    /**
     * @param temaProfe_idTemaProfeMB the temaProfe_idTemaProfeMB to set
     */
    public void setTemaProfe_idTemaProfeMB(int temaProfe_idTemaProfeMB) {
        this.temaProfe_idTemaProfeMB = temaProfe_idTemaProfeMB;
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
     * @return the idCasoPruebaMB
     */
    public int getIdCasoPruebaMB() {
        return idCasoPruebaMB;
    }

    /**
     * @param idCasoPruebaMB the idCasoPruebaMB to set
     */
    public void setIdCasoPruebaMB(int idCasoPruebaMB) {
        this.idCasoPruebaMB = idCasoPruebaMB;
    }

    /**
     * @return the ejercicioPro_idEjercicioProMB
     */
    public int getEjercicioPro_idEjercicioProMB() {
        return ejercicioPro_idEjercicioProMB;
    }

    /**
     * @param ejercicioPro_idEjercicioProMB the ejercicioPro_idEjercicioProMB to
     * set
     */
    public void setEjercicioPro_idEjercicioProMB(int ejercicioPro_idEjercicioProMB) {
        this.ejercicioPro_idEjercicioProMB = ejercicioPro_idEjercicioProMB;
    }

    /**
     * @return the pruebaMB
     */
    public String getPruebaMB() {
        return pruebaMB;
    }

    /**
     * @param pruebaMB the pruebaMB to set
     */
    public void setPruebaMB(String pruebaMB) {
        this.pruebaMB = pruebaMB;
    }

    /**
     * @return the resultadoMB
     */
    public String getResultadoMB() {
        return resultadoMB;
    }

    /**
     * @param resultadoMB the resultadoMB to set
     */
    public void setResultadoMB(String resultadoMB) {
        this.resultadoMB = resultadoMB;
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
     * @return the idEjercicioAlumnoMB
     */
    public int getIdEjercicioAlumnoMB() {
        return idEjercicioAlumnoMB;
    }

    /**
     * @param idEjercicioAlumnoMB the idEjercicioAlumnoMB to set
     */
    public void setIdEjercicioAlumnoMB(int idEjercicioAlumnoMB) {
        this.idEjercicioAlumnoMB = idEjercicioAlumnoMB;
    }

    /**
     * @return the ejePro_idEjeProEjAlumnoMB
     */
    public int getEjePro_idEjeProEjAlumnoMB() {
        return ejePro_idEjeProEjAlumnoMB;
    }

    /**
     * @param ejePro_idEjeProEjAlumnoMB the ejePro_idEjeProEjAlumnoMB to set
     */
    public void setEjePro_idEjeProEjAlumnoMB(int ejePro_idEjeProEjAlumnoMB) {
        this.ejePro_idEjeProEjAlumnoMB = ejePro_idEjeProEjAlumnoMB;
    }

    /**
     * @return the codigoFuenteAlumnoMB
     */
    public String getCodigoFuenteAlumnoMB() {
        return codigoFuenteAlumnoMB;
    }

    /**
     * @param codigoFuenteAlumnoMB the codigoFuenteAlumnoMB to set
     */
    public void setCodigoFuenteAlumnoMB(String codigoFuenteAlumnoMB) {
        this.codigoFuenteAlumnoMB = codigoFuenteAlumnoMB;
    }

    /**
     * @return the casoPrueba_idEjeAluMB
     */
    public int getCasoPrueba_idEjeAluMB() {
        return casoPrueba_idEjeAluMB;
    }

    /**
     * @param casoPrueba_idEjeAluMB the casoPrueba_idEjeAluMB to set
     */
    public void setCasoPrueba_idEjeAluMB(int casoPrueba_idEjeAluMB) {
        this.casoPrueba_idEjeAluMB = casoPrueba_idEjeAluMB;
    }

    /**
     * @return the statusMB
     */
    public int getStatusMB() {
        return statusMB;
    }

    /**
     * @param statusMB the statusMB to set
     */
    public void setStatusMB(int statusMB) {
        this.statusMB = statusMB;
    }

    /**
     * @return the opcs7
     */
    public List<SelectItem> getOpcs7() {
        return opcs7;
    }

    /**
     * @param opcs7 the opcs7 to set
     */
    public void setOpcs7(List<SelectItem> opcs7) {
        this.opcs7 = opcs7;
    }

    /**
     * @return the opcionActual_11
     */
    public String getOpcionActual_11() {
        return opcionActual_11;
    }

    /**
     * @param opcionActual_11 the opcionActual_11 to set
     */
    public void setOpcionActual_11(String opcionActual_11) {
        this.opcionActual_11 = opcionActual_11;
    }

    /**
     * @return the opcionActual_123
     */
    public int getOpcionActual_123() {
        return opcionActual_123;
    }

    /**
     * @param opcionActual_123 the opcionActual_123 to set
     */
    public void setOpcionActual_123(int opcionActual_123) {
        this.opcionActual_123 = opcionActual_123;
    }

    /**
     * @return the password2MB
     */
    public String getPassword2MB() {
        return password2MB;
    }

    /**
     * @param password2MB the password2MB to set
     */
    public void setPassword2MB(String password2MB) {
        this.password2MB = password2MB;
    }

    /**
     * @return the opcs8
     */
    public List<SelectItem> getOpcs8() {
        return opcs8;
    }

    /**
     * @param opcs8 the opcs8 to set
     */
    public void setOpcs8(List<SelectItem> opcs8) {
        this.opcs8 = opcs8;
    }
}
