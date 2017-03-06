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
public class GraficaProfesorBeans {
    
    // tabla clase alumno
    private int idClaseAlumno_ca;
    private int usuarios_idusuarios_ca;
    private int claseProfesor_idClaseProfesor_ca;
    private int idUsuario_ca;
    private String nombres_ca;
    private String paterno_ca;
    private String materno_ca;
    
      // CAMPOS GRAFICAPROFESOR1
    private int ca_idclasealumno;
    private int ca_usuarios_idusuarios;
    private int ca_claseprofesor_idclaseprofesor;

    private int ea_idejercicioAlumno;
    private int ea_ejercicioprofesor_idejercicioprofesor;
    private int ea_usuarios_idUsuarios;
    private String ea_fechainicio_a;
    private String ea_fechafinal_a;
    private String ea_tiempototal_a;
    private int ea_intentostotal_a;
    
    private int ep_idejercicioProfesor;
    private String ep_tiempototal;
    private int ep_intentostotal;

    private int cpa_idcasopruebaalumno;
    private int cpa_ejercicioalumno_idejercicioalumno;
    private int cpa_idcasoprueba;
    private int cpa_usuarios_idusuarios;
    private String cpa_estatus;
     // CONSULTA QUE CUENTA EL TOTAL DE INSCRITOS
    private int ca_usuarios_idusuariosT;
    private int ca_claseprofesor_idclaseprofesorT;
    private int ea_idejercicioAlumnoT;
    private int ea_usuarios_idUsuariosT;
    private int cpa_ejercicioalumno_idejercicioalumnoT;

    /**
     * @return the idClaseAlumno_ca
     */
    public int getIdClaseAlumno_ca() {
        return idClaseAlumno_ca;
    }

    /**
     * @param idClaseAlumno_ca the idClaseAlumno_ca to set
     */
    public void setIdClaseAlumno_ca(int idClaseAlumno_ca) {
        this.idClaseAlumno_ca = idClaseAlumno_ca;
    }

    /**
     * @return the usuarios_idusuarios_ca
     */
    public int getUsuarios_idusuarios_ca() {
        return usuarios_idusuarios_ca;
    }

    /**
     * @param usuarios_idusuarios_ca the usuarios_idusuarios_ca to set
     */
    public void setUsuarios_idusuarios_ca(int usuarios_idusuarios_ca) {
        this.usuarios_idusuarios_ca = usuarios_idusuarios_ca;
    }

    /**
     * @return the claseProfesor_idClaseProfesor_ca
     */
    public int getClaseProfesor_idClaseProfesor_ca() {
        return claseProfesor_idClaseProfesor_ca;
    }

    /**
     * @param claseProfesor_idClaseProfesor_ca the claseProfesor_idClaseProfesor_ca to set
     */
    public void setClaseProfesor_idClaseProfesor_ca(int claseProfesor_idClaseProfesor_ca) {
        this.claseProfesor_idClaseProfesor_ca = claseProfesor_idClaseProfesor_ca;
    }

    /**
     * @return the idUsuario_ca
     */
    public int getIdUsuario_ca() {
        return idUsuario_ca;
    }

    /**
     * @param idUsuario_ca the idUsuario_ca to set
     */
    public void setIdUsuario_ca(int idUsuario_ca) {
        this.idUsuario_ca = idUsuario_ca;
    }

    /**
     * @return the nombres_ca
     */
    public String getNombres_ca() {
        return nombres_ca;
    }

    /**
     * @param nombres_ca the nombres_ca to set
     */
    public void setNombres_ca(String nombres_ca) {
        this.nombres_ca = nombres_ca;
    }

    /**
     * @return the paterno_ca
     */
    public String getPaterno_ca() {
        return paterno_ca;
    }

    /**
     * @param paterno_ca the paterno_ca to set
     */
    public void setPaterno_ca(String paterno_ca) {
        this.paterno_ca = paterno_ca;
    }

    /**
     * @return the materno_ca
     */
    public String getMaterno_ca() {
        return materno_ca;
    }

    /**
     * @param materno_ca the materno_ca to set
     */
    public void setMaterno_ca(String materno_ca) {
        this.materno_ca = materno_ca;
    }

    /**
     * @return the ca_idclasealumno
     */
    public int getCa_idclasealumno() {
        return ca_idclasealumno;
    }

    /**
     * @param ca_idclasealumno the ca_idclasealumno to set
     */
    public void setCa_idclasealumno(int ca_idclasealumno) {
        this.ca_idclasealumno = ca_idclasealumno;
    }

    /**
     * @return the ca_usuarios_idusuarios
     */
    public int getCa_usuarios_idusuarios() {
        return ca_usuarios_idusuarios;
    }

    /**
     * @param ca_usuarios_idusuarios the ca_usuarios_idusuarios to set
     */
    public void setCa_usuarios_idusuarios(int ca_usuarios_idusuarios) {
        this.ca_usuarios_idusuarios = ca_usuarios_idusuarios;
    }

