/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uatx.tesis.beans.TemarioProBeans;
//import mx.uatx.tesis.beans.ClassProBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class TemarioProDAO {

    public int createTemaPro(TemarioProBeans tema, int idClase) {
        ResultSet rs = null;
        String query = "";
        String campos = "";
        MysqlConnect conn = MysqlConnect.getDbCon();
        campos += "'" + tema.getNombreTema() + "'";

        query = "INSERT INTO temaProfesor(idtemaProfesor,nombre,claseProfesor_idclaseProfesor) "
                + "values (null," + campos + "," + idClase + ");";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public int createSubTemaPro(TemarioProBeans subTema, int idTemaRef) {
        ResultSet rs = null;
        String query = "";
        String campos = "";
        MysqlConnect conn = MysqlConnect.getDbCon();
        campos += "'" + subTema.getNombreSub() + "'";

        query = "INSERT INTO subtemaProfesor(idsubtemaProfesor,nombre,temaProfesor_idtemaProfesor) "
                + "Values (null," + campos + "," + idTemaRef + ");";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public boolean retriveListTemaEliminar(int idTema) {
        ResultSet rs = null;
        String query = "";
        boolean error;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "DELETE FROM temaProfesor WHERE idTemaProfesor= " + idTema + ";";

        try {
            conn.insert(query);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = false;
        }

        return error;
    }

    public boolean TemaEDITAR(String nombre, int idTema) {

        ResultSet rs = null;
        String query = "";
        boolean estado = false;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "UPDATE   temaProfesor SET nombre = '" + nombre + "'  WHERE idTemaProfesor= " + idTema + ";";

        try {
            conn.insert(query);
            estado = true;
        } catch (Exception ex) {
            estado = false;
//            System.out.println(ex.getMessage());

        }

        return estado;
    }

    public boolean SubTemaEDITAR(String nombre, int idsubTema) {
        ResultSet rs = null;
        String query = "";
        boolean estado = false;
        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "UPDATE   subtemaProfesor SET nombre = '" + nombre + "'  WHERE idsubtemaProfesor= " + idsubTema + ";";

        try {
            conn.insert(query);
            estado = true;
        } catch (SQLException ex) {
            estado = false;
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return estado;
    }

    public boolean retriveListSubTemaEliminar(int idSubtema) {
        ResultSet rs = null;
        String query = "";
        boolean error;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "DELETE FROM subtemaProfesor WHERE idsubtemaProfesor= " + idSubtema + ";";

        try {
            conn.insert(query);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = false;
        }

        return error;
    }

    public List<TemarioProBeans> retriveListTemaPro(int idTemaClase) {
        ResultSet rs = null;
        String query = "";
        List<TemarioProBeans> temaList = new ArrayList<TemarioProBeans>();
        TemarioProBeans tema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idtemaProfesor,nombre,claseProfesor_idclaseProfesor FROM temaProfesor WHERE  claseProfesor_idclaseProfesor =" + idTemaClase + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                tema = new TemarioProBeans();
                tema.setIdTemaProfe(rs.getInt(1));
                tema.setNombreTema(rs.getString(2));
                tema.setClaseProfe_idClaseProfe(rs.getInt(3));
                temaList.add(tema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return temaList;
    }

    public List<TemarioProBeans> retriveListSUBTemaPro() {
        ResultSet rs = null;
        String query = "";
        List<TemarioProBeans> subTemaList = new ArrayList<TemarioProBeans>();
        TemarioProBeans subTema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idsubtemaProfesor, nombre, temaProfesor_idtemaProfesor FROM subtemaProfesor ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                subTema = new TemarioProBeans();
                subTema.setIdSubtemaProfe(rs.getInt(1));
                subTema.setNombreSubtema(rs.getString(2));
                subTema.setTemaProfe_idTemaProfe(rs.getInt(3));
                subTemaList.add(subTema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return subTemaList;
    }

    public List<TemarioProBeans> retriveListTemaProID(int idTemas) {
        ResultSet rs = null;
        String query = "";
        List<TemarioProBeans> temaListID = new ArrayList<TemarioProBeans>();
        TemarioProBeans tema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idtemaProfesor,nombre,claseProfesor_idclaseProfesor FROM temaProfesor WHERE idtemaProfesor =" + idTemas + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                tema = new TemarioProBeans();
                tema.setIdTemaProfe(rs.getInt(1));
                tema.setNombreTema(rs.getString(2));
                tema.setClaseProfe_idClaseProfe(rs.getInt(3));
                temaListID.add(tema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return temaListID;
    }

    public List<TemarioProBeans> retriveListSUBTemaProID(int idSubTema) {
        ResultSet rs = null;
        String query = "";
        List<TemarioProBeans> subTemaListID = new ArrayList<TemarioProBeans>();
        TemarioProBeans subTema = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idsubtemaProfesor, nombre, temaProfesor_idtemaProfesor FROM subtemaProfesor WHERE idsubtemaProfesor =" + idSubTema + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                subTema = new TemarioProBeans();
                subTema.setIdSubtemaProfe(rs.getInt(1));
                subTema.setNombreSubtema(rs.getString(2));
                subTema.setTemaProfe_idTemaProfe(rs.getInt(3));
                subTemaListID.add(subTema);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return subTemaListID;
    }

    public List<TemarioProBeans> retriveListAlumnosInscritos(int idclase) {
        ResultSet rs = null;
        String query = "";
        List<TemarioProBeans> alumnosInscritos = new ArrayList<TemarioProBeans>();
        TemarioProBeans inscritos = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT ca.idclasealumno, ca.usuarios_idusuarios, ca.claseprofesor_idclaseprofesor, us.idusuarios, us.nombres, us.apellidoPaterno, us.apellidoMaterno FROM clasealumno ca, usuarios us WHERE ca.claseprofesor_idclaseprofesor =" + idclase + " AND ca.usuarios_idusuarios = us.idusuarios" + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                inscritos = new TemarioProBeans();
                inscritos.setIdClaseAlumno_ca(rs.getInt(1));
                inscritos.setUsuarios_idusuarios_ca(rs.getInt(2));
                inscritos.setClaseProfesor_idClaseProfesor_ca(rs.getInt(3));
                inscritos.setIdUsuario_ca(rs.getInt(4));
                inscritos.setNombres_ca(rs.getString(5));
                inscritos.setPaterno_ca(rs.getString(6));
                inscritos.setMaterno_ca(rs.getString(7));

                alumnosInscritos.add(inscritos);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return alumnosInscritos;
    }

}
