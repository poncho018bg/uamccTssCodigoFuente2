/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.uatx.tesis.beans;

/**
 *
 * @author poikjasd
 */
public class TesisBEANS {
    // tabla roles
    private int idRoles;
    private String descripcionRol;
    // tabla Usuarios
    private int idUsuario;
    private String nombreUsu;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String password;
    private int rol_idRol;
    // tabla  ClaseProfesor
    private int idClaseProfesor;
    private int usuario_idUsuario;
    private String nombreClass;
    // tabla Temaprofesor
    private int idTemaProfe;
    private String nombreTema;
    private int claseProfe_idClaseProfe;
    // tabla subtemaProfesor
    private int idSubtemaProfe;
    private String nombreSubtema;
    private int temaProfe_idTemaProfe;
    // tabla ejercicioProfesor
    private int idEjercicioProfe;
    private int subtemaProfe_idSubtemaProfe;
    private String descripcionSub;
    private String sugerenciaSub;
    private String codigoFuenteSub;
    // tabla casoPrueba
    private int idCasoPrueba;
    private int ejercicioPro_idEjercicioPro;
    private String prueba;
    private String resultado;
    // tabla claseAlumno;
    private int idClaseAlumno;
    private int usuario_idUsuClaseAlu;
    private int clasePro_idClaseProClaseAlum;
    // tabla ejercicioAlumno
    private int idEjercicioAlumno;
    private int ejePro_idEjeProEjAlumno;
    private String codigoFuenteAlumno;
    private int casoPrueba_idEjeAlu;
    private int status;
    
    private int correoExist;
    /**
     * @return the idRoles
     */
    public int getIdRoles() {
        return idRoles;
    }

    /**
     * @param idRoles the idRoles to set
     */
    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    /**
     * @return the descripcionRol
     */
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * @param descripcionRol the descripcionRol to set
     */
    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsu
     */
    public String getNombreUsu() {
        return nombreUsu;
    }

    /**
     * @param nombreUsu the nombreUsu to set
     */
    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol_idRol
     */
    public int getRol_idRol() {
        return rol_idRol;
    }

