/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mx.uatx.tesis.beans.BuildExerciseProBeans;

import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class BuildExerciseProDAO {

    public int createExercisePro(BuildExerciseProBeans ejercicio, int idSubtema) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "INSERT INTO ejercicioProfesor(idejercicioProfesor, subtemaProfesor_idsubtemaprofesor,nombre, descripcion,sugerencia,codigoFuente, tiempoTotal, intentosTotal,tipo)"
                + "VALUES(null,'" + idSubtema + "','" + ejercicio.getNombreSub() + "','"
                + ejercicio.getDescripcionSub() + "','" + ejercicio.getSugerenciaSub() + "','"
                + ejercicio.getCodigoFuenteSub() + "','" + ejercicio.getTiempitotal() + "','" + ejercicio.getIntentosTotal() + "','" + ejercicio.getTipo() + "');";

        try {
            conn.insert(query);
            ejercicio.setBtn1Beans(false);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
            ejercicio.setBtn1Beans(true);
        }

        return 1;
    }

    public int UpdateEjercicioProf(BuildExerciseProBeans campos) {
        ResultSet rs = null;
        String query = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE ejercicioProfesor SET nombre = '" + campos.getNombreSub()
                + "', descripcion= '" + campos.getDescripcionSub()
                + "', sugerencia= '" + campos.getSugerenciaSub()
                + "', codigoFuente= '" + campos.getCodigoFuenteSub()
                + "', tiempoTotal= '" + campos.getTiempitotal()
                + "', intentosTotal= '" + campos.getIntentosTotal()
                + "', tipo= '" + campos.getTipo()
                + "' WHERE " + " idEjercicioProfesor = " + campos.getIdEjercicioProfe()
                + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<BuildExerciseProBeans> retriveEjercicioPRO(int id) {
        ResultSet rs = null;
        String query = "";
        List<BuildExerciseProBeans> ejercicioList2 = new ArrayList<BuildExerciseProBeans>();
        BuildExerciseProBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioProfesor, subtemaProfesor_idsubtemaprofesor,nombre,descripcion,sugerencia,codigoFuente, tiempoTotal, intentosTotal, tipo"
                + " FROM ejercicioProfesor WHERE idEjercicioProfesor = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new BuildExerciseProBeans();

                exercise.setIdEjercicioProfe(rs.getInt(1));
                exercise.setSubtemaProfe_idSubtemaProfe(rs.getInt(2));
                exercise.setNombreSub(rs.getString(3));
                exercise.setDescripcionSub(rs.getString(4));
                exercise.setSugerenciaSub(rs.getString(5));
                exercise.setCodigoFuenteSub(rs.getString(6));
                exercise.setTiempitotal(rs.getString(7));
                exercise.setIntentosTotal(rs.getInt(8));
                exercise.setTipo(rs.getString(9));

                ejercicioList2.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return ejercicioList2;

    }

}
