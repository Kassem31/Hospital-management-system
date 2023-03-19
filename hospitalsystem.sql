-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2022 at 06:13 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitalsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `PatientId` int(20) NOT NULL,
  `DoctorId` int(20) NOT NULL,
  `Date` date NOT NULL,
  `Time` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`PatientId`, `DoctorId`, `Date`, `Time`) VALUES
(1, 4, '2022-05-30', '14:00:00.000000'),
(3, 1, '2022-06-01', '14:00:00.000000'),
(4, 1, '2022-06-01', '15:00:00.000000'),
(6, 1, '2022-06-02', '17:00:00.000000'),
(2, 1, '2022-06-04', '14:00:00.000000'),
(3, 2, '2022-06-04', '15:00:00.000000'),
(5, 2, '2022-06-03', '16:00:00.000000'),
(2, 3, '2022-06-04', '16:00:00.000000'),
(3, 4, '2022-06-03', '18:00:00.000000'),
(4, 4, '2022-06-03', '19:00:00.000000'),
(5, 4, '2022-06-04', '19:00:00.000000'),
(6, 5, '2022-06-06', '15:00:00.000000'),
(4, 6, '2022-06-10', '16:00:00.000000'),
(1, 7, '2022-06-07', '14:00:00.000000'),
(3, 8, '2022-06-07', '14:00:00.000000'),
(3, 11, '2022-06-05', '17:00:00.000000'),
(6, 12, '2022-06-09', '13:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Id` int(20) NOT NULL,
  `Name` varchar(256) NOT NULL,
  `Password` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `department` varchar(256) NOT NULL,
  `salary` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Id`, `Name`, `Password`, `email`, `department`, `salary`) VALUES
(1, 'Steven', '123456', 'steven@gmail.com', 'Surgery', '500'),
(2, 'Mark', '02468', 'mark@gmail.com', 'Surgery', '400'),
(3, 'Tom', 'tomistom', 'tom@yahoo.com', 'Surgery', '450'),
(4, 'Jack', '124578', 'jack@gmail.com', 'Ear,Nose and Throat', '150'),
(5, 'Paul', 'paulthedoctor', 'paul@gmail.com', 'Ear,Nose and Throat', '200'),
(6, 'Larry', 'secretpassword', 'larry@yahoo.com', 'Nutrition and Dietetics', '100'),
(7, 'Peter', 'confidential', 'peter@gmail.com', 'Neurology', '550'),
(8, 'Miles', 'accessdenied', 'miles@gmail.com', 'Neurology', '600'),
(9, 'Sarah', 'elemental', 'sarah@gmail.com', 'Neurology', '700'),
(10, 'Sheldon', 'hiddenpassword', 'sheldon@gmail.com', 'Cardiology', '950'),
(11, 'Maya', 'forgotmypassword', 'maya@gmail.com', 'Cardiology', '900'),
(12, 'William', 'healthislife', 'william@gmail.com', 'Renal Unit', '800');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `Name` varchar(256) NOT NULL,
  `Password` varchar(256) NOT NULL,
  `ID` int(20) NOT NULL,
  `email` varchar(256) NOT NULL,
  `Age` int(20) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `Phonenumber` varchar(256) NOT NULL,
  `Address` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`Name`, `Password`, `ID`, `email`, `Age`, `gender`, `Phonenumber`, `Address`) VALUES
('Ralph', 'Ralph1234', 1, 'ralph@gmail.com', 28, 1, '01120304050', '3773 Richland Avenue'),
('Michael', 'guessnot', 2, 'mikey@gmail.com', 24, 1, '01044337621', '3775 Briarwood Drive'),
('David', 'keyaccess', 3, 'david@gmail.com', 39, 1, '01278991345', '4425 Augusta Park'),
('Selena', 'catwoman', 4, 'selena@yahoo.com', 23, 0, '01122334950', '2309 Aaron Smith Drive'),
('Laurel', 'fionapass', 5, 'laurel@yahoo.com', 31, 0, '01050492236', '85 Jones Street'),
('Simon', 'greatpassword', 6, 'simon@yahoo.com', 60, 1, '01258794435', '3700 Whitman Court');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
