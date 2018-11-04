
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_ADMIN', 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_EMPLOYEE', 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_MANAGER', 'Gestor') -- controla sistema, visualiza relátórios

--insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'zeller@zeller.com', 1234)
--INSERT INTO `USER`(ID, ACTIVE, CREATION_DATE, EMAIL, PASSWORD) VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 'zeller@zeller.com' , '1234')
-- For all users, decrypted password is '1234' -- 
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'bruno@bruno.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'wesley@wesley.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'zeller@zeller.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')

--insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')
--insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Administrador')
insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='bruno@bruno.com', select id from role where role_name='Administrador')
insert into user_role values (select id from `user` where email='wesley@wesley.com', select id from role where role_name='Gestor')

--INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 'bruno@bruno.com' , '1234')
--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Holanda', 'Bruno', 123456789, null, null, (select id from user where email='bruno@bruno.com'))
--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Wesley', 'José', 123456789, null, null, (select id from user where email='wesley@wesley.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Holanda', 'Bruno', 123456789, null, null, (select id from user where email='bruno@bruno.com'))



insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), null, null, 10000, null, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'), null)

--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),3,'2018-07',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),5,'2018-08',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),5,'2018-09',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into MONTHLY_BALANCE values(HIBERNATE_SEQUENCE.nextval,1,getDate(),1,'2018-10',(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-01', '01:00:00', '00:00:00', '2018-07-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '17:15:00', '07:15:00', 0, '2018-07-02', '00:00:00', '00:45:00', '2018-07-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-03', '01:00:00', '00:00:00', '2018-07-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-04', '01:00:00', '00:00:00', '2018-07-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-07-05', '01:00:00', '00:00:00', '2018-07-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-01', '01:00:00', '00:00:00', '2018-08-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-02', '01:00:00', '00:00:00', '2018-08-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-03', '01:00:00', '00:00:00', '2018-08-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-04', '01:00:00', '00:00:00', '2018-08-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-08-05', '01:00:00', '00:00:00', '2018-08-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-01', '01:00:00', '00:00:00', '2018-09-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-02', '01:00:00', '00:00:00', '2018-09-02', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-03', '01:00:00', '00:00:00', '2018-09-03', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-04', '01:00:00', '00:00:00', '2018-09-04', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), '13:00:00', '19:00:00', '09:00:00', 0, '2018-09-05', '01:00:00', '00:00:00', '2018-09-05', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into APPOINTMENT values(HIBERNATE_SEQUENCE.nextval,1,getDate(), '13:00:00', '17:00:00', '07:00:00', 0, '2018-10-01', '00:00:00', '01:00:00', '2018-10-01', '09:00:00', '12:00:00', null, null, null, null, 0, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))

insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 0, 'Hora Extra', 2, null, null)
insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 0, 'Banco de Horas', 2, null, null)
insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 1, 'Hora Extra', 2, 2, 'Banco de Horas')

insert into PARAMETER values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 6, null, 20, 50, 12, '01:30:00', 100, (select id from HOUR_TYPE where first = 'Hora Extra' and both = 1), (select id from HOUR_TYPE where first = 'Banco de Horas' and both = 0), (select id from HOUR_TYPE where first = 'Hora Extra' and both = 0))

--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'wesley@wesley.com', 'silva', 'wesley', 123456, getdate(), 123456789, null, select user_id from user where email='zeller@zeller.com')
--insert into appointment values(
--								HIBERNATE_SEQUENCE.nextval, 1, getdate(), current_time(), current_time(), 
--								current_time(), getdate(), current_time(), current_time(), current_date(), 
--								current_time(), current_time(), current_time(), current_time(), current_time(),  
--								current_time(), select id from employee where name='wesley'
--								)
