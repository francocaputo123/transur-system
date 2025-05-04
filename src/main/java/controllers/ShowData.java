/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ShowData {
    public void showData(JTable tableTest) {
        Cconection conect = new Cconection();
        
        DefaultTableModel model = new DefaultTableModel();
        
        String sql = "";
        
        model.addColumn("id");
        model.addColumn("type");
        model.addColumn("serial");
        
        tableTest.setModel(model);
        
        sql = "SELECT * FROM cylinder";
        
        String data[] = new String[3];
        Statement st;
        
        try {
            st = conect.conect().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                
                model.addRow(data);
            }
            
            tableTest.setModel(model);
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        finally {
            conect.closeConnection();
        }
    }
}
