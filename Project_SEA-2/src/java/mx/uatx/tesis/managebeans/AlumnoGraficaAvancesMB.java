/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.uatx.tesis.beans.AlumnoGraficaAvancesBeans;

import mx.uatx.tesis.daos.AlumnoGraficaAvancesDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
//@ViewScoped
public class AlumnoGraficaAvancesMB {

    //VARIABLES PARA LA SESSION
    private String claseAlumnoX;
    private String idUsuarioX;
    private HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    // TABLA EJERCICIO ALUMNO 
    private int g1MB_idejercicioAlumno;
    private int g1MB_ejercicioProfesor_idejercicioProfesor;
    private int g1MB_usuarios_idusuarios;
    private int g1MB_claseAlumno_idclaseAlumno;
    private String g1MB_fechaInicio_a;
    private String g1MB_fechaFinal_a;
    private int g1MB_tiempoTotal_a;
    private int g1MB_intentosTotal_a;
    //TABLA EJERCICIO PROFESOR
    private int g2MB_idejercicioProfesor;
    private String g2MB_nombre;
    private int g2MB_tiempoTotal;
    private int g2MB_intentosTotal;

    private BarChartModel barModel;
    private BarChartModel barModelTiempo;

    private CartesianChartModel model;
    private CartesianChartModel modelTiempo;
    private CartesianChartModel modelIntentos;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private HorizontalBarChartModel horizontalBarModel;
    private List<AlumnoGraficaAvancesBeans> listGrafica1 = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> listNombresEjercicio = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> listAlumnoPDF = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> ListTemasAlumno = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> ListSubTemasAlumno = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> ListEjericioAlumno = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalEjercicio = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalEjercicioPass = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> nombreProfesor = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalTema = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalSubtema = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalTemaPass = new ArrayList<AlumnoGraficaAvancesBeans>();
    private List<AlumnoGraficaAvancesBeans> totalSubtemaPass = new ArrayList<AlumnoGraficaAvancesBeans>();

    private String totalEjer;
    private String totalEjerPass;
    private String nombreProfesorX;

    /**
     * Creates a new instance of AlumnoGraficaAvancesMB
     */
    public AlumnoGraficaAvancesMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        downSession1();
        downSession2();
        downClaseAlumno();
        createBarModelsIntentos();
        createBarModelsTiempo();

        createLinearModel();
        cargarlistaPDF();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createBarModelsIntentos() {
        createBarModelIntentos();

    }

    private void createBarModelsTiempo() {

        createBarModelTiempo();

    }

    private void createLinearModel() {
        model = new CartesianChartModel();
        model.addSeries(getStockChartData("Alumno"));
    }

    private ChartSeries getStockChartData(String label) {
        BubbleChartModel model = new BubbleChartModel();
        //AvanceProfesorDAO grafica1 = new AvanceProfesorDAO();
        AlumnoGraficaAvancesDAO grafica = new AlumnoGraficaAvancesDAO();

        int idClase = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());
        ChartSeries data = new ChartSeries();
        data.setLabel(label);
        setListGrafica1(grafica.retriveTableAlumnos1(idClase, idUsuario));

        int e = 0;

