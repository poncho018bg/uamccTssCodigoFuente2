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
public class AlumnoTemarioBeans {
     // tabla Temaprofesor
    private int idTemaProfe;
    private String nombreTema;
    private int claseProfe_idClaseProfe;

    // tabla subtemaProfesor
    private int idSubtemaProfe;
    private String nombreSubtema;
    private String nombreSub;
    private int temaProfe_idTemaProfe;

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
    
}
