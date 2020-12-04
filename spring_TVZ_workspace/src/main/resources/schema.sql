CREATE TABLE IF NOT EXISTS student
(
    id_s          INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(40) NOT NULL,
    last_name     VARCHAR(40) NOT NULL,
    jmbag         VARCHAR(10) NOT NULL,
    date_of_birth TIMESTAMP   NOT NULL,
    ects_points   INT         NOT NULL
);
CREATE TABLE IF NOT EXISTS course
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(60) NOT NULL,
    numberofects INT         NOT NULL
);
CREATE TABLE IF NOT EXISTS studentsandcourses
(
    id_stud   INT NOT NULL,
    id_course INT NOT NULL,
    primary key (id_course, id_stud),
    foreign key (id_course) references course (id),
    foreign key (id_stud) references student (id_s)
);
CREATE TABLE IF NOT EXISTS user
(
    id         identity,
    username   varchar(100) not null,
    password   varchar(250) not null,
    first_name varchar(250) not null,
    last_name  varchar(250) not null
);
CREATE TABLE IF NOT EXISTS authority
(
    id   identity,
    name varchar(100) not null
);
CREATE TABLE IF NOT EXISTS user_authority
(
    user_id      bigint not null,
    authority_id bigint not null,
    constraint fk_user1 foreign key (user_id) references user (id),
    constraint fk_authority1 foreign key (authority_id) references authority (id)
);

