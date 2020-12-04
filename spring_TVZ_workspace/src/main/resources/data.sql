insert into student(first_name, last_name, jmbag, date_of_birth, ects_points)
values ('Mario', 'Lebic', '0213399738', '1992-02-12 00:00:05', 24);

insert into student(first_name, last_name, jmbag, date_of_birth, ects_points)
values ('Karla', 'Pilic', '0113092837', '1992-01-11 00:00:04', 97);

insert into student(first_name, last_name, jmbag, date_of_birth, ects_points)
values ('Lana', 'Kutes', '0249938473', '1995-09-11 00:00:03', 101);


insert into course(name, numberofects)
values ('Programiranje Web Aplikacija', 5);
insert into course(name, numberofects)
values ('Programiranje u jeziku Java', 5);
insert into course(name, numberofects)
values ('Operacijski sustavi', 6);
insert into course(name, numberofects)
values ('Osnove Elektrotehnike i Elektronike', 7);

insert into studentsandcourses(id_stud, id_course)
values (1, 2);
insert into studentsandcourses(id_stud, id_course)
values (2, 1);
insert into studentsandcourses(id_stud, id_course)
values (3, 3);

insert into user(id, username, password, first_name, last_name)
values (1, 'admin', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'admin', 'admin');
insert into user (id, username, password, first_name, last_name)
values (2, 'user', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'user', 'user');

insert into authority(id, name)
values (1, 'ROLE_ADMIN');
insert into authority(id, name)
values (2, 'ROLE_USER');


insert into user_authority(user_id, authority_id)
values (1, 1);
insert into user_authority(user_id, authority_id)
values (2, 2);

