-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июн 23 2021 г., 11:15
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

--
-- Дамп данных таблицы `discipline`
--

INSERT INTO `discipline` (`id_Discipline`, `name`, `Lecturer`) VALUES
(1, 'Иностранный язык А', 2),
(2, 'Физическая культура Ж', 3),
(3, 'Русский язык Г', 4),
(4, 'Математика К', 5),
(5, 'История П', 6),
(6, 'Основы Безопасности Жизнедеятельности Н', 7),
(7, 'Информатика Ч', 8),
(8, 'Физика Е', 9),
(9, 'Химия О', 10),
(10, 'Обществознание Б', 11),
(11, 'География А', 12),
(12, 'Техника профессиональной деятельности Ф', 13),
(13, 'Иностранный язык Д', 14),
(14, 'Русский язык С', 15),
(15, 'Математика Т', 16),
(16, 'Информатика В', 17),
(17, 'Математика В', 17),
(18, 'Математика З', 19),
(19, 'Производственная практика ПП07 П', 20),
(20, 'Учебная практика УП06 Ч', 8);

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

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`id_Groups`, `short_name`, `long_name`, `max_hours`, `group_status`) VALUES
(1, '1РА1уп', 'Радиоаппаратостроение 1 курс', 36, 'Учится'),
(2, '1РА2', 'Радиоаппаратостроение 1 курс', 36, 'Учится'),
(3, '1МРЭП1', 'МРЭП', 36, 'Учится'),
(4, '1КСК1', '1КСК1', 36, 'Учится'),
(5, '1ИСиП1', '1ИСиП-1', 36, 'Учится'),
(6, '1ИБ1', 'Информационная безопасность', 36, 'Учится');

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

--
-- Дамп данных таблицы `kabinet`
--

INSERT INTO `kabinet` (`id_Kabinet`, `number_of_kabinet`, `flag_computers`, `flag_docs`, `flag_projector`, `flag_sportzal`, `flag_master`, `kabinet_status`) VALUES
(1, 101, 1, 1, 1, 0, 0, 'Работает'),
(2, 1, 0, 0, 0, 1, 0, 'Работает'),
(3, 102, 1, 1, 0, 0, 0, 'Работает'),
(4, 103, 0, 1, 0, 0, 0, 'Работает'),
(5, 104, 1, 0, 0, 0, 0, 'Работает'),
(6, 105, 0, 0, 0, 0, 0, 'Работает'),
(7, 106, 0, 1, 0, 0, 0, 'Работает'),
(8, 107, 0, 1, 1, 0, 0, 'Работает'),
(9, 108, 1, 1, 1, 0, 0, 'Работает'),
(10, 109, 1, 1, 1, 0, 0, 'Работает'),
(11, 110, 0, 1, 1, 0, 0, 'Работает'),
(12, 112, 0, 1, 1, 0, 0, 'Работает'),
(13, -1, 0, 0, 0, 0, 0, 'Работает');

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

--
-- Дамп данных таблицы `lecturer`
--

INSERT INTO `lecturer` (`id_Lecturer`, `surname`, `name`, `second_name`, `status`, `priority_kabinet`, `need_in_kabinet`, `need_in_kab_id`) VALUES
(2, 'Апетян', 'Ермония', 'Богдасаровна', 'Работает', 102, 'Доска', 8),
(3, 'Жиленкова', 'Ирина', 'Борисовна', 'Работает', 1, 'Спортзал', 2),
(4, 'Громова', 'Наталья', 'Александровна', 'Работает', 112, 'Без предпочтени', 0),
(5, 'Кирсанова', 'Светлана', 'Владимировна', 'В отпуске', 108, 'Доска', 8),
(6, 'Преси', 'Игорь', 'Отсутсвует', 'Работает', 107, 'Без предпочтений', 0),
(7, 'Наумова', 'Ирина', 'Викторовна', 'Работает', 106, 'Доска, Проектор', 4),
(8, 'Чепыжова', 'Наталья', 'Рэмовна', 'Работает', 108, 'Компьютеры', 6),
(9, 'Ергакова', 'Татьяна', 'Евгеньевна', 'Работает', 112, 'Доска', 8),
(10, 'Овчинникова', 'Лидия', 'Анатольевна', 'Работает', 101, 'Доска', 8),
(11, 'Большаков', 'Алексей', 'Евгеньевич', 'Работает', 109, 'Доска, Проектор', 4),
(12, 'Астахов', 'Владимир', 'Олегович', 'Работает', 107, 'Доска', 8),
(13, 'Фарафонова', 'Ирина', 'Германовна', 'Работает', 101, 'Без предпочтений', 0),
(14, 'Деревянкина', 'Светлана', 'Николаевна', 'Работает', 107, 'Доска', 8),
(15, 'Смолина', 'Елена', 'Юрьевна', 'Работает', 103, 'Доска', 8),
(16, 'Толок', 'Игнатий', 'Федорович', 'Работает', 105, 'Без предпочтений', 0),
(17, 'Виклянчук', 'Елена', 'Анатольевна', 'Работает', 101, 'Компьютеры', 6),
(18, 'Седова', 'Екатерина', 'Анатольевна', 'Работает', 103, 'Доска', 8),
(19, 'Золина', 'Елена', 'Владимировна', 'Работает', 110, 'Доска', 8),
(20, 'Производственная', 'Практика', 'Практика', 'Работает', -1, 'Без предпочтений', 0);

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

