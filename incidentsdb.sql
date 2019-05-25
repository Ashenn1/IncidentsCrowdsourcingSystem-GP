-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 25, 2019 at 10:47 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `incidentsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `area`
--

CREATE TABLE `area` (
  `AreaId` int(11) NOT NULL,
  `Area_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `DepartmentId` int(11) NOT NULL,
  `Department_name` varchar(100) NOT NULL,
  `Photo` blob NOT NULL,
  `Authorization_username` varchar(70) NOT NULL,
  `Authorization_password` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `extra_incident_photos`
--

CREATE TABLE `extra_incident_photos` (
  `IncidentId` int(11) NOT NULL,
  `Incident_photo` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `incidents`
--

CREATE TABLE `incidents` (
  `IncidentId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Incident_name` varchar(100) NOT NULL,
  `Description` text,
  `Category` varchar(50) NOT NULL,
  `Severity` enum('Urgent','High','Normal','Low') NOT NULL,
  `Incident_datetime` datetime NOT NULL,
  `Longitude` double NOT NULL,
  `Latitude` double NOT NULL,
  `Incident_photo` blob,
  `Number_of_upvotes` int(11) NOT NULL,
  `Number_of_downvotes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `incident_warnings`
--

CREATE TABLE `incident_warnings` (
  `IncidentWarningId` int(11) NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  `IncidentDetails` text NOT NULL,
  `Category` varchar(70) NOT NULL,
  `Incident_datetime` datetime NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Longitude` double NOT NULL,
  `Latitiude` double NOT NULL,
  `Severity` enum('Urgent','High','Normal','Low') NOT NULL,
  `Incident_warning_headline` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `NotificationId` int(11) NOT NULL,
  `Type` varchar(70) NOT NULL,
  `Notifcation_message` text NOT NULL,
  `Notification_datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `organization_in_area`
--

CREATE TABLE `organization_in_area` (
  `OrganizationId` int(11) NOT NULL,
  `Organization_name` varchar(50) NOT NULL,
  `Organization_Description` text NOT NULL,
  `Help_category` varchar(100) NOT NULL,
  `Organization_area` varchar(100) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Organization_longitude` double NOT NULL,
  `Organization_latitiude` double NOT NULL,
  `Organization_email` varchar(100) NOT NULL,
  `Organization_phone_contact` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `User_photo` blob NOT NULL,
  `Home_address` text NOT NULL,
  `Longitude` double NOT NULL,
  `Latitude` double NOT NULL,
  `Rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_follow_area`
--

CREATE TABLE `user_follow_area` (
  `UserId` int(11) NOT NULL,
  `AreaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_follow_incident`
--

CREATE TABLE `user_follow_incident` (
  `UserId` int(11) NOT NULL,
  `IncidentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_notifications`
--

CREATE TABLE `user_notifications` (
  `UserId` int(11) NOT NULL,
  `NotificationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `voted_incidents`
--

CREATE TABLE `voted_incidents` (
  `UserId` int(11) NOT NULL,
  `IncidentId` int(11) NOT NULL,
  `Vote` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`AreaId`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DepartmentId`);

--
-- Indexes for table `extra_incident_photos`
--
ALTER TABLE `extra_incident_photos`
  ADD PRIMARY KEY (`IncidentId`);

--
-- Indexes for table `incidents`
--
ALTER TABLE `incidents`
  ADD PRIMARY KEY (`IncidentId`);

--
-- Indexes for table `incident_warnings`
--
ALTER TABLE `incident_warnings`
  ADD PRIMARY KEY (`IncidentWarningId`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`NotificationId`);

--
-- Indexes for table `organization_in_area`
--
ALTER TABLE `organization_in_area`
  ADD PRIMARY KEY (`OrganizationId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`),
  ADD UNIQUE KEY `Password` (`Password`);

--
-- Indexes for table `user_follow_area`
--
ALTER TABLE `user_follow_area`
  ADD PRIMARY KEY (`UserId`,`AreaId`);

--
-- Indexes for table `user_follow_incident`
--
ALTER TABLE `user_follow_incident`
  ADD PRIMARY KEY (`UserId`,`IncidentId`);

--
-- Indexes for table `user_notifications`
--
ALTER TABLE `user_notifications`
  ADD PRIMARY KEY (`UserId`,`NotificationId`);

--
-- Indexes for table `voted_incidents`
--
ALTER TABLE `voted_incidents`
  ADD PRIMARY KEY (`UserId`,`IncidentId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `area`
--
ALTER TABLE `area`
  MODIFY `AreaId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `DepartmentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `incidents`
--
ALTER TABLE `incidents`
  MODIFY `IncidentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `incident_warnings`
--
ALTER TABLE `incident_warnings`
  MODIFY `IncidentWarningId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `NotificationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `organization_in_area`
--
ALTER TABLE `organization_in_area`
  MODIFY `OrganizationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
