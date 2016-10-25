-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 25, 2016 at 02:18 PM
-- Server version: 5.7.15-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

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
(2, 'Svjetolost'),
(3, 'NAM'),
(4, 'OffSet'),
(5, 'Hamidovic'),
(6, 'Brzi');

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
(1, 'Numericke metode', 'Numericke metode u elektrotehnici', 152, '2016-01-01', -5, 9, 1, 1),
(2, 'Optimizacione metode', 'Optimizacione metode u elektrotehnici', 152, '2013-01-01', -5, 6, 2, 1),
(3, 'Matematika 1', 'Matematika 1', 212, '2008-01-01', -5, 6, 1, 1),
(4, 'Matematika 2', 'Matematika 2', 521, '2008-01-01', -5, 5, 1, 1),
(5, 'Matematika 3', 'Matematika 3', 198, '2008-01-01', -5, 0, 1, 1),
(6, 'Matematika 1 Zbirka', 'Matematika 1 Zbirka zadataka', 321, '2008-01-01', -5, 10, 1, 2),
(7, 'Matematika 2 Zbirka', 'Matematika 2 Zbirka zadataka', 254, '2008-01-01', -5, 5, 1, 2),
(8, 'Matematika 3 Zbirka', 'Matematika 3 Zbirka zadataka', 325, '2008-01-01', -5, 4, 1, 2),
(9, 'Osnovi elektortehnike 1', 'Osnovi elektortehnike 1', 352, '2008-01-01', -5, 4, 1, 1),
(10, 'Osnovi elektortehnike 2', 'Osnovi elektortehnike 2', 278, '2008-01-01', -5, 2, 1, 1),
(11, 'Osnovi elektortehnike 1 zbirka', 'Osnovi elektortehnike 1 zbirka zadataka', 352, '2008-01-01', -5, 0, 1, 2),
(12, 'Osnovi elektortehnike 2 zbirka', 'Osnovi elektortehnike 2 zbirka zadataka', 278, '2008-01-01', -5, 0, 1, 2),
(13, 'Operativni sistemi', 'Operativni sistemi', 256, '2015-01-01', -5, 4, 1, 1),
(14, 'Baze podataka', 'Baze podataka', 152, '2013-01-01', -5, 5, 1, 1),
(15, 'Osnovi elektronike 1', 'Osnovi elektronike 1', 152, '2013-01-01', -5, 0, 1, 1),
(16, 'Analogna integrisana elektornika', 'Analogna integrisana elektronika', 152, '2013-01-01', -5, 0, 2, 1),
(17, 'Automatsko upravljanje 1', 'Automatsko upravljanje 1', 152, '2013-01-01', -5, 0, 3, 1),
(18, 'Automatsko upravljanje 2', 'Automatsko upravljanje 2', 152, '2013-01-01', -5, 0, 2, 1),
(19, 'Robotika 1', 'Robotika 1', 152, '2013-01-01', -5, 0, 3, 1),
(20, 'Robotika 2', 'Robotika 2', 152, '2013-01-01', -5, 0, 4, 1);

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

--
-- Dumping data for table `KnjigaPredmetObaveznost`
--

