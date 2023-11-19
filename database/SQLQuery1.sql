drop database if exists CEMSystem
CREATE DATABASE CEMSystem
use CEMSystem
create table Courses(
	courseID int IDENTITY(9000, 1) PRIMARY KEY,
	[name] nvarchar(200),
	test int 
)
create table Tests(
	TestID int IDENTITY(1, 1) PRIMARY KEY,
	Course int ,
	test_path TEXT ,
	FOREIGN KEY (Course) REFERENCES Courses(courseID)
)

alter table Courses
ADD FOREIGN KEY (test) REFERENCES Tests(TestID);

create table Students (
	StudentId int IDENTITY(1000, 1) PRIMARY KEY,
	password nvarchar(25) ,
	Courses int ,
	FOREIGN KEY (Courses) REFERENCES Courses(courseID)
)
create table Lecturers(
	LecturerID int IDENTITY(2000,1) PRIMARY KEY ,
	[name] nvarchar(200),
	age int check (age > 1),
	[password] nvarchar(25),
	course int ,
	FOREIGN KEY (Course) REFERENCES Courses(courseID)
)

create table Administrators(
	AdminID int IDENTITY(3000,1) PRIMARY KEY,
	[name] nvarchar(200),
	age int check (age > 1),
	[password] nvarchar(25)
)