    /**
     * @param rol_idRol the rol_idRol to set
     */
    public void setRol_idRol(int rol_idRol) {
        this.rol_idRol = rol_idRol;
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
     * @return the idTemaProfe
     */
    public int getIdTemaProfe() {
        return idTemaProfe;
    }

    /**
     * @param idTemaProfe the idTemaProfe to set
     */
    public void setIdTemaProfe(int idTemaProfe) {
        this.idTemaProfe = idTemaProfe;
    }

    /**
     * @return the nombreTema
     */
    public String getNombreTema() {
        return nombreTema;
    }

    /**
     * @param nombreTema the nombreTema to set
     */
    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    /**
     * @return the claseProfe_idClaseProfe
     */
    public int getClaseProfe_idClaseProfe() {
        return claseProfe_idClaseProfe;
    }

    /**
     * @param claseProfe_idClaseProfe the claseProfe_idClaseProfe to set
     */
    public void setClaseProfe_idClaseProfe(int claseProfe_idClaseProfe) {
        this.claseProfe_idClaseProfe = claseProfe_idClaseProfe;
    }

    /**
     * @return the idSubtemaProfe
     */
    public int getIdSubtemaProfe() {
        return idSubtemaProfe;
    }

    /**
     * @param idSubtemaProfe the idSubtemaProfe to set
     */
    public void setIdSubtemaProfe(int idSubtemaProfe) {
        this.idSubtemaProfe = idSubtemaProfe;
    }

    /**
     * @return the nombreSubtema
     */
    public String getNombreSubtema() {
        return nombreSubtema;
    }

    /**
     * @param nombreSubtema the nombreSubtema to set
     */
    public void setNombreSubtema(String nombreSubtema) {
        this.nombreSubtema = nombreSubtema;
    }

    /**
     * @return the temaProfe_idTemaProfe
     */
    public int getTemaProfe_idTemaProfe() {
        return temaProfe_idTemaProfe;
    }

    /**
     * @param temaProfe_idTemaProfe the temaProfe_idTemaProfe to set
     */
    public void setTemaProfe_idTemaProfe(int temaProfe_idTemaProfe) {
        this.temaProfe_idTemaProfe = temaProfe_idTemaProfe;
    }

    /**
     * @return the idEjercicioProfe
     */
    public int getIdEjercicioProfe() {
        return idEjercicioProfe;
    }

    /**
     * @param idEjercicioProfe the idEjercicioProfe to set
     */
    public void setIdEjercicioProfe(int idEjercicioProfe) {
        this.idEjercicioProfe = idEjercicioProfe;
    }

    /**
     * @return the subtemaProfe_idSubtemaProfe
     */
    public int getSubtemaProfe_idSubtemaProfe() {
        return subtemaProfe_idSubtemaProfe;
    }

    /**
     * @param subtemaProfe_idSubtemaProfe the subtemaProfe_idSubtemaProfe to set
     */
    public void setSubtemaProfe_idSubtemaProfe(int subtemaProfe_idSubtemaProfe) {
        this.subtemaProfe_idSubtemaProfe = subtemaProfe_idSubtemaProfe;
    }

    /**
     * @return the descripcionSub
     */
    public String getDescripcionSub() {
        return descripcionSub;
    }

    /**
     * @param descripcionSub the descripcionSub to set
     */
    public void setDescripcionSub(String descripcionSub) {
        this.descripcionSub = descripcionSub;
    }

    /**
     * @return the sugerenciaSub
     */
    public String getSugerenciaSub() {
        return sugerenciaSub;
    }

    /**
     * @param sugerenciaSub the sugerenciaSub to set
     */
    public void setSugerenciaSub(String sugerenciaSub) {
        this.sugerenciaSub = sugerenciaSub;
    }

    /**
     * @return the codigoFuenteSub
     */
    public String getCodigoFuenteSub() {
        return codigoFuenteSub;
    }

    /**
     * @param codigoFuenteSub the codigoFuenteSub to set
     */
    public void setCodigoFuenteSub(String codigoFuenteSub) {
        this.codigoFuenteSub = codigoFuenteSub;
    }

    /**
     * @return the idCasoPrueba
     */
    public int getIdCasoPrueba() {
        return idCasoPrueba;
    }

    /**
     * @param idCasoPrueba the idCasoPrueba to set
     */
    public void setIdCasoPrueba(int idCasoPrueba) {
        this.idCasoPrueba = idCasoPrueba;
    }

    /**
     * @return the ejercicioPro_idEjercicioPro
     */
    public int getEjercicioPro_idEjercicioPro() {
        return ejercicioPro_idEjercicioPro;
    }

    /**
     * @param ejercicioPro_idEjercicioPro the ejercicioPro_idEjercicioPro to set
     */
    public void setEjercicioPro_idEjercicioPro(int ejercicioPro_idEjercicioPro) {
        this.ejercicioPro_idEjercicioPro = ejercicioPro_idEjercicioPro;
    }

    /**
     * @return the prueba
     */
    public String getPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the idClaseAlumno
     */
    public int getIdClaseAlumno() {
        return idClaseAlumno;
    }

    /**
     * @param idClaseAlumno the idClaseAlumno to set
     */
    public void setIdClaseAlumno(int idClaseAlumno) {
        this.idClaseAlumno = idClaseAlumno;
    }

    /**
     * @return the usuario_idUsuClaseAlu
     */
    public int getUsuario_idUsuClaseAlu() {
        return usuario_idUsuClaseAlu;
    }

    /**
     * @param usuario_idUsuClaseAlu the usuario_idUsuClaseAlu to set
     */
    public void setUsuario_idUsuClaseAlu(int usuario_idUsuClaseAlu) {
        this.usuario_idUsuClaseAlu = usuario_idUsuClaseAlu;
    }

    /**
     * @return the clasePro_idClaseProClaseAlum
     */
    public int getClasePro_idClaseProClaseAlum() {
        return clasePro_idClaseProClaseAlum;
    }

    /**
     * @param clasePro_idClaseProClaseAlum the clasePro_idClaseProClaseAlum to set
     */
    public void setClasePro_idClaseProClaseAlum(int clasePro_idClaseProClaseAlum) {
        this.clasePro_idClaseProClaseAlum = clasePro_idClaseProClaseAlum;
    }

    /**
     * @return the idEjercicioAlumno
     */
    public int getIdEjercicioAlumno() {
        return idEjercicioAlumno;
    }

    /**
     * @param idEjercicioAlumno the idEjercicioAlumno to set
     */
    public void setIdEjercicioAlumno(int idEjercicioAlumno) {
        this.idEjercicioAlumno = idEjercicioAlumno;
    }

    /**
     * @return the ejePro_idEjeProEjAlumno
     */
    public int getEjePro_idEjeProEjAlumno() {
        return ejePro_idEjeProEjAlumno;
    }

    /**
     * @param ejePro_idEjeProEjAlumno the ejePro_idEjeProEjAlumno to set
     */
    public void setEjePro_idEjeProEjAlumno(int ejePro_idEjeProEjAlumno) {
        this.ejePro_idEjeProEjAlumno = ejePro_idEjeProEjAlumno;
    }

    /**
     * @return the codigoFuenteAlumno
     */
    public String getCodigoFuenteAlumno() {
        return codigoFuenteAlumno;
    }

    /**
     * @param codigoFuenteAlumno the codigoFuenteAlumno to set
     */
    public void setCodigoFuenteAlumno(String codigoFuenteAlumno) {
        this.codigoFuenteAlumno = codigoFuenteAlumno;
    }

    /**
     * @return the casoPrueba_idEjeAlu
     */
    public int getCasoPrueba_idEjeAlu() {
        return casoPrueba_idEjeAlu;
    }

    /**
     * @param casoPrueba_idEjeAlu the casoPrueba_idEjeAlu to set
     */
    public void setCasoPrueba_idEjeAlu(int casoPrueba_idEjeAlu) {
        this.casoPrueba_idEjeAlu = casoPrueba_idEjeAlu;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the correoExist
     */
    public int getCorreoExist() {
        return correoExist;
    }

    /**
     * @param correoExist the correoExist to set
     */
    public void setCorreoExist(int correoExist) {
        this.correoExist = correoExist;
    }
}