    /**
     * @return the ca_claseprofesor_idclaseprofesor
     */
    public int getCa_claseprofesor_idclaseprofesor() {
        return ca_claseprofesor_idclaseprofesor;
    }

    /**
     * @param ca_claseprofesor_idclaseprofesor the ca_claseprofesor_idclaseprofesor to set
     */
    public void setCa_claseprofesor_idclaseprofesor(int ca_claseprofesor_idclaseprofesor) {
        this.ca_claseprofesor_idclaseprofesor = ca_claseprofesor_idclaseprofesor;
    }

    /**
     * @return the ea_idejercicioAlumno
     */
    public int getEa_idejercicioAlumno() {
        return ea_idejercicioAlumno;
    }

    /**
     * @param ea_idejercicioAlumno the ea_idejercicioAlumno to set
     */
    public void setEa_idejercicioAlumno(int ea_idejercicioAlumno) {
        this.ea_idejercicioAlumno = ea_idejercicioAlumno;
    }

    /**
     * @return the ea_ejercicioprofesor_idejercicioprofesor
     */
    public int getEa_ejercicioprofesor_idejercicioprofesor() {
        return ea_ejercicioprofesor_idejercicioprofesor;
    }

    /**
     * @param ea_ejercicioprofesor_idejercicioprofesor the ea_ejercicioprofesor_idejercicioprofesor to set
     */
    public void setEa_ejercicioprofesor_idejercicioprofesor(int ea_ejercicioprofesor_idejercicioprofesor) {
        this.ea_ejercicioprofesor_idejercicioprofesor = ea_ejercicioprofesor_idejercicioprofesor;
    }

    /**
     * @return the ea_usuarios_idUsuarios
     */
    public int getEa_usuarios_idUsuarios() {
        return ea_usuarios_idUsuarios;
    }

    /**
     * @param ea_usuarios_idUsuarios the ea_usuarios_idUsuarios to set
     */
    public void setEa_usuarios_idUsuarios(int ea_usuarios_idUsuarios) {
        this.ea_usuarios_idUsuarios = ea_usuarios_idUsuarios;
    }

    /**
     * @return the ea_fechainicio_a
     */
    public String getEa_fechainicio_a() {
        return ea_fechainicio_a;
    }

    /**
     * @param ea_fechainicio_a the ea_fechainicio_a to set
     */
    public void setEa_fechainicio_a(String ea_fechainicio_a) {
        this.ea_fechainicio_a = ea_fechainicio_a;
    }

    /**
     * @return the ea_fechafinal_a
     */
    public String getEa_fechafinal_a() {
        return ea_fechafinal_a;
    }

    /**
     * @param ea_fechafinal_a the ea_fechafinal_a to set
     */
    public void setEa_fechafinal_a(String ea_fechafinal_a) {
        this.ea_fechafinal_a = ea_fechafinal_a;
    }

    /**
     * @return the ea_tiempototal_a
     */
    public String getEa_tiempototal_a() {
        return ea_tiempototal_a;
    }

    /**
     * @param ea_tiempototal_a the ea_tiempototal_a to set
     */
    public void setEa_tiempototal_a(String ea_tiempototal_a) {
        this.ea_tiempototal_a = ea_tiempototal_a;
    }

    /**
     * @return the ea_intentostotal_a
     */
    public int getEa_intentostotal_a() {
        return ea_intentostotal_a;
    }

    /**
     * @param ea_intentostotal_a the ea_intentostotal_a to set
     */
    public void setEa_intentostotal_a(int ea_intentostotal_a) {
        this.ea_intentostotal_a = ea_intentostotal_a;
    }

    /**
     * @return the ep_idejercicioProfesor
     */
    public int getEp_idejercicioProfesor() {
        return ep_idejercicioProfesor;
    }

    /**
     * @param ep_idejercicioProfesor the ep_idejercicioProfesor to set
     */
    public void setEp_idejercicioProfesor(int ep_idejercicioProfesor) {
        this.ep_idejercicioProfesor = ep_idejercicioProfesor;
    }

    /**
     * @return the ep_tiempototal
     */
    public String getEp_tiempototal() {
        return ep_tiempototal;
    }

    /**
     * @param ep_tiempototal the ep_tiempototal to set
     */
    public void setEp_tiempototal(String ep_tiempototal) {
        this.ep_tiempototal = ep_tiempototal;
    }

    /**
     * @return the ep_intentostotal
     */
    public int getEp_intentostotal() {
        return ep_intentostotal;
    }

    /**
     * @param ep_intentostotal the ep_intentostotal to set
     */
    public void setEp_intentostotal(int ep_intentostotal) {
        this.ep_intentostotal = ep_intentostotal;
    }

