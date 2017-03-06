/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.util.ArrayList;
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
import mx.uatx.tesis.beans.AlumnoTemarioBeans;
import mx.uatx.tesis.daos.AlumnoTemarioDAO;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
//import mx.uatx.tesis.beans.TemarioProBeans;
//import mx.uatx.tesis.daos.TemarioProDAO;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class AlumnoTemarioMB {

    //VARIABLES PARA LA SESSION
    private String claseAlumno;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    // TreeNode instance
    private TreeNode singleSelectedTreeNode;
    private TreeNode root;
    private String idTemas_a;
    private String idSubTemas_a;
    private String nivelParent_a;
    //private int tamanioTree_a;

    // tabla Temaprofesor
    private int idTemaProfeMB_a;
    private String nombreTemaMB_a;
    private int claseProfe_idClaseProfeMB_a;
    private List<AlumnoTemarioBeans> listTema_a = new ArrayList<AlumnoTemarioBeans>();

    // tabla subtemaProfesor
    private int idSubtemaProfeMB_a;
    private String nombreSubtemaMB_a;
    private String nombreSubMB_a;
    private int temaProfe_idTemaProfeMB_a;
    private boolean child;

    /**
     * Creates a new instance of AlumnoTemarioMB
     */
    public AlumnoTemarioMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        if (httpServletRequest.getSession().getAttribute("sessionIDRerirectClase") != null) {
            setClaseAlumno(this.httpServletRequest.getSession().getAttribute("sessionIDRerirectClase").toString());

        }
        listarTemario();
    }

    public void listarTemario() {
        //Bajar credenciales

        int idClase = Integer.parseInt(getClaseAlumno());

        AlumnoTemarioDAO treeDao = new AlumnoTemarioDAO();
        List<AlumnoTemarioBeans> tema = treeDao.retriveListTemaAlumno(idClase);
        List<AlumnoTemarioBeans> subtema = treeDao.retriveListSUBTemaAlumno();

        // This is the root node, so it's data is root and its parent is null
        this.root = new DefaultTreeNode("Root Node", "", null);

        for (int x = 0; x < tema.size(); x++) {

            setIdTemaProfeMB_a(tema.get(x).getIdTemaProfe());
            setNombreTemaMB_a(tema.get(x).getNombreTema());
            setClaseProfe_idClaseProfeMB_a(tema.get(x).getClaseProfe_idClaseProfe());

            // Create child node
            TreeNode child = new DefaultTreeNode(idTemaProfeMB_a + " " + nombreTemaMB_a, this.root);

            for (int y = 0; y < subtema.size(); y++) {
                setIdSubtemaProfeMB_a(subtema.get(y).getIdSubtemaProfe());
                setNombreSubtemaMB_a(subtema.get(y).getNombreSubtema());
                setTemaProfe_idTemaProfeMB_a(subtema.get(y).getTemaProfe_idTemaProfe());

                if (idTemaProfeMB_a == temaProfe_idTemaProfeMB_a) {
                    // Reference the parent of child node
                    child.setParent(this.root);
                    // Create descendent nodes
                    TreeNode descendent = new DefaultTreeNode(idSubtemaProfeMB_a + " " + nombreSubtemaMB_a, child);
                    // Reference the parent of descendent node
                    descendent.setParent(child);

                }

            }

        }
        
    }

    public void redirectToEjerciciosAlumnos(int idSubtemaAlumno) throws IOException {

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("****NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionIDSubtemaAlumno", idSubtemaAlumno);

        } else {
            System.out.println("****NO session");
        }
        //Rediceccionar a la pagina Alumnos_Ejercicio.xhtml

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Alumnos_Ejercicio.xhtml");
        
    }

    public void onNodeSelect(NodeSelectEvent event) throws IOException {
        String idActual = "" + event.getTreeNode().getData();
        String nivelActual = "" + event.getTreeNode().getParent().getType();
        int tamanio = event.getTreeNode().getChildCount();

        StringTokenizer token = new StringTokenizer(idActual, " ");

        if (nivelActual.equals("Root Node")) {

            for (int i = 1; i < 2; i++) {
                String tok_1 = token.nextToken();
                String tok_2 = token.nextToken();
                setIdTemas_a(tok_1);
            }
            setChild(false);

        } else {

            for (int i = 1; i < 2; i++) {
                String tok_1 = token.nextToken();
                String tok_2 = token.nextToken();
                setIdTemas_a(tok_1);

            }

            setChild(true);

            // Redirecciona a la pagina de Alumnos_Ejercicio.xhtml
            int idSubtemaAlumno = Integer.parseInt(getIdTemas_a());
            redirectToEjerciciosAlumnos(idSubtemaAlumno);

        }

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
     * @return the claseAlumno
     */
    public String getClaseAlumno() {
        return claseAlumno;
    }

    /**
     * @param claseAlumno the claseAlumno to set
     */
    public void setClaseAlumno(String claseAlumno) {
        this.claseAlumno = claseAlumno;
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

    /**
     * @return the idTemas_a
     */
    public String getIdTemas_a() {
        return idTemas_a;
    }

    /**
     * @param idTemas_a the idTemas_a to set
     */
    public void setIdTemas_a(String idTemas_a) {
        this.idTemas_a = idTemas_a;
    }

    /**
     * @return the idSubTemas_a
     */
    public String getIdSubTemas_a() {
        return idSubTemas_a;
    }

    /**
     * @param idSubTemas_a the idSubTemas_a to set
     */
    public void setIdSubTemas_a(String idSubTemas_a) {
        this.idSubTemas_a = idSubTemas_a;
    }

    /**
     * @return the nivelParent_a
     */
    public String getNivelParent_a() {
        return nivelParent_a;
    }

    /**
     * @param nivelParent_a the nivelParent_a to set
     */
    public void setNivelParent_a(String nivelParent_a) {
        this.nivelParent_a = nivelParent_a;
    }

    /**
     * @return the idTemaProfeMB_a
     */
    public int getIdTemaProfeMB_a() {
        return idTemaProfeMB_a;
    }

    /**
     * @param idTemaProfeMB_a the idTemaProfeMB_a to set
     */
    public void setIdTemaProfeMB_a(int idTemaProfeMB_a) {
        this.idTemaProfeMB_a = idTemaProfeMB_a;
    }

    /**
     * @return the nombreTemaMB_a
     */
    public String getNombreTemaMB_a() {
        return nombreTemaMB_a;
    }

    /**
     * @param nombreTemaMB_a the nombreTemaMB_a to set
     */
    public void setNombreTemaMB_a(String nombreTemaMB_a) {
        this.nombreTemaMB_a = nombreTemaMB_a;
    }

    /**
     * @return the claseProfe_idClaseProfeMB_a
     */
    public int getClaseProfe_idClaseProfeMB_a() {
        return claseProfe_idClaseProfeMB_a;
    }

    /**
     * @param claseProfe_idClaseProfeMB_a the claseProfe_idClaseProfeMB_a to set
     */
    public void setClaseProfe_idClaseProfeMB_a(int claseProfe_idClaseProfeMB_a) {
        this.claseProfe_idClaseProfeMB_a = claseProfe_idClaseProfeMB_a;
    }

    /**
     * @return the idSubtemaProfeMB_a
     */
    public int getIdSubtemaProfeMB_a() {
        return idSubtemaProfeMB_a;
    }

    /**
     * @param idSubtemaProfeMB_a the idSubtemaProfeMB_a to set
     */
    public void setIdSubtemaProfeMB_a(int idSubtemaProfeMB_a) {
        this.idSubtemaProfeMB_a = idSubtemaProfeMB_a;
    }

    /**
     * @return the nombreSubtemaMB_a
     */
    public String getNombreSubtemaMB_a() {
        return nombreSubtemaMB_a;
    }

    /**
     * @param nombreSubtemaMB_a the nombreSubtemaMB_a to set
     */
    public void setNombreSubtemaMB_a(String nombreSubtemaMB_a) {
        this.nombreSubtemaMB_a = nombreSubtemaMB_a;
    }

    /**
     * @return the nombreSubMB_a
     */
    public String getNombreSubMB_a() {
        return nombreSubMB_a;
    }

    /**
     * @param nombreSubMB_a the nombreSubMB_a to set
     */
    public void setNombreSubMB_a(String nombreSubMB_a) {
        this.nombreSubMB_a = nombreSubMB_a;
    }

    /**
     * @return the temaProfe_idTemaProfeMB_a
     */
    public int getTemaProfe_idTemaProfeMB_a() {
        return temaProfe_idTemaProfeMB_a;
    }

    /**
     * @param temaProfe_idTemaProfeMB_a the temaProfe_idTemaProfeMB_a to set
     */
    public void setTemaProfe_idTemaProfeMB_a(int temaProfe_idTemaProfeMB_a) {
        this.temaProfe_idTemaProfeMB_a = temaProfe_idTemaProfeMB_a;
    }

    /**
     * @return the child
     */
    public boolean isChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(boolean child) {
        this.child = child;
    }

}
