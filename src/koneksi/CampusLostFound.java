package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CampusLostFound {
    private static Connection mysqlkonek;
    
    public static Connection koneksiDB() {
        if (mysqlkonek == null) {
            try {
                // Menghubungkan ke database db_lost_found di phpMyAdmin
                String DB = "jdbc:mysql://localhost:3306/db_lost_found"; 
                String user = "root";
                String pass = ""; // Kosongkan jika menggunakan XAMPP bawaan

                // Baris registerDriver dihapus karena otomatis di-load oleh DriverManager
                mysqlkonek = DriverManager.getConnection(DB, user, pass);
                System.out.println("Koneksi Database Berhasil!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi ke Database Gagal: " + e.getMessage());
            }
        }
        return mysqlkonek;
    }

    public static void main(String[] args) {
        // Memanggil fungsi koneksi untuk tes awal apakah database tersambung
        koneksiDB();
    }
}