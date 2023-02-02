CREATE DATABASE warcaby;
USE warcaby;

CREATE TABLE gry (
	id int PRIMARY KEY auto_increment,
    gracz1 varchar(250),
    gracz2 varchar(250),
    kolor_gracz1 int,
    kolor_gracz2 int
);

CREATE TABLE stan_planszy (
	id int PRIMARY KEY auto_increment,
	id_gry int,
    numer_ruchu int,
    plansza varchar(250)
);

ALTER TABLE stan_planszy ADD foreign key (id_gry) REFERENCES gry(id);

CREATE USER 'serwer'@'localhost' IDENTIFIED BY 'serwer';
CREATE USER 'klient'@'localhost' IDENTIFIED BY 'klient';
FLUSH PRIVILEGES;

GRANT SELECT, INSERT ON warcaby.gry TO 'serwer'@'localhost';
GRANT SELECT, INSERT ON warcaby.stan_planszy TO 'serwer'@'localhost';

GRANT SELECT ON warcaby.gry TO 'klient'@'localhost';
GRANT SELECT on warcaby.stan_planszy TO 'klient'@'localhost';