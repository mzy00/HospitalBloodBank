-- 病人信息表
CREATE TABLE Patients
(
    PatientID     INT PRIMARY KEY AUTO_INCREMENT COMMENT '病人ID',
    Name          VARCHAR(255) NOT NULL COMMENT '病人姓名',
    Age           INT COMMENT '病人年龄',
    Gender        VARCHAR(10) COMMENT '病人性别',
    ContactNumber VARCHAR(20) COMMENT '联系方式',
    BloodType     VARCHAR(5) COMMENT '病人血型'
) COMMENT='存储病人的基本信息';

-- 血库库存表
CREATE TABLE BloodInventory
(
    BloodID    INT PRIMARY KEY AUTO_INCREMENT COMMENT '血液ID',
    BloodType  VARCHAR(5) NOT NULL COMMENT '血型',
    Quantity   INT        NOT NULL COMMENT '库存数量',
    ExpiryDate DATE       NOT NULL COMMENT '过期日期'
) COMMENT='存储血液库存信息';

-- 献血者信息表
CREATE TABLE Donors
(
    DonorID       INT PRIMARY KEY AUTO_INCREMENT COMMENT '献血者ID',
    Name          VARCHAR(255) NOT NULL COMMENT '献血者姓名',
    Age           INT COMMENT '献血者年龄',
    Gender        VARCHAR(10) COMMENT '献血者性别',
    ContactNumber VARCHAR(20) COMMENT '联系方式',
    BloodType     VARCHAR(5) COMMENT '献血者血型'
) COMMENT='存储献血者的信息';

-- 献血记录表
CREATE TABLE DonationRecords
(
    RecordID     INT PRIMARY KEY AUTO_INCREMENT COMMENT '献血记录ID',
    DonorID      INT COMMENT '献血者ID',
    DonationDate DATE NOT NULL COMMENT '献血日期',
    FOREIGN KEY (DonorID) REFERENCES Donors (DonorID)
) COMMENT='存储献血记录';

-- 用血申请表
CREATE TABLE BloodRequests
(
    RequestID   INT PRIMARY KEY AUTO_INCREMENT COMMENT '申请ID',
    PatientID   INT COMMENT '病人ID',
    BloodType   VARCHAR(5) COMMENT '申请血型',
    Quantity    INT COMMENT '申请数量',
    RequestDate DATE NOT NULL COMMENT '申请日期',
    Status      VARCHAR(20) DEFAULT 'Pending' COMMENT '申请状态',
    FOREIGN KEY (PatientID) REFERENCES Patients (PatientID)
) COMMENT='存储用血申请信息';

-- 用户表
CREATE TABLE Users
(
    UserID   INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    Username VARCHAR(50) NOT NULL COMMENT '用户名',
    Password VARCHAR(50) NOT NULL COMMENT '密码',
    Role     VARCHAR(20) NOT NULL COMMENT '用户角色'
) COMMENT='存储系统用户信息';
