/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;


import javax.faces.bean.ViewScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import mx.uatx.tesis.beans.TemarioProBeans;
import mx.uatx.tesis.daos.TemarioProDAO;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import javax.servlet.http.HttpSession;


/**
 *
 * @author tlp_0_000
 */
@ManagedBean

@ViewScoped

public class TemarioProMB implements Serializable {

    //private HtmlCommandButton button;
    //private boolean disable;
    private boolean btnEliminar;
    private boolean btnEnable;
    private boolean actualizandoTemario = false;
    private boolean actualizandosubTemas = false;

    //VARIABLES PARA LA SESSION
    private String claseXX;
    private String temaXX;
    private String childXX;
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    //MOSTRAR
    private int idMB;
    private String nombreMB;
    private int referenciaMB;

    // TreeNode instance
    private TreeNode singleSelectedTreeNode;
    private TreeNode root;
    private String idTemas;
    private String idSubTemas;
    private String nivelParent;
    private int tamanioTree;

    // tabla Temaprofesor
    private int idTemaProfeMB;
    private String nombreTemaMB;
    private String nombreTemaMB2;
    private int claseProfe_idClaseProfeMB;
    private List<TemarioProBeans> listTema = new ArrayList<TemarioProBeans>();

    // tabla subtemaProfesor
    private int idSubtemaProfeMB;
    private String nombreSubtemaMB;
    private String nombreSubMB;
    private int temaProfe_idTemaProfeMB;
    private List<TemarioProBeans> listSubTema = new ArrayList<TemarioProBeans>();

    private boolean child;
// tabla clase Alumnos
    private int idClaseAlumno_caMB;
    private int usuarios_idusuarios_caMB;
    private int claseProfesor_idClaseProfesor_caMB;
    private List<TemarioProBeans> listInscritos = new ArrayList<TemarioProBeans>();
    private List<TemarioProBeans> listCalificar = new ArrayList<TemarioProBeans>();

    private boolean editarTema = true;
    private boolean editarSubtema = true;

    /**
     * Creates a new instance of TemarioProMB
     */
    public TemarioProMB() {

        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        downSession1();
        listarTemario();

        TemarioProDAO inscritos = new TemarioProDAO();
        //int idClase = downSession1();
        int idClase = Integer.parseInt(getClaseXX());
        setListInscritos(inscritos.retriveListAlumnosInscritos(idClase));

    }

    public int downSession1() {

        if (httpServletRequest.getSession().getAttribute("sessionIDClase") != null) {
            setClaseXX(this.httpServletRequest.getSession().getAttribute("sessionIDClase").toString());

        }
        int idClase = 0;//Integer.parseInt(getClaseXX());

        return idClase;
    }

    public String downSession2() {
        if (httpServletRequest.getSession().getAttribute("sessionVarIdTemas") != null) {
            setTemaXX(this.httpServletRequest.getSession().getAttribute("sessionVarIdTemas").toString());

        }
        String idTemas = getTemaXX();

        return idTemas;
    }

    public boolean downSession3() {
        if (httpServletRequest.getSession().getAttribute("sessionVarChild") != null) {
            setChildXX(this.httpServletRequest.getSession().getAttribute("sessionVarChild").toString());

        }

        boolean a;
        if (getChildXX().equals("true")) {
            a = true;
        } else {
            a = false;
        }
        return a;
    }

    public int listarTemario() {
        //Bajar credenciales
        //int idClase = downSession1();
        int idClase =  Integer.parseInt(getClaseXX());


        TemarioProDAO treeDao = new TemarioProDAO();
        List<TemarioProBeans> rol = treeDao.retriveListTemaPro(idClase);
        List<TemarioProBeans> subtema = treeDao.retriveListSUBTemaPro();

        // This is the root node, so it's data is root and its parent is null
        this.root = new DefaultTreeNode("Root Node", "", null);

        for (int x = 0; x < rol.size(); x++) {
            setIdTemaProfeMB(rol.get(x).getIdTemaProfe());
            setNombreTemaMB(rol.get(x).getNombreTema());
            setClaseProfe_idClaseProfeMB(rol.get(x).getClaseProfe_idClaseProfe());

            // Create child node
            TreeNode child = new DefaultTreeNode(idTemaProfeMB + " " + nombreTemaMB, this.root);

            for (int y = 0; y < subtema.size(); y++) {
                setIdSubtemaProfeMB(subtema.get(y).getIdSubtemaProfe());
                setNombreSubtemaMB(subtema.get(y).getNombreSubtema());
                setTemaProfe_idTemaProfeMB(subtema.get(y).getTemaProfe_idTemaProfe());

                if (idTemaProfeMB == temaProfe_idTemaProfeMB) {
                    // Reference the parent of child node
                    child.setParent(this.root);
                    // Create descendent nodes
                    TreeNode descendent = new DefaultTreeNode(idSubtemaProfeMB + " " + nombreSubtemaMB, child);
                    // Reference the parent of descendent node
                    descendent.setParent(child);

                }

            }

        }

        return 0;
    }

