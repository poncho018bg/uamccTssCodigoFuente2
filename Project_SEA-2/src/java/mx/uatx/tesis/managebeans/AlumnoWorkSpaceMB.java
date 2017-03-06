/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

//import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import mx.uatx.tesis.beans.AlumnoWorkSpaceBeans;

import mx.uatx.tesis.daos.AlumnoWorkSpaceDAOS;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
//r@SessionScoped
@RequestScoped
//@ViewScoped
public class AlumnoWorkSpaceMB {

    private static StringBuilder contents;

    /**
     * @return the tipoParametroX
     */
    public static String getTipoParametroX() {
        return tipoParametroX;
    }

    /**
     * @param aTipoParametroX the tipoParametroX to set
     */
    public static void setTipoParametroX(String aTipoParametroX) {
        tipoParametroX = aTipoParametroX;
    }

    //Tabla EjerciciosProfesor
    private int idEjercicioProfesorMB_A;
    private int subtemaProfesor_idSubtemaProfesorMB_A;
    private String nombreMB_A;
    private String descripcionMB_A;
    private String sugerenciaMB_A;
    private String codigoFuenteMB_A;
    private String tiempoTotalMB_A;
    private int intentosTotalMB_A;
    private String tipoMB_A;
    private List<AlumnoWorkSpaceBeans> listEjercicioAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
    private List<AlumnoWorkSpaceBeans> validarEjercicioExistente = new ArrayList<AlumnoWorkSpaceBeans>();

    //VARIABLES PARA LA SESSION
    private String idClaseAlumnoX;
    private String ejercicioX;
    private String ejercicioVarX;
    private String ejercicioAlumnoX;
    private static String tipoParametroX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    //tabla ejercicioAlumnos
    private int idejercicioAlumno_TAMB;
    private int ejercicioProfesor_idejercicioProfesor_TAMB;
    private int casoPrueba_idcasoprueba_TAMB;
    private int usuarios_idusuarios_TAMB;
    private int claseAlumno_idclaseAlumno_TAMB;
    private String codigoFuente_TAMB;
    private String estatus_TAMB;
    private String fechaInicio_TAMB;
    private String fechaFin_TAMB;
    private String tiempoTotal_TAMB;
    private int intentosTotal_TAMB;

    private String resultadoCompilador;

    //tabla casos de prueba
    private int idCasoPruebaMB;
    private int ejercicioPro_idEjercicioProMB;
    private String resultadoMB;
    private List<AlumnoWorkSpaceBeans> listCasoPrueba = new ArrayList<AlumnoWorkSpaceBeans>();
    private List<AlumnoWorkSpaceBeans> listParametros = new ArrayList<AlumnoWorkSpaceBeans>();
    private static List<AlumnoWorkSpaceBeans> listParametrosStatic = new ArrayList<AlumnoWorkSpaceBeans>();
    private List<Integer> parametrosInt = new ArrayList<Integer>();
    private String estadoValue;
    private static String estadoValueStatic;
    //tabla Parametros
    private int idParametrosMB;
    private int casoPrueba_idCasoPruebaMB;
    private String tipoMB;
    private String nombreVariableMB;
    private String valorMB;

    // variables para JavaCompiler
    private static String codigoBody;
    private static String salidaString;
    private static boolean toFail = false;

    //tabla caso de prueba Alumnos
    private List<AlumnoWorkSpaceBeans> validarPruebasAlumnos = new ArrayList<AlumnoWorkSpaceBeans>();
    private List<AlumnoWorkSpaceBeans> listPruebasAlumnosSalida = new ArrayList<AlumnoWorkSpaceBeans>();
    private List<AlumnoWorkSpaceBeans> methdParameters = new ArrayList<AlumnoWorkSpaceBeans>();
    private int idEjercicioAlumnoX;
    private String tiempoTotalX;
    private String intentosTotalX;
    private String tipoX;

    //listar TablaCasos de Prueba
    private int tA_idCasoPruebaAlumno;
    private int tA_ejercicioAlumno_id;
    private int tA_casoPrueba_id;
    private int tA_usuario_id;
    private String tA_status;
    private boolean estadoFinal;

    // CRONOMETRO    
    private int number;
    private int segundos;
    private boolean disableBTNcalificar;

    private int idSesion3;

    /**
     * Creates a new instance of AlumnoWorkSpaceMB
     */
    public AlumnoWorkSpaceMB() throws IOException {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        StringFecha();
        downSession1();
        downSession2();
        downSessionclaseAlumno();
        ejercicioAlumnoValidarListar();
        incrementValidar();
        //downSession3();

    }

    public String StringFecha() {
        Calendar fecha = new GregorianCalendar();

        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String simpleDate_Format = anio + "-" + mes + "-" + dia;

        return simpleDate_Format;
    }

    public int downSession1() {
        if (httpServletRequest.getSession().getAttribute("sessionVarIdEjerAlumno") != null) {
            setEjercicioVarX(this.httpServletRequest.getSession().getAttribute("sessionVarIdEjerAlumno").toString());

        }
        int idClase = Integer.parseInt(getEjercicioVarX());

        return idClase;
    }

    public String downSessionclaseAlumno() {
        if (httpServletRequest.getSession().getAttribute("sessionUPclaseAlumno") != null) {
            setIdClaseAlumnoX(this.httpServletRequest.getSession().getAttribute("sessionUPclaseAlumno").toString());

        }

        String idClase = getIdClaseAlumnoX();

        return idClase;
    }

    public int downSession2() {
        if (httpServletRequest.getSession().getAttribute("sessionIDUsuario") != null) {
            setEjercicioX(this.httpServletRequest.getSession().getAttribute("sessionIDUsuario").toString());

        }
        int idClase = Integer.parseInt(getEjercicioX());

        return idClase;
    }

    public int downSession3() {
        if (httpServletRequest.getSession().getAttribute("sessionIDejercicioAlumno") != null) {
            setEjercicioAlumnoX(this.httpServletRequest.getSession().getAttribute("sessionIDejercicioAlumno").toString());

        }
        int idClase = Integer.parseInt(getEjercicioAlumnoX());

        return idClase;
    }

    public String downSession4() {
        /**
         * Metodo para subir a sesión el tiempoTotal de cada ejercicio Profesor
         */
        if (httpServletRequest.getSession().getAttribute("sessionVarTiempo") != null) {
            setTiempoTotalX(this.httpServletRequest.getSession().getAttribute("sessionVarTiempo").toString());

        }
        String idClase = getTiempoTotalX();

        return idClase;
    }

    public String downSession5() {
        /**
         * Metodo para bajar intentosTotal de cada ejercicio Profesor
         */
        if (httpServletRequest.getSession().getAttribute("sessionVarIntentos") != null) {
            setIntentosTotalX(this.httpServletRequest.getSession().getAttribute("sessionVarIntentos").toString());

        }
        String idClase = getIntentosTotalX();

        return idClase;
    }

    public String downSession6() {
        /**
         * Metodo para bajar intentosTotal de cada ejercicio Profesor
         */
        if (httpServletRequest.getSession().getAttribute("sessionVarTipo") != null) {
            setTipoX(this.httpServletRequest.getSession().getAttribute("sessionVarTipo").toString());

        }
        String tipo = getTipoX();

        return tipo;
    }

    public List llenarTablaPruebas() {

        int totalOk = 0;
        int idejercicio = 0;
        int idEjercicio = Integer.valueOf(getEjercicioAlumnoX());

        int idUsuario = Integer.parseInt(getEjercicioX());
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans ejercicioBeans = new AlumnoWorkSpaceBeans();
        setListPruebasAlumnosSalida(ejercicioDao.retriveListSalidasAlumnos(idEjercicio, idUsuario));

        for (int x = 0; x < getListPruebasAlumnosSalida().size(); x++) {
            AlumnoWorkSpaceBeans pruebaFail = (AlumnoWorkSpaceBeans) getListPruebasAlumnosSalida().get(x);

            idejercicio = pruebaFail.getEjercicioAlumno_id_table();

            pruebaFail.getStatus_table();

            if (pruebaFail.getStatus_table().equals("OK")) {
                ++totalOk;
            }

        }

        if (ejercicioDao.listEstadoFinal(idEjercicio).size() != 0) {

            if (totalOk == getListPruebasAlumnosSalida().size()) {

                ejercicioDao.UpdateEstadoFinal(idEjercicio, "OK");
            } else {

                ejercicioDao.UpdateEstadoFinal(idEjercicio, "FAIL");
            }

        } else {
            if (totalOk == getListPruebasAlumnosSalida().size()) {
                ejercicioBeans.setIdEjercicioEstado(idejercicio);
                ejercicioBeans.setEstodoFinal("OK");
                ejercicioDao.insertEstadoFinal(ejercicioBeans);
            } else {
                ejercicioBeans.setIdEjercicioEstado(idejercicio);
                ejercicioBeans.setEstodoFinal("FAIL");
                ejercicioDao.insertEstadoFinal(ejercicioBeans);
            }
        }

        return getListPruebasAlumnosSalida();
    }