        for (int i = 0; i < getListGrafica1().size(); i++) {
            AlumnoGraficaAvancesBeans grafica11 = (AlumnoGraficaAvancesBeans) getListGrafica1().get(i);

            setG1MB_idejercicioAlumno(grafica11.getG1_idejercicioAlumno());
            setG1MB_ejercicioProfesor_idejercicioProfesor(grafica11.getG1_ejercicioProfesor_idejercicioProfesor());
            setG1MB_usuarios_idusuarios(grafica11.getG1_usuarios_idusuarios());
            setG1MB_claseAlumno_idclaseAlumno(grafica11.getG1_claseAlumno_idclaseAlumno());
            setG1MB_fechaInicio_a(grafica11.getG1_fechaInicio_a());
            setG1MB_fechaFinal_a(grafica11.getG1_fechaFinal_a());
            setG1MB_tiempoTotal_a(grafica11.getG1_tiempoTotal_a());
            setG1MB_intentosTotal_a(grafica11.getG1_intentosTotal_a());

            setListNombresEjercicio(grafica.retriveTableAlumnos2(getG1MB_ejercicioProfesor_idejercicioProfesor()));
            for (int j = 0; j < getListNombresEjercicio().size(); j++) {
                AlumnoGraficaAvancesBeans grafica111 = (AlumnoGraficaAvancesBeans) getListNombresEjercicio().get(j);
                setG2MB_idejercicioProfesor(grafica111.getG2_idejercicioProfesor());
                setG2MB_nombre(grafica111.getG2_nombre());
                setG2MB_tiempoTotal(grafica111.getG2_tiempoTotal());
                setG2MB_intentosTotal(grafica111.getG2_intentosTotal());

                try {
                    Date fechaMayor = formatter.parse(getG1MB_fechaInicio_a());
                    Date fechaMenor = formatter.parse(getG1MB_fechaFinal_a());
                    long diferencia_1 = fechaMayor.getTime();
                    long diferencia_2 = fechaMenor.getTime();
                    Long ms = diferencia_2 - diferencia_1;
                    long dias = ms / (1000 * 60 * 60 * 24);
                   

                    data.getData().put(getG2MB_nombre(), (int) dias);

                } catch (ParseException er) {
                    er.printStackTrace();
                }
            }

        }
        return data;
    }

    private void createBarModelIntentos() {
        modelIntentos = new CartesianChartModel();
        modelIntentos.addSeries(getStockChartDataIntentosProfesor("PROFESOR"));

        modelIntentos.addSeries(getStockChartDataIntentosAlumno("ALUMNO"));

    }

    private void createBarModelTiempo() {

        modelTiempo = new CartesianChartModel();
        modelTiempo.addSeries(getStockChartDataTiempoProfesor("PROFESOR"));

        modelTiempo.addSeries(getStockChartDataTiempoAlumno("ALUMNO"));
    }

    ChartSeries getStockChartDataIntentosProfesor(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AlumnoGraficaAvancesDAO grafica1 = new AlumnoGraficaAvancesDAO();
        int idClase = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());
        setListGrafica1(grafica1.retriveTableAlumnos1(idClase, idUsuario));
        for (int i = 0; i < getListGrafica1().size(); i++) {
            AlumnoGraficaAvancesBeans grafica11 = (AlumnoGraficaAvancesBeans) getListGrafica1().get(i);

            setG1MB_idejercicioAlumno(grafica11.getG1_idejercicioAlumno());
            setG1MB_ejercicioProfesor_idejercicioProfesor(grafica11.getG1_ejercicioProfesor_idejercicioProfesor());
            setG1MB_usuarios_idusuarios(grafica11.getG1_usuarios_idusuarios());
            setG1MB_claseAlumno_idclaseAlumno(grafica11.getG1_claseAlumno_idclaseAlumno());
            setG1MB_fechaInicio_a(grafica11.getG1_fechaInicio_a());
            setG1MB_fechaFinal_a(grafica11.getG1_fechaFinal_a());
            setG1MB_tiempoTotal_a(grafica11.getG1_tiempoTotal_a());
            setG1MB_intentosTotal_a(grafica11.getG1_intentosTotal_a());

            setListNombresEjercicio(grafica1.retriveTableAlumnos2(getG1MB_ejercicioProfesor_idejercicioProfesor()));
            for (int j = 0; j < getListNombresEjercicio().size(); j++) {
                AlumnoGraficaAvancesBeans grafica111 = (AlumnoGraficaAvancesBeans) getListNombresEjercicio().get(j);
                setG2MB_idejercicioProfesor(grafica111.getG2_idejercicioProfesor());
                setG2MB_nombre(grafica111.getG2_nombre());
                setG2MB_tiempoTotal(grafica111.getG2_tiempoTotal());
                setG2MB_intentosTotal(grafica111.getG2_intentosTotal());
                //boys.set(getG2MB_nombre(), getG2MB_intentosTotal());
                data.getData().put(getG2MB_nombre(), getG2MB_intentosTotal());
            }
        }
        return data;
    }

    ChartSeries getStockChartDataIntentosAlumno(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AlumnoGraficaAvancesDAO grafica1 = new AlumnoGraficaAvancesDAO();
        int idClase = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());

        setListGrafica1(grafica1.retriveTableAlumnos1(idClase, idUsuario));
        for (int i = 0; i < getListGrafica1().size(); i++) {
            AlumnoGraficaAvancesBeans grafica11 = (AlumnoGraficaAvancesBeans) getListGrafica1().get(i);

            setG1MB_idejercicioAlumno(grafica11.getG1_idejercicioAlumno());
            setG1MB_ejercicioProfesor_idejercicioProfesor(grafica11.getG1_ejercicioProfesor_idejercicioProfesor());
            setG1MB_usuarios_idusuarios(grafica11.getG1_usuarios_idusuarios());
            setG1MB_claseAlumno_idclaseAlumno(grafica11.getG1_claseAlumno_idclaseAlumno());
            setG1MB_fechaInicio_a(grafica11.getG1_fechaInicio_a());
            setG1MB_fechaFinal_a(grafica11.getG1_fechaFinal_a());
            setG1MB_tiempoTotal_a(grafica11.getG1_tiempoTotal_a());
            setG1MB_intentosTotal_a(grafica11.getG1_intentosTotal_a());

            setListNombresEjercicio(grafica1.retriveTableAlumnos2(getG1MB_ejercicioProfesor_idejercicioProfesor()));
            for (int j = 0; j < getListNombresEjercicio().size(); j++) {
                AlumnoGraficaAvancesBeans grafica111 = (AlumnoGraficaAvancesBeans) getListNombresEjercicio().get(j);
                setG2MB_idejercicioProfesor(grafica111.getG2_idejercicioProfesor());
                setG2MB_nombre(grafica111.getG2_nombre());
                setG2MB_tiempoTotal(grafica111.getG2_tiempoTotal());
                setG2MB_intentosTotal(grafica111.getG2_intentosTotal());

                data.getData().put(getG2MB_nombre(), getG1MB_intentosTotal_a());
            }
        }

        return data;
    }

    ChartSeries getStockChartDataTiempoProfesor(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AlumnoGraficaAvancesDAO grafica1 = new AlumnoGraficaAvancesDAO();
        int idClase = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());
        setListGrafica1(grafica1.retriveTableAlumnos1(idClase, idUsuario));
        for (int i = 0; i < getListGrafica1().size(); i++) {
            AlumnoGraficaAvancesBeans grafica11 = (AlumnoGraficaAvancesBeans) getListGrafica1().get(i);

            setG1MB_idejercicioAlumno(grafica11.getG1_idejercicioAlumno());
            setG1MB_ejercicioProfesor_idejercicioProfesor(grafica11.getG1_ejercicioProfesor_idejercicioProfesor());
            setG1MB_usuarios_idusuarios(grafica11.getG1_usuarios_idusuarios());
            setG1MB_claseAlumno_idclaseAlumno(grafica11.getG1_claseAlumno_idclaseAlumno());
            setG1MB_fechaInicio_a(grafica11.getG1_fechaInicio_a());
            setG1MB_fechaFinal_a(grafica11.getG1_fechaFinal_a());
            setG1MB_tiempoTotal_a(grafica11.getG1_tiempoTotal_a());
            setG1MB_intentosTotal_a(grafica11.getG1_intentosTotal_a());

            setListNombresEjercicio(grafica1.retriveTableAlumnos2(getG1MB_ejercicioProfesor_idejercicioProfesor()));
            for (int j = 0; j < getListNombresEjercicio().size(); j++) {
                AlumnoGraficaAvancesBeans grafica111 = (AlumnoGraficaAvancesBeans) getListNombresEjercicio().get(j);
                setG2MB_idejercicioProfesor(grafica111.getG2_idejercicioProfesor());
                setG2MB_nombre(grafica111.getG2_nombre());
                setG2MB_tiempoTotal(grafica111.getG2_tiempoTotal());
                setG2MB_intentosTotal(grafica111.getG2_intentosTotal());

                data.getData().put(getG2MB_nombre(), getG2MB_tiempoTotal());
            }
        }

        return data;
    }

    ChartSeries getStockChartDataTiempoAlumno(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AlumnoGraficaAvancesDAO grafica1 = new AlumnoGraficaAvancesDAO();
        int idClase = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());

        setListGrafica1(grafica1.retriveTableAlumnos1(idClase, idUsuario));
        for (int i = 0; i < getListGrafica1().size(); i++) {
            AlumnoGraficaAvancesBeans grafica11 = (AlumnoGraficaAvancesBeans) getListGrafica1().get(i);

            setG1MB_idejercicioAlumno(grafica11.getG1_idejercicioAlumno());
            setG1MB_ejercicioProfesor_idejercicioProfesor(grafica11.getG1_ejercicioProfesor_idejercicioProfesor());
            setG1MB_usuarios_idusuarios(grafica11.getG1_usuarios_idusuarios());
            setG1MB_claseAlumno_idclaseAlumno(grafica11.getG1_claseAlumno_idclaseAlumno());
            setG1MB_fechaInicio_a(grafica11.getG1_fechaInicio_a());
            setG1MB_fechaFinal_a(grafica11.getG1_fechaFinal_a());
            setG1MB_tiempoTotal_a(grafica11.getG1_tiempoTotal_a());
            setG1MB_intentosTotal_a(grafica11.getG1_intentosTotal_a());

            setListNombresEjercicio(grafica1.retriveTableAlumnos2(getG1MB_ejercicioProfesor_idejercicioProfesor()));
            for (int j = 0; j < getListNombresEjercicio().size(); j++) {
                AlumnoGraficaAvancesBeans grafica111 = (AlumnoGraficaAvancesBeans) getListNombresEjercicio().get(j);
                setG2MB_idejercicioProfesor(grafica111.getG2_idejercicioProfesor());
                setG2MB_nombre(grafica111.getG2_nombre());
                setG2MB_tiempoTotal(grafica111.getG2_tiempoTotal());
                setG2MB_intentosTotal(grafica111.getG2_intentosTotal());

                data.getData().put(getG2MB_nombre(), getG1MB_tiempoTotal_a());
            }
        }

        return data;

    }

    public void cargarlistaPDF() {

        int idClase2 = Integer.parseInt(downClaseAlumno());
        int idUsuario = Integer.parseInt(downSession2());
        AlumnoGraficaAvancesDAO listParamPDF = new AlumnoGraficaAvancesDAO();
        setListAlumnoPDF(listParamPDF.retriveClasePDF(idClase2, idUsuario));
        setListTemasAlumno(listParamPDF.retriveTemaPDF(idClase2));
        setListSubTemasAlumno(listParamPDF.retriveSubtemaPDF(idClase2));
        setListEjericioAlumno(listParamPDF.retriveEjercicioPDF(idClase2));
        setTotalEjercicio(listParamPDF.totalEjerciciosPDF(idClase2));
        setTotalEjercicioPass(listParamPDF.totalEjerciciosPassPDF(idClase2, idUsuario));
        setNombreProfesor(listParamPDF.nombreProfesorPDF(idClase2));

    }

    public int totalEjercicioX() {

        for (int i = 0; i < getTotalEjercicio().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalEjercicio().get(i);
            setTotalEjer(inscritosBeans.getTotalEjercicio());

        }
        int total = Integer.parseInt(getTotalEjer());
        return total;
    }

    public int totalEjercicioPassX() {

        for (int i = 0; i < getTotalEjercicioPass().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalEjercicioPass().get(i);
            setTotalEjerPass(inscritosBeans.getTotalPass());

        }
        int total = Integer.parseInt(getTotalEjerPass());
        return total;
    }

    public String nombreProfesorX() {

        for (int i = 0; i < getNombreProfesor().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getNombreProfesor().get(i);
            setNombreProfesorX(inscritosBeans.getNombreProfesor() + " " + inscritosBeans.getPaternoProfesor() + " " + inscritosBeans.getMaternoProfesor());

        }

        return getNombreProfesorX();
    }

    public void verClasePDF() throws Exception {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            int var1 = totalEjercicioX();
            int var2 = totalEjercicioPassX();
            int totalFail3 = var1 - var2;

            int promedio = (var2 * 100) / var1;
            Map parametros = new HashMap();
            parametros.put("nombreProfesor", nombreProfesorX());
            parametros.put("totalEjercicioP", "" + var1);
            parametros.put("totalEjercicioOK", "" + var2);
            parametros.put("totalEjericiosFAIL", "" + totalFail3);
            parametros.put("promedio", "" + promedio);

            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, new JRBeanCollectionDataSource(this.getListAlumnoPDF()));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateReportSubmit() throws ClassNotFoundException, SQLException, IOException, JRException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        response.setContentType("application/pdf");

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\report123.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("txtUsuario", "Alfonso");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(this.getListAlumnoPDF()));

            response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public void verTemaPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        //llena el cuerpo del pdf
        int total = 0;
        int totalPass = 0;
        int idClase = Integer.parseInt(downClaseAlumno());
        String claseAlumnoEliminar = params.get("idTema");
        int idTema = Integer.parseInt(claseAlumnoEliminar);
        String clase = downSession2();
        int idUsuario = Integer.parseInt(clase);
        AlumnoGraficaAvancesDAO listParamPDF = new AlumnoGraficaAvancesDAO();
        setListTemasAlumno(listParamPDF.retriveListTemasPDF(idUsuario, idTema));
        // llenar los parametros del pdf
        setTotalTema(listParamPDF.totalTemas(idTema));
        for (int i = 0; i < getTotalTema().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalTema().get(i);
            setTotalEjer(inscritosBeans.getTotalPass());
            total = Integer.parseInt(inscritosBeans.getTotalPass());

        }

        setTotalTemaPass(listParamPDF.totalTemasPass(idUsuario, idClase, idTema));
        for (int i = 0; i < getTotalTema().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalTema().get(i);
            totalPass = Integer.parseInt(inscritosBeans.getTotalPass());

        }

        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", nombreProfesorX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
            parametros.put("promedio", "" + promedio);

            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, new JRBeanCollectionDataSource(this.getListTemasAlumno()));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verSubtemaPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        int total = 0;
        int totalPass = 0;
        String claseAlumnoEliminar = params.get("idSubtema");
        int idSubTema = Integer.parseInt(claseAlumnoEliminar);
        String clase = downSession2();
        int idClase = Integer.parseInt(clase);
        AlumnoGraficaAvancesDAO listParamPDF = new AlumnoGraficaAvancesDAO();
        setListSubTemasAlumno(listParamPDF.retriveListSubtemasPDF(idClase, idSubTema)); // ejercicios del alumno por subtema
        setTotalSubtema(listParamPDF.SubtemasTotal(idSubTema)); // total de ejercicios en el subtema         
        for (int i = 0; i < getTotalSubtema().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalSubtema().get(i);

            total = Integer.parseInt(inscritosBeans.getTotalPass());

        }
        setTotalSubtemaPass(listParamPDF.SubtemasTotalPass(idSubTema, idClase));// Total de ejercicios pass en el subtema
        for (int i = 0; i < getTotalSubtemaPass().size(); i++) {
            AlumnoGraficaAvancesBeans inscritosBeans = (AlumnoGraficaAvancesBeans) getTotalSubtemaPass().get(i);
            totalPass = Integer.parseInt(inscritosBeans.getTotalPass());

        }

        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", nombreProfesorX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
            parametros.put("promedio", "" + promedio);

            byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, new JRBeanCollectionDataSource(this.getListSubTemasAlumno()));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verEjercicioPDF() throws Exception {
        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        String claseAlumnoEliminar = params.get("idEjercicio");
        int idClase = Integer.parseInt(claseAlumnoEliminar);
        String usuario = downSession2();
        int idUsuario = Integer.parseInt(usuario);

        AlumnoGraficaAvancesDAO listParamPDF = new AlumnoGraficaAvancesDAO();
        setListEjericioAlumno(listParamPDF.retriveListEjercicioPDF(idClase, idUsuario));

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxClase.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(reporte, null, new JRBeanCollectionDataSource(this.getListEjericioAlumno()));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void redirectToGraficasAlumnos() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_GraficaAvances.xhtml");
        
    }

    public String downSession1() {
        if (getHttpServletRequest().getSession().getAttribute("sessionIDRerirectClase") != null) {
            setClaseAlumnoX(this.getHttpServletRequest().getSession().getAttribute("sessionIDRerirectClase").toString());

        }
        String idClase = getClaseAlumnoX();

        return idClase;
    }

    public String downSession2() {
        if (getHttpServletRequest().getSession().getAttribute("sessionIDUsuario") != null) {
            setIdUsuarioX(this.getHttpServletRequest().getSession().getAttribute("sessionIDUsuario").toString());

        }
        String idClase = getIdUsuarioX();

        return idClase;
    }

    public String downClaseAlumno() {
        if (getHttpServletRequest().getSession().getAttribute("sessionUPclaseAlumno") != null) {
            setClaseAlumnoX(this.getHttpServletRequest().getSession().getAttribute("sessionUPclaseAlumno").toString());

        }
        String idClase = getClaseAlumnoX();

        return idClase;
    }

    /**
     * @return the claseAlumnoX
     */
    public String getClaseAlumnoX() {
        return claseAlumnoX;
    }

    /**
     * @param claseAlumnoX the claseAlumnoX to set
     */
    public void setClaseAlumnoX(String claseAlumnoX) {
        this.claseAlumnoX = claseAlumnoX;
    }

    /**
     * @return the idUsuarioX
     */
    public String getIdUsuarioX() {
        return idUsuarioX;
    }

    /**
     * @param idUsuarioX the idUsuarioX to set
     */
    public void setIdUsuarioX(String idUsuarioX) {
        this.idUsuarioX = idUsuarioX;
    }

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the faceContext
     */
    public FacesContext getFaceContext() {
        return faceContext;
    }

    /**
     * @return the facesMessage
     */
    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    /**
     * @param facesMessage the facesMessage to set
     */
    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }

    /**
     * @return the g1MB_idejercicioAlumno
     */
    public int getG1MB_idejercicioAlumno() {
        return g1MB_idejercicioAlumno;
    }

    /**
     * @param g1MB_idejercicioAlumno the g1MB_idejercicioAlumno to set
     */
    public void setG1MB_idejercicioAlumno(int g1MB_idejercicioAlumno) {
        this.g1MB_idejercicioAlumno = g1MB_idejercicioAlumno;
    }

    /**
     * @return the g1MB_ejercicioProfesor_idejercicioProfesor
     */
    public int getG1MB_ejercicioProfesor_idejercicioProfesor() {
        return g1MB_ejercicioProfesor_idejercicioProfesor;
    }

    /**
     * @param g1MB_ejercicioProfesor_idejercicioProfesor the
     * g1MB_ejercicioProfesor_idejercicioProfesor to set
     */
    public void setG1MB_ejercicioProfesor_idejercicioProfesor(int g1MB_ejercicioProfesor_idejercicioProfesor) {
        this.g1MB_ejercicioProfesor_idejercicioProfesor = g1MB_ejercicioProfesor_idejercicioProfesor;
    }

    /**
     * @return the g1MB_usuarios_idusuarios
     */
    public int getG1MB_usuarios_idusuarios() {
        return g1MB_usuarios_idusuarios;
    }

    /**
     * @param g1MB_usuarios_idusuarios the g1MB_usuarios_idusuarios to set
     */
    public void setG1MB_usuarios_idusuarios(int g1MB_usuarios_idusuarios) {
        this.g1MB_usuarios_idusuarios = g1MB_usuarios_idusuarios;
    }

    /**
     * @return the g1MB_claseAlumno_idclaseAlumno
     */
    public int getG1MB_claseAlumno_idclaseAlumno() {
        return g1MB_claseAlumno_idclaseAlumno;
    }

    /**
     * @param g1MB_claseAlumno_idclaseAlumno the g1MB_claseAlumno_idclaseAlumno
     * to set
     */
    public void setG1MB_claseAlumno_idclaseAlumno(int g1MB_claseAlumno_idclaseAlumno) {
        this.g1MB_claseAlumno_idclaseAlumno = g1MB_claseAlumno_idclaseAlumno;
    }

    /**
     * @return the g1MB_fechaInicio_a
     */
    public String getG1MB_fechaInicio_a() {
        return g1MB_fechaInicio_a;
    }

    /**
     * @param g1MB_fechaInicio_a the g1MB_fechaInicio_a to set
     */
    public void setG1MB_fechaInicio_a(String g1MB_fechaInicio_a) {
        this.g1MB_fechaInicio_a = g1MB_fechaInicio_a;
    }

    /**
     * @return the g1MB_fechaFinal_a
     */
    public String getG1MB_fechaFinal_a() {
        return g1MB_fechaFinal_a;
    }

    /**
     * @param g1MB_fechaFinal_a the g1MB_fechaFinal_a to set
     */
    public void setG1MB_fechaFinal_a(String g1MB_fechaFinal_a) {
        this.g1MB_fechaFinal_a = g1MB_fechaFinal_a;
    }

    /**
     * @return the g1MB_tiempoTotal_a
     */
    public int getG1MB_tiempoTotal_a() {
        return g1MB_tiempoTotal_a;
    }

    /**
     * @param g1MB_tiempoTotal_a the g1MB_tiempoTotal_a to set
     */
    public void setG1MB_tiempoTotal_a(int g1MB_tiempoTotal_a) {
        this.g1MB_tiempoTotal_a = g1MB_tiempoTotal_a;
    }

    /**
     * @return the g1MB_intentosTotal_a
     */
    public int getG1MB_intentosTotal_a() {
        return g1MB_intentosTotal_a;
    }

    /**
     * @param g1MB_intentosTotal_a the g1MB_intentosTotal_a to set
     */
    public void setG1MB_intentosTotal_a(int g1MB_intentosTotal_a) {
        this.g1MB_intentosTotal_a = g1MB_intentosTotal_a;
    }

    /**
     * @return the listGrafica1
     */
    public List<AlumnoGraficaAvancesBeans> getListGrafica1() {
        return listGrafica1;
    }

    /**
     * @param listGrafica1 the listGrafica1 to set
     */
    public void setListGrafica1(List<AlumnoGraficaAvancesBeans> listGrafica1) {
        this.listGrafica1 = listGrafica1;
    }

    /**
     * @return the listNombresEjercicio
     */
    public List<AlumnoGraficaAvancesBeans> getListNombresEjercicio() {
        return listNombresEjercicio;
    }

    /**
     * @param listNombresEjercicio the listNombresEjercicio to set
     */
    public void setListNombresEjercicio(List<AlumnoGraficaAvancesBeans> listNombresEjercicio) {
        this.listNombresEjercicio = listNombresEjercicio;
    }

    /**
     * @return the g2MB_idejercicioProfesor
     */
    public int getG2MB_idejercicioProfesor() {
        return g2MB_idejercicioProfesor;
    }

    /**
     * @param g2MB_idejercicioProfesor the g2MB_idejercicioProfesor to set
     */
    public void setG2MB_idejercicioProfesor(int g2MB_idejercicioProfesor) {
        this.g2MB_idejercicioProfesor = g2MB_idejercicioProfesor;
    }

    /**
     * @return the g2MB_nombre
     */
    public String getG2MB_nombre() {
        return g2MB_nombre;
    }

    /**
     * @param g2MB_nombre the g2MB_nombre to set
     */
    public void setG2MB_nombre(String g2MB_nombre) {
        this.g2MB_nombre = g2MB_nombre;
    }

    /**
     * @return the g2MB_tiempoTotal
     */
    public int getG2MB_tiempoTotal() {
        return g2MB_tiempoTotal;
    }

    /**
     * @param g2MB_tiempoTotal the g2MB_tiempoTotal to set
     */
    public void setG2MB_tiempoTotal(int g2MB_tiempoTotal) {
        this.g2MB_tiempoTotal = g2MB_tiempoTotal;
    }

    /**
     * @return the g2MB_intentosTotal
     */
    public int getG2MB_intentosTotal() {
        return g2MB_intentosTotal;
    }

    /**
     * @param g2MB_intentosTotal the g2MB_intentosTotal to set
     */
    public void setG2MB_intentosTotal(int g2MB_intentosTotal) {
        this.g2MB_intentosTotal = g2MB_intentosTotal;
    }

    /**
     * @return the barModelTiempo
     */
    public BarChartModel getBarModelTiempo() {
        return barModelTiempo;
    }

    public CartesianChartModel getLinearModel() {
        return model;
    }

    public CartesianChartModel getLinearModelTiempo() {
        return modelTiempo;
    }

    public CartesianChartModel getLinearModelIntentos() {
        return modelIntentos;
    }

    /**
     * @return the listAlumnoPDF
     */
    public List<AlumnoGraficaAvancesBeans> getListAlumnoPDF() {
        return listAlumnoPDF;
    }

    /**
     * @param listAlumnoPDF the listAlumnoPDF to set
     */
    public void setListAlumnoPDF(List<AlumnoGraficaAvancesBeans> listAlumnoPDF) {
        this.listAlumnoPDF = listAlumnoPDF;
    }

    /**
     * @return the ListTemasAlumno
     */
    public List<AlumnoGraficaAvancesBeans> getListTemasAlumno() {
        return ListTemasAlumno;
    }

    /**
     * @param ListTemasAlumno the ListTemasAlumno to set
     */
    public void setListTemasAlumno(List<AlumnoGraficaAvancesBeans> ListTemasAlumno) {
        this.ListTemasAlumno = ListTemasAlumno;
    }

    /**
     * @return the ListSubTemasAlumno
     */
    public List<AlumnoGraficaAvancesBeans> getListSubTemasAlumno() {
        return ListSubTemasAlumno;
    }

    /**
     * @param ListSubTemasAlumno the ListSubTemasAlumno to set
     */
    public void setListSubTemasAlumno(List<AlumnoGraficaAvancesBeans> ListSubTemasAlumno) {
        this.ListSubTemasAlumno = ListSubTemasAlumno;
    }

    /**
     * @return the ListEjericioAlumno
     */
    public List<AlumnoGraficaAvancesBeans> getListEjericioAlumno() {
        return ListEjericioAlumno;
    }

    /**
     * @param ListEjericioAlumno the ListEjericioAlumno to set
     */
    public void setListEjericioAlumno(List<AlumnoGraficaAvancesBeans> ListEjericioAlumno) {
        this.ListEjericioAlumno = ListEjericioAlumno;
    }

    /**
     * @return the totalEjercicio
     */
    public List<AlumnoGraficaAvancesBeans> getTotalEjercicio() {
        return totalEjercicio;
    }

    /**
     * @param totalEjercicio the totalEjercicio to set
     */
    public void setTotalEjercicio(List<AlumnoGraficaAvancesBeans> totalEjercicio) {
        this.totalEjercicio = totalEjercicio;
    }

    /**
     * @return the totalEjercicioPass
     */
    public List<AlumnoGraficaAvancesBeans> getTotalEjercicioPass() {
        return totalEjercicioPass;
    }

    /**
     * @param totalEjercicioPass the totalEjercicioPass to set
     */
    public void setTotalEjercicioPass(List<AlumnoGraficaAvancesBeans> totalEjercicioPass) {
        this.totalEjercicioPass = totalEjercicioPass;
    }

    /**
     * @return the totalEjer
     */
    public String getTotalEjer() {
        return totalEjer;
    }

    /**
     * @param totalEjer the totalEjer to set
     */
    public void setTotalEjer(String totalEjer) {
        this.totalEjer = totalEjer;
    }

    /**
     * @return the totalEjerPass
     */
    public String getTotalEjerPass() {
        return totalEjerPass;
    }

    /**
     * @param totalEjerPass the totalEjerPass to set
     */
    public void setTotalEjerPass(String totalEjerPass) {
        this.totalEjerPass = totalEjerPass;
    }

    /**
     * @return the nombreProfesor
     */
    public List<AlumnoGraficaAvancesBeans> getNombreProfesor() {
        return nombreProfesor;
    }

    /**
     * @param nombreProfesor the nombreProfesor to set
     */
    public void setNombreProfesor(List<AlumnoGraficaAvancesBeans> nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    /**
     * @return the nombreProfesorX
     */
    public String getNombreProfesorX() {
        return nombreProfesorX;
    }

    /**
     * @param nombreProfesorX the nombreProfesorX to set
     */
    public void setNombreProfesorX(String nombreProfesorX) {
        this.nombreProfesorX = nombreProfesorX;
    }

    /**
     * @return the totalTema
     */
    public List<AlumnoGraficaAvancesBeans> getTotalTema() {
        return totalTema;
    }

    /**
     * @param totalTema the totalTema to set
     */
    public void setTotalTema(List<AlumnoGraficaAvancesBeans> totalTema) {
        this.totalTema = totalTema;
    }

    /**
     * @return the totalSubtema
     */
    public List<AlumnoGraficaAvancesBeans> getTotalSubtema() {
        return totalSubtema;
    }

    /**
     * @param totalSubtema the totalSubtema to set
     */
    public void setTotalSubtema(List<AlumnoGraficaAvancesBeans> totalSubtema) {
        this.totalSubtema = totalSubtema;
    }

    /**
     * @return the totalTemaPass
     */
    public List<AlumnoGraficaAvancesBeans> getTotalTemaPass() {
        return totalTemaPass;
    }

    /**
     * @param totalTemaPass the totalTemaPass to set
     */
    public void setTotalTemaPass(List<AlumnoGraficaAvancesBeans> totalTemaPass) {
        this.totalTemaPass = totalTemaPass;
    }

    /**
     * @return the totalSubtemaPass
     */
    public List<AlumnoGraficaAvancesBeans> getTotalSubtemaPass() {
        return totalSubtemaPass;
    }

    /**
     * @param totalSubtemaPass the totalSubtemaPass to set
     */
    public void setTotalSubtemaPass(List<AlumnoGraficaAvancesBeans> totalSubtemaPass) {
        this.totalSubtemaPass = totalSubtemaPass;
    }

}
