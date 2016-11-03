-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2016 at 08:32 AM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `interaktivnikalendar`
--

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(10) NOT NULL,
  `title` varchar(40) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `dateAndTime` date NOT NULL,
  `weatherId` int(10) NOT NULL,
  `personId` int(10) NOT NULL,
  `eventTypeId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `eventarchive`
--

CREATE TABLE `eventarchive` (
  `id` int(10) NOT NULL,
  `title` varchar(20) NOT NULL,
  `dateAndTime` date NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `personId` int(10) NOT NULL,
  `eventTypeId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `eventtype`
--

CREATE TABLE `eventtype` (
  `id` int(10) NOT NULL,
  `description` varchar(20) NOT NULL,
  `color` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `eventtype`
--

INSERT INTO `eventtype` (`id`, `description`, `color`) VALUES
(100, 'Rodjendan', 'pink'),
(101, 'Sastanak', 'plava'),
(102, 'Izlazak', 'crvena');

-- --------------------------------------------------------

--
-- Table structure for table `mailtopersons`
--

CREATE TABLE `mailtopersons` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `eventId` int(10) NOT NULL,
  `archiveId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(10) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `weather`
--

CREATE TABLE `weather` (
  `id` int(10) NOT NULL,
  `date` date NOT NULL,
  `minTemperature` float NOT NULL,
  `maxTemperature` float NOT NULL,
  `description` varchar(50) NOT NULL,
  `icon` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weather`
--

INSERT INTO `weather` (`id`, `date`, `minTemperature`, `maxTemperature`, `description`, `icon`) VALUES
(10, '2016-10-25', 6, 17, 'Oblacno', 'xy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `weatherId` (`weatherId`),
  ADD KEY `personId` (`personId`),
  ADD KEY `eventTypeId` (`eventTypeId`);

--
-- Indexes for table `eventarchive`
--
ALTER TABLE `eventarchive`
  ADD PRIMARY KEY (`id`),
  ADD KEY `personId` (`personId`),
  ADD KEY `eventTypeId` (`eventTypeId`);

--
-- Indexes for table `eventtype`
--
ALTER TABLE `eventtype`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mailtopersons`
--
ALTER TABLE `mailtopersons`
  ADD PRIMARY KEY (`id`),
  ADD KEY `eventId` (`eventId`),
  ADD KEY `archiveId` (`archiveId`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `weather`
--
ALTER TABLE `weather`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `eventarchive`
--
ALTER TABLE `eventarchive`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `eventtype`
--
ALTER TABLE `eventtype`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;
--
-- AUTO_INCREMENT for table `mailtopersons`
--
ALTER TABLE `mailtopersons`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `weather`
--
ALTER TABLE `weather`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`weatherId`) REFERENCES `weather` (`id`),
  ADD CONSTRAINT `event_ibfk_2` FOREIGN KEY (`personId`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `event_ibfk_3` FOREIGN KEY (`eventTypeId`) REFERENCES `eventtype` (`id`);

--
-- Constraints for table `eventarchive`
--
ALTER TABLE `eventarchive`
  ADD CONSTRAINT `eventarchive_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `eventarchive_ibfk_2` FOREIGN KEY (`eventTypeId`) REFERENCES `eventtype` (`id`);

--
-- Constraints for table `mailtopersons`
--
ALTER TABLE `mailtopersons`
  ADD CONSTRAINT `mailtopersons_ibfk_1` FOREIGN KEY (`eventId`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `mailtopersons_ibfk_2` FOREIGN KEY (`archiveId`) REFERENCES `eventarchive` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
