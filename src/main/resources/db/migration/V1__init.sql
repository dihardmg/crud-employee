CREATE TABLE employee (
  id varchar(255) PRIMARY KEY,
  nama varchar(150) NOT NULL,
  email varchar(255) NOT NULL UNIQUE
);

CREATE TABLE s_users(
  id VARCHAR (40) PRIMARY KEY,
  username VARCHAR (40) NOT NULL,
  password VARCHAR (100) NOT NULL,
  active tinyint(1) DEFAULT NULL
);

CREATE TABLE s_roles(
  id VARCHAR (40) PRIMARY KEY,
  nama VARCHAR (100)NOT NULL
);

CREATE TABLE s_user_role(
  id_user VARCHAR (40)NOT NULL,
  id_role VARCHAR (40)NOT NULL
);