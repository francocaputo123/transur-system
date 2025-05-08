/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TruckCylinderController {
    
    public static void addCyliderForTruck(int id_truck, int id_cylinder, String position) {
        
        Cconection conect = new Cconection();
        
        String sql = "INSERT INTO truck_cylinder(id_truck, id_cylinder, active, asignation_date, position) VALUES(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conect.conect().prepareStatement(sql);
            
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Fallo al insertar a la base de datos" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
        finally{
            conect.closeConnection();
        }
    }
}
