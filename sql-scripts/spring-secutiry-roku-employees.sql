USE roku_directory;


DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `employee`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  UNIQUE KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
--
-- Default passwords here are: fun123
--

INSERT INTO `users`
VALUES
('mike','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('harry','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('anna','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

--
-- Table structure for table `employees`
--
CREATE TABLE `employee` (
	`id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(50) NULL DEFAULT NULL,
    `lastName` VARCHAR(50) NULL DEFAULT NULL,
    `email` VARCHAR(50) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `employee`
--
INSERT INTO `employee`
VALUES
	(1,'Mike','Seinfeld','mike@roku.com'),
	(2,'Aarry','David','harry@ruku.com'),
	(3,'Anna','Stern','anna@roku.com');



--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
('mike','ROLE_DOCTOR'),
('mike','ROLE_EMPLOYEE'),
('harry','ROLE_NURSE'),
('harry','ROLE_EMPLOYEE'),
('anna','ROLE_ADMIN'),
('anna','ROLE_EMPLOYEE');



DROP TABLE IF EXISTS `medical_history`;
DROP TABLE IF EXISTS `medication_history`;
DROP TABLE IF EXISTS `patients`;
DROP TABLE IF EXISTS `medications`;




CREATE TABLE `patients`(
	PatientId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL
);

CREATE TABLE `medical_history` (
	MedicalHistoryId INT PRIMARY KEY AUTO_INCREMENT,
    PatientId INT NOT NULL,
    Allergies TEXT,
    MedicalConditions TEXT,
    SurgicalHistory TEXT,
    MedicationHistroy TEXT,

    FOREIGN KEY (PatientId) REFERENCES patients(PatientId)
);

CREATE TABLE `medications`(
	MedicationId INT PRIMARY KEY AUTO_INCREMENT,
    MedicationName VARCHAR(100) NOT NULL
);

CREATE TABLE `medication_history`(
	MedicationHistoryID INT PRIMARY KEY AUTO_INCREMENT,
    PatientId INT NOT NULL,
    MedicationId INT NOT NULL,
    PrescriptionDate DATE NOT NULL,
    Dosage VARCHAR(50),
    ReasonForPres TEXT,
    PrescribingDoctor INT NOT NULL,
    Response TEXT,

    FOREIGN KEY (PatientId) REFERENCES patients(PatientId),
    FOREIGN KEY (MedicationId) REFERENCES medications (MedicationId),
    FOREIGN KEY (PrescribingDoctor) REFERENCES employee (id)
);

///

new

USE roku_directory;




--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  UNIQUE KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
--
-- Default passwords here are: fun123
--

INSERT INTO `users`
VALUES
('mike','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('harry','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('anna','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

--
-- Table structure for table `employees`
--
CREATE TABLE `employee` (
	`id` int NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NULL DEFAULT NULL,
    `last_name` VARCHAR(50) NULL DEFAULT NULL,
    `email` VARCHAR(50) NULL DEFAULT NULL,
     PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `employee`
--
INSERT INTO `employee`
VALUES
	(1,'Mike','Seinfeld','mike@roku.com'),
	(2,'Aarry','David','harry@ruku.com'),
	(3,'Anna','Stern','anna@roku.com');

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
('mike','ROLE_DOCTOR'),
('mike','ROLE_EMPLOYEE'),
('harry','ROLE_NURSE'),
('harry','ROLE_EMPLOYEE'),
('anna','ROLE_ADMIN'),
('anna','ROLE_EMPLOYEE');

CREATE TABLE `medications`(
	medication_id INT PRIMARY KEY AUTO_INCREMENT,
    medication_name VARCHAR(100) NOT NULL
);
INSERT INTO `medications`
VALUES
(20,'Ibuprofene'),
(21,'high blood pressure medicine'),
(22, 'diabetes medicine'),
(23, 'Paracetamol'),
(24, 'cough drop');


CREATE TABLE `patients`(
	patient_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL
);

INSERT INTO `patients`
VALUES
(10,"Harry", "Potter","harry@roku.com", "Godrics Hollow" ),
(11,"Jimmy", "McNulty","jimmy@rroku.com","Baltimore"),
(12,"Stringer","Bell","stringer@roku.com","Baltimore"),
(13,"James","Hall","hall@roku.com","Liverpool"),
(14,"Mary","Bloom","bloom@roku.com","London"),
(15,"Harold","McArdle","harold@roku.com","California");


CREATE TABLE `medical_history` (
	medical_history_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    allergies TEXT,
    medical_conditions TEXT,
    surgical_history TEXT,
    medication_histroy TEXT,

    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);



CREATE TABLE `medication_history`(
	medication_history_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    medication_id INT NOT NULL,
    prescription_date DATE NOT NULL,
    dosage VARCHAR(50),
    reason_for_pres TEXT,
    prescribing_doctor INT NOT NULL,
    response TEXT,

    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (medication_id) REFERENCES medications (medication_id),
    FOREIGN KEY (prescribing_doctor) REFERENCES employee (id)
);

INSERT INTO `medication_history`
VALUES
(630, 10, 20, '2023-10-19', '3 pills a day', 'had pain in his arm after the bones disappeard', 1, 'lessened the pain, did not grow arms back'),
(631,10,20,'2023-12-30','3 pills a day','had pain in his arm after the bones disappeard',1,'lessened the pain, did not grow arms back'),
(632,11,23,'2002-10-11', '4 pills a day','got shot in the arm',1,'lessened the pain'),
(633,12,24,'2002-08-07', '4ml in the mornings','patient complained about a sore cough',1,'aleviated cough symptoms'),
(624,13,21,'2001-05-04', '3 a day','patient presented with diabetes',1,'still monitoring'),
(625,14,22,'2001-05-04', 'once a day','paitent prersented with headaches',1,'monitoring'),
(616,15,20,'2019-01-03','once a day','patient presented with a bloody nose',1,'helped reliev pain');



CREATE TABLE `prescription`(
	prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    medication_name VARCHAR(50),
    prescription_date DATE,
    dosage VARCHAR(50),
    prescribing_doctor VARCHAR(50),
    fk_pharmacy_recipient INT UNIQUE ,

    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (fk_pharmacy_recipient) REFERENCES pharmacies(pharmacy_recipient_id)
);



INSERT INTO `prescription`
VALUES
(70,10,'Ibuprofene','2023-10-19','3 pills a day','Mike',400),
(71,11,'Ibuprofene','2023-12-30','3 pills a day','Mike',401),
(72,12,'cough drop','2002-08-07','4ml in the mornings','Mike',400),
(73,13,'high blood pressure medicine','2001-05-04','3 a day','Mike',402);


CREATE TABLE `pharmacies`(
	pharmacy_id INT PRIMARY KEY AUTO_INCREMENT,
	pharmacy_name VARCHAR(200),
    pharmacy_address VARCHAR(200),
    pharmacy_recipient_id INT UNIQUE
);


INSERT INTO `pharmacies`
VALUES
(80,'Hollands Pharmacy','Shaw Street',400),
(81,'Burkes Pharmacy','Henry Street',401),
(82,'Hennesseys Pharmacy','Dublin Street',402);

DROP TABLE `recipient`;
CREATE TABLE `recipient`(
	prescription_transaction_id INT PRIMARY KEY AUTO_INCREMENT,
	recipient_id INT,
    recipient_type VARCHAR(50),
    pharmacy_id INT,
    prescription_id int,

	FOREIGN KEY (pharmacy_id) REFERENCES pharmacies(pharmacy_id),
	FOREIGN KEY (prescription_id) REFERENCES prescription(prescription_id)

);