--
-- Дамп данных таблицы `nag`
--

INSERT INTO `nag` (`id_Discipline`, `id_Groups`, `hours_of_top_week`, `hours_of_down_week`, `hours_count_t`, `hours_count_d`, `hours_count_s`, `st_practic`, `pr_practic`) VALUES
(1, 1, 2, 1, 2, 1, 50, 0, 0),
(1, 3, 2, 1, 2, 1, 50, 0, 0),
(1, 5, 2, 1, 2, 1, 50, 0, 0),
(2, 1, 2, 1, 2, 1, 50, 0, 0),
(2, 2, 2, 1, 2, 1, 50, 0, 0),
(2, 3, 2, 1, 2, 1, 50, 0, 0),
(2, 4, 2, 1, 2, 1, 50, 0, 0),
(2, 5, 2, 1, 2, 1, 50, 0, 0),
(3, 1, 2, 3, 2, 3, 71, 0, 0),
(3, 3, 2, 3, 2, 3, 78, 0, 0),
(3, 5, 2, 3, 2, 3, 78, 0, 0),
(4, 1, 3, 3, 3, 3, 94, 0, 0),
(4, 4, 3, 3, 3, 3, 94, 0, 0),
(4, 5, 3, 3, 3, 3, 94, 0, 0),
(5, 1, 1, 2, 1, 2, 44, 0, 0),
(5, 2, 1, 2, 1, 2, 44, 0, 0),
(5, 3, 1, 2, 1, 2, 44, 0, 0),
(5, 4, 1, 2, 1, 2, 44, 0, 0),
(5, 5, 1, 2, 1, 2, 44, 0, 0),
(6, 1, 1, 1, 1, 1, 34, 0, 0),
(6, 2, 1, 1, 1, 1, 34, 0, 0),
(6, 3, 1, 1, 1, 1, 34, 0, 0),
(6, 4, 1, 1, 1, 1, 34, 0, 0),
(6, 5, 1, 1, 1, 1, 39, 0, 0),
(7, 1, 1, 1, 1, 1, 34, 0, 0),
(7, 4, 1, 1, 1, 1, 34, 0, 0),
(7, 5, 1, 1, 1, 1, 34, 0, 0),
(8, 1, 2, 1, 2, 1, 53, 0, 0),
(8, 2, 2, 1, 2, 1, 53, 0, 0),
(8, 3, 2, 1, 2, 1, 53, 0, 0),
(8, 4, 2, 1, 2, 1, 53, 0, 0),
(8, 5, 2, 1, 2, 1, 53, 0, 0),
(9, 1, 1, 1, 1, 1, 34, 0, 0),
(9, 2, 1, 1, 1, 1, 34, 0, 0),
(9, 3, 1, 1, 1, 1, 34, 0, 0),
(9, 4, 1, 1, 1, 1, 34, 0, 0),
(9, 5, 1, 1, 1, 1, 34, 0, 0),
(10, 1, 1, 2, 1, 2, 48, 0, 0),
(10, 2, 1, 2, 1, 2, 48, 0, 0),
(10, 3, 1, 2, 1, 2, 48, 0, 0),
(10, 4, 1, 2, 1, 2, 48, 0, 0),
(10, 5, 1, 2, 1, 2, 48, 0, 0),
(11, 1, 1, 1, 1, 1, 36, 0, 0),
(11, 2, 1, 1, 1, 1, 36, 0, 0),
(11, 3, 1, 1, 1, 1, 36, 0, 0),
(11, 4, 1, 1, 1, 1, 36, 0, 0),
(11, 5, 1, 1, 1, 1, 36, 0, 0),
(12, 1, 1, 1, 1, 1, 39, 0, 0),
(12, 2, 1, 1, 1, 1, 39, 0, 0),
(12, 3, 1, 1, 1, 1, 39, 0, 0),
(12, 4, 1, 1, 1, 1, 39, 0, 0),
(13, 2, 2, 1, 2, 1, 50, 0, 0),
(13, 4, 2, 1, 2, 1, 50, 0, 0),
(14, 2, 2, 3, 2, 3, 78, 0, 0),
(14, 4, 2, 3, 2, 3, 78, 0, 0),
(15, 2, 3, 3, 3, 3, 94, 0, 0),
(16, 2, 1, 1, 1, 1, 34, 0, 0),
(16, 3, 1, 1, 1, 1, 34, 0, 0),
(17, 3, 3, 3, 3, 3, 94, 0, 0),
(19, 1, 18, 18, 18, 18, 9, 0, 1),
(20, 1, 18, 18, 18, 18, 54, 1, 0);

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

