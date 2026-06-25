/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package index;
import User.lihat_user;
import User.tambah_user;
import User.lihat_user;
import User.tambah_user;
import barang_hilang.TambahLaporanForm;
import barang_hilang.lihat_barang_hilang;
import barang_temuan.lihat_barang_temuan;
import barang_temuan.penemu;
import klaim_barang.klaim_barang;
import klaim_barang.lihat_klaim_barang;
import auth.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.CampusLostFound;

public class dashboard extends javax.swing.JFrame {
     public static String namaUser;
     Connection conn;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(dashboard.class.getName());

    public dashboard() {
        initComponents();
        
     jLabel9.setText("Selamat Datang, " + namaUser);
     totalUser();
     totalBarangHilang();
     totalBarangTemuan();
     totalKlaim();
     loadAktivitas();
    }
    
    private void tampilData(String keyword) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
model.addColumn("Nama Pelapor");
        model.addColumn("Nama Barang");
        model.addColumn("Lokasi Hilang");
        model.addColumn("Tanggal");
        model.addColumn("Deskripsi");
        
        try {
            conn = CampusLostFound.koneksiDB();
            String sql;
            
            // PERBAIKAN: Query sekarang konsisten mencari di tabel 'barang_hilang'
            if (keyword.equals("")) {
                sql = "SELECT * FROM barang_hilang";
            } else {
                // Mencari berdasarkan nama pelapor, nama barang, lokasi, atau deskripsi
                sql = "SELECT * FROM barang_hilang WHERE "
                    + "nama_pelapor LIKE '%" + keyword + "%' "
                    + "OR nama_barang LIKE '%" + keyword + "%' "
                    + "OR lokasi_hilang LIKE '%" + keyword + "%' "
                    + "OR deskripsi LIKE '%" + keyword + "%'";
            }
            
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            while (res.next()) {
                // SESUAIKAN: Mengambil data berdasarkan nama field di tabel barang_hilang
                model.addRow(new Object[]{
                    res.getString("nama_pelapor"),
                    res.getString("nama_barang"),
                    res.getString("lokasi_hilang"),
                    res.getString("tanggal_hilang"),
                    res.getString("deskripsi")
                });
            }
            
            // Set model ke komponen JTable Anda di NetBeans
            tblAktivitas.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }
    
