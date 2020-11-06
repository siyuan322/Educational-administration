create database Student;
use Student;


create table AcdemicDean(id char(12) primary key,
						password char(32) not null,
						institude char(32));
create table Teacher(tid varchar(12) primary key,
					password varchar(32) not null,
					tname varchar(20),
					institute varchar(32));
create table student(sid varchar(20) primary key,
					password varchar(32),
					sname varchar(20)
					)
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
create table study(sid varchar(20),
					teachID int,
					primary key(sid, teachID),
					foreign key(sid) references student(sid),
					foreign key(teachID) references teach(teachID)
					)

create table curriculum(sid varchar(20),
						cid int,
						tid varchar(12),
						studyTime varchar(32),
						primary key(sid, studyTime),
						foreign key(sid) references student(sid),
						foreign key(cid) references course(cid),
						foreign key(tid) references teacher(tid)
						)

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

create view CourseByStudent
as select teachID, cname, tname, chour, ccredit, capacity, currentNum, studyTime, description, teach.institute
from teach, teacher, course
where teacher.tid = teach.tid and course.cid = teach.cid and isAudit=2
drop view CourseByStudent

-- ������
-- ѡ�Σ�����curriculum��
create trigger chooseStudy
on study after insert
as
begin
declare @teachID char(20)
declare @sid char(20)
declare @cid int
declare @tid char(20)
declare @studyTime char(20)
declare @currentNum int
declare @capacity int
select @sid=sid, @teachID=teachID from inserted
select @cid=cid, @tid=tid, @studyTime=studyTime, @capacity=capacity, @currentNum=currentNum from teach where teachID=@teachID
if (@capacity>@currentNum)
begin
	update teach set currentNum=currentNum+1 where teachID=@teachID
	insert into curriculum values(@sid, @cid, @tid, @studyTime)
end
end

-- ��ѡ
create trigger cancelStudy
on study after delete
as
begin
declare @teachID char(20)
declare @sid char(20)
declare @studyTime char(20)
select @sid=sid, @teachID=teachID from deleted
select @studyTime=studyTime from teach where teachID=@teachID
update teach set currentNum=currentNum-1 where teachID=@teachID
delete from curriculum where sid=@sid and studyTime=@studyTime
end



--���ݲ���(�����ѧԺ)
insert into AcdemicDean values('11223344', '123456', '�������ѧ�빤��ѧԺ');
insert into AcdemicDean values('44332211', '123456', '��ѧѧԺ');

insert into teacher values('0001', '123456','����', '�������ѧ�빤��ѧԺ');
insert into teacher values('0002','123456','����',  '�������ѧ�빤��ѧԺ');
insert into teacher values('0003', '123456','������', '�������ѧ�빤��ѧԺ');
insert into teacher values('1001', '123456','���ٰ�', '��ѧѧԺ');
insert into teacher values('1002','123456','ë�»�',  '��ѧѧԺ');
insert into teacher values('1003', '123456','ëë', '��ѧѧԺ');

insert into student values('20182000', '123456', '��˼Դ')
insert into student values('20182001', '123456', '����')
insert into student values('20182002', '123456', '����')
insert into student values('20182003', '123456', '���')


insert into course(cname, chour, ccredit, institute) values('���ݽṹ', '64', 4, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('�������ѧ����', '16', 1, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('�����߼�', '32', 2, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('Java�������', '40', 2.5, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('����ϵͳ', '64', 3.5, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('���ݿ�', '64', 3.5, '�������ѧ�빤��ѧԺ');
insert into course(cname, chour, ccredit, institute) values('�ߵ���ѧ', '80', 5, '��ѧѧԺ');
insert into course(cname, chour, ccredit, institute) values('���Դ���', '70', 4, '��ѧѧԺ');
insert into course(cname, chour, ccredit, institute) values('��ɢ��ѧ', '60', 3, '��ѧѧԺ');
insert into course(cname, chour, ccredit, institute) values('������', '80', 3, '��ѧѧԺ');
insert into course(cname, chour, ccredit, institute) values('��ѧ����', '80', 5, '��ѧѧԺ');

insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '��һ����', '���ݽṹ����Ҫ��','�������ѧ�빤��ѧԺ');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '�ܶ�����', '���ݽṹ����Ҫ��','�������ѧ�빤��ѧԺ');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100005, '0001', 40, '��һ����', '���������ţ��','�������ѧ�빤��ѧԺ');

--��������


