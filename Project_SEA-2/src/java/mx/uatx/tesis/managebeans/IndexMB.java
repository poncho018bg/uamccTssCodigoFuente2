/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.uatx.tesis.beans.IndexBeans;
import mx.uatx.tesis.beans.TesisBEANS;
import mx.uatx.tesis.daos.IndexDAO;
import mx.uatx.tesis.daos.TesisDAO;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
//@S essionScoped
@RequestScoped
public class IndexMB {

    private List<IndexBeans> validarUsuarioExistente = new ArrayList<IndexBeans>();
    private List<SelectItem> opcs1;
    private int opcionActual_123 = -1;
    // tabla Usuarios
    private int idUsuarioMB;
    private String nombreUsuMB;
    private String apellidoPaternoMB;
    private String apellidoMaternoMB;
    private String correoMB;
    private String passwordMB;
    private String password2MB;
    private int rol_idRolMB;

    // VARIABLES PARA LA SESSION
    HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    //menu
    private boolean showButton = false;

    /**
     * Creates a new instance of IndexMB
     */
    public IndexMB() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        listRoles();
    }

    public void loginUsuario() throws IOException, Exception {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

        IndexBeans tesisBean = new IndexBeans();
        IndexDAO conection = new IndexDAO();
        String desencript = Encriptar(this.getPasswordMB());

        List<IndexBeans> usuario = conection.retriveLogin(getCorreoMB(), desencript);
        setValidarUsuarioExistente(conection.retriveLogin(getCorreoMB(), desencript));

        if (getValidarUsuarioExistente().size() != 0) {

            //Subir a sesión credenciasles
            FacesContext faceContext = null;
            HttpSession sesion = null;
            faceContext = FacesContext.getCurrentInstance();

            if (faceContext != null) {
                httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
            } else {
                System.out.println("********NO CONTEXT");
            }

            sesion = httpServletRequest.getSession();
            if (sesion != null) {

                sesion.setAttribute("sessionIDUsuario", this.subirSession());

            } else {
                System.out.println("********NO session");
            }

            setShowButton(true);

            SelectItem si3 = new SelectItem();
            setOpcs1(new ArrayList<SelectItem>());

            int a2 = 0;

            for (int x = 0; x < usuario.size(); x++) {
                a2 = usuario.get(x).getRol_idRol();
            }
            if (a2 == 1) {
                ctx.redirect("Alumnos_MiClases.xhtml");

            } else {
                ctx.redirect("Profesor_Clases.xhtml");
            }
            setShowButton(false);
        } else {

            setShowButton(false);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Datos no validos.!"));
            setShowButton(true);
            

        }
        //subirSession();
        
    }

    public void listRoles() {

        IndexDAO usuarioDAO = new IndexDAO();

        List<IndexBeans> rol = usuarioDAO.retriveListRoles();
        SelectItem si3 = new SelectItem();
        opcs1 = new ArrayList<SelectItem>();

        int a2 = 0;
        String b2 = "";

        for (int x = 0; x < rol.size(); x++) {

            a2 = rol.get(x).getIdRoles();
            b2 = rol.get(x).getDescripcionRol();

            si3 = new SelectItem(a2, b2);
            this.opcs1.add(si3);

        }
        
    }

    public void saveRegistro() throws IOException, Exception {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        int existe = 0;

        TesisDAO usuarioDAO = new TesisDAO();
        TesisBEANS usuario = new TesisBEANS();
        String encriptarPassword = Encriptar(this.getPasswordMB());
        usuario.setNombreUsu(this.getNombreUsuMB());
        usuario.setApellidoPaterno(this.getApellidoPaternoMB());
        usuario.setApellidoMaterno(this.getApellidoMaternoMB());
        usuario.setCorreo(this.getCorreoMB());
        usuario.setPassword(encriptarPassword);
        usuario.setRol_idRol(this.getOpcionActual_123());

        List<TesisBEANS> correoEx = usuarioDAO.emailExist(this.getCorreoMB());
        for (int x = 0; x < correoEx.size(); x++) {
            existe = correoEx.get(x).getCorreoExist();
        }

        if (existe != 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Alerta..!", "El correo ya esta registrado"));

        } else {

            usuarioDAO.createUsuario(usuario);
            enviarCorreo(this.getNombreUsuMB(), this.getApellidoPaternoMB(), this.getCorreoMB(), this.getPasswordMB());
            ctx.redirect("index.xhtml");
        }

    }

    public void enviarCorreo(String nombre, String apellido, String corre, String password2) throws Exception {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "alfons018pbg@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("alfons018pbg@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("" + corre + ""));
            message.setSubject("Asistencia técnica");
            message.setText("\n \n \n Estimado:  " + nombre + "  " + apellido
                    + "\n El Servicio Tecnico de SEA ta da la bienvenida. "
                    + "\n Los siguientes son tus datos para acceder:"
                    + "\n Correo:    " + corre
                    + "\n Password: " + password2 + "");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("alfons018pbg@gmail.com", "al12fo05zo1990");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public void redirectToNewAccount() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("NewAccount.xhtml");
        
    }

    public void redirectToRecuperarCuenta() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("RecuperarCuenta.xhtml");
        
    }

    public int getIdUsuario(String correo, String pass) {
        return 22;
    }

    public int subirSession() {

        IndexBeans tesisBean = new IndexBeans();
        IndexDAO conection = new IndexDAO();

        String desencript = Encriptar(this.getPasswordMB());
        List<IndexBeans> usuario = conection.retriveLoginSession(correoMB, desencript);
        int a2 = 0;

        for (int x = 0; x < usuario.size(); x++) {
            a2 = usuario.get(x).getIdUsuario();
        }

        return a2;
    }

    /**
     * @return the idUsuarioMB
     */
    public int getIdUsuarioMB() {
        return idUsuarioMB;
    }

    /**
     * @param idUsuarioMB the idUsuarioMB to set
     */
    public void setIdUsuarioMB(int idUsuarioMB) {
        this.idUsuarioMB = idUsuarioMB;
    }

    /**
     * @return the nombreUsuMB
     */
    public String getNombreUsuMB() {
        return nombreUsuMB;
    }

    /**
     * @param nombreUsuMB the nombreUsuMB to set
     */
    public void setNombreUsuMB(String nombreUsuMB) {
        this.nombreUsuMB = nombreUsuMB;
    }

    /**
     * @return the apellidoPaternoMB
     */
    public String getApellidoPaternoMB() {
        return apellidoPaternoMB;
    }

    /**
     * @param apellidoPaternoMB the apellidoPaternoMB to set
     */
    public void setApellidoPaternoMB(String apellidoPaternoMB) {
        this.apellidoPaternoMB = apellidoPaternoMB;
    }

    /**
     * @return the apellidoMaternoMB
     */
    public String getApellidoMaternoMB() {
        return apellidoMaternoMB;
    }

    /**
     * @param apellidoMaternoMB the apellidoMaternoMB to set
     */
    public void setApellidoMaternoMB(String apellidoMaternoMB) {
        this.apellidoMaternoMB = apellidoMaternoMB;
    }

    /**
     * @return the correoMB
     */
    public String getCorreoMB() {
        return correoMB;
    }

    /**
     * @param correoMB the correoMB to set
     */
    public void setCorreoMB(String correoMB) {
        this.correoMB = correoMB;
    }

    /**
     * @return the passwordMB
     */
    public String getPasswordMB() {
        return passwordMB;
    }

    /**
     * @param passwordMB the passwordMB to set
     */
    public void setPasswordMB(String passwordMB) {
        this.passwordMB = passwordMB;
    }

    /**
     * @return the password2MB
     */
    public String getPassword2MB() {
        return password2MB;
    }

    /**
     * @param password2MB the password2MB to set
     */
    public void setPassword2MB(String password2MB) {
        this.password2MB = password2MB;
    }

    /**
     * @return the rol_idRolMB
     */
    public int getRol_idRolMB() {
        return rol_idRolMB;
    }

    /**
     * @param rol_idRolMB the rol_idRolMB to set
     */
    public void setRol_idRolMB(int rol_idRolMB) {
        this.rol_idRolMB = rol_idRolMB;
    }

    /**
     * @return the opcs1
     */
    public List<SelectItem> getOpcs1() {
        return opcs1;
    }

    /**
     * @param opcs1 the opcs1 to set
     */
    public void setOpcs1(List<SelectItem> opcs1) {
        this.opcs1 = opcs1;
    }

    /**
     * @return the opcionActual_123
     */
    public int getOpcionActual_123() {
        return opcionActual_123;
    }

    /**
     * @param opcionActual_123 the opcionActual_123 to set
     */
    public void setOpcionActual_123(int opcionActual_123) {
        this.opcionActual_123 = opcionActual_123;
    }

    /**
     * @return the validarUsuarioExistente
     */
    public List<IndexBeans> getValidarUsuarioExistente() {
        return validarUsuarioExistente;
    }

    /**
     * @param validarUsuarioExistente the validarUsuarioExistente to set
     */
    public void setValidarUsuarioExistente(List<IndexBeans> validarUsuarioExistente) {
        this.validarUsuarioExistente = validarUsuarioExistente;
    }

    /**
     * @return the showButton
     */
    public boolean isShowButton() {
        return showButton;
    }

    /**
     * @param showButton the showButton to set
     */
    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }

}
