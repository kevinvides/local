/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poopersistencia;

import Conexion.ConexionMySQL;
import Personas.Empleado;
import java.sql.Connection;

/**
 * .
 *
 * @author Mauricio
 */
public class POOpersistencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        test();        
        //crearEmpleado();
        //obtenerEmpleado();
        //modificarEmpleado();
        //eliminarEmpleado();
        
        todosEmpleados();
    }

    public static void crearEmpleado() {

        Empleado empleado = new Empleado();
        empleado.setCodigoEmpleado("M101116");
        empleado.setNombreEmpleado("Mauricio");
        empleado.setFechaIngreso("15-09-2022");
        empleado.setDepartamento("Redes");
        empleado.setEstado(true);

        boolean respuesta = empleado.crearEmpleado();

        if (respuesta) {
            System.out.println("Registro Creado");
        } else {
            System.out.println("error");
        }
        
        obtenerEmpleado("M101116");
    }

    public static void obtenerEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setCodigoEmpleado("VM101116");

        empleado.obtenerEmpleado();

        if (empleado.isEstado()) {
            System.out.println("Codigo: " + empleado.getCodigoEmpleado());
            System.out.println("Nombre: " + empleado.getNombreEmpleado());
            System.out.println("Departamento: " + empleado.getDepartamento());
            System.out.println("Fecha: " + empleado.getFechaIngreso());
            System.out.println("Estado: " + empleado.isEstado());
        } else {
            System.out.println("Codigo no existe");
        }
    }

    public static void obtenerEmpleado(String codigo) {
        Empleado empleado = new Empleado();
        empleado.setCodigoEmpleado(codigo);

        empleado.obtenerEmpleado();

        if (empleado.getNombreEmpleado() == null) {
            System.out.println("Regsitro no encontrado");
        } else {
            System.out.println("Codigo: " + empleado.getCodigoEmpleado());
            System.out.println("Nombre: " + empleado.getNombreEmpleado());
            System.out.println("Departamento: " + empleado.getDepartamento());
            System.out.println("Fecha: " + empleado.getFechaIngreso());
            System.out.println("Estado: " + empleado.isEstado());
        }
    }

    public static void modificarEmpleado() {
        Empleado empleado = new Empleado();        
        empleado.setNombreEmpleado("Vides");
        empleado.setFechaIngreso("15-09-2022");
        empleado.setDepartamento("Base de Datos");
        empleado.setEstado(true);
        empleado.setCodigoEmpleado("V101116");

        empleado.modificarEmpleado();

        boolean respuesta = empleado.modificarEmpleado();

        if (respuesta) {
            System.out.println("Registro Modificado");
        } else {
            System.out.println("Registro NO Modificado");
        }
        
        obtenerEmpleado("V101116");
    }

    public static void eliminarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setCodigoEmpleado("VM101116");

        boolean respuesta = empleado.eliminarEmpleado();

        if (respuesta) {
            System.out.println("Registro eliminado");
        } else {
            System.out.println("Codigo no existe");
        }
    }

    public static void test() {
        ConexionMySQL con = new ConexionMySQL();
        Connection conexion = con.getConexion();

        if (conexion == null) {
            System.out.println("No conectado");
        } else {
            System.out.println("Conectado");
        }
    }

    private static void todosEmpleados() {
        Empleado empleado = new Empleado();
        empleado.todosEmpleados();
    }
    
    


}
