CREATE DATABASE student_management;

USE student_management;

CREATE TABLE students (

    id INT PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    age INT NOT NULL,

    course VARCHAR(100) NOT NULL,

    marks DOUBLE NOT NULL
);
