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
public class EjerciciosProListBeans {

    // tabla ejercicioProfesor
    private int idEjercicioProfe;
    private int subtemaProfe_idSubtemaProfe;
    private String nombre;
    private String descripcionSub;
    private String sugerenciaSub;
    private String codigoFuenteSub;
    private String tiempitotal;
    private int intentosTotal;
    
    //cosos a Eliminar
    private int idCasoPruebaEliminar;
    private int idParametrosEliminar;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tiempitotal
     */
    public String getTiempitotal() {
        return tiempitotal;
    }

    /**
     * @param tiempitotal the tiempitotal to set
     */
    public void setTiempitotal(String tiempitotal) {
        this.tiempitotal = tiempitotal;
    }

    /**
     * @return the intentosTotal
     */
    public int getIntentosTotal() {
        return intentosTotal;
    }

    /**
     * @param intentosTotal the intentosTotal to set
     */
    public void setIntentosTotal(int intentosTotal) {
        this.intentosTotal = intentosTotal;
    }

    /**
     * @return the idCasoPruebaEliminar
     */
    public int getIdCasoPruebaEliminar() {
        return idCasoPruebaEliminar;
    }

    /**
     * @param idCasoPruebaEliminar the idCasoPruebaEliminar to set
     */
    public void setIdCasoPruebaEliminar(int idCasoPruebaEliminar) {
        this.idCasoPruebaEliminar = idCasoPruebaEliminar;
    }

    /**
     * @return the idParametrosEliminar
     */
    public int getIdParametrosEliminar() {
        return idParametrosEliminar;
    }

    /**
     * @param idParametrosEliminar the idParametrosEliminar to set
     */
    public void setIdParametrosEliminar(int idParametrosEliminar) {
        this.idParametrosEliminar = idParametrosEliminar;
    }

}