    public void temaVer() {
        int idActual = 0;
        TemarioProDAO temaDao = new TemarioProDAO();
        TemarioProBeans temaBeans = new TemarioProBeans();

        idActual = Integer.parseInt(downSession2());

        temaDao.retriveListTemaProID(idActual);

        setListTema(temaDao.retriveListTemaProID(idActual));

        for (int x = 0; x < listTema.size(); x++) {
            TemarioProBeans tema = (TemarioProBeans) listTema.get(x);
            setIdMB(tema.getIdTemaProfe());
            setNombreMB(tema.getNombreTema());
            setReferenciaMB(tema.getClaseProfe_idClaseProfe());
        }
        
    }

    public void temaVerEditar() {

        int idActual = 0;
        TemarioProDAO temaDao = new TemarioProDAO();
        TemarioProBeans temaBeans = new TemarioProBeans();

        idActual = Integer.parseInt(downSession2());

        temaDao.retriveListTemaProID(idActual);

        setListTema(temaDao.retriveListTemaProID(idActual));

        for (int x = 0; x < listTema.size(); x++) {
            TemarioProBeans tema = (TemarioProBeans) listTema.get(x);
            setIdMB(tema.getIdTemaProfe());
            setNombreTemaMB2(tema.getNombreTema());
            setReferenciaMB(tema.getClaseProfe_idClaseProfe());
        }
        setEditarTema(false);
    }

    public void subTemaVer() {
        int idActual = 0;
        TemarioProDAO subTemaDao = new TemarioProDAO();
        TemarioProBeans subTemaBeans = new TemarioProBeans();

        idActual = Integer.parseInt(downSession2());

        subTemaDao.retriveListSUBTemaProID(idActual);

        setListSubTema(subTemaDao.retriveListSUBTemaProID(idActual));

        for (int x = 0; x < listSubTema.size(); x++) {
            TemarioProBeans subTema = (TemarioProBeans) listSubTema.get(x);
            setIdMB(subTema.getIdSubtemaProfe());
            setNombreMB(subTema.getNombreSubtema());
            setReferenciaMB(subTema.getTemaProfe_idTemaProfe());

        }
        
    }

    public void subTemaVerEditar() {
        int idActual = 0;
        TemarioProDAO subTemaDao = new TemarioProDAO();
        TemarioProBeans subTemaBeans = new TemarioProBeans();

        idActual = Integer.parseInt(downSession2());

        subTemaDao.retriveListSUBTemaProID(idActual);

        setListSubTema(subTemaDao.retriveListSUBTemaProID(idActual));

        for (int x = 0; x < listSubTema.size(); x++) {
            TemarioProBeans subTema = (TemarioProBeans) listSubTema.get(x);
            setIdMB(subTema.getIdSubtemaProfe());
            setNombreSubMB(subTema.getNombreSubtema());
            setReferenciaMB(subTema.getTemaProfe_idTemaProfe());

        }
        setEditarSubtema(false);
    }

    public void saveTemaPro() {

        TemarioProDAO temaDAO = new TemarioProDAO();
        TemarioProBeans tema = new TemarioProBeans();

        if (isEditarTema()) {

            tema.setNombreTema(this.getNombreTemaMB2());

            int idClase = Integer.parseInt(getClaseXX());
            temaDAO.createTemaPro(tema, idClase);
        } else {

            temaDAO.TemaEDITAR(this.getNombreTemaMB2(), getIdMB());

        }

        setNombreTemaMB2("");
        setEditarTema(true);

        listarTemario();

    }

    public void saveSubTemaPro() throws IOException {

        if (isChild() == true) {

        } else {

            TemarioProDAO subtemaDAO = new TemarioProDAO();
            TemarioProBeans subtema = new TemarioProBeans();
            subtema.setNombreSub(this.getNombreSubMB());

            int idTema = Integer.parseInt(downSession2());

            if (isEditarSubtema()) {

                subtemaDAO.createSubTemaPro(subtema, idTema);

            } else {

                subtemaDAO.SubTemaEDITAR(this.getNombreSubMB(), getIdMB());
            }

        }
        setEditarSubtema(true);
        listarTemario();

        
    }

