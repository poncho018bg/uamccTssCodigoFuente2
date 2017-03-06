/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import mx.uatx.tesis.beans.AvanceProfesorBeans;
import mx.uatx.tesis.daos.AvanceProfesorDAO;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;

import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class AvanceProfesorMB {

    //VARIABLES PARA LA SESSION
    private String claseXX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    //tabla ejercicios alumnos
    private int ideejrcicioAlumno_ea_MB;
    private int idejercicioProfesor_ea_MB;
    private int idusuarios_ea_MB;
    private String fechaInicio_ea_MB;
    private String fechaFinal_ea_MB;
    private int tiempoTotal_ea_MB;
    private int intentosTotal_ea_MB;
    // TABLA EJERCICIOS PROFESOR
    private int idejercicioProfesor_epMB;
    private String nombre_epMB;

    private CartesianChartModel modelIntentos;
    private CartesianChartModel modelTiempo;
    private CartesianChartModel modelDias;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private List<AvanceProfesorBeans> listGrafica1 = new ArrayList<AvanceProfesorBeans>();
    private List<AvanceProfesorBeans> listNombresEjercicio = new ArrayList<AvanceProfesorBeans>();

    /**
     * Creates a new instance of AvanceProfesorMB
     */
    public AvanceProfesorMB() {

        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        downSession1();
        createLinearModelsIntentos();
        createBubbleModelsTiempo();
        createBubbleModelsDias();

    }

    public String downSession1() {
        if (httpServletRequest.getSession().getAttribute("sessionIDClase") != null) {
            setClaseXX(this.httpServletRequest.getSession().getAttribute("sessionIDClase").toString());

        }
        String idClase = getClaseXX();

        return idClase;
    }

    /**
     * public BubbleChartModel getBubbleModel1() { return bubbleModel1; }
     *
     *
     * public BubbleChartModel getBubbleModelTiempo() { return
     * bubbleModelTiempo; }
     */
    private void createLinearModelsIntentos() {

        modelIntentos = new CartesianChartModel();
        modelIntentos.addSeries(getStockChartDataIntentos("Intentos"));

    }

    private ChartSeries getStockChartDataIntentos(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AvanceProfesorDAO grafica1 = new AvanceProfesorDAO();

        String variables = downSession1();
        StringTokenizer token = new StringTokenizer(variables, " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);
        setListGrafica1(grafica1.DetallesGrafica1(idusuario, idclase));

        int e = 0;

        for (int i = 0; i < getListGrafica1().size(); i++) {
            AvanceProfesorBeans grafica11 = (AvanceProfesorBeans) getListGrafica1().get(i);
            setIdeejrcicioAlumno_ea_MB(grafica11.getIdeejrcicioAlumno_ea());
            setIdejercicioProfesor_ea_MB(grafica11.getIdejercicioProfesor_ea());
            setIdusuarios_ea_MB(grafica11.getIdusuarios_ea());
            setFechaInicio_ea_MB(grafica11.getFechaInicio_ea());
            setFechaFinal_ea_MB(grafica11.getFechaFinal_ea());
            setTiempoTotal_ea_MB(grafica11.getTiempoTotal_ea());
            setIntentosTotal_ea_MB(grafica11.getIntentosTotal_ea());

            setListNombresEjercicio(grafica1.nombreEjercicio(getIdejercicioProfesor_ea_MB()));
            for (int x = 0; x < getListNombresEjercicio().size(); x++) {

                AvanceProfesorBeans grafica22 = (AvanceProfesorBeans) getListNombresEjercicio().get(x);
                setIdejercicioProfesor_epMB(grafica22.getIdejercicioProfesor_ep());
                setNombre_epMB(grafica22.getNombre_ep());

                data.getData().put(getNombre_epMB(), getIntentosTotal_ea_MB());
            }

        }

        return data;
    }

    private void createBubbleModelsTiempo() {

        modelTiempo = new CartesianChartModel();
        modelTiempo.addSeries(getStockChartDataTiempo("Minutos"));
    }

    private ChartSeries getStockChartDataTiempo(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AvanceProfesorDAO grafica1 = new AvanceProfesorDAO();

        String variables = downSession1();
        StringTokenizer token = new StringTokenizer(variables, " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);
        setListGrafica1(grafica1.DetallesGrafica1(idusuario, idclase));

        int e = 0;

        for (int i = 0; i < getListGrafica1().size(); i++) {
            AvanceProfesorBeans grafica11 = (AvanceProfesorBeans) getListGrafica1().get(i);
            setIdeejrcicioAlumno_ea_MB(grafica11.getIdeejrcicioAlumno_ea());
            setIdejercicioProfesor_ea_MB(grafica11.getIdejercicioProfesor_ea());
            setIdusuarios_ea_MB(grafica11.getIdusuarios_ea());
            setFechaInicio_ea_MB(grafica11.getFechaInicio_ea());
            setFechaFinal_ea_MB(grafica11.getFechaFinal_ea());
            setTiempoTotal_ea_MB(grafica11.getTiempoTotal_ea());
            setIntentosTotal_ea_MB(grafica11.getIntentosTotal_ea());

            setListNombresEjercicio(grafica1.nombreEjercicio(getIdejercicioProfesor_ea_MB()));
            for (int x = 0; x < getListNombresEjercicio().size(); x++) {

                AvanceProfesorBeans grafica22 = (AvanceProfesorBeans) getListNombresEjercicio().get(x);
                setIdejercicioProfesor_epMB(grafica22.getIdejercicioProfesor_ep());
                setNombre_epMB(grafica22.getNombre_ep());

                data.getData().put(getNombre_epMB(), getTiempoTotal_ea_MB());
            }

        }

        return data;
    }

    private void createBubbleModelsDias() {
        modelDias = new CartesianChartModel();
        modelDias.addSeries(getStockChartDataDias("Dias"));

    }

    private ChartSeries getStockChartDataDias(String label) {
        ChartSeries data = new ChartSeries();
        data.setLabel(label);

        AvanceProfesorDAO grafica1 = new AvanceProfesorDAO();

        String variables = downSession1();
        StringTokenizer token = new StringTokenizer(variables, " ");
        String tok_1 = token.nextToken();
        String tok_2 = token.nextToken();

        int idclase = Integer.parseInt(tok_1);
        int idusuario = Integer.parseInt(tok_2);
        setListGrafica1(grafica1.DetallesGrafica1(idusuario, idclase));

        int e = 0;

        for (int i = 0; i < getListGrafica1().size(); i++) {
            AvanceProfesorBeans grafica11 = (AvanceProfesorBeans) getListGrafica1().get(i);
            setIdeejrcicioAlumno_ea_MB(grafica11.getIdeejrcicioAlumno_ea());
            setIdejercicioProfesor_ea_MB(grafica11.getIdejercicioProfesor_ea());
            setIdusuarios_ea_MB(grafica11.getIdusuarios_ea());
            setFechaInicio_ea_MB(grafica11.getFechaInicio_ea());
            setFechaFinal_ea_MB(grafica11.getFechaFinal_ea());
            setTiempoTotal_ea_MB(grafica11.getTiempoTotal_ea());
            setIntentosTotal_ea_MB(grafica11.getIntentosTotal_ea());

            setListNombresEjercicio(grafica1.nombreEjercicio(getIdejercicioProfesor_ea_MB()));
            for (int x = 0; x < getListNombresEjercicio().size(); x++) {

                AvanceProfesorBeans grafica22 = (AvanceProfesorBeans) getListNombresEjercicio().get(x);
                setIdejercicioProfesor_epMB(grafica22.getIdejercicioProfesor_ep());
                setNombre_epMB(grafica22.getNombre_ep());
                try {
                    Date fechaMayor = formatter.parse(getFechaInicio_ea_MB());
                    Date fechaMenor = formatter.parse(getFechaFinal_ea_MB());
                    long diferencia_1 = fechaMayor.getTime();
                    long diferencia_2 = fechaMenor.getTime();
                    Long ms = diferencia_2 - diferencia_1;
                    long dias = ms / (1000 * 60 * 60 * 24);

                    data.getData().put(getNombre_epMB(), (int) dias);

                } catch (ParseException er) {
                    er.printStackTrace();
                }

            }

        }

        return data;
    }

    /**
     * @return the listGrafica1
     */
    public List<AvanceProfesorBeans> getListGrafica1() {
        return listGrafica1;
    }

    /**
     * @param listGrafica1 the listGrafica1 to set
     */
    public void setListGrafica1(List<AvanceProfesorBeans> listGrafica1) {
        this.listGrafica1 = listGrafica1;
    }

    /**
     * @return the ideejrcicioAlumno_ea_MB
     */
    public int getIdeejrcicioAlumno_ea_MB() {
        return ideejrcicioAlumno_ea_MB;
    }

    /**
     * @param ideejrcicioAlumno_ea_MB the ideejrcicioAlumno_ea_MB to set
     */
    public void setIdeejrcicioAlumno_ea_MB(int ideejrcicioAlumno_ea_MB) {
        this.ideejrcicioAlumno_ea_MB = ideejrcicioAlumno_ea_MB;
    }

    /**
     * @return the idejercicioProfesor_ea_MB
     */
    public int getIdejercicioProfesor_ea_MB() {
        return idejercicioProfesor_ea_MB;
    }

    /**
     * @param idejercicioProfesor_ea_MB the idejercicioProfesor_ea_MB to set
     */
    public void setIdejercicioProfesor_ea_MB(int idejercicioProfesor_ea_MB) {
        this.idejercicioProfesor_ea_MB = idejercicioProfesor_ea_MB;
    }

    /**
     * @return the idusuarios_ea_MB
     */
    public int getIdusuarios_ea_MB() {
        return idusuarios_ea_MB;
    }

    /**
     * @param idusuarios_ea_MB the idusuarios_ea_MB to set
     */
    public void setIdusuarios_ea_MB(int idusuarios_ea_MB) {
        this.idusuarios_ea_MB = idusuarios_ea_MB;
    }

    /**
     * @return the fechaInicio_ea_MB
     */
    public String getFechaInicio_ea_MB() {
        return fechaInicio_ea_MB;
    }

    /**
     * @param fechaInicio_ea_MB the fechaInicio_ea_MB to set
     */
    public void setFechaInicio_ea_MB(String fechaInicio_ea_MB) {
        this.fechaInicio_ea_MB = fechaInicio_ea_MB;
    }

    /**
     * @return the fechaFinal_ea_MB
     */
    public String getFechaFinal_ea_MB() {
        return fechaFinal_ea_MB;
    }

    /**
     * @param fechaFinal_ea_MB the fechaFinal_ea_MB to set
     */
    public void setFechaFinal_ea_MB(String fechaFinal_ea_MB) {
        this.fechaFinal_ea_MB = fechaFinal_ea_MB;
    }

    /**
     * @return the tiempoTotal_ea_MB
     */
    public int getTiempoTotal_ea_MB() {
        return tiempoTotal_ea_MB;
    }

    /**
     * @param tiempoTotal_ea_MB the tiempoTotal_ea_MB to set
     */
    public void setTiempoTotal_ea_MB(int tiempoTotal_ea_MB) {
        this.tiempoTotal_ea_MB = tiempoTotal_ea_MB;
    }

    /**
     * @return the intentosTotal_ea_MB
     */
    public int getIntentosTotal_ea_MB() {
        return intentosTotal_ea_MB;
    }

    /**
     * @param intentosTotal_ea_MB the intentosTotal_ea_MB to set
     */
    public void setIntentosTotal_ea_MB(int intentosTotal_ea_MB) {
        this.intentosTotal_ea_MB = intentosTotal_ea_MB;
    }

    /**
     * @return the idejercicioProfesor_epMB
     */
    public int getIdejercicioProfesor_epMB() {
        return idejercicioProfesor_epMB;
    }

    /**
     * @param idejercicioProfesor_epMB the idejercicioProfesor_epMB to set
     */
    public void setIdejercicioProfesor_epMB(int idejercicioProfesor_epMB) {
        this.idejercicioProfesor_epMB = idejercicioProfesor_epMB;
    }

    /**
     * @return the nombre_epMB
     */
    public String getNombre_epMB() {
        return nombre_epMB;
    }

    /**
     * @param nombre_epMB the nombre_epMB to set
     */
    public void setNombre_epMB(String nombre_epMB) {
        this.nombre_epMB = nombre_epMB;
    }

    /**
     * @return the listNombresEjercicio
     */
    public List<AvanceProfesorBeans> getListNombresEjercicio() {
        return listNombresEjercicio;
    }

    /**
     * @param listNombresEjercicio the listNombresEjercicio to set
     */
    public void setListNombresEjercicio(List<AvanceProfesorBeans> listNombresEjercicio) {
        this.listNombresEjercicio = listNombresEjercicio;
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
     * @return the bubbleModelDias
     */
    public CartesianChartModel getLinearModelIntentos() {
        return modelIntentos;
    }

    public CartesianChartModel getLinearModelTiempo() {
        return modelTiempo;
    }

    public CartesianChartModel getLinearModelDias() {
        return modelDias;
    }
}
