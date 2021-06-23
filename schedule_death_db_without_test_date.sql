-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июн 23 2021 г., 11:14
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `schedule_death_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `discipline`
--

CREATE TABLE `discipline` (
  `id_Discipline` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `Lecturer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `id_Groups` int(11) NOT NULL,
  `short_name` varchar(12) COLLATE utf8_bin NOT NULL,
  `long_name` varchar(89) COLLATE utf8_bin NOT NULL,
  `max_hours` int(11) NOT NULL,
  `group_status` varchar(25) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `kabinet`
--

CREATE TABLE `kabinet` (
  `id_Kabinet` int(11) NOT NULL,
  `number_of_kabinet` int(11) NOT NULL,
  `flag_computers` tinyint(1) DEFAULT NULL,
  `flag_docs` tinyint(1) DEFAULT NULL,
  `flag_projector` tinyint(1) DEFAULT NULL,
  `flag_sportzal` tinyint(1) DEFAULT NULL,
  `flag_master` tinyint(1) DEFAULT NULL,
  `kabinet_status` varchar(25) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `lecturer`
--

CREATE TABLE `lecturer` (
  `id_Lecturer` int(11) NOT NULL,
  `surname` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `second_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `status` varchar(25) COLLATE utf8_bin NOT NULL,
  `priority_kabinet` int(11) NOT NULL,
  `need_in_kabinet` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `need_in_kab_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `nag`
--

CREATE TABLE `nag` (
  `id_Discipline` int(11) NOT NULL,
  `id_Groups` int(11) NOT NULL,
  `hours_of_top_week` int(11) NOT NULL,
  `hours_of_down_week` int(11) NOT NULL,
  `hours_count_t` int(11) NOT NULL,
  `hours_count_d` int(11) NOT NULL,
  `hours_count_s` int(11) NOT NULL,
  `st_practic` tinyint(1) NOT NULL,
  `pr_practic` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `schedule_change`
--

CREATE TABLE `schedule_change` (
  `Group` int(11) NOT NULL,
  `Displine` int(11) NOT NULL,
  `Lecturer` int(11) NOT NULL,
  `hours_count_s` int(11) NOT NULL,
  `hours_count_t` int(11) NOT NULL,
  `hours_count_d` int(11) NOT NULL,
  `hours_t` int(11) NOT NULL,
  `hours_d` int(11) NOT NULL,
  `hours_s` int(11) NOT NULL,
  `debt` int(11) NOT NULL,
  `hours_today` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `schedule_change_ht`
--

CREATE TABLE `schedule_change_ht` (
  `id_Group` int(11) NOT NULL,
  `id_Discipline` int(11) NOT NULL,
  `id_Lecturer` int(11) NOT NULL,
  `hours_today` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `schedule_change_true`
--

CREATE TABLE `schedule_change_true` (
  `Group` varchar(100) COLLATE utf8_bin NOT NULL,
  `Disp` varchar(100) COLLATE utf8_bin NOT NULL,
  `Lecturer` varchar(100) COLLATE utf8_bin NOT NULL,
  `para` int(11) NOT NULL,
  `kabinet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `schedule_need_change`
--

CREATE TABLE `schedule_need_change` (
  `day` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `day_n` int(11) NOT NULL,
  `para` int(11) NOT NULL,
  `Group` int(11) NOT NULL,
  `Lecturer` int(11) NOT NULL,
  `Displine` int(11) NOT NULL,
  `week` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `schedule_true`
--

CREATE TABLE `schedule_true` (
  `day` varchar(11) COLLATE utf8_bin NOT NULL,
  `day_n` int(11) NOT NULL,
  `para` int(11) NOT NULL,
  `Group` int(11) NOT NULL,
  `Lecturer` int(11) NOT NULL,
  `Displine` int(11) NOT NULL,
  `week` varchar(8) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `sch_kab`
--

CREATE TABLE `sch_kab` (
  `id_Group` int(11) NOT NULL,
  `id_Discipline` int(11) NOT NULL,
  `id_Lecturer` int(11) NOT NULL,
  `para` int(11) NOT NULL,
  `kabinet` int(11) NOT NULL,
  `Have` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `storage`
--

CREATE TABLE `storage` (
  `Group` int(11) NOT NULL,
  `Disciplane` int(11) NOT NULL,
  `Lecturer` int(11) NOT NULL,
  `hours_t` int(11) NOT NULL,
  `hours_d` int(11) NOT NULL,
  `debt` int(11) NOT NULL,
  `hours_s` int(11) NOT NULL,
  `st_practic` tinyint(1) NOT NULL,
  `pr_practic` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Структура таблицы `testchange1`
--

CREATE TABLE `testchange1` (
  `Group` int(11) NOT NULL,
  `Displine` int(11) NOT NULL,
  `Lecturer` int(11) NOT NULL,
  `hours_count_s` int(11) NOT NULL,
  `debt` int(11) NOT NULL,
  `hours_today` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`id_Discipline`),
  ADD KEY `Lecturer` (`Lecturer`);

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id_Groups`);

--
-- Индексы таблицы `kabinet`
--
ALTER TABLE `kabinet`
  ADD PRIMARY KEY (`id_Kabinet`),
  ADD UNIQUE KEY `number_of_kabinet` (`number_of_kabinet`);

--
-- Индексы таблицы `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`id_Lecturer`),
  ADD KEY `priority_kabinet` (`priority_kabinet`);

--
-- Индексы таблицы `nag`
--
ALTER TABLE `nag`
  ADD PRIMARY KEY (`id_Discipline`,`id_Groups`),
  ADD KEY `1234` (`id_Groups`),
  ADD KEY `hours_of_top_week` (`hours_of_top_week`,`hours_of_down_week`),
  ADD KEY `hours_of_down_week` (`hours_of_down_week`),
  ADD KEY `hours_count_s` (`hours_count_s`);

--
-- Индексы таблицы `schedule_change`
--
ALTER TABLE `schedule_change`
  ADD KEY `schedule_change_ibfk_2` (`Displine`),
  ADD KEY `schedule_change_ibfk_3` (`Lecturer`),
  ADD KEY `hours_count_s` (`hours_count_s`,`hours_count_t`,`hours_count_d`),
  ADD KEY `debt` (`debt`),
  ADD KEY `schedule_change_ibfk_7` (`hours_count_t`),
  ADD KEY `schedule_change_ibfk_8` (`hours_count_d`),
  ADD KEY `Group` (`Group`,`Displine`,`Lecturer`) USING BTREE,
  ADD KEY `hours_t` (`hours_t`,`hours_d`,`hours_s`);

--
-- Индексы таблицы `schedule_change_ht`
--
ALTER TABLE `schedule_change_ht`
  ADD KEY `id_Group` (`id_Group`,`id_Discipline`,`id_Lecturer`),
  ADD KEY `id_Discipline` (`id_Discipline`),
  ADD KEY `id_Lecturer` (`id_Lecturer`);

--
-- Индексы таблицы `schedule_need_change`
--
ALTER TABLE `schedule_need_change`
  ADD KEY `Group` (`Group`),
  ADD KEY `Displine` (`Displine`),
  ADD KEY `Lecturer` (`Lecturer`);

--
-- Индексы таблицы `schedule_true`
--
ALTER TABLE `schedule_true`
  ADD KEY `Group` (`Group`,`Lecturer`,`Displine`),
  ADD KEY `Displine` (`Displine`),
  ADD KEY `Lecturer` (`Lecturer`);

--
-- Индексы таблицы `sch_kab`
--
ALTER TABLE `sch_kab`
  ADD KEY `id_Group` (`id_Group`),
  ADD KEY `id_Lecturer` (`id_Lecturer`),
  ADD KEY `id_Discipline` (`id_Discipline`);

--
-- Индексы таблицы `storage`
--
ALTER TABLE `storage`
  ADD KEY `Group` (`Group`,`Disciplane`,`Lecturer`),
  ADD KEY `Lecturer` (`Lecturer`),
  ADD KEY `hours_t` (`hours_t`),
  ADD KEY `hours_d` (`hours_d`),
  ADD KEY `debt` (`debt`),
  ADD KEY `hours_s` (`hours_s`),
  ADD KEY `storage_ibfk_2` (`Disciplane`);

--
-- Индексы таблицы `testchange1`
--
ALTER TABLE `testchange1`
  ADD KEY `Group` (`Group`,`Displine`,`Lecturer`),
  ADD KEY `Displine` (`Displine`),
  ADD KEY `Lecturer` (`Lecturer`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `discipline`
--
ALTER TABLE `discipline`
  MODIFY `id_Discipline` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `groups`
--
ALTER TABLE `groups`
  MODIFY `id_Groups` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `kabinet`
--
ALTER TABLE `kabinet`
  MODIFY `id_Kabinet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `id_Lecturer` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `discipline`
--
ALTER TABLE `discipline`
  ADD CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `lecturer`
--
ALTER TABLE `lecturer`
  ADD CONSTRAINT `lecturer_ibfk_1` FOREIGN KEY (`priority_kabinet`) REFERENCES `kabinet` (`number_of_kabinet`);

--
-- Ограничения внешнего ключа таблицы `nag`
--
ALTER TABLE `nag`
  ADD CONSTRAINT `123` FOREIGN KEY (`id_Discipline`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `1234` FOREIGN KEY (`id_Groups`) REFERENCES `groups` (`id_Groups`);

--
-- Ограничения внешнего ключа таблицы `schedule_change`
--
ALTER TABLE `schedule_change`
  ADD CONSTRAINT `schedule_change_ibfk_1` FOREIGN KEY (`Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `schedule_change_ibfk_2` FOREIGN KEY (`Displine`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `schedule_change_ibfk_3` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `schedule_change_ht`
--
ALTER TABLE `schedule_change_ht`
  ADD CONSTRAINT `schedule_change_ht_ibfk_1` FOREIGN KEY (`id_Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `schedule_change_ht_ibfk_2` FOREIGN KEY (`id_Discipline`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `schedule_change_ht_ibfk_3` FOREIGN KEY (`id_Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `schedule_need_change`
--
ALTER TABLE `schedule_need_change`
  ADD CONSTRAINT `schedule_need_change_ibfk_1` FOREIGN KEY (`Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `schedule_need_change_ibfk_2` FOREIGN KEY (`Displine`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `schedule_need_change_ibfk_3` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `schedule_true`
--
ALTER TABLE `schedule_true`
  ADD CONSTRAINT `schedule_true_ibfk_1` FOREIGN KEY (`Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `schedule_true_ibfk_2` FOREIGN KEY (`Displine`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `schedule_true_ibfk_3` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `sch_kab`
--
ALTER TABLE `sch_kab`
  ADD CONSTRAINT `sch_kab_ibfk_1` FOREIGN KEY (`id_Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `sch_kab_ibfk_2` FOREIGN KEY (`id_Lecturer`) REFERENCES `lecturer` (`id_Lecturer`),
  ADD CONSTRAINT `sch_kab_ibfk_3` FOREIGN KEY (`id_Discipline`) REFERENCES `discipline` (`id_Discipline`);

--
-- Ограничения внешнего ключа таблицы `storage`
--
ALTER TABLE `storage`
  ADD CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `storage_ibfk_2` FOREIGN KEY (`Disciplane`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `storage_ibfk_3` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);

--
-- Ограничения внешнего ключа таблицы `testchange1`
--
ALTER TABLE `testchange1`
  ADD CONSTRAINT `testchange1_ibfk_1` FOREIGN KEY (`Group`) REFERENCES `groups` (`id_Groups`),
  ADD CONSTRAINT `testchange1_ibfk_2` FOREIGN KEY (`Displine`) REFERENCES `discipline` (`id_Discipline`),
  ADD CONSTRAINT `testchange1_ibfk_3` FOREIGN KEY (`Lecturer`) REFERENCES `lecturer` (`id_Lecturer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