--
-- Дамп данных таблицы `schedule_change`
--

INSERT INTO `schedule_change` (`Group`, `Displine`, `Lecturer`, `hours_count_s`, `hours_count_t`, `hours_count_d`, `hours_t`, `hours_d`, `hours_s`, `debt`, `hours_today`) VALUES
(1, 2, 3, 25, 2, 1, 2, 1, 50, 0, 1),
(1, 4, 5, 47, 3, 3, 3, 3, 94, 0, 1),
(2, 5, 6, 22, 1, 2, 1, 2, 44, 0, 1),
(2, 9, 10, 17, 1, 1, 1, 1, 34, 0, 1),
(2, 12, 13, 20, 1, 1, 1, 1, 39, 0, 1),
(3, 8, 9, 27, 2, 1, 2, 1, 53, 0, 1),
(3, 2, 3, 25, 2, 1, 2, 1, 50, 0, 1),
(3, 16, 17, 17, 1, 1, 1, 1, 34, 0, 1),
(4, 4, 5, 94, 3, 3, 3, 3, 94, 0, 1),
(4, 13, 14, 50, 2, 1, 2, 1, 50, 0, 1),
(4, 11, 12, 36, 1, 1, 1, 1, 36, 0, 1),
(5, 10, 11, 48, 1, 2, 1, 2, 48, 0, 1),
(5, 8, 9, 53, 2, 1, 2, 1, 53, 0, 1),
(5, 9, 10, 34, 1, 1, 1, 1, 34, 0, 1);

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

--
-- Дамп данных таблицы `schedule_change_ht`
--

INSERT INTO `schedule_change_ht` (`id_Group`, `id_Discipline`, `id_Lecturer`, `hours_today`) VALUES
(1, 3, 4, 1),
(1, 8, 9, 1),
(1, 9, 10, 2),
(2, 15, 16, 2),
(2, 16, 17, 1),
(3, 5, 6, 1),
(3, 10, 11, 1),
(3, 17, 17, 1),
(4, 2, 3, 2),
(4, 7, 8, 1),
(4, 8, 9, 1),
(5, 2, 3, 1),
(5, 3, 4, 2),
(5, 7, 8, 1);

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

--
-- Дамп данных таблицы `schedule_change_true`
--

INSERT INTO `schedule_change_true` (`Group`, `Disp`, `Lecturer`, `para`, `kabinet`) VALUES
('1РА1уп', 'Химия', 'Овчинникова Л. А.', 1, 101),
('1РА2', 'Математика', 'Толок И. Ф.', 1, 105),
('1МРЭП1', 'Обществознание', 'Большаков А. Е.', 1, 109),
('1КСК1', 'Физика', 'Ергакова Т. Е.', 1, 112),
('1ИСиП1', 'Русский язык', 'Громова Н. А.', 1, 108),
('1РА2', 'Математика', 'Толок И. Ф.', 2, 105),
('1МРЭП1', 'Математика', 'Виклянчук Е. А.', 2, 101),
('1РА1уп', 'Физика', 'Ергакова Т. Е.', 2, 112),
('1ИСиП1', 'Русский язык', 'Громова Н. А.', 2, 109),
('1КСК1', 'Физическая культура', 'Жиленкова И. Б.', 2, 1),
('1РА1уп', 'Русский язык', 'Громова Н. А.', 3, 112),
('1РА2', 'Информатика', 'Виклянчук Е. А.', 3, 101),
('1КСК1', 'Информатика', 'Чепыжова Н. Р.', 3, 108),
('1ИСиП1', 'Физическая культура', 'Жиленкова И. Б.', 3, 1),
('1МРЭП1', 'История', 'Преси И. О.', 3, 107),
('1РА1уп', 'Химия', 'Овчинникова Л. А.', 4, 101),
('1ИСиП1', 'Информатика', 'Чепыжова Н. Р.', 4, 108),
('1КСК1', 'Физическая культура', 'Жиленкова И. Б.', 4, 1);

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

