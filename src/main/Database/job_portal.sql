-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 18, 2024 lúc 12:30 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `job_portal`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','employer','candidate') NOT NULL,
  `avatar_url` varchar(255) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `role`, `avatar_url`, `is_deleted`) VALUES
('acct_2a66238a-7907-46aa-b7a4-68405db5ebf3', 'employer', '$2a$10$Zqw1p47tV9ekwzarRcEXIuWhuYt2g23boH04bFH5cU5TJq.6iuuLy', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_2e5b2c2d-3a98-4086-be7c-ef2ce9afe536', 'VIN_AI', '$2a$10$bbd6B63PUPpRpN/TMZvNOe6t7BpjYS/mhzrMyBgmGIIWUwRagK6fq', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_412e5709-6a61-47bc-992f-b5ea7cd309b4', 'candidate1', '$2a$10$ZVyI9udGpM/w9Hzr2CXxq.wRIFfFxG2bNTDdg/olmtpygxMQoqhpi', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_435b36b5-dcaf-488b-9537-b01f419dd197', 'employer1', '$2a$10$Y1aPVKOkeTGgJA6fKJkdNOEcmAC/fzsKaAirguGWkEATikYJ/4X5K', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_487308fa-19b5-4b37-acef-141f443af466', 'employer4', '$2a$10$Ow75vJd6lv6HPOYXuT8NSencqhfSUb4N33bjhOjCvfRSYJFwXva9K', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_48d4f310-d202-4c68-9d4b-7bef380d3cbd', 'user1', '$2a$10$R5t8dpFRdjF4Nj4/50t6tuSP8RUexiGe7Z1qElqxzM.OT7Jt3Sxi6', 'candidate', 'https://i.ibb.co/myfBQzM/avatars-1.png', 0),
('acct_58745906-7f05-41cd-9ff2-842768e2b7c3', 'candidate2', '$2a$10$M5Q2TJ.6fGW76t4vyDkdhuKgKs85LcVnMlIGWx/agiBEW4fhQdFs6', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_74a8d4fa-ede8-4d63-8ca0-5fedcf80fc52', 'employer2', '$2a$10$CEvRa.tp.RtAwu/thXsyc.YSNi5cIXsWmxofyq.BeHa8wQ5IQW0jq', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_84ee841e-e123-42ff-9d1d-5ade476a70de', 'candidate', '$2a$10$d97qutuNbrNSyWAxjlJ3OuO0IxsFrp9mtj3LrTK3sx8VZ/U0Xo0ii', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_986fa7cc-0766-419b-b6ea-febd8b28192e', 'candidate3', '$2a$10$du6Y15rCITItkmGXzOclq.j.NM1TyLUvKhgR9zR6jmnHazyMPHeoe', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_99434f22-ca5e-4874-b434-56b81d884197', 'employer3', '$2a$10$q011md5xkuj7r6T9eoBuau1RkbNhg84HWbWzGs4QPzvFD2hN8ypie', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_a3f92c75-e912-4bda-93f1-26b68b6f4f67', 'user123', '$2a$10$qdBvTkdz6e6ekd7.g0qrEep0NcHxT82oYb8PiOTZSbvjB03kFqO4S', 'candidate', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_a552857f-7abb-4c75-9072-313c485b9c01', 'admin', '$2a$10$bzIGcEXRfTweLUKxAVPlvOy9Rq/6DVZHxFpoXr4kCiLNnyUSR3Vie', 'admin', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_ceafe9b0-bcdd-435b-b139-c8be5fa33c5c', 'Net_tech', '$2a$10$ArF4G1nR5MEh8RY0xOzcxudFEXRy55MAIWyvKRezmHIO/XpCHsPmK', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0),
('acct_ff5ca874-9fdd-419e-8b0e-3b87e66ca2ed', 'admin1', '$2a$10$M7Tp.Qc5wLQhYDk7yzwcReZ1stAf38iEjsZhde7wQELPFYTtlEH1W', 'employer', 'https://i.ibb.co/P9B37Bq/default-avatar.jpg', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `candidate_profile`
--

CREATE TABLE `candidate_profile` (
  `id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cv_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `candidate_profile`
--

INSERT INTO `candidate_profile` (`id`, `account_id`, `name`, `email`, `cv_url`) VALUES
('cand_4c1064e4-2ada-442d-9ec3-be5f51e8a45c', 'acct_84ee841e-e123-42ff-9d1d-5ade476a70de', 'candidate', 'candidate@gmail.com', 'https://i.ibb.co/JdQ3y4q/CV-template-1.png'),
('cand_522f9de3-f7c6-4d79-8e6f-a180e25b8cc0', 'acct_412e5709-6a61-47bc-992f-b5ea7cd309b4', 'John Doe', 'john.doe@example.com', 'https://i.ibb.co/4KVrprh/CV-template-1.png'),
('cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'acct_48d4f310-d202-4c68-9d4b-7bef380d3cbd', 'Phan Văn Toàn', 'toanyogame@gmail.com', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg'),
('cand_dbd54ea3-efce-488e-b034-0ce0fb354eac', 'acct_a3f92c75-e912-4bda-93f1-26b68b6f4f67', 'Giáo', 'correct_login@example.com', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg'),
('cand_f08bd287-293d-4aa2-a6fa-f2855ffa937a', 'acct_58745906-7f05-41cd-9ff2-842768e2b7c3', 'Jane Smith', 'jane.smith@example.com', 'https://i.ibb.co/4KVrprh/CV-template-1.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employer_profile`
--

CREATE TABLE `employer_profile` (
  `id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `link` text NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employer_profile`
--

INSERT INTO `employer_profile` (`id`, `account_id`, `name`, `address`, `email`, `link`, `description`) VALUES
('ER_20daf028-ad06-4f57-9339-95ff845f3bf1', 'acct_2a66238a-7907-46aa-b7a4-68405db5ebf3', 'employer', 'employer', 'employer4@gmail.com', 'https://www.facebook.com/;https://www.youtube.com/', ' employer'),
('ER_90f49fbd-43b3-4d35-b1f4-9a699ee690e2', 'acct_487308fa-19b5-4b37-acef-141f443af466', 'employer4', 'employer4', 'employer4@gmail.com', 'https://www.facebook.com/', ' a '),
('ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'acct_ceafe9b0-bcdd-435b-b139-c8be5fa33c5c', 'NET TECH _ FUTURE', 'Lien Chieu District, Da Nang City, Viet Nam', 'phanvantoan.contact@gmail.com', 'https://www.facebook.com/phan.van.toan.634116', 'NET TECH là đại diện hàng đầu về thương mại điện tử'),
('ER_aebcddbc-4a36-43ed-80a6-de5d8d4d113a', 'acct_74a8d4fa-ede8-4d63-8ca0-5fedcf80fc52', 'Company B', '456 Elm St, City B', 'companyB@example.com', 'https://companyB.com;https://companyC.com/', ' Innovative solutions provider     '),
('ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'acct_2e5b2c2d-3a98-4086-be7c-ef2ce9afe536', 'VIN AI', 'Hai Chau District, Da Nang City, Viet Nam', 'masonhome.contact@gmail.com', 'https://www.facebook.com/phan.van.toan.634116', ' '),
('ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'acct_ff5ca874-9fdd-419e-8b0e-3b87e66ca2ed', 'Phan Văn Toàn', 'Lien Chieu District, Da Nang City, Viet Nam', 'toanyogame@gmail.com', 'https://www.facebook.com/phan.van.toan.634116', 'Toàn yo game'),
('ER_f4e20948-364f-498d-b11a-4360cf591cea', 'acct_435b36b5-dcaf-488b-9537-b01f419dd197', 'Company B', '123 Main St, City A', 'companyA@example.com', 'https://companyA.com', ' Leading tech company  ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `job`
--

CREATE TABLE `job` (
  `id` varchar(255) NOT NULL,
  `employer_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `salary_range_id` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `job_type` enum('part-time','full-time','internship','') NOT NULL,
  `experience` enum('Intern','Fresher','Junior','Middle','Senior','Lead') NOT NULL,
  `date_post` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_available` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `job`
--

INSERT INTO `job` (`id`, `employer_id`, `title`, `description`, `salary_range_id`, `location`, `job_type`, `experience`, `date_post`, `is_available`) VALUES
('AB2024-12-18 15:18:43', 'ER_f4e20948-364f-498d-b11a-4360cf591cea', 'abc', 'a', '1', 'abc', 'full-time', 'Intern', '2024-12-18 08:18:43', 1),
('JOB_1', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'Software Developer', 'Develop and maintain software applications.', '7', 'Ha Noi', 'full-time', 'Senior', '2024-12-15 08:47:35', 1),
('JOB_10', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'QA Engineer', 'Test software for quality assurance.', '5', 'Da Nang', 'full-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_11', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'Graphic Designer', 'Create visual designs for marketing.', '3', 'Da Nang', 'internship', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_12', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'IT Support', 'Provide technical support to users.', '2', 'Ho Chi Minh', 'part-time', 'Fresher', '2024-12-15 08:47:35', 1),
('JOB_13', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'Content Writer', 'Write content for the website and blog.', '2', 'Ho Chi Minh', 'full-time', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_14', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'Marketing Coordinator', 'Coordinate marketing campaigns.', '3', 'Ha Noi', 'part-time', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_15', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'SEO Specialist', 'Optimize website for search engines.', '5', 'Ho Chi Minh', 'full-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_16', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'Accountant', 'Handle financial records and transactions.', '6', 'Ho Chi Minh', 'part-time', 'Senior', '2024-12-15 08:47:35', 1),
('JOB_17', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'Technical Writer', 'Create documentation and user manuals.', '3', 'Ha Noi', 'full-time', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_18', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'E-commerce Manager', 'Manage online store and sales.', '4', 'Da Nang', 'part-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_19', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'Project Manager', 'Oversee project planning and execution.', '6', 'Da Nang', 'full-time', 'Senior', '2024-12-15 08:47:35', 1),
('JOB_2', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'UI/UX Designer', 'Design user interfaces and experiences.', '3', 'Da Nang', 'part-time', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_20', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'Customer Success Manager', 'Ensure customer satisfaction.', '4', 'Da Nang', 'part-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_3', 'ER_98a8e232-127d-4e01-8a63-f1a555360cf5', 'Network Engineer', 'Configure and maintain network infrastructures.', '4', 'Ha Noi', 'full-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_4', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'Data Analyst', 'Analyze data and generate insights.', '1', 'Da Nang', 'internship', 'Intern', '2024-12-15 08:47:35', 1),
('JOB_5', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'Marketing Specialist', 'Plan and execute marketing campaigns.', '2', 'Ha Noi', 'full-time', 'Fresher', '2024-12-15 08:47:35', 1),
('JOB_6', 'ER_c06203d3-4e39-4e34-bd38-bebc56cdf2a6', 'Customer Support', 'Assist customers with inquiries.', '3', 'Da Nang', 'part-time', 'Junior', '2024-12-15 08:47:35', 1),
('JOB_7', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'Web Developer', 'Develop and maintain websites.', '7', 'Ha Noi', 'full-time', 'Senior', '2024-12-15 08:47:35', 1),
('JOB_8', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'Sales Executive', 'Sell company produ	cts and services.', '4', 'Da Nang', 'full-time', 'Middle', '2024-12-15 08:47:35', 1),
('JOB_9', 'ER_c6b8743d-f5f5-4d59-8d1f-cd9252d20902', 'HR Manager', 'Manage human resources and recruitment.', '6', 'Ho Chi Minh', 'part-time', 'Senior', '2024-12-15 08:47:35', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `job_application`
--

CREATE TABLE `job_application` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `cv_url` varchar(255) NOT NULL,
  `job_id` varchar(255) NOT NULL,
  `candidate_id` varchar(255) NOT NULL,
  `status` enum('Pending','Reject','Accept','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `job_application`
--

INSERT INTO `job_application` (`id`, `name`, `email`, `phone`, `cv_url`, `job_id`, `candidate_id`, `status`) VALUES
('JA_05c4c91f-4b47-4d7e-8ae4-251893fa2e26', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_7', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_12729cc7-4b7a-4aff-9988-600da71b3e0a', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_12', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_16c53a26-b238-4438-b053-c38968b49971', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_1', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_341d0c39-1cbd-4e3d-a693-be88b0e14c9e', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_1', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_7df7098a-9be6-4f06-b05f-923663d1882c', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_2', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_7fc702cd-bc1f-4406-87bb-6965bedf6601', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_14', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_99c73b8f-a9fd-4b16-8526-239a37d53ad6', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_17', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_a94692ce-8a02-432c-a6fa-b45514276fd8', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_12', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_afeb4ad0-0fd3-4977-b6ca-e6e5242ee670', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_18', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending'),
('JA_d6b5294e-51fb-487b-bd27-2136e2394efa', 'Phan Văn Toàn', 'toanyogame@gmail.com', '0387517120', 'https://i.ibb.co/ZJ9Q3qb/Phan-Van-Toan-CV.jpg', 'JOB_1', 'cand_bb7fd9f3-9bd7-48b4-a789-5118e4ce7013', 'Pending');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `salary_range`
--

CREATE TABLE `salary_range` (
  `id` varchar(255) NOT NULL,
  `salary_range` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `salary_range`
--

INSERT INTO `salary_range` (`id`, `salary_range`) VALUES
('1', 'Dưới 5 triệu'),
('2', 'Từ 5 - 10 triệu'),
('3', 'Từ 10 - 15 triệu'),
('4', 'Từ 15 - 20 triệu'),
('5', 'Từ 20 - 30 triệu'),
('6', 'Từ 30 - 40 triệu'),
('7', 'Trên 40 triệu');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `candidate_profile`
--
ALTER TABLE `candidate_profile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_candidate_account` (`account_id`);

--
-- Chỉ mục cho bảng `employer_profile`
--
ALTER TABLE `employer_profile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_employer_account` (`account_id`);

--
-- Chỉ mục cho bảng `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_job_salary_range` (`salary_range_id`),
  ADD KEY `fk_job_employer` (`employer_id`);

--
-- Chỉ mục cho bảng `job_application`
--
ALTER TABLE `job_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ja_candidate` (`candidate_id`),
  ADD KEY `fk_ja_job` (`job_id`);

--
-- Chỉ mục cho bảng `salary_range`
--
ALTER TABLE `salary_range`
  ADD PRIMARY KEY (`id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `candidate_profile`
--
ALTER TABLE `candidate_profile`
  ADD CONSTRAINT `fk_candidate_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `employer_profile`
--
ALTER TABLE `employer_profile`
  ADD CONSTRAINT `fk_employer_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `job`
--
ALTER TABLE `job`
  ADD CONSTRAINT `fk_job_employer` FOREIGN KEY (`employer_id`) REFERENCES `employer_profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_job_salary_range` FOREIGN KEY (`salary_range_id`) REFERENCES `salary_range` (`id`);

--
-- Các ràng buộc cho bảng `job_application`
--
ALTER TABLE `job_application`
  ADD CONSTRAINT `fk_ja_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidate_profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ja_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
