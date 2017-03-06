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
public class IndexBeans {

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

}
