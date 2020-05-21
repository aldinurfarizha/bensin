-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 21 Bulan Mei 2020 pada 16.48
-- Versi server: 10.1.43-MariaDB-cll-lve
-- Versi PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bensin`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(100) NOT NULL,
  `stand_meter` int(100) NOT NULL,
  `pembelian` int(100) NOT NULL,
  `penjualan` int(10) NOT NULL,
  `stock` int(100) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `stand_meter`, `pembelian`, `penjualan`, `stock`, `tanggal`) VALUES
(0, 45, 66, 45, 56, '2020-04-11'),
(12, 83, 66, 38, 84, '2020-04-12'),
(13, 116, 0, 33, 51, '2020-04-13'),
(14, 167, 66, 51, 66, '2020-04-14'),
(15, 235, 66, 68, 64, '2020-04-15'),
(16, 277, 0, 42, 22, '2020-04-16'),
(17, 336, 66, 59, 29, '2020-04-17'),
(18, 381, 66, 45, 50, '2020-04-18'),
(19, 438, 66, 57, 59, '2020-04-19'),
(20, 503, 66, 65, 60, '2020-04-20'),
(21, 542, 0, 39, 21, '2020-04-21'),
(22, 618, 81, 76, 26, '2020-04-22'),
(23, 690, 78, 72, 32, '2020-04-23'),
(24, 755, 79, 65, 46, '2020-04-24'),
(25, 821, 66, 66, 46, '2020-04-25'),
(27, 873, 66, 52, 60, '2020-04-26'),
(28, 919, 0, 46, 14, '2020-04-27'),
(31, 962, 79, 43, 50, '2020-04-28'),
(32, 1033, 71, 71, 50, '2020-04-29'),
(34, 1090, 66, 57, 59, '2020-04-30'),
(35, 52, 0, 52, 7, '2020-05-01'),
(36, 126, 78, 74, 11, '2020-05-02'),
(39, 187, 66, 61, 16, '2020-05-03'),
(40, 241, 66, 54, 28, '2020-05-04'),
(41, 340, 132, 99, 61, '2020-05-05'),
(42, 388, 0, 48, 13, '2020-05-06'),
(43, 446, 76, 58, 31, '2020-05-07'),
(44, 502, 76, 56, 51, '2020-05-08'),
(45, 557, 66, 55, 62, '2020-05-09'),
(46, 623, 66, 66, 62, '2020-05-10'),
(47, 678, 0, 55, 7, '2020-05-11'),
(48, 749, 71, 71, 7, '2020-05-12'),
(49, 817, 66, 68, 5, '2020-05-13'),
(50, 880, 66, 63, 8, '2020-05-14'),
(51, 962, 136, 82, 62, '2020-05-15'),
(52, 1076, 66, 114, 14, '2020-05-16'),
(53, 1157, 71, 81, 4, '2020-05-17'),
(54, 1213, 66, 56, 14, '2020-05-18'),
(55, 1290, 66, 77, 3, '2020-05-19'),
(56, 1407, 136, 117, 22, '2020-05-20');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
