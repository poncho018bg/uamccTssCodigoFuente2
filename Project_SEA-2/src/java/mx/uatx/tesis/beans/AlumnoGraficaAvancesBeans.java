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
public class AlumnoGraficaAvancesBeans {

    // TABLA EJERCICIO ALUMNO 
    private int g1_idejercicioAlumno;
    private int g1_ejercicioProfesor_idejercicioProfesor;
    private int g1_usuarios_idusuarios;
    private int g1_claseAlumno_idclaseAlumno;
    private String g1_fechaInicio_a;
    private String g1_fechaFinal_a;
    private int g1_tiempoTotal_a;
    private int g1_intentosTotal_a;
    //TABLA EJERCICIO PROFESOR
    private int g2_idejercicioProfesor;
    private String g2_nombre;
    private int g2_tiempoTotal;
    private int g2_intentosTotal;
    // variables de PDF
    private String nombreUsuario;
    private String apellidoPaterno;
    private String nombreEjercicio;
    private String minutos;
    private String num_Intentos;
    private String fechaInicio;
    private String fechaFin;
    
     // variables de PDF  
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
    
    //variables para el subtema
    private int idSubtema;
    private String nombreSubtemas;
    
     // variable para ejercicio
    private int idEjercicio;
    private String ejercicioNom;
    
    // variables para los parametros del PDF
    private String totalEjercicio;
    private String totalPass;
    private String totalFail;
    private String nombreProfesor;
    private String paternoProfesor;
    private String maternoProfesor;
    
    /**
     * @return the g1_idejercicioAlumno
     */
    public int getG1_idejercicioAlumno() {
        return g1_idejercicioAlumno;
    }

    /**
     * @param g1_idejercicioAlumno the g1_idejercicioAlumno to set
     */
    public void setG1_idejercicioAlumno(int g1_idejercicioAlumno) {
        this.g1_idejercicioAlumno = g1_idejercicioAlumno;
    }

    /**
     * @return the g1_ejercicioProfesor_idejercicioProfesor
     */
    public int getG1_ejercicioProfesor_idejercicioProfesor() {
        return g1_ejercicioProfesor_idejercicioProfesor;
    }

    /**
     * @param g1_ejercicioProfesor_idejercicioProfesor the
     * g1_ejercicioProfesor_idejercicioProfesor to set
     */
    public void setG1_ejercicioProfesor_idejercicioProfesor(int g1_ejercicioProfesor_idejercicioProfesor) {
        this.g1_ejercicioProfesor_idejercicioProfesor = g1_ejercicioProfesor_idejercicioProfesor;
    }

    /**
     * @return the g1_usuarios_idusuarios
     */
    public int getG1_usuarios_idusuarios() {
        return g1_usuarios_idusuarios;
    }

    /**
     * @param g1_usuarios_idusuarios the g1_usuarios_idusuarios to set
     */
    public void setG1_usuarios_idusuarios(int g1_usuarios_idusuarios) {
        this.g1_usuarios_idusuarios = g1_usuarios_idusuarios;
    }

    /**
     * @return the g1_claseAlumno_idclaseAlumno
     */
    public int getG1_claseAlumno_idclaseAlumno() {
        return g1_claseAlumno_idclaseAlumno;
    }

    /**
     * @param g1_claseAlumno_idclaseAlumno the g1_claseAlumno_idclaseAlumno to
     * set
     */
    public void setG1_claseAlumno_idclaseAlumno(int g1_claseAlumno_idclaseAlumno) {
        this.g1_claseAlumno_idclaseAlumno = g1_claseAlumno_idclaseAlumno;
    }

    /**
     * @return the g1_fechaInicio_a
     */
    public String getG1_fechaInicio_a() {
        return g1_fechaInicio_a;
    }

    /**
     * @param g1_fechaInicio_a the g1_fechaInicio_a to set
     */
    public void setG1_fechaInicio_a(String g1_fechaInicio_a) {
        this.g1_fechaInicio_a = g1_fechaInicio_a;
    }