    public void evaluarCasos2() {

        int idCaso = 0;
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();

        idCaso = Integer.parseInt(getEjercicioVarX());
        setListCasoPrueba(ejercicioDao.retriveCasosPrueba(idCaso));

        for (int x = 0; x < getListCasoPrueba().size(); x++) {
            AlumnoWorkSpaceBeans casoPrueba = (AlumnoWorkSpaceBeans) getListCasoPrueba().get(x);
            setIdCasoPruebaMB(casoPrueba.getIdCasoPrueba());
            setEjercicioPro_idEjercicioProMB(casoPrueba.getEjercicioPro_idEjercicioPro());
            setResultadoMB(casoPrueba.getResultado());
            //for para listar los parametros
            setListParametros(ejercicioDao.retriveListParametros(casoPrueba.getEjercicioPro_idEjercicioPro()));
            for (int y = 0; y < getListParametros().size(); y++) {
                AlumnoWorkSpaceBeans parametros = (AlumnoWorkSpaceBeans) getListParametros().get(y);

                setIdParametrosMB(parametros.getIdParametros());
                setCasoPrueba_idCasoPruebaMB(parametros.getCasoPrueba_idCasoPrueba());
                setTipoMB(parametros.getTipo());
                setNombreVariableMB(parametros.getNombreVariable());
                setValorMB(parametros.getValor());
                // SUBIENTO A SESION EL TIPO DE PARAMETRO (int, String, float)

                setTipoParametroX(getTipoMB());
            }

        }
        
    }

