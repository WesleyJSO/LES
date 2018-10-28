
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Gestor') -- controla sistema, visualiza relátórios

--insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'zeller@zeller.com', 1234)
--INSERT INTO `USER`(ID, ACTIVE, CREATION_DATE, EMAIL, PASSWORD) VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 'zeller@zeller.com' , '1234')

--insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')

--INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 'bruno@bruno.com' , '1234')
--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Holanda', 'Bruno', 123456789, null, null, (select id from user where email='bruno@bruno.com'))

--insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 1, 10000, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),3,'2018-07',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),5,'2018-08',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),5,'2018-09',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),1,'2018-10',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-01', '01:00:00', '00:00:00', '2018-07-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '17:00:00', '09:00:00', 0, '2018-07-02', '01:00:00', '00:00:00', '2018-07-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-03', '01:00:00', '00:00:00', '2018-07-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-04', '01:00:00', '00:00:00', '2018-07-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-05', '01:00:00', '00:00:00', '2018-07-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-01', '01:00:00', '00:00:00', '2018-08-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-02', '01:00:00', '00:00:00', '2018-08-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-03', '01:00:00', '00:00:00', '2018-08-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-04', '01:00:00', '00:00:00', '2018-08-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-05', '01:00:00', '00:00:00', '2018-08-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-01', '01:00:00', '00:00:00', '2018-09-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-02', '01:00:00', '00:00:00', '2018-09-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-03', '01:00:00', '00:00:00', '2018-09-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-04', '01:00:00', '00:00:00', '2018-09-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-05', '01:00:00', '00:00:00', '2018-09-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '17:00:00', '07:00:00', 0, '2018-10-01', '00:00:00', '01:00:00', '2018-10-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'wesley@wesley.com', 'silva', 'wesley', 123456, getdate(), 123456789, null, select user_id from user where email='zeller@zeller.com')
--insert into appointment values(
--								HIBERNATE_SEQUENCE.nextval, 1, getdate(), current_time(), current_time(), 
--								current_time(), getdate(), current_time(), current_time(), current_date(), 
--								current_time(), current_time(), current_time(), current_time(), current_time(),  
--								current_time(), select id from employee where name='wesley'
--								)