    /**
     * @return the g1_fechaFinal_a
     */
    public String getG1_fechaFinal_a() {
        return g1_fechaFinal_a;
    }

    /**
     * @param g1_fechaFinal_a the g1_fechaFinal_a to set
     */
    public void setG1_fechaFinal_a(String g1_fechaFinal_a) {
        this.g1_fechaFinal_a = g1_fechaFinal_a;
    }

    /**
     * @return the g1_tiempoTotal_a
     */
    public int getG1_tiempoTotal_a() {
        return g1_tiempoTotal_a;
    }

    /**
     * @param g1_tiempoTotal_a the g1_tiempoTotal_a to set
     */
    public void setG1_tiempoTotal_a(int g1_tiempoTotal_a) {
        this.g1_tiempoTotal_a = g1_tiempoTotal_a;
    }

    /**
     * @return the g1_intentosTotal_a
     */
    public int getG1_intentosTotal_a() {
        return g1_intentosTotal_a;
    }

    /**
     * @param g1_intentosTotal_a the g1_intentosTotal_a to set
     */
    public void setG1_intentosTotal_a(int g1_intentosTotal_a) {
        this.g1_intentosTotal_a = g1_intentosTotal_a;
    }

    /**
     * @return the g2_idejercicioProfesor
     */
    public int getG2_idejercicioProfesor() {
        return g2_idejercicioProfesor;
    }

    /**
     * @param g2_idejercicioProfesor the g2_idejercicioProfesor to set
     */
    public void setG2_idejercicioProfesor(int g2_idejercicioProfesor) {
        this.g2_idejercicioProfesor = g2_idejercicioProfesor;
    }

    /**
     * @return the g2_nombre
     */
    public String getG2_nombre() {
        return g2_nombre;
    }

    /**
     * @param g2_nombre the g2_nombre to set
     */
    public void setG2_nombre(String g2_nombre) {
        this.g2_nombre = g2_nombre;
    }

    /**
     * @return the g2_tiempoTotal
     */
    public int getG2_tiempoTotal() {
        return g2_tiempoTotal;
    }

    /**
     * @param g2_tiempoTotal the g2_tiempoTotal to set
     */
    public void setG2_tiempoTotal(int g2_tiempoTotal) {
        this.g2_tiempoTotal = g2_tiempoTotal;
    }

    /**
     * @return the g2_intentosTotal
     */
    public int getG2_intentosTotal() {
        return g2_intentosTotal;
    }

    /**
     * @param g2_intentosTotal the g2_intentosTotal to set
     */
    public void setG2_intentosTotal(int g2_intentosTotal) {
        this.g2_intentosTotal = g2_intentosTotal;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
     * @return the totalEjercicio
     */
    public String getTotalEjercicio() {
        return totalEjercicio;
    }

    /**
     * @param totalEjercicio the totalEjercicio to set
     */
    public void setTotalEjercicio(String totalEjercicio) {
        this.totalEjercicio = totalEjercicio;
    }

    /**
     * @return the totalPass
     */
    public String getTotalPass() {
        return totalPass;
    }

    /**
     * @param totalPass the totalPass to set
     */
    public void setTotalPass(String totalPass) {
        this.totalPass = totalPass;
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
     * @return the paternoProfesor
     */
    public String getPaternoProfesor() {
        return paternoProfesor;
    }

    /**
     * @param paternoProfesor the paternoProfesor to set
     */
    public void setPaternoProfesor(String paternoProfesor) {
        this.paternoProfesor = paternoProfesor;
    }

    /**
     * @return the maternoProfesor
     */
    public String getMaternoProfesor() {
        return maternoProfesor;
    }

    /**
     * @param maternoProfesor the maternoProfesor to set
     */
    public void setMaternoProfesor(String maternoProfesor) {
        this.maternoProfesor = maternoProfesor;
    }

}
