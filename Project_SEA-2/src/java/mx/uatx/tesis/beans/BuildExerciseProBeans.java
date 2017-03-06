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
public class BuildExerciseProBeans {
    // tabla ejercicioProfesor
    private int idEjercicioProfe;
    private int subtemaProfe_idSubtemaProfe;
    private String nombreSub;
    private String descripcionSub;
    private String sugerenciaSub;
    private String codigoFuenteSub;
    private String tiempitotal;
    private int intentosTotal;
    private String tipo;
    // Enable botones
    private boolean btn1Beans = true;
    private boolean btn2Beans;

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
     * @return the btn1Beans
     */
    public boolean isBtn1Beans() {
        return btn1Beans;
    }

    /**
     * @param btn1Beans the btn1Beans to set
     */
    public void setBtn1Beans(boolean btn1Beans) {
        this.btn1Beans = btn1Beans;
    }

    /**
     * @return the btn2Beans
     */
    public boolean isBtn2Beans() {
        return btn2Beans;
    }

    /**
     * @param btn2Beans the btn2Beans to set
     */
    public void setBtn2Beans(boolean btn2Beans) {
        this.btn2Beans = btn2Beans;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
