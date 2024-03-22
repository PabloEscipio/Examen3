drop database IKEA;
create database IKEA;
use IKEA;

create table DEPARTAMENTOS(
Codigo int primary key,
Nombre varchar(100),
Presupuesto int
);

create table EMPLEADOS(
DNI varchar(8) primary key,
Nombre varchar(100),
Apellidos varchar(255),
Departamento int,
constraint fk_DepartamentosToEmpleados foreign key(Departamento) references DEPARTAMENTOS(Codigo)
);

insert into DEPARTAMENTOS values
(1, 'Ventas', 120000),
(2, 'Recursos Humanos', 15000),
(3, 'Contabilidad', 45000);

INSERT INTO EMPLEADOS (DNI, Nombre, Apellidos, Departamento) VALUES
('12345678', 'Juan', 'Perez', 1),
('23456789', 'Maria', 'Lopez', 2),
('34567890', 'Carlos', 'Gonzalez', 1),
('45678901', 'Ana', 'Martinez', 3),
('56789012', 'Pedro', 'Sanchez', 2),
('67890123', 'Laura', 'Rodriguez', 1),
('78901234', 'Sofia', 'Fernandez', 3),
('89012345', 'Diego', 'Gomez', 2),
('90123456', 'Elena', 'Diaz', 1),
('01234567', 'Pablo', 'Alvarez', 3),
('11223344', 'Alejandro', 'Romero', 2),
('22334455', 'Lucia', 'Torres', 1),
('33445566', 'Isabel', 'Santos', 3),
('44556677', 'Javier', 'Jimenez', 2),
('55667788', 'Marta', 'Ruiz', 1),
('66778899', 'Raul', 'Hernandez', 3),
('77889900', 'Carmen', 'Garcia', 2),
('88990011', 'Luis', 'Navarro', 1),
('99001122', 'Eva', 'Vazquez', 3),
('00112233', 'Mario', 'Iglesias', 2);

select * from departamentos;
select * from empleados;