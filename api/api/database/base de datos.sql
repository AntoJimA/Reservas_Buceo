DROP database if exists usuario;
create database usuario;
use usuario;
create table Usuario(
id int not null unique auto_increment,
username varchar(255),
password text, 
nombre varchar(255),
apellido varchar(255),
email varchar(255),
nivelBuceo varchar(255),
roll varchar(24),
primary key(id)
);

create table Salida(
id int not null unique auto_increment,
fecha date,
hora char(5),
capacidad int,
plazasDisponiles int,
primary key(id)
);

create table Reserva(
id_usuario int not null,
id_salida int not null,
primary key(id_usuario,id_salida),
unique(id_usuario,id_salida),
foreign key(id_usuario) references Usuario(id),
foreign key(id_salida) references Salida(id) 
);

select * from Salida;
select * from Usuario;
select * from Reserva;