INSERT INTO `KnjigaPredmetObaveznost` (`sifKnjPredObav`, `sifVaznObav`, `sifKnjiga`, `sifPredmet`) VALUES
(1, 1, 14, 1),
(2, 1, 15, 3),
(3, 2, 16, 3),
(4, 1, 17, 9),
(5, 3, 18, 9),
(6, 5, 19, 9),
(7, 2, 20, 9);

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
(1, 'Emir', 'Meskovic', 'Vanredan profesor', 0, 'emir', 1, 1, 1),
(2, 'Amer', 'Hasanovic', 'Vanredan profesor', 0, 'amer', 0, 0, 0),
(3, 'Edin', 'Pjanic', 'Vanredan profesor', 0, 'edin', 0, 0, 0),
(4, 'Aljo', 'Mujcic', 'Vanredan profesor', 0, 'aljo', 0, 0, 0),
(5, 'Nermin', 'Suljanovic', 'Vanredan profesor', 0, 'nermin', 0, 0, 0),
(6, 'Asmir', 'Gogic', 'Docent profesor', 0, 'asmir', 0, 0, 0),
(7, 'Naser', 'Prljaca', 'Redovan profesor', 0, 'naser', 0, 0, 0),
(8, 'Amir', 'Nuhanovic', 'Redovan profesor', 0, 'amir', 0, 0, 0),
(9, 'Amir', 'Tokic', 'Vanredan profesor', 0, 'amir', 0, 0, 0),
(10, 'Dario', 'Osmanovic', 'Asistent', 0, 'dario', 0, 0, 0),
(11, 'Lejla', 'Mehmedovic-Banjanovic', 'Vanredan profesor', 0, 'lejla', 0, 0, 0),
(12, 'Selvin', 'Fehric', 'Asistent', 0, 'selvin', 0, 0, 0),
(13, 'Slobodan', 'Manojlovic', 'Asistent', 0, 'slobodan', 0, 0, 0);

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
(1, 'Baze podataka', 'BP', 1, 7),
(2, 'Operativni sistemi', 'OS', 2, 6),
(3, 'Osnovi elektronike', 'OElektr', 4, 3),
(4, 'Analogna integrisana elektronika', 'AIE', 4, 4),
(5, 'Signali i sistemi', 'SIS', 5, 5),
(6, 'Numericke metode u elektrotehnici', 'Num', 8, 4),
(7, 'Optimizacione metode u elektrotehnici', 'Opt', 8, 5),
(8, 'Sistemi u realnom vremenu', 'SRV', 11, 8),
(9, 'Automatsko upravljanje 1', 'AU1', 7, 5),
(10, 'Automatsko upravljanje 2', 'AU2', 7, 6);

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
(1, 'num01', '2016-03-01', 'Odli?no', 0, 1),
(2, 'num02', '2016-03-01', 'Odli?no', 0, 1),
(3, 'num03', '2016-03-01', 'Odli?no', 0, 1),
(4, 'num04', '2016-03-01', 'Odli?no', 0, 1),
(5, 'num05', '2016-03-01', 'Odli?no', 0, 1),
(6, 'num06', '2016-03-01', 'Odli?no', 0, 1),
(7, 'num07', '2016-03-01', 'Odli?no', 0, 1),
(8, 'num08', '2016-03-01', 'Odli?no', 0, 1),
(9, 'num09', '2016-03-01', 'Odli?no', 0, 1),
(10, 'opt01', '2016-03-01', 'Odli?no', 0, 2),
(11, 'opt02', '2016-03-01', 'Odli?no', 0, 2),
(12, 'opt03', '2016-03-01', 'Odli?no', 0, 2),
(13, 'opt04', '2016-03-01', 'Odli?no', 0, 2),
(14, 'opt05', '2016-03-01', 'Odli?no', 0, 2),
(15, 'opt06', '2016-03-01', 'Odli?no', 0, 2),
(16, 'mat101', '2016-03-01', 'Odli?no', 0, 3),
(17, 'mat102', '2016-03-01', 'Odli?no', 0, 3),
(18, 'mat103', '2016-03-01', 'Odli?no', 0, 3),
(19, 'mat104', '2016-03-01', 'Odli?no', 0, 3),
(20, 'mat105', '2016-03-01', 'Odli?no', 0, 3),
(21, 'mat106', '2016-03-01', 'Odli?no', 0, 3),
(22, 'mat201', '2016-03-01', 'Odli?no', 2, 4),
(23, 'mat202', '2016-03-01', 'Odli?no', 0, 4),
(24, 'mat203', '2016-03-01', 'Odli?no', 0, 4),
(25, 'mat204', '2016-03-01', 'Odli?no', 0, 4),
(26, 'mat205', '2016-03-01', 'Odli?no', 0, 4),
(30, 'mat1z01', '2016-03-01', 'Odli?no', 0, 6),
(31, 'mat1z02', '2016-03-01', 'Odli?no', 0, 6),
(32, 'mat1z03', '2016-03-01', 'Odli?no', 0, 6),
(33, 'mat1z04', '2016-03-01', 'Odli?no', 0, 6),
(34, 'mat1z05', '2016-03-01', 'Odli?no', 0, 6),
(35, 'mat1z06', '2016-03-01', 'Odli?no', 0, 6),
(36, 'mat1z07', '2016-03-01', 'Odli?no', 0, 6),
(37, 'mat1z08', '2016-03-01', 'Odli?no', 0, 6),
(38, 'mat1z09', '2016-03-01', 'Odli?no', 0, 6),
(39, 'mat1z10', '2016-03-01', 'Odli?no', 0, 6),
(40, 'mat2z01', '2016-03-01', 'Odli?no', 0, 7),
(41, 'mat2z02', '2016-03-01', 'Odli?no', 0, 7),
(42, 'mat2z03', '2016-03-01', 'Odli?no', 0, 7),
(43, 'mat2z04', '2016-03-01', 'Odli?no', 0, 7),
(44, 'mat2z05', '2016-03-01', 'Odli?no', 0, 7),
(45, 'mat3z01', '2016-03-01', 'Odli?no', 0, 8),
(46, 'mat3z02', '2016-03-01', 'Odli?no', 0, 8),
(47, 'mat3z03', '2016-03-01', 'Odli?no', 0, 8),
(48, 'mat3z04', '2016-03-01', 'Odli?no', 0, 8),
(49, 'oe101', '2016-03-01', 'Odli?no', 0, 9),
(50, 'oe102', '2016-03-01', 'Odli?no', 0, 9),
(51, 'oe103', '2016-03-01', 'Odli?no', 0, 9),
(52, 'oe104', '2016-03-01', 'Odli?no', 0, 9),
(53, 'oe201', '2016-03-01', 'Odli?no', 0, 10),
(54, 'oe202', '2016-03-01', 'Odli?no', 0, 10),
(55, 'os01', '2016-03-01', 'Odli?no', 0, 13),
(56, 'os02', '2016-03-01', 'Odli?no', 0, 13),
(57, 'os03', '2016-03-01', 'Odli?no', 0, 13),
(58, 'os04', '2016-03-01', 'Odli?no', 0, 13),
(59, 'bp01', '2016-03-01', 'Odli?no', 0, 14),
(60, 'bp02', '2016-03-01', 'Odli?no', 0, 14),
(61, 'bp03', '2016-03-01', 'Odli?no', 0, 14),
(62, 'bp04', '2016-03-01', 'Odli?no', 0, 14),
(63, 'bp05', '2016-03-01', 'Odli?no', 0, 14);

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
(1, '2016-10-25', '2016-10-25', '2016-10-29', '2016-10-25', 1, 1, 1, -1),
(2, '2016-10-25', '2016-10-25', '2016-10-28', NULL, 1, 22, 1, 1);

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