     private void totalUser() {

        try {

            String sql = "SELECT COUNT(*) AS total FROM user";

            Connection conn =  koneksi.CampusLostFound.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lblTotalUser.setText(rs.getString("total"));
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
    }
     private void totalBarangHilang() {

    try {

        String sql = "SELECT COUNT(*) AS total FROM barang_hilang";

        Connection conn = koneksi.CampusLostFound.koneksiDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            lblTotalHilang.setText(rs.getString("total"));
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
     private void totalBarangTemuan() {

    try {

        String sql = "SELECT COUNT(*) AS total FROM barang_temuan";

        Connection conn = koneksi.CampusLostFound.koneksiDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            lblTotalTemuan.setText(rs.getString("total"));
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
private void totalKlaim() {

    try {

        String sql = "SELECT COUNT(*) AS total FROM klaim_barang";

        Connection conn = koneksi.CampusLostFound.koneksiDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            lblTotalKlaim.setText(rs.getString("total"));
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    // Tombol Logout
  
private void loadAktivitas() {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("Nama Pelapor");
    model.addColumn("Nama Barang");
    model.addColumn("Lokasi Hilang");
    model.addColumn("Tanggal");
    model.addColumn("Deskripsi");

    try {

        String sql =
        "SELECT nama_pelapor,nama_barang,lokasi_hilang,tanggal_hilang,deskripsi "
      + "FROM barang_hilang ORDER BY tanggal_hilang DESC";

        Connection conn = koneksi.CampusLostFound.koneksiDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){

            model.addRow(new Object[]{
                rs.getString("nama_pelapor"),
                rs.getString("nama_barang"),
                rs.getString("lokasi_hilang"),
                rs.getString("tanggal_hilang"),
                rs.getString("deskripsi")
            });

        }

        tblAktivitas.setModel(model);

    } catch(Exception e){
        javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardTemuan1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        panelSidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLihatUser = new javax.swing.JButton();
        btnTambahUser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnTambahLaporan = new javax.swing.JButton();
        btnLihatBarangHilang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnKlaimBarang = new javax.swing.JButton();
        btnDataPenemu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnLihatBarangTemuan1 = new javax.swing.JButton();
        btnLihatKlaim = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelHeader = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelWelcome = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cardUser = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblTotalUser = new javax.swing.JLabel();
        cardHilang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblTotalHilang = new javax.swing.JLabel();
        cardTemuan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblTotalTemuan = new javax.swing.JLabel();
        cardKlaim = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        lblTotalKlaim = new javax.swing.JLabel();
        panelAktivitas = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAktivitas = new javax.swing.JTable();

        cardTemuan1.setBackground(new java.awt.Color(255, 255, 255));
        cardTemuan1.setForeground(new java.awt.Color(255, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("TOTAL USER ");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("25");

        javax.swing.GroupLayout cardTemuan1Layout = new javax.swing.GroupLayout(cardTemuan1);
        cardTemuan1.setLayout(cardTemuan1Layout);
        cardTemuan1Layout.setHorizontalGroup(
            cardTemuan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTemuan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardTemuan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        cardTemuan1Layout.setVerticalGroup(
            cardTemuan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTemuan1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelSidebar.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOST & FOUND");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MANAGEMENT SYSTEM");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("USER");

        btnLihatUser.setBackground(new java.awt.Color(0, 51, 102));
        btnLihatUser.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatUser.setText("Lihat User                  ");
        btnLihatUser.setBorderPainted(false);
        btnLihatUser.addActionListener(this::btnLihatUserActionPerformed);

        btnTambahUser.setBackground(new java.awt.Color(0, 51, 102));
        btnTambahUser.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahUser.setText("Tambah User            ");
        btnTambahUser.setBorderPainted(false);
        btnTambahUser.addActionListener(this::btnTambahUserActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("KLAIM BARANG");

        btnTambahLaporan.setBackground(new java.awt.Color(0, 51, 102));
        btnTambahLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahLaporan.setText("Tambah Laporan      ");
        btnTambahLaporan.setBorderPainted(false);
        btnTambahLaporan.addActionListener(this::btnTambahLaporanActionPerformed);

        btnLihatBarangHilang.setBackground(new java.awt.Color(0, 51, 102));
        btnLihatBarangHilang.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatBarangHilang.setText("Lihat Barang Hilang  ");
        btnLihatBarangHilang.setBorderPainted(false);
        btnLihatBarangHilang.addActionListener(this::btnLihatBarangHilangActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BARANG HILANG");

        btnKlaimBarang.setBackground(new java.awt.Color(0, 51, 102));
        btnKlaimBarang.setForeground(new java.awt.Color(255, 255, 255));
        btnKlaimBarang.setText("Klaim Barang            ");
        btnKlaimBarang.setBorderPainted(false);
        btnKlaimBarang.addActionListener(this::btnKlaimBarangActionPerformed);

        btnDataPenemu.setBackground(new java.awt.Color(0, 51, 102));
        btnDataPenemu.setForeground(new java.awt.Color(255, 255, 255));
        btnDataPenemu.setText("Data Penemu            ");
        btnDataPenemu.setBorderPainted(false);
        btnDataPenemu.addActionListener(this::btnDataPenemuActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BARANG TEMUAN");

        btnLihatBarangTemuan1.setBackground(new java.awt.Color(0, 51, 102));
        btnLihatBarangTemuan1.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatBarangTemuan1.setText("Lihat Barang Temuan");
        btnLihatBarangTemuan1.setBorderPainted(false);
        btnLihatBarangTemuan1.addActionListener(this::btnLihatBarangTemuan1ActionPerformed);

        btnLihatKlaim.setBackground(new java.awt.Color(0, 51, 102));
        btnLihatKlaim.setForeground(new java.awt.Color(255, 255, 255));
        btnLihatKlaim.setText("Lihat Klaim                ");
        btnLihatKlaim.setBorderPainted(false);
        btnLihatKlaim.addActionListener(this::btnLihatKlaimActionPerformed);

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelSidebarLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLihatUser)
                            .addComponent(jLabel3)
                            .addComponent(btnTambahUser)
                            .addComponent(jLabel4)
                            .addComponent(btnTambahLaporan)
                            .addComponent(btnLihatBarangHilang)
                            .addComponent(jLabel5)
                            .addComponent(btnDataPenemu)
                            .addComponent(jLabel6)
                            .addComponent(btnLihatBarangTemuan1)
                            .addComponent(btnKlaimBarang)
                            .addComponent(btnLihatKlaim)
                            .addComponent(btnLogout))
                        .addGap(47, 47, 47))
                    .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelSidebarLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel2))
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLihatUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTambahUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLihatBarangHilang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTambahLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLihatBarangTemuan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDataPenemu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKlaimBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLihatKlaim)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("DASHBOARD");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelWelcome.setBackground(new java.awt.Color(245, 249, 255));
        panelWelcome.setForeground(new java.awt.Color(220, 230, 240));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 102));
        jLabel9.setText("Selamat Datang, + Nama User");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Kelola sistem lost and found dengan mudah dan efisien.");

        javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
        panelWelcome.setLayout(panelWelcomeLayout);
        panelWelcomeLayout.setHorizontalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelWelcomeLayout.setVerticalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cardUser.setBackground(new java.awt.Color(255, 255, 255));
        cardUser.setForeground(new java.awt.Color(255, 204, 204));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("TOTAL USER ");

        lblTotalUser.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalUser.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalUser.setText("25");

        javax.swing.GroupLayout cardUserLayout = new javax.swing.GroupLayout(cardUser);
        cardUser.setLayout(cardUserLayout);
        cardUserLayout.setHorizontalGroup(
            cardUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardUserLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(cardUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalUser)
                    .addComponent(jLabel11))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        cardUserLayout.setVerticalGroup(
            cardUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardUserLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalUser)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        cardHilang.setBackground(new java.awt.Color(255, 255, 255));
        cardHilang.setForeground(new java.awt.Color(255, 204, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 51));
        jLabel13.setText("BARANG HILANG");

        lblTotalHilang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalHilang.setForeground(new java.awt.Color(204, 0, 51));
        lblTotalHilang.setText("23");

        javax.swing.GroupLayout cardHilangLayout = new javax.swing.GroupLayout(cardHilang);
        cardHilang.setLayout(cardHilangLayout);
        cardHilangLayout.setHorizontalGroup(
            cardHilangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHilangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cardHilangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(lblTotalHilang))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        cardHilangLayout.setVerticalGroup(
            cardHilangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHilangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalHilang)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        cardTemuan.setBackground(new java.awt.Color(255, 255, 255));
        cardTemuan.setForeground(new java.awt.Color(255, 204, 204));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 204, 0));
        jLabel15.setText("BARANG TEMUAN");

        lblTotalTemuan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalTemuan.setForeground(new java.awt.Color(102, 204, 0));
        lblTotalTemuan.setText("20");

        javax.swing.GroupLayout cardTemuanLayout = new javax.swing.GroupLayout(cardTemuan);
        cardTemuan.setLayout(cardTemuanLayout);
        cardTemuanLayout.setHorizontalGroup(
            cardTemuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTemuanLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(cardTemuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblTotalTemuan))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        cardTemuanLayout.setVerticalGroup(
            cardTemuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTemuanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalTemuan)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        cardKlaim.setBackground(new java.awt.Color(255, 255, 255));
        cardKlaim.setForeground(new java.awt.Color(255, 204, 204));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 0, 153));
        jLabel19.setText("KLAIM SELESAI");

        lblTotalKlaim.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalKlaim.setForeground(new java.awt.Color(153, 0, 153));
        lblTotalKlaim.setText("15");

        javax.swing.GroupLayout cardKlaimLayout = new javax.swing.GroupLayout(cardKlaim);
        cardKlaim.setLayout(cardKlaimLayout);
        cardKlaimLayout.setHorizontalGroup(
            cardKlaimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardKlaimLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(cardKlaimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(lblTotalKlaim))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        cardKlaimLayout.setVerticalGroup(
            cardKlaimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardKlaimLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalKlaim)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        panelAktivitas.setBackground(new java.awt.Color(245, 249, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 102));
        jLabel21.setText("AKTIVITAS TERBARU");

        tblAktivitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAktivitas);

        javax.swing.GroupLayout panelAktivitasLayout = new javax.swing.GroupLayout(panelAktivitas);
        panelAktivitas.setLayout(panelAktivitasLayout);
        panelAktivitasLayout.setHorizontalGroup(
            panelAktivitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAktivitasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAktivitasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE))
        );
        panelAktivitasLayout.setVerticalGroup(
            panelAktivitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAktivitasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cardHilang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cardTemuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cardKlaim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelAktivitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardHilang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTemuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardKlaim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAktivitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahUserActionPerformed
User.tambah_user form = new User.tambah_user();
    form.setVisible(true);

    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahUserActionPerformed

    private void btnTambahLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLaporanActionPerformed
TambahLaporanForm form = new TambahLaporanForm();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahLaporanActionPerformed

    private void btnLihatBarangHilangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatBarangHilangActionPerformed
lihat_barang_hilang form = new lihat_barang_hilang();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLihatBarangHilangActionPerformed

    private void btnKlaimBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKlaimBarangActionPerformed
 klaim_barang form = new klaim_barang();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnKlaimBarangActionPerformed

    private void btnDataPenemuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPenemuActionPerformed
penemu form = new penemu();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnDataPenemuActionPerformed

    private void btnLihatBarangTemuan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatBarangTemuan1ActionPerformed
  lihat_barang_temuan form = new lihat_barang_temuan();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLihatBarangTemuan1ActionPerformed

    private void btnLihatKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatKlaimActionPerformed
 lihat_klaim_barang form = new lihat_klaim_barang();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLihatKlaimActionPerformed

    private void btnLihatUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatUserActionPerformed
       User.lihat_user form = new User.lihat_user();
    form.setVisible(true);

    this.dispose(); // TODO add your handling code here:
    }//GEN-LAST:event_btnLihatUserActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       int pilih = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin logout?",
            "Konfirmasi Logout",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (pilih == javax.swing.JOptionPane.YES_OPTION) {

        login lg = new login();
        lg.setVisible(true);

        this.dispose();
    } // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDataPenemu;
    private javax.swing.JButton btnKlaimBarang;
    private javax.swing.JButton btnLihatBarangHilang;
    private javax.swing.JButton btnLihatBarangTemuan1;
    private javax.swing.JButton btnLihatKlaim;
    private javax.swing.JButton btnLihatUser;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnTambahLaporan;
    private javax.swing.JButton btnTambahUser;
    private javax.swing.JPanel cardHilang;
    private javax.swing.JPanel cardKlaim;
    private javax.swing.JPanel cardTemuan;
    private javax.swing.JPanel cardTemuan1;
    private javax.swing.JPanel cardUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalHilang;
    private javax.swing.JLabel lblTotalKlaim;
    private javax.swing.JLabel lblTotalTemuan;
    private javax.swing.JLabel lblTotalUser;
    private javax.swing.JPanel panelAktivitas;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelWelcome;
    private javax.swing.JTable tblAktivitas;
    // End of variables declaration//GEN-END:variables
}
