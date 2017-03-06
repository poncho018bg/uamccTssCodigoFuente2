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
import mx.uatx.tesis.beans.ClassProBeans;
//import mx.uatx.tesis.beans.IndexBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class ClassProDAO {

    public int createClasePro(ClassProBeans clase, int id) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + clase.getNombreClass() + "'";

        query = "INSERT INTO claseProfesor(idClaseProfesor,usuarios_idUsuarios,nombreCLase) "
                + "Values (null," + id + "," + campos + ")";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public int UpdateclaseProf(ClassProBeans campos) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE claseProfesor SET nombreClase = '" + campos.getNombreClass() + "' "
                + " WHERE " + " idClaseProfesor = " + campos.getIdClaseProfesor() + "  AND usuarios_idusuarios= " + campos.getUsuario_idUsuario() + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<ClassProBeans> retriveListClasePro(int id) {
        ResultSet rs = null;
        String query = "";
        List<ClassProBeans> claseList = new ArrayList<ClassProBeans>();
        ClassProBeans clase = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idClaseProfesor,usuarios_idUsuarios,nombreClase FROM claseProfesor WHERE usuarios_idUsuarios = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                clase = new ClassProBeans();
                clase.setIdClaseProfesor(rs.getInt(1));
                clase.setUsuario_idUsuario(rs.getInt(2));
                clase.setNombreClass(rs.getString(3));

                claseList.add(clase);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return claseList;

    }

    public List<ClassProBeans> retriveClaseProEditar(int id, int idClase) {
        ResultSet rs = null;
        String query = "";
        List<ClassProBeans> claseList = new ArrayList<ClassProBeans>();
        ClassProBeans clase = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idClaseProfesor,usuarios_idUsuarios,nombreClase FROM claseProfesor "
                + " WHERE usuarios_idUsuarios = " + id + " "
                + " AND idClaseProfesor = " + idClase + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                clase = new ClassProBeans();
                clase.setIdClaseProfesor(rs.getInt(1));
                clase.setUsuario_idUsuario(rs.getInt(2));
                clase.setNombreClass(rs.getString(3));

                claseList.add(clase);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return claseList;

    }

    public boolean retriveListClaseEliminar(int idClase) {
        ResultSet rs = null;
        String query = "";
        ClassProBeans clase = null;
        boolean error2;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "DELETE FROM claseProfesor WHERE idClaseProfesor= " + idClase + ";";

        try {
            conn.insert(query);
            error2 = true;
        } catch (SQLException ex) {

//            System.out.println(ex);
            error2 = false;
        }

        return error2;
    }

}
