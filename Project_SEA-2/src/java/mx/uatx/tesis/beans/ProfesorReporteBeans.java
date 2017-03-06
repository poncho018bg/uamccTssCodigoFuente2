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
public class ProfesorReporteBeans {

    // variables de PDF
    private String nombreEjercicio;
    private String minutos;
    private String num_Intentos;
    private String fechaInicio;
    private String fechaFin;
    private String nombreAlumno;
    private String paternoAlumno;
    private String estado;
    private String clase;
    private String tema;
    private String subtema;       
    private String minutosp;
    private String num_Intentosp;   

    // variables para el tema
    private int idTema;
    private String nombreTema;

    // variables para el subtema
    private int idSubtema;
    private String nombreSubtemas;

    // variable para ejercicio
    private int idEjercicio;
    private String ejercicioNom;
    
    //variables para alumno
    private int idAlumno;
    private String nomAlumno;
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    //Variable
    private String nombreProfesor;
    
    private String totalEjercicios;
    private String totalEjerciciosPass;

     
    /**
     * @return the nombreEjercicio
     */
    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    /**
     * @param nombreEjercicio the nombreEjercicio to set
     */
    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    /**
     * @return the minutos
     */
    public String getMinutos() {
        return minutos;
    }

    /**
     * @param minutos the minutos to set
     */
    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    /**
     * @return the num_Intentos
     */
    public String getNum_Intentos() {
        return num_Intentos;
    }

    /**
     * @param num_Intentos the num_Intentos to set
     */
    public void setNum_Intentos(String num_Intentos) {
        this.num_Intentos = num_Intentos;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the paternoAlumno
     */
    public String getPaternoAlumno() {
        return paternoAlumno;
    }

    /**
     * @param paternoAlumno the paternoAlumno to set
     */
    public void setPaternoAlumno(String paternoAlumno) {
        this.paternoAlumno = paternoAlumno;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the subtema
     */
    public String getSubtema() {
        return subtema;
    }

    /**
     * @param subtema the subtema to set
     */
    public void setSubtema(String subtema) {
        this.subtema = subtema;
    }

    /**
     * @return the nombreAlumno
     */
    public String getNombreAlumno() {
        return nombreAlumno;
    }

    /**
     * @param nombreAlumno the nombreAlumno to set
     */
    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    /**
     * @return the idTema
     */
    public int getIdTema() {
        return idTema;
    }

    /**
     * @param idTema the idTema to set
     */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
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
     * @return the idSubtema
     */
    public int getIdSubtema() {
        return idSubtema;
    }

    /**
     * @param idSubtema the idSubtema to set
     */
    public void setIdSubtema(int idSubtema) {
        this.idSubtema = idSubtema;
    }

    /**
     * @return the nombreSubtemas
     */
    public String getNombreSubtemas() {
        return nombreSubtemas;
    }

    /**
     * @param nombreSubtemas the nombreSubtemas to set
     */
    public void setNombreSubtemas(String nombreSubtemas) {
        this.nombreSubtemas = nombreSubtemas;
    }

    /**
     * @return the idEjercicio
     */
    public int getIdEjercicio() {
        return idEjercicio;
    }

    /**
     * @param idEjercicio the idEjercicio to set
     */
    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    /**
     * @return the ejercicioNom
     */
    public String getEjercicioNom() {
        return ejercicioNom;
    }

    /**
     * @param ejercicioNom the ejercicioNom to set
     */
    public void setEjercicioNom(String ejercicioNom) {
        this.ejercicioNom = ejercicioNom;
    }

    /**
     * @return the idAlumno
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * @param idAlumno the idAlumno to set
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    /**
     * @return the nomAlumno
     */
    public String getNomAlumno() {
        return nomAlumno;
    }

    /**
     * @param nomAlumno the nomAlumno to set
     */
    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
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
     * @return the nombreProfesor
     */
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    /**
     * @param nombreProfesor the nombreProfesor to set
     */
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    /**
     * @return the minutosp
     */
    public String getMinutosp() {
        return minutosp;
    }

    /**
     * @param minutosp the minutosp to set
     */
    public void setMinutosp(String minutosp) {
        this.minutosp = minutosp;
    }

    /**
     * @return the num_Intentosp
     */
    public String getNum_Intentosp() {
        return num_Intentosp;
    }

    /**
     * @param num_Intentosp the num_Intentosp to set
     */
    public void setNum_Intentosp(String num_Intentosp) {
        this.num_Intentosp = num_Intentosp;
    }

    /**
     * @return the totalEjercicios
     */
    public String getTotalEjercicios() {
        return totalEjercicios;
    }

    /**
     * @param totalEjercicios the totalEjercicios to set
     */
    public void setTotalEjercicios(String totalEjercicios) {
        this.totalEjercicios = totalEjercicios;
    }

    /**
     * @return the totalEjerciciosPass
     */
    public String getTotalEjerciciosPass() {
        return totalEjerciciosPass;
    }

    /**
     * @param totalEjerciciosPass the totalEjerciciosPass to set
     */
    public void setTotalEjerciciosPass(String totalEjerciciosPass) {
        this.totalEjerciciosPass = totalEjerciciosPass;
    }

}
