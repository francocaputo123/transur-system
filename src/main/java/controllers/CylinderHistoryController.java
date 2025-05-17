/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuario
 */
public class CylinderHistoryController {
    
    public static ArrayList<String[]> getDataHistory() {
       
        Cconection conect = new Cconection(); 
        ArrayList<String[]> list = new ArrayList<>();
        
        String sql = "SELECT * FROM history_cylinder";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conect.conect().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String data[] = new String[7];
                data[0] = rs.getString("id_history_cylinder");
                data[1] = rs.getString("id_truck");
                data[2] = rs.getString("id_cylinder");
                data[3] = rs.getString("t_internal");
                data[4] = rs.getString("c_position");
                data[5] = rs.getString("asignation_date");
                data[6] = rs.getString("end_date");
                
                list.add(data);
            }
            
            return list;
        } catch (Exception e) {
            System.err.println("Error al pasar los datos: " + e.getMessage());
        }finally {
            try {
                if(ps != null) ps.close();
                if(rs != null) rs.close();
                conect.closeConnection();
            } catch (Exception e) {
            System.err.println("Error al cerrar coneccion: " + e.getMessage());
            }
        }
        return null;
    }
    
 public static void addNewHistory(Cconection conect, int id_truck, int id_cylinder, String c_position, String asignation_date) {
     
    String sqlInternal = "SELECT internal FROM truck WHERE id_truck = ?";
    String sqlInsert = "INSERT INTO history_cylinder(id_truck, id_cylinder, t_internal, c_position, asignation_date, end_date) VALUES(?,?,?,?,?,?)";

    Connection conn = null;
    try {
        conn = conect.conect();
        conn.setAutoCommit(false);  // Desactiva el auto commit para manejar la transacción manualmente

       try (PreparedStatement psInternal = conn.prepareStatement(sqlInternal);
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {

            psInternal.setInt(1, id_truck);
            try (ResultSet rs = psInternal.executeQuery()) {
                if (rs.next()) {
                    String t_internal = rs.getString("internal");

                    psInsert.setInt(1, id_truck);
                    psInsert.setInt(2, id_cylinder);
                    psInsert.setString(3, t_internal);
                    psInsert.setString(4, c_position);
                    psInsert.setString(5, asignation_date);
                    psInsert.setString(6, "Activo");

                    psInsert.executeUpdate();
                }
            }
        }

        conn.commit();  // Confirma la transacción si todo es exitoso
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback();  // Revierte la transacción en caso de error
            } catch (SQLException ex) {
                System.err.println("Error al revertir la transacción: " + ex.getMessage());
            }
        }
        JOptionPane.showMessageDialog(null, "Hubo un problema al registrar el historial: " + e.getMessage(), "Error con el historial", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);  // Restaura el auto commit
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando conexión: " + e.getMessage());
        }
    }
}
    
}
