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
import mx.uatx.tesis.beans.CasoPruebaProBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class CasoPruebaProDAO {

    public int createCasoPruebaPro(CasoPruebaProBeans casoPrueba, int idEjercicio) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "INSERT INTO casoPrueba(idcasoPrueba, ejercicioProfesor_idejercicioProfesor,resultado)"
                + "VALUES(null,'" + idEjercicio + "','" + casoPrueba.getResultadoPRO() + "');";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public List<CasoPruebaProBeans> retriveListEjercicioPro(int id) {
        ResultSet rs = null;
        String query = "";
        List<CasoPruebaProBeans> casoPruebaList = new ArrayList<CasoPruebaProBeans>();
        CasoPruebaProBeans casoPrueba = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idcasoPrueba, ejercicioProfesor_idejercicioProfesor,resultado"
                + " FROM casoPrueba WHERE ejercicioProfesor_idejercicioProfesor = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPrueba = new CasoPruebaProBeans();
                casoPrueba.setIdCasoPruebaPRO(rs.getInt(1));
                casoPrueba.setEjercicio_idEjercicioPRO(rs.getInt(2));
                casoPrueba.setResultadoPRO(rs.getString(3));

                casoPruebaList.add(casoPrueba);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return casoPruebaList;

    }

    public int createParametrosPro(CasoPruebaProBeans parametros, int idcasoPrueba) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "INSERT INTO parametros(idparametros, casoPrueba_idcasoPrueba,tipo,nombreVariable,valor)"
                + "VALUES(null,'" + idcasoPrueba + "','" + parametros.getTipoPRO() + "','"
                + parametros.getNombreVariablePRO() + "','" + parametros.getValorPRO() + "');";

        try {
            conn.insert(query);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return 1;
    }

    public List<CasoPruebaProBeans> retriveListParametros(int id) {
        ResultSet rs = null;
        String query = "";
        List<CasoPruebaProBeans> parametrosList = new ArrayList<CasoPruebaProBeans>();
        CasoPruebaProBeans parametros = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idparametros, casoPrueba_idcasoPrueba, tipo, nombreVariable, valor"
                + " FROM parametros WHERE casoPrueba_idcasoPrueba = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                parametros = new CasoPruebaProBeans();

                parametros.setIdParametroPRO(rs.getInt(1));
                parametros.setCaso_idCasoPruebaPRO(rs.getInt(2));
                parametros.setTipoPRO(rs.getString(3));
                parametros.setNombreVariablePRO(rs.getString(4));
                parametros.setValorPRO(rs.getString(5));

                parametrosList.add(parametros);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return parametrosList;

    }

    public List<CasoPruebaProBeans> parametrosEditar(int id) {
        ResultSet rs = null;
        String query = "";
        List<CasoPruebaProBeans> parametrosList = new ArrayList<CasoPruebaProBeans>();
        CasoPruebaProBeans parametros = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idparametros, casoPrueba_idcasoPrueba, tipo, nombreVariable, valor"
                + " FROM parametros WHERE idparametros = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                parametros = new CasoPruebaProBeans();

                parametros.setIdParametroPRO(rs.getInt(1));
                parametros.setCaso_idCasoPruebaPRO(rs.getInt(2));
                parametros.setTipoPRO(rs.getString(3));
                parametros.setNombreVariablePRO(rs.getString(4));
                parametros.setValorPRO(rs.getString(5));

                parametrosList.add(parametros);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return parametrosList;

    }

    public boolean deleteSalida(int id) {
        ResultSet rs = null;
        String query = "";
        boolean error;

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "DELETE FROM casoPrueba WHERE idcasoPrueba= " + id + ";";

        try {
            conn.insert(query);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = false;
        }

        return error;
    }

    public boolean deleteParametros(int id) {
        ResultSet rs = null;
        String query = "";
        boolean error;

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "DELETE FROM parametros WHERE idparametros= " + id + ";";

        try {
            conn.insert(query);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassProDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = false;
        }

        return error;
    }

    public List<CasoPruebaProBeans> retriveEditarSalida(int id) {
        ResultSet rs = null;
        String query = "";
        List<CasoPruebaProBeans> casoPruebaList = new ArrayList<CasoPruebaProBeans>();
        CasoPruebaProBeans casoPrueba = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idcasoPrueba, ejercicioProfesor_idejercicioProfesor,resultado"
                + " FROM casoPrueba WHERE idcasoPrueba = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPrueba = new CasoPruebaProBeans();
                casoPrueba.setIdCasoPruebaPRO(rs.getInt(1));
                casoPrueba.setEjercicio_idEjercicioPRO(rs.getInt(2));
                casoPrueba.setResultadoPRO(rs.getString(3));

                casoPruebaList.add(casoPrueba);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return casoPruebaList;

    }

    public void upDateSalida(CasoPruebaProBeans parametros) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "UPDATE casoPrueba SET resultado = '" + parametros.getResultadoPRO()
                + "' WHERE " + " idCasoPrueba = " + parametros.getIdCasoPruebaPRO()
                + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

    }

    public void upDateParametro(CasoPruebaProBeans parametros) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "UPDATE parametros SET tipo = '" + parametros.getTipoPRO()
                + "', nombreVariable= '" + parametros.getNombreVariablePRO()
                + "', valor='" + parametros.getValorPRO()
                + "'  WHERE " + " idparametros = " + parametros.getIdParametroPRO()
                + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

    }
}
