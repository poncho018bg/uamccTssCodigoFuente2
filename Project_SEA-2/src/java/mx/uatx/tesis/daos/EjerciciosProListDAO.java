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
import mx.uatx.tesis.beans.EjerciciosProListBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class EjerciciosProListDAO {

    public List<EjerciciosProListBeans> retriveListEjercicioPro(int id) {
        ResultSet rs = null;
        String query = "";
        List<EjerciciosProListBeans> ejercicioList = new ArrayList<EjerciciosProListBeans>();
        EjerciciosProListBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioProfesor, subtemaProfesor_idsubtemaprofesor,nombre,descripcion,sugerencia,codigoFuente, tiempoTotal, intentosTotal"
                + " FROM ejercicioProfesor WHERE subtemaProfesor_idsubtemaprofesor = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new EjerciciosProListBeans();

                exercise.setIdEjercicioProfe(rs.getInt(1));
                exercise.setSubtemaProfe_idSubtemaProfe(rs.getInt(2));
                exercise.setNombre(rs.getString(3));
                exercise.setDescripcionSub(rs.getString(4));
                exercise.setSugerenciaSub(rs.getString(5));
                exercise.setCodigoFuenteSub(rs.getString(6));
                exercise.setTiempitotal(rs.getString(7));
                exercise.setIntentosTotal(rs.getInt(8));

                ejercicioList.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return ejercicioList;

    }

    public boolean deleteEjercicio(int id) {
        ResultSet rs = null;
        String query = "";
        boolean error;

        EjerciciosProListBeans exercise = null;
        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "DELETE FROM ejercicioProfesor WHERE idejercicioProfesor= " + id + ";";

        try {
            conn.insert(query);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = false;
        }

        return error;
    }

    public List<EjerciciosProListBeans> retriveCasosEliminar(int id) {
        ResultSet rs = null;
        String query = "";
        List<EjerciciosProListBeans> ejercicioList = new ArrayList<EjerciciosProListBeans>();
        EjerciciosProListBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idcasoPrueba"
                + " FROM casoPrueba WHERE ejerciciProfesor_idEjercicioProfesor = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new EjerciciosProListBeans();

                exercise.setIdCasoPruebaEliminar(rs.getInt(1));

                ejercicioList.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return ejercicioList;

    }

    public List<EjerciciosProListBeans> retriveParametrosEliminar(int id) {
        ResultSet rs = null;
        String query = "";
        List<EjerciciosProListBeans> ejercicioList = new ArrayList<EjerciciosProListBeans>();
        EjerciciosProListBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idparametros"
                + " FROM parametros WHERE casoprueba_idcasoPrueba = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new EjerciciosProListBeans();

                exercise.setIdParametrosEliminar(rs.getInt(1));

                ejercicioList.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return ejercicioList;

    }

}
