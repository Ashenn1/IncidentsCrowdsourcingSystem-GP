-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 20, 2019 at 01:37 PM
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
  `Area_name` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `area`
--

INSERT INTO `area` (`AreaId`, `Area_name`) VALUES
(1, 'Zamalek'),
(2, 'Al Haram'),
(3, 'Al Omraneyah');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `DepartmentId` int(11) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Department_name` varchar(100) NOT NULL,
  `Department_type` varchar(100) NOT NULL,
  `Authorization_username` varchar(70) NOT NULL,
  `Authorization_password` varchar(70) NOT NULL,
  `Longitude` double NOT NULL,
  `Latitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  



INSERT INTO `department` (`DepartmentId`,`AreaId`, `Department_name`,`Department_type`, `Authorization_username`, 
  `Authorization_password`,`Longitude`,`Latitude`) VALUES
(1, 3, 'Al Haram Hospital','Hospital','Al-Haram-Hospital','qazxswe', 29.991146,31.150887),
(2, 1, 'Shagaret El Dor Hospital', 'Hospital','Shagaret-El-Dor-Hospital','WEJ33GA', 30.062993, 31.219095),
(3, 3, 'Imam Ali hospital charity','Hospital','Imam-Ali-hospital','sajbsde', 29.995174, 31.201739),

(4,3, 'Omraneya Police Station','Police Station','Omraneya-Police-Station','CnwVuegts', 30.004138,31.204976),
(5,2,'El Ahram Police Station','Police Station','Al Haram-Police-Station','Khsgddq1', 29.983624,31.133367),
(6,1, 'El Gezirah Police Station','Police Station','El-Gezirah-Police-Station','lmks7ywY', 30.055572,31.219641),

(7,2, 'Drinking Water and Sanitation Company','Water and Sewer','Water-Sanitation-Company','Qn2sqmgl', 30.114980,31.211010),
(8,2,'Water and Sanitation Company - Greater Cairo','Water and Sewer','Water-Greater-Cairo','CXFzaf3', 30.058153,31.243545),


(9,3,'veterinary clinic in Giza','Stray Dogs','veterinary-clinic-Giza','rh6Tu6', 30.009460,31.204027),
(10,1, 'El Zamalek Veterinary Clinic','Stray Dogs','Zamalek-Veterinary-Clinic','lmkq6ajs', 30.066607,31.216755),
(11,3,'Rakha Veterinary Clinic','Stray Dogs','Rakha-Veterinary-Clinic','pSjq3hd', 30.004273,31.199707),

(12,3, 'South Cairo Electricity Distribution Co. - El Haram 2','Power','Electricity-Distribution-Co','pcs3hd', 30.005195,31.190817),
(13,1,'South Cairo Electricity Distribution Co. - Zamalek','Power','Elec-Distribution-Zamalek','p18swhd', 30.059797,31.222409),

(14,1, 'Central Fire Department','Fire','Central-Fire-Department','CXa8WFz', 30.059082,31.221476),
(15,3,'Al omrania fire department','Fire','Alomraniafiredepartment','FzWOb20d', 29.999531,31.205326),
(16,3,'Giza Fire Station','Fire','Giza-Fire-Station','PE12PJyq', 30.015869,31.214489), 

(17,2, 'Gezira Traffic Offices','Traffic','Giza-Fire-Station','PE12PJyq', 30.069041,31.228261),
(18,2, 'Hadaeq El Ahram Traffic','Traffic','Hadaeq-ElAhram-Traffic','qsYfJ6Uq4', 30.006280,31.136006),
(19,2,'Faisal Traffic Police Station','Traffic','Faisal-Traffic-Police-Station','Ueaj7wgWn', 30.014646,31.204086);


-- Table structure for table `incident_warnings`
--

DROP TABLE IF EXISTS `incident_warnings`;


