/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.beans;

import java.util.Date;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoEjercicioBeans {
// tabla EjerciciasAlumnos

    private int idEjercicioAlumno;
    private int ejePro_idEjeProEjAlumno;
    private int casoPrueba_idEjeAlu;
    private int usuario_idUsuarioAlum;
    private String codigoFuenteAlumno;
    private String status;
    private Date fechaStart;
    private Date fechaEnd;
    //tabla EjerciciosProfesor
    private int idejercicioProfesor_A;
    private int subtemaProfesor_idsubtemaProfesor_A;
    private String nombre_A;

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
     * @return the usuario_idUsuarioAlum
     */
    public int getUsuario_idUsuarioAlum() {
        return usuario_idUsuarioAlum;
    }

    /**
     * @param usuario_idUsuarioAlum the usuario_idUsuarioAlum to set
     */
    public void setUsuario_idUsuarioAlum(int usuario_idUsuarioAlum) {
        this.usuario_idUsuarioAlum = usuario_idUsuarioAlum;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the fechaStart
     */
    public Date getFechaStart() {
        return fechaStart;
    }

    /**
     * @param fechaStart the fechaStart to set
     */
    public void setFechaStart(Date fechaStart) {
        this.fechaStart = fechaStart;
    }

    /**
     * @return the fechaEnd
     */
    public Date getFechaEnd() {
        return fechaEnd;
    }

    /**
     * @param fechaEnd the fechaEnd to set
     */
    public void setFechaEnd(Date fechaEnd) {
        this.fechaEnd = fechaEnd;
    }

    /**
     * @return the idejercicioProfesor_A
     */
    public int getIdejercicioProfesor_A() {
        return idejercicioProfesor_A;
    }

    /**
     * @param idejercicioProfesor_A the idejercicioProfesor_A to set
     */
    public void setIdejercicioProfesor_A(int idejercicioProfesor_A) {
        this.idejercicioProfesor_A = idejercicioProfesor_A;
    }

    /**
     * @return the subtemaProfesor_idsubtemaProfesor_A
     */
    public int getSubtemaProfesor_idsubtemaProfesor_A() {
        return subtemaProfesor_idsubtemaProfesor_A;
    }

    /**
     * @param subtemaProfesor_idsubtemaProfesor_A the
     * subtemaProfesor_idsubtemaProfesor_A to set
     */
    public void setSubtemaProfesor_idsubtemaProfesor_A(int subtemaProfesor_idsubtemaProfesor_A) {
        this.subtemaProfesor_idsubtemaProfesor_A = subtemaProfesor_idsubtemaProfesor_A;
    }

    /**
     * @return the nombre_A
     */
    public String getNombre_A() {
        return nombre_A;
    }

    /**
     * @param nombre_A the nombre_A to set
     */
    public void setNombre_A(String nombre_A) {
        this.nombre_A = nombre_A;
    }

}
