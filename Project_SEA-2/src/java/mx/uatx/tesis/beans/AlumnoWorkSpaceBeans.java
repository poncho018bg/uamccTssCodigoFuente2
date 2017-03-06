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
public class AlumnoWorkSpaceBeans {

    // tabla ejercicioProfesor
    private int idEjercicioProfesor_A;
    private int subtemaProfesor_idSubtemaProfesor_A;
    private String nombre_A;
    private String descripcion_A;
    private String sugerencia_A;
    private String codigoFuente_A;
    private String tiempoTotal_A;
    private int intentosTotal_A;
    private String tipo_a;

    //tabla ejercicioAlumnos
    private int idejercicioAlumno_TA;
    private int ejercicioProfesor_idejercicioProfesor_TA;
    private int casoPrueba_idcasoprueba_TA;
    private int usuarios_idusuarios_TA;
    private int claseAlumno_idclaseAlumno_TA;
    private String codigoFuente_TA;
    private String estatus_TA;
    private String fechaInicio_TA;
    private String fechaFin_TA;
    private String tiempoTotal_TA;
    private int intentosTotal_TA;
    //tabla casos de prueba
    private int idCasoPrueba;
    private int ejercicioPro_idEjercicioPro;
    private String resultado;
    //tabla Parametros
    private int idParametros;
    private int casoPrueba_idCasoPrueba;
    private String tipo;
    private String nombreVariable;
    private String valor;
    // tabla casoPruebaAlumnos
    private int pA_idCasoPruebaAlumno;
    private int pA_ejercicioAlumno_id;
    private int pA_casoPrueba_id;
    private int pA_usuario_id;
    private String pA_status;
    //tabla 2 casoPrueba Alumnos
    // tabla casoPruebaAlumnos
    private int idCasoPruebaAlumno_table;
    private int ejercicioAlumno_id_table;
    private int casoPrueba_id_table;
    private int usuario_id_table;
    private String status_table;
    private String resultado_table;
    private String colorResult;

    private int idEjercicioEstado;
    private String estodoFinal;
    private int idEjrcicioEstadoAlumno;
    // tabla tiempo e intentos
    private int ea_usuarios_idusuarios;
    private int ea_idEjercicioAlumno;
    private int ea_ejercicioProfesor_idEjercicioProfesor;
    private int ep_idejercicioProfesor;
    private int ea_tiempoTotal_a;
    private int ep_tiempoTotal;
    private int ea_intentosTotal_a;
    private int ep_intentosTotal;
    
    // registro existe
    private int contEjercicio;
    
    // TIEMPOS ALUMNOS VS PROFESOR
    private int tiempoAlumnoVs;
    private int intentoAlumnoVs;
    private int tiempoProfesorVs;
    private int intentoProfesorVs;
    
    private String nombreVariableM;
    private String valorVaribaleM;
    private String tipoVariableM;
    private int casoPruebaM;

    /**
     * @return the idEjercicioProfesor_A
     */
    public int getIdEjercicioProfesor_A() {
        return idEjercicioProfesor_A;
    }

    /**
     * @param idEjercicioProfesor_A the idEjercicioProfesor_A to set
     */
    public void setIdEjercicioProfesor_A(int idEjercicioProfesor_A) {
        this.idEjercicioProfesor_A = idEjercicioProfesor_A;
    }

    /**
     * @return the subtemaProfesor_idSubtemaProfesor_A
     */
    public int getSubtemaProfesor_idSubtemaProfesor_A() {
        return subtemaProfesor_idSubtemaProfesor_A;
    }

