-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 27, 2019 at 11:54 PM
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
(1, 'mjollye0', 'msouthon0@flickr.com', 'JKqF9Aug2K5', '', '4 Mohammed Abd El-Wahab Omar Al Khayam Zamalek Giza Governorate', 31.2242735, 30.0536882, 0),
(2, 'eburgen1', 'vsellar1@simplemachines.org', '6Zq1fw', '', '24 Al Gezera Omar Al Khayam Zamalek Giza Governorate', 31.2177074, 30.0537111, 0),
(3, 'oantognetti2', 'cgutherson2@wikimedia.org', '6iBZz2Wj', '', '3 Meshel Lotf Allah Omar Al Khayam Zamalek Cairo Governorate', 31.2217122, 30.0582578, 0),
(4, 'mreignould3', 'cshepperd3@youtu.be', 'S3uZyRbXE', '', '6 Kamal Al Tawil Mohammed Mazhar Zamalek Giza Governorate', 31.2202485, 30.0694507, 0),
(5, 'ktaks4', 'bhardwidge4@woothemes.com', 'vkbLuOQ', '', '20 Kamal Al Tawil Mohammed Mazhar Zamalek Giza Governorate', 31.2203854, 30.0713422, 0),
(6, 'msalmond5', 'sruselin5@nymag.com', 'Pp5QScHHs1', '', '35 Al Gazira Al WSTA Mohammed Mazhar Zamalek Giza Governorate', 31.2182806, 30.0712678, 0),
(7, 'lsaddington6', 'wbanbury6@mysql.com', '2gwPKp', '', '19 Abou Al Fada Abu Al Feda Zamalek Giza Governorate', 31.2145237, 30.0672182, 0),
(8, 'hoglethorpe7', 'fsebire7@senate.gov', 'kZvedNNH5JlZ', '', '3 Bahgat Ali Abu Al Feda Zamalek Giza Governorate', 31.214932, 30.0654888, 0),
(9, 'kboame8', 'bfancourt8@bigcartel.com', 'LRzGjGPxV', '', '23 Ibn Zanikai Al Gabalayah Zamalek Giza Governorate', 31.2146803, 30.0610634, 0),
(10, 'ckillen9', 'etonnesen9@nps.gov', 'Tlfu0agH', '', '4 Shagaret Al Dor Al Gabalayah Zamalek Giza Governorate', 31.2152393, 30.0595556, 0),
(11, 'bmcgaugana', 'twombwella@phoca.cz', 'p07wP3W2m6o', '', '2 Al Kamel Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2164644, 30.0578133, 0),
(12, 'kbedboroughb', 'emassenhoveb@unesco.org', '0PVPjBqLR2', '', 'Bahaa El-Deen Qaraqosh Al Gabalayah Zamalek Giza Governorate', 31.2175864, 30.0569278, 0),
(13, 'tfenningc', 'rvassiec@patch.com', 'gtXntpzW', '', '14 Hassan Sabry St Omar Al Khayam Zamalek Giza Governorate', 31.2182534, 30.057587, 0),
(14, 'obrattelld', 'jfillsd@fema.gov', 'rh6Tu6gCC8pe', '', '6 Al Kamel Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2172577, 30.0590799, 0),
(15, 'mwhisbye', 'cthieme@microsoft.com', 'UiCmJKGOI', '', '6 Salah El-Deen Al Gabalayah Zamalek Giza Governorate', 31.2173848, 30.058354, 0),
(16, 'fharmesf', 'dkullerf@jigsy.com', 'nJIRuBx', '', '12 Mohammed Abd El-Wahab Omar Al Khayam Zamalek Giza Governorate', 31.2240052, 30.0544086, 0),
(17, 'hcustyg', 'zgeertseng@edublogs.org', 'JM6cgXC0', '', '12 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2202932, 30.0563997, 0),
(18, 'imattecoth', 'bsmurfitth@xrea.com', '9Ju5a4TXGcp', '', '10 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2199801, 30.0564413, 0),
(19, 'jgrossi', 'alowdhami@chronoengine.com', 'NfyhKJjv', '', '3 Al Shahid Eshak Yagoob Omar Al Khayam Zamalek Giza Governorate', 31.2213975, 30.0585885, 0),
(20, 'mshrubsallj', 'kbillettj@yellowpages.com', 'CXFzdag', '', 'Yehia Ibrahim Mohammed Mazhar Zamalek Cairo Governorate', 31.2211922, 30.0605585, 0),
(21, 'mslayk', 'wmacafeek@ucla.edu', 'Fg1Z7TJ', '', '3 Brazil St Mohammed Mazhar Zamalek Cairo Governorate', 31.2202237, 30.060679, 0),
(22, 'edewil', 'bivashovl@prweb.com', 'obakHZa', '', '6 Ahmed Sabry Mohammed Mazhar Zamalek Giza Governorate', 31.2190856, 30.0616323, 0),
(23, 'xbrierleym', 'estrutem@prweb.com', 'GFYcqKz', '', '14 El-Saleh Ayoub Al Gabalayah Zamalek Giza Governorate', 31.217006, 30.0607367, 0),
(24, 'nbrowettn', 'rpremblen@sbwire.com', 'zRm0IdNM5npV', '', '11 Shagaret Al Dor Al Gabalayah Zamalek Giza Governorate', 31.2164916, 30.0609327, 0),
(25, 'ucorradettio', 'akibbelo@macromedia.com', 'afOZzGpm', '', '8 El-Mansour Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2151499, 30.061204, 0),
(26, 'iyurinp', 'mluetkemeyerp@slideshare.net', 'YwKavaGfs8f9', '', '22 Ibn Zanikai Al Gabalayah Zamalek Giza Governorate', 31.2147474, 30.0606192, 0),
(27, 'glinnitq', 'kjoseffq@facebook.com', '29sq8gvQTIed', '', '23 Salah El-Deen Al Gabalayah Zamalek Giza Governorate', 31.214971, 30.0597074, 0),
(28, 'truzicr', 'awitardr@census.gov', 'CD95TvPMQg', '', '5 Bahaa El-Deen Qaraqosh Al Gabalayah Zamalek Giza Governorate', 31.2169486, 30.0573756, 0),
(29, 'dsmithens', 'estutelys@yale.edu', 'XS8jUL', '', '2 El-Aziz Othman Al Gabalayah Zamalek Giza Governorate', 31.2175042, 30.0576147, 0),
(30, 'drenfieldt', 'pbowyert@quantcast.com', 'LfekFuKMENs', '', '14 Al Sheikh Al Marsafi Omar Al Khayam Zamalek Giza Governorate', 31.2205168, 30.0564628, 0),
(31, 'awignallu', 'dbalwinu@samsung.com', 'LdmMosY8eu', '', '4 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2202866, 30.0565991, 0),
(32, 'tadessv', 'gezzellv@tripadvisor.com', 'D2zRo2HNPL', '', '3 Mahmoud Azmy Omar Al Khayam Zamalek Giza Governorate', 31.220159, 30.0573692, 0),
(33, 'ykilnerw', 'bspranklingw@hubpages.com', 'Lrrictl8y8qo', '', '4 Omarat Aliamni Omar Al Khayam Zamalek Cairo Governorate', 31.2217006, 30.0577369, 0),
(34, 'hhumbeyx', 'rlafrentzx@harvard.edu', 'Ilb5UDw9zK', '', 'Zaki Ali Omar Al Khayam Zamalek Giza Governorate', 31.2222278, 30.0588963, 0),
(35, 'vbrimfieldy', 'aabbayy@economist.com', 'aOEnaK', '', 'Aziz Abaza Mohammed Mazhar Zamalek Giza Governorate', 31.2234841, 30.0596489, 0),
(36, 'rdonovanz', 'jfluinz@si.edu', 'L2huTKMZAD', '', '300 Al Haram, At Talbeyah Al Qebleyah, Al Omraneyah, Giza Governorate', 31.1735283, 29.9994864, 0),
(37, 'meves10', 'ipollak10@facebook.com', 'uUsLk9A', '', '432 King Faisal St Oula Al Haram Al Omraneyah Giza Governorate', 31.1741211, 30.000581, 0),
(38, 'barnald11', 'emiebes11@hp.com', 'pNDRni', '', '6 El-Salam Al Omraneyah Giza Governorate', 31.1750572, 29.9996383, 0),
(39, 'ogarett12', 'fhess12@hhs.gov', 'eDOWiIVTD94J', '', 'Hamida Tag Al Din At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1783203, 30.000384, 0),
(40, 'smattosoff13', 'hpurkiss13@mac.com', '7E4ZP6bYmWIT', '', 'Taha Hussein Al Omraneyah Al Gharbeyah Al Omraneyah Giza Governorate', 31.1827977, 30.0039762, 0),
(41, 'jmaccari14', 'kgergher14@epa.gov', '8EVqMQmC0Mfm', '', 'Ali El-Refaey Al Omraneyah Giza Governorate', 31.1785975, 30.0013924, 0),
(42, 'kklishin15', 'tdann15@elegantthemes.com', '8i2XHZev7Byq', '', 'Farid El Sebaay Oula Al Haram Al Omraneyah Giza Governorate', 31.1768245, 30.0013131, 0),
(43, 'mlivett16', 'lpeyes16@businessweek.com', 'oDxUaSPjx', '', 'Shaaban Shalaby Al Omraneyah Giza Governorate', 31.1760883, 29.99999, 0),
(44, 'rhancke17', 'rfend17@umich.edu', 'HahKQF', '', 'Fatma Khalifa Al Omraneyah Giza Governorate', 31.1733703, 29.9992663, 0),
(45, 'icaverhill18', 'skalberer18@globo.com', '4yojoWzwdQY', '', 'Ayoub At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1726615, 29.9985702, 0),
(46, 'lsloat19', 'cmudle19@imdb.com', 'n631hM3', '', 'Soliman El-Banna At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1708283, 29.997642, 0),
(47, 'cmelly1a', 'cwitsey1a@wix.com', 'GvgDZr', '', 'Amin Abd El-Hameed Oula Al Haram Al Omraneyah Giza Governorate', 31.1677094, 29.9980969, 0),
(48, 'wgorner1b', 'ibrownlie1b@altervista.org', 'Z3Uy4TfH', '', 'Khaled Amin Oula Al Haram Al Omraneyah Giza Governorate', 31.1642116, 29.9988085, 0),
(49, 'rcadamy1c', 'dchattell1c@woothemes.com', 'J3n2qA', '', 'El-Taawon Oula Al Haram Al Omraneyah Giza Governorate', 31.1623418, 29.9989283, 0),
(50, 'bmuffitt1d', 'fmacalaster1d@networksolutions', 'b48JdI69', '', 'El-Omda Ahmed Abd El-Aal Oula Al Haram Al Omraneyah Giza Governorate', 31.1616643, 29.9994255, 0),
(51, 'ehughs1e', 'avossing1e@indiegogo.com', 'JGgudku6v', '', 'Khater Oula Al Haram Al Omraneyah Giza Governorate', 31.1618324, 29.9994846, 0),
(52, 'utotterdill1f', 'kheardman1f@wired.com', 'CIlDVzaz5v', '', 'El-Mansheya El-Gadid St Monshaat Al Bakari Al Haram Giza Governorate', 31.160006, 30.0000839, 0),
(53, 'lelegood1g', 'mcuttles1g@princeton.edu', 'rb85qefUcN', '', 'King Faisal St Monshaat Al Bakari Al Haram Giza Governorate', 31.1598227, 29.9993483, 0),
(54, 'rlargent1h', 'kweepers1h@bravesites.com', 'uZEEmM', '', 'Shams Ibrahim Soliman Monshaat Al Bakari Al Haram Giza Governorate', 31.1601805, 30.0007087, 0),
(55, 'gbeauly1i', 'bboissier1i@howstuffworks.com', 'WTmfkh3', '', 'El-Mansheya El-Gadid St Monshaat Al Bakari Al Haram Giza Governorate', 31.1592144, 30.0020584, 0),
(56, 'cbenaine1j', 'gvannozzii1j@upenn.edu', '7V2QCp', '', 'El-Omda Ahmed Abd El-Aal Oula Al Haram Al Omraneyah Giza Governorate', 31.1616643, 29.9994255, 0),
(57, 'hpacquet1k', 'glumly1k@nifty.com', 'pmaIYYwEmRgy', '', 'El-Khaleel Ibrahim Oula Al Haram Al Omraneyah Giza Governorate', 31.1673514, 29.9998155, 0),
(58, 'dechelle1l', 'fdotson1l@unblog.fr', 'bgWr7a6fO13', '', 'Ahmed Abd El-Mohsen Oula Al Haram Al Omraneyah Giza Governorate', 31.1596951, 29.9962236, 0),
(59, 'dheak1m', 'lbellis1m@360.cn', 'IBPst2W', '', 'Mohammed El-Mahdi Oula Al Haram Al Omraneyah Giza Governorate', 31.1582816, 29.9961746, 0),
(60, 'mmar1n', 'mskate1n@ca.gov', 'QQbqLA', '', 'Ramseis Al Sani Oula Al Haram Al Omraneyah Giza Governorate', 31.156267, 29.9940482, 0),
(61, 'wbatrick1o', 'sworks1o@hao123.com', 'jNMJb8pAv', '', '132 Al Haram Oula Al Haram Al Omraneyah Giza Governorate', 31.1546707, 29.992887, 0),
(62, 'gdavidge1p', 'obottjer1p@nymag.com', 'P2jAtEm', '', 'Nemt Nasr Allah Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1546108, 29.9920694, 0),
(63, 'zpawelek1q', 'shampstead1q@cnbc.com', 'kKXbCh', '', 'Aboi Gazia Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1578353, 29.99213, 0),
(64, 'nyaakov1r', 'sbosket1r@craigslist.org', '5GQbPS', '', 'El-Omda Oula Al Haram Al Omraneyah Giza Governorate', 31.150708, 29.9916669, 0),
(65, 'hambage1s', 'sborley1s@canalblog.com', 'vh3Bzg6c', '', 'Hasan Samorah Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.15137, 29.9908798, 0),
(66, 'aouver1t', 'rbreckell1t@hostgator.com', 'scLF0ibhFhF', '', 'Ragab Abd El-Hameed Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1531914, 29.9889701, 0),
(67, 'hlarman1u', 'wcottu1u@pagesperso-orange.fr', 'j315h1K', '', 'El-Omda El-Kadeem Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1552165, 29.9880551, 0),
(68, 'tjaskowicz1v', 'strebilcock1v@usgs.gov', 'yfpJLZ', '', 'Ajiad Makkah Kafr Nassar Al Haram Giza Governorate', 31.1350563, 30.0004029, 0),
(69, 'espellar1w', 'aoconnel1w@ucla.edu', 'cPYP1LaWqBh', '', 'Mohammed Ateya Kafr Nassar Al Haram Giza Governorate', 31.1346351, 29.9988617, 0),
(70, 'agirt1x', 'bmacklam1x@vkontakte.ru', 'FCOgFJiT', '', 'El-Iman Kafr Nassar Al Haram Giza Governorate', 31.1344996, 29.9984566, 0);

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