    /**
     * @return the cpa_idcasopruebaalumno
     */
    public int getCpa_idcasopruebaalumno() {
        return cpa_idcasopruebaalumno;
    }

    /**
     * @param cpa_idcasopruebaalumno the cpa_idcasopruebaalumno to set
     */
    public void setCpa_idcasopruebaalumno(int cpa_idcasopruebaalumno) {
        this.cpa_idcasopruebaalumno = cpa_idcasopruebaalumno;
    }

    /**
     * @return the cpa_ejercicioalumno_idejercicioalumno
     */
    public int getCpa_ejercicioalumno_idejercicioalumno() {
        return cpa_ejercicioalumno_idejercicioalumno;
    }

    /**
     * @param cpa_ejercicioalumno_idejercicioalumno the cpa_ejercicioalumno_idejercicioalumno to set
     */
    public void setCpa_ejercicioalumno_idejercicioalumno(int cpa_ejercicioalumno_idejercicioalumno) {
        this.cpa_ejercicioalumno_idejercicioalumno = cpa_ejercicioalumno_idejercicioalumno;
    }

    /**
     * @return the cpa_idcasoprueba
     */
    public int getCpa_idcasoprueba() {
        return cpa_idcasoprueba;
    }

    /**
     * @param cpa_idcasoprueba the cpa_idcasoprueba to set
     */
    public void setCpa_idcasoprueba(int cpa_idcasoprueba) {
        this.cpa_idcasoprueba = cpa_idcasoprueba;
    }

    /**
     * @return the cpa_usuarios_idusuarios
     */
    public int getCpa_usuarios_idusuarios() {
        return cpa_usuarios_idusuarios;
    }

    /**
     * @param cpa_usuarios_idusuarios the cpa_usuarios_idusuarios to set
     */
    public void setCpa_usuarios_idusuarios(int cpa_usuarios_idusuarios) {
        this.cpa_usuarios_idusuarios = cpa_usuarios_idusuarios;
    }

    /**
     * @return the cpa_estatus
     */
    public String getCpa_estatus() {
        return cpa_estatus;
    }

    /**
     * @param cpa_estatus the cpa_estatus to set
     */
    public void setCpa_estatus(String cpa_estatus) {
        this.cpa_estatus = cpa_estatus;
    }

    /**
     * @return the ca_usuarios_idusuariosT
     */
    public int getCa_usuarios_idusuariosT() {
        return ca_usuarios_idusuariosT;
    }

    /**
     * @param ca_usuarios_idusuariosT the ca_usuarios_idusuariosT to set
     */
    public void setCa_usuarios_idusuariosT(int ca_usuarios_idusuariosT) {
        this.ca_usuarios_idusuariosT = ca_usuarios_idusuariosT;
    }

    /**
     * @return the ca_claseprofesor_idclaseprofesorT
     */
    public int getCa_claseprofesor_idclaseprofesorT() {
        return ca_claseprofesor_idclaseprofesorT;
    }

    /**
     * @param ca_claseprofesor_idclaseprofesorT the ca_claseprofesor_idclaseprofesorT to set
     */
    public void setCa_claseprofesor_idclaseprofesorT(int ca_claseprofesor_idclaseprofesorT) {
        this.ca_claseprofesor_idclaseprofesorT = ca_claseprofesor_idclaseprofesorT;
    }

    /**
     * @return the ea_idejercicioAlumnoT
     */
    public int getEa_idejercicioAlumnoT() {
        return ea_idejercicioAlumnoT;
    }

    /**
     * @param ea_idejercicioAlumnoT the ea_idejercicioAlumnoT to set
     */
    public void setEa_idejercicioAlumnoT(int ea_idejercicioAlumnoT) {
        this.ea_idejercicioAlumnoT = ea_idejercicioAlumnoT;
    }

    /**
     * @return the ea_usuarios_idUsuariosT
     */
    public int getEa_usuarios_idUsuariosT() {
        return ea_usuarios_idUsuariosT;
    }

    /**
     * @param ea_usuarios_idUsuariosT the ea_usuarios_idUsuariosT to set
     */
    public void setEa_usuarios_idUsuariosT(int ea_usuarios_idUsuariosT) {
        this.ea_usuarios_idUsuariosT = ea_usuarios_idUsuariosT;
    }

    /**
     * @return the cpa_ejercicioalumno_idejercicioalumnoT
     */
    public int getCpa_ejercicioalumno_idejercicioalumnoT() {
        return cpa_ejercicioalumno_idejercicioalumnoT;
    }

    /**
     * @param cpa_ejercicioalumno_idejercicioalumnoT the cpa_ejercicioalumno_idejercicioalumnoT to set
     */
    public void setCpa_ejercicioalumno_idejercicioalumnoT(int cpa_ejercicioalumno_idejercicioalumnoT) {
        this.cpa_ejercicioalumno_idejercicioalumnoT = cpa_ejercicioalumno_idejercicioalumnoT;
    }
    
}
