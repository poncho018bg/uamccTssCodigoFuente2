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
public class CasoPruebaProBeans {

    // Tabla Caso de Prueba
    private int idCasoPruebaPRO;
    private int ejercicio_idEjercicioPRO;
    private String ResultadoPRO;
    //Tabla Parametros
    private int idParametroPRO;
    private int caso_idCasoPruebaPRO;
    private String tipoPRO;
    private String nombreVariablePRO;
    private String valorPRO;

    /**
     * @return the idCasoPruebaPRO
     */
    public int getIdCasoPruebaPRO() {
        return idCasoPruebaPRO;
    }

    /**
     * @param idCasoPruebaPRO the idCasoPruebaPRO to set
     */
    public void setIdCasoPruebaPRO(int idCasoPruebaPRO) {
        this.idCasoPruebaPRO = idCasoPruebaPRO;
    }

    /**
     * @return the ejercicio_idEjercicioPRO
     */
    public int getEjercicio_idEjercicioPRO() {
        return ejercicio_idEjercicioPRO;
    }

    /**
     * @param ejercicio_idEjercicioPRO the ejercicio_idEjercicioPRO to set
     */
    public void setEjercicio_idEjercicioPRO(int ejercicio_idEjercicioPRO) {
        this.ejercicio_idEjercicioPRO = ejercicio_idEjercicioPRO;
    }

    /**
     * @return the ResultadoPRO
     */
    public String getResultadoPRO() {
        return ResultadoPRO;
    }

    /**
     * @param ResultadoPRO the ResultadoPRO to set
     */
    public void setResultadoPRO(String ResultadoPRO) {
        this.ResultadoPRO = ResultadoPRO;
    }

    /**
     * @return the idParametroPRO
     */
    public int getIdParametroPRO() {
        return idParametroPRO;
    }

    /**
     * @param idParametroPRO the idParametroPRO to set
     */
    public void setIdParametroPRO(int idParametroPRO) {
        this.idParametroPRO = idParametroPRO;
    }

    /**
     * @return the caso_idCasoPruebaPRO
     */
    public int getCaso_idCasoPruebaPRO() {
        return caso_idCasoPruebaPRO;
    }

    /**
     * @param caso_idCasoPruebaPRO the caso_idCasoPruebaPRO to set
     */
    public void setCaso_idCasoPruebaPRO(int caso_idCasoPruebaPRO) {
        this.caso_idCasoPruebaPRO = caso_idCasoPruebaPRO;
    }

    /**
     * @return the tipoPRO
     */
    public String getTipoPRO() {
        return tipoPRO;
    }

    /**
     * @param tipoPRO the tipoPRO to set
     */
    public void setTipoPRO(String tipoPRO) {
        this.tipoPRO = tipoPRO;
    }

    /**
     * @return the nombreVariablePRO
     */
    public String getNombreVariablePRO() {
        return nombreVariablePRO;
    }

    /**
     * @param nombreVariablePRO the nombreVariablePRO to set
     */
    public void setNombreVariablePRO(String nombreVariablePRO) {
        this.nombreVariablePRO = nombreVariablePRO;
    }

    /**
     * @return the valorPRO
     */
    public String getValorPRO() {
        return valorPRO;
    }

    /**
     * @param valorPRO the valorPRO to set
     */
    public void setValorPRO(String valorPRO) {
        this.valorPRO = valorPRO;
    }

}
