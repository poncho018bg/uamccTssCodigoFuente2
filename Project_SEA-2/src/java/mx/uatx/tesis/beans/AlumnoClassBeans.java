/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.beans;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoClassBeans {

    // tabla claseAlumno;
    private int idClaseAlumno;
    private int usuario_idUsuClaseAlu;
    private int clasePro_idClaseProClaseAlum;
    // tabla Usuarios
    private int idUsuario;
    private String nombreUsu;    
    private int rol_idRol;
    // tabla  ClaseProfesor
    private int idClaseProfesor;
    private int usuario_idUsuario;
    private String nombreClass;
    // tabla  ClaseProfesorTAB
    private int idClaseAlumnoTAB;
    private int usuario_idUsuarioTAB;
    private int ClaseProfesor_idClaseProfesorTAB;
    private int idClaseProfesorTAB;    
    private String nombreClassTAB;
    
    private int totalClases;
    
    private int idclaseUp;
    private int idusuarioUp;
    private int idClaseProfUp;

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
     * @param clasePro_idClaseProClaseAlum the clasePro_idClaseProClaseAlum to
     * set
     */
    public void setClasePro_idClaseProClaseAlum(int clasePro_idClaseProClaseAlum) {
        this.clasePro_idClaseProClaseAlum = clasePro_idClaseProClaseAlum;
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
     * @return the idClaseProfesorTAB
     */
    public int getIdClaseProfesorTAB() {
        return idClaseProfesorTAB;
    }

    /**
     * @param idClaseProfesorTAB the idClaseProfesorTAB to set
     */
    public void setIdClaseProfesorTAB(int idClaseProfesorTAB) {
        this.idClaseProfesorTAB = idClaseProfesorTAB;
    }

    /**
     * @return the usuario_idUsuarioTAB
     */
    public int getUsuario_idUsuarioTAB() {
        return usuario_idUsuarioTAB;
    }

    /**
     * @param usuario_idUsuarioTAB the usuario_idUsuarioTAB to set
     */
    public void setUsuario_idUsuarioTAB(int usuario_idUsuarioTAB) {
        this.usuario_idUsuarioTAB = usuario_idUsuarioTAB;
    }

    /**
     * @return the nombreClassTAB
     */
    public String getNombreClassTAB() {
        return nombreClassTAB;
    }

    /**
     * @param nombreClassTAB the nombreClassTAB to set
     */
    public void setNombreClassTAB(String nombreClassTAB) {
        this.nombreClassTAB = nombreClassTAB;
    }

    /**
     * @return the idClaseAlumnoTAB
     */
    public int getIdClaseAlumnoTAB() {
        return idClaseAlumnoTAB;
    }

    /**
     * @param idClaseAlumnoTAB the idClaseAlumnoTAB to set
     */
    public void setIdClaseAlumnoTAB(int idClaseAlumnoTAB) {
        this.idClaseAlumnoTAB = idClaseAlumnoTAB;
    }

    /**
     * @return the ClaseProfesor_idClaseProfesorTAB
     */
    public int getClaseProfesor_idClaseProfesorTAB() {
        return ClaseProfesor_idClaseProfesorTAB;
    }

    /**
     * @param ClaseProfesor_idClaseProfesorTAB the ClaseProfesor_idClaseProfesorTAB to set
     */
    public void setClaseProfesor_idClaseProfesorTAB(int ClaseProfesor_idClaseProfesorTAB) {
        this.ClaseProfesor_idClaseProfesorTAB = ClaseProfesor_idClaseProfesorTAB;
    }

    /**
     * @return the totalClases
     */
    public int getTotalClases() {
        return totalClases;
    }

    /**
     * @param totalClases the totalClases to set
     */
    public void setTotalClases(int totalClases) {
        this.totalClases = totalClases;
    }

    /**
     * @return the idclaseUp
     */
    public int getIdclaseUp() {
        return idclaseUp;
    }

    /**
     * @param idclaseUp the idclaseUp to set
     */
    public void setIdclaseUp(int idclaseUp) {
        this.idclaseUp = idclaseUp;
    }

    /**
     * @return the idusuarioUp
     */
    public int getIdusuarioUp() {
        return idusuarioUp;
    }

    /**
     * @param idusuarioUp the idusuarioUp to set
     */
    public void setIdusuarioUp(int idusuarioUp) {
        this.idusuarioUp = idusuarioUp;
    }

    /**
     * @return the idClaseProfUp
     */
    public int getIdClaseProfUp() {
        return idClaseProfUp;
    }

    /**
     * @param idClaseProfUp the idClaseProfUp to set
     */
    public void setIdClaseProfUp(int idClaseProfUp) {
        this.idClaseProfUp = idClaseProfUp;
    }

}
