
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Gestor') -- controla sistema, visualiza relátórios

--insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'zeller@zeller.com', 1234)
INSERT INTO `USER`(ID, ACTIVE, CREATION_DATE, EMAIL, PASSWORD) VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 'zeller@zeller.com' , '1234')

--insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')


insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'silva', 'wesley', 123456789, null, null, (select id from user where email='zeller@zeller.com'))
--insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'wesley@wesley.com', 'silva', 'wesley', 123456, getdate(), 123456789, null, select user_id from user where email='zeller@zeller.com')
--insert into appointment values(
--								HIBERNATE_SEQUENCE.nextval, 1, getdate(), current_time(), current_time(), 
--								current_time(), getdate(), current_time(), current_time(), current_date(), 
--								current_time(), current_time(), current_time(), current_time(), current_time(),  
--								current_time(), select id from employee where name='wesley'
--								)
