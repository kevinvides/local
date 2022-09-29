/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Mauricio
 */
public class ConexionMySQL {
    
    private final String user = "user02";
    private final String password = "pa$$word";
    private final String url = "jdbc:mysql://localhost:3306/empleados";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection con = null;
    
    public Connection getConexion() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }
    
    public void close() {

        try {
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
    
}