    public void passParamEliminar() {

        int idActual = 0;
        TemarioProDAO subtemaDAO = new TemarioProDAO();

        boolean e = downSession3();
        if (e == true) {
            idActual = Integer.parseInt(downSession2());
            if (subtemaDAO.retriveListSubTemaEliminar(idActual)) {

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Exito..!", "SUBTEMA ELIMINADO "));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Alerta..!", "ELIMINA LOS EJERCICIOS ASOCIADOS "));
            }
        } else {

            idActual = Integer.parseInt(downSession2());
            if (subtemaDAO.retriveListTemaEliminar(idActual)) {

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Exito..!", "TEMA ELIMINADO "));

            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Alerta..!", "ELIMINA LOS SUBTEMAS ASOCIADOS "));

            }

        }
        listarTemario();

        
    }

    public void passParamEditar() {

        int idActual = 0;
        idActual = Integer.parseInt(downSession2());
        TemarioProDAO subtemaDAO = new TemarioProDAO();
        boolean value = downSession3();
        if (value == true) {
            // EDITAR CHILD

            setActualizandosubTemas(true);

            subTemaVerEditar();

        } else {
            //** EDITAR ROOT

            setActualizandoTemario(true);
            temaVerEditar();

        }
        listarTemario();

     
    }

    public void actualizarTema() {

        int idActual = 0;
        idActual = Integer.parseInt(downSession2());
        TemarioProDAO subtemaDAO = new TemarioProDAO();

        //** EDITAR ROOT
        if (subtemaDAO.TemaEDITAR(getNombreTemaMB2(), idActual)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito..!", "TEMA Actualizado "));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error..!", "Un error a ocurrido, Por favor intenta otra vez! "));
        }

        listarTemario();

        
    }

    public void actualizarSubTema() {

        int idActual = 0;
        idActual = Integer.parseInt(downSession2());
        TemarioProDAO subtemaDAO = new TemarioProDAO();

        // EDITAR CHILD
        if (subtemaDAO.SubTemaEDITAR(getNombreTemaMB2(), idActual)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Exito..!", "SUBTEMA Actualizado "));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error..!", "Un error a ocurrido, Por favor intenta otra vez! "));
        }

        listarTemario();

        
    }

    public void upSession1(String idTemas) {
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
            sesion.setAttribute("sessionVarIdTemas", idTemas);
        } else {

        }
        
    }

    public void upSession3(String esChild) {
        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("******NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {
            sesion.setAttribute("sessionVarChild", esChild);
        } else {
            System.out.println("*******NO session");
        }
        
    }

    public void redirectBackToClass() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_Clases.xhtml");
        
    }

    public void redirectToListEjercicios() throws IOException {
        int idActual = 0;

        idActual = Integer.parseInt(getTemaXX());

        //Subir a sesión credenciasles
        FacesContext faceContext = null;
        HttpSession sesion = null;
        faceContext = FacesContext.getCurrentInstance();

        if (faceContext != null) {

            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        } else {
            System.out.println("*******NO CONTEXT");
        }

        sesion = httpServletRequest.getSession();
        if (sesion != null) {

            sesion.setAttribute("sessionIDSubtema", idActual);

        } else {
            System.out.println("**********NO session");
        }

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("Profesor_AgregarEjercicio.xhtml");

        
    }

    public void onNodeSelect(NodeSelectEvent event) {

        String idActual = "" + event.getTreeNode().getData();
        String nivelActual = "" + event.getTreeNode().getParent().getType();
        int tamanio = event.getTreeNode().getChildCount();
        setTamanioTree(tamanio);
        setNivelParent(nivelActual);
        StringTokenizer token = new StringTokenizer(idActual, " ");

        if (nivelActual.equals("Root Node")) {
            for (int i = 1; i < 2; i++) {
                String tok_1 = token.nextToken();
                String tok_2 = token.nextToken();
                upSession1(tok_1);
            }

            setBtnEliminar(true);
            setBtnEnable(false);
            upSession3("false");

            temaVer();

        } else {

            for (int i = 1; i < 2; i++) {
                String tok_1 = token.nextToken();
                String tok_2 = token.nextToken();

                upSession1(tok_1);
            }

            setBtnEliminar(false);
            setBtnEnable(true);

            upSession3("true");

            subTemaVer();

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
     * @return the idTemaProfeMB
     */
    public int getIdTemaProfeMB() {
        return idTemaProfeMB;
    }

    /**
     * @param idTemaProfeMB the idTemaProfeMB to set
     */
    public void setIdTemaProfeMB(int idTemaProfeMB) {
        this.idTemaProfeMB = idTemaProfeMB;
    }

    /**
     * @return the nombreTemaMB
     */
    public String getNombreTemaMB() {
        return nombreTemaMB;
    }

    /**
     * @param nombreTemaMB the nombreTemaMB to set
     */
    public void setNombreTemaMB(String nombreTemaMB) {
        this.nombreTemaMB = nombreTemaMB;
    }

    /**
     * @return the claseProfe_idClaseProfeMB
     */
    public int getClaseProfe_idClaseProfeMB() {
        return claseProfe_idClaseProfeMB;
    }

    /**
     * @param claseProfe_idClaseProfeMB the claseProfe_idClaseProfeMB to set
     */
    public void setClaseProfe_idClaseProfeMB(int claseProfe_idClaseProfeMB) {
        this.claseProfe_idClaseProfeMB = claseProfe_idClaseProfeMB;
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
     * @return the idSubtemaProfeMB
     */
    public int getIdSubtemaProfeMB() {
        return idSubtemaProfeMB;
    }

    /**
     * @param idSubtemaProfeMB the idSubtemaProfeMB to set
     */
    public void setIdSubtemaProfeMB(int idSubtemaProfeMB) {
        this.idSubtemaProfeMB = idSubtemaProfeMB;
    }

    /**
     * @return the nombreSubtemaMB
     */
    public String getNombreSubtemaMB() {
        return nombreSubtemaMB;
    }

    /**
     * @param nombreSubtemaMB the nombreSubtemaMB to set
     */
    public void setNombreSubtemaMB(String nombreSubtemaMB) {
        this.nombreSubtemaMB = nombreSubtemaMB;
    }

    /**
     * @return the temaProfe_idTemaProfeMB
     */
    public int getTemaProfe_idTemaProfeMB() {
        return temaProfe_idTemaProfeMB;
    }

    /**
     * @param temaProfe_idTemaProfeMB the temaProfe_idTemaProfeMB to set
     */
    public void setTemaProfe_idTemaProfeMB(int temaProfe_idTemaProfeMB) {
        this.temaProfe_idTemaProfeMB = temaProfe_idTemaProfeMB;
    }

    /**
     * @return the idTemas
     */
    public String getIdTemas() {
        return idTemas;
    }

    /**
     * @param idTemas the idTemas to set
     */
    public void setIdTemas(String idTemas) {
        this.idTemas = idTemas;
    }

    /**
     * @return the nivelParent
     */
    public String getNivelParent() {
        return nivelParent;
    }

    /**
     * @param nivelParent the nivelParent to set
     */
    public void setNivelParent(String nivelParent) {
        this.nivelParent = nivelParent;
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
     * @return the listTema
     */
    public List<TemarioProBeans> getListTema() {
        return listTema;
    }

    /**
     * @param listTema the listTema to set
     */
    public void setListTema(List<TemarioProBeans> listTema) {
        this.listTema = listTema;
    }

    /**
     * @return the listSubTema
     */
    public List<TemarioProBeans> getListSubTema() {
        return listSubTema;
    }

    /**
     * @param listSubTema the listSubTema to set
     */
    public void setListSubTema(List<TemarioProBeans> listSubTema) {
        this.listSubTema = listSubTema;
    }

    /**
     * @return the idMB
     */
    public int getIdMB() {
        return idMB;
    }

    /**
     * @param idMB the idMB to set
     */
    public void setIdMB(int idMB) {
        this.idMB = idMB;
    }

    /**
     * @return the nombreMB
     */
    public String getNombreMB() {
        return nombreMB;
    }

    /**
     * @param nombreMB the nombreMB to set
     */
    public void setNombreMB(String nombreMB) {
        this.nombreMB = nombreMB;
    }

    /**
     * @return the referenciaMB
     */
    public int getReferenciaMB() {
        return referenciaMB;
    }

    /**
     * @param referenciaMB the referenciaMB to set
     */
    public void setReferenciaMB(int referenciaMB) {
        this.referenciaMB = referenciaMB;
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
     * @return the nombreSubMB
     */
    public String getNombreSubMB() {
        return nombreSubMB;
    }

    /**
     * @param nombreSubMB the nombreSubMB to set
     */
    public void setNombreSubMB(String nombreSubMB) {
        this.nombreSubMB = nombreSubMB;
    }

    /**
     * @return the idSubTemas
     */
    public String getIdSubTemas() {
        return idSubTemas;
    }

    /**
     * @param idSubTemas the idSubTemas to set
     */
    public void setIdSubTemas(String idSubTemas) {
        this.idSubTemas = idSubTemas;
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

    /**
     * @return the tamanioTree
     */
    public int getTamanioTree() {
        return tamanioTree;
    }

    /**
     * @param tamanioTree the tamanioTree to set
     */
    public void setTamanioTree(int tamanioTree) {
        this.tamanioTree = tamanioTree;
    }

    /**
     * @return the btnEnable
     */
    public boolean isBtnEnable() {
        return btnEnable;
    }

    /**
     * @param btnEnable the btnEnable to set
     */
    public void setBtnEnable(boolean btnEnable) {
        this.btnEnable = btnEnable;
    }

    /**
     * @return the btnEliminar
     */
    public boolean isBtnEliminar() {
        return btnEliminar;
    }

    /**
     * @param btnEliminar the btnEliminar to set
     */
    public void setBtnEliminar(boolean btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * @return the nombreTemaMB2
     */
    public String getNombreTemaMB2() {
        return nombreTemaMB2;
    }

    /**
     * @param nombreTemaMB2 the nombreTemaMB2 to set
     */
    public void setNombreTemaMB2(String nombreTemaMB2) {
        this.nombreTemaMB2 = nombreTemaMB2;
    }

    /**
     * @return the idClaseAlumno_caMB
     */
    public int getIdClaseAlumno_caMB() {
        return idClaseAlumno_caMB;
    }

    /**
     * @param idClaseAlumno_caMB the idClaseAlumno_caMB to set
     */
    public void setIdClaseAlumno_caMB(int idClaseAlumno_caMB) {
        this.idClaseAlumno_caMB = idClaseAlumno_caMB;
    }

    /**
     * @return the usuarios_idusuarios_caMB
     */
    public int getUsuarios_idusuarios_caMB() {
        return usuarios_idusuarios_caMB;
    }

    /**
     * @param usuarios_idusuarios_caMB the usuarios_idusuarios_caMB to set
     */
    public void setUsuarios_idusuarios_caMB(int usuarios_idusuarios_caMB) {
        this.usuarios_idusuarios_caMB = usuarios_idusuarios_caMB;
    }

    /**
     * @return the claseProfesor_idClaseProfesor_caMB
     */
    public int getClaseProfesor_idClaseProfesor_caMB() {
        return claseProfesor_idClaseProfesor_caMB;
    }

    /**
     * @param claseProfesor_idClaseProfesor_caMB the
     * claseProfesor_idClaseProfesor_caMB to set
     */
    public void setClaseProfesor_idClaseProfesor_caMB(int claseProfesor_idClaseProfesor_caMB) {
        this.claseProfesor_idClaseProfesor_caMB = claseProfesor_idClaseProfesor_caMB;
    }

    /**
     * @return the listInscritos
     */
    public List<TemarioProBeans> getListInscritos() {
        return listInscritos;
    }

    /**
     * @param listInscritos the listInscritos to set
     */
    public void setListInscritos(List<TemarioProBeans> listInscritos) {
        this.listInscritos = listInscritos;
    }

    /**
     * @return the listCalificar
     */
    public List<TemarioProBeans> getListCalificar() {
        return listCalificar;
    }

    /**
     * @param listCalificar the listCalificar to set
     */
    public void setListCalificar(List<TemarioProBeans> listCalificar) {
        this.listCalificar = listCalificar;
    }

    /**
     * @return the actualizandoTemario
     */
    public boolean isActualizandoTemario() {
        return actualizandoTemario;
    }

    /**
     * @param actualizandoTemario the actualizandoTemario to set
     */
    public void setActualizandoTemario(boolean actualizandoTemario) {
        this.actualizandoTemario = actualizandoTemario;
    }

    /**
     * @return the actualizandosubTemas
     */
    public boolean isActualizandosubTemas() {
        return actualizandosubTemas;
    }

    /**
     * @param actualizandosubTemas the actualizandosubTemas to set
     */
    public void setActualizandosubTemas(boolean actualizandosubTemas) {
        this.actualizandosubTemas = actualizandosubTemas;
    }

    /**
     * @return the editarTema
     */
    public boolean isEditarTema() {
        return editarTema;
    }

    /**
     * @param editarTema the editarTema to set
     */
    public void setEditarTema(boolean editarTema) {
        this.editarTema = editarTema;
    }

    /**
     * @return the editarSubtema
     */
    public boolean isEditarSubtema() {
        return editarSubtema;
    }

    /**
     * @param editarSubtema the editarSubtema to set
     */
    public void setEditarSubtema(boolean editarSubtema) {
        this.editarSubtema = editarSubtema;
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
     * @return the childXX
     */
    public String getChildXX() {
        return childXX;
    }

    /**
     * @param childXX the childXX to set
     */
    public void setChildXX(String childXX) {
        this.childXX = childXX;
    }

}
