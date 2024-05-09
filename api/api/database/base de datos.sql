DROP database if exists usuario;
create database usuario;
use usuario;
create table usuario(
id int not null unique auto_increment,
nombre varchar(255),
apellido varchar(255),
email varchar(255),
nivelBuceo varchar(255)
);

create table salida(
fecha date,
hora char(5),
capacidad int,
plazasDisponiles int
);