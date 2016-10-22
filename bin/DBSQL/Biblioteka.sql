-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 19, 2016 at 12:13 PM
-- Server version: 5.7.15-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Biblioteka`
--
CREATE DATABASE IF NOT EXISTS `Biblioteka` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Biblioteka`;

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE `autor` (
  `sifAutor` int(11) NOT NULL,
  `imeAutor` varchar(30) NOT NULL,
  `prezAutor` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `autor`
--

INSERT INTO `autor` (`sifAutor`, `imeAutor`, `prezAutor`) VALUES
(1, 'Amer', 'Hasanovic'),
(2, 'Aljo', 'Mujcic'),
(3, 'Naser', 'Prljaca'),
(4, 'Zenan', 'Sehic'),
(5, 'Emir', 'Meskovic'),
(6, 'Edin', 'Pjanic'),
(7, 'Mirza', 'Kusljugic'),
(8, 'Amir', 'Nuhanovic'),
(9, 'Jasmin', 'Zigic'),
(10, 'Izudin', 'Softic');

-- --------------------------------------------------------

--
-- Table structure for table `autorRBr`
--

CREATE TABLE `autorRBr` (
  `sifAutorRBr` int(11) NOT NULL,
  `rBrAutora` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `autorRBr`
--

INSERT INTO `autorRBr` (`sifAutorRBr`, `rBrAutora`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `izdavac`
--

CREATE TABLE `izdavac` (
  `sifIzdavac` int(11) NOT NULL,
  `nazIzdavac` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `izdavac`
--

INSERT INTO `izdavac` (`sifIzdavac`, `nazIzdavac`) VALUES
(1, 'UNTZ'),
(2, 'Svjetlost'),
(3, 'NAM'),
(4, 'Brzi');

-- --------------------------------------------------------

--
-- Table structure for table `knjiga`
--

CREATE TABLE `knjiga` (
  `sifKnjiga` int(11) NOT NULL,
  `naslov` varchar(50) NOT NULL,
  `origNaslov` varchar(50) NOT NULL,
  `brStranica` int(11) NOT NULL,
  `godIzdanja` date NOT NULL,
  `negBodovi` int(11) NOT NULL,
  `brPrimjeraka` int(11) NOT NULL,
  `sifIzdavac` int(11) NOT NULL,
  `sifVrstaKnjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `knjiga`
--

INSERT INTO `knjiga` (`sifKnjiga`, `naslov`, `origNaslov`, `brStranica`, `godIzdanja`, `negBodovi`, `brPrimjeraka`, `sifIzdavac`, `sifVrstaKnjige`) VALUES
(1, 'Optimizacione metode', 'Optimizacione metode u elektrotehnici', 500, '2014-01-01', 8, 4, 1, 1),
(2, 'Numericke metode', 'Numericke metode u elektrotehnici', 432, '2012-01-01', 4, 2, 1, 1),
(3, 'Operativni sistemi', 'Operativni sistemi', 432, '2015-01-01', 4, 5, 2, 1),
(4, 'Baze', 'Baze podataka', 121, '2012-01-01', 3, 0, 4, 3),
(5, 'Elektronika', 'Elektronika', 332, '2011-01-01', 6, 1, 3, 2),
(6, 'Analogna elektronika', 'Analogna integrisana elektronika', 332, '2011-01-01', 5, 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `KnjigaAutorRBr`
--

CREATE TABLE `KnjigaAutorRBr` (
  `sifKnjigaAutorRBr` int(11) NOT NULL,
  `sifKnjiga` int(11) NOT NULL,
  `sifAutor` int(11) NOT NULL,
  `sifAutorRBr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `KnjigaAutorRBr`
--

INSERT INTO `KnjigaAutorRBr` (`sifKnjigaAutorRBr`, `sifKnjiga`, `sifAutor`, `sifAutorRBr`) VALUES
(1, 1, 8, 1),
(2, 2, 8, 1),
(3, 2, 10, 2),
(4, 3, 1, 1),
(5, 3, 6, 2),
(6, 4, 5, 1),
(7, 5, 2, 1),
(8, 6, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `KnjigaPredmetObaveznost`
--

CREATE TABLE `KnjigaPredmetObaveznost` (
  `sifKnjPredObav` int(11) NOT NULL,
  `sifVaznObav` int(11) NOT NULL,
  `sifKnjiga` int(11) NOT NULL,
  `sifPredmet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nastavnik`
--

CREATE TABLE `nastavnik` (
  `sifNastavnik` int(11) NOT NULL,
  `imeNastavnik` varchar(30) NOT NULL,
  `prezNastavnik` varchar(30) NOT NULL,
  `zvanje` varchar(30) NOT NULL,
  `negBodovi` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `bibliotekar` int(11) NOT NULL,
  `brPosudjenihKnjiga` int(11) NOT NULL,
  `brRezervacija` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nastavnik`
--

INSERT INTO `nastavnik` (`sifNastavnik`, `imeNastavnik`, `prezNastavnik`, `zvanje`, `negBodovi`, `password`, `bibliotekar`, `brPosudjenihKnjiga`, `brRezervacija`) VALUES
(1, 'Emir', 'Meskovic', 'Docent profesor', 0, 'emir', 1, 0, 0),
(2, 'Amer', 'Hasanovic', 'Vanredan profesor', 0, 'amer', 0, 0, 0),
(3, 'Edin', 'Pjanic', 'Docent profesor', 0, 'edin', 0, 0, 0),
(4, 'Aljo', 'Mujcic', 'Vanredan profesor', 0, 'aljo', 0, 0, 0),
(5, 'Dario', 'Osmanovic', 'Asistent', 0, 'dario', 0, 0, 0),
(6, 'Selvin', 'Fehric', 'Asistent', 0, 'selvin', 0, 0, 0),
(7, 'Naser', 'Prljaca', 'Redovan profesor', 0, 'naser', 0, 0, 0),
(8, 'Zenan', 'Sehic', 'Redovan profesor', 0, 'zenan', 0, 0, 0),
(9, 'Asmir', 'Gogic', 'Docent profesor', 0, 'asmir', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `obaveznost`
--

CREATE TABLE `obaveznost` (
  `sifObaveznost` int(11) NOT NULL,
  `obavezna` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obaveznost`
--

INSERT INTO `obaveznost` (`sifObaveznost`, `obavezna`) VALUES
(1, 'DA'),
(2, 'NE');

-- --------------------------------------------------------

--
-- Table structure for table `predmet`
--

CREATE TABLE `predmet` (
  `sifPredmet` int(11) NOT NULL,
  `nazPredmet` varchar(50) NOT NULL,
  `kratPredmet` varchar(30) NOT NULL,
  `sifNastavnik` int(11) NOT NULL,
  `sifSemestar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predmet`
--

INSERT INTO `predmet` (`sifPredmet`, `nazPredmet`, `kratPredmet`, `sifNastavnik`, `sifSemestar`) VALUES
(1, 'Razvoj Softvera', 'RS', 1, 7),
(2, 'Baze Podataka', 'BP', 1, 7),
(3, 'Operativni Sistemi', 'OS', 2, 6),
(4, 'Algoritmi', 'ALG', 3, 5),
(5, 'Strukture Podataka', 'SP', 3, 6),
(6, 'Windows Programiranje', 'WP', 3, 4),
(7, 'Teorija informacija i kodovanje', 'TIK', 4, 5),
(8, 'Osnovic elektronike', 'TIK', 4, 3),
(9, 'Baze Podataka Lab', 'BP Lab', 5, 7),
(10, 'Automatsko Upravljanje I', 'AUI', 7, 5),
(11, 'Automatsko Upravljanje II', 'AUII', 7, 6);

-- --------------------------------------------------------

--
-- Table structure for table `primjerak`
--

CREATE TABLE `primjerak` (
  `sifPrimjerak` int(11) NOT NULL,
  `inventarniBr` varchar(30) NOT NULL,
  `datumNabavke` date NOT NULL,
  `stanje` varchar(30) NOT NULL,
  `rezervisan` int(11) DEFAULT NULL,
  `sifKnjiga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `primjerak`
--

INSERT INTO `primjerak` (`sifPrimjerak`, `inventarniBr`, `datumNabavke`, `stanje`, `rezervisan`, `sifKnjiga`) VALUES
(1, 'opt01', '2016-09-04', 'Odli?no', 1, 1),
(2, 'opt02', '2016-09-04', 'Odli?no', 0, 1),
(3, 'opt03', '2016-09-04', 'Odli?no', 0, 1),
(4, 'opt04', '2016-09-04', 'Odli?no', 0, 1),
(5, 'num01', '2016-09-07', 'Odli?no', 1, 2),
(6, 'num02', '2016-09-07', 'Odli?no', 0, 2),
(7, 'os01', '2016-09-07', 'Odli?no', 1, 3),
(8, 'os02', '2016-09-05', 'Odli?no', 1, 3),
(9, 'os03', '2016-09-05', 'Odli?no', 0, 3),
(10, 'os04', '2016-09-05', 'Odli?no', 0, 3),
(11, 'os05', '2016-09-05', 'Odli?no', 0, 3),
(12, 'El01', '2016-09-05', 'Odli?no', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE `rezervacija` (
  `sifRezervacija` int(11) NOT NULL,
  `datRezervacija` date NOT NULL,
  `datPodizanja` date DEFAULT NULL,
  `datVracanja` date NOT NULL,
  `datKadVracena` date DEFAULT NULL,
  `sifKorisnik` int(11) NOT NULL,
  `sifPrimjerak` int(11) NOT NULL,
  `nastStud` int(11) NOT NULL,
  `odobrena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`sifRezervacija`, `datRezervacija`, `datPodizanja`, `datVracanja`, `datKadVracena`, `sifKorisnik`, `sifPrimjerak`, `nastStud`, `odobrena`) VALUES
(1, '2016-09-19', NULL, '2016-09-30', NULL, 1, 12, 2, 0),
(2, '2016-09-19', NULL, '2016-11-18', NULL, 1, 5, 2, 0),
(3, '2016-09-19', NULL, '2016-10-21', NULL, 1, 7, 2, 0),
(4, '2016-09-19', NULL, '2016-09-30', NULL, 3, 8, 2, 0),
(5, '2016-09-19', NULL, '2016-12-09', NULL, 3, 1, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `RezervacijaPrimjerakNastavnik`
--

CREATE TABLE `RezervacijaPrimjerakNastavnik` (
  `sifRezPrimNast` int(11) NOT NULL,
  `sifRezervacija` int(11) NOT NULL,
  `sifPrimjerak` int(11) NOT NULL,
  `sifNastavnik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `RezervacijaPrimjerakStudent`
--

CREATE TABLE `RezervacijaPrimjerakStudent` (
  `sifRezPrimStud` int(11) NOT NULL,
  `sifRezervacija` int(11) NOT NULL,
  `sifPrimjerak` int(11) NOT NULL,
  `sifStudent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RezervacijaPrimjerakStudent`
--

INSERT INTO `RezervacijaPrimjerakStudent` (`sifRezPrimStud`, `sifRezervacija`, `sifPrimjerak`, `sifStudent`) VALUES
(1, 1, 12, 1),
(2, 2, 5, 1),
(3, 3, 7, 1),
(4, 4, 8, 3),
(5, 5, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `semestar`
--

CREATE TABLE `semestar` (
  `sifSemestar` int(11) NOT NULL,
  `semestar` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semestar`
--

INSERT INTO `semestar` (`sifSemestar`, `semestar`) VALUES
(1, 'I'),
(2, 'II'),
(3, 'III'),
(4, 'IV'),
(5, 'V'),
(6, 'VI'),
(7, 'VII'),
(8, 'VIII');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `sifStudent` int(11) NOT NULL,
  `imeStudent` varchar(30) NOT NULL,
  `prezStudent` varchar(30) NOT NULL,
  `brIndexa` varchar(10) NOT NULL,
  `negBodovi` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `brPosudjenihKnjiga` int(11) NOT NULL,
  `brRezervacija` int(11) NOT NULL,
  `sifSemestar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`sifStudent`, `imeStudent`, `prezStudent`, `brIndexa`, `negBodovi`, `password`, `brPosudjenihKnjiga`, `brRezervacija`, `sifSemestar`) VALUES
(1, 'Dino', 'Zecevic', 'dino', 0, 'dino', 0, 3, 8),
(2, 'Mirza', 'Omerkic', 'mirza', 0, 'mirza', 0, 0, 6),
(3, 'Mizbah', 'Alic', 'mizbah', 0, 'mizbah', 0, 2, 4),
(4, 'Alen', 'Avdic', 'alen', 0, 'alen', 0, 0, 1),
(5, 'Sinisa', 'Suljkic', 'sinisa', 0, 'sinisa', 0, 0, 2),
(6, 'Suad', 'Suljic', 'suad', 0, 'suad', 0, 0, 4),
(7, 'Jasmin', 'Zigic', 'jasmin', 0, 'jasmin', 0, 0, 7);

-- --------------------------------------------------------

--
-- Table structure for table `vaznost`
--

CREATE TABLE `vaznost` (
  `sifVaznost` int(11) NOT NULL,
  `rBrVaznost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vaznost`
--

INSERT INTO `vaznost` (`sifVaznost`, `rBrVaznost`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `VaznostObaveznost`
--

CREATE TABLE `VaznostObaveznost` (
  `sifVaznObav` int(11) NOT NULL,
  `sifVaznost` int(11) NOT NULL,
  `sifObaveznost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `VaznostObaveznost`
--

INSERT INTO `VaznostObaveznost` (`sifVaznObav`, `sifVaznost`, `sifObaveznost`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1),
(4, 2, 2),
(5, 3, 1),
(6, 3, 2),
(7, 4, 1),
(8, 4, 2),
(9, 5, 1),
(10, 5, 2),
(11, 6, 1),
(12, 6, 2),
(13, 7, 1),
(14, 7, 2),
(15, 8, 1),
(16, 8, 2),
(17, 9, 1),
(18, 9, 2),
(19, 10, 1),
(20, 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `vrstaKnjige`
--

CREATE TABLE `vrstaKnjige` (
  `sifVrstaKnjige` int(11) NOT NULL,
  `vrsta` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vrstaKnjige`
--

INSERT INTO `vrstaKnjige` (`sifVrstaKnjige`, `vrsta`) VALUES
(1, 'Knjiga'),
(2, 'Zbirka zadataka'),
(3, 'Prirucnik'),
(4, 'Diplomski rad'),
(5, 'Magistarski rad'),
(6, 'Doktorski rad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`sifAutor`);

--
-- Indexes for table `autorRBr`
--
ALTER TABLE `autorRBr`
  ADD PRIMARY KEY (`sifAutorRBr`);

--
-- Indexes for table `izdavac`
--
ALTER TABLE `izdavac`
  ADD PRIMARY KEY (`sifIzdavac`);

--
-- Indexes for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD PRIMARY KEY (`sifKnjiga`),
  ADD KEY `sifVrstaKnjige` (`sifVrstaKnjige`),
  ADD KEY `sifIzdavac` (`sifIzdavac`);

--
-- Indexes for table `KnjigaAutorRBr`
--
ALTER TABLE `KnjigaAutorRBr`
  ADD PRIMARY KEY (`sifKnjigaAutorRBr`),
  ADD KEY `sifAutorRBr` (`sifAutorRBr`),
  ADD KEY `sifAutor` (`sifAutor`),
  ADD KEY `sifKnjiga` (`sifKnjiga`);

--
-- Indexes for table `KnjigaPredmetObaveznost`
--
ALTER TABLE `KnjigaPredmetObaveznost`
  ADD PRIMARY KEY (`sifKnjPredObav`),
  ADD KEY `sifVaznObav` (`sifVaznObav`),
  ADD KEY `sifKnjiga` (`sifKnjiga`),
  ADD KEY `sifPredmet` (`sifPredmet`);

--
-- Indexes for table `nastavnik`
--
ALTER TABLE `nastavnik`
  ADD PRIMARY KEY (`sifNastavnik`);

--
-- Indexes for table `obaveznost`
--
ALTER TABLE `obaveznost`
  ADD PRIMARY KEY (`sifObaveznost`);

--
-- Indexes for table `predmet`
--
ALTER TABLE `predmet`
  ADD PRIMARY KEY (`sifPredmet`),
  ADD KEY `sifSemestar` (`sifSemestar`),
  ADD KEY `sifNastavnik` (`sifNastavnik`);

--
-- Indexes for table `primjerak`
--
ALTER TABLE `primjerak`
  ADD PRIMARY KEY (`sifPrimjerak`),
  ADD KEY `sifKnjiga` (`sifKnjiga`);

--
-- Indexes for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD PRIMARY KEY (`sifRezervacija`);

--
-- Indexes for table `RezervacijaPrimjerakNastavnik`
--
ALTER TABLE `RezervacijaPrimjerakNastavnik`
  ADD PRIMARY KEY (`sifRezPrimNast`),
  ADD KEY `sifNastavnik` (`sifNastavnik`),
  ADD KEY `sifPrimjerak` (`sifPrimjerak`),
  ADD KEY `sifRezervacija` (`sifRezervacija`);

--
-- Indexes for table `RezervacijaPrimjerakStudent`
--
ALTER TABLE `RezervacijaPrimjerakStudent`
  ADD PRIMARY KEY (`sifRezPrimStud`),
  ADD KEY `sifStudent` (`sifStudent`),
  ADD KEY `sifPrimjerak` (`sifPrimjerak`),
  ADD KEY `sifRezervacija` (`sifRezervacija`);

--
-- Indexes for table `semestar`
--
ALTER TABLE `semestar`
  ADD PRIMARY KEY (`sifSemestar`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`sifStudent`),
  ADD KEY `sifSemestar` (`sifSemestar`);

--
-- Indexes for table `vaznost`
--
ALTER TABLE `vaznost`
  ADD PRIMARY KEY (`sifVaznost`);

--
-- Indexes for table `VaznostObaveznost`
--
ALTER TABLE `VaznostObaveznost`
  ADD PRIMARY KEY (`sifVaznObav`),
  ADD KEY `sifObaveznost` (`sifObaveznost`),
  ADD KEY `sifVaznost` (`sifVaznost`);

--
-- Indexes for table `vrstaKnjige`
--
ALTER TABLE `vrstaKnjige`
  ADD PRIMARY KEY (`sifVrstaKnjige`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `sifAutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `autorRBr`
--
ALTER TABLE `autorRBr`
  MODIFY `sifAutorRBr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `izdavac`
--
ALTER TABLE `izdavac`
  MODIFY `sifIzdavac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `sifKnjiga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `KnjigaAutorRBr`
--
ALTER TABLE `KnjigaAutorRBr`
  MODIFY `sifKnjigaAutorRBr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `KnjigaPredmetObaveznost`
--
ALTER TABLE `KnjigaPredmetObaveznost`
  MODIFY `sifKnjPredObav` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `nastavnik`
--
ALTER TABLE `nastavnik`
  MODIFY `sifNastavnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `obaveznost`
--
ALTER TABLE `obaveznost`
  MODIFY `sifObaveznost` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `predmet`
--
ALTER TABLE `predmet`
  MODIFY `sifPredmet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `primjerak`
--
ALTER TABLE `primjerak`
  MODIFY `sifPrimjerak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `sifRezervacija` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `RezervacijaPrimjerakNastavnik`
--
ALTER TABLE `RezervacijaPrimjerakNastavnik`
  MODIFY `sifRezPrimNast` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `RezervacijaPrimjerakStudent`
--
ALTER TABLE `RezervacijaPrimjerakStudent`
  MODIFY `sifRezPrimStud` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `semestar`
--
ALTER TABLE `semestar`
  MODIFY `sifSemestar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `sifStudent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `vaznost`
--
ALTER TABLE `vaznost`
  MODIFY `sifVaznost` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `VaznostObaveznost`
--
ALTER TABLE `VaznostObaveznost`
  MODIFY `sifVaznObav` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `vrstaKnjige`
--
ALTER TABLE `vrstaKnjige`
  MODIFY `sifVrstaKnjige` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD CONSTRAINT `knjiga_ibfk_1` FOREIGN KEY (`sifIzdavac`) REFERENCES `izdavac` (`sifIzdavac`),
  ADD CONSTRAINT `knjiga_ibfk_2` FOREIGN KEY (`sifVrstaKnjige`) REFERENCES `vrstaKnjige` (`sifVrstaKnjige`);

--
-- Constraints for table `KnjigaAutorRBr`
--
ALTER TABLE `KnjigaAutorRBr`
  ADD CONSTRAINT `KnjigaAutorRBr_ibfk_1` FOREIGN KEY (`sifKnjiga`) REFERENCES `knjiga` (`sifKnjiga`),
  ADD CONSTRAINT `KnjigaAutorRBr_ibfk_2` FOREIGN KEY (`sifAutor`) REFERENCES `autor` (`sifAutor`),
  ADD CONSTRAINT `KnjigaAutorRBr_ibfk_3` FOREIGN KEY (`sifAutorRBr`) REFERENCES `autorRBr` (`sifAutorRBr`);

--
-- Constraints for table `KnjigaPredmetObaveznost`
--
ALTER TABLE `KnjigaPredmetObaveznost`
  ADD CONSTRAINT `KnjigaPredmetObaveznost_ibfk_1` FOREIGN KEY (`sifVaznObav`) REFERENCES `VaznostObaveznost` (`sifVaznObav`),
  ADD CONSTRAINT `KnjigaPredmetObaveznost_ibfk_2` FOREIGN KEY (`sifKnjiga`) REFERENCES `knjiga` (`sifKnjiga`),
  ADD CONSTRAINT `KnjigaPredmetObaveznost_ibfk_3` FOREIGN KEY (`sifPredmet`) REFERENCES `predmet` (`sifPredmet`);

--
-- Constraints for table `predmet`
--
ALTER TABLE `predmet`
  ADD CONSTRAINT `predmet_ibfk_1` FOREIGN KEY (`sifNastavnik`) REFERENCES `nastavnik` (`sifNastavnik`),
  ADD CONSTRAINT `predmet_ibfk_2` FOREIGN KEY (`sifSemestar`) REFERENCES `semestar` (`sifSemestar`);

--
-- Constraints for table `primjerak`
--
ALTER TABLE `primjerak`
  ADD CONSTRAINT `primjerak_ibfk_1` FOREIGN KEY (`sifKnjiga`) REFERENCES `knjiga` (`sifKnjiga`);

--
-- Constraints for table `RezervacijaPrimjerakNastavnik`
--
ALTER TABLE `RezervacijaPrimjerakNastavnik`
  ADD CONSTRAINT `RezervacijaPrimjerakNastavnik_ibfk_1` FOREIGN KEY (`sifRezervacija`) REFERENCES `rezervacija` (`sifRezervacija`),
  ADD CONSTRAINT `RezervacijaPrimjerakNastavnik_ibfk_2` FOREIGN KEY (`sifPrimjerak`) REFERENCES `primjerak` (`sifPrimjerak`),
  ADD CONSTRAINT `RezervacijaPrimjerakNastavnik_ibfk_3` FOREIGN KEY (`sifNastavnik`) REFERENCES `nastavnik` (`sifNastavnik`);

--
-- Constraints for table `RezervacijaPrimjerakStudent`
--
ALTER TABLE `RezervacijaPrimjerakStudent`
  ADD CONSTRAINT `RezervacijaPrimjerakStudent_ibfk_1` FOREIGN KEY (`sifRezervacija`) REFERENCES `rezervacija` (`sifRezervacija`),
  ADD CONSTRAINT `RezervacijaPrimjerakStudent_ibfk_2` FOREIGN KEY (`sifPrimjerak`) REFERENCES `primjerak` (`sifPrimjerak`),
  ADD CONSTRAINT `RezervacijaPrimjerakStudent_ibfk_3` FOREIGN KEY (`sifStudent`) REFERENCES `student` (`sifStudent`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`sifSemestar`) REFERENCES `semestar` (`sifSemestar`);

--
-- Constraints for table `VaznostObaveznost`
--
ALTER TABLE `VaznostObaveznost`
  ADD CONSTRAINT `VaznostObaveznost_ibfk_1` FOREIGN KEY (`sifVaznost`) REFERENCES `vaznost` (`sifVaznost`),
  ADD CONSTRAINT `VaznostObaveznost_ibfk_2` FOREIGN KEY (`sifObaveznost`) REFERENCES `obaveznost` (`sifObaveznost`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
