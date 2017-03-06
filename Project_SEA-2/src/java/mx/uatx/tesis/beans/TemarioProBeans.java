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
public class TemarioProBeans {

    // tabla Temaprofesor
    private int idTemaProfe;
    private String nombreTema;
    private int claseProfe_idClaseProfe;

    // tabla subtemaProfesor
    private int idSubtemaProfe;
    private String nombreSubtema;
    private String nombreSub;
    private int temaProfe_idTemaProfe;
    // tabla clase alumno
    private int idClaseAlumno_ca;
    private int usuarios_idusuarios_ca;
    private int claseProfesor_idClaseProfesor_ca;
    private int idUsuario_ca;
    private String nombres_ca;
    private String paterno_ca;
    private String materno_ca;
/**
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

**/
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
     * @return the nombreSub
     */
    public String getNombreSub() {
        return nombreSub;
    }

    /**
     * @param nombreSub the nombreSub to set
     */
    public void setNombreSub(String nombreSub) {
        this.nombreSub = nombreSub;
    }

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
     * @param claseProfesor_idClaseProfesor_ca the
     * claseProfesor_idClaseProfesor_ca to set
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

}
