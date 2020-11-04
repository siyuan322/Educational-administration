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

select * from CourseByAD where institute = '�������ѧ�빤��ѧԺ';


--���ݲ���(�����ѧԺ)
insert into AcdemicDean values('11223344', '123456', '�������ѧ�빤��ѧԺ');

insert into teacher values('0001', '123456','����', '�������ѧ�빤��ѧԺ');
insert into teacher values('0002','123456','����',  '�������ѧ�빤��ѧԺ');
insert into teacher values('0003', '123456','����', '�������ѧ�빤��ѧԺ');

insert into course(cname, chour, ccredit, institute) values('���ݽṹ', '64', 4, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('�������ѧ����', '16', 1, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('�����߼�', '32', 2, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('Java�������', '40', 2.5, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('����ϵͳ', '64', 3.5, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('���ݿ�', '64', 3.5, '�������ѧ�빤��ѧԺ');

insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '��һ����', '���ݽṹ����Ҫ��','�������ѧ�빤��ѧԺ');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '�ܶ�����', '���ݽṹ����Ҫ��','�������ѧ�빤��ѧԺ');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100005, '0001', 40, '��һ����', '���������ţ��','�������ѧ�빤��ѧԺ');

--��������


