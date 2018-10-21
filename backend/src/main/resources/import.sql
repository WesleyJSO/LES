insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Gestor') -- controla sistema, visualiza relátórios

insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'zeller@zeller.com', 'José', 'Victor', 1234)
insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'wesley@wesley.com', 'José', 'Wesley', 1234)

insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')

insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'wesley@gail.com', 'silva', 'wesley', 123456, getdate(), 123456789, null, null)
--insert into appointment values(
--								HIBERNATE_SEQUENCE.nextval, 1, getdate(), current_time(), current_time(), 
--								current_time(), getdate(), current_time(), current_time(), current_date(), 
--								current_time(), current_time(), current_time(), current_time(), current_time(),  
--								current_time(), select id from employee where name='wesley'
--								)
