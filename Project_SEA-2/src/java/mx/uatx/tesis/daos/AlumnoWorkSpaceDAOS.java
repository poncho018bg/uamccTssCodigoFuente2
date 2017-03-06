/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.tesis.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.uatx.tesis.beans.AlumnoWorkSpaceBeans;
//import mx.uatx.tesis.beans.EjerciciosProListBeans;
import mx.uatx.tesis.db.MysqlConnect;

/**
 *
 * @author tlp_0_000
 */
public class AlumnoWorkSpaceDAOS {

    public List<AlumnoWorkSpaceBeans> retriveListEjercicioPro(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> ejercicioList = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idejercicioProfesor,nombre,descripcion,sugerencia,codigoFuente,tiempoTotal,intentosTotal,tipo"
                + " FROM ejercicioProfesor "
                + " WHERE idejercicioProfesor = " + id + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new AlumnoWorkSpaceBeans();

                exercise.setIdEjercicioProfesor_A(rs.getInt(1));
                exercise.setNombre_A(rs.getString(2));
                exercise.setDescripcion_A(rs.getString(3));
                exercise.setSugerencia_A(rs.getString(4));
                exercise.setCodigoFuente_A(rs.getString(5));
                exercise.setTiempoTotal_A(rs.getString(6));
                exercise.setIntentosTotal_A(rs.getInt(7));
                exercise.setTipo_a(rs.getString(8));

                ejercicioList.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }

