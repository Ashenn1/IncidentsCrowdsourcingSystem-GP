-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 27, 2019 at 09:58 PM
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
  `AreaId` int(11) NOT NULL,
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

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `Username`, `Email`, `Password`, `User_photo`, `Home_address`, `Longitude`, `Latitude`, `Rating`) VALUES
(1, 'mjollye0', 'msouthon0@flickr.com', 'JKqF9Aug2K5', '', '', 0, 0, 0),
(2, 'eburgen1', 'vsellar1@simplemachines.org', '6Zq1fw', '', '', 0, 0, 0),
(3, 'oantognetti2', 'cgutherson2@wikimedia.org', '6iBZz2Wj', '', '', 0, 0, 0),
(4, 'mreignould3', 'cshepperd3@youtu.be', 'S3uZyRbXE', '', '', 0, 0, 0),
(5, 'ktaks4', 'bhardwidge4@woothemes.com', 'vkbLuOQ', '', '', 0, 0, 0),
(6, 'msalmond5', 'sruselin5@nymag.com', 'Pp5QScHHs1', '', '', 0, 0, 0),
(7, 'lsaddington6', 'wbanbury6@mysql.com', '2gwPKp', '', '', 0, 0, 0),
(8, 'hoglethorpe7', 'fsebire7@senate.gov', 'kZvedNNH5JlZ', '', '', 0, 0, 0),
(9, 'kboame8', 'bfancourt8@bigcartel.com', 'LRzGjGPxV', '', '', 0, 0, 0),
(10, 'ckillen9', 'etonnesen9@nps.gov', 'Tlfu0agH', '', '', 0, 0, 0),
(11, 'bmcgaugana', 'twombwella@phoca.cz', 'p07wP3W2m6o', '', '', 0, 0, 0),
(12, 'kbedboroughb', 'emassenhoveb@unesco.org', '0PVPjBqLR2', '', '', 0, 0, 0),
(13, 'tfenningc', 'rvassiec@patch.com', 'gtXntpzW', '', '', 0, 0, 0),
(14, 'obrattelld', 'jfillsd@fema.gov', 'rh6Tu6gCC8pe', '', '', 0, 0, 0),
(15, 'mwhisbye', 'cthieme@microsoft.com', 'UiCmJKGOI', '', '', 0, 0, 0),
(16, 'fharmesf', 'dkullerf@jigsy.com', 'nJIRuBx', '', '', 0, 0, 0),
(17, 'hcustyg', 'zgeertseng@edublogs.org', 'JM6cgXC0', '', '', 0, 0, 0),
(18, 'imattecoth', 'bsmurfitth@xrea.com', '9Ju5a4TXGcp', '', '', 0, 0, 0),
(19, 'jgrossi', 'alowdhami@chronoengine.com', 'NfyhKJjv', '', '', 0, 0, 0),
(20, 'mshrubsallj', 'kbillettj@yellowpages.com', 'CXFzdag', '', '', 0, 0, 0),
(21, 'mslayk', 'wmacafeek@ucla.edu', 'Fg1Z7TJ', '', '', 0, 0, 0),
(22, 'edewil', 'bivashovl@prweb.com', 'obakHZa', '', '', 0, 0, 0),
(23, 'xbrierleym', 'estrutem@prweb.com', 'GFYcqKz', '', '', 0, 0, 0),
(24, 'nbrowettn', 'rpremblen@sbwire.com', 'zRm0IdNM5npV', '', '', 0, 0, 0),
(25, 'ucorradettio', 'akibbelo@macromedia.com', 'afOZzGpm', '', '', 0, 0, 0),
(26, 'iyurinp', 'mluetkemeyerp@slideshare.net', 'YwKavaGfs8f9', '', '', 0, 0, 0),
(27, 'glinnitq', 'kjoseffq@facebook.com', '29sq8gvQTIed', '', '', 0, 0, 0),
(28, 'truzicr', 'awitardr@census.gov', 'CD95TvPMQg', '', '', 0, 0, 0),
(29, 'dsmithens', 'estutelys@yale.edu', 'XS8jUL', '', '', 0, 0, 0),
(30, 'drenfieldt', 'pbowyert@quantcast.com', 'LfekFuKMENs', '', '', 0, 0, 0),
(31, 'awignallu', 'dbalwinu@samsung.com', 'LdmMosY8eu', '', '', 0, 0, 0),
(32, 'tadessv', 'gezzellv@tripadvisor.com', 'D2zRo2HNPL', '', '', 0, 0, 0),
(33, 'ykilnerw', 'bspranklingw@hubpages.com', 'Lrrictl8y8qo', '', '', 0, 0, 0),
(34, 'hhumbeyx', 'rlafrentzx@harvard.edu', 'Ilb5UDw9zK', '', '', 0, 0, 0),
(35, 'vbrimfieldy', 'aabbayy@economist.com', 'aOEnaK', '', '', 0, 0, 0),
(36, 'rdonovanz', 'jfluinz@si.edu', 'L2huTKMZAD', '', '', 0, 0, 0),
(37, 'meves10', 'ipollak10@facebook.com', 'uUsLk9A', '', '', 0, 0, 0),
(38, 'barnald11', 'emiebes11@hp.com', 'pNDRni', '', '', 0, 0, 0),
(39, 'ogarett12', 'fhess12@hhs.gov', 'eDOWiIVTD94J', '', '', 0, 0, 0),
(40, 'smattosoff13', 'hpurkiss13@mac.com', '7E4ZP6bYmWIT', '', '', 0, 0, 0),
(41, 'jmaccari14', 'kgergher14@epa.gov', '8EVqMQmC0Mfm', '', '', 0, 0, 0),
(42, 'kklishin15', 'tdann15@elegantthemes.com', '8i2XHZev7Byq', '', '', 0, 0, 0),
(43, 'mlivett16', 'lpeyes16@businessweek.com', 'oDxUaSPjx', '', '', 0, 0, 0),
(44, 'rhancke17', 'rfend17@umich.edu', 'HahKQF', '', '', 0, 0, 0),
(45, 'icaverhill18', 'skalberer18@globo.com', '4yojoWzwdQY', '', '', 0, 0, 0),
(46, 'lsloat19', 'cmudle19@imdb.com', 'n631hM3', '', '', 0, 0, 0),
(47, 'cmelly1a', 'cwitsey1a@wix.com', 'GvgDZr', '', '', 0, 0, 0),
(48, 'wgorner1b', 'ibrownlie1b@altervista.org', 'Z3Uy4TfH', '', '', 0, 0, 0),
(49, 'rcadamy1c', 'dchattell1c@woothemes.com', 'J3n2qA', '', '', 0, 0, 0),
(50, 'bmuffitt1d', 'fmacalaster1d@networksolutions', 'b48JdI69', '', '', 0, 0, 0),
(51, 'ehughs1e', 'avossing1e@indiegogo.com', 'JGgudku6v', '', '', 0, 0, 0),
(52, 'utotterdill1f', 'kheardman1f@wired.com', 'CIlDVzaz5v', '', '', 0, 0, 0),
(53, 'lelegood1g', 'mcuttles1g@princeton.edu', 'rb85qefUcN', '', '', 0, 0, 0),
(54, 'rlargent1h', 'kweepers1h@bravesites.com', 'uZEEmM', '', '', 0, 0, 0),
(55, 'gbeauly1i', 'bboissier1i@howstuffworks.com', 'WTmfkh3', '', '', 0, 0, 0),
(56, 'cbenaine1j', 'gvannozzii1j@upenn.edu', '7V2QCp', '', '', 0, 0, 0),
(57, 'hpacquet1k', 'glumly1k@nifty.com', 'pmaIYYwEmRgy', '', '', 0, 0, 0),
(58, 'dechelle1l', 'fdotson1l@unblog.fr', 'bgWr7a6fO13', '', '', 0, 0, 0),
(59, 'dheak1m', 'lbellis1m@360.cn', 'IBPst2W', '', '', 0, 0, 0),
(60, 'mmar1n', 'mskate1n@ca.gov', 'QQbqLA', '', '', 0, 0, 0),
(61, 'wbatrick1o', 'sworks1o@hao123.com', 'jNMJb8pAv', '', '', 0, 0, 0),
(62, 'gdavidge1p', 'obottjer1p@nymag.com', 'P2jAtEm', '', '', 0, 0, 0),
(63, 'zpawelek1q', 'shampstead1q@cnbc.com', 'kKXbCh', '', '', 0, 0, 0),
(64, 'nyaakov1r', 'sbosket1r@craigslist.org', '5GQbPS', '', '', 0, 0, 0),
(65, 'hambage1s', 'sborley1s@canalblog.com', 'vh3Bzg6c', '', '', 0, 0, 0),
(66, 'aouver1t', 'rbreckell1t@hostgator.com', 'scLF0ibhFhF', '', '', 0, 0, 0),
(67, 'hlarman1u', 'wcottu1u@pagesperso-orange.fr', 'j315h1K', '', '', 0, 0, 0),
(68, 'tjaskowicz1v', 'strebilcock1v@usgs.gov', 'yfpJLZ', '', '', 0, 0, 0),
(69, 'espellar1w', 'aoconnel1w@ucla.edu', 'cPYP1LaWqBh', '', '', 0, 0, 0),
(70, 'agirt1x', 'bmacklam1x@vkontakte.ru', 'FCOgFJiT', '', '', 0, 0, 0);

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
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
