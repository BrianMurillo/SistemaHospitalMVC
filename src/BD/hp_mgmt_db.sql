-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-04-2021 a las 04:15:32
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hp_mgmt_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admitpatient_room`
--

CREATE TABLE `admitpatient_room` (
  `PatientID` int(10) NOT NULL,
  `Disease` varchar(50) DEFAULT NULL,
  `AdmitDate` date DEFAULT NULL,
  `RoomNo` int(5) NOT NULL,
  `DoctorID` int(10) NOT NULL,
  `AP_Remarks` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `admitpatient_room`
--

INSERT INTO `admitpatient_room` (`PatientID`, `Disease`, `AdmitDate`, `RoomNo`, `DoctorID`, `AP_Remarks`) VALUES
(1, 'Typhoid fever', '2019-03-21', 5, 1, 'High temp and weakness.'),
(2, 'Vomito', '2021-01-13', 6, 1, ''),
(3, 'Fractura', '2018-04-15', 3, 1, ''),
(4, 'Toz', '2021-02-04', 1, 2, 'Anginas'),
(5, 'Toz', '2020-01-21', 6, 2, ''),
(12, 'Fiebre', '2019-01-20', 2, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bill_room`
--

CREATE TABLE `bill_room` (
  `DischargeID` int(5) DEFAULT NULL,
  `BillingDate` date DEFAULT NULL,
  `RoomCharges` int(10) DEFAULT NULL,
  `ServiceCharges` int(10) DEFAULT NULL,
  `PaymentMode` varchar(20) DEFAULT NULL,
  `PaymentModeDetails` varchar(100) DEFAULT NULL,
  `TotalCharges` int(10) DEFAULT NULL,
  `NoOfDays` int(5) DEFAULT NULL,
  `TotalRoomCharges` int(10) DEFAULT NULL,
  `BillNo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `bill_room`
--

INSERT INTO `bill_room` (`DischargeID`, `BillingDate`, `RoomCharges`, `ServiceCharges`, `PaymentMode`, `PaymentModeDetails`, `TotalCharges`, `NoOfDays`, `TotalRoomCharges`, `BillNo`) VALUES
(4, '2021-02-20', 5000, 300, 'by Cash', 'Pago realizado con exito', 280300, 56, 280000, '1'),
(1, '2015-01-12', 5000, 500, 'by Cash', 'Payment done successfully!', 1485500, 297, 1485000, '120150112'),
(5, '2021-01-10', 2500, 750, 'by Cash', 'pago efectivo', 998250, 399, 997500, '2'),
(3, '2021-02-14', 10000, 20000, 'by Cash', 'Pago Realizado en efectivo', 50000, 3, 30000, '3'),
(12, '2020-02-12', 2500, 550, 'by Cash', 'Pago Exitoso', 23050, 9, 22500, '4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dischargepatient_room`
--

CREATE TABLE `dischargepatient_room` (
  `AdmitID` int(20) DEFAULT NULL,
  `DischargeDate` date DEFAULT NULL,
  `DP_Remarks` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dischargepatient_room`
--

INSERT INTO `dischargepatient_room` (`AdmitID`, `DischargeDate`, `DP_Remarks`) VALUES
(1, '2018-01-17', 'Patient recovered successfully!'),
(12, '2019-01-29', 'Patient is stable'),
(3, '2018-04-18', 'Patient operated'),
(5, '2020-02-23', 'Salio bien de toda las observaciones'),
(4, '2020-12-10', 'Toz Disminuyo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `DoctorID` int(10) NOT NULL,
  `DoctorName` varchar(20) DEFAULT NULL,
  `FatherName` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `ContacNo` varchar(11) DEFAULT NULL,
  `Qualifications` varchar(50) DEFAULT NULL,
  `Gender` varchar(1) DEFAULT NULL,
  `BloodGroup` varchar(5) DEFAULT NULL,
  `DateOfJoining` date DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`DoctorID`, `DoctorName`, `FatherName`, `Email`, `ContacNo`, `Qualifications`, `Gender`, `BloodGroup`, `DateOfJoining`, `Address`) VALUES
(1, 'Ajay Mishra', 'Deepak MIshra', 'ajay123@gmail.com ', '9880876532', 'MD,MBBS', 'M', 'A+', '2019-03-20', '108;Whitefield,Bangalor'),
(2, 'Anjali Chopra', 'Madan Chopra', 'anjalirox123@gmail.com', '9880756634', 'MD,MBBS', 'F', 'A-', '2020-01-02', '#111;Gayathri Temple;Whitefield;Bangalor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patientregistration`
--

CREATE TABLE `patientregistration` (
  `PatientID` int(10) NOT NULL,
  `PatientName` varchar(20) DEFAULT NULL,
  `FatherName` varchar(20) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `ContactNo` varchar(11) DEFAULT NULL,
  `Age` int(2) DEFAULT NULL,
  `Remarks` varchar(100) DEFAULT NULL,
  `Gen` varchar(1) DEFAULT NULL,
  `BG` varchar(3) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `patientregistration`
--

INSERT INTO `patientregistration` (`PatientID`, `PatientName`, `FatherName`, `Email`, `ContactNo`, `Age`, `Remarks`, `Gen`, `BG`, `Address`) VALUES
(1, 'Akash Agarwal', 'P.K Agarwal', 'agarwal.akash1997@gmail.com', '41162171', 18, 'Mild fever\n', 'M', 'B+', 'Beml layout'),
(2, 'Shankar Kumar', 'Rohan Kumar', 'shanks@gmail.com', '8095678932', 20, 'Patient admitted with severe bleeding and concussion', 'M', 'B+', '#205,ITPL road,Bangalore-560066'),
(3, 'Bhoomika Mishra', 'Kunal Mishra', 'bhoomika98@gmail.com', '8095678456', 25, 'fractured knee', 'F', 'AB+', '#105;Jyothi Nivas;Kundanahalli Gate;Bangalore-560066'),
(4, 'Brian', 'Miguel', 'muso@gmail.com', '34534534', 24, 'Gripa', 'M', 'O+', 'Bosques de Berbera 13'),
(5, 'Oswaldo', 'Miguel', 'muso@gmail.com', '34534534', 24, 'Toz', 'M', 'O+', 'Bosques de Berbera 13'),
(12, 'Katherine', 'Miguel Angel Murillo', 'kata@gmail.com', '41162191', 18, 'Vomito', 'F', 'O+', 'Bosques de Berbera 13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registration`
--

CREATE TABLE `registration` (
  `name` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email_id` varchar(30) DEFAULT NULL,
  `contact_no` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registration`
--

INSERT INTO `registration` (`name`, `user_name`, `password`, `email_id`, `contact_no`) VALUES
('Juan Carlos', 'ak', 'akshay', 'JK@gmail.com', 41162171),
('Oswaldo', 'akash', '12345', 'akash.agarwal', 41162171),
('akshay', 'aksh', 'aksh', 'aksh@gmail.com', 41162181),
('Miguel', 'Angel', '1234', 'Migue@gmail.com', 55464564),
('KoKe', 'anu', 'anu123', 'sampath.anurag@gmail.com', 80956835),
('Carlos', 'C54', 'ca1234', 'aksh@gmail.com', 41162181),
('Brian Murillo Salas', 'muso54', 'muso123', 'musovespo54@gmail.com', 2731053);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `RoomNo` int(5) NOT NULL,
  `RoomType` varchar(10) DEFAULT NULL,
  `RoomCharges` int(10) DEFAULT NULL,
  `RoomStatus` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`RoomNo`, `RoomType`, `RoomCharges`, `RoomStatus`) VALUES
(1, 'General', 5000, 'Booked'),
(2, 'General', 2500, 'Booked'),
(3, 'Deluxe', 10000, 'Booked'),
(4, 'Deluxe', 8500, 'Vacant'),
(5, 'Deluxe', 7000, 'Booked'),
(6, 'General', 2500, 'Vacant');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

CREATE TABLE `services` (
  `ServiceName` varchar(20) DEFAULT NULL,
  `ServiceDate` date DEFAULT NULL,
  `PatientID` int(10) DEFAULT NULL,
  `ServiceCharges` int(10) DEFAULT NULL,
  `ServiceID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`ServiceName`, `ServiceDate`, `PatientID`, `ServiceCharges`, `ServiceID`) VALUES
('Consulta', '2021-10-03', 4, 300, 10),
('Consulta General', '2014-09-22', 1, 500, 16),
('Consulta General', '2019-01-20', 12, 500, 17),
('Cirugía', '2018-04-15', 2, 15000, 18),
('Cirugía', '2018-04-15', 3, 20000, 19),
('Consulta General', '2021-01-02', 5, 200, 20),
('Consulta General', '2021-03-04', 12, 50, 21),
('Consulta General', '2021-03-04', 5, 550, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_name`, `password`) VALUES
('admin', 'admin'),
('akash', '123456'),
('aksh', 'akash'),
('anu', 'anu123'),
('avit', 'akash');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admitpatient_room`
--
ALTER TABLE `admitpatient_room`
  ADD PRIMARY KEY (`PatientID`),
  ADD KEY `RoomNo` (`RoomNo`),
  ADD KEY `DoctorID` (`DoctorID`);

--
-- Indices de la tabla `bill_room`
--
ALTER TABLE `bill_room`
  ADD PRIMARY KEY (`BillNo`);

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`DoctorID`);

--
-- Indices de la tabla `patientregistration`
--
ALTER TABLE `patientregistration`
  ADD PRIMARY KEY (`PatientID`);

--
-- Indices de la tabla `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`user_name`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`RoomNo`);

--
-- Indices de la tabla `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`ServiceID`),
  ADD KEY `PatientID` (`PatientID`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_name`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `services`
--
ALTER TABLE `services`
  MODIFY `ServiceID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admitpatient_room`
--
ALTER TABLE `admitpatient_room`
  ADD CONSTRAINT `admitpatient_room_ibfk_1` FOREIGN KEY (`RoomNo`) REFERENCES `room` (`RoomNo`),
  ADD CONSTRAINT `admitpatient_room_ibfk_2` FOREIGN KEY (`DoctorID`) REFERENCES `doctor` (`DoctorID`);

--
-- Filtros para la tabla `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `services_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `patientregistration` (`PatientID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