        return ejercicioList;

    }

    public int createExercisePro(AlumnoWorkSpaceBeans ejercicio) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + ejercicio.getEjercicioProfesor_idejercicioProfesor_TA() + "',";

        campos += "'" + ejercicio.getUsuarios_idusuarios_TA() + "',";
        campos += "'" + ejercicio.getClaseAlumno_idclaseAlumno_TA() + "',";
        campos += "'" + ejercicio.getCodigoFuente_TA() + "',";

        campos += "'" + ejercicio.getFechaInicio_TA() + "',";
        campos += "'" + ejercicio.getFechaFin_TA() + "',";
        campos += "'" + ejercicio.getTiempoTotal_TA() + "',";
        campos += "'" + ejercicio.getIntentosTotal_TA() + "'";
        query = "INSERT INTO ejercicioAlumno(idejercicioAlumno, ejercicioProfesor_idejercicioProfesor, "
                + " usuarios_idusuarios, claseAlumno_idclaseAlumno, codigoFuente, fechaInicio_a,fechaFinal_a,tiempoTotal_a, intentosTotal_a)"
                + " VALUES(null," + campos + ");";

        try {
            conn.insert(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<AlumnoWorkSpaceBeans> retriveEjercicioActualizado(int idProfesor, int idAlumno) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> ejercicioListUpdate = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT  Count(*)"
                + " FROM ejercicioAlumno "
                + " WHERE ejercicioProfesor_idejercicioProfesor = " + idProfesor + "  "
                + " AND usuarios_idusuarios= " + idAlumno + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new AlumnoWorkSpaceBeans();
                exercise.setContEjercicio(rs.getInt(1));
                ejercicioListUpdate.add(exercise);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return ejercicioListUpdate;
    }

    public List<AlumnoWorkSpaceBeans> retriveListEjercicioAlumnosEdit2(int idProfesor, int idAlumno) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> ejercicioList2 = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans exercise = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT prof.idejercicioProfesor, prof.nombre, prof.descripcion, prof.sugerencia,prof.tiempoTotal,prof.intentosTotal,tipo, "
                + " al.idejercicioAlumno, al.ejercicioProfesor_idejercicioProfesor, al.usuarios_idusuarios, al.codigoFuente, al.fechaInicio_a,al.fechaFinal_a,al.tiempoTotal_a,al.intentosTotal_a "
                + " FROM ejercicioprofesor prof, ejercicioalumno al "
                + " WHERE al.ejercicioProfesor_idejercicioProfesor = " + idProfesor
                + " AND al.usuarios_idusuarios= " + idAlumno
                + " AND prof.idejercicioProfesor = " + idProfesor;

        try {
            rs = conn.query(query);
            while (rs.next()) {
                exercise = new AlumnoWorkSpaceBeans();

                exercise.setIdEjercicioProfesor_A(rs.getInt(1));
                exercise.setNombre_A(rs.getString(2));
                exercise.setDescripcion_A(rs.getString(3));
                exercise.setSugerencia_A(rs.getString(4));
                exercise.setTiempoTotal_A(rs.getString(5));
                exercise.setIntentosTotal_A(rs.getInt(6));
                // +++
                exercise.setTipo_a(rs.getString(7));
                exercise.setIdejercicioAlumno_TA(rs.getInt(8));
                exercise.setEjercicioProfesor_idejercicioProfesor_TA(rs.getInt(9));
                exercise.setUsuarios_idusuarios_TA(rs.getInt(10));
                exercise.setCodigoFuente_A(rs.getString(11));

                exercise.setFechaInicio_TA(rs.getString(12));
                exercise.setFechaFin_TA(rs.getString(13));
                exercise.setTiempoTotal_TA(rs.getString(14));
                exercise.setIntentosTotal_TA(rs.getInt(15));

                ejercicioList2.add(exercise);
            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
        }
        return ejercicioList2;

    }

    public int UpdateExerciseAlumno(AlumnoWorkSpaceBeans ejercicio, int idProfesor, int idAlumno) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE ejercicioAlumno SET codigoFuente = '" + ejercicio.getCodigoFuente_TA() + "', fechaFinal_a ='" + ejercicio.getFechaFin_TA() + "', tiempoTotal_a= " + ejercicio.getTiempoTotal_TA() + " ,  intentosTotal_a=" + ejercicio.getIntentosTotal_TA()
                + " WHERE " + "ejercicioProfesor_idejercicioProfesor = " + idProfesor + "  AND usuarios_idusuarios= " + idAlumno + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public int UpdateExerciseAlumnoTiempo(AlumnoWorkSpaceBeans ejercicio, int idProfesor, int idAlumno) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE ejercicioAlumno SET tiempoTotal_a = " + ejercicio.getTiempoTotal_TA()
                + " WHERE " + "ejercicioProfesor_idejercicioProfesor = " + idProfesor + "  AND usuarios_idusuarios= " + idAlumno + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<AlumnoWorkSpaceBeans> retriveCasosPrueba(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> casoPrueba = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans casoPru = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idcasoPrueba, ejercicioProfesor_idejercicioProfesor, resultado FROM casoprueba WHERE ejercicioProfesor_idejercicioProfesor=" + id + "";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPru = new AlumnoWorkSpaceBeans();
                casoPru.setIdCasoPrueba(rs.getInt(1));
                casoPru.setEjercicioPro_idEjercicioPro(rs.getInt(2));
                casoPru.setResultado(rs.getString(3));

                casoPrueba.add(casoPru);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return casoPrueba;
    }

    public List<AlumnoWorkSpaceBeans> retriveListParametros(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> listParametros = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans parametros = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idParametros, casoPrueba_idCasoPrueba, tipo, nombreVariable, valor "
                + " FROM parametros "
                + " WHERE casoPrueba_idCasoPrueba=" + id + "";

        try {
            rs = conn.query(query);
            while (rs.next()) {

                parametros = new AlumnoWorkSpaceBeans();
                parametros.setIdParametros(rs.getInt(1));
                parametros.setCasoPrueba_idCasoPrueba(rs.getInt(2));
                parametros.setTipo(rs.getString(3));
                parametros.setNombreVariable(rs.getString(4));
                parametros.setValor(rs.getString(5));

                listParametros.add(parametros);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listParametros;
    }

    //+++++++++++++++++++  CASO PRUEBA ALUMNOS   ++++++++++++++++++++
    public int createPruebaAlumno(AlumnoWorkSpaceBeans ejercicio) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + ejercicio.getpA_ejercicioAlumno_id() + "',";
        campos += "'" + ejercicio.getpA_casoPrueba_id() + "',";
        campos += "'" + ejercicio.getpA_usuario_id() + "',";
        campos += "'" + ejercicio.getpA_status() + "'";

        query = "INSERT INTO casoPruebaAlumno(idcasoPruebaAlumno, ejercicioAlumno_idejercicioAlumno, "
                + "casoPrueba_idCasoPrueba,usuarios_idusuarios, estatus)"
                + "VALUES(null," + campos + ");";

        try {
            conn.insert(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<AlumnoWorkSpaceBeans> retriveListPruebasAlumnos(int idAlumnoEjercicio, int idPrueba, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> listPruebaAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans pruebaAlumno = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idcasoPruebaAlumno, ejercicioAlumno_idejercicioAlumno, casoPrueba_idcasoPrueba, usuarios_idusuarios, estatus "
                + " FROM casopruebaalumno "
                + " WHERE ejercicioAlumno_idejercicioAlumno=" + idAlumnoEjercicio + " "
                + " AND casoPrueba_idcasoPrueba= " + idPrueba + " "
                + " AND usuarios_idusuarios=" + idUsuario + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {

                pruebaAlumno = new AlumnoWorkSpaceBeans();
                pruebaAlumno.setpA_idCasoPruebaAlumno(rs.getInt(1));
                pruebaAlumno.setpA_ejercicioAlumno_id(rs.getInt(2));
                pruebaAlumno.setpA_casoPrueba_id(rs.getInt(3));
                pruebaAlumno.setpA_usuario_id(rs.getInt(4));
                pruebaAlumno.setpA_status(rs.getString(5));

                listPruebaAlumno.add(pruebaAlumno);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listPruebaAlumno;
    }

    public List<AlumnoWorkSpaceBeans> retriveListSalidasAlumnos(int idAlumnoEjercicio, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        int contador = 0;
        List<AlumnoWorkSpaceBeans> listPruebaAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans pruebaAlumno = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT ca.idcasoPruebaAlumno, ca.ejercicioAlumno_idejercicioAlumno, ca.casoPrueba_idcasoPrueba, "
                + " ca.usuarios_idusuarios, ca.estatus, cp.resultado, IF(ca.estatus LIKE 'OK', 'GREEN','RED') "
                + " FROM casopruebaalumno ca, casoprueba cp, ejercicioalumno ea, ejercicioprofesor ep "
                + " WHERE ca.ejercicioAlumno_idejercicioAlumno= " + idAlumnoEjercicio
                + " AND ca.usuarios_idusuarios= " + idUsuario
                + " AND ca.casoPrueba_idcasoPrueba = cp.idcasoPrueba "
                + " AND ea.ejercicioProfesor_idejercicioProfesor=ep.idejercicioProfesor "
                + " AND ep.idejercicioProfesor=cp.ejercicioProfesor_idejercicioProfesor "
                + " AND ea.idejercicioAlumno= ca.ejercicioAlumno_idejercicioAlumno; ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                contador++;

                pruebaAlumno = new AlumnoWorkSpaceBeans();
                pruebaAlumno.setIdCasoPruebaAlumno_table(rs.getInt(1));
                pruebaAlumno.setEjercicioAlumno_id_table(rs.getInt(2));
                pruebaAlumno.setCasoPrueba_id_table(rs.getInt(3));
                pruebaAlumno.setUsuario_id_table(rs.getInt(4));
                pruebaAlumno.setStatus_table(rs.getString(5));
                if (contador <= 2) {
                    pruebaAlumno.setResultado_table(rs.getString(6));
                } else {
                    pruebaAlumno.setResultado_table("");
                }

                pruebaAlumno.setColorResult(rs.getString(7));

                listPruebaAlumno.add(pruebaAlumno);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listPruebaAlumno;
    }

    public List<AlumnoWorkSpaceBeans> retriveListSalidasAlumnos22(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        int contador = 0;
        List<AlumnoWorkSpaceBeans> listPruebaAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans pruebaAlumno = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT p.Tipo, p.nombreVariable, p.valor "
                + " FROM  casoprueba cp, ejercicioprofesor ep, parametros p "
                + " WHERE ep.idejercicioProfesor=" + idEjercicio
                + " AND ep.idejercicioProfesor=cp.ejercicioProfesor_idejercicioProfesor "
                + " AND p.casoPrueba_idcasoPrueba= cp.idcasoPrueba;";

        try {
            rs = conn.query(query);
            while (rs.next()) {

                pruebaAlumno = new AlumnoWorkSpaceBeans();
                pruebaAlumno.setTipoVariableM(rs.getString(1));
                pruebaAlumno.setNombreVariableM(rs.getString(2));
                pruebaAlumno.setValorVaribaleM(rs.getString(3));

                listPruebaAlumno.add(pruebaAlumno);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listPruebaAlumno;
    }

    public int UpdatePruebasAlumno(String status, int idAlumno, int idPrueba, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE casoPruebaAlumno SET estatus = '" + status + "' WHERE "
                + " ejercicioAlumno_idejercicioAlumno = " + idAlumno
                + "  AND casoPrueba_idcasoPrueba= " + idPrueba
                + " AND usuarios_idusuarios=" + idUsuario + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
           System.out.println(ex.getMessage());

        }

        return 1;
    }

    public int insertEstadoFinal(AlumnoWorkSpaceBeans ejercicio) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        campos += "'" + ejercicio.getIdEjercicioEstado() + "',";
        campos += "'" + ejercicio.getEstodoFinal() + "'";

        query = "INSERT INTO ejercicioEstado(idEjercicioEstado, ejercicioAlumno_idejercicioAlumno, "
                + " estado)"
                + "VALUES(null," + campos + ");";

        try {
            conn.insert(query);

        } catch (Exception ex) {
          System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<AlumnoWorkSpaceBeans> listEstadoFinal(int idEjercicio) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> listPruebaAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans pruebaAlumno = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT idEjercicioEstado, ejercicioAlumno_idejercicioAlumno,estado "
                + " FROM ejercicioEstado "
                + " WHERE ejercicioAlumno_idejercicioAlumno=" + idEjercicio + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {

                pruebaAlumno = new AlumnoWorkSpaceBeans();
                pruebaAlumno.setIdEjercicioEstado(rs.getInt(1));
                pruebaAlumno.setIdEjrcicioEstadoAlumno(rs.getInt(2));
                pruebaAlumno.setEstodoFinal(rs.getString(3));
                listPruebaAlumno.add(pruebaAlumno);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listPruebaAlumno;
    }

    public int UpdateEstadoFinal(int idEjercicio, String estado) {
        ResultSet rs = null;
        String query = "";
        String campos = "";

        MysqlConnect conn = MysqlConnect.getDbCon();

        query = "UPDATE ejercicioEstado SET estado = '" + estado + "' WHERE "
                + " ejercicioAlumno_idejercicioAlumno = " + idEjercicio + ";";

        try {
            conn.insert(query);

        } catch (Exception ex) {
           System.out.println(ex.getMessage());

        }

        return 1;
    }

    public List<AlumnoWorkSpaceBeans> listTiempoIntentos(int idEjercicio, int idUsuario) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> listPruebaAlumno = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans pruebaAlumno = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = "SELECT ea.usuarios_idusuarios, ea.idEjercicioAlumno, ea.ejercicioProfesor_idEjercicioProfesor, "
                + " ep.idejercicioProfesor, ea.tiempoTotal_a, ep.tiempoTotal, ea.intentosTotal_a, ep.intentosTotal "
                + " FROM ejercicioalumno ea, ejercicioProfesor ep "
                + " WHERE ea.ejercicioProfesor_idEjercicioProfesor= ep.idejercicioProfesor "
                + " AND ea.usuarios_idUsuarios =" + idUsuario
                + " AND ea.idejercicioAlumno=" + idEjercicio + ";";

        try {
            rs = conn.query(query);
            while (rs.next()) {

                pruebaAlumno = new AlumnoWorkSpaceBeans();

                pruebaAlumno.setEa_usuarios_idusuarios(rs.getInt(1));
                pruebaAlumno.setEa_idEjercicioAlumno(rs.getInt(2));
                pruebaAlumno.setEa_ejercicioProfesor_idEjercicioProfesor(rs.getInt(3));
                pruebaAlumno.setEp_idejercicioProfesor(rs.getInt(4));
                pruebaAlumno.setEa_tiempoTotal_a(rs.getInt(5));
                pruebaAlumno.setEp_tiempoTotal(rs.getInt(6));
                pruebaAlumno.setEa_intentosTotal_a(rs.getInt(7));
                pruebaAlumno.setEp_intentosTotal(rs.getInt(8));

                listPruebaAlumno.add(pruebaAlumno);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return listPruebaAlumno;
    }

    public List<AlumnoWorkSpaceBeans> alumVSprof(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> casoPrueba = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans casoPru = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " select  ea.tiempoTotal_a, ea.intentosTotal_a, "
                + " ep.tiempoTotal, ep.intentosTotal "
                + " FROM ejercicioalumno ea, ejercicioprofesor ep "
                + " where ea.idejercicioAlumno= " + id
                + " and ea.ejercicioProfesor_idejercicioProfesor= ep.idejercicioProfesor ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPru = new AlumnoWorkSpaceBeans();
                casoPru.setTiempoAlumnoVs(rs.getInt(1));
                casoPru.setIntentoAlumnoVs(rs.getInt(2));
                casoPru.setTiempoProfesorVs(rs.getInt(3));
                casoPru.setIntentoProfesorVs(rs.getInt(4));

                casoPrueba.add(casoPru);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return casoPrueba;
    }

    public List<AlumnoWorkSpaceBeans> methodParameter(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> casoPrueba = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans casoPru = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT  p.Tipo, p.nombreVariable"
                + " FROM  casoprueba cp, ejercicioprofesor ep, parametros p "
                + " WHERE  p.casoPrueba_idcasoPrueba=" + id
                + " AND ep.idejercicioProfesor=cp.ejercicioProfesor_idejercicioProfesor "
                + " AND p.casoPrueba_idcasoPrueba= cp.idcasoPrueba ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPru = new AlumnoWorkSpaceBeans();
                casoPru.setTipoVariableM(rs.getString(1));
                casoPru.setNombreVariableM(rs.getString(2));

                casoPrueba.add(casoPru);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return casoPrueba;
    }

    public List<AlumnoWorkSpaceBeans> idCasoPrueba(int id) {
        ResultSet rs = null;
        String query = "";
        List<AlumnoWorkSpaceBeans> casoPrueba = new ArrayList<AlumnoWorkSpaceBeans>();
        AlumnoWorkSpaceBeans casoPru = null;

        MysqlConnect conn = MysqlConnect.getDbCon();
        query = " SELECT  DISTINCT  ( cp.idcasoPrueba) "
                + " FROM  casoprueba cp, ejercicioprofesor ep, parametros p "
                + " WHERE ep.idejercicioProfesor= " + id
                + " AND ep.idejercicioProfesor=cp.ejercicioProfesor_idejercicioProfesor "
                + " AND   cp.idcasoPrueba "
                + " AND p.casoPrueba_idcasoPrueba= cp.idcasoPrueba ";

        try {
            rs = conn.query(query);
            while (rs.next()) {
                casoPru = new AlumnoWorkSpaceBeans();
                casoPru.setCasoPruebaM(rs.getInt(1));
                casoPrueba.add(casoPru);

            }
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());

        }

        return casoPrueba;
    }
}