    /**
     * @param subtemaProfesor_idSubtemaProfesor_A the
     * subtemaProfesor_idSubtemaProfesor_A to set
     */
    public void setSubtemaProfesor_idSubtemaProfesor_A(int subtemaProfesor_idSubtemaProfesor_A) {
        this.subtemaProfesor_idSubtemaProfesor_A = subtemaProfesor_idSubtemaProfesor_A;
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

    /**
     * @return the descripcion_A
     */
    public String getDescripcion_A() {
        return descripcion_A;
    }

    /**
     * @param descripcion_A the descripcion_A to set
     */
    public void setDescripcion_A(String descripcion_A) {
        this.descripcion_A = descripcion_A;
    }

    /**
     * @return the sugerencia_A
     */
    public String getSugerencia_A() {
        return sugerencia_A;
    }

    /**
     * @param sugerencia_A the sugerencia_A to set
     */
    public void setSugerencia_A(String sugerencia_A) {
        this.sugerencia_A = sugerencia_A;
    }

    /**
     * @return the codigoFuente_A
     */
    public String getCodigoFuente_A() {
        return codigoFuente_A;
    }

    /**
     * @param codigoFuente_A the codigoFuente_A to set
     */
    public void setCodigoFuente_A(String codigoFuente_A) {
        this.codigoFuente_A = codigoFuente_A;
    }

    /**
     * @return the idejercicioAlumno_TA
     */
    public int getIdejercicioAlumno_TA() {
        return idejercicioAlumno_TA;
    }

    /**
     * @param idejercicioAlumno_TA the idejercicioAlumno_TA to set
     */
    public void setIdejercicioAlumno_TA(int idejercicioAlumno_TA) {
        this.idejercicioAlumno_TA = idejercicioAlumno_TA;
    }

    /**
     * @return the ejercicioProfesor_idejercicioProfesor_TA
     */
    public int getEjercicioProfesor_idejercicioProfesor_TA() {
        return ejercicioProfesor_idejercicioProfesor_TA;
    }

    /**
     * @param ejercicioProfesor_idejercicioProfesor_TA the
     * ejercicioProfesor_idejercicioProfesor_TA to set
     */
    public void setEjercicioProfesor_idejercicioProfesor_TA(int ejercicioProfesor_idejercicioProfesor_TA) {
        this.ejercicioProfesor_idejercicioProfesor_TA = ejercicioProfesor_idejercicioProfesor_TA;
    }

    /**
     * @return the casoPrueba_idcasoprueba_TA
     */
    public int getCasoPrueba_idcasoprueba_TA() {
        return casoPrueba_idcasoprueba_TA;
    }

    /**
     * @param casoPrueba_idcasoprueba_TA the casoPrueba_idcasoprueba_TA to set
     */
    public void setCasoPrueba_idcasoprueba_TA(int casoPrueba_idcasoprueba_TA) {
        this.casoPrueba_idcasoprueba_TA = casoPrueba_idcasoprueba_TA;
    }

    /**
     * @return the usuarios_idusuarios_TA
     */
    public int getUsuarios_idusuarios_TA() {
        return usuarios_idusuarios_TA;
    }

    /**
     * @param usuarios_idusuarios_TA the usuarios_idusuarios_TA to set
     */
    public void setUsuarios_idusuarios_TA(int usuarios_idusuarios_TA) {
        this.usuarios_idusuarios_TA = usuarios_idusuarios_TA;
    }

    /**
     * @return the codigoFuente_TA
     */
    public String getCodigoFuente_TA() {
        return codigoFuente_TA;
    }

    /**
     * @param codigoFuente_TA the codigoFuente_TA to set
     */
    public void setCodigoFuente_TA(String codigoFuente_TA) {
        this.codigoFuente_TA = codigoFuente_TA;
    }

    /**
     * @return the estatus_TA
     */
    public String getEstatus_TA() {
        return estatus_TA;
    }

    /**
     * @param estatus_TA the estatus_TA to set
     */
    public void setEstatus_TA(String estatus_TA) {
        this.estatus_TA = estatus_TA;
    }

    /**
     * @return the fechaInicio_TA
     */
    public String getFechaInicio_TA() {
        return fechaInicio_TA;
    }

    /**
     * @param fechaInicio_TA the fechaInicio_TA to set
     */
    public void setFechaInicio_TA(String fechaInicio_TA) {
        this.fechaInicio_TA = fechaInicio_TA;
    }

    /**
     * @return the fechaFin_TA
     */
    public String getFechaFin_TA() {
        return fechaFin_TA;
    }

    /**
     * @param fechaFin_TA the fechaFin_TA to set
     */
    public void setFechaFin_TA(String fechaFin_TA) {
        this.fechaFin_TA = fechaFin_TA;
    }

    /**
     * @return the idCasoPrueba
     */
    public int getIdCasoPrueba() {
        return idCasoPrueba;
    }

    /**
     * @param idCasoPrueba the idCasoPrueba to set
     */
    public void setIdCasoPrueba(int idCasoPrueba) {
        this.idCasoPrueba = idCasoPrueba;
    }

    /**
     * @return the ejercicioPro_idEjercicioPro
     */
    public int getEjercicioPro_idEjercicioPro() {
        return ejercicioPro_idEjercicioPro;
    }

    /**
     * @param ejercicioPro_idEjercicioPro the ejercicioPro_idEjercicioPro to set
     */
    public void setEjercicioPro_idEjercicioPro(int ejercicioPro_idEjercicioPro) {
        this.ejercicioPro_idEjercicioPro = ejercicioPro_idEjercicioPro;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the idParametros
     */
    public int getIdParametros() {
        return idParametros;
    }

    /**
     * @param idParametros the idParametros to set
     */
    public void setIdParametros(int idParametros) {
        this.idParametros = idParametros;
    }

    /**
     * @return the casoPrueba_idCasoPrueba
     */
    public int getCasoPrueba_idCasoPrueba() {
        return casoPrueba_idCasoPrueba;
    }

    /**
     * @param casoPrueba_idCasoPrueba the casoPrueba_idCasoPrueba to set
     */
    public void setCasoPrueba_idCasoPrueba(int casoPrueba_idCasoPrueba) {
        this.casoPrueba_idCasoPrueba = casoPrueba_idCasoPrueba;
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

    /**
     * @return the nombreVariable
     */
    public String getNombreVariable() {
        return nombreVariable;
    }

    /**
     * @param nombreVariable the nombreVariable to set
     */
    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the pA_idCasoPruebaAlumno
     */
    public int getpA_idCasoPruebaAlumno() {
        return pA_idCasoPruebaAlumno;
    }

    /**
     * @param pA_idCasoPruebaAlumno the pA_idCasoPruebaAlumno to set
     */
    public void setpA_idCasoPruebaAlumno(int pA_idCasoPruebaAlumno) {
        this.pA_idCasoPruebaAlumno = pA_idCasoPruebaAlumno;
    }

    /**
     * @return the pA_ejercicioAlumno_id
     */
    public int getpA_ejercicioAlumno_id() {
        return pA_ejercicioAlumno_id;
    }

    /**
     * @param pA_ejercicioAlumno_id the pA_ejercicioAlumno_id to set
     */
    public void setpA_ejercicioAlumno_id(int pA_ejercicioAlumno_id) {
        this.pA_ejercicioAlumno_id = pA_ejercicioAlumno_id;
    }

    /**
     * @return the pA_casoPrueba_id
     */
    public int getpA_casoPrueba_id() {
        return pA_casoPrueba_id;
    }

    /**
     * @param pA_casoPrueba_id the pA_casoPrueba_id to set
     */
    public void setpA_casoPrueba_id(int pA_casoPrueba_id) {
        this.pA_casoPrueba_id = pA_casoPrueba_id;
    }

    /**
     * @return the pA_status
     */
    public String getpA_status() {
        return pA_status;
    }

    /**
     * @param pA_status the pA_status to set
     */
    public void setpA_status(String pA_status) {
        this.pA_status = pA_status;
    }

    /**
     * @return the pA_usuario_id
     */
    public int getpA_usuario_id() {
        return pA_usuario_id;
    }

    /**
     * @param pA_usuario_id the pA_usuario_id to set
     */
    public void setpA_usuario_id(int pA_usuario_id) {
        this.pA_usuario_id = pA_usuario_id;
    }

    /**
     * @return the tiempoTotal_A
     */
    public String getTiempoTotal_A() {
        return tiempoTotal_A;
    }

    /**
     * @param tiempoTotal_A the tiempoTotal_A to set
     */
    public void setTiempoTotal_A(String tiempoTotal_A) {
        this.tiempoTotal_A = tiempoTotal_A;
    }

    /**
     * @return the intentosTotal_A
     */
    public int getIntentosTotal_A() {
        return intentosTotal_A;
    }

    /**
     * @param intentosTotal_A the intentosTotal_A to set
     */
    public void setIntentosTotal_A(int intentosTotal_A) {
        this.intentosTotal_A = intentosTotal_A;
    }

    /**
     * @return the tiempoTotal_TA
     */
    public String getTiempoTotal_TA() {
        return tiempoTotal_TA;
    }

    /**
     * @param tiempoTotal_TA the tiempoTotal_TA to set
     */
    public void setTiempoTotal_TA(String tiempoTotal_TA) {
        this.tiempoTotal_TA = tiempoTotal_TA;
    }

    /**
     * @return the intentosTotal_TA
     */
    public int getIntentosTotal_TA() {
        return intentosTotal_TA;
    }

    /**
     * @param intentosTotal_TA the intentosTotal_TA to set
     */
    public void setIntentosTotal_TA(int intentosTotal_TA) {
        this.intentosTotal_TA = intentosTotal_TA;
    }

    /**
     * @return the idCasoPruebaAlumno_table
     */
    public int getIdCasoPruebaAlumno_table() {
        return idCasoPruebaAlumno_table;
    }

    /**
     * @param idCasoPruebaAlumno_table the idCasoPruebaAlumno_table to set
     */
    public void setIdCasoPruebaAlumno_table(int idCasoPruebaAlumno_table) {
        this.idCasoPruebaAlumno_table = idCasoPruebaAlumno_table;
    }

    /**
     * @return the ejercicioAlumno_id_table
     */
    public int getEjercicioAlumno_id_table() {
        return ejercicioAlumno_id_table;
    }

    /**
     * @param ejercicioAlumno_id_table the ejercicioAlumno_id_table to set
     */
    public void setEjercicioAlumno_id_table(int ejercicioAlumno_id_table) {
        this.ejercicioAlumno_id_table = ejercicioAlumno_id_table;
    }

    /**
     * @return the casoPrueba_id_table
     */
    public int getCasoPrueba_id_table() {
        return casoPrueba_id_table;
    }

    /**
     * @param casoPrueba_id_table the casoPrueba_id_table to set
     */
    public void setCasoPrueba_id_table(int casoPrueba_id_table) {
        this.casoPrueba_id_table = casoPrueba_id_table;
    }

    /**
     * @return the usuario_id_table
     */
    public int getUsuario_id_table() {
        return usuario_id_table;
    }

    /**
     * @param usuario_id_table the usuario_id_table to set
     */
    public void setUsuario_id_table(int usuario_id_table) {
        this.usuario_id_table = usuario_id_table;
    }

    /**
     * @return the status_table
     */
    public String getStatus_table() {
        return status_table;
    }

    /**
     * @param status_table the status_table to set
     */
    public void setStatus_table(String status_table) {
        this.status_table = status_table;
    }

    /**
     * @return the claseAlumno_idclaseAlumno_TA
     */
    public int getClaseAlumno_idclaseAlumno_TA() {
        return claseAlumno_idclaseAlumno_TA;
    }

    /**
     * @param claseAlumno_idclaseAlumno_TA the claseAlumno_idclaseAlumno_TA to
     * set
     */
    public void setClaseAlumno_idclaseAlumno_TA(int claseAlumno_idclaseAlumno_TA) {
        this.claseAlumno_idclaseAlumno_TA = claseAlumno_idclaseAlumno_TA;
    }

    /**
     * @return the tipo_a
     */
    public String getTipo_a() {
        return tipo_a;
    }

    /**
     * @param tipo_a the tipo_a to set
     */
    public void setTipo_a(String tipo_a) {
        this.tipo_a = tipo_a;
    }

    /**
     * @return the estodoFinal
     */
    public String getEstodoFinal() {
        return estodoFinal;
    }

    /**
     * @param estodoFinal the estodoFinal to set
     */
    public void setEstodoFinal(String estodoFinal) {
        this.estodoFinal = estodoFinal;
    }

    /**
     * @return the idEjercicioEstado
     */
    public int getIdEjercicioEstado() {
        return idEjercicioEstado;
    }

    /**
     * @param idEjercicioEstado the idEjercicioEstado to set
     */
    public void setIdEjercicioEstado(int idEjercicioEstado) {
        this.idEjercicioEstado = idEjercicioEstado;
    }

    /**
     * @return the idEjrcicioEstadoAlumno
     */
    public int getIdEjrcicioEstadoAlumno() {
        return idEjrcicioEstadoAlumno;
    }

    /**
     * @param idEjrcicioEstadoAlumno the idEjrcicioEstadoAlumno to set
     */
    public void setIdEjrcicioEstadoAlumno(int idEjrcicioEstadoAlumno) {
        this.idEjrcicioEstadoAlumno = idEjrcicioEstadoAlumno;
    }

    /**
     * @return the resultado_table
     */
    public String getResultado_table() {
        return resultado_table;
    }

    /**
     * @param resultado_table the resultado_table to set
     */
    public void setResultado_table(String resultado_table) {
        this.resultado_table = resultado_table;
    }

    /**
     * @return the ea_usuarios_idusuarios
     */
    public int getEa_usuarios_idusuarios() {
        return ea_usuarios_idusuarios;
    }

    /**
     * @param ea_usuarios_idusuarios the ea_usuarios_idusuarios to set
     */
    public void setEa_usuarios_idusuarios(int ea_usuarios_idusuarios) {
        this.ea_usuarios_idusuarios = ea_usuarios_idusuarios;
    }

    /**
     * @return the ea_idEjercicioAlumno
     */
    public int getEa_idEjercicioAlumno() {
        return ea_idEjercicioAlumno;
    }

    /**
     * @param ea_idEjercicioAlumno the ea_idEjercicioAlumno to set
     */
    public void setEa_idEjercicioAlumno(int ea_idEjercicioAlumno) {
        this.ea_idEjercicioAlumno = ea_idEjercicioAlumno;
    }

    /**
     * @return the ea_ejercicioProfesor_idEjercicioProfesor
     */
    public int getEa_ejercicioProfesor_idEjercicioProfesor() {
        return ea_ejercicioProfesor_idEjercicioProfesor;
    }

    /**
     * @param ea_ejercicioProfesor_idEjercicioProfesor the ea_ejercicioProfesor_idEjercicioProfesor to set
     */
    public void setEa_ejercicioProfesor_idEjercicioProfesor(int ea_ejercicioProfesor_idEjercicioProfesor) {
        this.ea_ejercicioProfesor_idEjercicioProfesor = ea_ejercicioProfesor_idEjercicioProfesor;
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
     * @return the ea_tiempoTotal_a
     */
    public int getEa_tiempoTotal_a() {
        return ea_tiempoTotal_a;
    }

    /**
     * @param ea_tiempoTotal_a the ea_tiempoTotal_a to set
     */
    public void setEa_tiempoTotal_a(int ea_tiempoTotal_a) {
        this.ea_tiempoTotal_a = ea_tiempoTotal_a;
    }

    /**
     * @return the ep_tiempoTotal
     */
    public int getEp_tiempoTotal() {
        return ep_tiempoTotal;
    }

    /**
     * @param ep_tiempoTotal the ep_tiempoTotal to set
     */
    public void setEp_tiempoTotal(int ep_tiempoTotal) {
        this.ep_tiempoTotal = ep_tiempoTotal;
    }

    /**
     * @return the ea_intentosTotal_a
     */
    public int getEa_intentosTotal_a() {
        return ea_intentosTotal_a;
    }

    /**
     * @param ea_intentosTotal_a the ea_intentosTotal_a to set
     */
    public void setEa_intentosTotal_a(int ea_intentosTotal_a) {
        this.ea_intentosTotal_a = ea_intentosTotal_a;
    }

    /**
     * @return the ep_intentosTotal
     */
    public int getEp_intentosTotal() {
        return ep_intentosTotal;
    }

    /**
     * @param ep_intentosTotal the ep_intentosTotal to set
     */
    public void setEp_intentosTotal(int ep_intentosTotal) {
        this.ep_intentosTotal = ep_intentosTotal;
    }

    /**
     * @return the colorResult
     */
    public String getColorResult() {
        return colorResult;
    }

    /**
     * @param colorResult the colorResult to set
     */
    public void setColorResult(String colorResult) {
        this.colorResult = colorResult;
    }

    /**
     * @return the contEjercicio
     */
    public int getContEjercicio() {
        return contEjercicio;
    }

    /**
     * @param contEjercicio the contEjercicio to set
     */
    public void setContEjercicio(int contEjercicio) {
        this.contEjercicio = contEjercicio;
    }

    /**
     * @return the tiempoAlumnoVs
     */
    public int getTiempoAlumnoVs() {
        return tiempoAlumnoVs;
    }

    /**
     * @param tiempoAlumnoVs the tiempoAlumnoVs to set
     */
    public void setTiempoAlumnoVs(int tiempoAlumnoVs) {
        this.tiempoAlumnoVs = tiempoAlumnoVs;
    }

    /**
     * @return the intentoAlumnoVs
     */
    public int getIntentoAlumnoVs() {
        return intentoAlumnoVs;
    }

    /**
     * @param intentoAlumnoVs the intentoAlumnoVs to set
     */
    public void setIntentoAlumnoVs(int intentoAlumnoVs) {
        this.intentoAlumnoVs = intentoAlumnoVs;
    }

    /**
     * @return the tiempoProfesorVs
     */
    public int getTiempoProfesorVs() {
        return tiempoProfesorVs;
    }

    /**
     * @param tiempoProfesorVs the tiempoProfesorVs to set
     */
    public void setTiempoProfesorVs(int tiempoProfesorVs) {
        this.tiempoProfesorVs = tiempoProfesorVs;
    }

    /**
     * @return the intentoProfesorVs
     */
    public int getIntentoProfesorVs() {
        return intentoProfesorVs;
    }

    /**
     * @param intentoProfesorVs the intentoProfesorVs to set
     */
    public void setIntentoProfesorVs(int intentoProfesorVs) {
        this.intentoProfesorVs = intentoProfesorVs;
    }

    /**
     * @return the nombreVariableM
     */
    public String getNombreVariableM() {
        return nombreVariableM;
    }

    /**
     * @param nombreVariableM the nombreVariableM to set
     */
    public void setNombreVariableM(String nombreVariableM) {
        this.nombreVariableM = nombreVariableM;
    }

    /**
     * @return the valorVaribaleM
     */
    public String getValorVaribaleM() {
        return valorVaribaleM;
    }

    /**
     * @param valorVaribaleM the valorVaribaleM to set
     */
    public void setValorVaribaleM(String valorVaribaleM) {
        this.valorVaribaleM = valorVaribaleM;
    }

    /**
     * @return the tipoVariableM
     */
    public String getTipoVariableM() {
        return tipoVariableM;
    }

    /**
     * @param tipoVariableM the tipoVariableM to set
     */
    public void setTipoVariableM(String tipoVariableM) {
        this.tipoVariableM = tipoVariableM;
    }

    /**
     * @return the casoPruebaM
     */
    public int getCasoPruebaM() {
        return casoPruebaM;
    }

    /**
     * @param casoPruebaM the casoPruebaM to set
     */
    public void setCasoPruebaM(int casoPruebaM) {
        this.casoPruebaM = casoPruebaM;
    }

}