    public void ejercicioAlumnoVer() {
        int idActual = 0;
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans ejercicioBeans = new AlumnoWorkSpaceBeans();

        idActual = Integer.parseInt(getEjercicioVarX());

        //LISTANDO LOS METHODPARAMETERS DEL METODO
        setListPruebasAlumnosSalida(ejercicioDao.idCasoPrueba(Integer.parseInt(getEjercicioVarX())));

        String methodParameter = "";
        int idCaso = 0;
        for (int x = 0; x < getListPruebasAlumnosSalida().size(); x++) {

            AlumnoWorkSpaceBeans pruebaFail = (AlumnoWorkSpaceBeans) getListPruebasAlumnosSalida().get(x);
            if (x < 1) {
                idCaso = pruebaFail.getCasoPruebaM();

            }

        }

        //methdParameters
        setMethdParameters(ejercicioDao.methodParameter(idCaso));

        for (int x = 0; x < getMethdParameters().size(); x++) {

            AlumnoWorkSpaceBeans pruebaFail = (AlumnoWorkSpaceBeans) getMethdParameters().get(x);

            if (x < getMethdParameters().size() - 1) {
                methodParameter += pruebaFail.getTipoVariableM() + " " + pruebaFail.getNombreVariableM() + ", ";
            } else {
                methodParameter += pruebaFail.getTipoVariableM() + " " + pruebaFail.getNombreVariableM() + " ";
            }

        }

        setListEjercicioAlumno(ejercicioDao.retriveListEjercicioPro(idActual));

        for (int x = 0; x < getListEjercicioAlumno().size(); x++) {
            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) getListEjercicioAlumno().get(x);
            setIdEjercicioProfesorMB_A(ejercicio.getIdEjercicioProfesor_A());
            setNombreMB_A(ejercicio.getNombre_A());
            setDescripcionMB_A(ejercicio.getDescripcion_A());
            setSugerenciaMB_A(ejercicio.getSugerencia_A());

            StringTokenizer token = new StringTokenizer(ejercicio.getCodigoFuente_A(), "(");
            String tok2 = ejercicio.getCodigoFuente_A();

            StringBuilder builder = new StringBuilder(ejercicio.getCodigoFuente_A());
            String sCadenaInvertida = builder.reverse().toString();
            String tok_1 = null;
            String tok_2 = null;
            StringTokenizer token2 = new StringTokenizer(sCadenaInvertida, ")");

            for (int i = 1; i < 2; i++) {
                tok_1 = token.nextToken();

            }

            for (int i = 1; i < 2; i++) {
                tok_2 = token.nextToken();
            }

            setCodigoFuenteMB_A(tok_1 + "(" + methodParameter + tok_2);

            setTiempoTotalMB_A(ejercicio.getTiempoTotal_A());
            setIntentosTotalMB_A(ejercicio.getIntentosTotal_A());
            setTipoMB_A(ejercicio.getTipo_a());
        }
        // Subir a sesión el total de intentos y el tiempo total del ejercicio
        upTiempoTotal(getTiempoTotalMB_A());
        upIntentosTotal(getIntentosTotalMB_A());
        upTipoEjercicio(getTipoMB_A());
        downSession6();

        
    }

    public void ejercicioAlumnoProUPDATE() {
        int idEjProfesor = 0;
        int idAlumno = 0;
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans ejercicioBeans = new AlumnoWorkSpaceBeans();

        idEjProfesor = Integer.parseInt(getEjercicioVarX());

        idAlumno = Integer.parseInt(getEjercicioX());

        setListEjercicioAlumno(ejercicioDao.retriveListEjercicioAlumnosEdit2(idEjProfesor, idAlumno));

        for (int x = 0; x < getListEjercicioAlumno().size(); x++) {

            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) getListEjercicioAlumno().get(x);

            setIdEjercicioProfesorMB_A(ejercicio.getIdEjercicioProfesor_A());
            setNombreMB_A(ejercicio.getNombre_A());
            setDescripcionMB_A(ejercicio.getDescripcion_A());
            setSugerenciaMB_A(ejercicio.getSugerencia_A());
            setTiempoTotalMB_A(ejercicio.getTiempoTotal_A());
            setIntentosTotalMB_A(ejercicio.getIntentosTotal_A());
            setTipoMB_A(ejercicio.getTipo_a());

            setIdejercicioAlumno_TAMB(ejercicio.getIdejercicioAlumno_TA());
            setEjercicioProfesor_idejercicioProfesor_TAMB(ejercicio.getEjercicioProfesor_idejercicioProfesor_TA());
            setCasoPrueba_idcasoprueba_TAMB(ejercicio.getCasoPrueba_idcasoprueba_TA());
            setUsuarios_idusuarios_TAMB(ejercicio.getUsuarios_idusuarios_TA());

            setCodigoFuenteMB_A(ejercicio.getCodigoFuente_A());

            setFechaInicio_TAMB(ejercicio.getFechaInicio_TA());
            setFechaFin_TAMB(ejercicio.getFechaFin_TA());
            setTiempoTotal_TAMB(ejercicio.getTiempoTotal_TA());
            setIntentosTotal_TAMB(ejercicio.getIntentosTotal_TA());

        }
        //tiempo del alumno
        setNumber(Integer.parseInt(getTiempoTotal_TAMB()));
        // subir a sesion el id del ejercicio alumno
        upEjercicioAlumno(getIdejercicioAlumno_TAMB());
        // bubir a sesion el tipo de ejercicio
        upTipoEjercicio(getTipoMB_A());
        downSession6();
        //BAJANDO ID EJERCICIO ALUMNO 
        downSession3();

        
    }

    public void ejercicioAlumnoValidarListar() throws IOException {

        int idEjProfesor = 0;
        int idAlumno = 0;
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans ejercicioBeans = new AlumnoWorkSpaceBeans();

        idEjProfesor = Integer.parseInt(getEjercicioVarX());

        idAlumno = Integer.parseInt(getEjercicioX());

        int ejercicioExiste = 0;

        setValidarEjercicioExistente(ejercicioDao.retriveEjercicioActualizado(idEjProfesor, idAlumno));

        for (int x = 0; x < getValidarEjercicioExistente().size(); x++) {

            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) getValidarEjercicioExistente().get(x);
            ejercicioExiste = ejercicio.getContEjercicio();
        }

        if (ejercicioExiste != 0) {
            //Lista los datos de la tabla ejercicioProfesor ya que el ejercicio no exixte en la tabla ejercicioAlumno

            ejercicioAlumnoProUPDATE();
            llenarTablaPruebas();
            profeVsAlum();

        } else {

            //Lista los campos de la tabla EJERCICIOALUMNO ya actualizados
            ejercicioAlumnoVer();

        }
        
    }

    public int getNumber() {
        return number;
    }

    public void profeVsAlum() {

        int tiempoA = 0;
        int tiempoP = 0;
        int intentoA = 0;
        int intentoP = 0;
        AlumnoWorkSpaceDAOS excerciseDAO = new AlumnoWorkSpaceDAOS();

        List<AlumnoWorkSpaceBeans> vs = excerciseDAO.alumVSprof(Integer.parseInt(getEjercicioAlumnoX()));
        for (int x = 0; x < vs.size(); x++) {

            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) vs.get(x);
            tiempoA = ejercicio.getTiempoAlumnoVs();
            intentoA = ejercicio.getIntentoAlumnoVs();
            tiempoP = ejercicio.getTiempoProfesorVs();
            intentoP = ejercicio.getIntentoProfesorVs();
        }

        if (tiempoA >= tiempoP || intentoA >= intentoP) {
            setDisableBTNcalificar(true);
        }

    }

    public int increment() throws IOException {

        AlumnoWorkSpaceDAOS excerciseDAO = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans exerciseBeans = new AlumnoWorkSpaceBeans();

        setEjercicioProfesor_idejercicioProfesor_TAMB(Integer.parseInt(getEjercicioVarX()));

        setUsuarios_idusuarios_TAMB(Integer.parseInt(getEjercicioX()));
        FacesContext context = FacesContext.getCurrentInstance();

        int tiempo = Integer.parseInt(getTiempoTotalMB_A());

        if (number >= tiempo) {
            setDisableBTNcalificar(true);
        }

        number++;
        exerciseBeans.setTiempoTotal_TA(String.valueOf(number));

        if (number == tiempo) {
            excerciseDAO.UpdateExerciseAlumnoTiempo(exerciseBeans, getEjercicioProfesor_idejercicioProfesor_TAMB(), getUsuarios_idusuarios_TAMB());

        } else if (number > tiempo) {

            context.addMessage(null, new FacesMessage("STOP", "El tiempo para este ejercicio ha terminado"));
            setDisableBTNcalificar(true);

        } else {
            excerciseDAO.UpdateExerciseAlumnoTiempo(exerciseBeans, getEjercicioProfesor_idejercicioProfesor_TAMB(), getUsuarios_idusuarios_TAMB());
        }

        return number;
    }

    public void incrementSegundos() {
        segundos++;

    }

    public int incrementValidar() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();

        int tiempo = Integer.parseInt(getTiempoTotalMB_A());

        if (number >= tiempo) {

            context.addMessage(null, new FacesMessage("STOP", "El tiempo para este ejercicio ha terminado"));
            setDisableBTNcalificar(true);

        }

        isDisableBTNcalificar();
        return number;
    }

    public void ejercicioAlumnoValidarSave() {
        System.out.println("CALIFICO 1 "+this.codigoFuenteMB_A);
       // llenarTablaPruebas();

        resultadoCompilador = "";
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();
        int idEjProfesor = Integer.parseInt(getEjercicioVarX());
        int idAlumno = Integer.parseInt(getEjercicioX());
        setValidarEjercicioExistente(ejercicioDao.retriveEjercicioActualizado(idEjProfesor, idAlumno));
        int ejercicioExiste = 0;

        for (int x = 0; x < getValidarEjercicioExistente().size(); x++) {

            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) getValidarEjercicioExistente().get(x);
            ejercicioExiste = ejercicio.getContEjercicio();
        }
        if (ejercicioExiste != 0) {

            updateExerciseAlumno();

        } else {

            saveExerciseAlumno();

        }
        if (isDisableBTNcalificar()) {
//            SUPERASTE LOS INTENTOS YA NO PUEDES CONTINUAR
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("STOP", "El Número de intentos  para este ejercicio se ha superado"));

        } else {
            executeCode();
            setResultadoCompilador("\n" + getSalidaString().replace("[\n\r]", "->").trim());

        }

        llenarTablaPruebas();

    }

    public void upEjercicioAlumno(int idEjercicioAlumno) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionIDejercicioAlumno", idEjercicioAlumno);

        } else {

        }
        
    }

    public final void upSalidaCompilador(String salida) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionSalidaCompilador", salida);

        } else {

        }
        
    }

    public void upIntentosTotal(int intentos) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionVarIntentos", intentos);

        } else {

        }
        
    }

    public void upTiempoTotal(String tiempo) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionVarTiempo", tiempo);

        } else {

        }
        
    }

    public void upTipoEjercicio(String tipo) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionVarTipo", tipo);

        } else {

        }
        
    }

    public void upTipoParametro(String tipo) {

        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {

        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionTipoParametro", tipo);

        } else {

        }
        
    }

    public void saveExerciseAlumno() {

        AlumnoWorkSpaceDAOS excerciseDAO = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans exerciseBeans = new AlumnoWorkSpaceBeans();

        setEjercicioProfesor_idejercicioProfesor_TAMB(Integer.parseInt(getEjercicioVarX()));
        setUsuarios_idusuarios_TAMB(Integer.parseInt(getEjercicioX()));

        setFechaInicio_TAMB(StringFecha());
        setFechaFin_TAMB(StringFecha());
        setTiempoTotal_TAMB(String.valueOf(getNumber()));
        setIntentosTotal_TAMB(1);

        setClaseAlumno_idclaseAlumno_TAMB(Integer.parseInt(getIdClaseAlumnoX()));

        exerciseBeans.setEjercicioProfesor_idejercicioProfesor_TA(getEjercicioProfesor_idejercicioProfesor_TAMB());
        exerciseBeans.setUsuarios_idusuarios_TA(getUsuarios_idusuarios_TAMB());
        exerciseBeans.setClaseAlumno_idclaseAlumno_TA(getClaseAlumno_idclaseAlumno_TAMB());
        exerciseBeans.setCodigoFuente_TA(getCodigoFuenteMB_A().replaceAll("'", "\\\\'")); 
        exerciseBeans.setFechaInicio_TA(getFechaInicio_TAMB());
        exerciseBeans.setFechaFin_TA(getFechaFin_TAMB());
        exerciseBeans.setTiempoTotal_TA(getTiempoTotal_TAMB());
        exerciseBeans.setIntentosTotal_TA(getIntentosTotal_TAMB());

        excerciseDAO.createExercisePro(exerciseBeans);

    }

    public void createPruebaAlumno(int idCasoPrueba, String estatus) {

        int idEjercicioAlumno2 = Integer.parseInt(getEjercicioAlumnoX());
        setIdEjercicioAlumnoX(idEjercicioAlumno2);

        int idUsuario = Integer.parseInt(getEjercicioX());

        AlumnoWorkSpaceDAOS excerciseDAO = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans exerciseBeans = new AlumnoWorkSpaceBeans();

        setValidarPruebasAlumnos(excerciseDAO.retriveListPruebasAlumnos(getIdEjercicioAlumnoX(), idCasoPrueba, idUsuario));

        if (getValidarPruebasAlumnos().size() != 0) {

            excerciseDAO.UpdatePruebasAlumno(estatus, getIdEjercicioAlumnoX(), idCasoPrueba, idUsuario);
        } else {

            exerciseBeans.setpA_ejercicioAlumno_id(getIdEjercicioAlumnoX());
            exerciseBeans.setpA_casoPrueba_id(idCasoPrueba);
            exerciseBeans.setpA_usuario_id(idUsuario);
            exerciseBeans.setpA_status(estatus);

            excerciseDAO.createPruebaAlumno(exerciseBeans);
        }

        
    }

    public void updateExerciseAlumno() {
        AlumnoWorkSpaceDAOS excerciseDAO = new AlumnoWorkSpaceDAOS();
        AlumnoWorkSpaceBeans exerciseBeans = new AlumnoWorkSpaceBeans();

        setEjercicioProfesor_idejercicioProfesor_TAMB(Integer.parseInt(getEjercicioVarX()));
        setUsuarios_idusuarios_TAMB(Integer.parseInt(getEjercicioX()));

        setListEjercicioAlumno(excerciseDAO.retriveListEjercicioAlumnosEdit2(getEjercicioProfesor_idejercicioProfesor_TAMB(), getUsuarios_idusuarios_TAMB()));
        for (int x = 0; x < getListEjercicioAlumno().size(); x++) {

            AlumnoWorkSpaceBeans ejercicio = (AlumnoWorkSpaceBeans) getListEjercicioAlumno().get(x);

            setIdEjercicioProfesorMB_A(ejercicio.getIdEjercicioProfesor_A());
            setNombreMB_A(ejercicio.getNombre_A());
            setDescripcionMB_A(ejercicio.getDescripcion_A());
            setSugerenciaMB_A(ejercicio.getSugerencia_A());
            setTiempoTotalMB_A(ejercicio.getTiempoTotal_A());
            setIntentosTotalMB_A(ejercicio.getIntentosTotal_A());

            setIdejercicioAlumno_TAMB(ejercicio.getIdejercicioAlumno_TA());
            setEjercicioProfesor_idejercicioProfesor_TAMB(ejercicio.getEjercicioProfesor_idejercicioProfesor_TA());
            setCasoPrueba_idcasoprueba_TAMB(ejercicio.getCasoPrueba_idcasoprueba_TA());
            setUsuarios_idusuarios_TAMB(ejercicio.getUsuarios_idusuarios_TA());

            setFechaInicio_TAMB(ejercicio.getFechaInicio_TA());
            setFechaFin_TAMB(ejercicio.getFechaFin_TA());
            setTiempoTotal_TAMB(ejercicio.getTiempoTotal_TA());
            setIntentosTotal_TAMB(ejercicio.getIntentosTotal_TA());

        }
        int intentos = Integer.valueOf(getIntentosTotal_TAMB());

        intentos++;
        getIntentosTotalMB_A();
        if (intentos >= getIntentosTotalMB_A()) {
            setDisableBTNcalificar(true);

        }
        int feee = number;
        exerciseBeans.setCodigoFuente_TA(getCodigoFuenteMB_A().replaceAll("'", "\\\\'"));
        exerciseBeans.setFechaFin_TA(StringFecha());
        exerciseBeans.setTiempoTotal_TA(String.valueOf(feee));
        exerciseBeans.setIntentosTotal_TA(intentos);
        excerciseDAO.UpdateExerciseAlumno(exerciseBeans, getEjercicioProfesor_idejercicioProfesor_TAMB(), getUsuarios_idusuarios_TAMB());
    }

    public void redirectToBackExceAlumno() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_Ejercicio.xhtml");
        
    }

    /**
     * INICIA LA LOGICA PARA COMPILAR EL CODIGO
     * ........................................................................................................................
     * ************************************************************************************************************************
     */
    public void executeCode() {

        //1.Construct an in-memory java source file from your dynamic code
        JavaFileObject file = getJavaFileObject(getCodigoFuenteMB_A());
        Iterable<? extends JavaFileObject> files = Arrays.asList(file);

        //2.Compile your files by JavaCompiler
        compile(files);

        //3.Load your class by URLClassLoader, then instantiate the instance, and call method by reflection
        int idCaso = 0;
        AlumnoWorkSpaceDAOS ejercicioDao = new AlumnoWorkSpaceDAOS();

        idCaso = Integer.parseInt(getEjercicioVarX());

        setListCasoPrueba(ejercicioDao.retriveCasosPrueba(idCaso));

        for (int x = 0; x < getListCasoPrueba().size(); x++) {
            AlumnoWorkSpaceBeans casoPrueba = (AlumnoWorkSpaceBeans) getListCasoPrueba().get(x);
            setIdCasoPruebaMB(casoPrueba.getIdCasoPrueba());
            setEjercicioPro_idEjercicioProMB(casoPrueba.getEjercicioPro_idEjercicioPro());
            setResultadoMB(casoPrueba.getResultado());

            //for para listar los parametros
            setListParametros(ejercicioDao.retriveListParametros(getIdCasoPruebaMB()));

            List<AlumnoWorkSpaceBeans> parametross = ejercicioDao.retriveListParametros(casoPrueba.getEjercicioPro_idEjercicioPro());
            int tamanio = getListParametros().size();

            int tipoMetodoInt = Integer.parseInt(getTipoX());
            runIt(tamanio, getListParametros(), getResultadoMB(), tipoMetodoInt);

            setEstadoValue(getEstadoValueStatic());

            // guardar el idCasoPrueba en la tabla casoPruebaAlumno
            if (isToFail()) {
                createPruebaAlumno(getIdCasoPruebaMB(), "Fail");
            } else {
                createPruebaAlumno(getIdCasoPruebaMB(), getEstadoValue());
            }

        }

    }
    private static String classOutputFolder = "/";

    /**
     * @return the methdParameters
     */
    public List<AlumnoWorkSpaceBeans> getMethdParameters() {
        return methdParameters;
    }

    /**
     * @param methdParameters the methdParameters to set
     */
    public void setMethdParameters(List<AlumnoWorkSpaceBeans> methdParameters) {
        this.methdParameters = methdParameters;
    }

    /**
     * @return the idSesion3
     */
    public int getIdSesion3() {
        return idSesion3;
    }

    /**
     * @param idSesion3 the idSesion3 to set
     */
    public void setIdSesion3(int idSesion3) {
        this.idSesion3 = idSesion3;
    }

    public static class MyDiagnosticListener implements DiagnosticListener<JavaFileObject> {

        public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

            System.out.println("Line Number->" + diagnostic.getLineNumber());
            System.out.println("code->" + diagnostic.getCode());
            System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
            System.out.println("Source->" + diagnostic.getSource());
            System.out.println(" ");
            String SalidaCompilador = " \n Line Number->" + (diagnostic.getLineNumber()-1)
                    + " \n code->" + diagnostic.getCode()
                    + " \n Message->" + diagnostic.getMessage(Locale.ENGLISH)
                    + " \n Source->" + diagnostic.getSource() + "";
            String out1 = getSalidaString();
            setSalidaString("\n" + out1 + SalidaCompilador + "");

        }
    }

    /**
     * java File Object represents an in-memory java source file <br>
     * so there is no need to put the source file on hard disk *
     */
    public static class InMemoryJavaFileObject extends SimpleJavaFileObject {

        private String contents = null;

        public InMemoryJavaFileObject(String className, String contents) throws Exception {
            super(URI.create("string:///" + className.replace('.', '/')
                    + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.contents = contents;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException {
            return contents;
        }
    }

    /**
     * Get a simple Java File Object ,<br>
     * It is just for demo, content of the source code is dynamic in real use
     * case
     */
    private static JavaFileObject getJavaFileObject(String codeBody) {

        String codigoHead = "package math;"
                + "public class Calculator { \n";

        String codigoFoot = "}";

        contents = new StringBuilder(codigoHead + codeBody + codigoFoot);
        JavaFileObject so = null;
        try {
            so = new InMemoryJavaFileObject("math.Calculator", contents.toString());
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return so;
    }

    /**
     * compile your files by JavaCompiler
     */
    public static void compile(Iterable<? extends JavaFileObject> files) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        MyDiagnosticListener c = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c, Locale.ENGLISH, null);

        Iterable options = Arrays.asList("-d", classOutputFolder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, c, options, null, files);
        Boolean result = task.call();
        if (result == true) {
            System.out.println("Succeed");
            setToFail(false);
        } else {
            setToFail(true);
        }
    }

    public static void runIt(int totalParametros, List<AlumnoWorkSpaceBeans> parametros, String compileOut, int tipo) {

        File file = new File(classOutputFolder);

        try {
            // Convert File to a URL
            URL url = file.toURL(); // file:/classes/demo
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader loader = new URLClassLoader(urls);

            // Load in the class; Class.childclass should be located in
            // the directory file:/class/demo/
            Class thisClass = loader.loadClass("math.Calculator");

            /**
             * LAVJ: Aquí puedo enviarle los parametros y valores que quiera
             */
            String valorParametrosString = null;
            String tipoParametrosString1 = null;
            String tipoParametrosString2 = null;
            int valorParametrosInt = 0;
            String valorParametrosString2 = null;
            float valorParametrosFloat2 = 0;

            /**
             * inicia logica de sacar int
             */
            String codigo = contents.toString();
            StringTokenizer token = new StringTokenizer(codigo, ")");
            String tok_1 = null;
            String tok_2 = null;

            for (int i = 1; i < 2; i++) {
                tok_1 = token.nextToken();

            }

            StringBuilder builder = new StringBuilder(tok_1);
            String sCadenaInvertida = builder.reverse().toString();

            StringTokenizer token2 = new StringTokenizer(sCadenaInvertida, "(");
            for (int i = 1; i < 2; i++) {
                tok_2 = token2.nextToken();
            }

            StringBuilder builder2 = new StringBuilder(tok_2);
            // Texto
            String sCadenaInvertida2 = builder2.reverse().toString();
            String sCadenaInvertidaString = sCadenaInvertida2;
            String sCadenaInvertidaFloat = sCadenaInvertida2;

            // Texto que vamos a buscar
            String sTextoBuscadoInt = "int";
            String sTextoBuscadoString = "String";
            String sTextoBuscadoFloat = "float";

            // Contador de ocurrencias 
            int contador = 0;
            int contador2 = 0;
            int contador3 = 0;
            /**
             * BUSCANDO ENTEROS
             */
            while (sCadenaInvertida2.indexOf(sTextoBuscadoInt) > -1) {
                sCadenaInvertida2 = sCadenaInvertida2.substring(sCadenaInvertida2.indexOf(
                        sTextoBuscadoInt) + sTextoBuscadoInt.length(), sCadenaInvertida2.length());
                contador++;
            }
            /**
             * BUSCANDO STRING
             */
            while (sCadenaInvertidaString.indexOf(sTextoBuscadoString) > -1) {
                sCadenaInvertidaString = sCadenaInvertidaString.substring(sCadenaInvertidaString.indexOf(
                        sTextoBuscadoString) + sTextoBuscadoString.length(), sCadenaInvertidaString.length());
                contador2++;
            }
            /**
             * BUSCANDO FLOAT
             */
            while (sCadenaInvertidaFloat.indexOf(sTextoBuscadoFloat) > -1) {
                sCadenaInvertidaFloat = sCadenaInvertidaFloat.substring(sCadenaInvertidaFloat.indexOf(
                        sTextoBuscadoFloat) + sTextoBuscadoFloat.length(), sCadenaInvertidaFloat.length());
                contador3++;
            }

            Class[] par = null;
            contador += contador2 + contador3;
            /**
             * verifica que los method parameter del alumno y profesor coincidan
             */
            if (contador == totalParametros) {
                System.out.println("+++++++++++++++CONTATOR Y TOTAL PARAMETROS ES IGUALES+++++++++++++++");
                par = new Class[totalParametros];

                switch (tipo) {
                    case 1:
                        for (int x = 0; x < totalParametros; x++) {

                            if (parametros.get(x).getTipo().equals("int")) {

                                par[x] = Integer.TYPE;
                            } else if (parametros.get(x).getTipo().equals("String")) {

                                par[x] = String.class;
                            } else if (parametros.get(x).getTipo().equals("float")) {

                                par[x] = Float.TYPE;
                            }
                        }
                        Object paramsObj[] = new Object[totalParametros];
                        Object[] losValores = new Object[totalParametros];

                        for (int x1 = 0; x1 < totalParametros; x1++) {

                            // llenar con los valores de la tabla parametros 
                            if (parametros.get(x1).getTipo().equals("int")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                valorParametrosInt = Integer.parseInt(valorParametrosString);
                                losValores[x1] = valorParametrosInt;

                            } else if (parametros.get(x1).getTipo().equals("String")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                losValores[x1] = valorParametrosString;

                            } else if (parametros.get(x1).getTipo().equals("float")) {
                                valorParametrosString = parametros.get(x1).getValor();
                                float valorFloat = Float.valueOf(valorParametrosString);
                                losValores[x1] = valorFloat;

                            }
                        }

                        for (int x = 0; x < totalParametros; x++) {
                            paramsObj[x] = losValores[x];
                        }

                        Object instance = thisClass.newInstance();
                        Method thisMethod = thisClass.getDeclaredMethod("validaEjercicio", par);
                        System.out.println("SALIDA" + compileOut);
                        int saidaInt = Integer.parseInt(compileOut);
                        /**
                         * LAVJ: Aquí capturo la salida
                         */
                        Integer salida = (Integer) thisMethod.invoke(instance, paramsObj);
                        //muestra la salida el textArea
                        String out1 = getSalidaString();
                        setSalidaString(" \n " + out1 + "\n" + salida);
                        if (saidaInt == salida) {

                            setEstadoValueStatic("OK");

                        } else {

                            setEstadoValueStatic("FAIL");
                        }

                        break;
                    case 2:
                        for (int x = 0; x < totalParametros; x++) {

                            if (parametros.get(x).getTipo().equals("int")) {

                                par[x] = Integer.TYPE;
                            } else if (parametros.get(x).getTipo().equals("String")) {

                                par[x] = String.class;
                            } else if (parametros.get(x).getTipo().equals("float")) {

                                par[x] = Float.TYPE;
                            }
                        }
                        Object paramsObjString[] = new Object[totalParametros];
                        //int[] losValores = new int[totalParametros];
                        Object[] losvaloresString = new Object[totalParametros];

//                            // llenar con los valores de la tabla parametros 
                        for (int x1 = 0; x1 < totalParametros; x1++) {

                            if (parametros.get(x1).getTipo().equals("int")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                valorParametrosInt = Integer.parseInt(valorParametrosString);
                                losvaloresString[x1] = valorParametrosInt;

                            } else if (parametros.get(x1).getTipo().equals("String")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                losvaloresString[x1] = valorParametrosString;

                            } else if (parametros.get(x1).getTipo().equals("float")) {
                                valorParametrosString = parametros.get(x1).getValor();
                                float valorFloat = Float.valueOf(valorParametrosString);
                                losvaloresString[x1] = valorFloat;

                            }
                        }

                        for (int x = 0; x < totalParametros; x++) {
                            paramsObjString[x] = losvaloresString[x];
                        }

                        Object instanceString = thisClass.newInstance();
                        Method thisMethodString = thisClass.getDeclaredMethod("validaEjercicio", par);

                        String saidaString = String.valueOf(compileOut);
                        /**
                         * LAVJ: Aquí capturo la salida
                         */
                        //Integer salida = (Integer) thisMethod.invoke(instance, paramsObj);
                        String salidaString2 = (String) thisMethodString.invoke(instanceString, paramsObjString);
                        //muestra la salida el textArea
                        String out = getSalidaString();
                        setSalidaString("\n " + out + "\n" + salidaString2);
                        // mandar la salida a la BD
                        if (saidaString.equals(salidaString2)) {

                            setEstadoValueStatic("OK");

                        } else {

                            setEstadoValueStatic("FAIL");
                        }
                        break;
                    case 3:
                        for (int x = 0; x < totalParametros; x++) {

                            if (parametros.get(x).getTipo().equals("int")) {

                                par[x] = Integer.TYPE;
                            } else if (parametros.get(x).getTipo().equals("String")) {

                                par[x] = String.class;
                            } else if (parametros.get(x).getTipo().equals("float")) {

                                par[x] = Float.TYPE;
                            }
                        }
                        Object paramsObjFloat[] = new Object[totalParametros];

                        Object[] losvaloresFloat = new Object[totalParametros];
                        for (int x1 = 0; x1 < totalParametros; x1++) {

                            if (parametros.get(x1).getTipo().equals("int")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                valorParametrosInt = Integer.parseInt(valorParametrosString);
                                losvaloresFloat[x1] = valorParametrosInt;

                            } else if (parametros.get(x1).getTipo().equals("String")) {

                                valorParametrosString = parametros.get(x1).getValor();
                                losvaloresFloat[x1] = valorParametrosString;

                            } else if (parametros.get(x1).getTipo().equals("float")) {
                                valorParametrosString = parametros.get(x1).getValor();
                                float valorFloat = Float.valueOf(valorParametrosString);
                                losvaloresFloat[x1] = valorFloat;

                            }
                        }

                        for (int x = 0; x < totalParametros; x++) {
                            paramsObjFloat[x] = losvaloresFloat[x];
                        }

                        Object instanceFloat = thisClass.newInstance();
                        Method thisMethodFloat = thisClass.getDeclaredMethod("validaEjercicio", par);

                        Float saidaFloat = Float.valueOf(compileOut);
                        /**
                         * LAVJ: Aquí capturo la salida
                         */
                        //Integer salida = (Integer) thisMethod.invoke(instance, paramsObj);
                        Float salidaFloat2 = (Float) thisMethodFloat.invoke(instanceFloat, paramsObjFloat);
                        //muestra la salida el textArea
                        String out12 = getSalidaString();
                        setSalidaString(" \n " + out12 + "\n" + salidaFloat2);
                        // mandar la salida a la BD
                        if (saidaFloat.equals(salidaFloat2)) {

                            setEstadoValueStatic("OK");

                        } else {

                            setEstadoValueStatic("FAIL");
                        }
                        break;

                    default:
                        break;

                }
            } else if (contador < totalParametros) {
//      los method paramether de tu codigo son menores a los que el profesor ha declarado
                setEstadoValueStatic("FAIL");

                //muestra la salida el textArea
                String out12 = getSalidaString();
                setSalidaString(" \n " + out12 + " \n número de Method parameters declarados no corresponden");

            } else if (contador > totalParametros) {
//                CONTATOR ES MAYOR A  TOTAL PARAMETROS 
                setEstadoValueStatic("FAIL");

                //muestra la salida el textArea
                String out12 = getSalidaString();
                setSalidaString(" \n " + out12 + " \n número de Method parameters declarados no corresponden");

            }
            /**
             * termina logica de sacar int
             */

        } catch (MalformedURLException e) {
            setSalidaString("\n" + e);
        } catch (ClassNotFoundException e) {
            setSalidaString("\n" + e);
        } catch (Exception ex) {
            ex.printStackTrace();
            setSalidaString("\n" + ex);

        }
    }

    /**
     * TERMINA LA LOGICA PARA COMPILAR EL CODIGO
     * ........................................................................................................................
     */
    /**
     * @return the idEjercicioProfesorMB_A
     */
    public int getIdEjercicioProfesorMB_A() {
        return idEjercicioProfesorMB_A;
    }

    /**
     * @param idEjercicioProfesorMB_A the idEjercicioProfesorMB_A to set
     */
    public void setIdEjercicioProfesorMB_A(int idEjercicioProfesorMB_A) {
        this.idEjercicioProfesorMB_A = idEjercicioProfesorMB_A;
    }

    /**
     * @return the subtemaProfesor_idSubtemaProfesorMB_A
     */
    public int getSubtemaProfesor_idSubtemaProfesorMB_A() {
        return subtemaProfesor_idSubtemaProfesorMB_A;
    }

    /**
     * @param subtemaProfesor_idSubtemaProfesorMB_A the
     * subtemaProfesor_idSubtemaProfesorMB_A to set
     */
    public void setSubtemaProfesor_idSubtemaProfesorMB_A(int subtemaProfesor_idSubtemaProfesorMB_A) {
        this.subtemaProfesor_idSubtemaProfesorMB_A = subtemaProfesor_idSubtemaProfesorMB_A;
    }

    /**
     * @return the nombreMB_A
     */
    public String getNombreMB_A() {
        return nombreMB_A;
    }

    /**
     * @param nombreMB_A the nombreMB_A to set
     */
    public void setNombreMB_A(String nombreMB_A) {
        this.nombreMB_A = nombreMB_A;
    }

    /**
     * @return the descripcionMB_A
     */
    public String getDescripcionMB_A() {
        return descripcionMB_A;
    }

    /**
     * @param descripcionMB_A the descripcionMB_A to set
     */
    public void setDescripcionMB_A(String descripcionMB_A) {
        this.descripcionMB_A = descripcionMB_A;
    }

    /**
     * @return the sugerenciaMB_A
     */
    public String getSugerenciaMB_A() {
        return sugerenciaMB_A;
    }

    /**
     * @param sugerenciaMB_A the sugerenciaMB_A to set
     */
    public void setSugerenciaMB_A(String sugerenciaMB_A) {
        this.sugerenciaMB_A = sugerenciaMB_A;
    }

    /**
     * @return the codigoFuenteMB_A
     */
    public String getCodigoFuenteMB_A() {
        return codigoFuenteMB_A;
    }

    /**
     * @param codigoFuenteMB_A the codigoFuenteMB_A to set
     */
    public void setCodigoFuenteMB_A(String codigoFuenteMB_A) {
        this.codigoFuenteMB_A = codigoFuenteMB_A;
    }

    /**
     * @return the ejercicioX
     */
    public String getEjercicioX() {
        return ejercicioX;
    }

    /**
     * @param ejercicioX the ejercicioX to set
     */
    public void setEjercicioX(String ejercicioX) {
        this.ejercicioX = ejercicioX;
    }

    /**
     * @return the listEjercicioAlumno
     */
    public List<AlumnoWorkSpaceBeans> getListEjercicioAlumno() {
        return listEjercicioAlumno;
    }

    /**
     * @param listEjercicioAlumno the listEjercicioAlumno to set
     */
    public void setListEjercicioAlumno(List<AlumnoWorkSpaceBeans> listEjercicioAlumno) {
        this.listEjercicioAlumno = listEjercicioAlumno;
    }

    /**
     * @return the idejercicioAlumno_TAMB
     */
    public int getIdejercicioAlumno_TAMB() {
        return idejercicioAlumno_TAMB;
    }

    /**
     * @param idejercicioAlumno_TAMB the idejercicioAlumno_TAMB to set
     */
    public void setIdejercicioAlumno_TAMB(int idejercicioAlumno_TAMB) {
        this.idejercicioAlumno_TAMB = idejercicioAlumno_TAMB;
    }

    /**
     * @return the ejercicioProfesor_idejercicioProfesor_TAMB
     */
    public int getEjercicioProfesor_idejercicioProfesor_TAMB() {
        return ejercicioProfesor_idejercicioProfesor_TAMB;
    }

    /**
     * @param ejercicioProfesor_idejercicioProfesor_TAMB the
     * ejercicioProfesor_idejercicioProfesor_TAMB to set
     */
    public void setEjercicioProfesor_idejercicioProfesor_TAMB(int ejercicioProfesor_idejercicioProfesor_TAMB) {
        this.ejercicioProfesor_idejercicioProfesor_TAMB = ejercicioProfesor_idejercicioProfesor_TAMB;
    }

    /**
     * @return the casoPrueba_idcasoprueba_TAMB
     */
    public int getCasoPrueba_idcasoprueba_TAMB() {
        return casoPrueba_idcasoprueba_TAMB;
    }

    /**
     * @param casoPrueba_idcasoprueba_TAMB the casoPrueba_idcasoprueba_TAMB to
     * set
     */
    public void setCasoPrueba_idcasoprueba_TAMB(int casoPrueba_idcasoprueba_TAMB) {
        this.casoPrueba_idcasoprueba_TAMB = casoPrueba_idcasoprueba_TAMB;
    }

    /**
     * @return the usuarios_idusuarios_TAMB
     */
    public int getUsuarios_idusuarios_TAMB() {
        return usuarios_idusuarios_TAMB;
    }

    /**
     * @param usuarios_idusuarios_TAMB the usuarios_idusuarios_TAMB to set
     */
    public void setUsuarios_idusuarios_TAMB(int usuarios_idusuarios_TAMB) {
        this.usuarios_idusuarios_TAMB = usuarios_idusuarios_TAMB;
    }

    /**
     * @return the codigoFuente_TAMB
     */
    public String getCodigoFuente_TAMB() {
        return codigoFuente_TAMB;
    }

    /**
     * @param codigoFuente_TAMB the codigoFuente_TAMB to set
     */
    public void setCodigoFuente_TAMB(String codigoFuente_TAMB) {
        this.codigoFuente_TAMB = codigoFuente_TAMB;
    }

    /**
     * @return the estatus_TAMB
     */
    public String getEstatus_TAMB() {
        return estatus_TAMB;
    }

    /**
     * @param estatus_TAMB the estatus_TAMB to set
     */
    public void setEstatus_TAMB(String estatus_TAMB) {
        this.estatus_TAMB = estatus_TAMB;
    }

    /**
     * @return the fechaInicio_TAMB
     */
    public String getFechaInicio_TAMB() {
        return fechaInicio_TAMB;
    }

    /**
     * @param fechaInicio_TAMB the fechaInicio_TAMB to set
     */
    public void setFechaInicio_TAMB(String fechaInicio_TAMB) {
        this.fechaInicio_TAMB = fechaInicio_TAMB;
    }

    /**
     * @return the fechaFin_TAMB
     */
    public String getFechaFin_TAMB() {
        return fechaFin_TAMB;
    }

    /**
     * @param fechaFin_TAMB the fechaFin_TAMB to set
     */
    public void setFechaFin_TAMB(String fechaFin_TAMB) {
        this.fechaFin_TAMB = fechaFin_TAMB;
    }

    /**
     * @return the validarEjercicioExistente
     */
    public List<AlumnoWorkSpaceBeans> getValidarEjercicioExistente() {
        return validarEjercicioExistente;
    }

    /**
     * @param validarEjercicioExistente the validarEjercicioExistente to set
     */
    public void setValidarEjercicioExistente(List<AlumnoWorkSpaceBeans> validarEjercicioExistente) {
        this.validarEjercicioExistente = validarEjercicioExistente;
    }

    /**
     * @return the resultadoCompilador
     */
    public String getResultadoCompilador() {
        // resultadoCompilador = salidaString;
        return resultadoCompilador;
    }

    /**
     * @param resultadoCompilador the resultadoCompilador to set
     */
    public void setResultadoCompilador(String resultadoCompilador) {
        this.resultadoCompilador = resultadoCompilador;
    }

    /**
     * @return the idCasoPruebaMB
     */
    public int getIdCasoPruebaMB() {
        return idCasoPruebaMB;
    }

    /**
     * @param idCasoPruebaMB the idCasoPruebaMB to set
     */
    public void setIdCasoPruebaMB(int idCasoPruebaMB) {
        this.idCasoPruebaMB = idCasoPruebaMB;
    }

    /**
     * @return the ejercicioPro_idEjercicioProMB
     */
    public int getEjercicioPro_idEjercicioProMB() {
        return ejercicioPro_idEjercicioProMB;
    }

    /**
     * @param ejercicioPro_idEjercicioProMB the ejercicioPro_idEjercicioProMB to
     * set
     */
    public void setEjercicioPro_idEjercicioProMB(int ejercicioPro_idEjercicioProMB) {
        this.ejercicioPro_idEjercicioProMB = ejercicioPro_idEjercicioProMB;
    }

    /**
     * @return the resultadoMB
     */
    public String getResultadoMB() {
        return resultadoMB;
    }

    /**
     * @param resultadoMB the resultadoMB to set
     */
    public void setResultadoMB(String resultadoMB) {
        this.resultadoMB = resultadoMB;
    }

    /**
     * @return the listCasoPrueba
     */
    public List<AlumnoWorkSpaceBeans> getListCasoPrueba() {
        return listCasoPrueba;
    }

    /**
     * @param listCasoPrueba the listCasoPrueba to set
     */
    public void setListCasoPrueba(List<AlumnoWorkSpaceBeans> listCasoPrueba) {
        this.listCasoPrueba = listCasoPrueba;
    }

    /**
     * @return the codigoBody
     */
    public static String getCodigoBody() {
        return codigoBody;
    }

    /**
     * @param aCodigoBody the codigoBody to set
     */
    public static void setCodigoBody(String aCodigoBody) {
        codigoBody = aCodigoBody;
    }

    /**
     * @return the listParametros
     */
    public List<AlumnoWorkSpaceBeans> getListParametros() {
        return listParametros;
    }

    /**
     * @param listParametros the listParametros to set
     */
    public void setListParametros(List<AlumnoWorkSpaceBeans> listParametros) {
        this.listParametros = listParametros;
    }

    /**
     * @return the idParametrosMB
     */
    public int getIdParametrosMB() {
        return idParametrosMB;
    }

    /**
     * @param idParametrosMB the idParametrosMB to set
     */
    public void setIdParametrosMB(int idParametrosMB) {
        this.idParametrosMB = idParametrosMB;
    }

    /**
     * @return the casoPrueba_idCasoPruebaMB
     */
    public int getCasoPrueba_idCasoPruebaMB() {
        return casoPrueba_idCasoPruebaMB;
    }

    /**
     * @param casoPrueba_idCasoPruebaMB the casoPrueba_idCasoPruebaMB to set
     */
    public void setCasoPrueba_idCasoPruebaMB(int casoPrueba_idCasoPruebaMB) {
        this.casoPrueba_idCasoPruebaMB = casoPrueba_idCasoPruebaMB;
    }

    /**
     * @return the tipoMB
     */
    public String getTipoMB() {
        return tipoMB;
    }

    /**
     * @param tipoMB the tipoMB to set
     */
    public void setTipoMB(String tipoMB) {
        this.tipoMB = tipoMB;
    }

    /**
     * @return the nombreVariableMB
     */
    public String getNombreVariableMB() {
        return nombreVariableMB;
    }

    /**
     * @param nombreVariableMB the nombreVariableMB to set
     */
    public void setNombreVariableMB(String nombreVariableMB) {
        this.nombreVariableMB = nombreVariableMB;
    }

    /**
     * @return the valorMB
     */
    public String getValorMB() {
        return valorMB;
    }

    /**
     * @param valorMB the valorMB to set
     */
    public void setValorMB(String valorMB) {
        this.valorMB = valorMB;

    }

    /**
     * @return the listParametrosStatic
     */
    public static List<AlumnoWorkSpaceBeans> getListParametrosStatic() {
        return listParametrosStatic;
    }

    /**
     * @param aListParametrosStatic the listParametrosStatic to set
     */
    public static void setListParametrosStatic(List<AlumnoWorkSpaceBeans> aListParametrosStatic) {
        listParametrosStatic = aListParametrosStatic;
    }

    /**
     * @return the estadoValueStatic
     */
    public static String getEstadoValueStatic() {
        return estadoValueStatic;
    }

    /**
     * @param aEstadoValueStatic the estadoValueStatic to set
     */
    public static void setEstadoValueStatic(String aEstadoValueStatic) {
        estadoValueStatic = aEstadoValueStatic;
    }

    /**
     * @return the parametrosInt
     */
    public List<Integer> getParametrosInt() {
        return parametrosInt;
    }

    /**
     * @param parametrosInt the parametrosInt to set
     */
    public void setParametrosInt(List<Integer> parametrosInt) {
        this.parametrosInt = parametrosInt;
    }

    /**
     * @return the estadoValue
     */
    public String getEstadoValue() {
        return estadoValue;
    }

    /**
     * @param estadoValue the estadoValue to set
     */
    public void setEstadoValue(String estadoValue) {
        this.estadoValue = estadoValue;
    }

    /**
     * @return the tiempoTotal_TAMB
     */
    public String getTiempoTotal_TAMB() {
        return tiempoTotal_TAMB;
    }

    /**
     * @param tiempoTotal_TAMB the tiempoTotal_TAMB to set
     */
    public void setTiempoTotal_TAMB(String tiempoTotal_TAMB) {
        this.tiempoTotal_TAMB = tiempoTotal_TAMB;
    }

    /**
     * @return the ejercicioAlumnoX
     */
    public String getEjercicioAlumnoX() {
        return ejercicioAlumnoX;
    }

    /**
     * @param ejercicioAlumnoX the ejercicioAlumnoX to set
     */
    public void setEjercicioAlumnoX(String ejercicioAlumnoX) {
        this.ejercicioAlumnoX = ejercicioAlumnoX;
    }

    /**
     * @return the validarPruebasAlumnos
     */
    public List<AlumnoWorkSpaceBeans> getValidarPruebasAlumnos() {
        return validarPruebasAlumnos;
    }

    /**
     * @param validarPruebasAlumnos the validarPruebasAlumnos to set
     */
    public void setValidarPruebasAlumnos(List<AlumnoWorkSpaceBeans> validarPruebasAlumnos) {
        this.validarPruebasAlumnos = validarPruebasAlumnos;
    }

    /**
     * @return the listPruebasAlumnosSalida
     */
    public List<AlumnoWorkSpaceBeans> getListPruebasAlumnosSalida() {
        return listPruebasAlumnosSalida;
    }

    /**
     * @param listPruebasAlumnosSalida the listPruebasAlumnosSalida to set
     */
    public void setListPruebasAlumnosSalida(List<AlumnoWorkSpaceBeans> listPruebasAlumnosSalida) {
        this.listPruebasAlumnosSalida = listPruebasAlumnosSalida;
    }

    /**
     * @return the idEjercicioAlumnoX
     */
    public int getIdEjercicioAlumnoX() {
        return idEjercicioAlumnoX;
    }

    /**
     * @param idEjercicioAlumnoX the idEjercicioAlumnoX to set
     */
    public void setIdEjercicioAlumnoX(int idEjercicioAlumnoX) {
        this.idEjercicioAlumnoX = idEjercicioAlumnoX;
    }

    /**
     * @return the tiempoTotalMB_A
     */
    public String getTiempoTotalMB_A() {
        return tiempoTotalMB_A;
    }

    /**
     * @param tiempoTotalMB_A the tiempoTotalMB_A to set
     */
    public void setTiempoTotalMB_A(String tiempoTotalMB_A) {
        this.tiempoTotalMB_A = tiempoTotalMB_A;
    }

    /**
     * @return the intentosTotalMB_A
     */
    public int getIntentosTotalMB_A() {
        return intentosTotalMB_A;
    }

    /**
     * @param intentosTotalMB_A the intentosTotalMB_A to set
     */
    public void setIntentosTotalMB_A(int intentosTotalMB_A) {
        this.intentosTotalMB_A = intentosTotalMB_A;
    }

    /**
     * @return the intentosTotal_TAMB
     */
    public int getIntentosTotal_TAMB() {
        return intentosTotal_TAMB;
    }

    /**
     * @param intentosTotal_TAMB the intentosTotal_TAMB to set
     */
    public void setIntentosTotal_TAMB(int intentosTotal_TAMB) {
        this.intentosTotal_TAMB = intentosTotal_TAMB;
    }

    /**
     * @return the tiempoTotalX
     */
    public String getTiempoTotalX() {
        return tiempoTotalX;
    }

    /**
     * @param tiempoTotalX the tiempoTotalX to set
     */
    public void setTiempoTotalX(String tiempoTotalX) {
        this.tiempoTotalX = tiempoTotalX;
    }

    /**
     * @return the intentosTotalX
     */
    public String getIntentosTotalX() {
        return intentosTotalX;
    }

    /**
     * @param intentosTotalX the intentosTotalX to set
     */
    public void setIntentosTotalX(String intentosTotalX) {
        this.intentosTotalX = intentosTotalX;
    }

    /**
     * @return the tA_idCasoPruebaAlumno
     */
    public int gettA_idCasoPruebaAlumno() {
        return tA_idCasoPruebaAlumno;
    }

    /**
     * @param tA_idCasoPruebaAlumno the tA_idCasoPruebaAlumno to set
     */
    public void settA_idCasoPruebaAlumno(int tA_idCasoPruebaAlumno) {
        this.tA_idCasoPruebaAlumno = tA_idCasoPruebaAlumno;
    }

    /**
     * @return the tA_ejercicioAlumno_id
     */
    public int gettA_ejercicioAlumno_id() {
        return tA_ejercicioAlumno_id;
    }

    /**
     * @param tA_ejercicioAlumno_id the tA_ejercicioAlumno_id to set
     */
    public void settA_ejercicioAlumno_id(int tA_ejercicioAlumno_id) {
        this.tA_ejercicioAlumno_id = tA_ejercicioAlumno_id;
    }

    /**
     * @return the tA_casoPrueba_id
     */
    public int gettA_casoPrueba_id() {
        return tA_casoPrueba_id;
    }

    /**
     * @param tA_casoPrueba_id the tA_casoPrueba_id to set
     */
    public void settA_casoPrueba_id(int tA_casoPrueba_id) {
        this.tA_casoPrueba_id = tA_casoPrueba_id;
    }

    /**
     * @return the tA_usuario_id
     */
    public int gettA_usuario_id() {
        return tA_usuario_id;
    }

    /**
     * @param tA_usuario_id the tA_usuario_id to set
     */
    public void settA_usuario_id(int tA_usuario_id) {
        this.tA_usuario_id = tA_usuario_id;
    }

    /**
     * @return the tA_status
     */
    public String gettA_status() {
        return tA_status;
    }

    /**
     * @param tA_status the tA_status to set
     */
    public void settA_status(String tA_status) {
        this.tA_status = tA_status;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the disableBTNcalificar
     */
    public boolean isDisableBTNcalificar() {
        return disableBTNcalificar;
    }

    /**
     * @param disableBTNcalificar the disableBTNcalificar to set
     */
    public void setDisableBTNcalificar(boolean disableBTNcalificar) {
        this.disableBTNcalificar = disableBTNcalificar;
    }

    /**
     * @return the idClaseAlumnoX
     */
    public String getIdClaseAlumnoX() {
        return idClaseAlumnoX;
    }

    /**
     * @param idClaseAlumnoX the idClaseAlumnoX to set
     */
    public void setIdClaseAlumnoX(String idClaseAlumnoX) {
        this.idClaseAlumnoX = idClaseAlumnoX;
    }

    /**
     * @return the claseAlumno_idclaseAlumno_TAMB
     */
    public int getClaseAlumno_idclaseAlumno_TAMB() {
        return claseAlumno_idclaseAlumno_TAMB;
    }

    /**
     * @param claseAlumno_idclaseAlumno_TAMB the claseAlumno_idclaseAlumno_TAMB
     * to set
     */
    public void setClaseAlumno_idclaseAlumno_TAMB(int claseAlumno_idclaseAlumno_TAMB) {
        this.claseAlumno_idclaseAlumno_TAMB = claseAlumno_idclaseAlumno_TAMB;
    }

    /**
     * @return the tipoMB_A
     */
    public String getTipoMB_A() {
        return tipoMB_A;
    }

    /**
     * @param tipoMB_A the tipoMB_A to set
     */
    public void setTipoMB_A(String tipoMB_A) {
        this.tipoMB_A = tipoMB_A;
    }

    /**
     * @return the tipoX
     */
    public String getTipoX() {
        return tipoX;
    }

    /**
     * @param tipoX the tipoX to set
     */
    public void setTipoX(String tipoX) {
        this.tipoX = tipoX;
    }

    /**
     * @return the salidaString
     */
    public static String getSalidaString() {
//        System.out.println(salidaString);
        return salidaString;
    }

    /**
     * @param aSalidaString the salidaString to set
     */
    public static void setSalidaString(String aSalidaString) {
        salidaString = aSalidaString;
    }

    /**
     * @return the toFail
     */
    public static boolean isToFail() {
        return toFail;
    }

    /**
     * @param aToFail the toFail to set
     */
    public static void setToFail(boolean aToFail) {
        toFail = aToFail;
    }

    /**
     * @return the estadoFinal
     */
    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    /**
     * @param estadoFinal the estadoFinal to set
     */
    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    /**
     * @return the segundos
     */
    public int getSegundos() {
        return segundos;
    }

    /**
     * @return the ejercicioVarX
     */
    public String getEjercicioVarX() {
        return ejercicioVarX;
    }

    /**
     * @param ejercicioVarX the ejercicioVarX to set
     */
    public void setEjercicioVarX(String ejercicioVarX) {
        this.ejercicioVarX = ejercicioVarX;
    }

}
