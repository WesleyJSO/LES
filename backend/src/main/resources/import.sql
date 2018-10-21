insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'Gestor') -- controla sistema, visualiza relátórios

insert into `user` values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'zeller@zeller.com', 'José', 'Victor', 123)

insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')
