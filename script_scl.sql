create database db_scl;

use db_scl;

CREATE TABLE PREDIO (
    id int IDENTITY(1,1), 
    tecnico varchar(255) NOT NULL,
	productor varchar(255) NOT NULL,
	super_men varchar(255)NOT NULL,
	nombre_predio varchar(255)NOT NULL,
	comuna_predio varchar(255)NOT NULL,
	km_talca int NOT NULL,
	productividad varchar(255),
    PRIMARY KEY (id)
);

drop table PREDIO;

--inserciones--
insert into PREDIO values('JOSE DANIEL CASTILLO VASQUEZ','ADRIANA VILLALOBOS ALMUNA','15,60','LOTE 2 PARCELA Nº 56 FUNDO LA CUART','LONGAVI','82','');
insert into PREDIO values('JOSE DANIEL CASTILLO VASQUEZ','AGR. LOS CERRILLOS DE MIRAFLORES LT','20,20','PARCELA Nº73 COLONIA ROBERTO OPAZO','LONGAVI','82','');

select * from PREDIO;
