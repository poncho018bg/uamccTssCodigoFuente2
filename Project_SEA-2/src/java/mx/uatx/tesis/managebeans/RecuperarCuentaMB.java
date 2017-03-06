/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.managebeans;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import mx.uatx.tesis.beans.RecuperarCuentaBeans;
import mx.uatx.tesis.daos.RecuperarCuentaDAO;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author tlp_0_000
 */
@ManagedBean
@RequestScoped
public class RecuperarCuentaMB {

    private int idUsuario;
    private String nombre;
    private String paterno;
    private String materno;

    private String correo;
    private String password;

    private int rol;

    /**
     * Creates a new instance of RecuperarCuentaMB
     */
    public RecuperarCuentaMB() {
    }

    public void existeRegistro() throws IOException, Exception {

        RecuperarCuentaDAO recuperar = new RecuperarCuentaDAO();

        if (recuperar.retriveLogin(correo).size() != 0) {

            List<RecuperarCuentaBeans> recuperarCuenta = recuperar.retriveLogin(correo);

            for (int x = 0; x < recuperarCuenta.size(); x++) {
                setIdUsuario(recuperarCuenta.get(x).getIdUsuario());
                setNombre(recuperarCuenta.get(x).getNombre());
                setPaterno(recuperarCuenta.get(x).getParterno());
                setMaterno(recuperarCuenta.get(x).getMaterno());
                setCorreo(recuperarCuenta.get(x).getCorreo());
                setPassword(recuperarCuenta.get(x).getPassword());
                setRol(recuperarCuenta.get(x).getRol());
            }
            enviarCorreo(getNombre(), getPaterno(), getCorreo(), getPassword());
            redirectToIndex();

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Datos no validos.!"));

        }

        
    }

    public void redirectToRecuperarCuenta() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("RecuperarCuenta.xhtml");
        
    }

    public String redirectToIndex() throws IOException {

        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        ctx.redirect("index.xhtml");
        return "index.xhtml";
    }

    public void enviarCorreo(String nombre, String apellido, String corre, String password2) throws Exception {

        String passwordDesencriptada = Desencriptar(password2);

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
                    + "\n El Servicio Tecnico de SEA ha recibido tu solicitud. "
                    + "\n Los siguientes son tus datos para acceder:"
                    + "\n Correo:    " + corre
                    + "\n Password: " + passwordDesencriptada + "");

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

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the parterno
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * @param parterno the parterno to set
     */
    public void setPaterno(String parterno) {
        this.paterno = parterno;
    }

    /**
     * @return the materno
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * @param materno the materno to set
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * @return the rol
     */
    public int getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

}
