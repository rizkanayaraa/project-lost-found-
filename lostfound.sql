-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2026 at 08:24 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_lost_found`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang_hilang`
--

CREATE TABLE `barang_hilang` (
  `id_barang_hilang` int(11) NOT NULL,
  `nama_pelapor` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `lokasi_hilang` varchar(100) NOT NULL,
  `tanggal_hilang` date NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang_hilang`
--

INSERT INTO `barang_hilang` (`id_barang_hilang`, `nama_pelapor`, `nama_barang`, `lokasi_hilang`, `tanggal_hilang`, `deskripsi`) VALUES
(1, 'Andi Saputra', 'Dompet Hitam', 'Perpustakaan', '2025-06-01', 'Dompet berisi KTP dan ATM'),
(2, 'Budi Santoso', 'Laptop Asus', 'Lab Komputer', '2025-06-03', 'Laptop warna hitam'),
(3, 'Citra Lestari', 'Power Bank', 'Kantin', '2025-06-05', 'Power bank 20000mAh'),
(4, 'Dewi Anggraini', 'Kunci Motor', 'Parkiran A', '2025-06-07', 'Gantungan warna merah'),
(5, 'Eko Pratama', 'Tas Ransel', 'Ruang Kelas B203', '2025-06-10', 'Tas warna biru');

-- --------------------------------------------------------

--
-- Table structure for table `barang_temuan`
--

CREATE TABLE `barang_temuan` (
  `id_barang_temuan` int(11) NOT NULL,
  `nama_penemu` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `lokasi_temuan` varchar(100) NOT NULL,
  `tanggal_temuan` date NOT NULL,
  `status_pengambilan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang_temuan`
--

INSERT INTO `barang_temuan` (`id_barang_temuan`, `nama_penemu`, `nama_barang`, `lokasi_temuan`, `tanggal_temuan`, `status_pengambilan`) VALUES
(1, 'Rina', 'Dompet Hitam', 'Perpustakaan', '2025-06-02', 'Belum Diambil'),
(2, 'Fajar', 'Laptop Asus', 'Lab Komputer', '2025-06-04', 'Belum Diambil'),
(3, 'Siska', 'Power Bank', 'Kantin', '2025-06-06', 'Sudah Diambil'),
(4, 'Yoga', 'Kunci Motor', 'Parkiran A', '2025-06-08', 'Belum Diambil'),
(5, 'Nanda', 'Tas Ransel', 'Ruang Kelas B203', '2025-06-11', 'Belum Diambil');

-- --------------------------------------------------------

--
-- Table structure for table `klaim_barang`
--

CREATE TABLE `klaim_barang` (
  `id_klaim` int(11) NOT NULL,
  `id_barang_temuan` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `tanggal_klaim` date NOT NULL,
  `status_verifikasi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klaim_barang`
--

INSERT INTO `klaim_barang` (`id_klaim`, `id_barang_temuan`, `id_user`, `tanggal_klaim`, `status_verifikasi`) VALUES
(1, 1, 1, '2025-06-03', 'Disetujui'),
(2, 2, 2, '2025-06-05', 'Menunggu'),
(3, 3, 3, '2025-06-07', 'Disetujui'),
(4, 4, 4, '2025-06-09', 'Ditolak'),
(5, 5, 5, '2025-06-12', 'Menunggu');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nim` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `nim`, `email`, `no_hp`, `username`, `password`) VALUES
(1, 'Andi Saputra', '221001001', 'andi@gmail.com', '081234567890', 'andi', '12345'),
(2, 'Budi Santoso', '221001002', 'budi@gmail.com', '081234567891', 'budi', '12345'),
(3, 'Citra Lestari', '221001003', 'citra@gmail.com', '081234567892', 'citra', '12345'),
(4, 'Dewi Anggraini', '221001004', 'dewi@gmail.com', '081234567893', 'dewi', '12345'),
(5, 'Eko Pratama', '221001005', 'eko@gmail.com', '081234567894', 'eko', '12345'),
(6, 'rizka', '241011', 'rizka@gmail.com', '0888888888', 'rizka', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang_hilang`
--
ALTER TABLE `barang_hilang`
  ADD PRIMARY KEY (`id_barang_hilang`),
  ADD KEY `id_user` (`nama_pelapor`);

--
-- Indexes for table `barang_temuan`
--
ALTER TABLE `barang_temuan`
  ADD PRIMARY KEY (`id_barang_temuan`),
  ADD KEY `id_user` (`nama_penemu`);

--
-- Indexes for table `klaim_barang`
--
ALTER TABLE `klaim_barang`
  ADD PRIMARY KEY (`id_klaim`),
  ADD KEY `id_barang_temuan` (`id_barang_temuan`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang_hilang`
--
ALTER TABLE `barang_hilang`
  MODIFY `id_barang_hilang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `barang_temuan`
--
ALTER TABLE `barang_temuan`
  MODIFY `id_barang_temuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `klaim_barang`
--
ALTER TABLE `klaim_barang`
  MODIFY `id_klaim` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `klaim_barang`
--
ALTER TABLE `klaim_barang`
  ADD CONSTRAINT `klaim_barang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `klaim_barang_ibfk_2` FOREIGN KEY (`id_barang_temuan`) REFERENCES `barang_temuan` (`id_barang_temuan`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
