create database Student;
use Student;


create table AcdemicDean(id char(12) primary key,
						password char(32) not null,
						institude char(32));
create table Teacher(tid varchar(12) primary key,
					password varchar(32) not null,
					tname varchar(20),
					institute varchar(32));
create table course(cid int identity(100000, 1) primary key,
					cname varchar(30),
					chour varchar(5),
					ccredit float(1),
					institute varchar(32));
create table Teach(teachID int identity(1000, 1) primary key,
					cid int,
					tid varchar(12),
					capacity int,
					currentNum int default(0),
					studyTime varchar(20),
					description varchar(200),
					institute varchar(32),
					isAudit int default(1),
					foreign key(cid) references course(cid),
					foreign key(tid) references teacher(tid)
					);
create view CourseByTeacher
as
select teachID, tid, cname, chour, ccredit, capacity, currentNum, studyTime, description, isAudit
from course, teach
where course.cid = teach.cid;


create view CourseByAD
as
select teachID, teacher.tid, tname, course.cid, cname,capacity, studyTime, description, isAudit, course.institute
from course, teach, teacher
where course.cid = Teach.cid and Teach.tid = teacher.tid;

select * from CourseByAD where institute = '计算机科学与工程学院';


--数据测试(计算机学院)
insert into AcdemicDean values('11223344', '123456', '计算机科学与工程学院');

insert into teacher values('0001', '123456','张三', '计算机科学与工程学院');
insert into teacher values('0002','123456','李四',  '计算机科学与工程学院');
insert into teacher values('0003', '123456','王五', '计算机科学与工程学院');

insert into course(cname, chour, ccredit, institute) values('数据结构', '64', 4, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('计算机科学概论', '16', 1, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('数字逻辑', '32', 2, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('Java程序设计', '40', 2.5, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('操作系统', '64', 3.5, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('数据库', '64', 3.5, '计算机科学与工程学院');

insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '周一上午', '数据结构很重要啦','计算机科学与工程学院');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '周二上午', '数据结构很重要啦','计算机科学与工程学院');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100005, '0001', 40, '周一上午', '计算机概论牛逼','计算机科学与工程学院');

--测试数据


