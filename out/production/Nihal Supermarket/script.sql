DROP DATABASE IF EXISTS NihalSupermarket;
CREATE DATABASE IF NOT EXISTS NihalSupermarket;
SHOW DATABASES ;
USE NihalSupermarket;
#-------------------


DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    custID VARCHAR(15),
    custTitle VARCHAR(15),
    custName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    custAddress TEXT,
    city VARCHAR(20),
    province VARCHAR(20),
    CONSTRAINT PRIMARY KEY (custID)
    );
SHOW TABLES ;
DESCRIBE Customer;
#---------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
    orderId VARCHAR(15),
    cId VARCHAR(15),
    orderDate DATE,
    time VARCHAR(15),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(custID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `Order`;
#-----------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    itemCode VARCHAR(15),
    description TEXT,
    packSize VARCHAR(20),
    qtyOnHand INT DEFAULT 0,
    discount INT DEFAULT 0,
    unitPrice DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (itemCode)
    );
SHOW TABLES ;
DESCRIBE Item;
#------------------------
DROP TABLE IF EXISTS `Order Detail`;
CREATE TABLE IF NOT EXISTS `Order Detail`(
    itemCode VARCHAR(15),
    orderId VARCHAR(15),
    orderQty INT,
    discount DOUBLE,
    CONSTRAINT PRIMARY KEY (itemCode, orderId),
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES Item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES `Order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `Order Detail`;

DROP TABLE IF EXISTS UserManagement;
CREATE TABLE IF NOT EXISTS UserManagement(
    userId VARCHAR (15),
    userPassword VARCHAR (15),
    CONSTRAINT PRIMARY KEY (userId, userPassword)
    );
SHOW TABLES ;
DESCRIBE UserManagement;

DROP TABLE IF EXISTS UserCashier;
CREATE TABLE IF NOT EXISTS UserCashier(
    userId VARCHAR (15),
    userPassword VARCHAR (15),
    CONSTRAINT PRIMARY KEY (userId, userPassword)
    );
SHOW TABLES ;
DESCRIBE UserCashier;