CREATE TABLE `incident_warnings` (
  `IncidentWarningId` int(11) NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  `IncidentDetails` text NOT NULL,
  `Category` varchar(70) NOT NULL,
  `Incident_datetime` datetime NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Severity` enum('Urgent','High','Normal','Low') NOT NULL,
  `Incident_warning_headline` varchar(70) NOT NULL,
  `Longitude` double NOT NULL,
  `Latitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `incident_warnings` (`IncidentWarningId`, `DepartmentId`,`IncidentDetails`, `Category`, `Incident_datetime`,`AreaId`,`Severity`
,`Incident_warning_headline`,`Longitude`,`Latitude`) VALUES
(1, 6,'series of theft incidents be worry','theft','2019-05-02 03:56:39', 1,'Urgent', 'Incident_warning_headline',29.991146,31.150987),
(2, 5,'Armed Robbery with high level of threat','Robbery','2019-04-11 13:56:39', 2,'Urgent', 'Incident_warning_headline',30.001124,31.172717),
(3, 4,'Dangerous road blockers','theft','2019-1-24 1:06:09',3, 'High', 'Incident_warning_headline',29.995233, 31.179290),
(4, 6,'Missing Person whose name is ..... and age is ........','Missing Person','2019-04-02 12:06:49', 1,'Normal', 'Incident_warning_headline', 30.055572,31.219641),
(5, 7,'Sewer Leakage','Sewer leackage','2019-03-14 15:56:39', 3,'Normal', 'Incident_warning_headline',29.980979, 31.168377),
(6, 8,'Water Outage','Water Outage','2019-01-29 11:50:09', 1,'Normal', 'Incident_warning_headline',30.042462, 31.225297),
(7, 9,'Stray Dogs are alot here be cautious','Stray Dogs','2019-03-4 22:55:00', 3,'High', 'Incident_warning_headline',30.003313, 31.184694),
(8, 13,'Power Outage in all area for 6 hours','Power Outage','2019-02-11 17:00:30', 1,'Normal', 'Incident_warning_headline',30.058467, 31.222071),
(9, 15,'Fire in a near building be aware in case of spread','Fire','2019-03-18 21:56:33', 3,'High', 'Incident_warning_headline',29.999560, 31.174012),
(10, 16,'Fire on a near building be aware in case of spread','Fire','2019-01-01 16:56:20', 2,'Urgent', 'Incident_warning_headline',30.000767, 31.169720),
(11, 17,'Traffic jam along the road','Road Problems','2019-04-02 15:55:09', 1,'Low', 'Incident_warning_headline',30.059015, 31.218349),
(12, 18,'Blocked road because of a series of car accidents','Road Problems','2019-03-30 12:04:02', 2,'High', 'Incident_warning_headline',30.004037, 31.124348),
(13, 19,'Blocked road because of heavy Rain','Road Problems','2019-01-01 06:56:21', 2,'High', 'Incident_warning_headline',29.992200, 31.141469);



-- --------------------------------------------------------


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

--
-- Dumping data for table `incidents`
--

INSERT INTO `incidents` (`IncidentId`, `UserId`, `Incident_name`, `Description`, `Category`, `Severity`, `Incident_datetime`, `Longitude`, `Latitude`,
 `AreaId`, `Incident_photo`, `Number_of_upvotes`, `Number_of_downvotes`) VALUES
(1, 32, 'Assault', NULL, 'Assault', 'High', '2019-12-05 12:39:16', 31.21909075986, 30.056582141259, 1, NULL, 7, 3),
(2, 5, 'Assault', NULL, 'Assault', 'Urgent', '2018-12-03 12:39:16', 31.126242183351, 30.017965481496, 2, NULL, 7, 10),
(3, 1, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Normal', '2019-03-03 07:37:40', 31.119047655478, 30.004672493205, 2, NULL, 5, 4),
(4, 7, 'Power Outage', NULL, 'Power Outage', 'Urgent', '2019-01-02 20:55:46', 31.117022101898, 30.011694134746, 2, NULL, 6, 4),
(5, 46, 'Too much Grabage Here', NULL, 'Grabage', 'Low', '2019-02-12 17:47:37', 31.161111032308, 30.000426141469, 3, NULL, 8, 2),
(6, 28, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Urgent', '2019-03-01 01:13:46', 31.218845700996, 30.056415069628, 1, NULL, 10, 6),
(7, 43, 'Harrasement', NULL, 'Harrasement', 'Low', '2019-02-06 01:02:14', 31.123900955443, 30.00458563618, 2, NULL, 1, 2),
(8, 63, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Normal', '2019-03-22 09:07:29', 31.219232137596, 30.056510135808, 1, NULL, 9, 4),
(9, 53, 'Assault', NULL, 'Assault', 'Low', '2019-04-04 12:44:00', 31.171190321445, 29.994301533969, 3, NULL, 2, 7),
(10, 67, 'Too much Grabage Here', NULL, 'Grabage', 'Normal', '2019-03-14 09:24:11', 31.128212083605, 30.013186673849, 2, NULL, 2, 2),
(11, 55, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Urgent', '2019-04-25 08:06:23', 31.169106070066, 29.998033288584, 3, NULL, 9, 2),
(12, 14, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-04-01 19:08:58', 31.159010827491, 29.993883808919, 3, NULL, 2, 6),
(13, 26, 'Missing Pet', NULL, 'Missing Pet', 'Urgent', '2019-01-18 05:36:38', 31.218928926245, 30.056583380312, 1, NULL, 10, 6),
(14, 59, 'Missing Person', NULL, 'Missing Person', 'Urgent', '2019-01-05 22:24:58', 31.219184856006, 30.056312976639, 1, NULL, 7, 7),
(15, 26, 'Missing Pet', NULL, 'Missing Pet', 'Urgent', '2019-01-17 23:32:36', 31.219010213393, 30.056581991315, 1, NULL, 9, 9),
(16, 51, 'Building is on Fire! , Beware', NULL, 'Fire', 'Urgent', '2019-01-07 00:58:38', 31.172579580879, 29.995268010215, 3, NULL, 9, 6),
(17, 59, 'Building is on Fire! , Beware', NULL, 'Fire', 'Normal', '2019-02-14 20:08:57', 31.219074154337, 30.056717316915, 1, NULL, 1, 9),
(18, 45, 'Harrasement', NULL, 'Harrasement', 'High', '2019-03-11 15:33:00', 31.121988161743, 30.006805271061, 2, NULL, 3, 6),
(19, 66, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'High', '2019-04-22 03:37:23', 31.170613857895, 29.989172594178, 3, NULL, 9, 5),
(20, 65, 'Robbery, be alert!', NULL, 'Robbery', 'Urgent', '2019-01-23 06:46:21', 31.219259585919, 30.056683394635, 1, NULL, 2, 1),
(21, 30, 'Sewer Leakage', NULL, 'Sewer Leakage', 'High', '2019-04-08 21:31:44', 31.11615511947, 30.007823378394, 2, NULL, 9, 8),
(22, 52, 'Power Outage', NULL, 'Power Outage', 'Urgent', '2019-01-29 21:15:39', 31.161223558908, 29.993923989349, 3, NULL, 2, 10),
(23, 63, 'Missing Pet', NULL, 'Missing Pet', 'Low', '2019-03-05 02:44:34', 31.161070098794, 29.987724444514, 3, NULL, 2, 6),
(24, 25, 'Water Outage', NULL, 'Water Outage', 'High', '2019-01-20 01:06:34', 31.126344968712, 30.010265696602, 2, NULL, 2, 7),
(25, 30, 'theft', NULL, 'theft', 'Normal', '2019-01-16 17:38:30', 31.114482339454, 30.009342285435, 2, NULL, 5, 9),
(26, 26, 'Power Outage', NULL, 'Power Outage', 'High', '2019-01-20 11:42:09', 31.128699742057, 30.00676078701, 2, NULL, 2, 3),
(27, 47, 'Building is on Fire! , Beware', NULL, 'Fire', 'High', '2019-02-21 22:33:19', 31.21903953191, 30.056470461632, 1, NULL, 9, 3),
(28, 32, 'Power Outage', NULL, 'Power Outage', 'Low', '2019-01-23 13:24:16', 31.124323315538, 30.016557511735, 2, NULL, 9, 2),
(29, 51, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Urgent', '2019-01-15 20:15:26', 31.218932108244, 30.056396168469, 1, NULL, 6, 9),
(30, 23, 'Too much Grabage Here', NULL, 'Grabage', 'Urgent', '2019-01-12 14:14:08', 31.16191863518, 29.991157590348, 3, NULL, 2, 10),
(31, 6, 'Water Outage', NULL, 'Water Outage', 'Urgent', '2019-01-30 00:52:10', 31.163892590879, 29.986859338802, 3, NULL, 6, 1),
(32, 20, 'Too much Grabage Here', NULL, 'Grabage', 'High', '2019-01-21 22:18:43', 31.219298511682, 30.056552475831, 1, NULL, 9, 8),
(33, 69, 'Water Outage', NULL, 'Water Outage', 'Low', '2019-02-28 07:11:17', 31.122634942704, 30.008048660084, 2, NULL, 10, 5),
(34, 1, 'Missing Person', NULL, 'Missing Person', 'Normal', '2019-04-03 09:02:29', 31.126000425029, 30.009430787184, 2, NULL, 2, 10),
(35, 31, 'theft', NULL, 'theft', 'Normal', '2019-01-03 03:46:06', 31.116642978042, 30.004374646105, 2, NULL, 2, 8),
(36, 26, 'theft', NULL, 'theft', 'Urgent', '2019-02-06 15:31:00', 31.169547227615, 29.997876730999, 3, NULL, 6, 6),
(37, 38, 'Missing Pet', NULL, 'Missing Pet', 'Urgent', '2019-04-06 07:34:17', 31.122383361659, 30.009375618, 2, NULL, 6, 3),
(38, 47, 'Water Outage', NULL, 'Water Outage', 'Normal', '2019-03-18 11:35:38', 31.169249558471, 29.996837015179, 3, NULL, 6, 1),
(39, 2, 'Sewer Leakage', NULL, 'Sewer Leakage', 'High', '2019-01-14 16:15:53', 31.163416704385, 29.997451587731, 3, NULL, 4, 6),
(40, 2, 'Building is on Fire! , Beware', NULL, 'Fire', 'Low', '2019-03-12 04:02:19', 31.164359783694, 29.985421067737, 3, NULL, 6, 7),
(41, 65, 'Harrasement', NULL, 'Harrasement', 'Low', '2019-03-29 10:38:58', 31.117677640337, 30.012345433958, 2, NULL, 4, 6),
(42, 10, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'High', '2019-01-27 02:30:29', 31.163007064732, 29.989620101768, 3, NULL, 7, 7),
(43, 28, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Low', '2019-03-13 03:38:22', 31.1217844912, 30.012580821857, 2, NULL, 2, 8),
(44, 48, 'Sewer Leakage', NULL, 'Sewer Leakage', 'High', '2019-04-27 11:45:00', 31.219102253971, 30.056674088239, 1, NULL, 3, 8),
(45, 30, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'High', '2019-04-30 18:13:04', 31.218882924542, 30.056363525044, 1, NULL, 5, 7),
(46, 1, 'Missing Person', NULL, 'Missing Person', 'Normal', '2019-01-28 21:07:56', 31.122606834795, 30.00797737902, 2, NULL, 10, 1),
(47, 35, 'Harrasement', NULL, 'Harrasement', 'High', '2019-03-29 09:36:33', 31.219208841745, 30.056618581423, 1, NULL, 1, 3),
(48, 32, 'Water Outage', NULL, 'Water Outage', 'High', '2019-01-30 03:57:15', 31.123989909651, 30.013808004352, 2, NULL, 4, 2),
(49, 45, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-02-21 11:22:42', 31.219172327436, 30.056364791184, 1, NULL, 1, 2),
(50, 61, 'theft', NULL, 'theft', 'Normal', '2019-02-24 22:31:21', 31.21933893409, 30.056439593011, 1, NULL, 8, 2),
(51, 12, 'Missing Person', NULL, 'Missing Person', 'Normal', '2019-01-09 13:45:21', 31.119488993606, 30.01806502838, 2, NULL, 9, 5),
(52, 48, 'theft', NULL, 'theft', 'Urgent', '2019-01-18 01:26:03', 31.129215396992, 30.014805124539, 2, NULL, 2, 4),
(53, 13, 'Missing Person', NULL, 'Missing Person', 'Normal', '2019-03-31 05:02:12', 31.219352124667, 30.056451978859, 1, NULL, 4, 10),
(54, 7, 'Power Outage', NULL, 'Power Outage', 'High', '2019-03-22 19:15:15', 31.124632553313, 30.006115134781, 2, NULL, 1, 10),
(55, 30, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Normal', '2019-04-21 21:22:28', 31.17409072182, 29.99865985179, 3, NULL, 9, 2),
(56, 19, 'Too much Grabage Here', NULL, 'Grabage', 'High', '2019-02-04 16:23:56', 31.167091359559, 29.99805638215, 3, NULL, 7, 4),
(57, 65, 'Missing Pet', NULL, 'Missing Pet', 'Low', '2019-02-24 20:11:32', 31.12677494767, 30.013142900044, 2, NULL, 7, 3),
(58, 11, 'Power Outage', NULL, 'Power Outage', 'High', '2019-01-09 19:58:22', 31.121571385395, 30.013318814048, 2, NULL, 8, 1),
(59, 57, 'Too much Grabage Here', NULL, 'Grabage', 'Urgent', '2019-01-07 15:30:00', 31.162483753074, 30.00155464928, 3, NULL, 3, 2),
(60, 14, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Urgent', '2019-04-14 02:02:53', 31.219210438289, 30.056678557638, 1, NULL, 9, 8),
(61, 3, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Low', '2019-02-08 19:06:53', 31.167284784188, 29.994598414426, 3, NULL, 2, 10),
(62, 13, 'Water Outage', NULL, 'Water Outage', 'Urgent', '2019-01-27 16:52:07', 31.12268602488, 30.018964082563, 2, NULL, 3, 7),
(63, 65, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-01-29 07:22:30', 31.161292232803, 29.994859284629, 3, NULL, 8, 5),
(64, 69, 'Missing Person', NULL, 'Missing Person', 'Urgent', '2019-03-20 19:11:48', 31.169222031379, 29.985840680936, 3, NULL, 7, 1),
(65, 10, 'Power Outage', NULL, 'Power Outage', 'Low', '2019-02-03 00:27:48', 31.128902127253, 30.009270877943, 2, NULL, 6, 2),
(66, 46, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-02-14 17:01:11', 31.160903580862, 29.989783006085, 3, NULL, 9, 8),
(67, 49, 'Robbery, be alert!', NULL, 'Robbery', 'High', '2019-03-31 02:07:22', 31.123509958336, 30.008190074308, 2, NULL, 1, 10),
(68, 62, 'theft', NULL, 'theft', 'Normal', '2019-03-27 13:59:15', 31.219087871878, 30.056541358818, 1, NULL, 5, 5),
(69, 42, 'Robbery, be alert!', NULL, 'Robbery', 'Normal', '2019-02-27 21:11:06', 31.219120559006, 30.056358746155, 1, NULL, 9, 10),
(70, 34, 'Water Outage', NULL, 'Water Outage', 'Normal', '2019-01-17 01:40:58', 31.121376101553, 30.015156158067, 2, NULL, 6, 1),
(71, 6, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-01-21 16:40:41', 31.119233579775, 30.003792656945, 2, NULL, 4, 5),
(72, 54, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-03-12 02:35:03', 31.218933902938, 30.056678864123, 1, NULL, 5, 5),
(73, 65, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Low', '2019-02-28 15:27:22', 31.169781126944, 29.996993761151, 3, NULL, 5, 1),
(74, 19, 'Harrasement', NULL, 'Harrasement', 'Urgent', '2019-04-26 15:36:17', 31.166500084625, 29.993429604436, 3, NULL, 8, 2),
(75, 41, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Low', '2019-03-11 21:43:05', 31.165317655943, 29.998384980998, 3, NULL, 2, 9),
(76, 52, 'Too much Grabage Here', NULL, 'Grabage', 'Normal', '2019-03-12 01:08:39', 31.170127763277, 30.000640847636, 3, NULL, 4, 1),
(77, 55, 'Harrasement', NULL, 'Harrasement', 'Low', '2019-04-07 04:25:58', 31.165366308801, 30.00122550034, 3, NULL, 4, 10),
(78, 63, 'Robbery, be alert!', NULL, 'Robbery', 'High', '2019-03-16 04:48:32', 31.219217994837, 30.056457412385, 1, NULL, 10, 6),
(79, 26, 'Stray Dogs', NULL, 'Stray Dogs', 'Urgent', '2019-02-06 12:24:05', 31.168279065913, 29.997965653174, 3, NULL, 6, 4),
(80, 53, 'theft', NULL, 'theft', 'High', '2019-01-07 16:56:39', 31.219170937205, 30.056739678291, 1, NULL, 2, 2),
(81, 47, 'Assault', NULL, 'Assault', 'Normal', '2019-02-26 20:48:48', 31.219249542679, 30.056273346357, 1, NULL, 6, 1),
(82, 18, 'Missing Person', NULL, 'Missing Person', 'Low', '2019-04-16 00:20:02', 31.120233821332, 30.017125028545, 2, NULL, 4, 1),
(83, 67, 'theft', NULL, 'theft', 'Normal', '2019-01-20 14:31:56', 31.164914438307, 30.002348455338, 3, NULL, 4, 8),
(84, 18, 'Assault', NULL, 'Assault', 'High', '2019-04-20 18:32:29', 31.1151772042, 30.007359785903, 2, NULL, 6, 3),
(85, 12, 'Water Outage', NULL, 'Water Outage', 'Normal', '2019-04-08 09:49:35', 31.219108903743, 30.056593043232, 1, NULL, 4, 8),
(86, 46, 'Power Outage', NULL, 'Power Outage', 'Low', '2019-04-02 04:02:59', 31.116829964664, 30.018052290169, 2, NULL, 10, 6),
(87, 12, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-04-13 07:21:34', 31.116843856698, 30.013475060065, 2, NULL, 4, 3),
(88, 56, 'Building is on Fire! , Beware', NULL, 'Fire', 'Low', '2019-04-29 03:36:44', 31.120092004653, 30.019150831041, 2, NULL, 8, 9),
(89, 39, 'Harrasement', NULL, 'Harrasement', 'Urgent', '2019-02-13 00:56:01', 31.219166294792, 30.056510960566, 1, NULL, 10, 5),
(90, 58, 'Water Outage', NULL, 'Water Outage', 'Normal', '2019-02-10 05:45:54', 31.219111386976, 30.056236943463, 1, NULL, 3, 10),
(91, 27, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-03-02 23:43:27', 31.173963439257, 29.991754977904, 3, NULL, 6, 8),
(92, 3, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-02-03 02:29:15', 31.219288617631, 30.056381292037, 1, NULL, 9, 6),
(93, 12, 'Assault', NULL, 'Assault', 'Urgent', '2019-03-23 23:02:49', 31.12131770149, 30.010518121432, 2, NULL, 9, 6),
(94, 4, 'Robbery, be alert!', NULL, 'Robbery', 'Urgent', '2019-03-07 10:54:05', 31.21932096033, 30.056412801733, 1, NULL, 10, 10),
(95, 64, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-02-24 21:15:51', 31.164122607636, 29.992577334675, 3, NULL, 6, 2),
(96, 50, 'Building is on Fire! , Beware', NULL, 'Fire', 'High', '2019-04-27 20:15:11', 31.165065746536, 30.00093503793, 3, NULL, 3, 9),
(97, 51, 'Too much Grabage Here', NULL, 'Grabage', 'Normal', '2019-03-25 01:29:43', 31.125230283413, 30.01158393024, 2, NULL, 2, 3),
(98, 9, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'High', '2019-04-27 09:10:04', 31.219134835365, 30.056594846674, 1, NULL, 7, 3),
(99, 12, 'Too much Grabage Here', NULL, 'Grabage', 'Normal', '2019-01-26 18:31:56', 31.121100456739, 30.018737475403, 2, NULL, 6, 3),
(100, 55, 'Assault', NULL, 'Assault', 'Normal', '2019-03-06 16:20:55', 31.167118504565, 29.987393673522, 3, NULL, 6, 10),
(101, 26, 'Too much Grabage Here', NULL, 'Grabage', 'Urgent', '2019-01-13 00:19:08', 31.21888012628, 30.056519737011, 1, NULL, 7, 3),
(102, 68, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Low', '2019-03-14 11:58:54', 31.16315406799, 29.995722751232, 3, NULL, 2, 4),
(103, 12, 'Missing Pet', NULL, 'Missing Pet', 'Low', '2019-01-29 11:52:53', 31.113054645914, 30.011285508947, 2, NULL, 9, 8),
(104, 42, 'Water Outage', NULL, 'Water Outage', 'Urgent', '2019-01-31 16:06:19', 31.114653664757, 30.012441958082, 2, NULL, 7, 10),
(105, 18, 'Missing Pet', NULL, 'Missing Pet', 'High', '2019-03-23 18:01:44', 31.219288934498, 30.056629620182, 1, NULL, 4, 8),
(106, 66, 'Missing Person', NULL, 'Missing Person', 'Normal', '2019-03-19 14:29:43', 31.173183110395, 29.997074529924, 3, NULL, 7, 8),
(107, 40, 'theft', NULL, 'theft', 'High', '2019-04-09 00:33:12', 31.166083554009, 29.98679998312, 3, NULL, 5, 3),
(108, 68, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Urgent', '2019-04-06 05:37:29', 31.219040651869, 30.056751148883, 1, NULL, 10, 6),
(109, 66, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Low', '2019-03-27 10:15:02', 31.125482000849, 30.018647260082, 2, NULL, 1, 6),
(110, 10, 'Missing Person', NULL, 'Missing Person', 'Low', '2019-03-14 07:27:31', 31.161586726333, 29.993890784458, 3, NULL, 1, 1),
(111, 22, 'Harrasement', NULL, 'Harrasement', 'Normal', '2019-02-01 18:50:02', 31.219158050588, 30.056714712049, 1, NULL, 5, 10),
(112, 42, 'Water Outage', NULL, 'Water Outage', 'Urgent', '2019-04-22 00:33:32', 31.125188781462, 30.017180408354, 2, NULL, 5, 2),
(113, 3, 'Power Outage', NULL, 'Power Outage', 'Normal', '2019-01-31 17:19:40', 31.21910201442, 30.056516520731, 1, NULL, 5, 2),
(114, 39, 'Harrasement', NULL, 'Harrasement', 'Normal', '2019-03-31 03:33:28', 31.126564787481, 30.012353817899, 2, NULL, 2, 3),
(115, 11, 'Sewer Leakage', NULL, 'Sewer Leakage', 'Normal', '2019-01-23 12:52:02', 31.12571860617, 30.011740570484, 2, NULL, 10, 5),
(116, 50, 'Robbery, be alert!', NULL, 'Robbery', 'Normal', '2019-02-27 09:20:51', 31.219081740521, 30.056505771027, 1, NULL, 1, 6),
(117, 42, 'Power Outage', NULL, 'Power Outage', 'Urgent', '2019-01-22 11:35:49', 31.219147440494, 30.056563700254, 1, NULL, 6, 3),
(118, 30, 'theft', NULL, 'theft', 'Urgent', '2019-04-26 14:27:19', 31.118257153751, 30.010881233229, 2, NULL, 5, 10),
(119, 50, 'Robbery, be alert!', NULL, 'Robbery', 'High', '2019-03-25 04:38:58', 31.113989315721, 30.014869359603, 2, NULL, 8, 6),
(120, 56, 'Stray Dogs', NULL, 'Stray Dogs', 'Normal', '2019-03-09 21:11:08', 31.114689218587, 30.008636327444, 2, NULL, 4, 10),
(121, 35, 'Robbery, be alert!', NULL, 'Robbery', 'Normal', '2019-03-19 13:27:09', 31.166691050048, 29.985475776137, 3, NULL, 5, 8),
(122, 59, 'Dangerous Fogs in Highway', NULL, 'Dangerous Weather', 'Urgent', '2019-03-29 17:06:16', 31.112902963546, 30.010325248864, 2, NULL, 9, 10),
(123, 14, 'Water Outage', NULL, 'Water Outage', 'High', '2019-02-22 19:37:40', 31.117292130774, 30.007320239291, 2, NULL, 3, 8),
(124, 57, 'theft', NULL, 'theft', 'Low', '2019-04-21 12:01:09', 31.166773124869, 29.985702442629, 3, NULL, 7, 7),
(125, 29, 'Robbery, be alert!', NULL, 'Robbery', 'Urgent', '2019-02-08 09:42:43', 31.164893550823, 29.989998753065, 3, NULL, 9, 7),
(126, 14, 'Sewer Leakage', NULL, 'Sewer Leakage', 'High', '2019-03-20 14:47:06', 31.120820484165, 30.01863746901, 2, NULL, 8, 7),
(127, 62, 'Robbery, be alert!', NULL, 'Robbery', 'Urgent', '2019-02-24 23:48:40', 31.126647812354, 30.011957565496, 2, NULL, 1, 4),
(128, 49, 'Water Outage', NULL, 'Water Outage', 'Normal', '2019-02-04 03:52:48', 31.122580617693, 30.005116327026, 2, NULL, 7, 1),
(129, 40, 'Missing Pet', NULL, 'Missing Pet', 'Low', '2019-04-24 22:57:45', 31.124052557308, 30.005622514565, 2, NULL, 2, 2),
(130, 40, 'Water Outage', NULL, 'Water Outage', 'Urgent', '2019-02-20 05:07:31', 31.127791077781, 30.011262771098, 2, NULL, 2, 6),
(131, 23, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-03-31 20:24:53', 31.219205453138, 30.056518520382, 1, NULL, 2, 3),
(132, 19, 'Assault', NULL, 'Assault', 'Normal', '2019-01-24 07:23:13', 31.121746918856, 30.012397378149, 2, NULL, 6, 5),
(133, 39, 'Too much Grabage Here', NULL, 'Grabage', 'Low', '2019-03-08 12:03:04', 31.218882597448, 30.05635708284, 1, NULL, 10, 4),
(134, 48, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Low', '2019-04-02 10:53:49', 31.219204421393, 30.056247268192, 1, NULL, 10, 9),
(135, 41, 'Harrasement', NULL, 'Harrasement', 'Urgent', '2019-02-02 05:03:37', 31.119073205302, 30.014742118137, 2, NULL, 2, 5),
(136, 5, 'theft', NULL, 'theft', 'Low', '2019-03-30 07:32:43', 31.161890460731, 29.994487770644, 3, NULL, 8, 5),
(137, 36, 'Robbery, be alert!', NULL, 'Robbery', 'Low', '2019-04-13 02:11:18', 31.159783288854, 29.991637791463, 3, NULL, 5, 2),
(138, 64, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'High', '2019-02-28 15:47:45', 31.119257208012, 30.012341675727, 2, NULL, 5, 10),
(139, 21, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Normal', '2019-03-19 12:34:21', 31.167613226615, 29.994656031123, 3, NULL, 9, 10),
(140, 14, 'theft', NULL, 'theft', 'Normal', '2019-02-26 02:06:33', 31.160160235361, 29.997681438856, 3, NULL, 10, 1),
(141, 21, 'Building is on Fire! , Beware', NULL, 'Fire', 'Low', '2019-02-07 02:32:30', 31.219360053796, 30.056571442242, 1, NULL, 1, 6),
(142, 40, 'Harrasement', NULL, 'Harrasement', 'High', '2019-02-09 22:10:49', 31.175548029677, 29.994464121948, 3, NULL, 6, 5),
(143, 13, 'Too many unauthorized means of transportation', NULL, 'unauthorized means of transportation', 'Urgent', '2019-02-01 11:59:47', 31.218899827557, 30.056365657989, 1, NULL, 4, 3);

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


DROP TABLE IF EXISTS `organization_in_area`;

CREATE TABLE `Organization_in_area` (

  `OrganizationId` int(11) NOT NULL,
  `Organization_name` varchar(50) NOT NULL,
  `Organization_Description` text NOT NULL,
  `category` varchar(100) NOT NULL,
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
  `Rating` float NOT NULL,
  `AreaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `Username`, `Email`, `Password`, `User_photo`, `Home_address`, `Longitude`, `Latitude`, `Rating`, `AreaId`) VALUES
(1, 'mjollye0', 'msouthon0@flickr.com', 'JKqF9Aug2K5', '', '4 Mohammed Abd El-Wahab Omar Al Khayam Zamalek Giza Governorate', 31.2242735, 30.0536882, 0, 1),
(2, 'eburgen1', 'vsellar1@simplemachines.org', '6Zq1fw', '', '24 Al Gezera Omar Al Khayam Zamalek Giza Governorate', 31.2177074, 30.0537111, 0, 1),
(3, 'oantognetti2', 'cgutherson2@wikimedia.org', '6iBZz2Wj', '', '3 Meshel Lotf Allah Omar Al Khayam Zamalek Cairo Governorate', 31.2217122, 30.0582578, 0, 1),
(4, 'mreignould3', 'cshepperd3@youtu.be', 'S3uZyRbXE', '', '6 Kamal Al Tawil Mohammed Mazhar Zamalek Giza Governorate', 31.2202485, 30.0694507, 0, 1),
(5, 'ktaks4', 'bhardwidge4@woothemes.com', 'vkbLuOQ', '', '20 Kamal Al Tawil Mohammed Mazhar Zamalek Giza Governorate', 31.2203854, 30.0713422, 0, 1),
(6, 'msalmond5', 'sruselin5@nymag.com', 'Pp5QScHHs1', '', '35 Al Gazira Al WSTA Mohammed Mazhar Zamalek Giza Governorate', 31.2182806, 30.0712678, 0, 1),
(7, 'lsaddington6', 'wbanbury6@mysql.com', '2gwPKp', '', '19 Abou Al Fada Abu Al Feda Zamalek Giza Governorate', 31.2145237, 30.0672182, 0, 1),
(8, 'hoglethorpe7', 'fsebire7@senate.gov', 'kZvedNNH5JlZ', '', '3 Bahgat Ali Abu Al Feda Zamalek Giza Governorate', 31.214932, 30.0654888, 0, 1),
(9, 'kboame8', 'bfancourt8@bigcartel.com', 'LRzGjGPxV', '', '23 Ibn Zanikai Al Gabalayah Zamalek Giza Governorate', 31.2146803, 30.0610634, 0, 1),
(10, 'ckillen9', 'etonnesen9@nps.gov', 'Tlfu0agH', '', '4 Shagaret Al Dor Al Gabalayah Zamalek Giza Governorate', 31.2152393, 30.0595556, 0, 1),
(11, 'bmcgaugana', 'twombwella@phoca.cz', 'p07wP3W2m6o', '', '2 Al Kamel Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2164644, 30.0578133, 0, 1),
(12, 'kbedboroughb', 'emassenhoveb@unesco.org', '0PVPjBqLR2', '', 'Bahaa El-Deen Qaraqosh Al Gabalayah Zamalek Giza Governorate', 31.2175864, 30.0569278, 0, 1),
(13, 'tfenningc', 'rvassiec@patch.com', 'gtXntpzW', '', '14 Hassan Sabry St Omar Al Khayam Zamalek Giza Governorate', 31.2182534, 30.057587, 0, 1),
(14, 'obrattelld', 'jfillsd@fema.gov', 'rh6Tu6gCC8pe', '', '6 Al Kamel Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2172577, 30.0590799, 0, 1),
(15, 'mwhisbye', 'cthieme@microsoft.com', 'UiCmJKGOI', '', '6 Salah El-Deen Al Gabalayah Zamalek Giza Governorate', 31.2173848, 30.058354, 0, 1),
(16, 'fharmesf', 'dkullerf@jigsy.com', 'nJIRuBx', '', '12 Mohammed Abd El-Wahab Omar Al Khayam Zamalek Giza Governorate', 31.2240052, 30.0544086, 0, 1),
(17, 'hcustyg', 'zgeertseng@edublogs.org', 'JM6cgXC0', '', '12 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2202932, 30.0563997, 0, 1),
(18, 'imattecoth', 'bsmurfitth@xrea.com', '9Ju5a4TXGcp', '', '10 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2199801, 30.0564413, 0, 1),
(19, 'jgrossi', 'alowdhami@chronoengine.com', 'NfyhKJjv', '', '3 Al Shahid Eshak Yagoob Omar Al Khayam Zamalek Giza Governorate', 31.2213975, 30.0585885, 0, 1),
(20, 'mshrubsallj', 'kbillettj@yellowpages.com', 'CXFzdag', '', 'Yehia Ibrahim Mohammed Mazhar Zamalek Cairo Governorate', 31.2211922, 30.0605585, 0, 1),
(21, 'mslayk', 'wmacafeek@ucla.edu', 'Fg1Z7TJ', '', '3 Brazil St Mohammed Mazhar Zamalek Cairo Governorate', 31.2202237, 30.060679, 0, 1),
(22, 'edewil', 'bivashovl@prweb.com', 'obakHZa', '', '6 Ahmed Sabry Mohammed Mazhar Zamalek Giza Governorate', 31.2190856, 30.0616323, 0, 1),
(23, 'xbrierleym', 'estrutem@prweb.com', 'GFYcqKz', '', '14 El-Saleh Ayoub Al Gabalayah Zamalek Giza Governorate', 31.217006, 30.0607367, 0, 1),
(24, 'nbrowettn', 'rpremblen@sbwire.com', 'zRm0IdNM5npV', '', '11 Shagaret Al Dor Al Gabalayah Zamalek Giza Governorate', 31.2164916, 30.0609327, 0, 1),
(25, 'ucorradettio', 'akibbelo@macromedia.com', 'afOZzGpm', '', '8 El-Mansour Mohammed Al Gabalayah Zamalek Giza Governorate', 31.2151499, 30.061204, 0, 1),
(26, 'iyurinp', 'mluetkemeyerp@slideshare.net', 'YwKavaGfs8f9', '', '22 Ibn Zanikai Al Gabalayah Zamalek Giza Governorate', 31.2147474, 30.0606192, 0, 1),
(27, 'glinnitq', 'kjoseffq@facebook.com', '29sq8gvQTIed', '', '23 Salah El-Deen Al Gabalayah Zamalek Giza Governorate', 31.214971, 30.0597074, 0, 1),
(28, 'truzicr', 'awitardr@census.gov', 'CD95TvPMQg', '', '5 Bahaa El-Deen Qaraqosh Al Gabalayah Zamalek Giza Governorate', 31.2169486, 30.0573756, 0, 1),
(29, 'dsmithens', 'estutelys@yale.edu', 'XS8jUL', '', '2 El-Aziz Othman Al Gabalayah Zamalek Giza Governorate', 31.2175042, 30.0576147, 0, 1),
(30, 'drenfieldt', 'pbowyert@quantcast.com', 'LfekFuKMENs', '', '14 Al Sheikh Al Marsafi Omar Al Khayam Zamalek Giza Governorate', 31.2205168, 30.0564628, 0, 1),
(31, 'awignallu', 'dbalwinu@samsung.com', 'LdmMosY8eu', '', '4 Ahmed El-Kashef Omar Al Khayam Zamalek Giza Governorate', 31.2202866, 30.0565991, 0, 1),
(32, 'tadessv', 'gezzellv@tripadvisor.com', 'D2zRo2HNPL', '', '3 Mahmoud Azmy Omar Al Khayam Zamalek Giza Governorate', 31.220159, 30.0573692, 0, 1),
(33, 'ykilnerw', 'bspranklingw@hubpages.com', 'Lrrictl8y8qo', '', '4 Omarat Aliamni Omar Al Khayam Zamalek Cairo Governorate', 31.2217006, 30.0577369, 0, 1),
(34, 'hhumbeyx', 'rlafrentzx@harvard.edu', 'Ilb5UDw9zK', '', 'Zaki Ali Omar Al Khayam Zamalek Giza Governorate', 31.2222278, 30.0588963, 0, 1),
(35, 'vbrimfieldy', 'aabbayy@economist.com', 'aOEnaK', '', 'Aziz Abaza Mohammed Mazhar Zamalek Giza Governorate', 31.2234841, 30.0596489, 0, 1),
(36, 'rdonovanz', 'jfluinz@si.edu', 'L2huTKMZAD', '', '300 Al Haram, At Talbeyah Al Qebleyah, Al Omraneyah, Giza Governorate', 31.1735283, 29.9994864, 0, 3),
(37, 'meves10', 'ipollak10@facebook.com', 'uUsLk9A', '', '432 King Faisal St Oula Al Haram Al Omraneyah Giza Governorate', 31.1741211, 30.000581, 0, 3),
(38, 'barnald11', 'emiebes11@hp.com', 'pNDRni', '', '6 El-Salam Al Omraneyah Giza Governorate', 31.1750572, 29.9996383, 0, 3),
(39, 'ogarett12', 'fhess12@hhs.gov', 'eDOWiIVTD94J', '', 'Hamida Tag Al Din At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1783203, 30.000384, 0, 3),
(40, 'smattosoff13', 'hpurkiss13@mac.com', '7E4ZP6bYmWIT', '', 'Taha Hussein Al Omraneyah Al Gharbeyah Al Omraneyah Giza Governorate', 31.1827977, 30.0039762, 0, 3),
(41, 'jmaccari14', 'kgergher14@epa.gov', '8EVqMQmC0Mfm', '', 'Ali El-Refaey Al Omraneyah Giza Governorate', 31.1785975, 30.0013924, 0, 3),
(42, 'kklishin15', 'tdann15@elegantthemes.com', '8i2XHZev7Byq', '', 'Farid El Sebaay Oula Al Haram Al Omraneyah Giza Governorate', 31.1768245, 30.0013131, 0, 3),
(43, 'mlivett16', 'lpeyes16@businessweek.com', 'oDxUaSPjx', '', 'Shaaban Shalaby Al Omraneyah Giza Governorate', 31.1760883, 29.99999, 0, 3),
(44, 'rhancke17', 'rfend17@umich.edu', 'HahKQF', '', 'Fatma Khalifa Al Omraneyah Giza Governorate', 31.1733703, 29.9992663, 0, 3),
(45, 'icaverhill18', 'skalberer18@globo.com', '4yojoWzwdQY', '', 'Ayoub At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1726615, 29.9985702, 0, 3),
(46, 'lsloat19', 'cmudle19@imdb.com', 'n631hM3', '', 'Soliman El-Banna At Talbeyah Al Qebleyah Al Omraneyah Giza Governorate', 31.1708283, 29.997642, 0, 3),
(47, 'cmelly1a', 'cwitsey1a@wix.com', 'GvgDZr', '', 'Amin Abd El-Hameed Oula Al Haram Al Omraneyah Giza Governorate', 31.1677094, 29.9980969, 0, 3),
(48, 'wgorner1b', 'ibrownlie1b@altervista.org', 'Z3Uy4TfH', '', 'Khaled Amin Oula Al Haram Al Omraneyah Giza Governorate', 31.1642116, 29.9988085, 0, 3),
(49, 'rcadamy1c', 'dchattell1c@woothemes.com', 'J3n2qA', '', 'El-Taawon Oula Al Haram Al Omraneyah Giza Governorate', 31.1623418, 29.9989283, 0, 3),
(50, 'bmuffitt1d', 'fmacalaster1d@networksolutions', 'b48JdI69', '', 'El-Omda Ahmed Abd El-Aal Oula Al Haram Al Omraneyah Giza Governorate', 31.1616643, 29.9994255, 0, 3),
(51, 'ehughs1e', 'avossing1e@indiegogo.com', 'JGgudku6v', '', 'Khater Oula Al Haram Al Omraneyah Giza Governorate', 31.1618324, 29.9994846, 0, 3),
(52, 'utotterdill1f', 'kheardman1f@wired.com', 'CIlDVzaz5v', '', 'El-Mansheya El-Gadid St Monshaat Al Bakari Al Haram Giza Governorate', 31.160006, 30.0000839, 0, 2),
(53, 'lelegood1g', 'mcuttles1g@princeton.edu', 'rb85qefUcN', '', 'King Faisal St Monshaat Al Bakari Al Haram Giza Governorate', 31.1598227, 29.9993483, 0, 2),
(54, 'rlargent1h', 'kweepers1h@bravesites.com', 'uZEEmM', '', 'Shams Ibrahim Soliman Monshaat Al Bakari Al Haram Giza Governorate', 31.1601805, 30.0007087, 0, 2),
(55, 'gbeauly1i', 'bboissier1i@howstuffworks.com', 'WTmfkh3', '', 'El-Mansheya El-Gadid St Monshaat Al Bakari Al Haram Giza Governorate', 31.1592144, 30.0020584, 0, 2),
(56, 'cbenaine1j', 'gvannozzii1j@upenn.edu', '7V2QCp', '', 'El-Omda Ahmed Abd El-Aal Oula Al Haram Al Omraneyah Giza Governorate', 31.1616643, 29.9994255, 0, 3),
(57, 'hpacquet1k', 'glumly1k@nifty.com', 'pmaIYYwEmRgy', '', 'El-Khaleel Ibrahim Oula Al Haram Al Omraneyah Giza Governorate', 31.1673514, 29.9998155, 0, 3),
(58, 'dechelle1l', 'fdotson1l@unblog.fr', 'bgWr7a6fO13', '', 'Ahmed Abd El-Mohsen Oula Al Haram Al Omraneyah Giza Governorate', 31.1596951, 29.9962236, 0, 3),
(59, 'dheak1m', 'lbellis1m@360.cn', 'IBPst2W', '', 'Mohammed El-Mahdi Oula Al Haram Al Omraneyah Giza Governorate', 31.1582816, 29.9961746, 0, 3),
(60, 'mmar1n', 'mskate1n@ca.gov', 'QQbqLA', '', 'Ramseis Al Sani Oula Al Haram Al Omraneyah Giza Governorate', 31.156267, 29.9940482, 0, 3),
(61, 'wbatrick1o', 'sworks1o@hao123.com', 'jNMJb8pAv', '', '132 Al Haram Oula Al Haram Al Omraneyah Giza Governorate', 31.1546707, 29.992887, 0, 3),
(62, 'gdavidge1p', 'obottjer1p@nymag.com', 'P2jAtEm', '', 'Nemt Nasr Allah Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1546108, 29.9920694, 0, 3),
(63, 'zpawelek1q', 'shampstead1q@cnbc.com', 'kKXbCh', '', 'Aboi Gazia Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1578353, 29.99213, 0, 3),
(64, 'nyaakov1r', 'sbosket1r@craigslist.org', '5GQbPS', '', 'El-Omda Oula Al Haram Al Omraneyah Giza Governorate', 31.150708, 29.9916669, 0, 3),
(65, 'hambage1s', 'sborley1s@canalblog.com', 'vh3Bzg6c', '', 'Hasan Samorah Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.15137, 29.9908798, 0, 3),
(66, 'aouver1t', 'rbreckell1t@hostgator.com', 'scLF0ibhFhF', '', 'Ragab Abd El-Hameed Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1531914, 29.9889701, 0, 3),
(67, 'hlarman1u', 'wcottu1u@pagesperso-orange.fr', 'j315h1K', '', 'El-Omda El-Kadeem Al Kom Al Akhdar Al Omraneyah Giza Governorate', 31.1552165, 29.9880551, 0, 3),
(68, 'tjaskowicz1v', 'strebilcock1v@usgs.gov', 'yfpJLZ', '', 'Ajiad Makkah Kafr Nassar Al Haram Giza Governorate', 31.1350563, 30.0004029, 0, 2),
(69, 'espellar1w', 'aoconnel1w@ucla.edu', 'cPYP1LaWqBh', '', 'Mohammed Ateya Kafr Nassar Al Haram Giza Governorate', 31.1346351, 29.9988617, 0, 2),
(70, 'agirt1x', 'bmacklam1x@vkontakte.ru', 'FCOgFJiT', '', 'El-Iman Kafr Nassar Al Haram Giza Governorate', 31.1344996, 29.9984566, 0, 2),
(71, 'Alvera Cassley', 'acassley0@pcworld.com', 'iMRKg9XVks', '', 'Abou El Feda, Mohammed Mazhar, Zamalek, Giza Governorate', 30.072332, 31.221248, 0, 1),
(72, 'Greg Faulkener', 'gfaulkener1@meetup.com', 'u8r27E', '', '2 Sekat Abou Al Fida\nAbu Al Feda\nZamalek\nGiza Governorate', 30.066925, 31.216475, 0, 1),
(73, 'Odelle Ruf', 'oruf2@marketwatch.com', 'AWMOeSyx', '', '18 Omar Toson, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.064477, 31.208498, 0, 0),
(74, 'Lilith MacScherie', 'lmacscherie3@microsoft.com', '8LH4TYh', '', '15 Gamal Abd Al Naser _al Nile, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.06175, 31.209133, 0, 0),
(75, 'Lexy Ewdale', 'lewdale4@weibo.com', '4VnSbv', '', '21 Bahgat Ali, Mohammed Mazhar, Zamalek, Giza Governorate', 30.070322, 31.221215, 0, 1),
(76, 'Javier Brinson', 'jbrinson5@sfgate.com', 'TED8Jw2wYz3e', '', '27 El-Yasmin, Mohammed Mazhar, Zamalek, Giza Governorate', 30.069564, 31.219255, 0, 1),
(77, 'Merissa Pocknoll', 'mpocknoll6@nytimes.com', 'a0By9tsdW', '', '31 Al Narges, Mohammed Mazhar, Zamalek, Giza Governorate', 30.070091, 31.219811, 0, 1),
(78, 'Joya Oldham', 'joldham7@over-blog.com', 'WwlE6oUqQs5', '', '33 Al Ward, Mohammed Mazhar, Zamalek, Giza Governorate', 30.070305, 31.220052, 0, 1),
(79, 'Allie Delgua', 'adelgua8@devhub.com', 'lJwPE12PJyXk', '', '10 Kamal Al Tawil, Mohammed Mazhar, Zamalek, Giza Governorate', 30.069896, 31.222461, 0, 1),
(80, 'Mabel Karpeev', 'mkarpeev9@hibu.com', 'cmMmPeQJT', '', 'Zamalek, Mohammed Mazhar, Zamalek, Giza Governorate', 30.071628, 31.22076, 0, 1),
(81, 'Helli Henrion', 'hhenriona@wikimedia.org', 'qUXqR4P8xouW', '', '5 El-Nabawy El-Mohandes, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.064006, 31.21179, 0, 0),
(82, 'Georgy Trevithick', 'gtrevithickb@behance.net', '34W3MhF8ple', '', '17 Dr Ahmed Al Hofy-Almansora, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.064888, 31.208328, 0, 0),
(83, 'Ardenia Dennes', 'adennesc@bbb.org', 's0i5Qe', '', '15 El-Gihad, Tag Ad Dewal, Imbaba, Giza Governorate', 30.068192, 31.210043, 0, 0),
(84, 'Gisela Wonham', 'gwonhamd@mediafire.com', 'ajPxQW', '', '21 Dr Ahmed Al Hofy-Almansora, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.065264, 31.208578, 0, 0),
(85, 'Fran Torpie', 'ftorpiee@umn.edu', 'CAvU2eU2770', '', '56 Saleh Abou Sewaral Mahrousa, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.064205, 31.205711, 0, 0),
(86, 'Catlaina Velte', 'cveltef@people.com.cn', 'BuzgYd5', '', '6 Abd El-Aziz Talaat Harb, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.065616, 31.205271, 0, 0),
(87, 'Matias Percival', 'mpercivalg@tuttocitta.it', 'pwBiZXZ4xPyZ', '', 'Salah El-Deen, Tag Ad Dewal, Imbaba, Giza Governorate', 30.067698, 31.214362, 0, 0),
(88, 'Nelia Gouth', 'ngouthh@dot.gov', 'binZLJ', '', '6-2 Haroun Tag Ad Dewal Imbaba Giza Governorate', 30.06989, 31.215832, 0, 0),
(89, 'Conchita Heinert', 'cheinerti@ask.com', '5Nxvls', '', '4 Ismail Mohammed, Abu Al Feda, Zamalek, Giza Governorate', 30.065273, 31.216945, 0, 1),
(90, 'Koressa Marikhin', 'kmarikhinj@scribd.com', 'hvwJuJ', '', '2 Aldoktor Allahwni, Abu Al Feda, Zamalek, Giza Governorate', 30.062515, 31.218592, 0, 1),
(91, 'Ellswerth Denson', 'edensonk@exblog.jp', 'ixVXvP7', '', '12 Hasan Asem, Mohammed Mazhar, Zamalek, Giza Governorate', 30.06278, 31.220112, 0, 1),
(92, 'Derril Sime', 'dsimel@privacy.gov.au', 'T2dzTaR9g', '', 'Unnamed Road, Abu Al Feda, Zamalek, Giza Governorate', 30.061893, 31.218132, 0, 1),
(93, 'Freida Ingley', 'fingleym@google.com.au', 'HSJKggf', '', '58 Abou Al Mahasen Al Shazli, Al Huwaiteyah, Al Agouzah, Giza Governorate', 30.060361, 31.209973, 0, 0),
(94, 'Elka Kupec', 'ekupecn@parallels.com', 'uncPBYwp6', '', '11 26 July, Al Huwaiteyah, Al Agouzah, Giza Governorate', 30.060463, 31.209168, 0, 0),
(95, 'Brandea Palffrey', 'bpalffreyo@domainmarket.com', 'z0pxwaUp', '', '5 El-Nabawy El-Mohandes, Madinet Al Eelam, Al Agouzah, Giza Governorate', 30.064006, 31.21179, 0, 0),
(96, 'Moira Moxsom', 'mmoxsomp@sciencedirect.com', 'la1AaMPwV', '', '6 Ahmed Sabry, Mohammed Mazhar, Zamalek, Giza Governorate', 30.061669, 31.221247, 0, 1),
(97, 'Addy Pighills', 'apighillsq@nhs.uk', 'gptzu2s', '', '12 Hasan Asem, Mohammed Mazhar, Zamalek, Giza Governorate', 30.062587, 31.220486, 0, 1),
(98, 'Fan Lawfull', 'flawfullr@vk.com', 'O9RwC8U7', '', '14 Al Ghayth, Al Huwaiteyah, Al Agouzah, Giza Governorate', 30.05825, 31.21434, 0, 0),
(99, 'Gracie Cowper', 'gcowpers@tmall.com', 'UVSN1j1C', '', '7 Al Zafer, Al Huwaiteyah, Al Agouzah, Giza Governorate', 30.057373, 31.214013, 0, 0),
(100, 'Mar Cropp', 'mcroppt@go.com', 'R7wa6ZupI', '', '11 Al Ghayth, Al Huwaiteyah, Al Agouzah, Giza Governorate', 30.057187, 31.214823, 0, 0);

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
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;


CREATE TABLE `notifications` (
  `NotificationId` int(11) NOT NULL,
  `DepartmentId` int(11),
  `Type` varchar(70) NOT NULL,
  `Notifcation_message` text NOT NULL,
  `Notification_datetime` datetime NOT NULL
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
  MODIFY `AreaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `DepartmentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `incidents`
--
ALTER TABLE `incidents`
  MODIFY `IncidentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;
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
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;