-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 16, 2017 at 09:43 PM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `software`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Name` varchar(20) NOT NULL,
  `AdminID` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Name`, `AdminID`, `Username`, `password`) VALUES
('Abdo lotfy', 'abdo', 'abdo', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `Name` varchar(20) NOT NULL,
  `BrandID` varchar(20) NOT NULL,
  `counter` int(11) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`Name`, `BrandID`, `counter`, `type`) VALUES
('nike', 'nike', 0, 'original');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Name` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `CustomerID` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Name`, `Password`, `CustomerID`, `Username`) VALUES
('ffff fff', '3amar', '3amar', '3amar'),
('ali qwer', '12356', 'abdo', 'abdo'),
('Ali Mohammed', '1234', 'Ali', 'Ali');

-- --------------------------------------------------------

--
-- Table structure for table `has`
--

CREATE TABLE `has` (
  `StoreID` varchar(20) NOT NULL,
  `ProductID` varchar(20) NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `name` varchar(20) NOT NULL,
  `ProductID` varchar(20) NOT NULL,
  `sold` int(11) NOT NULL,
  `BrandID` varchar(20) NOT NULL,
  `Type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`name`, `ProductID`, `sold`, `BrandID`, `Type`) VALUES
('far7a1', 'far7a1', 0, 'nike', 'Suggested');

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `Name` varchar(20) NOT NULL,
  `Numberofbuyers` int(11) NOT NULL,
  `numberofvisitors` int(11) NOT NULL,
  `StoreID` varchar(20) NOT NULL,
  `StoreOwnerID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`Name`, `Numberofbuyers`, `numberofvisitors`, `StoreID`, `StoreOwnerID`) VALUES
('B-Tech', 0, 0, 'B-Tech', 'Test'),
('far7a', 0, 0, 'far7a', 'aliali');

-- --------------------------------------------------------

--
-- Table structure for table `storeowner`
--

CREATE TABLE `storeowner` (
  `Name` varchar(20) NOT NULL,
  `StoreOwnerID` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storeowner`
--

INSERT INTO `storeowner` (`Name`, `StoreOwnerID`, `Username`, `Password`) VALUES
('ali mohamed', 'aliali', 'aliali', '10061997'),
('test awe', 'storeowner1', 'storeowner1', 'root'),
('Ali Mohammed', 'Test', 'Test', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `vouchercard`
--

CREATE TABLE `vouchercard` (
  `Name` varchar(20) NOT NULL,
  `VoucherID` varchar(20) NOT NULL,
  `Value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminID`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`BrandID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `has`
--
ALTER TABLE `has`
  ADD KEY `StoreID` (`StoreID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `PrandID` (`BrandID`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`StoreID`),
  ADD KEY `StoreOwnerID` (`StoreOwnerID`);

--
-- Indexes for table `storeowner`
--
ALTER TABLE `storeowner`
  ADD PRIMARY KEY (`StoreOwnerID`);

--
-- Indexes for table `vouchercard`
--
ALTER TABLE `vouchercard`
  ADD PRIMARY KEY (`VoucherID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `has`
--
ALTER TABLE `has`
  ADD CONSTRAINT `has_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `has_ibfk_2` FOREIGN KEY (`StoreID`) REFERENCES `store` (`StoreID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`BrandID`) REFERENCES `brand` (`BrandID`);

--
-- Constraints for table `store`
--
ALTER TABLE `store`
  ADD CONSTRAINT `store_ibfk_1` FOREIGN KEY (`StoreOwnerID`) REFERENCES `storeowner` (`StoreOwnerID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
