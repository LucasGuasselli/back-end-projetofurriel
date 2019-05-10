-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 08-Maio-2019 às 23:34
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projeto_furriel`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `auxilio_transporte`
--

CREATE TABLE `auxilio_transporte` (
  `id` int(11) NOT NULL,
  `valor_diarioat` double NOT NULL,
  `valor_totalat` double NOT NULL,
  `militar_preccp` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `auxilio_transporte`
--

INSERT INTO `auxilio_transporte` (`id`, `valor_diarioat`, `valor_totalat`, `militar_preccp`) VALUES
(1, 11, 220, 123456789),
(2, 15, 298, 2456);

-- --------------------------------------------------------

--
-- Estrutura da tabela `conducao`
--

CREATE TABLE `conducao` (
  `id` int(11) NOT NULL,
  `itinerario` varchar(255) DEFAULT NULL,
  `nome_empresa` varchar(255) DEFAULT NULL,
  `tipo_de_transporte` varchar(255) DEFAULT NULL,
  `valor` double NOT NULL,
  `auxilio_transporte_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `conducao`
--

INSERT INTO `conducao` (`id`, `itinerario`, `nome_empresa`, `tipo_de_transporte`, `valor`, `auxilio_transporte_id`) VALUES
(1, 'bairro-centro', 'Viva-Sul', 'Onibus', 4.7, 1),
(2, 'centro-bairro', 'Viva-Sul', 'Onibus', 4.7, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `militar`
--

CREATE TABLE `militar` (
  `preccp` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `posto_graduacao_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `militar`
--

INSERT INTO `militar` (`preccp`, `nome`, `posto_graduacao_id`) VALUES
(123456789, 'Lucas', 3),
(2456, 'Grillo', 5),
(258, 'Mauro', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `posto_graduacao`
--

CREATE TABLE `posto_graduacao` (
  `id` int(11) NOT NULL,
  `cota_parte` double NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `soldo` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `posto_graduacao`
--

INSERT INTO `posto_graduacao` (`id`, `cota_parte`, `nome`, `soldo`) VALUES
(1, 37.576, 'SoldadoEV', 854),
(2, 65.032, 'SoldadoEP', 1758),
(3, 107.756, 'Cabo', 2449),
(4, 157.696, '3º Sargento', 3584),
(5, 195.58, '2º Sargento', 4445),
(6, 224.84, '1º Sargento', 5110),
(7, 256.044, 'Subtenente', 5751),
(8, 397.5, 'Aspirante', 6625),
(9, 311.608, '2º Tenente', 7082),
(10, 467.76, '1º Tenente', 7796),
(11, 511.02, 'Capitão', 8517),
(12, 628.32, 'Major', 10472);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auxilio_transporte`
--
ALTER TABLE `auxilio_transporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk0yvu6v6myow2ojme32qqpyqm` (`militar_preccp`);

--
-- Indexes for table `conducao`
--
ALTER TABLE `conducao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp9ey2jkuj253kp82xjnc87moq` (`auxilio_transporte_id`);

--
-- Indexes for table `militar`
--
ALTER TABLE `militar`
  ADD PRIMARY KEY (`preccp`),
  ADD KEY `FK3sxi3b0pirv2wfx4gk9aqjfo9` (`posto_graduacao_id`);

--
-- Indexes for table `posto_graduacao`
--
ALTER TABLE `posto_graduacao`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auxilio_transporte`
--
ALTER TABLE `auxilio_transporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `conducao`
--
ALTER TABLE `conducao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `posto_graduacao`
--
ALTER TABLE `posto_graduacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
