/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.ProfesorReporteBeans;
import mx.uatx.tesis.daos.ProfesorReporteDAO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class ProfesorReporteMB {

    //VARIABLES PARA LA SESSION
    private String claseXX;
    private String temaXX;
    private String nombreProfesorXX;
    private String idClassUser;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    private List<ProfesorReporteBeans> listAlumnoPDF = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> listTema = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> listSubtema = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> listEjercicio = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> listAlumnos = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> listNombreProfesor = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> ejercicioTotal = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> ejercicioTotalPass = new ArrayList<ProfesorReporteBeans>();

    private List<ProfesorReporteBeans> totalEjercicio = new ArrayList<ProfesorReporteBeans>();
    private List<ProfesorReporteBeans> totalEjercicioPass = new ArrayList<ProfesorReporteBeans>();

    /**
     * Creates a new instance of ProfesorReporteMB
     */
    public ProfesorReporteMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        downSession1();
        downSession2();
        downSessionClassUser();

        cargarlistaPDF();
    }

    public String downSession1() {
        if (httpServletRequest.getSession().getAttribute("sessionIDClase") != null) {
            setClaseXX(this.httpServletRequest.getSession().getAttribute("sessionIDClase").toString());

        }
        String idClase = getClaseXX();

        return idClase;
    }

    public String downSession2() {
        if (httpServletRequest.getSession().getAttribute("sessionIDUsuario") != null) {
            setTemaXX(this.httpServletRequest.getSession().getAttribute("sessionIDUsuario").toString());

        }
        String idClase = getTemaXX();

        return idClase;
    }

    public String downSessionClassUser() {
        if (httpServletRequest.getSession().getAttribute("sesionIdClaseUser") != null) {
            setIdClassUser(this.httpServletRequest.getSession().getAttribute("sesionIdClaseUser").toString());

        }
        String idClase = getIdClassUser();

        return idClase;
    }

    public void cargarlistaPDF() {

        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        String idClase = downSession1();
        int idClass = Integer.parseInt(idClase);

        String idUsuario = downSession2();
        int idUser = Integer.parseInt(idUsuario);

        setListTema(listParamPDF.retriveTemaPDF(idUser, idClass));
        setListSubtema(listParamPDF.retriveSubtemaPDF(idClass));
        setListEjercicio(listParamPDF.retriveEjercicioPDF(idClass));
        setListAlumnos(listParamPDF.retriveAlumnoPDF(idClass));
        setListNombreProfesor(listParamPDF.retrivePROFESOR(idUser));
        for (int i = 0; i < getListNombreProfesor().size(); i++) {
            ProfesorReporteBeans nombrep = (ProfesorReporteBeans) getListNombreProfesor().get(i);
            setNombreProfesorXX(nombrep.getNombreProfesor());
        }

        
    }

    public void verEjercicioPDF() throws Exception {
        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        int total = 0;
        int totalPass = 0;
        String claseAlumnoEliminar = params.get("idEjercicio");
        int idClase = Integer.parseInt(claseAlumnoEliminar);

        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        setListAlumnoPDF(listParamPDF.retriveListEjercicioPDF(idClase));

        //inicia el llenado de parametros
        setEjercicioTotal(listParamPDF.ejercicioTotal(idClase)); // total en subtemas
        for (int i = 0; i < getEjercicioTotal().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotal().get(i);
            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }
        setEjercicioTotalPass(listParamPDF.ejercicioTotalPass(idClase));// total de subtemas Pass
        for (int i = 0; i < getEjercicioTotalPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotalPass().get(i);
            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());

        }
        int totalFail = total - totalPass;
        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verAlumnosPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        int total = 0;
        int totalPass = 0;
        String claseAlumnoEliminar = params.get("idUsuario");
        int idUsuario = Integer.parseInt(claseAlumnoEliminar);
        String idClase = downSession1();
        int idClass = Integer.parseInt(idClase);

        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        setListAlumnoPDF(listParamPDF.retriveListAlumnosPDF(idClass, idUsuario));

        //inicia el llenado de parametros
        setEjercicioTotal(listParamPDF.alumnoTotal(idUsuario)); // total en subtemas
        for (int i = 0; i < getEjercicioTotal().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotal().get(i);
            //setTotalEjer(inscritosBeans.getTotalPass());
            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }
        setEjercicioTotalPass(listParamPDF.alumnoTotalPass(idUsuario));// total de subtemas Pass
        for (int i = 0; i < getEjercicioTotalPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotalPass().get(i);
            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());

        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;
        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA-2\\web\\WEB-INF\\reportxAlumno.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verPDF() throws Exception {

        int total = 0;
        int totalPass = 0;
        int idClase = Integer.parseInt(getClaseXX());

        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        setListAlumnoPDF(listParamPDF.retriveListAlumnosPDF(idClase));

        //inicia el llenado de parametros
        setEjercicioTotal(listParamPDF.totalClasePDF(idClase)); // total en subtemas
        for (int i = 0; i < getEjercicioTotal().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotal().get(i);

            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());
        }
        setEjercicioTotalPass(listParamPDF.totalClasePassPDF(idClase));// total de subtemas Pass
        for (int i = 0; i < getEjercicioTotalPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotalPass().get(i);
            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());

        }
        int totalFail = total - totalPass;
        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verTemaPDF() throws Exception {
        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        int total = 0;
        int totalPass = 0;
        String claseAlumnoEliminar = params.get("idTema");
        int idTema = Integer.parseInt(claseAlumnoEliminar);
        String clase = downSession1();
        int idClase = Integer.parseInt(clase);
        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        setListAlumnoPDF(listParamPDF.retriveListTemasPDF(idClase, idTema));

        //inicia el llenado de parametros
        setEjercicioTotal(listParamPDF.totalTemas(idTema)); // total en subtemas
        for (int i = 0; i < getEjercicioTotal().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotal().get(i);
            //setTotalEjer(inscritosBeans.getTotalPass());
            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }
        setEjercicioTotalPass(listParamPDF.totalTemasPass(idTema));// total de subtemas Pass
        for (int i = 0; i < getEjercicioTotalPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotalPass().get(i);
            //setTotalEjer(inscritosBeans.getTotalPass());
            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());

        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verSubtemaPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        int total = 0;
        int totalPass = 0;
        String claseAlumnoEliminar = params.get("idSubtema");
        int idSubTema = Integer.parseInt(claseAlumnoEliminar);
        String clase = downSession1();
        int idClase = Integer.parseInt(clase);
        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();
        setListAlumnoPDF(listParamPDF.retriveListSubtemasPDF(idClase, idSubTema));

        //inicia el llenado de parametros
        setEjercicioTotal(listParamPDF.SubtemasTotal(idSubTema)); // total en subtemas
        for (int i = 0; i < getEjercicioTotal().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotal().get(i);
            //setTotalEjer(inscritosBeans.getTotalPass());
            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }
        setEjercicioTotalPass(listParamPDF.SubtemasTotalPass(idSubTema));// total de subtemas Pass
        for (int i = 0; i < getEjercicioTotalPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getEjercicioTotalPass().get(i);

            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());

        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;
        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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
            String ruta = ("C:\\Users\\tlp_0_000\\Desktop\\Tesis NetBeans\\Tesis_Final_P_F_5_1\\web\\WEB-INF\\report123.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(this.getListAlumnoPDF()));

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

    public void verClasePDF() throws Exception {

        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();

        //llena el cuerpo del pdf
        int total = 0;
        int totalPass = 0;
        int idClase = Integer.parseInt(downSession1());

        StringTokenizer token = new StringTokenizer(getIdClassUser(), " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);

        setListAlumnoPDF(listParamPDF.retriveClasePDF(idclase, idusuario));
        setTotalEjercicio(listParamPDF.totalEjerciciosXClasePDF(idclase, idusuario));
        for (int i = 0; i < getTotalEjercicio().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicio().get(i);

            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }

        setTotalEjercicioPass(listParamPDF.totalEjerciciosXClasePassPDF(idClase, idusuario));

        for (int i = 0; i < getTotalEjercicioPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicioPass().get(i);

            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());
        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verTemaAlumnoPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();

        //llena el cuerpo del pdf
        int total = 0;
        int totalPass = 0;
        int idClase = Integer.parseInt(downSession1());
        String idTema = params.get("idTema");
        int idTemaX = Integer.parseInt(idTema);
        StringTokenizer token = new StringTokenizer(getIdClassUser(), " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);

        setListAlumnoPDF(listParamPDF.retriveTemaAlumnoPDF(idTemaX, idusuario));
        setTotalEjercicio(listParamPDF.totalEjerciciosXTemaPDF(idTemaX, idusuario));
        for (int i = 0; i < getTotalEjercicio().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicio().get(i);

            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }

        setTotalEjercicioPass(listParamPDF.totalEjerciciosXTemaPassPDF(idTemaX, idusuario));

        for (int i = 0; i < getTotalEjercicioPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicioPass().get(i);

            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());
        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void verSubtemaAlumnoPDF() throws Exception {

        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        ProfesorReporteDAO listParamPDF = new ProfesorReporteDAO();

        //llena el cuerpo del pdf
        int total = 0;
        int totalPass = 0;
        int idClase = Integer.parseInt(downSession1());
        String idTema = params.get("idSubtema");
        int idTemaX = Integer.parseInt(idTema);
        StringTokenizer token = new StringTokenizer(getIdClassUser(), " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);

        setListAlumnoPDF(listParamPDF.retriveSubtemaTemaAlumnoPDF(idTemaX, idusuario));
        setTotalEjercicio(listParamPDF.totalEjerciciosXSubtemaTemaPDF(idTemaX, idusuario));
        for (int i = 0; i < getTotalEjercicio().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicio().get(i);

            total = Integer.parseInt(inscritosBeans.getTotalEjercicios());

        }

        setTotalEjercicioPass(listParamPDF.totalEjerciciosXSubtemaTemaPassPDF(idTemaX, idusuario));

        for (int i = 0; i < getTotalEjercicioPass().size(); i++) {
            ProfesorReporteBeans inscritosBeans = (ProfesorReporteBeans) getTotalEjercicioPass().get(i);

            totalPass = Integer.parseInt(inscritosBeans.getTotalEjerciciosPass());
        }
        int totalFail = total - totalPass;

        int promedio = (totalPass * 100) / total;

        FacesContext facesContext = FacesContext.getCurrentInstance();

        String outFileName = "/Users/tlp_0_000/Desktop/JasperReport/web/WEB-INF/Scpt.pdf";

        try {
            String ruta = ("C:\\Users\\tlp_0_000\\Documents\\NetBeansProjects\\Project_SEA\\web\\WEB-INF\\reportxAlumno.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);

            Map parametros = new HashMap();
            parametros.put("nombreProfesor", getNombreProfesorXX());
            parametros.put("totalEjercicioP", "" + total);
            parametros.put("totalEjercicioOK", "" + totalPass);
            parametros.put("totalEjericiosFAIL", "" + totalFail);
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

    public void subirSesion() throws IOException, Exception {
        FacesContext facesContext2 = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext2.getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        String claseAlumnouser = params.get("idClaseAlumno");

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*****NO CONTEXT");
        }
        httpServletRequest.removeAttribute("sesionIdClaseUser");
        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sesionIdClaseUser", claseAlumnouser);

        } else {
            System.out.println("*******NO session");
        }

    }

    /**
     * @return the listAlumnoPDF
     */
    public List<ProfesorReporteBeans> getListAlumnoPDF() {
        return listAlumnoPDF;
    }

    /**
     * @param listAlumnoPDF the listAlumnoPDF to set
     */
    public void setListAlumnoPDF(List<ProfesorReporteBeans> listAlumnoPDF) {
        this.listAlumnoPDF = listAlumnoPDF;
    }

    /**
     * @return the claseXX
     */
    public String getClaseXX() {
        return claseXX;
    }

    /**
     * @param claseXX the claseXX to set
     */
    public void setClaseXX(String claseXX) {
        this.claseXX = claseXX;
    }

    /**
     * @return the temaXX
     */
    public String getTemaXX() {
        return temaXX;
    }

    /**
     * @param temaXX the temaXX to set
     */
    public void setTemaXX(String temaXX) {
        this.temaXX = temaXX;
    }

    /**
     * @return the listTema
     */
    public List<ProfesorReporteBeans> getListTema() {
        return listTema;
    }

    /**
     * @param listTema the listTema to set
     */
    public void setListTema(List<ProfesorReporteBeans> listTema) {
        this.listTema = listTema;
    }

    /**
     * @return the listSubtema
     */
    public List<ProfesorReporteBeans> getListSubtema() {
        return listSubtema;
    }

    /**
     * @param listSubtema the listSubtema to set
     */
    public void setListSubtema(List<ProfesorReporteBeans> listSubtema) {
        this.listSubtema = listSubtema;
    }

    /**
     * @return the listEjercicio
     */
    public List<ProfesorReporteBeans> getListEjercicio() {
        return listEjercicio;
    }

    /**
     * @param listEjercicio the listEjercicio to set
     */
    public void setListEjercicio(List<ProfesorReporteBeans> listEjercicio) {
        this.listEjercicio = listEjercicio;
    }

    /**
     * @return the listAlumnos
     */
    public List<ProfesorReporteBeans> getListAlumnos() {
        return listAlumnos;
    }

    /**
     * @param listAlumnos the listAlumnos to set
     */
    public void setListAlumnos(List<ProfesorReporteBeans> listAlumnos) {
        this.listAlumnos = listAlumnos;
    }

    /**
     * @return the nombreProfesorXX
     */
    public String getNombreProfesorXX() {
        return nombreProfesorXX;
    }

    /**
     * @param nombreProfesorXX the nombreProfesorXX to set
     */
    public void setNombreProfesorXX(String nombreProfesorXX) {
        this.nombreProfesorXX = nombreProfesorXX;
    }

    /**
     * @return the listNombreProfesor
     */
    public List<ProfesorReporteBeans> getListNombreProfesor() {
        return listNombreProfesor;
    }

    /**
     * @param listNombreProfesor the listNombreProfesor to set
     */
    public void setListNombreProfesor(List<ProfesorReporteBeans> listNombreProfesor) {
        this.listNombreProfesor = listNombreProfesor;
    }

    /**
     * @return the ejercicioTotal
     */
    public List<ProfesorReporteBeans> getEjercicioTotal() {
        return ejercicioTotal;
    }

    /**
     * @param ejercicioTotal the ejercicioTotal to set
     */
    public void setEjercicioTotal(List<ProfesorReporteBeans> ejercicioTotal) {
        this.ejercicioTotal = ejercicioTotal;
    }

    /**
     * @return the ejercicioTotalPass
     */
    public List<ProfesorReporteBeans> getEjercicioTotalPass() {
        return ejercicioTotalPass;
    }

    /**
     * @param ejercicioTotalPass the ejercicioTotalPass to set
     */
    public void setEjercicioTotalPass(List<ProfesorReporteBeans> ejercicioTotalPass) {
        this.ejercicioTotalPass = ejercicioTotalPass;
    }

    /**
     * @return the totalEjercicio
     */
    public List<ProfesorReporteBeans> getTotalEjercicio() {
        return totalEjercicio;
    }

    /**
     * @param totalEjercicio the totalEjercicio to set
     */
    public void setTotalEjercicio(List<ProfesorReporteBeans> totalEjercicio) {
        this.totalEjercicio = totalEjercicio;
    }

    /**
     * @return the totalEjercicioPass
     */
    public List<ProfesorReporteBeans> getTotalEjercicioPass() {
        return totalEjercicioPass;
    }

    /**
     * @param totalEjercicioPass the totalEjercicioPass to set
     */
    public void setTotalEjercicioPass(List<ProfesorReporteBeans> totalEjercicioPass) {
        this.totalEjercicioPass = totalEjercicioPass;
    }

    /**
     * @return the idClassUser
     */
    public String getIdClassUser() {
        return idClassUser;
    }

    /**
     * @param idClassUser the idClassUser to set
     */
    public void setIdClassUser(String idClassUser) {
        this.idClassUser = idClassUser;
    }
}
