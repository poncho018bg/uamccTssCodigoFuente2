/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.AlumnoEjercicioBeans;
import mx.uatx.tesis.daos.AlumnoEjercicioDAO;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class AlumnoEjercicioMB {

    //VARIABLES PARA LA SESSION
    private String idTemarioAlumno;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    //tabla EjerciciosAlumnos
    private int idEjercicioAlumno_MB;
    private int ejePro_idEjeProEjAlumno_MB;
    private int casoPrueba_idEjeAlu_MB;
    private int usuario_idUsuarioAlum_MB;
    private String codigoFuenteAlumno_MB;
    private String status_MB;
    private Date fechaStart_MB;
    private Date fechaEnd_MB;
    //tabla EjerciciosProfesor
    private int idejercicioProfesor_AMB;
    private int subtemaProfesor_idsubtemaProfesor_AMB;
    private String nombre_AMB;
    // TreeNode instance
    private TreeNode singleSelectedTreeNode;
    private TreeNode root;
    private String idTemas_a;
    private String idSubTemas_a;
    private String nivelParent_a;

    /**
     * Creates a new instance of AlumnoEjercicioMB
     */
    public AlumnoEjercicioMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        if (httpServletRequest.getSession().getAttribute("sessionIDSubtemaAlumno") != null) {
            setIdTemarioAlumno(this.httpServletRequest.getSession().getAttribute("sessionIDSubtemaAlumno").toString());

        }
        int idSub = Integer.parseInt(getIdTemarioAlumno());
        listarTemario(idSub);
    }

    public int listarTemario(int idSubtema) {

        AlumnoEjercicioDAO treeDao = new AlumnoEjercicioDAO();
        List<AlumnoEjercicioBeans> ejercicio = treeDao.retriveListEjerToAlumnos(idSubtema);

        // This is the root node, so it's data is root and its parent is null
        this.setRoot(new DefaultTreeNode("Root Node", "", null));

        for (int x = 0; x < ejercicio.size(); x++) {

            setIdejercicioProfesor_AMB(ejercicio.get(x).getIdejercicioProfesor_A());
            setSubtemaProfesor_idsubtemaProfesor_AMB(ejercicio.get(x).getSubtemaProfesor_idsubtemaProfesor_A());
            setNombre_AMB(ejercicio.get(x).getNombre_A());

            // Create child node
            TreeNode child = new DefaultTreeNode(idejercicioProfesor_AMB + " " + nombre_AMB, this.getRoot());

        }
        return 0;
    }

    public void upSession1(String idEjercicio) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*****NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            sesion.setAttribute("sessionVarIdEjerAlumno", idEjercicio);
        } else {
            System.out.println("*****NO session");
        }
        
    }

    public void redirectToWorkSpace() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_workspace.xhtml");
        
    }

    public void onNodeSelect(NodeSelectEvent event) throws IOException {
        String idActual = "" + event.getTreeNode().getData();
        StringTokenizer token = new StringTokenizer(idActual, " ");

        for (int i = 1; i < 2; i++) {
            String tok_1 = token.nextToken();
            String tok_2 = token.nextToken();

            upSession1(tok_1);
        }
        redirectToWorkSpace();

    }

    public void onNodeUnSelect(NodeUnselectEvent event) {

    }

    public void onNodeExpand(NodeExpandEvent event) {

    }

    public void onNodeCollapse(NodeCollapseEvent event) {

    }

    public String printSelectedNodes() {

        return "";
    }

    /**
     * @return the idEjercicioAlumno_MB
     */
    public int getIdEjercicioAlumno_MB() {
        return idEjercicioAlumno_MB;
    }

    /**
     * @param idEjercicioAlumno_MB the idEjercicioAlumno_MB to set
     */
    public void setIdEjercicioAlumno_MB(int idEjercicioAlumno_MB) {
        this.idEjercicioAlumno_MB = idEjercicioAlumno_MB;
    }

    /**
     * @return the ejePro_idEjeProEjAlumno_MB
     */
    public int getEjePro_idEjeProEjAlumno_MB() {
        return ejePro_idEjeProEjAlumno_MB;
    }

    /**
     * @param ejePro_idEjeProEjAlumno_MB the ejePro_idEjeProEjAlumno_MB to set
     */
    public void setEjePro_idEjeProEjAlumno_MB(int ejePro_idEjeProEjAlumno_MB) {
        this.ejePro_idEjeProEjAlumno_MB = ejePro_idEjeProEjAlumno_MB;
    }

    /**
     * @return the casoPrueba_idEjeAlu_MB
     */
    public int getCasoPrueba_idEjeAlu_MB() {
        return casoPrueba_idEjeAlu_MB;
    }

    /**
     * @param casoPrueba_idEjeAlu_MB the casoPrueba_idEjeAlu_MB to set
     */
    public void setCasoPrueba_idEjeAlu_MB(int casoPrueba_idEjeAlu_MB) {
        this.casoPrueba_idEjeAlu_MB = casoPrueba_idEjeAlu_MB;
    }

    /**
     * @return the usuario_idUsuarioAlum_MB
     */
    public int getUsuario_idUsuarioAlum_MB() {
        return usuario_idUsuarioAlum_MB;
    }

    /**
     * @param usuario_idUsuarioAlum_MB the usuario_idUsuarioAlum_MB to set
     */
    public void setUsuario_idUsuarioAlum_MB(int usuario_idUsuarioAlum_MB) {
        this.usuario_idUsuarioAlum_MB = usuario_idUsuarioAlum_MB;
    }

    /**
     * @return the codigoFuenteAlumno_MB
     */
    public String getCodigoFuenteAlumno_MB() {
        return codigoFuenteAlumno_MB;
    }

    /**
     * @param codigoFuenteAlumno_MB the codigoFuenteAlumno_MB to set
     */
    public void setCodigoFuenteAlumno_MB(String codigoFuenteAlumno_MB) {
        this.codigoFuenteAlumno_MB = codigoFuenteAlumno_MB;
    }

    /**
     * @return the status_MB
     */
    public String getStatus_MB() {
        return status_MB;
    }

    /**
     * @param status_MB the status_MB to set
     */
    public void setStatus_MB(String status_MB) {
        this.status_MB = status_MB;
    }

    /**
     * @return the fechaStart_MB
     */
    public Date getFechaStart_MB() {
        return fechaStart_MB;
    }

    /**
     * @param fechaStart_MB the fechaStart_MB to set
     */
    public void setFechaStart_MB(Date fechaStart_MB) {
        this.fechaStart_MB = fechaStart_MB;
    }

    /**
     * @return the fechaEnd_MB
     */
    public Date getFechaEnd_MB() {
        return fechaEnd_MB;
    }

    /**
     * @param fechaEnd_MB the fechaEnd_MB to set
     */
    public void setFechaEnd_MB(Date fechaEnd_MB) {
        this.fechaEnd_MB = fechaEnd_MB;
    }

    /**
     * @return the idejercicioProfesor_AMB
     */
    public int getIdejercicioProfesor_AMB() {
        return idejercicioProfesor_AMB;
    }

    /**
     * @param idejercicioProfesor_AMB the idejercicioProfesor_AMB to set
     */
    public void setIdejercicioProfesor_AMB(int idejercicioProfesor_AMB) {
        this.idejercicioProfesor_AMB = idejercicioProfesor_AMB;
    }

    /**
     * @return the subtemaProfesor_idsubtemaProfesor_AMB
     */
    public int getSubtemaProfesor_idsubtemaProfesor_AMB() {
        return subtemaProfesor_idsubtemaProfesor_AMB;
    }

    /**
     * @param subtemaProfesor_idsubtemaProfesor_AMB the
     * subtemaProfesor_idsubtemaProfesor_AMB to set
     */
    public void setSubtemaProfesor_idsubtemaProfesor_AMB(int subtemaProfesor_idsubtemaProfesor_AMB) {
        this.subtemaProfesor_idsubtemaProfesor_AMB = subtemaProfesor_idsubtemaProfesor_AMB;
    }

    /**
     * @return the nombre_AMB
     */
    public String getNombre_AMB() {
        return nombre_AMB;
    }

    /**
     * @param nombre_AMB the nombre_AMB to set
     */
    public void setNombre_AMB(String nombre_AMB) {
        this.nombre_AMB = nombre_AMB;
    }

    /**
     * @return the idTemarioAlumno
     */
    public String getIdTemarioAlumno() {
        return idTemarioAlumno;
    }

    /**
     * @param idTemarioAlumno the idTemarioAlumno to set
     */
    public void setIdTemarioAlumno(String idTemarioAlumno) {
        this.idTemarioAlumno = idTemarioAlumno;
    }

    /**
     * @return the singleSelectedTreeNode
     */
    public TreeNode getSingleSelectedTreeNode() {
        return singleSelectedTreeNode;
    }

    /**
     * @param singleSelectedTreeNode the singleSelectedTreeNode to set
     */
    public void setSingleSelectedTreeNode(TreeNode singleSelectedTreeNode) {
        this.singleSelectedTreeNode = singleSelectedTreeNode;
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

}