--
-- Dumping data for table `RezervacijaPrimjerakNastavnik`
--

INSERT INTO `RezervacijaPrimjerakNastavnik` (`sifRezPrimNast`, `sifRezervacija`, `sifPrimjerak`, `sifNastavnik`) VALUES
(1, 1, 1, 1),
(2, 2, 22, 1);

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
(1, 'Dino', 'Zecevic', 'dino', 0, 'dino', 0, 0, 1),
(2, 'Mizbah', 'Alic', 'mizbah', 0, 'mizbah', 0, 0, 1),
(3, 'Mirza', 'Omerkic', 'mirza', 0, 'mirza', 0, 0, 1),
(4, 'Sinisa', 'Suljkic', 'sinisa', 0, 'sinisa', 0, 0, 1),
(5, 'Suad', 'Suljic', 'suad', 0, 'suad', 0, 0, 1),
(6, 'Jasmin', 'Zigic', 'jasmin', 0, 'jasmin', 0, 0, 1),
(7, 'Alen', 'Basic', 'alen', 0, 'alen', 0, 0, 1),
(8, 'Zlatan', 'Zecevic', 'zlatan', 0, 'zlatan', 0, 0, 1),
(9, 'Akbar', 'Zecevic', 'akbar', 0, 'akbar', 0, 0, 1);

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
  MODIFY `sifAutor` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `autorRBr`
--
ALTER TABLE `autorRBr`
  MODIFY `sifAutorRBr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `izdavac`
--
ALTER TABLE `izdavac`
  MODIFY `sifIzdavac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `sifKnjiga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `KnjigaAutorRBr`
--
ALTER TABLE `KnjigaAutorRBr`
  MODIFY `sifKnjigaAutorRBr` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `KnjigaPredmetObaveznost`
--
ALTER TABLE `KnjigaPredmetObaveznost`
  MODIFY `sifKnjPredObav` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `nastavnik`
--
ALTER TABLE `nastavnik`
  MODIFY `sifNastavnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `obaveznost`
--
ALTER TABLE `obaveznost`
  MODIFY `sifObaveznost` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `predmet`
--
ALTER TABLE `predmet`
  MODIFY `sifPredmet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `primjerak`
--
ALTER TABLE `primjerak`
  MODIFY `sifPrimjerak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `sifRezervacija` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `RezervacijaPrimjerakNastavnik`
--
ALTER TABLE `RezervacijaPrimjerakNastavnik`
  MODIFY `sifRezPrimNast` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `RezervacijaPrimjerakStudent`
--
ALTER TABLE `RezervacijaPrimjerakStudent`
  MODIFY `sifRezPrimStud` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `semestar`
--
ALTER TABLE `semestar`
  MODIFY `sifSemestar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `sifStudent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
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
