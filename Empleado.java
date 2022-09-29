/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;

import Conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.StringTokenizer;

/**
 *
 * @author Mauricio
 */
public class Empleado {

    String codigoEmpleado;
    String nombreEmpleado;
    String fechaIngreso;
    String departamento;
    boolean estado;

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean crearEmpleado() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();
        PreparedStatement ps;

        String sql = "insert into empleado(codigoEmpleado, nombreEmpleado, fechaIngreso, departamento, estado )"
                + "values (?, ?, ?, ?, ?)";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, this.codigoEmpleado);
            ps.setString(2, this.nombreEmpleado);
            ps.setString(3, this.fechaIngreso);
            ps.setString(4, this.departamento);
            ps.setBoolean(5, this.estado);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public void obtenerEmpleado() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select idempleado, codigoEmpleado, nombreEmpleado, fechaIngreso, departamento, estado from empleado where codigoEmpleado=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, this.codigoEmpleado);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.codigoEmpleado = rs.getString("codigoEmpleado");
                this.nombreEmpleado = rs.getString("nombreEmpleado");
                this.fechaIngreso = rs.getString("fechaIngreso");
                this.departamento = rs.getString("departamento");
                this.estado = rs.getBoolean("estado");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
    }

    public boolean modificarEmpleado() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();
        PreparedStatement ps;

        String sql = "update empleado set nombreEmpleado=?, fechaIngreso=?, departamento=?, estado=? where codigoEmpleado=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, this.nombreEmpleado);
            ps.setString(2, this.fechaIngreso);
            ps.setString(3, this.departamento);
            ps.setBoolean(4, this.estado);
            ps.setString(5, this.codigoEmpleado);

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public boolean eliminarEmpleado() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();
        PreparedStatement ps;

        String sql = "delete from empleado where codigoEmpleado=?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, this.codigoEmpleado);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            con.close();
        }
    }

    public void todosEmpleados() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();

        ResultSet rs;
        PreparedStatement ps;

        String sql = "select codigoEmpleado, nombreEmpleado, fechaIngreso, departamento, estado from empleado";

        String amarillo = "\033[33m";
        String azul = "\033[36m";

        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(azul + "Codigo: " + amarillo + rs.getString("codigoEmpleado")
                        + azul + " Nombre: " + amarillo + rs.getString("nombreEmpleado")
                        + azul + " Fecha: " + amarillo + rs.getString("fechaIngreso")
                        + azul + " Departamento: " + amarillo + rs.getString("departamento")
                        + azul + " Estado: " + amarillo + rs.getBoolean("estado"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
    }

    private String letras() {
        String letras = "", frase;
        StringTokenizer nomSeparado = new StringTokenizer(this.nombreEmpleado);

        if (nomSeparado.countTokens() > 1) {
            // hay mas de dos palabras
            while (nomSeparado.hasMoreTokens()) {
                frase = nomSeparado.nextToken();
                letras = letras + frase.substring(0, 1);

                if (letras.length() == 2) {
                    break;
                }
            }
        } else {
            // hay solo una palabra
            letras = this.nombreEmpleado.substring(0, 1);
            letras = letras + letras;
        }
        return letras;
    }

    private String crearCodigo() {
        String codigo = "";
        int correlativo = 0;
        LocalDate date = LocalDate.now();
        String anio = Integer.toString(date.getYear());

        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();

        ResultSet rs;
        String letras = letras();
        PreparedStatement ps;

        String sql = "select codigoEmpleado from empleados.empleado "
                + "where codigoEmpleado like '" + letras + "%' "
                + "order by idempleado desc limit l;";

        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                codigo = rs.getString("codigoEmpleado");
                correlativo = 1 + Integer.parseInt(codigo.substring(2, 5));

                System.out.println("correlativo: " + correlativo);
                codigo = letras + correlativo + anio.substring(2, 4);
            } else {
                codigo = letras + 100 + anio.substring(2, 4);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
        return codigo;
    }

}
