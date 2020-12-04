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
						
-- 书本
create table book(bookId int identity(300000, 1) primary key,
						cid int unique,
						bookName varchar(50),
						bookAuthor varchar(20),
						publishing varchar(20),
						imgPath varchar(255),
						foreign key(cid) references course(cid)
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

select * from CourseByAD where institute = '计算机科学与工程学院';

create view CourseByStudent
as select teachID, cname, tname, chour, ccredit, capacity, currentNum, studyTime, description, teach.institute
from teach, teacher, course
where teacher.tid = teach.tid and course.cid = teach.cid and isAudit=2
drop view CourseByStudent

-- 触发器
-- 选课，触发curriculum表
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

-- 退选
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



--数据测试(计算机学院)
insert into AcdemicDean values('11223344', '123456', '计算机科学与工程学院');
insert into AcdemicDean values('44332211', '123456', '数学学院');

insert into teacher values('0001', '123456','王五', '计算机科学与工程学院');
insert into teacher values('0002','123456','赵六',  '计算机科学与工程学院');
insert into teacher values('0003', '123456','蔺相如', '计算机科学与工程学院');
insert into teacher values('1001', '123456','李少白', '数学学院');
insert into teacher values('1002','123456','毛新辉',  '数学学院');
insert into teacher values('1003', '123456','毛毛', '数学学院');

insert into student values('20182000', '123456', '黄思源')
insert into student values('20182001', '123456', '李尖尖')
insert into student values('20182002', '123456', '何晓')
insert into student values('20182003', '123456', '李海潮')


insert into course(cname, chour, ccredit, institute) values('数据结构', '64', 4, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('计算机科学概论', '16', 1, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('数字逻辑', '32', 2, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('Java程序设计', '40', 2.5, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('操作系统', '64', 3.5, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('数据库', '64', 3.5, '计算机科学与工程学院');
insert into course(cname, chour, ccredit, institute) values('高等数学', '80', 5, '数学学院');
insert into course(cname, chour, ccredit, institute) values('线性代数', '70', 4, '数学学院');
insert into course(cname, chour, ccredit, institute) values('离散数学', '60', 3, '数学学院');
insert into course(cname, chour, ccredit, institute) values('概率论', '80', 3, '数学学院');
insert into course(cname, chour, ccredit, institute) values('数学分析', '80', 5, '数学学院');

insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '周一上午', '数据结构很重要啦','计算机科学与工程学院');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100004, '0001', 40, '周二上午', '数据结构很重要啦','计算机科学与工程学院');
insert into teach(cid, tid, capacity, studyTime, description, institute)
values(100005, '0001', 40, '周一上午', '计算机概论牛逼','计算机科学与工程学院');

--测试数据