--
-- Дамп данных таблицы `schedule_need_change`
--

INSERT INTO `schedule_need_change` (`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES
('Вторник', 1, 0, 4, 3, 2, 'верхняя'),
('Вторник', 1, 0, 1, 4, 3, 'верхняя'),
('Вторник', 1, 0, 5, 5, 4, 'верхняя'),
('Вторник', 1, 0, 3, 11, 10, 'верхняя'),
('Вторник', 1, 0, 2, 17, 16, 'верхняя'),
('Вторник', 1, 1, 4, 3, 2, 'верхняя'),
('Вторник', 1, 1, 5, 4, 3, 'верхняя'),
('Вторник', 1, 1, 1, 5, 4, 'верхняя'),
('Вторник', 1, 1, 3, 17, 17, 'верхняя'),
('Вторник', 1, 1, 2, 16, 15, 'верхняя'),
('Вторник', 1, 2, 5, 3, 2, 'верхняя'),
('Вторник', 1, 2, 4, 8, 7, 'верхняя'),
('Вторник', 1, 2, 1, 9, 8, 'верхняя'),
('Вторник', 1, 2, 3, 6, 5, 'верхняя'),
('Вторник', 1, 2, 2, 16, 15, 'верхняя'),
('Вторник', 1, 3, 5, 8, 7, 'верхняя'),
('Вторник', 1, 3, 4, 9, 8, 'верхняя'),
('Вторник', 1, 3, 1, 10, 9, 'верхняя'),
('Вторник', 0, 98, 1, 10, 9, 'верхняя'),
('Вторник', 0, 97, 5, 4, 3, 'верхняя');

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

--
-- Дамп данных таблицы `schedule_true`
--

INSERT INTO `schedule_true` (`day`, `day_n`, `para`, `Group`, `Lecturer`, `Displine`, `week`) VALUES
('Понедельник', 0, 0, 1, 3, 2, 'верхняя'),
('Понедельник', 0, 0, 4, 5, 4, 'верхняя'),
('Понедельник', 0, 0, 2, 6, 5, 'верхняя'),
('Понедельник', 0, 0, 3, 9, 8, 'верхняя'),
('Понедельник', 0, 0, 5, 11, 10, 'верхняя'),
('Понедельник', 0, 1, 3, 3, 2, 'верхняя'),
('Понедельник', 0, 1, 1, 5, 4, 'верхняя'),
('Понедельник', 0, 1, 5, 9, 8, 'верхняя'),
('Понедельник', 0, 1, 2, 10, 9, 'верхняя'),
('Понедельник', 0, 1, 4, 14, 13, 'верхняя'),
('Понедельник', 0, 2, 2, 13, 12, 'верхняя'),
('Понедельник', 0, 2, 3, 17, 16, 'верхняя'),
('Понедельник', 0, 2, 5, 10, 9, 'верхняя'),
('Понедельник', 0, 2, 4, 12, 11, 'верхняя'),
('Вторник', 1, 0, 4, 3, 2, 'верхняя'),
('Вторник', 1, 0, 1, 4, 3, 'верхняя'),
('Вторник', 1, 0, 5, 5, 4, 'верхняя'),
('Вторник', 1, 0, 3, 11, 10, 'верхняя'),
('Вторник', 1, 0, 2, 17, 16, 'верхняя'),
('Вторник', 1, 1, 4, 3, 2, 'верхняя'),
('Вторник', 1, 1, 5, 4, 3, 'верхняя'),
('Вторник', 1, 1, 1, 5, 4, 'верхняя'),
('Вторник', 1, 1, 3, 17, 17, 'верхняя'),
('Вторник', 1, 1, 2, 16, 15, 'верхняя'),
('Вторник', 1, 2, 5, 3, 2, 'верхняя'),
('Вторник', 1, 2, 4, 8, 7, 'верхняя'),
('Вторник', 1, 2, 1, 9, 8, 'верхняя'),
('Вторник', 1, 2, 3, 6, 5, 'верхняя'),
('Вторник', 1, 2, 2, 16, 15, 'верхняя'),
('Вторник', 1, 3, 5, 8, 7, 'верхняя'),
('Вторник', 1, 3, 4, 9, 8, 'верхняя'),
('Вторник', 1, 3, 1, 10, 9, 'верхняя'),
('Среда', 2, 0, 1, 2, 1, 'верхняя'),
('Среда', 2, 0, 4, 15, 14, 'верхняя'),
('Среда', 2, 0, 2, 7, 6, 'верхняя'),
('Среда', 2, 0, 3, 17, 17, 'верхняя'),
('Среда', 2, 1, 1, 2, 1, 'верхняя'),
('Среда', 2, 1, 3, 13, 12, 'верхняя'),
('Среда', 2, 1, 5, 7, 6, 'верхняя'),
('Среда', 2, 1, 2, 16, 15, 'верхняя'),
('Среда', 2, 1, 4, 15, 14, 'верхняя'),
('Среда', 2, 2, 5, 2, 1, 'верхняя'),
('Среда', 2, 2, 2, 12, 11, 'верхняя'),
('Среда', 2, 2, 4, 9, 8, 'верхняя'),
('Среда', 2, 2, 1, 11, 10, 'верхняя'),
('Четверг', 3, 0, 3, 9, 8, 'верхняя'),
('Четверг', 3, 0, 2, 3, 2, 'верхняя'),
('Четверг', 3, 0, 1, 8, 7, 'верхняя'),
('Четверг', 3, 0, 5, 6, 5, 'верхняя'),
('Четверг', 3, 0, 4, 7, 6, 'верхняя'),
('Четверг', 3, 1, 5, 2, 1, 'верхняя'),
('Четверг', 3, 1, 4, 5, 4, 'верхняя'),
('Четверг', 3, 1, 1, 7, 6, 'верхняя'),
('Четверг', 3, 1, 2, 9, 8, 'верхняя'),
('Четверг', 3, 1, 3, 10, 9, 'верхняя'),
('Четверг', 3, 2, 3, 7, 6, 'верхняя'),
('Четверг', 3, 2, 1, 13, 12, 'верхняя'),
('Четверг', 3, 2, 5, 9, 8, 'верхняя'),
('Четверг', 3, 2, 2, 15, 14, 'верхняя'),
('Четверг', 3, 2, 4, 10, 9, 'верхняя'),
('Четверг', 3, 3, 5, 12, 11, 'верхняя'),
('Четверг', 3, 3, 3, 4, 3, 'верхняя'),
('Пятница', 4, 0, 1, 3, 2, 'верхняя'),
('Пятница', 4, 0, 3, 4, 3, 'верхняя'),
('Пятница', 4, 0, 4, 5, 4, 'верхняя'),
('Пятница', 4, 0, 2, 15, 14, 'верхняя'),
('Пятница', 4, 1, 5, 5, 4, 'верхняя'),
('Пятница', 4, 1, 2, 11, 10, 'верхняя'),
('Пятница', 4, 1, 3, 12, 11, 'верхняя'),
('Пятница', 4, 1, 4, 14, 13, 'верхняя'),
('Пятница', 4, 1, 1, 6, 5, 'верхняя'),
('Пятница', 4, 2, 5, 5, 4, 'верхняя'),
('Пятница', 4, 2, 1, 12, 11, 'верхняя'),
('Пятница', 4, 2, 2, 14, 13, 'верхняя'),
('Пятница', 4, 2, 3, 2, 1, 'верхняя'),
('Суббота', 5, 0, 2, 3, 2, 'верхняя'),
('Суббота', 5, 0, 3, 2, 1, 'верхняя'),
('Суббота', 5, 0, 1, 4, 3, 'верхняя'),
('Суббота', 5, 0, 4, 6, 5, 'верхняя'),
('Суббота', 5, 1, 3, 3, 2, 'верхняя'),
('Суббота', 5, 1, 1, 9, 8, 'верхняя'),
('Суббота', 5, 1, 4, 11, 10, 'верхняя'),
('Суббота', 5, 1, 2, 14, 13, 'верхняя'),
('Суббота', 5, 1, 5, 4, 3, 'верхняя'),
('Суббота', 5, 2, 5, 3, 2, 'верхняя'),
('Суббота', 5, 2, 3, 17, 17, 'верхняя'),
('Суббота', 5, 2, 2, 9, 8, 'верхняя'),
('Суббота', 5, 2, 4, 13, 12, 'верхняя'),
('Суббота', 5, 2, 1, 5, 4, 'верхняя'),
('Понедельник', 0, 0, 1, 3, 2, 'нижняя'),
('Понедельник', 0, 0, 3, 4, 3, 'нижняя'),
('Понедельник', 0, 0, 4, 6, 5, 'нижняя'),
('Понедельник', 0, 0, 2, 7, 6, 'нижняя'),
('Понедельник', 0, 0, 5, 10, 9, 'нижняя'),
('Понедельник', 0, 1, 1, 5, 4, 'нижняя'),
('Понедельник', 0, 1, 4, 11, 10, 'нижняя'),
('Понедельник', 0, 1, 5, 12, 11, 'нижняя'),
('Понедельник', 0, 1, 2, 14, 13, 'нижняя'),
('Понедельник', 0, 1, 3, 13, 12, 'нижняя'),
('Понедельник', 0, 2, 1, 2, 1, 'нижняя'),
('Понедельник', 0, 2, 3, 17, 16, 'нижняя'),
('Понедельник', 0, 2, 2, 15, 14, 'нижняя'),
('Понедельник', 0, 2, 4, 14, 13, 'нижняя'),
('Понедельник', 0, 2, 5, 8, 7, 'нижняя'),
('Вторник', 1, 0, 5, 4, 3, 'нижняя'),
('Вторник', 1, 0, 1, 11, 10, 'нижняя'),
('Вторник', 1, 0, 3, 6, 5, 'нижняя'),
('Вторник', 1, 0, 4, 7, 6, 'нижняя'),
('Вторник', 1, 0, 2, 16, 15, 'нижняя'),
('Вторник', 1, 1, 5, 4, 3, 'нижняя'),
('Вторник', 1, 1, 1, 6, 5, 'нижняя'),
('Вторник', 1, 1, 3, 7, 6, 'нижняя'),
('Вторник', 1, 1, 4, 11, 10, 'нижняя'),
('Вторник', 1, 1, 2, 15, 14, 'нижняя'),
('Вторник', 1, 2, 5, 5, 4, 'нижняя'),
('Вторник', 1, 2, 1, 7, 6, 'нижняя'),
('Вторник', 1, 2, 3, 12, 11, 'нижняя'),
('Вторник', 1, 2, 4, 15, 14, 'нижняя'),
('Вторник', 1, 2, 2, 6, 5, 'нижняя'),
('Вторник', 1, 3, 1, 9, 8, 'нижняя'),
('Среда', 2, 0, 4, 3, 2, 'нижняя'),
('Среда', 2, 0, 3, 4, 3, 'нижняя'),
('Среда', 2, 0, 5, 5, 4, 'нижняя'),
('Среда', 2, 0, 1, 6, 5, 'нижняя'),
('Среда', 2, 0, 2, 9, 8, 'нижняя'),
('Среда', 2, 1, 5, 6, 5, 'нижняя'),
('Среда', 2, 1, 3, 9, 8, 'нижняя'),
('Среда', 2, 1, 1, 11, 10, 'нижняя'),
('Среда', 2, 1, 2, 16, 15, 'нижняя'),
('Среда', 2, 1, 4, 13, 12, 'нижняя'),
('Среда', 2, 2, 3, 10, 9, 'нижняя'),
('Среда', 2, 2, 4, 15, 14, 'нижняя'),
('Среда', 2, 2, 2, 11, 10, 'нижняя'),
('Четверг', 3, 0, 1, 13, 12, 'нижняя'),
('Четверг', 3, 0, 3, 3, 2, 'нижняя'),
('Четверг', 3, 0, 4, 6, 5, 'нижняя'),
('Четверг', 3, 0, 5, 11, 10, 'нижняя'),
('Четверг', 3, 0, 2, 12, 11, 'нижняя'),
('Четверг', 3, 1, 5, 2, 1, 'нижняя'),
('Четверг', 3, 1, 1, 4, 3, 'нижняя'),
('Четверг', 3, 1, 4, 10, 9, 'нижняя'),
('Четверг', 3, 1, 3, 11, 10, 'нижняя'),
('Четверг', 3, 1, 2, 16, 15, 'нижняя'),
('Четверг', 3, 2, 5, 9, 8, 'нижняя'),
('Четверг', 3, 2, 3, 4, 3, 'нижняя'),
('Четверг', 3, 2, 4, 8, 7, 'нижняя'),
('Четверг', 3, 2, 1, 10, 9, 'нижняя'),
('Пятница', 4, 0, 1, 4, 3, 'нижняя'),
('Пятница', 4, 0, 4, 5, 4, 'нижняя'),
('Пятница', 4, 0, 5, 6, 5, 'нижняя'),
('Пятница', 4, 0, 3, 11, 10, 'нижняя'),
('Пятница', 4, 0, 2, 17, 16, 'нижняя'),
('Пятница', 4, 1, 1, 5, 4, 'нижняя'),
('Пятница', 4, 1, 2, 6, 5, 'нижняя'),
('Пятница', 4, 1, 4, 12, 11, 'нижняя'),
('Пятница', 4, 1, 5, 3, 2, 'нижняя'),
('Пятница', 4, 1, 3, 17, 17, 'нижняя'),
('Пятница', 4, 2, 5, 5, 4, 'нижняя'),
('Пятница', 4, 2, 4, 15, 14, 'нижняя'),
('Пятница', 4, 2, 1, 12, 11, 'нижняя'),
('Пятница', 4, 2, 2, 10, 9, 'нижняя'),
('Пятница', 4, 2, 3, 17, 17, 'нижняя'),
('Пятница', 4, 3, 2, 15, 14, 'нижняя'),
('Суббота', 5, 0, 3, 2, 1, 'нижняя'),
('Суббота', 5, 0, 1, 4, 3, 'нижняя'),
('Суббота', 5, 0, 4, 5, 4, 'нижняя'),
('Суббота', 5, 0, 5, 11, 10, 'нижняя'),
('Суббота', 5, 0, 2, 3, 2, 'нижняя'),
('Суббота', 5, 1, 5, 4, 3, 'нижняя'),
('Суббота', 5, 1, 4, 5, 4, 'нижняя'),
('Суббота', 5, 1, 3, 6, 5, 'нижняя'),
('Суббота', 5, 1, 1, 8, 7, 'нижняя'),
('Суббота', 5, 1, 2, 11, 10, 'нижняя'),
('Суббота', 5, 2, 3, 17, 17, 'нижняя'),
('Суббота', 5, 2, 1, 5, 4, 'нижняя'),
('Суббота', 5, 2, 2, 13, 12, 'нижняя'),
('Суббота', 5, 2, 4, 9, 8, 'нижняя'),
('Суббота', 5, 2, 5, 7, 6, 'нижняя');

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

--
-- Дамп данных таблицы `sch_kab`
--

INSERT INTO `sch_kab` (`id_Group`, `id_Discipline`, `id_Lecturer`, `para`, `kabinet`, `Have`) VALUES
(1, 9, 10, 0, 101, 1),
(2, 15, 16, 0, 105, 1),
(3, 10, 11, 0, 109, 1),
(4, 8, 9, 0, 112, 1),
(5, 3, 4, 0, 108, 1),
(2, 15, 16, 1, 105, 1),
(3, 17, 17, 1, 101, 1),
(1, 8, 9, 1, 112, 1),
(5, 3, 4, 1, 109, 1),
(4, 2, 3, 1, 1, 1),
(1, 3, 4, 2, 112, 1),
(2, 16, 17, 2, 101, 1),
(4, 7, 8, 2, 108, 1),
(5, 2, 3, 2, 1, 1),
(3, 5, 6, 2, 107, 1),
(1, 9, 10, 3, 101, 1),
(5, 7, 8, 3, 108, 1),
(4, 2, 3, 3, 1, 1);

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

--
-- Дамп данных таблицы `storage`
--

INSERT INTO `storage` (`Group`, `Disciplane`, `Lecturer`, `hours_t`, `hours_d`, `debt`, `hours_s`, `st_practic`, `pr_practic`) VALUES
(1, 13, 14, 1, 2, 0, 25, 0, 0),
(1, 2, 3, 2, 1, 0, 19, 0, 0),
(1, 18, 19, 4, 4, 0, 47, 0, 0),
(1, 5, 6, 2, 1, 0, 22, 0, 0),
(1, 6, 7, 1, 1, 0, 17, 0, 0),
(1, 8, 9, 2, 1, 0, 25, 0, 0),
(1, 9, 10, 1, 1, 0, 15, 0, 0),
(1, 11, 12, 1, 1, 0, 18, 0, 0),
(1, 12, 13, 1, 1, 0, 20, 0, 0),
(1, 16, 17, 1, 1, 0, 17, 0, 0),
(2, 13, 14, 2, 1, 0, 25, 0, 0),
(2, 2, 3, 2, 1, 0, 25, 0, 0),
(1, 1, 2, 2, 1, 0, 25, 0, 0),
(1, 2, 3, 2, 1, 0, 22, 0, 0),
(1, 3, 4, 2, 3, 0, 35, 0, 0),
(1, 4, 5, 3, 3, 0, 47, 0, 0),
(1, 5, 6, 1, 2, 0, 22, 0, 0),
(1, 6, 7, 1, 1, 0, 17, 0, 0),
(1, 7, 8, 1, 1, 0, 17, 0, 0),
(1, 8, 9, 2, 1, 0, 25, 0, 0),
(1, 9, 10, 1, 1, 0, 15, 0, 0),
(1, 10, 11, 1, 2, 0, 24, 0, 0),
(1, 11, 12, 1, 1, 0, 18, 0, 0),
(2, 13, 14, 2, 1, 0, 25, 0, 0),
(2, 2, 3, 2, 1, 0, 25, 0, 0),
(2, 14, 15, 2, 3, 0, 39, 0, 0),
(2, 15, 16, 3, 3, 0, 39, 0, 0),
(2, 5, 6, 1, 2, 0, 20, 0, 0),
(2, 16, 17, 1, 1, 0, 13, 0, 0),
(2, 8, 9, 2, 1, 0, 27, 0, 0),
(2, 9, 10, 1, 1, 0, 15, 0, 0),
(2, 10, 11, 1, 2, 0, 24, 0, 0),
(2, 11, 12, 1, 1, 0, 18, 0, 0),
(2, 12, 13, 1, 1, 0, 18, 0, 0),
(1, 12, 13, 1, 1, 0, 20, 0, 0),
(2, 6, 7, 1, 1, 0, 17, 0, 0),
(3, 1, 2, 2, 1, 0, 25, 0, 0),
(3, 2, 3, 2, 1, 0, 23, 0, 0),
(3, 3, 4, 2, 3, 0, 39, 0, 0),
(3, 17, 17, 3, 3, 0, 43, 0, 0),
(3, 5, 6, 1, 2, 0, 18, 0, 0),
(3, 6, 7, 1, 1, 0, 17, 0, 0),
(3, 16, 17, 1, 1, 0, 15, 0, 0),
(3, 8, 9, 2, 1, 0, 25, 0, 0),
(3, 9, 10, 1, 1, 0, 17, 0, 0),
(3, 10, 11, 1, 2, 0, 20, 0, 0),
(3, 11, 12, 1, 1, 0, 18, 0, 0),
(3, 12, 13, 1, 1, 0, 20, 0, 0),
(4, 13, 14, 2, 1, 0, 48, 0, 0),
(4, 2, 3, 2, 1, 0, 42, 0, 0),
(4, 14, 15, 2, 3, 0, 78, 0, 0),
(4, 4, 5, 3, 3, 0, 94, 0, 0),
(4, 5, 6, 1, 2, 0, 44, 0, 0),
(4, 6, 7, 1, 1, 0, 34, 0, 0),
(4, 7, 8, 1, 1, 0, 30, 0, 0),
(4, 8, 9, 2, 1, 0, 48, 0, 0),
(4, 9, 10, 1, 1, 0, 34, 0, 0),
(4, 10, 11, 1, 2, 0, 48, 0, 0),
(4, 11, 12, 1, 1, 0, 33, 0, 0),
(4, 12, 13, 1, 1, 0, 39, 0, 0),
(5, 1, 2, 2, 1, 0, 50, 0, 0),
(5, 2, 3, 2, 1, 0, 45, 0, 0),
(5, 3, 4, 2, 3, 0, 73, 0, 0),
(5, 4, 5, 3, 3, 0, 94, 0, 0),
(5, 5, 6, 1, 2, 0, 42, 0, 0),
(5, 7, 8, 1, 1, 0, 32, 0, 0),
(5, 8, 9, 2, 1, 0, 50, 0, 0),
(5, 9, 10, 1, 1, 0, 32, 0, 0),
(5, 10, 11, 1, 2, 0, 45, 0, 0),
(5, 11, 12, 1, 1, 0, 36, 0, 0),
(5, 6, 7, 1, 1, 0, 39, 0, 0),
(1, 19, 20, 18, 18, 0, 9, 0, 1),
(1, 20, 8, 18, 18, 0, 45, 1, 0);

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
-- Дамп данных таблицы `testchange1`
--

INSERT INTO `testchange1` (`Group`, `Displine`, `Lecturer`, `hours_count_s`, `debt`, `hours_today`) VALUES
(1, 3, 4, 0, 0, 1),
(1, 8, 9, 0, 0, 1),
(1, 9, 10, 0, 0, 2),
(2, 16, 17, 0, 0, 1),
(2, 15, 16, 0, 0, 2),
(2, 15, 16, 0, 0, 2),
(3, 10, 11, 0, 0, 1),
(3, 17, 17, 0, 0, 1),
(3, 5, 6, 0, 0, 1),
(4, 2, 3, 0, 0, 2),
(4, 2, 3, 0, 0, 2),
(4, 7, 8, 0, 0, 1),
(4, 8, 9, 0, 0, 1),
(5, 3, 4, 0, 0, 2),
(5, 2, 3, 0, 0, 1),
(5, 7, 8, 0, 0, 1),
(1, 9, 10, 0, 0, 2),
(5, 3, 4, 0, 0, 2);

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
  MODIFY `id_Discipline` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблицы `groups`
--
ALTER TABLE `groups`
  MODIFY `id_Groups` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `kabinet`
--
ALTER TABLE `kabinet`
  MODIFY `id_Kabinet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `id_Lecturer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

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
