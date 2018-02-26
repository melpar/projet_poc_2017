-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 26 Février 2018 à 17:37
-- Version du serveur :  10.0.23-MariaDB
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `zfl3-zparlanme`
--

-- --------------------------------------------------------

--
-- Structure de la table `T_CONNEXION_ADMIN_COA`
--

CREATE TABLE `T_CONNEXION_ADMIN_COA` (
  `COA_idMail` varchar(100) NOT NULL,
  `COA_motDePasse` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_CONNEXION_ADMIN_COA`
--

INSERT INTO `T_CONNEXION_ADMIN_COA` (`COA_idMail`, `COA_motDePasse`) VALUES
('nicolas@gmail.com', 'fa7dibeyLuOt8z+NMZGK+w==');

-- --------------------------------------------------------

--
-- Structure de la table `T_CONNEXION_CON`
--

CREATE TABLE `T_CONNEXION_CON` (
  `CON_idMail` varchar(100) NOT NULL,
  `CON_motDePasse` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_CONNEXION_CON`
--

INSERT INTO `T_CONNEXION_CON` (`CON_idMail`, `CON_motDePasse`) VALUES
('nicolas@gmail.com', 'fa7dibeyLuOt8z+NMZGK+w=='),
('ok@gmail.com', '4Y/wzJXdfFYhIi43IeXSBg=='),
('robin.louarn@icloud.com', 'fDBeZEy1Nhth8BB6honfHw=='),
('testAjout@hotmail.fr', 'dOJ5/v8NWzsROH+5GmW0MQ=='),
('user1@gmail.com', 'KcXXHHiMmmvbzkH0SZIvvA==');

-- --------------------------------------------------------

--
-- Structure de la table `T_LISTEDIFFUSION_LIS`
--

CREATE TABLE `T_LISTEDIFFUSION_LIS` (
  `LIS_mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_LISTEDIFFUSION_LIS`
--

INSERT INTO `T_LISTEDIFFUSION_LIS` (`LIS_mail`) VALUES
('user1@gmail.com'),
('test'),
('nicolas@gmail.com'),
('nicolas@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `T_PERSONNE_PER`
--

CREATE TABLE `T_PERSONNE_PER` (
  `PER_id` int(11) NOT NULL,
  `PER_nom` varchar(100) NOT NULL,
  `PER_prenom` varchar(100) NOT NULL,
  `PER_risque` tinyint(1) NOT NULL,
  `PER_idMail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_PERSONNE_PER`
--

INSERT INTO `T_PERSONNE_PER` (`PER_id`, `PER_nom`, `PER_prenom`, `PER_risque`, `PER_idMail`) VALUES
(6, 'ok', 'Google', 0, 'testAjout@hotmail.fr'),
(7, 'ok', 'Google', 0, 'testAjout@hotmail.fr'),
(8, 'toto', 'tata', 0, 'user1@gmail.com'),
(10, 'nicolas', 'le guyader', 0, 'nicolas@gmail.com'),
(11, 'Robin', 'Louarn', 0, 'robin.louarn@icloud.com');

-- --------------------------------------------------------

--
-- Structure de la table `T_QUESTION_QUE`
--

CREATE TABLE `T_QUESTION_QUE` (
  `QUE_id` int(11) NOT NULL,
  `QUE_question` text NOT NULL,
  `QUE_typeMultiple` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_QUESTION_QUE`
--

INSERT INTO `T_QUESTION_QUE` (`QUE_id`, `QUE_question`, `QUE_typeMultiple`) VALUES
(4, 'Avez-vous a votre connaisance un risque agrave de sante?', 0),
(5, 'Vous voulez-vous changer d\'assurance?', 0),
(6, 'Voulez-vous faire un emprunt?', 0),
(7, 'Voulez-vous faire un emprunt?', 0),
(8, 'Voulez-vous faire un emprunt à moyen/long terme?', 0),
(9, 'Voulez-vous faire un emprunt à court terme?', 0),
(10, 'Avez-vous déjà eu des refus?', 0),
(11, 'Avez-vous déjà eu des refus?', 0),
(12, 'Etes-vous perdus dans vos démarches?', 0),
(13, 'Quel est votre plat favori ?', 1);

-- --------------------------------------------------------

--
-- Structure de la table `T_REPONSEPERSONNE_REP`
--

CREATE TABLE `T_REPONSEPERSONNE_REP` (
  `REP_id` int(11) NOT NULL,
  `REP_idQuestion` int(11) NOT NULL,
  `REP_idMail` varchar(100) NOT NULL,
  `REP_reponse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `T_REPONSEPERSONNE_REP`
--

INSERT INTO `T_REPONSEPERSONNE_REP` (`REP_id`, `REP_idQuestion`, `REP_idMail`, `REP_reponse`) VALUES
(2, 4, 'nicolas@gmail.com', 'oui'),
(3, 8, 'nicolas@gmail.com', 'non'),
(54, 5, 'nicolas@gmail.com', 'oui'),
(55, 5, 'nicolas@gmail.com', 'oui'),
(56, 5, 'nicolas@gmail.com', 'oui'),
(57, 5, 'nicolas@gmail.com', 'oui'),
(58, 5, 'nicolas@gmail.com', 'oui'),
(59, 5, 'nicolas@gmail.com', 'oui'),
(60, 5, 'nicolas@gmail.com', 'oui'),
(61, 5, 'nicolas@gmail.com', 'oui'),
(62, 5, 'nicolas@gmail.com', 'oui'),
(63, 5, 'nicolas@gmail.com', 'oui'),
(84, 6, 'nicolas@gmail.com', 'non'),
(85, 9, 'nicolas@gmail.com', 'non'),
(86, 7, 'nicolas@gmail.com', 'non'),
(87, 11, 'nicolas@gmail.com', 'non'),
(89, 5, 'nicolas@gmail.com', 'oui'),
(90, 8, 'nicolas@gmail.com', 'non'),
(91, 10, 'nicolas@gmail.com', 'non'),
(92, 12, 'nicolas@gmail.com', 'non'),
(93, 4, 'nicolas@gmail.com', 'oui'),
(94, 9, 'nicolas@gmail.com', 'non'),
(95, 10, 'nicolas@gmail.com', 'non'),
(96, 7, 'nicolas@gmail.com', 'non'),
(97, 11, 'nicolas@gmail.com', 'non'),
(98, 6, 'nicolas@gmail.com', 'non'),
(99, 5, 'nicolas@gmail.com', 'oui'),
(100, 8, 'nicolas@gmail.com', 'non'),
(101, 12, 'nicolas@gmail.com', 'non'),
(103, 4, 'nicolas@gmail.com', 'oui');

-- --------------------------------------------------------

--
-- Structure de la table `T_REPONSEQUESTION_REQ`
--

CREATE TABLE `T_REPONSEQUESTION_REQ` (
  `REQ_id` int(11) NOT NULL,
  `REQ_idQuestion` int(11) NOT NULL,
  `REQ_reponse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `T_CONNEXION_ADMIN_COA`
--
ALTER TABLE `T_CONNEXION_ADMIN_COA`
  ADD PRIMARY KEY (`COA_idMail`);

--
-- Index pour la table `T_CONNEXION_CON`
--
ALTER TABLE `T_CONNEXION_CON`
  ADD PRIMARY KEY (`CON_idMail`);

--
-- Index pour la table `T_PERSONNE_PER`
--
ALTER TABLE `T_PERSONNE_PER`
  ADD PRIMARY KEY (`PER_id`),
  ADD KEY `PER_idMail` (`PER_idMail`);

--
-- Index pour la table `T_QUESTION_QUE`
--
ALTER TABLE `T_QUESTION_QUE`
  ADD PRIMARY KEY (`QUE_id`);

--
-- Index pour la table `T_REPONSEPERSONNE_REP`
--
ALTER TABLE `T_REPONSEPERSONNE_REP`
  ADD PRIMARY KEY (`REP_id`),
  ADD KEY `REP_idQuestion` (`REP_idQuestion`),
  ADD KEY `REP_idMail` (`REP_idMail`);

--
-- Index pour la table `T_REPONSEQUESTION_REQ`
--
ALTER TABLE `T_REPONSEQUESTION_REQ`
  ADD PRIMARY KEY (`REQ_id`),
  ADD KEY `REQ_idQuestion` (`REQ_idQuestion`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `T_PERSONNE_PER`
--
ALTER TABLE `T_PERSONNE_PER`
  MODIFY `PER_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `T_QUESTION_QUE`
--
ALTER TABLE `T_QUESTION_QUE`
  MODIFY `QUE_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `T_REPONSEPERSONNE_REP`
--
ALTER TABLE `T_REPONSEPERSONNE_REP`
  MODIFY `REP_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;
--
-- AUTO_INCREMENT pour la table `T_REPONSEQUESTION_REQ`
--
ALTER TABLE `T_REPONSEQUESTION_REQ`
  MODIFY `REQ_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `T_PERSONNE_PER`
--
ALTER TABLE `T_PERSONNE_PER`
  ADD CONSTRAINT `T_PERSONNE_PER_ibfk_1` FOREIGN KEY (`PER_idMail`) REFERENCES `T_CONNEXION_CON` (`CON_idMail`);

--
-- Contraintes pour la table `T_REPONSEPERSONNE_REP`
--
ALTER TABLE `T_REPONSEPERSONNE_REP`
  ADD CONSTRAINT `T_REPONSEPERSONNE_REP_ibfk_1` FOREIGN KEY (`REP_idQuestion`) REFERENCES `T_QUESTION_QUE` (`QUE_id`),
  ADD CONSTRAINT `T_REPONSEPERSONNE_REP_ibfk_2` FOREIGN KEY (`REP_idMail`) REFERENCES `T_CONNEXION_CON` (`CON_idMail`);

--
-- Contraintes pour la table `T_REPONSEQUESTION_REQ`
--
ALTER TABLE `T_REPONSEQUESTION_REQ`
  ADD CONSTRAINT `T_REPONSEQUESTION_REQ_ibfk_1` FOREIGN KEY (`REQ_idQuestion`) REFERENCES `T_QUESTION_QUE` (`QUE_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
