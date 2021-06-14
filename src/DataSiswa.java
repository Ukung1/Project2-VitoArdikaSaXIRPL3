/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Admin
 */
public class DataSiswa extends javax.swing.JFrame {
    
    Connection koneksi;
    
    public DataSiswa() {
        initComponents();
        koneksi = DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_sekolah");
        showData();
    }
    
    DefaultTableModel dtm;
    public void showData() {
        String[] kolom = {"NO", "NIS", "Nama", "Kelas", "Jurusan"};
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM t_siswa";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()){
                String nis = rs.getString("nis");
                String nama = rs.getString("nama");
                String kelas = rs.getString("kelas");
                String jurusan = rs.getString("jurusan");
                
                dtm.addRow(new String[]{no + "", nis, nama, kelas, jurusan });
                no++;
            }
         }catch (SQLException ex) {
             ex.printStackTrace();
         }
        t_siswa.setModel(dtm);
    }
}
